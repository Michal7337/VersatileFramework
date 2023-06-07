package me.michal737.versatileframework;

import com.google.gson.Gson;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Objects;

public class SimpleLocation {

    private final int x, y, z;
    private final String world;

    public SimpleLocation(int x, int y, int z, String world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getWorld() {
        return world;
    }

    public World getRealWorld(){
        return VersatileFramework.getInstance().getServer().getWorld(world);
    }

    public Block getBlock(){
        return new Location(VersatileFramework.getInstance().getServer().getWorld(world), x, y, z).getBlock();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleLocation that = (SimpleLocation) o;
        return x == that.x && y == that.y && z == that.z && Objects.equals(world, that.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, world);
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }

    public static SimpleLocation fromJson(String json){
        return new Gson().fromJson(json, SimpleLocation.class);
    }

    public static SimpleLocation fromBlock(Block block){
        return new SimpleLocation(block.getX(), block.getY(), block.getZ(), block.getWorld().getName());
    }

}
