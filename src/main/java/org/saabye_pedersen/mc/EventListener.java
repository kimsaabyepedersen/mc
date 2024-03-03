package org.saabye_pedersen.mc;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EventListener implements Listener {

    private final SheepDamageByEntityEventHandler sheepDamageByEntityEventHandler;

    public EventListener(SheepDamageByEntityEventHandler sheepDamageByEntityEventHandler) {
        this.sheepDamageByEntityEventHandler = sheepDamageByEntityEventHandler;
    }

    @EventHandler
    public void onSheepHit(EntityDamageByEntityEvent event) {

        if (event.getEntityType() == EntityType.SHEEP) {
            sheepDamageByEntityEventHandler.handle((Sheep) event.getEntity(), event);
        }
    }

}
