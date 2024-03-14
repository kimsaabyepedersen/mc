package org.saabye_pedersen.mc;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;

public class SheepSpawner {


    public Sheep spawnSheep(World world, Location location){
        Sheep sheep = (Sheep) world.spawnEntity(location, EntityType.SHEEP);
        sheep.setColor(DyeColor.CYAN); // Set the sheep's color (replace RED with the desired color)
        sheep.setSheared(true);
        sheep.setCustomName("Sheepdog sheep");
        return sheep;
    }

}
