package me.shortdev.devutils.packages.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class Screen {
    private Inventory inventory;

    public static int defaultSize = 27;

    public Screen(Inventory inventory) {
        this.inventory = inventory;
    }

    public Screen() {
        inventory = Bukkit.createInventory(null, defaultSize);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
