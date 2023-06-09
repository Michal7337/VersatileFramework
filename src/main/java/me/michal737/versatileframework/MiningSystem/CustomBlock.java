package me.michal737.versatileframework.MiningSystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.michal737.versatileframework.VersatileFramework;
import org.apache.commons.io.FilenameUtils;
import org.bukkit.Material;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class CustomBlock {

    private int hardness, resistance;
    private String name, breakType, material;

    public CustomBlock(String id, int hardness, int resistance, String breakType) {
        this.hardness = hardness;
        this.resistance = resistance;
        this.name = id;
        this.breakType = breakType;
    }

    public int getHardness() {
        return hardness;
    }

    public int getResistance() {
        return resistance;
    }

    public String getName() {
        return name;
    }

    public String getBreakType() {
        return breakType;
    }

    public String getMaterialName(){
        return material;
    }

    public Material getMaterial(){
        return Material.getMaterial(material);
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomBlock that = (CustomBlock) o;
        return hardness == that.hardness && resistance == that.resistance && Objects.equals(name, that.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(hardness, resistance, name);
    }

    public static void storeBlock(String name, CustomBlock blockObject){

        File blockFile = getBlockFile(name);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {

            blockFile.createNewFile();
            FileWriter writer = new FileWriter(blockFile);
            gson.toJson(blockObject, writer);
            writer.close();

        } catch (IOException e) {throw new RuntimeException(e);}

    }

    public static File getBlockFile(String name){
        return new File(VersatileFramework.getInstance().getDataFolder().getAbsolutePath() + "/blocks/" + name + ".json");
    }

    public static File getBlocksFolder(){
        return new File(VersatileFramework.getInstance().getDataFolder().getAbsolutePath() + "/blocks/");
    }

    public static CustomBlock getCustomBlock(String name){
        try {
            return new Gson().fromJson(new FileReader(getBlockFile(name)), CustomBlock.class);
        } catch (FileNotFoundException e) {throw new RuntimeException(e);}
    }

    public static ArrayList<String> getAllCustomBlocks(){

        String[] files = CustomBlock.getBlocksFolder().list();
        if (files == null) files = new String[]{};
        ArrayList<String> names = new ArrayList<>();
        for (String filename : files) {names.add(FilenameUtils.removeExtension(filename));}
        return names;

    }

}
