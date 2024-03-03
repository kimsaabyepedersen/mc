package org.saabye_pedersen.mc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    private final SheepSpawner sheepSpawner;

    public CommandHandler(SheepSpawner sheepSpawner) {
        this.sheepSpawner = sheepSpawner;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = ((Player) commandSender).getPlayer();


            if(command.getName().equalsIgnoreCase("sheepdog")){
                p.sendMessage("You are a sheepdog");

                sheepSpawner.spawnSheep(p.getWorld(), p.getLocation());


                return true;
            }


        }


        return true;
    }
}
