package shortdev.devutils.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GUI {
    private List<Screen> screens = new ArrayList<>();
    public static int defaultSize = 54;

    public GUI() {
        screens.add(new Screen(Bukkit.createInventory(null, defaultSize)));
    }

    public GUI(int inventorySize) {
        screens.add(new Screen(Bukkit.createInventory(null, inventorySize)));
    }

    public GUI(Inventory inventory) {
        screens.add(new Screen(inventory));
    }

    public GUI(Screen screen) {
        screens.add(screen);
    }

    public GUI(List<Screen> screens) {
        this.screens = screens;
    }

}
