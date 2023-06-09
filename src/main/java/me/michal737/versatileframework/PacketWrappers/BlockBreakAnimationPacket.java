package me.michal737.versatileframework.PacketWrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class BlockBreakAnimationPacket {

    private PacketContainer packet;
    private int entityID;
    private int destroyStage;
    private Block block;

    public BlockBreakAnimationPacket(int entityID, int destroyStage, Block block) {

        this.packet = new PacketContainer(PacketType.Play.Server.BLOCK_BREAK_ANIMATION);
        this.entityID = entityID;
        this.destroyStage = destroyStage;
        this.block = block;
        updatePacket();

    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
        updatePacket();
    }

    public int getDestroyStage() {
        return destroyStage;
    }

    public void setDestroyStage(int destroyStage) {
        this.destroyStage = destroyStage;
        updatePacket();
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
        updatePacket();
    }

    public PacketContainer getPacket() {
        return packet;
    }

    public void send(Player player) {

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    private void updatePacket(){

        packet.getIntegers().write(0, entityID);
        packet.getIntegers().write(1, destroyStage);
        packet.getBlockPositionModifier().write(0, new BlockPosition(block.getX(), block.getY(), block.getZ()));

    }

}
