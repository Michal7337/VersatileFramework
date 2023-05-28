package me.michal737.versatileframework.commands;

import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class PaperCommand extends Command {

    private final Plugin plugin;

    public PaperCommand(@NotNull String name, @NotNull Plugin plugin) {
        super(name);
        this.plugin = plugin;
    }

    protected PaperCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases, @NotNull Plugin plugin) {
        super(name, description, usageMessage, aliases);
        this.plugin = plugin;
    }

    public boolean register() {
        boolean isSuccessful = plugin.getServer().getCommandMap().register(getName(), plugin.getName(), this);
        if (isSuccessful) {
            plugin.getServer().getOnlinePlayers().forEach(Player::updateCommands);
        }
        return isSuccessful;
    }

    public boolean unregister() {
        SimpleCommandMap simpleCommandMap = (SimpleCommandMap) plugin.getServer().getCommandMap();
        Map<String, Command> knownCommands = simpleCommandMap.getKnownCommands();
        ArrayList<String> keysForRemoval = new ArrayList<>();
        knownCommands.forEach((key, value) -> {
            if (value == this) {
                keysForRemoval.add(key);
            }
        });
        return knownCommands.keySet().removeAll(keysForRemoval);
    }
}
