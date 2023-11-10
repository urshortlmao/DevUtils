package shortdev.devutils.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shortdev.devutils.DevUtils;
import shortdev.devutils.customenchantments.CustomEnchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Button {
    private ItemStack item;

    public static DevUtils plugin;

    public Button(ItemStack item) {
        this.item = item;
    }

    public static Button create(Material material, int count, String name, List<String> lore) {
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
        }
        return new Button(item);
    }

    public static Button create(Material material, int count, char alternateColorChar, String name, List<String> lore) {
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
        }
        return new Button(item);
    }

    public static Button create(Material material, int count, String name, List<String> lore, CustomEnchantment customEnchantment) {
        if (count > 64) count = 64;
        ItemStack item = new ItemStack(material, count);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(Objects.requireNonNull(NamespacedKey.fromString(customEnchantment.getKey(), plugin)), PersistentDataType.BOOLEAN, true);
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes('&', line));
            }
            colouredLore.addAll(customEnchantment.getLore());
            meta.setLore(colouredLore);
        }
        return new Button(item);
    }

    public static Button create(Material material, int count, char alternateColorChar, String name, List<String> lore, CustomEnchantment customEnchantment) {
        if (count > 64) count = 64;
        ItemStack item = new ItemStack(material, count);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes(alternateColorChar, name));
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(Objects.requireNonNull(NamespacedKey.fromString(customEnchantment.getKey(), plugin)), PersistentDataType.BOOLEAN, true);
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(alternateColorChar, line));
            }
            colouredLore.addAll(customEnchantment.getLore());
            meta.setLore(colouredLore);
        }
        return new Button(item);
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}
