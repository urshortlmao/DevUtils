package shortdev.devutils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public final class DevUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

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
        final char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int randInt = random.nextInt(26);
            builder.append(alphabet[randInt]);
        }
        return builder.toString();
    }

    public static String uniqueRandomAlphabeticalString(int length, List<String> previouslyGenerated) {
        if (length < 0) {
            return null;
        }
        if (previouslyGenerated.size() >= Math.pow(26, length)) {

        }
        StringBuilder builder = new StringBuilder();
        final char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int randInt = random.nextInt(26);
            builder.append(alphabet[randInt]);
        }
        if (!previouslyGenerated.contains(builder.toString())) return builder.toString();

    }

    private static String uniqueRandomAlphabeticalString(int length, List<String> previouslyGenerated, int recursions) {
        
    }

}
