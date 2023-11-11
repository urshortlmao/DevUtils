package shortdev.devutils.data;

import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.List;

public class Save {

    private final String filePath;
    List<String> data;

    public Save(Plugin plugin, String fileName, List<String> data) {
        filePath = plugin.getDataFolder() + fileName;
        this.data = data;
    }

    public void update(List<String> data) {
        this.data = data;
        save();
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String str : data) {
                writer.write(str);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : data) {
            String[] entry = s.split(",");
            // get separate data from line
        }
    }

    public List<String> getData() {
        return data;
    }
}
