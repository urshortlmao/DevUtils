package me.shortdev.devutils.customenchantments;

import org.bukkit.ChatColor;

import java.util.HashMap;

public class CustomEnchantmentType {
    private static HashMap<Integer, CustomEnchantmentType> typeMap = new HashMap<>();
    private String name;
    private final int id;
    private ChatColor color;

    public CustomEnchantmentType(String name) {
        color = ChatColor.GRAY;
        this.name = name;
        int tempId = 1;
        while (typeMap.containsKey(tempId)) tempId++;
        id = tempId;
        typeMap.put(id, this);
    }
    public CustomEnchantmentType(String name, ChatColor color) {
        this.name = name;
        this.color = color;
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

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }
}
