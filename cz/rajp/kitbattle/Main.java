package cz.rajp.kitbattle;

import cz.rajp.kitbattle.events.*;
import cz.rajp.kitbattle.game.BoardManager;
import cz.rajp.kitbattle.game.StatsManager;
import cz.rajp.kitbattle.game.kits.Default;
import cz.rajp.kitbattle.game.kits.Tank;
import cz.rajp.kitbattle.utils.CoinsAPI;
import cz.rajp.kitbattle.utils.CoinsCMD;
import cz.rajp.kitbattle.utils.SetSpawn;
import cz.rajp.kitbattle.utils.stats.Deaths;
import cz.rajp.kitbattle.utils.stats.Kills;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {

    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    public static String prefix = "§8[§bKitPvP§8] §r";
    public static ArrayList<String> defaultt = new ArrayList<String>();
    public static ArrayList<String> tank = new ArrayList<String>();
    public static ArrayList<String> kitused = new ArrayList<String>();

    StatsManager sm;

    public static HashMap<String, Integer> killstreaks = new HashMap<String, Integer>();

    @Override
    public void onEnable() {
        instance = this;
        //KITS
        getCommand("default").setExecutor(new Default());
        getCommand("tank").setExecutor(new Tank());

        //CMDS
        getCommand("setspawn").setExecutor(new SetSpawn());
        getCommand("spawn123456789").setExecutor(new SetSpawn());
        getCommand("coins").setExecutor(new CoinsCMD());

        loadEvents();
        CoinsAPI.createFile();

        sm.kills = new Kills(new File(getDataFolder() + "/Stats/kills.yml"));
        sm.kills.save();
        sm.kills.getConfig().options().copyDefaults(true);
        sm.kills.save();

        sm.deaths = new Deaths(new File(getDataFolder() + "/Stats/deaths.yml"));
        sm.deaths.save();
        sm.deaths.getConfig().options().copyDefaults(true);
        sm.deaths.save();
    }

    public void loadEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new SoupEvent(), this);
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new LeaveEvent(), this);
        pm.registerEvents(new DeathEvent(), this);
        pm.registerEvents(new BoardManager(), this);
        pm.registerEvents(new ItemClick(), this);
        pm.registerEvents(new MenuEvent(), this);
    }
}
