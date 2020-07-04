/*
 * MIT License
 *
 * Copyright (c) 2020 4PE18
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fr.hyldaria.dev.badwords;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * @author 4PE18
 */
public final class BadWordsDetector {

    private final BadWordsPlugin instance;
    private final List<String> insults;


    BadWordsDetector(BadWordsPlugin instance) {
        this.instance = instance;
        this.getInstance().getLogger().info("Detector is loading...");

        if (this.getInstance().getConfig().getPure_certitude_length() < 0) throw new InvalidParameterException("Invalid pure certitude length parameter!");
        if (this.getInstance().getConfig().getResemblance_min() < 0) throw new InvalidParameterException("Invalid resemblance min parameter!");

        this.getInstance().getLogger().info("Loading insults...");
        this.insults = new ArrayList<>();
        if (this.getInstance().getConfig().getGenerate_spaced_enabled()) {

            this.getInstance().getLogger().info("Generate spaced is enabled, getting insults from config...");
            List<String> strings = this.getInstance().getConfig().getInsults();
            this.getInstance().getLogger().info("Got " + strings.size() + "insults!");

            this.getInstance().getLogger().info("Adding already \"spaced insults\" to the main list to avoid too long variations...");
            this.getInsults().addAll(strings.stream().filter(s -> s.contains(" ")).collect(Collectors.toList()));
            this.getInstance().getLogger().info("Added " + this.getInsults().size() + " insults variations!");

            this.getInstance().getLogger().info("Generating spaced versions for the remaining...");
            this.getInsults().addAll(strings.stream().filter(s -> !s.contains(" ")).map(this::getSpacedVersions).flatMap(Arrays::stream).collect(Collectors.toList()));
            this.getInstance().getLogger().info("Generating spaced versions!");

        } else {

            this.getInstance().getLogger().info("Generate spaced is disabled, getting insults from config...");
            this.getInsults().addAll(this.getInstance().getConfig().getInsults());

        }
        this.getInstance().getLogger().info("Insults loaded (" + this.getInsults().size() + " insults variations) !");

        this.getInstance().getLogger().info("Detector loaded!");
    }

    public BadWordsJob process(BadWordsJob work) {
        detection: for (String i : this.getInsults()) {
            if (this.getInstance().getConfig().getPure_certitude_enabled() && i.length() >= this.getInstance().getConfig().getPure_certitude_length()
                    && work.getMessage().toLowerCase().replaceAll("[^a-zA-Z0-9]+"," ").contains(i.toLowerCase())) {
                work.setResemblance(100);
                work.setToxic(true);
                work.setHighlight(i);
                work.setDetection(i);
                if (this.getInstance().getConfig().getDebugmode()) {
                    this.getInstance().getLogger().log(Level.CONFIG, "[DEBUG] > " + work.getAuthor() + " : " + i + " (" + work.getResemblance() + "%)");
                }
                break;
            } else if (this.getInstance().getConfig().getResemblance_enabled()) {
                for (String w : work.getMessage().replace("  ", "").split(" ")) {
                    int resemblance = this.getResemblance(w.toLowerCase().replaceAll("[^a-zA-Z0-9]+"," "), i.toLowerCase());
                    if (resemblance >= this.getInstance().getConfig().getResemblance_min() &&
                            !this.getInstance().getConfig().getExceptions().contains(w.toLowerCase().replaceAll("[^a-zA-Z0-9]+"," "))) {
                        work.setResemblance(resemblance);
                        work.setDetection(i);
                        work.setToxic(true);
                        work.setHighlight(w);
                        break detection;
                    }
                }
            }
        }

        if (!work.isToxic() && this.getInstance().getConfig().getResemblance_enabled()) {
            detection: for (String i : this.getInsults()) {
                for (Map.Entry<String, String> w : this.getCleanWords(work.getMessage()).entrySet()) {
                    int resemblance = this.getResemblance(w.getKey().toLowerCase().replaceAll("[^a-zA-Z0-9]+"," "), i.toLowerCase());
                    if (resemblance >= this.getInstance().getConfig().getResemblance_min() &&
                            !this.getInstance().getConfig().getExceptions().contains(w.getKey().toLowerCase().replaceAll("[^a-zA-Z0-9]+"," "))) {
                        work.setResemblance(resemblance);
                        work.setToxic(true);
                        work.setHighlight(w.getValue());
                        work.setDetection(i);
                        if (this.getInstance().getConfig().getDebugmode()) {
                            this.getInstance().getLogger().log(Level.CONFIG, "[DEBUG] > " + work.getAuthor() + " : " + i + " (" + work.getResemblance() + "%)");
                        }
                        break detection;
                    }
                }
            }
        }

        work.setPending(false);
        work.setResolution(Instant.now());

        if (work.isToxic() && this.getInstance().getConfig().getDebugmode()) {
            this.getInstance().getLogger().log(Level.CONFIG, "[DEBUG] Positive check on > " + work.toString());
        }

        return work;
    }


    private int getResemblance(String inputA, String inputB) {
        final int lengthA = inputA.length(), lengthB = inputB.length();
        final int[][] distance = new int[lengthA + 1][lengthB + 1];
        for (int i = 0; i <= lengthA; i++) distance[i][0] = i;
        for (int j = 0; j <= lengthB; j++) distance[0][j] = j;
        for (int i = 1; i <= lengthA; i++) for (int j = 1; j <= lengthB; j++)
                distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1), distance[i - 1][j - 1]
                        + (inputA.charAt(i - 1) == inputB.charAt(j - 1) ? 0 : 1));
        return (lengthA - distance[lengthA][lengthB]) * 100 / lengthA;
    }

    private Map<String, String> getCleanWords(String input) {
        String[] words = input.split(" ");
        Map<String, String> map = new HashMap<>();
        StringBuilder in = new StringBuilder();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i <= words.length - 1; i++) {
            if (words[i].length() > 0) {
                in.append(words[i]);
                out.append(words[i]);
                if (i > words.length - 2 || !(words[i].length() <= 2 && words[i+1].length() <= 2) || words[i].length() > 3) {
                    if (!map.containsKey(in.toString()) && in.length() > 0) map.put(in.toString(), out.toString());
                    in = new StringBuilder();
                    out = new StringBuilder();
                } else out.append(" ");
            }
        }
        return map;
    }

    private String[] getSpacedVersions(String input) {
        this.getInstance().getLogger().info("Generating " + (int) Math.pow(2, input.length() - 1) + " variations for " +  input + "...");
        final String[] result = new String[(int) Math.pow(2, input.length() - 1)];
        for (int i = 0; i < Math.pow(2, input.length() - 1); i++) {
            final String format = String.format("%" + (input.length() - 1) + "s", Integer.toBinaryString(i)).replace(' ', '0');
            final StringBuilder builder = new StringBuilder();
            for (int k = 0; k < input.length(); k++)
                builder.append(input.charAt(k)).append((k != input.length() - 1 && format.charAt(k) == '1') ? " " : "");
            result[i] = builder.toString();
        }
        this.getInstance().getLogger().info("Generated variations!");
        return result;
    }


    public BadWordsPlugin getInstance() {
        return this.instance;
    }

    public List<String> getInsults() {
        return this.insults;
    }
}
