package shortdev.devutils.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import shortdev.devutils.DevUtils;

public class Screen {
    private Inventory inventory;

    public Screen(Inventory inventory) {
        this.inventory = inventory;
    }

    public Screen() {
        inventory = Bukkit.createInventory(null, DevUtils.defaultScreenSize);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
