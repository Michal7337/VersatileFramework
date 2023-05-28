package me.michal737.versatileframework.commands;

import me.michal737.versatileframework.VersatileFramework;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MainVfGUI implements InventoryHolder {

    private final Inventory inventory;
    private ItemStack menuGlass;

    public MainVfGUI(){

        this.inventory = VersatileFramework.getInstance().getServer().createInventory(this, 54);
        createGUI();

    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    private void createGUI(){

        MiniMessage miniMessage = MiniMessage.miniMessage();

        ItemStack[] items = new ItemStack[54];

        menuGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta mgMeta = menuGlass.getItemMeta();
        mgMeta.displayName(Component.text(""));
        menuGlass.setItemMeta(mgMeta);
        Arrays.fill(items, menuGlass);

        ItemStack helpIcon = new ItemStack(Material.BOOK);
        ItemMeta helpIconMeta = helpIcon.getItemMeta();
        helpIconMeta.displayName(miniMessage.deserialize("<aqua>Help</aqua>"));
        helpIcon.setItemMeta(helpIconMeta);

        ItemStack settingsIcon = new ItemStack(Material.PAPER);
        ItemMeta settingsIconMeta = settingsIcon.getItemMeta();
        settingsIconMeta.displayName(miniMessage.deserialize("<gold>Settings</gold>"));
        settingsIcon.setItemMeta(settingsIconMeta);

        ItemStack miningSystemIcon = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta miningSystemIconMeta = miningSystemIcon.getItemMeta();
        miningSystemIconMeta.displayName(miniMessage.deserialize("<green>Custom mining system</green>"));
        miningSystemIcon.setItemMeta(miningSystemIconMeta);

        ItemStack customItemsIcon = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta customItemsIconMeta = customItemsIcon.getItemMeta();
        customItemsIconMeta.displayName(miniMessage.deserialize("<yellow>Custom items<yellow>"));
        customItemsIcon.setItemMeta(customItemsIconMeta);

        ItemStack blockFeaturesIcon = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta blockFeaturesIconMeta = blockFeaturesIcon.getItemMeta();
        blockFeaturesIconMeta.displayName(miniMessage.deserialize("<blue>Block features</blue>"));
        blockFeaturesIcon.setItemMeta(blockFeaturesIconMeta);

        ItemStack otherIcon = new ItemStack(Material.TNT);
        ItemMeta otherIconMeta = otherIcon.getItemMeta();
        otherIconMeta.displayName(miniMessage.deserialize("<red>Other</red>"));
        otherIcon.setItemMeta(otherIconMeta);

        items[10] = helpIcon;
        items[13] = settingsIcon;
        items[16] = miningSystemIcon;
        items[37] = customItemsIcon;
        items[40] = blockFeaturesIcon;
        items[43] = otherIcon;

        this.inventory.setContents(items);

    }

}
