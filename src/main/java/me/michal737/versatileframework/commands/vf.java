package me.michal737.versatileframework.commands;

import me.michal737.versatileframework.MiningSystem.CustomBlockObject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class vf implements TabExecutor {

    private final ArrayList<String> mainCommandArgs = new ArrayList<>(Arrays.asList("mining", "block", "help", "test"));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 0) {
            if (sender instanceof Player) {
                ((Player) sender).openInventory(new MainVfGUI().getInventory());
            }else sender.sendRichMessage("<red>Specify an argument!</red>");
            return true;
        }

        switch (args[0]) {
            case "mining" -> mining(sender, command, label, args);
            case "block" -> block(sender, command, label, args);
            case "help" -> help(sender, command, label, args);
            case "test" -> test(sender, command, label, args);
            default -> sender.sendRichMessage("<red>Not a valid argument!</red>");
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

        if (args.length > 1){

            if (args[0].equals("mining")){

                if (args.length == 2) {

                    ArrayList<String> completions = new ArrayList<>();
                    ArrayList<String> arguments = new ArrayList<>(Arrays.asList("createblock", "deleteblock"));
                    StringUtil.copyPartialMatches(args[1], arguments, completions);
                    return completions;

                }

                if (args.length == 3) {

                    if (args[1].equals("deleteblock")) {

                        String[] files = CustomBlockObject.getBlocksFolder().list();
                        if (files == null) files = new String[]{};
                        ArrayList<String> completions = new ArrayList<>();
                        ArrayList<String> arguments = new ArrayList<>();
                        for (String filename : files) {arguments.add(FilenameUtils.removeExtension(filename));}
                        StringUtil.copyPartialMatches(args[2], arguments, completions);
                        return completions;

                    }

                }

            }

        }

        return null;

    }

    private void mining(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

        // /vf mining createblock <name> <hardness> <resistance> <breaktype>

        if (args.length < 2) {sender.sendRichMessage("<red>Provide an argument!"); return;}

        if (args[1].equals("createblock")) {

            if (args.length < 6) {sender.sendRichMessage("<red>Specify more arguments!"); return;}

            String name = args[2];
            String hardness = args[3];
            String resistance = args[4];
            String breakType = args[5];

            CustomBlockObject blockObject = new CustomBlockObject(name, Integer.parseInt(hardness), Integer.parseInt(resistance), breakType);
            CustomBlockObject.storeBlock(name, blockObject);

        }else if (args[1].equals("deleteblock")){

            if (args.length < 3) return;
            String name = args[2];
            File file = CustomBlockObject.getBlockFile(name);
            if (!file.exists()) {sender.sendRichMessage("<red>That block doesn't exist!"); return;}
            file.delete();

        }else {sender.sendRichMessage("<red>Not a valid argument!");}

    }

    private void block(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

    }

    private void help(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

    }

    private void test(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

    }

}
