package shortdev.devutils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import shortdev.devutils.gui.Button;
import shortdev.devutils.listeners.MovementListener;
import shortdev.devutils.npc.NPC;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

public final class DevUtils extends JavaPlugin {

    public static final char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};


    protected boolean debug = true;


    @Override
    public void onEnable() {
        // Plugin startup logic
        Button.plugin = this;

        getServer().getPluginManager().registerEvents(new MovementListener(), this);

        if (NPC.spawnNPCSOnRestart) {

        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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

}
