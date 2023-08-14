package shortdev.devutils.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import shortdev.devutils.customenchantments.CustomEnchantment;

import java.util.ArrayList;
import java.util.List;

public class Button {
    private ItemStack item;

    public

    public static Button create(Material material, int count, String name, List<String> lore, CustomEnchantment customEnchantment) {
        if (count > 64) count = 64;
        ItemStack item = new ItemStack(material, count);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(customEnchantment.getKey(), PersistentDataType.BOOLEAN, true);
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes('&', line));
            }
            colouredLore.addAll(customEnchantment.getLore());
            meta.setLore(colouredLore);
        }

    }

    public static Button create(Material material, int count, char alternateColorChar, String name, List<String> lore, CustomEnchantment customEnchantment) {
        if (count > 64) count = 64;
        ItemStack item = new ItemStack(material, count);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes(alternateColorChar, name));
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(customEnchantment.getKey(), PersistentDataType.BOOLEAN, true);
            List<String> colouredLore = new ArrayList<>();
            for (String line : lore) {
                colouredLore.add(ChatColor.translateAlternateColorCodes(alternateColorChar, line));
            }
            colouredLore.addAll(customEnchantment.getLore());
            meta.setLore(colouredLore);
        }

    }

}
