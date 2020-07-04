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
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

import java.awt.*;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 4PE18
 */
//TODO Discord integration webhook
public class BadWordsNotifier {

    private final BadWordsPlugin instance;
    private final DateTimeFormatter dateTimeFormatter;


    public BadWordsNotifier(BadWordsPlugin instance) {
        this.instance = instance;
        this.getInstance().getLogger().info("Notifier is loading...");

        if (this.getInstance().getConfig().getNotif_perm() == null || this.getInstance().getConfig().getNotif_perm().isEmpty())
            throw new InvalidParameterException("Invalid notif perm parameter!");
        if (this.getInstance().getConfig().getNotif_message() == null || this.getInstance().getConfig().getNotif_message().isEmpty())
            throw new InvalidParameterException("Invalid notif message parameter!");
        if (this.getInstance().getConfig().getDiscord_webhook_resemblance_value() < 0)
            throw new InvalidParameterException("Invalid discord webhook resemblance value parameter!");
        if (this.getInstance().getConfig().getDiscord_webhook_url() == null || this.getInstance().getConfig().getDiscord_webhook_url().isEmpty())
            throw new InvalidParameterException("Invalid discord webhook url parameter!");
        if (this.getInstance().getConfig().getDiscord_webhook_embed_player() == null || this.getInstance().getConfig().getDiscord_webhook_embed_player().isEmpty())
            throw new InvalidParameterException("Invalid discord webhook embed player parameter!");
        if (this.getInstance().getConfig().getDiscord_webhook_embed_message() == null || this.getInstance().getConfig().getDiscord_webhook_embed_message().isEmpty())
            throw new InvalidParameterException("Invalid discord webhook embed message parameter!");
        if (this.getInstance().getConfig().getDiscord_webhook_embed_certitude() == null || this.getInstance().getConfig().getDiscord_webhook_embed_certitude().isEmpty())
            throw new InvalidParameterException("Invalid discord webhook embed certitude parameter!");
        if (this.getInstance().getConfig().getDiscord_webhook_embed_date() == null || this.getInstance().getConfig().getDiscord_webhook_embed_date().isEmpty())
            throw new InvalidParameterException("Invalid discord webhook embed date parameter!");
        Locale locale;
        if (this.getInstance().getConfig().getDiscord_webhook_embed_locale() == null || this.getInstance().getConfig().getDiscord_webhook_embed_locale().isEmpty()) {
            throw new InvalidParameterException("Invalid discord webhook embed locale parameter!");
        } else if (this.getInstance().getConfig().getDiscord_webhook_enabled() && this.getInstance().getConfig().getDiscord_webhook_embed_enabled()) {
            try {
                locale = (Locale) Locale.class.getField(this.getInstance().getConfig().getDiscord_webhook_embed_locale()).get(null);
            } catch (Exception e) {
                throw new InvalidParameterException("Invalid discord webhook embed locale parameter!");
            }
        } else locale = null;
        ZoneId zoneId;
        if (this.getInstance().getConfig().getDiscord_webhook_embed_zoneid() == null || this.getInstance().getConfig().getDiscord_webhook_embed_zoneid().isEmpty()) {
            throw new InvalidParameterException("Invalid discord webhook embed zoneid parameter!");
        } else if (this.getInstance().getConfig().getDiscord_webhook_enabled() && this.getInstance().getConfig().getDiscord_webhook_embed_enabled()) {
            try {
                zoneId = ZoneId.of(this.getInstance().getConfig().getDiscord_webhook_embed_zoneid());
            } catch (Exception e) {
                throw new InvalidParameterException("Invalid discord webhook embed zoneid parameter!");
            }
        } else zoneId = null;
        if (this.getInstance().getConfig().getDiscord_webhook_embed_timeformat() == null || this.getInstance().getConfig().getDiscord_webhook_embed_timeformat().isEmpty())
            throw new InvalidParameterException("Invalid discord webhook embed timeformat parameter!");
        if (this.getInstance().getConfig().getDiscord_webhook_noembed_message() == null || this.getInstance().getConfig().getDiscord_webhook_noembed_message().isEmpty())
            throw new InvalidParameterException("Invalid discord webhook noembed message parameter!");
        if (this.getInstance().getConfig().getDiscord_webhook_enabled() && this.getInstance().getConfig().getDiscord_webhook_embed_enabled()) {
            this.dateTimeFormatter = DateTimeFormatter.ofPattern(this.getInstance().getConfig().getDiscord_webhook_embed_timeformat()).
                    withLocale(locale).withZone(zoneId);
        } else {
            this.dateTimeFormatter = null;
        }


        this.getInstance().getLogger().info("Notifier loaded!");
    }


    public void notify(BadWordsJob work) {
        if (Objects.nonNull(work) && !work.isPending() && work.isToxic()) {
            AtomicInteger staff = new AtomicInteger();
            ProxyServer.getInstance().getPlayers().stream().filter(p -> p.hasPermission(this.getInstance().getConfig().getNotif_perm()))
                    .forEach(p -> {
                        p.sendMessage(TextComponent.fromLegacyText(
                                ChatColor.translateAlternateColorCodes('&', this.getInstance().getConfig().getNotif_message().
                                        replace("%player%", work.getAuthor()).
                                        replace("%message%", this.getInstance().getConfig().getNotif_message_colorcode() +
                                                work.getMessage().replace(work.getHighlight(), this.getInstance().getConfig().getNotif_highlight_colorcode() +
                                                        work.getHighlight() + this.getInstance().getConfig().getNotif_message_colorcode())).
                                        replace("%detection%", work.getDetection()).
                                        replace("%certitude%", work.getResemblance().toString() + "%"))
                        ));
                        staff.getAndIncrement();
                    });
            if (this.getInstance().getConfig().getDiscord_webhook_embed_enabled()) {
                if ((!this.getInstance().getConfig().getDiscord_webhook_no_staff() || staff.get() < 1) &&
                        (!this.getInstance().getConfig().getDiscord_webhook_resemblance_enabled() ||
                                work.getResemblance() >= this.getInstance().getConfig().getDiscord_webhook_resemblance_value())) {
                    DiscordWebhook webhook = new DiscordWebhook(this.getInstance().getConfig().getDiscord_webhook_url());
                    if (this.getInstance().getConfig().getDiscord_webhook_embed_enabled()) {
                        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
                        embed.setTitle(this.getInstance().getConfig().getDiscord_webhook_embed_title());
                        embed.setColor(Color.decode("#7289DA"));
                        embed.setDescription(this.getInstance().getConfig().getDiscord_webhook_embed_description());
                        embed.setThumbnail("https://minotar.net/avatar/" + work.getAuthor() + "/50");
                        embed.addField("**" + this.getInstance().getConfig().getDiscord_webhook_embed_player() + "**",
                                work.getAuthor(),
                                this.getInstance().getConfig().getDiscord_webhook_embed_inline());

                        embed.addField("**" + this.getInstance().getConfig().getDiscord_webhook_embed_message() + "**",
                                work.getMessage().replace(work.getHighlight(), "**" + work.getHighlight() + "**"),
                                this.getInstance().getConfig().getDiscord_webhook_embed_inline());

                        embed.addField("**" + this.getInstance().getConfig().getDiscord_webhook_embed_detection() + "**",
                                work.getDetection(),
                                this.getInstance().getConfig().getDiscord_webhook_embed_inline());

                        embed.addField("**" + this.getInstance().getConfig().getDiscord_webhook_embed_certitude() + "**",
                                "*" + work.getResemblance().toString() + "%*",
                                this.getInstance().getConfig().getDiscord_webhook_embed_inline());

                        embed.addField("**" + this.getInstance().getConfig().getDiscord_webhook_embed_date() + "**",
                                this.getDateTimeFormatter().format(work.getCreation()),
                                false);

                        embed.setFooter("BadWords by 4PE18", null);
                        embed.setTimestamp(work.getResolution());
                        webhook.addEmbed(embed);
                    } else {
                        webhook.setContent(this.getInstance().getConfig().getDiscord_webhook_noembed_message().replace("%player%", work.getAuthor()).
                                replace("%message%",  work.getMessage().replace(work.getHighlight(), "**" + work.getHighlight() + "**").
                                        replace("%certitude%", work.getResemblance().toString() + "%")));
                    }
                    try {
                        webhook.execute();
                    } catch (IOException e) {
                        this.getInstance().getLogger().severe("Failed to execute discord webhook!");
                        e.printStackTrace();
                    }

                }
            }
        }
    }


    public BadWordsPlugin getInstance() {
        return this.instance;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }
}
