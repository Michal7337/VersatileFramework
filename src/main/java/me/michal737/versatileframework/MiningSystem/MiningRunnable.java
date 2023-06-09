package me.michal737.versatileframework.MiningSystem;

import me.michal737.versatileframework.BlockDataStorage;
import me.michal737.versatileframework.VersatileFramework;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class MiningRunnable extends BukkitRunnable {

    private BlockDamageEvent event;
    private Block block;
    private Player player;
    private CustomBlock customBlock;
    private int hardness, resistance, blHPLeft;
    private String breakType;
    private int miningSpeed, breakingPower;
    private final Plugin plugin;
    private boolean isCancelled;

    public MiningRunnable(BlockDamageEvent event){

        this.event = event;
        this.block = event.getBlock();
        this.player = event.getPlayer();
        this.plugin = VersatileFramework.getInstance();

        if (BlockDataStorage.readData(block, "VFMiningBlockName") == null) {isCancelled = true; return;}
        this.customBlock = CustomBlock.getCustomBlock(BlockDataStorage.readData(block, "VFMiningBlockName"));

        this.hardness = customBlock.getHardness();
        this.resistance = customBlock.getResistance();
        this.breakType = customBlock.getBreakType();
        this.blHPLeft = hardness;

        if (player.getPersistentDataContainer().has(new NamespacedKey(plugin, "miningspeed"))) {
            this.miningSpeed = player.getPersistentDataContainer().get(new NamespacedKey(plugin, "miningspeed"), PersistentDataType.INTEGER);
        }else miningSpeed = 0;

        if (player.getPersistentDataContainer().has(new NamespacedKey(plugin, "breakingpower"))) {
            this.breakingPower = player.getPersistentDataContainer().get(new NamespacedKey(plugin, "breakingpower"), PersistentDataType.INTEGER);
        }else breakingPower = 0;

    }

    @Override
    public void run() {

        if (isCancelled) {this.cancel(); return;}

        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, -1, -1, false, false));

        if (breakingPower < resistance) {this.cancel(); player.sendRichMessage("cancel"); return;}
        if (miningSpeed >= hardness) {breakBlock(); this.cancel(); return;}

        float destroyStage;
        float remainingProc;

        blHPLeft -= miningSpeed;
        player.sendRichMessage("hpLeft: " + blHPLeft);
        remainingProc = (float) blHPLeft/hardness;
        destroyStage = 1-remainingProc;
        player.sendRichMessage("dstStg: " + (destroyStage));

        player.sendBlockDamage(block.getLocation(), destroyStage);

        if (blHPLeft <= 0) {breakBlock(); this.cancel();}

    }
                                                                        
    private void breakBlock(){

        block.breakNaturally();
        player.removePotionEffect(PotionEffectType.SLOW_DIGGING);

    }

}
