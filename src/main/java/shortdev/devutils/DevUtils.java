package shortdev.devutils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import shortdev.devutils.gui.Button;
import shortdev.devutils.listeners.MovementListener;

import java.io.File;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public final class DevUtils extends JavaPlugin {

    public static final char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};


    public boolean debug = true;


    @Override
    public void onEnable() {
        // Plugin startup logic
        Button.plugin = this;

        getServer().getPluginManager().registerEvents(new MovementListener(), this);
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

}
