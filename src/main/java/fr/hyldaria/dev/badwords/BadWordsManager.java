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

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author 4PE18
 */
public class BadWordsManager {

    private final BadWordsPlugin instance;

    private HashMap<UUID, Boolean> notifMap;


    public BadWordsManager(BadWordsPlugin instance) {
        this.instance = instance;
        this.getInstance().getLogger().info("Manager is loading...");

        this.getInstance().getLogger().info("Manager loaded!");
    }


    public void handleNotifChange(ProxiedPlayer player) {
        if (this.getInstance().getConfig().getCommand_enabled()) {
            if (!this.getNotifMap().containsKey(player.getUniqueId())) {
                this.getNotifMap().put(player.getUniqueId(), this.getInstance().getConfig().getCommand_default());
                player.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&',
                        this.getInstance().getConfig().getCommand_default() ?
                        this.getInstance().getConfig().getCommand_notif_enabled() : this.getInstance().getConfig().getCommand_notif_disabled())));
            } else if(this.getNotifMap().get(player.getUniqueId())) {
                this.getNotifMap().replace(player.getUniqueId(), false);
                player.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&',
                        this.getInstance().getConfig().getCommand_notif_disabled())));
            } else {
                this.getNotifMap().replace(player.getUniqueId(), true);
                player.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&',
                        this.getInstance().getConfig().getCommand_notif_enabled())));
            }
        } player.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&',
                this.getInstance().getConfig().getCommand_notif_disabled())));
    }

    public void handleJoin(ProxiedPlayer player) {
        if (this.getInstance().getConfig().getCommand_enabled()) {
            if (!this.getNotifMap().containsKey(player.getUniqueId()))
                this.getNotifMap().put(player.getUniqueId(), this.getInstance().getConfig().getCommand_default());
            if (this.getInstance().getConfig().getCommand_notify_on_join()) {
                player.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', this.getNotifEnabled(player) ?
                        this.getInstance().getConfig().getCommand_notif_enabled() : this.getInstance().getConfig().getCommand_notif_disabled())));
            }
        }
    }

    public void handleLeave(ProxiedPlayer player) {
        if (this.getNotifMap().containsKey(player.getUniqueId()) && this.getInstance().getConfig().getCommand_enabled()
                && this.getInstance().getConfig().getCommand_reset()) this.getNotifMap().remove(player.getUniqueId());
    }

    public HashMap<UUID, Boolean> getNotifMap() {
        return notifMap;
    }

    public Boolean getNotifEnabled(ProxiedPlayer player) {
        if (this.getInstance().getConfig().getCommand_enabled()) {
            if (!this.getNotifMap().containsKey(player.getUniqueId()))
                this.getNotifMap().put(player.getUniqueId(), this.getInstance().getConfig().getCommand_default());
            return this.getNotifMap().get(player.getUniqueId());
        } else return true;
    }

    public BadWordsPlugin getInstance() {
        return instance;
    }
}
