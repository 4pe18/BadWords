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

import java.time.Instant;
import java.util.Objects;

/**
 * @author 4PE18
 */
public class BadWordsJob {

    private final String author;
    private final String message;
    private final Instant creation;

    private Boolean pending;

    private Instant resolution;
    private Boolean toxic;
    private Integer resemblance;
    private String highlight;
    private String detection;


    public BadWordsJob(String author, String message) {
        this.author = author;
        this.message = message;
        this.creation = Instant.now();

        this.pending = true;

        this.resolution = this.creation;
        this.toxic = false;
        this.resemblance = 0;
        this.highlight = "";
    }

    public void setPending(Boolean pending) {
        if (Objects.nonNull(pending)) this.pending = pending;
    }

    public void setResolution(Instant resolution) {
        if (Objects.nonNull(resolution)) this.resolution = resolution;
    }

    public void setToxic(Boolean toxic) {
        if (Objects.nonNull(author)) this.toxic = toxic;
    }

    public void setResemblance(Integer resemblance) {
        if (Objects.nonNull(author)) this.resemblance = resemblance;
    }

    public void setHighlight(String highlight) {
        if (Objects.nonNull(author)) this.highlight = highlight;
    }

    public void setDetection(String detection) {
        if (Objects.nonNull(detection)) this.detection = detection;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getMessage() {
        return this.message;
    }

    public Boolean isPending() {
        return this.pending;
    }

    public Instant getCreation() {
        return this.creation;
    }

    public Instant getResolution() {
        return resolution;
    }

    public Boolean isToxic() {
        return this.toxic;
    }

    public Integer getResemblance() {
        return this.resemblance;
    }

    public String getHighlight() {
        return this.highlight;
    }

    public String getDetection() {
        return this.detection;
    }
}
