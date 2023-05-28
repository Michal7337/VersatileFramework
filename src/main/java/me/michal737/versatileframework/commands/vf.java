package me.michal737.versatileframework.commands;

import me.michal737.versatileframework.BlockDataStorage;
import me.michal737.versatileframework.SimpleLocation;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class vf implements TabExecutor {

    private final ArrayList<String> mainCommandArgs = new ArrayList<>(Arrays.asList("mining", "block", "help", "test"));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        switch (args[0]) {
            case "mining" -> mining(sender, command, label, args);
            case "block" -> block(sender, command, label, args);
            case "help" -> help(sender, command, label, args);
            case "test" -> test(sender, command, label, args);
            default -> {
                sender.sendRichMessage("<red>Not a valid argument!</red>");
            }
        }

        return true;

    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1){
            ArrayList<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], mainCommandArgs, completions);
            return completions;
        }

        return null;

    }

    private void mining(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

    }

    private void block(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

    }

    private void help(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

    }

    private void test(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

        SimpleLocation location = new SimpleLocation(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), args[4]);

        //BlockDataStorage.storeData(location.getBlock(), args[5], args[6]);

        String value = BlockDataStorage.readData(location.getBlock(), args[5]);

        if (value == null) {sender.sendPlainMessage("null"); return;}
        sender.sendPlainMessage(value);

    }

}
