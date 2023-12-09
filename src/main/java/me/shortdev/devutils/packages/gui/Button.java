package me.shortdev.devutils.packages.gui;

import me.shortdev.devutils.DevUtils;
import me.shortdev.devutils.packages.customenchantments.CustomEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Button {
    private ItemStack item;
    private Material material;
    private int count;
    private String name;
    private List<String> lore;
    private CustomEnchantment customEnchantment;
    private char colorChar;

    public static DevUtils plugin;

    public Button(Material material, int count, char alternateColorChar, String name, List<String> lore) {
        colorChar = alternateColorChar;
        if (count > 64) count = 64;
        item = new ItemStack(material, count);
        this.name = name;
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes(alternateColorChar, name));
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(alternateColorChar, line));
            }
            meta.setLore(colouredLore);
            this.lore = colouredLore;
        }
        item.setItemMeta(meta);
    }

    public Button(ItemStack item, char alternateColorChar, String name, List<String> lore) {
        colorChar = alternateColorChar;
        this.name = name;
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes(alternateColorChar, name));
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(alternateColorChar, line));
            }
            meta.setLore(colouredLore);
            this.lore = colouredLore;
        }
        item.setItemMeta(meta);
        this.item = item;
    }

    public Button(Material material, int count, char alternateColorChar, String name, List<String> lore, CustomEnchantment customEnchantment) {
        colorChar = alternateColorChar;
        if (count > 64) count = 64;
        item = new ItemStack(material, count);
        this.name = name;
        this.customEnchantment = customEnchantment;
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes(alternateColorChar, name));
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(Objects.requireNonNull(NamespacedKey.fromString(customEnchantment.getKey(), plugin)), PersistentDataType.BOOLEAN, true);
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(alternateColorChar, line));
            }
            if (!customEnchantment.isLoreHidden()) {
                colouredLore.addAll(customEnchantment.getLore());
            }
            meta.setLore(colouredLore);
            this.lore = colouredLore;
        }
        item.setItemMeta(meta);
    }

    public Button(ItemStack item, char alternateColorChar, String name, List<String> lore, CustomEnchantment customEnchantment) {
        colorChar = alternateColorChar;
        this.name = name;
        this.customEnchantment = customEnchantment;
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes(alternateColorChar, name));
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(Objects.requireNonNull(NamespacedKey.fromString(customEnchantment.getKey(), plugin)), PersistentDataType.BOOLEAN, true);
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(alternateColorChar, line));
            }
            if (!customEnchantment.isLoreHidden()) {
                colouredLore.addAll(customEnchantment.getLore());
            }
            meta.setLore(colouredLore);
            this.lore = colouredLore;
        }
        item.setItemMeta(meta);
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
        material = item.getType();
        count = item.getAmount();
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
        item = new ItemStack(material, count);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        item = new ItemStack(material, count);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes(colorChar, name));
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(Objects.requireNonNull(NamespacedKey.fromString(customEnchantment.getKey(), plugin)), PersistentDataType.BOOLEAN, true);
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(colorChar, line));
            }
            colouredLore.addAll(customEnchantment.getLore());
            meta.setLore(colouredLore);
            this.lore = colouredLore;
        }
        item.setItemMeta(meta);
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(colorChar, line));
            }
            colouredLore.addAll(customEnchantment.getLore());
            meta.setLore(colouredLore);
            this.lore = colouredLore;
        }
        item.setItemMeta(meta);
    }

    public CustomEnchantment getCustomEnchantment() {
        return customEnchantment;
    }

    public void setCustomEnchantment(CustomEnchantment customEnchantment) {
        this.customEnchantment = customEnchantment;
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(Objects.requireNonNull(NamespacedKey.fromString(customEnchantment.getKey(), plugin)), PersistentDataType.BOOLEAN, true);
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(colorChar, line));
            }
            colouredLore.addAll(customEnchantment.getLore());
            meta.setLore(colouredLore);
            this.lore = colouredLore;
        }
        item.setItemMeta(meta);
    }

    public char getColorChar() {
        return colorChar;
    }

    public void setColorChar(char colorChar) {
        this.colorChar = colorChar;
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes(colorChar, name));
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(Objects.requireNonNull(NamespacedKey.fromString(customEnchantment.getKey(), plugin)), PersistentDataType.BOOLEAN, true);
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(colorChar, line));
            }
            if (!customEnchantment.isLoreHidden()) {
                colouredLore.addAll(customEnchantment.getLore());
            }
            meta.setLore(colouredLore);
            this.lore = colouredLore;
        }
        item.setItemMeta(meta);
    }
}
