package org.saabye_pedersen.mc;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");
        GameHandler gameHandler = new GameHandler(new SheepSpawner());
        this.getCommand("sheepdog").setExecutor(new CommandHandler(gameHandler));
        getServer().getPluginManager().registerEvents(new EventListener(new SheepDamageByEntityEventHandler(gameHandler)), this);

    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");

    }
}