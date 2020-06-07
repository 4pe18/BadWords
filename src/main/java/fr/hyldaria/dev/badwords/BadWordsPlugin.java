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

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Collector;

/**
 * @author 4PE18
 */
public class BadWordsPlugin extends Plugin {

    private final int[] data = new int[] {
            0x00023, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D,
            0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D,
            0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D,
            0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D,
            0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x00023, 0x0000A, 0x00023,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00023, 0x0000A, 0x00023, 0x00020,
            0x00020, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02557, 0x00020, 0x00020, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588,
            0x02557, 0x00020, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02557, 0x00020, 0x02588, 0x02588, 0x02557, 0x00020, 0x00020,
            0x00020, 0x00020, 0x02588, 0x02588, 0x02557, 0x00020, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02557, 0x00020, 0x02588,
            0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02557, 0x00020, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02557, 0x00020,
            0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02557, 0x00020, 0x00020, 0x00023, 0x0000A, 0x00023, 0x00020, 0x00020,
            0x02588, 0x02588, 0x02554, 0x02550, 0x02550, 0x02588, 0x02588, 0x02557, 0x02588, 0x02588, 0x02554, 0x02550, 0x02550, 0x02588, 0x02588,
            0x02557, 0x02588, 0x02588, 0x02554, 0x02550, 0x02550, 0x02588, 0x02588, 0x02557, 0x02588, 0x02588, 0x02551, 0x00020, 0x00020, 0x00020,
            0x00020, 0x02588, 0x02588, 0x02551, 0x02588, 0x02588, 0x02554, 0x02550, 0x02550, 0x02550, 0x02588, 0x02588, 0x02557, 0x02588, 0x02588,
            0x02554, 0x02550, 0x02550, 0x02588, 0x02588, 0x02557, 0x02588, 0x02588, 0x02554, 0x02550, 0x02550, 0x02588, 0x02588, 0x02557, 0x02588,
            0x02588, 0x02554, 0x02550, 0x02550, 0x02550, 0x02550, 0x0255D, 0x00020, 0x00020, 0x00023, 0x0000A, 0x00023, 0x00020, 0x00020, 0x02588,
            0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02554, 0x0255D, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02551,
            0x02588, 0x02588, 0x02551, 0x00020, 0x00020, 0x02588, 0x02588, 0x02551, 0x02588, 0x02588, 0x02551, 0x00020, 0x02588, 0x02557, 0x00020,
            0x02588, 0x02588, 0x02551, 0x02588, 0x02588, 0x02551, 0x00020, 0x00020, 0x00020, 0x02588, 0x02588, 0x02551, 0x02588, 0x02588, 0x02588,
            0x02588, 0x02588, 0x02588, 0x02554, 0x0255D, 0x02588, 0x02588, 0x02551, 0x00020, 0x00020, 0x02588, 0x02588, 0x02551, 0x02588, 0x02588,
            0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02557, 0x00020, 0x00020, 0x00023, 0x0000A, 0x00023, 0x00020, 0x00020, 0x02588, 0x02588,
            0x02554, 0x02550, 0x02550, 0x02588, 0x02588, 0x02557, 0x02588, 0x02588, 0x02554, 0x02550, 0x02550, 0x02588, 0x02588, 0x02551, 0x02588,
            0x02588, 0x02551, 0x00020, 0x00020, 0x02588, 0x02588, 0x02551, 0x02588, 0x02588, 0x02551, 0x02588, 0x02588, 0x02588, 0x02557, 0x02588,
            0x02588, 0x02551, 0x02588, 0x02588, 0x02551, 0x00020, 0x00020, 0x00020, 0x02588, 0x02588, 0x02551, 0x02588, 0x02588, 0x02554, 0x02550,
            0x02550, 0x02588, 0x02588, 0x02557, 0x02588, 0x02588, 0x02551, 0x00020, 0x00020, 0x02588, 0x02588, 0x02551, 0x0255A, 0x02550, 0x02550,
            0x02550, 0x02550, 0x02588, 0x02588, 0x02551, 0x00020, 0x00020, 0x00023, 0x0000A, 0x00023, 0x00020, 0x00020, 0x02588, 0x02588, 0x02588,
            0x02588, 0x02588, 0x02588, 0x02554, 0x0255D, 0x02588, 0x02588, 0x02551, 0x00020, 0x00020, 0x02588, 0x02588, 0x02551, 0x02588, 0x02588,
            0x02588, 0x02588, 0x02588, 0x02588, 0x02554, 0x0255D, 0x0255A, 0x02588, 0x02588, 0x02588, 0x02554, 0x02588, 0x02588, 0x02588, 0x02554,
            0x0255D, 0x0255A, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02554, 0x0255D, 0x02588, 0x02588, 0x02551, 0x00020, 0x00020,
            0x02588, 0x02588, 0x02551, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02588, 0x02554, 0x0255D, 0x02588, 0x02588, 0x02588, 0x02588,
            0x02588, 0x02588, 0x02588, 0x02551, 0x00020, 0x00020, 0x00023, 0x0000A, 0x00023, 0x00020, 0x00020, 0x0255A, 0x02550, 0x02550, 0x02550,
            0x02550, 0x02550, 0x0255D, 0x00020, 0x0255A, 0x02550, 0x0255D, 0x00020, 0x00020, 0x0255A, 0x02550, 0x0255D, 0x0255A, 0x02550, 0x02550,
            0x02550, 0x02550, 0x02550, 0x0255D, 0x00020, 0x00020, 0x0255A, 0x02550, 0x02550, 0x0255D, 0x0255A, 0x02550, 0x02550, 0x0255D, 0x00020,
            0x00020, 0x0255A, 0x02550, 0x02550, 0x02550, 0x02550, 0x02550, 0x0255D, 0x00020, 0x0255A, 0x02550, 0x0255D, 0x00020, 0x00020, 0x0255A,
            0x02550, 0x0255D, 0x0255A, 0x02550, 0x02550, 0x02550, 0x02550, 0x02550, 0x0255D, 0x00020, 0x0255A, 0x02550, 0x02550, 0x02550, 0x02550,
            0x02550, 0x02550, 0x0255D, 0x00020, 0x00020, 0x00023, 0x0000A, 0x00023, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00023, 0x0000A, 0x00023, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00062, 0x00079, 0x00020, 0x00034, 0x00050, 0x00045, 0x00031, 0x00038,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020, 0x00020,
            0x00020, 0x00020, 0x00020, 0x00023, 0x0000A, 0x00023, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D,
            0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D,
            0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D,
            0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D,
            0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D, 0x0003D,
            0x0003D, 0x0003D, 0x00023
    };

    private BadWordsConfig config;
    private BadWordsNotifier notifier;
    private BadWordsDetector detector;
    private BadWordsProcessor processor;


    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info("BadWords is loading...");


        try {
            this.initData();
        } catch (ReflectiveOperationException e) {
            getLogger().severe("Enable error: failed to load data!");
            e.printStackTrace();
            this.onDisable();
            return;
        }

        getLogger().info("Loading data folder/directory...");
        if (!getDataFolder().exists()) {
            getLogger().info("Directory does not exist, creating it...");
            if (getDataFolder().mkdir()) getLogger().info("Directory created successfully!");
            else {
                getLogger().severe("Enable error: failed to create data folder/directory!");
                this.onDisable();
                return;
            }
        }
        getLogger().info("Data folder/directory loaded!");

        getLogger().info("Loading configuration file...");
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            getLogger().info("Configuration file does not exist, creating it and copying the default configuration into it...");
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
                getLogger().info("Configuration file created and default configuration copied into it successfully!");
            } catch (IOException e) {
                getLogger().severe("Enable error: failed to create the configuration file and/or to copy the default configuration into it!");
                e.printStackTrace();
                this.onDisable();
                return;
            }
        }
        getLogger().info("Configuration file loaded!");

        getLogger().info("Loading configuration content from file...");
        Configuration configuration;
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            getLogger().severe("Enable error: failed to load configuration content!");
            e.printStackTrace();
            this.onDisable();
            return;
        }
        getLogger().info("Configuration content loaded!");

        getLogger().info("Creating configuration instance with the  configuration content...");
        try {
            this.config = new BadWordsConfig();
            this.getConfig().setNotif_perm(configuration.getString("notif_perm"));
            this.getConfig().setNotif_message_colorcode(configuration.getString("notif_message_colorcode"));
            this.getConfig().setNotif_highlight_colorcode(configuration.getString("notif_highlight_colorcode"));
            this.getConfig().setNotif_message(configuration.getString("notif_message"));

            this.getConfig().setBypass_enabled(configuration.getBoolean("bypass_enabled"));
            this.getConfig().setBypass_perm(configuration.getString("bypass_perm"));

            this.getConfig().setGenerate_spaced_enabled(configuration.getBoolean("generate_spaced_enabled"));

            this.getConfig().setPure_certitude_enabled(configuration.getBoolean("pure_certitude_enabled"));
            this.getConfig().setPure_certitude_length(configuration.getInt("pure_certitude_length"));
            this.getConfig().setResemblance_enabled(configuration.getBoolean("resemblance_enabled"));
            this.getConfig().setResemblance_min(configuration.getInt("resemblance_min"));

            this.getConfig().setConcurrent_enabled(configuration.getBoolean("concurrent_enabled"));
            this.getConfig().setConcurrent_queue_enabled(configuration.getBoolean("concurrent_queue_enabled"));
            this.getConfig().setConcurrent_queue_capacity(configuration.getInt("concurrent_queue_capacity"));
            this.getConfig().setConcurrent_max_threads(configuration.getInt("concurrent_max_threads"));

            this.getConfig().setDiscord_webhook_enabled(configuration.getBoolean("discord_webhook_enabled"));
            this.getConfig().setDiscord_webhook_no_staff(configuration.getBoolean("discord_webhook_no_staff"));
            this.getConfig().setDiscord_webhook_resemblance_enabled(configuration.getBoolean("discord_webhook_resemblance_enabled"));
            this.getConfig().setDiscord_webhook_resemblance_value(configuration.getInt("discord_webhook_resemblance_value"));
            this.getConfig().setDiscord_webhook_url(configuration.getString("discord_webhook_url"));
            this.getConfig().setDiscord_webhook_embed_enabled(configuration.getBoolean("discord_webhook_embed_enabled"));
            this.getConfig().setDiscord_webhook_embed_title(configuration.getString("discord_webhook_embed_title"));
            this.getConfig().setDiscord_webhook_embed_description(configuration.getString("discord_webhook_embed_description"));
            this.getConfig().setDiscord_webhook_embed_player(configuration.getString("discord_webhook_embed_player"));
            this.getConfig().setDiscord_webhook_embed_message(configuration.getString("discord_webhook_embed_message"));
            this.getConfig().setDiscord_webhook_embed_certitude(configuration.getString("discord_webhook_embed_certitude"));
            this.getConfig().setDiscord_webhook_embed_date(configuration.getString("discord_webhook_embed_date"));
            this.getConfig().setDiscord_webhook_embed_locale(configuration.getString("discord_webhook_embed_locale"));
            this.getConfig().setDiscord_webhook_embed_zoneid(configuration.getString("discord_webhook_embed_zoneid"));
            this.getConfig().setDiscord_webhook_embed_timeformat(configuration.getString("discord_webhook_embed_timeformat"));
            this.getConfig().setDiscord_webhook_embed_inline(configuration.getBoolean("discord_webhook_embed_inline"));
            this.getConfig().setDiscord_webhook_noembed_message(configuration.getString("discord_webhook_noembed_message"));

            this.getConfig().setMysql_enabled(configuration.getBoolean("mysql_enabled"));
            this.getConfig().setMysql_host(configuration.getString("mysql_host"));
            this.getConfig().setMysql_port(configuration.getInt("mysql_port"));
            this.getConfig().setMysql_database(configuration.getString("mysql_database"));
            this.getConfig().setMysql_table(configuration.getString("mysql_table"));

            this.getConfig().setDebugmode(configuration.getBoolean("debugmode"));

            this.getConfig().setInsults(configuration.getStringList("insults"));
            this.getConfig().setExceptions(configuration.getStringList("exceptions"));
        } catch (Exception e) {
            getLogger().severe("Enable error: failed to create configuration instance!");
            e.printStackTrace();
            this.onDisable();
            return;
        }
        getLogger().info("Configuration instance created!");

        getLogger().info("Creating notifier instance...");
        try {
            this.notifier = new BadWordsNotifier(this);
        } catch (Exception e) {
            getLogger().severe("Enable error: failed to create notifier instance!");
            e.printStackTrace();
            this.onDisable();
            return;
        }
        getLogger().info("Notifier instance created!");

        getLogger().info("Creating detector instance...");
        try {
            this.detector = new BadWordsDetector(this);
        } catch (Exception e) {
            getLogger().severe("Enable error: failed to create detector instance!");
            e.printStackTrace();
            this.onDisable();
            return;
        }
        getLogger().info("Detector instance created!");

        getLogger().info("Creating processor instance...");
        try {
            this.processor = new BadWordsProcessor(this);
        } catch (Exception e) {
            getLogger().severe("Enable error: failed to create processor instance!");
            e.printStackTrace();
            this.onDisable();
            return;
        }
        getLogger().info("Processor instance created!");

        getLogger().info("Registering listeners...");
        try {
            ProxyServer.getInstance().getPluginManager().registerListener(this, new BadWordsListeners(this));
        } catch (Exception e) {
            getLogger().severe("Enable error: failed to register listeners!");
            e.printStackTrace();
            this.onDisable();
            return;
        }
        getLogger().info("Listeners registered!");

        getLogger().info("BadWords enabled!");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("BadWords is disabling...");

        getLogger().info("Disabling processor instance...");
        if (this.getProcessor() != null) {
            this.getProcessor().stop();
        }
        getLogger().info("Processor instance disabled!");


        getLogger().info("Unregistering listeners...");
        try {
            ProxyServer.getInstance().getPluginManager().unregisterListeners(this);
        } catch (Exception e) {
            getLogger().severe("Enable error: failed to unregister listeners!");
            e.printStackTrace();
        }
        getLogger().info("Listeners unregistered!");

        getLogger().info("BadWords disabled!");
    }

    private void initData() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method a = this.getClass().getSuperclass().getMethod("getLogger");
        Object b = a.invoke(this);
        Method c = b.getClass().getMethod("info", String.class);
        c.invoke(b, "\n" + Arrays.stream(this.getData()).mapToObj(d -> (char) d).collect(Collector.of(StringBuilder::new,
                StringBuilder::append, StringBuilder::append, StringBuilder::toString)));
    }


    public int[] getData() {
        return this.data;
    }

    public BadWordsConfig getConfig() {
        return this.config;
    }

    public BadWordsNotifier getNotifier() {
        return this.notifier;
    }

    public BadWordsDetector getDetector() {
        return this.detector;
    }

    public BadWordsProcessor getProcessor() {
        return this.processor;
    }

}
