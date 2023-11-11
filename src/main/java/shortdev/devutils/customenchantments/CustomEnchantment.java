package shortdev.devutils.customenchantments;

import org.bukkit.ChatColor;
import shortdev.devutils.DevUtils;

import java.util.*;

public class CustomEnchantment {
    private static HashMap<String, CustomEnchantment> customEnchantmentMap = new HashMap<>();
    private HashMap<CustomEnchantmentType, Integer> typeLevelMap;
    private final String key;
    private boolean hideLore = false;


    public static boolean showRomanNumerals = true;

    public CustomEnchantment(HashMap<CustomEnchantmentType, Integer> typeLevelMap) {
        this.typeLevelMap = typeLevelMap;
        this.key = DevUtils.uniqueRandomAlphabeticalString(8, customEnchantmentMap.keySet());
    }

    public static HashMap<String, CustomEnchantment> getCustomEnchantmentMap() {
        return customEnchantmentMap;
    }

    public static void setCustomEnchantmentMap(HashMap<String, CustomEnchantment> customEnchantmentMap) {
        CustomEnchantment.customEnchantmentMap = customEnchantmentMap;
    }

    public void addType(CustomEnchantmentType type, int value) {
        typeLevelMap.put(type, value);
    }

    public Set<CustomEnchantmentType> getTypes() {
        return typeLevelMap.keySet();
    }

    public HashMap<CustomEnchantmentType, Integer> getTypeLevelMap() {
        return typeLevelMap;
    }

    public void setTypeLevelMap(HashMap<CustomEnchantmentType, Integer> typeLevelMap) {
        this.typeLevelMap = typeLevelMap;
    }

    public String getKey() {
        return key;
    }

    public List<String> getLore() {
        List<String> lore = new ArrayList<>();
        if (showRomanNumerals) {
            for (CustomEnchantmentType type : typeLevelMap.keySet()) {
                lore.add(ChatColor.GRAY + type.getName() + " " + DevUtils.getRomanNumeral(typeLevelMap.get(type)));
            }
        } else {
            for (CustomEnchantmentType type : typeLevelMap.keySet()) {
                lore.add(ChatColor.GRAY + type.getName() + " " + typeLevelMap.get(type));
            }
        }
        return lore;
    }

    public boolean isLoreHidden() {
        return hideLore;
    }

    public void hideLore(boolean hideLore) {
        this.hideLore = hideLore;
    }

    public void hideLore() {
        hideLore = true;
    }

    public void showLore() {
        hideLore = false;
    }
}
