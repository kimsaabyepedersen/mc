package org.saabye_pedersen.mc;

import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SheepDamageByEntityEventHandler {
    private final GameHandler gameHandler;

    public SheepDamageByEntityEventHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    public void handle(Sheep sheep, EntityDamageByEntityEvent event) {

        if (sheep.getCustomName() != null && sheep.getCustomName().equals("Sheepdog sheep")) {
                if(event.getDamager() instanceof Player){
                    gameHandler.sheepHit((Player) event.getDamager(), sheep);
            }
        }
    }


}
