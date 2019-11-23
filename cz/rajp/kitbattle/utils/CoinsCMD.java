package cz.rajp.kitbattle.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CoinsCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 3) {
            if (sender.hasPermission("kitpvp.coins")) {
                String target = args[1];
                if (args[0].equalsIgnoreCase("add")) {
                    if (target != null)
                    {
                        int Coins = Integer.parseInt(args[2]);
                        CoinsAPI.addCoins(target, Coins);
                    }
                    else
                    {
                        sender.sendMessage("§cTento hrac je aktulne offline!");
                    }

                } else if (args[0].equalsIgnoreCase("remove")) {

                    if (target != null) {
                        int Coins = Integer.parseInt(args[2]);
                        CoinsAPI.removeCoins(target, Coins);
                    }
                    else {
                        sender.sendMessage("§cTento hrac je aktulne offline!");
                    }
                }
            } else {
                sender.sendMessage("§cTento hrac je aktulne offline!");
            }
        }
        return false;
    }
}