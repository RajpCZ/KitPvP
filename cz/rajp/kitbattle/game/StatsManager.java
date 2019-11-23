package cz.rajp.kitbattle.game;

import cz.rajp.kitbattle.utils.stats.Deaths;
import cz.rajp.kitbattle.utils.stats.Kills;
import org.bukkit.entity.Player;

public class StatsManager {

    public static Deaths deaths;
    public static Kills kills;

    public static void addKill(Player p, int number) {
        kills.getConfig().set("Players." + p.getName() + ".kill", Integer.valueOf(getKills(p) + number));
        kills.save();
    }

    public static int getKills(Player p) {
        return kills.getConfig().getInt("Players." + p.getName() + ".kill");
    }

    public static void addDeath(Player p, int number) {
        deaths.getConfig().set("Players." + p.getName() + ".death", Integer.valueOf(getDeaths(p) + number));
        deaths.save();
    }

    public static int getDeaths(Player p) {
        return deaths.getConfig().getInt("Players." + p.getName() + ".death");
    }
}