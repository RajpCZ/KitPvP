package cz.rajp.kitbattle.game;

import cz.rajp.kitbattle.Main;
import cz.rajp.kitbattle.utils.CoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class BoardManager implements Listener {

    ScoreboardManager manager = Bukkit.getScoreboardManager();
    final Scoreboard board = manager.getNewScoreboard();
    final Objective objective = board.registerNewObjective("KitPvP", "dummy");

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player hrac = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            public void run() {
                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard board = manager.getNewScoreboard();
                Objective objective = board.registerNewObjective("KitPvP", "dummy");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);

                // - Ranks - //
                String rank = "Silver I";
                if (StatsManager.getKills(hrac) == 250) {
                    rank = "Silver II";
                }
                if (StatsManager.getKills(hrac) == 500) {
                    rank = "Silver III";
                }
                if (StatsManager.getKills(hrac) == 750) {
                    rank = "Silver IV";
                }
                if (StatsManager.getKills(hrac) == 1000) {
                    rank = "Gold I";
                }
                if (StatsManager.getKills(hrac) == 1250) {
                    rank = "Gold II";
                }
                if (StatsManager.getKills(hrac) == 1500) {
                    rank = "Gold III";
                }
                if (StatsManager.getKills(hrac) == 1750) {
                    rank = "Gold IV";
                }
                if (StatsManager.getKills(hrac) == 2000) {
                    rank = "Diamond I";
                }
                if (StatsManager.getKills(hrac) == 2250) {
                    rank = "Diamond II";
                }
                if (StatsManager.getKills(hrac) == 2500) {
                    rank = "Diamond III";
                }
                if (StatsManager.getKills(hrac) == 2750) {
                    rank = "Diamond IV";
                }
                if (StatsManager.getKills(hrac) == 3000) {
                    rank = "Supreme";
                }
                if (StatsManager.getKills(hrac) == 3500) {
                    rank = "Global";
                }

                String target = CoinsAPI.getUUID(hrac.getName());

                // - Board - //
                objective.setDisplayName("§b§lKitPvP");
                Score score1 = objective.getScore("§r");
                score1.setScore(10);
                Score score2 = objective.getScore("§fKills: §3" + String.valueOf(StatsManager.getKills(hrac)));
                score2.setScore(9);
                Score score3 = objective.getScore("§fDeaths: §3" + String.valueOf(StatsManager.getDeaths(hrac)));
                score3.setScore(8);
                Score score4 = objective.getScore("§fKillstreak: §3" + Main.getInstance().killstreaks.get(hrac.getName()));
                score4.setScore(7);
                Score score5 = objective.getScore("§r§f");
                score5.setScore(5);
                Score score6 = objective.getScore("§fCoins: §3" +  Main.getInstance().getConfig().getString("Coins." + target));
                score6.setScore(4);
                Score score7 = objective.getScore("§fRank: §3" + rank);
                score7.setScore(3);
                Score score8 = objective.getScore("§r§f§g");
                score8.setScore(2);
                Score score9 = objective.getScore("  §3mc.skeletria.cz");
                score9.setScore(1);
                hrac.setScoreboard(board);
            }
        }, 0, 20 * 10);
    }
}