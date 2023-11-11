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
    private Material material;
    private int count;
    private String name;
    private List<String> lore;

    public static DevUtils plugin;

    public Button(Material material, int count, char alternateColorChar, String name, List<String> lore) {
        if (count > 64) count = 64;
        item = new ItemStack(material, count);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes(alternateColorChar, name));
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(alternateColorChar, line));
            }
            meta.setLore(colouredLore);
        }
        item.setItemMeta(meta);
    }

    public Button(ItemStack item, char alternateColorChar, String name, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes(alternateColorChar, name));
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(alternateColorChar, line));
            }
            meta.setLore(colouredLore);
        }
        item.setItemMeta(meta);
        this.item = item;
    }

    public Button(Material material, int count, char alternateColorChar, String name, List<String> lore, CustomEnchantment customEnchantment) {
        if (count > 64) count = 64;
        item = new ItemStack(material, count);
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
        item.setItemMeta(meta);
    }

    public Button(ItemStack item, char alternateColorChar, String name, List<String> lore, CustomEnchantment customEnchantment) {
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
}
