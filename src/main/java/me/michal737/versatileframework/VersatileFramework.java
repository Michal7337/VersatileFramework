package me.michal737.versatileframework;

import me.michal737.versatileframework.MiningSystem.MiningListeners;
import me.michal737.versatileframework.commands.MainVfGUI;
import me.michal737.versatileframework.commands.vf;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class VersatileFramework extends JavaPlugin {

    private static VersatileFramework pluginInstance;
    private static File blockDataFile;

    @Override
    public void onEnable() {

        pluginInstance = this;
        blockDataFile = new File(getDataFolder().getAbsolutePath() + "/BlockData.json");

        try {setupFiles();} catch (IOException e) {throw new RuntimeException(e);}
        registerListeners();
        registerCommands();

        getLogger().info("VersatileFramework enabled!");

    }

    @Override
    public void onDisable() {

        getLogger().info("VersatileFramework disabled!");

    }

    public static VersatileFramework getInstance(){
        return pluginInstance;
    }

    public static File getBlockDataFile() {
        return blockDataFile;
    }

    private void registerListeners(){

        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new MainVfGUI(), this);
        pluginManager.registerEvents(new MiningListeners(), this);

    }

    private void registerCommands(){

        getCommand("vf").setExecutor(new vf());
        getCommand("vf").setTabCompleter(new vf());

    }

    private void setupFiles() throws IOException {

        getDataFolder().mkdir();

        blockDataFile.createNewFile();

        new File(getDataFolder().getAbsolutePath() + "/blocks").mkdir();

    }

}
