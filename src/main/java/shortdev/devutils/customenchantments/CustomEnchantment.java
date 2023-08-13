package shortdev.devutils.customenchantments;

import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.List;

public class CustomEnchantment {
    private static HashMap<NamespacedKey, CustomEnchantment> customEnchantmentMap = new HashMap<>();

    private List<CustomEnchantmentType> types;
    private HashMap<CustomEnchantmentType, Integer> typeLevelMap;
    private final NamespacedKey key;

    public CustomEnchantment(List<CustomEnchantmentType> types, HashMap<CustomEnchantmentType, Integer> typeLevelMap, NamespacedKey key) {
        this.types = types;
        this.typeLevelMap = typeLevelMap;
        this.key = key;
    }

    public boolean addType(CustomEnchantmentType type, int value) {
        types.add(type);

    }
}
