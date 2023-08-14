package shortdev.devutils.customenchantments;

import java.util.HashMap;

public class CustomEnchantmentType {
    private static HashMap<Integer, CustomEnchantmentType> typeMap = new HashMap<>();
    private String name;
    private final int id;

    public CustomEnchantmentType(String name) {
        this.name = name;
        int tempId = 1;
        while (typeMap.containsKey(tempId)) tempId++;
        id = tempId;
        typeMap.put(id, this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public static CustomEnchantmentType fromId(int id) {
        return typeMap.get(id);
    }
}
