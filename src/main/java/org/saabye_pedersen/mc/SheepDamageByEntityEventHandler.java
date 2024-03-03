package org.saabye_pedersen.mc;

import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SheepDamageByEntityEventHandler {
    public void handle(Sheep sheep, EntityDamageByEntityEvent event) {

        if (sheep.getCustomName() != null && sheep.getCustomName().equals("Sheepdog sheep")) {
            if(sheep.isDead()){
                Player p = sheep.getKiller();
                p.sendMessage("You killed a sheepdog sheep, bravo");
            }else{
                if(event.getDamager() instanceof Player){
                    Player p = (Player) event.getDamager();
                    p.sendMessage("You hit a sheepdog sheep");
                }
            }
        }
    }
}
