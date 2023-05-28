package me.michal737.versatileframework.commands;

import me.michal737.versatileframework.VersatileFramework;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
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

        ItemStack[] items = new ItemStack[54];

        menuGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta mgMeta = menuGlass.getItemMeta();
        mgMeta.displayName(Component.text(""));
        menuGlass.setItemMeta(mgMeta);
        Arrays.fill(items, menuGlass);

        ItemStack helpIcon = new ItemStack(Material.BOOK);
        ItemMeta helpIconMeta = helpIcon.getItemMeta();
        helpIconMeta.displayName(Component.text("Help", NamedTextColor.AQUA));
        helpIcon.setItemMeta(helpIconMeta);

        ItemStack settingsIcon = new ItemStack(Material.PAPER);
        ItemMeta settingsIconMeta = settingsIcon.getItemMeta();
        settingsIconMeta.displayName(Component.text("Settings", NamedTextColor.GOLD));
        settingsIcon.setItemMeta(settingsIconMeta);

        ItemStack miningSystemIcon = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta miningSystemIconMeta = miningSystemIcon.getItemMeta();
        miningSystemIconMeta.displayName(Component.text("Custom mining system", NamedTextColor.GREEN));
        miningSystemIcon.setItemMeta(miningSystemIconMeta);

        ItemStack customItemsIcon = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta customItemsIconMeta = customItemsIcon.getItemMeta();
        customItemsIconMeta.displayName(Component.text("Custom items", NamedTextColor.YELLOW));
        customItemsIcon.setItemMeta(customItemsIconMeta);

        ItemStack blockFeaturesIcon = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta blockFeaturesIconMeta = blockFeaturesIcon.getItemMeta();
        blockFeaturesIconMeta.displayName(Component.text("Block features", NamedTextColor.BLUE));
        blockFeaturesIcon.setItemMeta(blockFeaturesIconMeta);

        ItemStack otherIcon = new ItemStack(Material.TNT);
        ItemMeta otherIconMeta = otherIcon.getItemMeta();
        otherIconMeta.displayName(Component.text("Other", NamedTextColor.RED));
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
