package cz.rajp.kitbattle.utils.stats;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Deaths {

    private FileConfiguration config;
    private File file;

    public Deaths(File paramFile) {
        this.file = paramFile;
        this.config = YamlConfiguration.loadConfiguration(paramFile);
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void save() {
        try {
            this.config.save(this.file);
        }
        catch (IOException localIOException) {

            localIOException.printStackTrace();
        }
    }
}
