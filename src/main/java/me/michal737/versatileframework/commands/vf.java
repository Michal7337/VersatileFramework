package me.michal737.versatileframework.commands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.*;
import me.michal737.versatileframework.BlockDataStorage;
import me.michal737.versatileframework.MiningSystem.CustomBlock;
import me.michal737.versatileframework.SimpleLocation;
import me.michal737.versatileframework.VersatileFramework;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
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
                    ArrayList<String> arguments = new ArrayList<>(Arrays.asList("createblock", "deleteblock", "setblock"));
                    StringUtil.copyPartialMatches(args[1], arguments, completions);
                    return completions;

                }

                if (args.length == 3) {

                    if (args[1].equals("deleteblock")) {

                        return filterCompletions(CustomBlock.getAllCustomBlocks(), args[2]);

                    }else if (args[1].equals("setblock")){

                        return filterCompletions(CustomBlock.getAllCustomBlocks(), args[2]);

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

            CustomBlock blockObject = new CustomBlock(name, Integer.parseInt(hardness), Integer.parseInt(resistance), breakType);
            CustomBlock.storeBlock(name, blockObject);

            sender.sendRichMessage("<green>Block successfully created!");

        }else if (args[1].equals("deleteblock")){

            if (args.length < 3) return;
            String name = args[2];
            File file = CustomBlock.getBlockFile(name);
            if (!file.exists()) {sender.sendRichMessage("<red>That block doesn't exist!"); return;}
            file.delete();
            sender.sendRichMessage("<green>Block successfully deleted!");

        }else if (args[1].equals("setblock")){

            // /vf mining setblock <block> x y z (world)

            SimpleLocation location;

            if (sender instanceof Player player){
                if (args.length < 6) {sender.sendRichMessage("<red>Specify more arguments!"); return;}
                location = new SimpleLocation(Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), player.getWorld().getName());
            }else {
                if (args.length < 7) {sender.sendRichMessage("<red>Specify more arguments!"); return;}
                location = new SimpleLocation(Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), args[6]);
            }

            if (!CustomBlock.getAllCustomBlocks().contains(args[2])) {sender.sendRichMessage("<red>That block doesn't exist!"); return;}

            BlockDataStorage.storeData(location.getBlock(), "VFMiningBlockName", args[2]);
            sender.sendRichMessage("<green>Block successfully set!");

        }else {sender.sendRichMessage("<red>Not a valid argument!");}


    }

    private void block(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

    }

    private void help(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

    }

    private void test(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

        sender.sendRichMessage("test");

        Player player = (Player) sender;

        if (args[1].equals("speed")) {
            player.getPersistentDataContainer().set(new NamespacedKey(VersatileFramework.getInstance(), "miningspeed"), PersistentDataType.INTEGER, Integer.parseInt(args[2]));
        }else if (args[1].equals("power")) player.getPersistentDataContainer().set(new NamespacedKey(VersatileFramework.getInstance(), "breakingpower"), PersistentDataType.INTEGER, Integer.parseInt(args[2]));

    }

    private ArrayList<String> filterCompletions(ArrayList<String> originals, String token){

        ArrayList<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(token, originals, completions);
        return completions;

    }

}
