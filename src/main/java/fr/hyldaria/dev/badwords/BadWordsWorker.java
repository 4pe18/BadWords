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

import java.util.Objects;

/**
 * @author 4PE18
 */
public class BadWordsWorker extends Thread {


    private final BadWordsPlugin instance;
    private final Integer number;

    private Boolean running;


    public BadWordsWorker(BadWordsPlugin instance, Integer number) {
        this.instance = instance;
        this.number = number;

        this.running = false;
    }


    @Override
    public void run() {
        this.getInstance().getLogger().info("Thread/Worker #" + this.getNumber() + " started!");
        while (this.getRunning()) {
            try {
                this.getInstance().getNotifier().notify(this.getInstance().getDetector().process(this.getInstance().getProcessor().getQueue().take()));
            } catch (InterruptedException e) {
                if (this.getRunning()) {
                    this.getInstance().getLogger().info("Error with Thread/Worker #" + this.getNumber() + ": waiting for an element in the queue has been interrupted!");
                    e.printStackTrace();
                } else {
                    this.getInstance().getLogger().info("Thread/Worker #" + this.getNumber() + " cancelled waiting...");
                }
            } catch (NullPointerException ignored) {

            }
        }
        this.getInstance().getLogger().info("Thread/Worker #" + this.getNumber() + " stopped!");
    }


    public void setRunning(Boolean running) {
        if (Objects.nonNull(running)) this.running = running;
    }


    public BadWordsPlugin getInstance() {
        return instance;
    }

    public Boolean getRunning() {
        return this.running;
    }

    public Integer getNumber() {
        return this.number;
    }

    public Boolean isRunning() {
        return this.running;
    }
}
