package me.michal737.versatileframework.MiningSystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.michal737.versatileframework.VersatileFramework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class CustomBlockObject {

    private int hardness, resistance;
    private String id, breakType;

    public CustomBlockObject(int hardness, int resistance, String id, String breakType) {
        this.hardness = hardness;
        this.resistance = resistance;
        this.id = id;
        this.breakType = breakType;
    }

    public int getHardness() {
        return hardness;
    }

    public int getResistance() {
        return resistance;
    }

    public String getId() {
        return id;
    }

    public String getBreakType() {
        return breakType;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomBlockObject that = (CustomBlockObject) o;
        return hardness == that.hardness && resistance == that.resistance && Objects.equals(id, that.id);

    }

    @Override
    public int hashCode() {
        return Objects.hash(hardness, resistance, id);
    }

    public static void storeBlock(String name, CustomBlockObject blockObject){

        File blockFile = new File(new File(VersatileFramework.getInstance().getDataFolder().getAbsolutePath() + "/blocks") + "/" + name + ".json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {

            FileWriter writer = new FileWriter(blockFile);
            gson.toJson(blockObject, writer);
            writer.close();

        } catch (IOException e) {throw new RuntimeException(e);}

    }

}
