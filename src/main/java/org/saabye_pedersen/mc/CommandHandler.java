package org.saabye_pedersen.mc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    private final GameHandler gameHandler;

    public CommandHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player p = ((Player) commandSender).getPlayer();


            if (command.getName().equalsIgnoreCase("sheepdog")) {
                if (args.length == 1) {
                    String arg = args[0];
                    if ("reset".equalsIgnoreCase(arg)) {
                        gameHandler.resetGame(p);
                    } else if ("score".equalsIgnoreCase(arg)) {
                        gameHandler.score(p);
                    }
                    else {
                        p.sendMessage("Unknown subcommand");
                    }
                } else if (args.length == 2) {
                    String arg = args[0];
                    if ("start".equalsIgnoreCase(arg)) {
                        int sheepcount = Integer.valueOf(args[1]);
                        gameHandler.startGame(p, sheepcount);
                    } else if ("add".equalsIgnoreCase(arg)) {
                        Player target = p.getServer().getPlayer(args[1]);
                        if (target != null) {
                            gameHandler.addPlayers(p, target);
                        } else {
                            p.sendMessage("Player not found");
                        }
                    } else {
                        p.sendMessage("Unknown subcommand");
                    }

                } else {
                    p.sendMessage("Please provide a subcommand");
                }

                return true;
            }

        }
        return false;
    }
}