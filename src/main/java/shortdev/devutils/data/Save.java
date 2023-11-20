package shortdev.devutils.data;

import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.HashMap;

public class Save {

    private static HashMap<Integer, Save> saves = new HashMap<>();
    private final String filePath;
    private final int id;
    private Plugin plugin;
    HashMap<String, String> data;

    public Save(Plugin plugin, String fileName, HashMap<String, String> data) {
        this.plugin = plugin;
        this.data = data;
        int tempId = 1;
        while (saves.containsKey(tempId)) tempId++;
        id = tempId;
        saves.put(id, this);
        filePath = plugin.getDataFolder() + fileName + "_" + id;
    }

    public Save(int id, Plugin plugin, String fileName, HashMap<String, String> data) throws Exception {
        this.plugin = plugin;
        this.data = data;
        this.id = id;
        filePath = plugin.getDataFolder() + fileName + "_" + id;
        if (saves.containsKey(id)) {
            Save save = saves.get(id);
            throw new Exception(plugin.getName() + " tried to create a duplicate DevUtils Save instance with the ID " + id + ". It seems that the plugin " + save.plugin.getName() + " is already using this ID.");
        }
        saves.put(id, this);
    }

    public Save(String filePath) {
        this.filePath = filePath;
        data = new HashMap<>();
        String[] fileNameParts = filePath.split("_");
        id = Integer.parseInt(fileNameParts[fileNameParts.length - 1]);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineParts = line.split(": ");
                String key = lineParts[0];
                String value;
                StringBuilder builder = new StringBuilder();
                for (int i = 1; i < lineParts.length; i++) {
                    builder.append(lineParts[i]);
                }
                value = builder.toString();
                data.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(HashMap<String, String> data) {
        this.data = data;
        saves.put(id, this);
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String key : data.keySet()) {
                writer.write(key + ": " + data.get(key));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public static HashMap<Integer, Save> getSaves() {
        return saves;
    }

    public static void setSaves(HashMap<Integer, Save> saves) {
        Save.saves = saves;
    }

    public int getId() {
        return id;
    }

}
