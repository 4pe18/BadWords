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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author 4PE18
 */
public class BadWordsConfig {

    private String notif_perm = "badwords.notif";
    private String notif_message_colorcode =  "&e";
    private String notif_highlight_colorcode = "&6";
    private String notif_message = "&c[Filter] &a%player% &7> ";

    private Boolean bypass_enabled = false;
    private String bypass_perm = "badwords.bypass";

    private Boolean generate_spaced_enabled = true;

    private Boolean pure_certitude_enabled = true;
    private Integer pure_certitude_length = 4;
    private Boolean resemblance_enabled = true;
    private Integer resemblance_min = 60;

    private Boolean concurrent_enabled = true;
    private Boolean concurrent_queue_enabled = true;
    private Integer concurrent_queue_capacity = 500;
    private Integer concurrent_max_threads = 5;

    private Boolean discord_webhook_enabled = false;
    private Boolean discord_webhook_no_staff = true;
    private Boolean discord_webhook_resemblance_enabled = false;
    private Integer discord_webhook_resemblance_value = 70;
    private String discord_webhook_url = "https://discordapp.com/api/webhooks/...";
    private Boolean discord_webhook_embed_enabled = true;
    private String discord_webhook_embed_title = "BadWords";
    private String discord_webhook_embed_description = "Message suspicieux détecté!";
    private String discord_webhook_embed_player= "Joueur";
    private String discord_webhook_embed_message = "Message";
    private String discord_webhook_embed_certitude = "Certitude";
    private String discord_webhook_embed_date = "Date";
    private String discord_webhook_embed_locale = "FRANCE";
    private String discord_webhook_embed_zoneid = "Europe/Paris";
    private String discord_webhook_embed_timeformat = "**hh**:**mm**:**ss** '`['dd MMM yyyy']`'";
    private Boolean discord_webhook_embed_inline = true;
    private String discord_webhook_noembed_message = "**%player%** > %message% \n`%certitude% de certitude`";

    private Boolean debugmode = false;

    private List<String> insults = new ArrayList<>();


    BadWordsConfig() {
        insults.addAll(Arrays.asList("connard", "nique", "con", "pd", "salope", "batard", "suce", "suceur", "pute", "fdp"));
    }


    public void setNotif_perm(String notif_perm) {
        if (Objects.nonNull(notif_perm)) this.notif_perm = notif_perm;
    }

    public void setNotif_message_colorcode(String notif_message_colorcode) {
        if (Objects.nonNull(notif_message_colorcode)) this.notif_message_colorcode = notif_message_colorcode;
    }

    public void setNotif_highlight_colorcode(String notif_highlight_colorcode) {
        if (Objects.nonNull(notif_highlight_colorcode)) this.notif_highlight_colorcode = notif_highlight_colorcode;
    }

    public void setNotif_message(String notif_message) {
        if (Objects.nonNull(notif_message)) this.notif_message = notif_message;
    }

    public void setBypass_enabled(Boolean bypass_enabled) {
        if (Objects.nonNull(bypass_enabled)) this.bypass_enabled = bypass_enabled;
    }

    public void setBypass_perm(String bypass_perm) {
        if (Objects.nonNull(bypass_perm)) this.bypass_perm = bypass_perm;
    }

    public void setGenerate_spaced_enabled(Boolean generate_spaced_enabled) {
        if (Objects.nonNull(generate_spaced_enabled)) this.generate_spaced_enabled = generate_spaced_enabled;
    }

    public void setPure_certitude_enabled(Boolean pure_certitude_enabled) {
        if (Objects.nonNull(pure_certitude_enabled)) this.pure_certitude_enabled = pure_certitude_enabled;
    }

    public void setPure_certitude_length(Integer pure_certitude_length) {
        if (Objects.nonNull(pure_certitude_length)) this.pure_certitude_length = pure_certitude_length;
    }

    public void setResemblance_enabled(Boolean resemblance_enabled) {
        if (Objects.nonNull(resemblance_enabled)) this.resemblance_enabled = resemblance_enabled;
    }

    public void setResemblance_min(Integer resemblance_min) {
        if (Objects.nonNull(resemblance_min)) this.resemblance_min = resemblance_min;
    }

    public void setConcurrent_enabled(Boolean concurrent_enabled) {
        if (Objects.nonNull(concurrent_enabled)) this.concurrent_enabled = concurrent_enabled;
    }

    public void setConcurrent_queue_enabled(Boolean concurrent_queue_enabled) {
        if (Objects.nonNull(concurrent_queue_enabled)) this.concurrent_queue_enabled = concurrent_queue_enabled;
    }

    public void setConcurrent_queue_capacity(Integer concurrent_queue_capacity) {
        if (Objects.nonNull(concurrent_queue_capacity)) this.concurrent_queue_capacity = concurrent_queue_capacity;
    }

    public void setConcurrent_max_threads(Integer concurrent_max_threads) {
        if (Objects.nonNull(concurrent_max_threads)) this.concurrent_max_threads = concurrent_max_threads;
    }

    public void setDiscord_webhook_enabled(Boolean discord_webhook_enabled) {
        if (Objects.nonNull(discord_webhook_enabled)) this.discord_webhook_enabled = discord_webhook_enabled;
    }

    public void setDiscord_webhook_no_staff(Boolean discord_webhook_no_staff) {
        if (Objects.nonNull(discord_webhook_no_staff)) this.discord_webhook_no_staff = discord_webhook_no_staff;
    }

    public void setDiscord_webhook_resemblance_enabled(Boolean discord_webhook_resemblance_enabled) {
        if (Objects.nonNull(discord_webhook_resemblance_enabled)) this.discord_webhook_resemblance_enabled = discord_webhook_resemblance_enabled;
    }

    public void setDiscord_webhook_resemblance_value(Integer discord_webhook_resemblance_value) {
        if (Objects.nonNull(discord_webhook_resemblance_value)) this.discord_webhook_resemblance_value = discord_webhook_resemblance_value;
    }

    public void setDiscord_webhook_url(String discord_webhook_url) {
        if (Objects.nonNull(discord_webhook_url)) this.discord_webhook_url = discord_webhook_url;
    }

    public void setDiscord_webhook_embed_enabled(Boolean discord_webhook_embed_enabled) {
        if (Objects.nonNull(discord_webhook_embed_enabled)) this.discord_webhook_embed_enabled = discord_webhook_embed_enabled;
    }

    public void setDiscord_webhook_embed_title(String discord_webhook_embed_title) {
        if (Objects.nonNull(discord_webhook_embed_title)) this.discord_webhook_embed_title = discord_webhook_embed_title;
    }

    public void setDiscord_webhook_embed_description(String discord_webhook_embed_description) {
        if (Objects.nonNull(discord_webhook_embed_description)) this.discord_webhook_embed_description = discord_webhook_embed_description;
    }

    public void setDiscord_webhook_embed_player(String discord_webhook_embed_player) {
        if (Objects.nonNull(discord_webhook_embed_player)) this.discord_webhook_embed_player = discord_webhook_embed_player;
    }

    public void setDiscord_webhook_embed_message(String discord_webhook_embed_message) {
        if (Objects.nonNull(discord_webhook_embed_message)) this.discord_webhook_embed_message = discord_webhook_embed_message;
    }

    public void setDiscord_webhook_embed_certitude(String discord_webhook_embed_certitude) {
        if (Objects.nonNull(discord_webhook_embed_certitude)) this.discord_webhook_embed_certitude = discord_webhook_embed_certitude;
    }

    public void setDiscord_webhook_embed_date(String discord_webhook_embed_date) {
        if (Objects.nonNull(discord_webhook_embed_date)) this.discord_webhook_embed_date = discord_webhook_embed_date;
    }

    public void setDiscord_webhook_embed_locale(String discord_webhook_embed_locale) {
        if (Objects.nonNull(discord_webhook_embed_locale)) this.discord_webhook_embed_locale = discord_webhook_embed_locale;
    }

    public void setDiscord_webhook_embed_zoneid(String discord_webhook_embed_zoneid) {
        if (Objects.nonNull(discord_webhook_embed_zoneid)) this.discord_webhook_embed_zoneid = discord_webhook_embed_zoneid;
    }

    public void setDiscord_webhook_embed_timeformat(String discord_webhook_embed_timeformat) {
        if (Objects.nonNull(discord_webhook_embed_timeformat)) this.discord_webhook_embed_timeformat = discord_webhook_embed_timeformat;
    }

    public void setDiscord_webhook_embed_inline(Boolean discord_webhook_embed_inline) {
        if (Objects.nonNull(discord_webhook_embed_inline)) this.discord_webhook_embed_inline = discord_webhook_embed_inline;
    }

    public void setDiscord_webhook_noembed_message(String discord_webhook_noembed_message) {
        if (Objects.nonNull(discord_webhook_noembed_message)) this.discord_webhook_noembed_message = discord_webhook_noembed_message;
    }

    public void setDebugmode(Boolean debugmode) {
        if (Objects.nonNull(debugmode)) this.debugmode = debugmode;
    }

    public void setInsults(List<String> insults) {
        if (Objects.nonNull(insults)) this.insults = insults;
    }


    public String getNotif_perm() {
        return this.notif_perm;
    }

    public String getNotif_message_colorcode() {
        return this.notif_message_colorcode;
    }

    public String getNotif_highlight_colorcode() {
        return this.notif_highlight_colorcode;
    }

    public String getNotif_message() {
        return this.notif_message;
    }

    public Boolean getBypass_enabled() {
        return this.bypass_enabled;
    }

    public String getBypass_perm() {
        return this.bypass_perm;
    }

    public Boolean getGenerate_spaced_enabled() {
        return this.generate_spaced_enabled;
    }

    public Boolean getPure_certitude_enabled() {
        return this.pure_certitude_enabled;
    }

    public Integer getPure_certitude_length() {
        return this.pure_certitude_length;
    }

    public Boolean getResemblance_enabled() {
        return this.resemblance_enabled;
    }

    public Integer getResemblance_min() {
        return this.resemblance_min;
    }

    public Boolean getConcurrent_enabled() {
        return this.concurrent_enabled;
    }

    public Boolean getConcurrent_queue_enabled() {
        return this.concurrent_queue_enabled;
    }

    public Integer getConcurrent_queue_capacity() {
        return this.concurrent_queue_capacity;
    }

    public Integer getConcurrent_max_threads() {
        return this.concurrent_max_threads;
    }

    public Boolean getDiscord_webhook_enabled() {
        return this.discord_webhook_enabled;
    }

    public Boolean getDiscord_webhook_no_staff() {
        return this.discord_webhook_no_staff;
    }

    public Boolean getDiscord_webhook_resemblance_enabled() {
        return this.discord_webhook_resemblance_enabled;
    }

    public Integer getDiscord_webhook_resemblance_value() {
        return this.discord_webhook_resemblance_value;
    }

    public String getDiscord_webhook_url() {
        return this.discord_webhook_url;
    }

    public Boolean getDiscord_webhook_embed_enabled() {
        return this.discord_webhook_embed_enabled;
    }

    public String getDiscord_webhook_embed_title() {
        return this.discord_webhook_embed_title;
    }

    public String getDiscord_webhook_embed_description() {
        return this.discord_webhook_embed_description;
    }

    public String getDiscord_webhook_embed_player() {
        return this.discord_webhook_embed_player;
    }

    public String getDiscord_webhook_embed_message() {
        return this.discord_webhook_embed_message;
    }

    public String getDiscord_webhook_embed_certitude() {
        return this.discord_webhook_embed_certitude;
    }

    public String getDiscord_webhook_embed_date() {
        return this.discord_webhook_embed_date;
    }

    public String getDiscord_webhook_embed_locale() {
        return this.discord_webhook_embed_locale;
    }

    public String getDiscord_webhook_embed_zoneid() {
        return this.discord_webhook_embed_zoneid;
    }

    public String getDiscord_webhook_embed_timeformat() {
        return this.discord_webhook_embed_timeformat;
    }

    public Boolean getDiscord_webhook_embed_inline() {
        return this.discord_webhook_embed_inline;
    }

    public String getDiscord_webhook_noembed_message() {
        return this.discord_webhook_noembed_message;
    }

    public Boolean getDebugmode() {
        return debugmode;
    }

    public List<String> getInsults() {
        return this.insults;
    }
}
