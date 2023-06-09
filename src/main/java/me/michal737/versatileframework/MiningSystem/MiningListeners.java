package me.michal737.versatileframework.MiningSystem;

import me.michal737.versatileframework.VersatileFramework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageAbortEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class MiningListeners implements Listener {

    private BukkitTask miningRunnable;

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event){

        event.getPlayer().sendRichMessage("damage");
        miningRunnable = new MiningRunnable(event).runTaskTimer(VersatileFramework.getInstance(), 0, 1);

    }

    @EventHandler
    public void onBlockDamageAbort(BlockDamageAbortEvent event){

        event.getPlayer().sendRichMessage("abort");
        miningRunnable.cancel();
        event.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);

    }

}
