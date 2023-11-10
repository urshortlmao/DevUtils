package shortdev.devutils.customenchantments;

import org.bukkit.ChatColor;
import shortdev.devutils.DevUtils;

import java.util.*;

public class CustomEnchantment {
    private static HashMap<String, CustomEnchantment> customEnchantmentMap = new HashMap<>();
    private HashMap<CustomEnchantmentType, Integer> typeLevelMap;
    private final String key;

    public CustomEnchantment(HashMap<CustomEnchantmentType, Integer> typeLevelMap) {
        this.typeLevelMap = typeLevelMap;
        this.key = Objects.requireNonNull(DevUtils.uniqueRandomAlphabeticalString(8, customEnchantmentMap.keySet()));
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
        for (CustomEnchantmentType type : typeLevelMap.keySet()) {
            lore.add(ChatColor.GRAY + type.getName() + " " + getRomanNumeral(typeLevelMap.get(type)));
        }
        return lore;
    }

    private String getRomanNumeral(int n) {
        if (n < 1 || n > 10) return String.valueOf(n);
        switch (n) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            case 10:
                return "X";
        }
        return "";
    }

}
