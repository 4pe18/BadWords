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


import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.security.InvalidParameterException;

/**
 * @author 4PE18
 */
public class BadWordsListeners implements Listener {

    private final BadWordsPlugin instance;


    public BadWordsListeners(BadWordsPlugin instance) {
        this.instance = instance;
        this.getInstance().getLogger().info("Listeners are loading...");

        if (this.getInstance().getConfig().getBypass_perm() == null || this.getInstance().getConfig().getBypass_perm().isEmpty())
            throw new InvalidParameterException("Invalid bypass perm parameter!");


        this.getInstance().getLogger().info("Listeners loaded!");
    }

    @EventHandler
    public void onChatEvent(ChatEvent event) {
        if (event.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer sender = (ProxiedPlayer) event.getSender();
            if (!this.getInstance().getConfig().getBypass_enabled() || !sender.hasPermission(this.getInstance().getConfig().getBypass_perm())) {
                this.getInstance().getProcessor().workJob(new BadWordsJob(sender.getName(), event.getMessage()));
            }
        }
    }

    public BadWordsPlugin getInstance() {
        return this.instance;
    }

}
