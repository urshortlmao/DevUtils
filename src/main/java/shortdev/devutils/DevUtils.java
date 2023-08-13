package shortdev.devutils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.java.JavaPlugin;
import shortdev.devutils.customenchantments.CustomEnchantment;

import java.util.ArrayList;
import java.util.List;
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

    public static ItemStack createGuiItem(Material material, int count, String name, List<String> lore, List<CustomEnchantment> customEnchantments) {
        if (count > 64) count = 64;
        ItemStack item = new ItemStack(material, count);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes('&', line));
            }
            meta.setLore(colouredLore);
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set();
        }
    }

    public static ItemStack createGuiItem(Material material, int count, char alternateColorChar, String name, List<String> lore, List<CustomEnchantment> customEnchantments) {
        if (count > 64) count = 64;
        ItemStack item = new ItemStack(material, count);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes(alternateColorChar, name));
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(alternateColorChar, line));
            }
            meta.setLore(colouredLore);
            meta.getPersistentDataContainer()
        }
    }
}
