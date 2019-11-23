package cz.rajp.kitbattle.utils;

import cz.rajp.kitbattle.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SetSpawn implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            return true;
        }
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("setspawn"))
        {
            if ((p.hasPermission("kitpvp.setspawn")) || (p.hasPermission("*")))
            {
                p.sendMessage("§eSpawn nastaven!");
            }
            else
            {
                p.sendMessage("§cYou dont have permissions!");
                return true;
            }
            File ordner = new File("plugins//KitPvP");
            File file = new File("plugins//KitPvP//spawn.yml");
            if (!ordner.exists()) {
                ordner.mkdir();
            }
            if (!file.exists()) {
                try
                {
                    file.createNewFile();
                }
                catch (IOException e)
                {
                    return true;
                }
            }
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            Location loc = p.getLocation();

            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();
            double yaw = loc.getYaw();
            double pitch = loc.getPitch();
            String worldname = loc.getWorld().getName();

            cfg.set("World", worldname);
            cfg.set("x", Double.valueOf(x));
            cfg.set("y", Double.valueOf(y));
            cfg.set("z", Double.valueOf(z));
            cfg.set("yaw", Double.valueOf(yaw));
            cfg.set("pitch", Double.valueOf(pitch));
            try
            {
                cfg.save(file);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("spawn123456789"))
        {
            File file = new File("plugins//KitPvP//spawn.yml");
            if (!file.exists())
            {
                p.sendMessage("");
                return true;
            }
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            Location loc = p.getLocation();
            String worldname = cfg.getString("World");
            double x = cfg.getDouble("x");
            double y = cfg.getDouble("y");
            double z = cfg.getDouble("z");
            double yaw = cfg.getDouble("yaw");
            double pitch = cfg.getDouble("pitch");

            World w = Bukkit.getWorld(worldname);

            loc.setX(x);
            loc.setY(y);
            loc.setZ(z);
            loc.setYaw((float)yaw);
            loc.setPitch((float)pitch);
            loc.setWorld(w);

            p.teleport(loc);

        }
        return false;
    }
}

