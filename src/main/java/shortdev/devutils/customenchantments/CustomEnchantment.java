package shortdev.devutils.customenchantments;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomEnchantment {
    private static HashMap<NamespacedKey, CustomEnchantment> customEnchantmentMap = new HashMap<>();

    private List<CustomEnchantmentType> types;
    private HashMap<CustomEnchantmentType, Integer> typeLevelMap;
    private final NamespacedKey key;

    public CustomEnchantment(List<CustomEnchantmentType> types, HashMap<CustomEnchantmentType, Integer> typeLevelMap) {
        this.types = types;
        this.typeLevelMap = typeLevelMap;
        this.key = NamespacedKey.fromString();
    }

    public static HashMap<NamespacedKey, CustomEnchantment> getCustomEnchantmentMap() {
        return customEnchantmentMap;
    }

    public static void setCustomEnchantmentMap(HashMap<NamespacedKey, CustomEnchantment> customEnchantmentMap) {
        CustomEnchantment.customEnchantmentMap = customEnchantmentMap;
    }

    public void addType(CustomEnchantmentType type, int value) {
        types.add(type);
        typeLevelMap.put(type, value);
    }

    public List<CustomEnchantmentType> getTypes() {
        return types;
    }

    public void setTypes(List<CustomEnchantmentType> types) {
        this.types = types;
    }

    public HashMap<CustomEnchantmentType, Integer> getTypeLevelMap() {
        return typeLevelMap;
    }

    public void setTypeLevelMap(HashMap<CustomEnchantmentType, Integer> typeLevelMap) {
        this.typeLevelMap = typeLevelMap;
    }

    public NamespacedKey getKey() {
        return key;
    }

    public List<String> getLore() {
        List<String> lore = new ArrayList<>();
        for (CustomEnchantmentType type : types) {
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
