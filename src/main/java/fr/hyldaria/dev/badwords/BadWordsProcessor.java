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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 4PE18
 */
public class BadWordsProcessor {

    private final BadWordsPlugin instance;
    private final BlockingQueue<BadWordsJob> queue;
    private final List<BadWordsWorker> workers;


    public BadWordsProcessor(BadWordsPlugin instance) {
        this.instance = instance;
        this.getInstance().getLogger().info("Processor loading...");

        if (this.getInstance().getConfig().getConcurrent_queue_capacity() < 0) throw new InvalidParameterException("Invalid concurrent queue capacity parameter!");
        if (this.getInstance().getConfig().getConcurrent_max_threads() < 0) throw new InvalidParameterException("Invalid concurrent queue capacity parameter!");

        if (this.getInstance().getConfig().getConcurrent_enabled()) {
            this.getInstance().getLogger().info("Concurrent is enabled, checking concurrent queue settings...");
            if (this.getInstance().getConfig().getConcurrent_queue_enabled()) {
                this.getInstance().getLogger().info("Concurrent queue is enabled, creating linked blocking queue with maximum capacity " +
                        this.getInstance().getConfig().getConcurrent_queue_capacity());
                this.queue = new LinkedBlockingQueue<>(this.getInstance().getConfig().getConcurrent_queue_capacity());
                this.getInstance().getLogger().info("created!");
                this.getInstance().getLogger().info("Creating " + this.getInstance().getConfig().getConcurrent_max_threads() + " Threads/Workers...");
                this.workers = new ArrayList<>();
                for (int x = 1; x <= this.getInstance().getConfig().getConcurrent_max_threads(); x++) {
                    this.getInstance().getLogger().info("Instantiating Thread/Worker #" + x + "...");
                    BadWordsWorker worker = new BadWordsWorker(this.getInstance(), x);
                    this.getInstance().getLogger().info("Done. Adding the Thread/Worker to the list...");
                    this.getWorkers().add(worker);
                    this.getInstance().getLogger().info("Done. Preparing the Thread/Worker to start...");
                    worker.setRunning(true);
                    this.getInstance().getLogger().info("Done. Starting the Thread/Worker...");
                    worker.start();
                    this.getInstance().getLogger().info("Thread/Worker #" + x + " created successfully!");
                }
                this.getInstance().getLogger().info("All the Threads/Workers have been created successfully!");
                this.getInstance().getLogger().info("Starting all the threads...");
            } else {
                this.getInstance().getLogger().severe("Concurrent queue is disabled, ignoring other settings... Warning: this mode is discouraged!");
                this.getInstance().getLogger().info("Concurrent is disabled, finishing loading the processor...");
                this.queue = null;
                this.workers = null;
                this.getInstance().getLogger().info("finished!");
            }
        } else {
            this.getInstance().getLogger().info("Concurrent is disabled, finishing loading the processor...");
            this.queue = null;
            this.workers = null;
            this.getInstance().getLogger().info("finished!");
        }

        this.getInstance().getLogger().info("Processor loaded!");
    }

    public void stop() {
        this.getInstance().getLogger().info("Processor is stopping...");

        this.getInstance().getLogger().info("Stopping all the Thread/Worker...");
        this.getWorkers().forEach(t -> {
            if (t.getRunning()) t.setRunning(false);
            t.interrupt();
        });
        this.getInstance().getLogger().info("All the Thread/Worker have been stopped!");

        this.getInstance().getLogger().info("Processor stopped!");
    }


    public void workJob(BadWordsJob job) {
        if (!this.getInstance().getConfig().getConcurrent_enabled())
            this.getInstance().getNotifier().notify(this.getInstance().getDetector().process(job));
        else if (!this.getInstance().getConfig().getConcurrent_queue_enabled()) {
            new Thread(() -> this.getInstance().getNotifier().notify(this.getInstance().getDetector().process(job))).start();
        } else {
            this.getQueue().offer(job);
        }

    }


    public BadWordsPlugin getInstance() {
        return this.instance;
    }

    public BlockingQueue<BadWordsJob> getQueue() {
        return queue;
    }

    public List<BadWordsWorker> getWorkers() {
        return workers;
    }
}
