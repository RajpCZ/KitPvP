package cz.rajp.kitbattle.game.kits;

import cz.rajp.kitbattle.Main;
import net.minecraft.server.v1_8_R1.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Default implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player hrac = (Player) sender;
        String kitName = "Default";

        if (cmd.getName().equalsIgnoreCase("default")) {

            if (!Main.getInstance().defaultt.contains(hrac.getName())) {
                hrac.sendMessage(Main.getInstance().prefix + "§7You have received the §3" + kitName + " §7kit!");

                Main.getInstance().defaultt.add(hrac.getName());
                Main.getInstance().kitused.add(hrac.getName());

                //SETUP INVENTORY
                hrac.getInventory().clear();
                hrac.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD, 1));
                ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
                for(int slot = 0; slot < hrac.getInventory().getSize(); slot++) {
                    if(hrac.getInventory().getItem(slot) == null) {
                        hrac.getInventory().setItem(slot, soup);
                    }
                }
            } else {
                hrac.sendMessage(Main.getInstance().prefix + "§cYou have already chosen this kit!");
            }
        }
        return false;
    }
}
