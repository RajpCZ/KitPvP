package cz.rajp.kitbattle.utils;

import com.google.common.base.Charsets;
import cz.rajp.kitbattle.Main;

import java.util.UUID;

public class CoinsAPI {

    public static void createFile() {
        if (Main.getInstance().getConfig() == null) {

            Main.getInstance().getConfig().set("Coins", "");
            Main.getInstance().saveConfig();
        }
    }


    public static boolean exists(String Player) {
        String target = getUUID(Player);
        if (Main.getInstance().getConfig().get("Coins." + target) != null) {
            return true;
        }
        return false;
    }


    public static void createPlayer(String Player) {
        String target = getUUID(Player);
        if (Main.getInstance().getConfig().get("Coins." + target) == null) {

            Main.getInstance().getConfig().set("Coins." + target, Integer.valueOf(0));
            Main.getInstance().saveConfig();
        }
    }


    public static void addCoins(String Player, int Coins) {
        String target = getUUID(Player);
        if (exists(target)) {

            int coins = Main.getInstance().getConfig().getInt("Coins." + target) + Coins;
            Main.getInstance().getConfig().set("Coins." + target, Integer.valueOf(coins));
            Main.getInstance().saveConfig();
        }
        else {

            createPlayer(target);
            int coins = Main.getInstance().getConfig().getInt("Coins." + target) + Coins;
            Main.getInstance().getConfig().set("Coins." + target, Integer.valueOf(coins));
            Main.getInstance().saveConfig();
        }
    }


    public static void removeCoins(String Player, int Coins) {
        String target = getUUID(Player);
        if (exists(target)) {

            if (Main.getInstance().getConfig().getInt("Coins." + target) > Coins)
            {
                int coins = Main.getInstance().getConfig().getInt("Coins." + target) - Coins;
                Main.getInstance().getConfig().set("Coins." + target, Integer.valueOf(coins));
                Main.getInstance().saveConfig();
            }

        } else {

            createPlayer(target);
            if (Main.getInstance().getConfig().getInt("Coins." + target) > Coins) {

                int coins = Main.getInstance().getConfig().getInt("Coins." + target) - Coins;
                Main.getInstance().getConfig().set("Coins." + target, Integer.valueOf(coins));
                Main.getInstance().saveConfig();
            }
        }
    }

    public static void setCoins(String Player, int Coins) {
        String target = getUUID(Player);
        if (exists(target)) {

            Main.getInstance().getConfig().set("Coins." + target, Integer.valueOf(Coins));
            Main.getInstance().saveConfig();
        }
        else {

            createPlayer(target);
            Main.getInstance().getConfig().set("Coins." + target, Integer.valueOf(Coins));
            Main.getInstance().saveConfig();
        }
    }

    public static String getUUID(String Player) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + Player).getBytes(Charsets.UTF_8)).toString();
    }
}
