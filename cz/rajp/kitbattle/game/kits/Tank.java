package cz.rajp.kitbattle.game.kits;

import cz.rajp.kitbattle.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Tank implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player hrac = (Player) sender;
        String kitName = "Tank";

        if (cmd.getName().equalsIgnoreCase("tank")) {

            if (!Main.getInstance().tank.contains(hrac.getName())) {
                hrac.sendMessage(Main.getInstance().prefix + "§7You have received the §3" + kitName + " §7kit!");

                Main.getInstance().tank.add(hrac.getName());
                Main.getInstance().kitused.add(hrac.getName());

                //SETUP INVENTORY
                hrac.getInventory().clear();
                hrac.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD, 1));
                hrac.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1));
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
