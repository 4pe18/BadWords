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
    private String notif_message = "&8[&9BadWords&8] &a%player% &7> %message% &7(&e%detection% : %certitude%&7)";

    private Boolean bypass_enabled = false;
    private String bypass_perm = "badwords.bypass";

    private Boolean command_enabled = true;
    private String command_label = "badwords";
    private String command_no_perm = "&8[&9BadWords&8] &cDésolé, vous n'avez pas la permission pour utiliser cette commande";
    private String command_console = "&8[&9BadWords&8] &cErreur: cette commande n'est pas utilisable par la console";
    private String command_notif_enabled = "&8[&9BadWords&8] &eMessages de notifications &aactivés &e!";
    private String command_notif_disabled = "&8[&9BadWords&8] &eMessages de notifications &cdésactivés &e!";
    private Boolean command_default = true;
    private Boolean command_reset = true;
    private Boolean command_notify_on_join = false;

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
    private String discord_webhook_embed_detection = "Détection";
    private String discord_webhook_embed_certitude = "Certitude";
    private String discord_webhook_embed_date = "Date";
    private String discord_webhook_embed_locale = "FRANCE";
    private String discord_webhook_embed_zoneid = "Europe/Paris";
    private String discord_webhook_embed_timeformat = "**hh**:**mm**:**ss** '`['dd MMM yyyy']`'";
    private Boolean discord_webhook_embed_inline = true;
    private String discord_webhook_noembed_message = "**%player%** > %message% \n`\"%detection%\" : %certitude%%`";

    private Boolean debugmode = false;

    private Boolean mysql_enabled = false;
    private String mysql_host = "0.0.0.0";
    private Integer mysql_port = 3306;
    private String mysql_username = "admin";
    private String mysql_password = "admin123";
    private String mysql_database = "database";
    private String mysql_table = "badwords";

    private String mysql_table_id = "id";
    private String mysql_table_uuid = "uuid";
    private String mysql_table_username = "username";
    private String mysql_table_servername = "servername";
    private String mysql_table_date = "date";
    private String mysql_table_message = "message";
    private String mysql_table_highlight = "highlight";
    private String mysql_table_foundword = "foundword";
    private String mysql_table_resemblance = "resemblance";

    private List<String> insults = new ArrayList<>();
    private List<String> exceptions = new ArrayList<>();


    BadWordsConfig() {
        insults.addAll(Arrays.asList("connard", "nique", "con", "pd", "salope", "batard", "suce", "suceur", "pute", "fdp"));
        exceptions.addAll(Arrays.asList("con:config", "configuration"));
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

    public void setCommand_enabled(Boolean command_enabled) {
        if (Objects.nonNull(command_enabled)) this.command_enabled = command_enabled;
    }

    public void setCommand_label(String command_label) {
        if (Objects.nonNull(command_label)) this.command_label = command_label;
    }

    public void setCommand_no_perm(String command_no_perm) {
        if (Objects.nonNull(command_no_perm)) this.command_no_perm = command_no_perm;
    }

    public void setCommand_console(String command_console) {
        if (Objects.nonNull(command_console)) this.command_console = command_console;
    }

    public void setCommand_notif_enabled(String command_notif_enabled) {
        if (Objects.nonNull(command_notif_enabled)) this.command_notif_enabled = command_notif_enabled;
    }

    public void setCommand_notif_disabled(String command_notif_disabled) {
        if (Objects.nonNull(command_notif_disabled)) this.command_notif_disabled = command_notif_disabled;
    }

    public void setGenerate_spaced_enabled(Boolean generate_spaced_enabled) {
        if (Objects.nonNull(generate_spaced_enabled)) this.generate_spaced_enabled = generate_spaced_enabled;
    }

    public void setCommand_default(Boolean command_default) {
        if (Objects.nonNull(command_default)) this.command_default = command_default;
    }

    public void setCommand_reset(Boolean command_reset) {
        if (Objects.nonNull(command_reset)) this.command_reset = command_reset;
    }

    public void setCommand_notify_on_join(Boolean command_notify_on_join) {
        if (Objects.nonNull(command_notify_on_join)) this.command_notify_on_join = command_notify_on_join;
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

    public void setDiscord_webhook_embed_detection(String discord_webhook_embed_detection) {
        if (Objects.nonNull(discord_webhook_embed_detection)) this.discord_webhook_embed_detection = discord_webhook_embed_detection;
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

    public void setMysql_enabled(Boolean mysql_enabled) {
        if (Objects.nonNull(mysql_enabled)) this.mysql_enabled = mysql_enabled;
    }

    public void setMysql_host(String mysql_host) {
        if (Objects.nonNull(mysql_host)) this.mysql_host = mysql_host;
    }

    public void setMysql_port(Integer mysql_port) {
        if (Objects.nonNull(mysql_port)) this.mysql_port = mysql_port;
    }

    public void setMysql_username(String mysql_username) {
        if (Objects.nonNull(mysql_username)) this.mysql_username = mysql_username;
    }

    public void setMysql_password(String mysql_password) {
        if (Objects.nonNull(mysql_password)) this.mysql_password = mysql_password;
    }

    public void setMysql_database(String mysql_database) {
        if (Objects.nonNull(mysql_database)) this.mysql_database = mysql_database;
    }

    public void setMysql_table(String mysql_table) {
        if (Objects.nonNull(mysql_table)) this.mysql_table = mysql_table;
    }

    public void setMysql_table_id(String mysql_table_id) {
        if (Objects.nonNull(mysql_table_id)) this.mysql_table_id = mysql_table_id;
    }

    public void setMysql_table_uuid(String mysql_table_uuid) {
        if (Objects.nonNull(mysql_table_uuid)) this.mysql_table_uuid = mysql_table_uuid;
    }

    public void setMysql_table_username(String mysql_table_username) {
        if (Objects.nonNull(mysql_table_username)) this.mysql_table_username = mysql_table_username;
    }

    public void setMysql_table_servername(String mysql_table_servername) {
        if (Objects.nonNull(mysql_table_servername)) this.mysql_table_servername = mysql_table_servername;
    }

    public void setMysql_table_date(String mysql_table_date) {
        if (Objects.nonNull(mysql_table_date)) this.mysql_table_date = mysql_table_date;
    }

    public void setMysql_table_message(String mysql_table_message) {
        if (Objects.nonNull(mysql_table_message)) this.mysql_table_message = mysql_table_message;
    }

    public void setMysql_table_highlight(String mysql_table_highlight) {
        if (Objects.nonNull(mysql_table_highlight)) this.mysql_table_highlight = mysql_table_highlight;
    }

    public void setMysql_table_foundword(String mysql_table_foundword) {
        if (Objects.nonNull(mysql_table_foundword)) this.mysql_table_foundword = mysql_table_foundword;
    }

    public void setMysql_table_resemblance(String mysql_table_resemblance) {
        if (Objects.nonNull(mysql_table_resemblance)) this.mysql_table_resemblance = mysql_table_resemblance;
    }

    public void setInsults(List<String> insults) {
        if (Objects.nonNull(insults)) this.insults = insults;
    }

    public void setExceptions(List<String> exceptions) {
        if (Objects.nonNull(exceptions)) this.exceptions = exceptions;
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

    public Boolean getCommand_enabled() {
        return command_enabled;
    }

    public String getCommand_label() {
        return command_label;
    }

    public String getCommand_no_perm() {
        return command_no_perm;
    }

    public String getCommand_console() {
        return command_console;
    }

    public String getCommand_notif_enabled() {
        return command_notif_enabled;
    }

    public String getCommand_notif_disabled() {
        return command_notif_disabled;
    }

    public Boolean getCommand_default() {
        return command_default;
    }

    public Boolean getCommand_reset() {
        return command_reset;
    }

    public Boolean getCommand_notify_on_join() {
        return command_notify_on_join;
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

    public String getDiscord_webhook_embed_detection() {
        return this.discord_webhook_embed_detection;
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

    public Boolean getMysql_enabled() {
        return mysql_enabled;
    }

    public String getMysql_host() {
        return mysql_host;
    }

    public Integer getMysql_port() {
        return mysql_port;
    }

    public String getMysql_username() {
        return mysql_username;
    }

    public String getMysql_password() {
        return mysql_password;
    }

    public String getMysql_database() {
        return mysql_database;
    }

    public String getMysql_table() {
        return mysql_table;
    }

    public String getMysql_table_id() {
        return mysql_table_id;
    }

    public String getMysql_table_uuid() {
        return mysql_table_uuid;
    }

    public String getMysql_table_username() {
        return mysql_table_username;
    }

    public String getMysql_table_servername() {
        return mysql_table_servername;
    }

    public String getMysql_table_date() {
        return mysql_table_date;
    }

    public String getMysql_table_message() {
        return mysql_table_message;
    }

    public String getMysql_table_highlight() {
        return mysql_table_highlight;
    }

    public String getMysql_table_foundword() {
        return mysql_table_foundword;
    }

    public String getMysql_table_resemblance() {
        return mysql_table_resemblance;
    }

    public List<String> getInsults() {
        return this.insults;
    }

    public List<String> getExceptions() {
        return this.exceptions;
    }
}
