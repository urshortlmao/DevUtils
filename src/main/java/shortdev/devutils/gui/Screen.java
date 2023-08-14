package shortdev.devutils.gui;

import org.bukkit.inventory.Inventory;

public class Screen {
    private Inventory inventory;

    public Screen(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
