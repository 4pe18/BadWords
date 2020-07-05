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
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author 4PE18
 */
public class BadWordsCommandWrapper {

    private final BadWordsPlugin instance;

    private Command command;


    public BadWordsCommandWrapper(BadWordsPlugin instance) {
        this.instance = instance;
        this.getInstance().getLogger().info("CommandWrapper is loading...");

        this.getInstance().getLogger().info("Creating command...");
        createCommand();

        if (this.getInstance().getConfig().getCommand_enabled()) {
            this.getInstance().getLogger().info("Command is enabled, registering it...");
            this.getInstance().getProxy().getPluginManager().registerCommand(this.getInstance(), command);
        }
        this.getInstance().getLogger().info("CommandWrapper loaded!");
    }

    public void createCommand() {
        this.command = new Command(this.getInstance().getConfig().getCommand_label()) {

            @Override
            public void execute(CommandSender commandSender, String[] strings) {
                if (!commandSender.hasPermission(BadWordsCommandWrapper.this.getInstance().getConfig().getNotif_perm()))
                    commandSender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&',
                            BadWordsCommandWrapper.this.getInstance().getConfig().getCommand_no_perm())));
                if (commandSender instanceof ProxiedPlayer) {
                    BadWordsCommandWrapper.this.getInstance().getManager().handleNotifChange((ProxiedPlayer) commandSender);
                } else {
                    commandSender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&',
                            BadWordsCommandWrapper.this.getInstance().getConfig().getCommand_console())));
                }
            }
        };
    }

    public Command getCommand() {
        return command;
    }

    public BadWordsPlugin getInstance() {
        return instance;
    }
}
