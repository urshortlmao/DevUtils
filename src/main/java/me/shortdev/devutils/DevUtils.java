package me.shortdev.devutils;

import me.shortdev.devutils.data.Save;
import me.shortdev.devutils.npc.listeners.MovementListener;
import me.shortdev.devutils.npc.listeners.RightClickListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public final class DevUtils extends JavaPlugin {

    public static final char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static DevUtils plugin;
    private static HashMap<String, Plugin> plugins;
    private static HashMap<Plugin, Set<Class<?>>> pluginResponseClassMap;


    public boolean debug = true;


    @Override
    public void onEnable() {
        getLogger().info("DevUtils Alpha v1.0.0");

        //Making this instance statically accessible
        plugin = this;

        //Registering event classes
        getServer().getPluginManager().registerEvents(new MovementListener(), this);
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);

        //Load save metadata
        List<String> pluginData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getDataFolder() + "PluginInfo"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                pluginData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<Integer, Save> saves = new HashMap<>();
        for (String str : pluginData) {
            String[] lineParts = str.split(": ");
            for (File file : Objects.requireNonNull(new File(lineParts[1]).listFiles())) {
                Save save = new Save(file.getPath());
                saves.put(save.getId(), save);
            }
        }
        Save.setSaves(saves);
    }

    @Override
    public void onDisable() {
        //Save save metadata
        for (Save save : Save.getSaves().values()) {
            save.saveToFile();
        }
        Save save = new Save(this, "PluginInfo", new HashMap<>());
        HashMap<String, String> data = new HashMap<>();
        for (Plugin plugin : plugins.values()) {
            data.put(plugin.getName(), plugin.getDataFolder().getPath());
        }
        save.update(data);
        save.saveToFile();
    }

    public static void sendMessage(String message, Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void sendMessage(char alternateColorChar, String message, Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes(alternateColorChar, message));
    }

    public static boolean sendMessage(String message, UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        if (p == null || !p.isOnline()) {
            return false;
        }
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        return true;
    }

    public static boolean sendMessage(char alternateColorChar, String message, UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        if (p == null || !p.isOnline()) {
            return false;
        }
        p.sendMessage(ChatColor.translateAlternateColorCodes(alternateColorChar, message));
        return true;
    }

    public static String randomAlphabeticalString(int length) {
        if (length < 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int randInt = random.nextInt(26);
            builder.append(alphabet[randInt]);
        }
        return builder.toString();
    }

    public static String uniqueRandomAlphabeticalString(int length, Set<String> previouslyGenerated) {
        if (length < 0) {
            return null;
        }
        if (previouslyGenerated.size() >= Math.pow(26, length)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int randInt = random.nextInt(26);
            builder.append(alphabet[randInt]);
        }
        if (!previouslyGenerated.contains(builder.toString())) return builder.toString();
        return uniqueRandomAlphabeticalString(length, previouslyGenerated);
    }

    public static String getRomanNumeral(int x) {
        if (x == 0) return "0";
        if (x > 3999) return String.valueOf(x);
        int n = Math.abs(x);
        StringBuilder stringBuilder = new StringBuilder();
        if (Math.signum(x) == -1) stringBuilder.append("-");
        while (n > 0) {
            int magnitude = (int) Math.log10(n);
            int digit = (int) (n * Math.pow(10, -magnitude));
            int delta = (int) (digit * Math.pow(10, magnitude));
            n -= delta;
            stringBuilder.append(switch (delta) {
                case 3000 -> "MMM";
                case 2000 -> "MM";
                case 1000 -> "M";
                case 900 -> "CM";
                case 800 -> "DCCC";
                case 700 -> "DCC";
                case 600 -> "DC";
                case 500 -> "D";
                case 400 -> "CD";
                case 300 -> "CCC";
                case 200 -> "CC";
                case 100 -> "C";
                case 90 -> "XC";
                case 80 -> "LXXX";
                case 70 -> "LXX";
                case 60 -> "LX";
                case 50 -> "L";
                case 40 -> "XL";
                case 30 -> "XXX";
                case 20 -> "XX";
                case 10 -> "X";
                case 9 -> "IX";
                case 8 -> "VIII";
                case 7 -> "VII";
                case 6 -> "VI";
                case 5 -> "V";
                case 4 -> "IV";
                case 3 -> "III";
                case 2 -> "II";
                case 1 -> "I";
                default -> "";
            });
        }
        return stringBuilder.toString();
    }

    public static void registerNPCResponseClasses(Plugin plugin, Class<?>... npcResponseClasses) {
        pluginResponseClassMap.put(plugin, Set.copyOf(Arrays.asList(npcResponseClasses)));
    }

    public static DevUtils getPlugin() {
        return plugin;
    }

    public static void registerPlugin(Plugin plugin) {
        plugins.put(plugin.getName(), plugin);
    }

    public static HashMap<Plugin, Set<Class<?>>> getPluginResponseClassMap() {
        return pluginResponseClassMap;
    }
}
