package org.saabye_pedersen.mc;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");
        this.getCommand("sheepdog").setExecutor(new CommandHandler(new SheepSpawner()));
        getServer().getPluginManager().registerEvents(new EventListener(new SheepDamageByEntityEventHandler()), this);

    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");

    }
}