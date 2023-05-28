package me.michal737.versatileframework;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bukkit.block.Block;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class BlockDataStorage {

    private BlockDataStorage(){}

    private static HashMap<String, HashMap<String, String>> blocks;

    public static void storeData(Block block, String namespace, String data){

        readBlocks();

        String location = SimpleLocation.fromBlock(block).toString();

        if (blocks == null) {

            blocks = new HashMap<>();
            HashMap<String, String> newData = new HashMap<>();
            newData.put(namespace, data);
            blocks.put(location, newData);

        }

        if (!blocks.containsKey(location)){

            HashMap<String, String> newData = new HashMap<>();
            newData.put(namespace, data);
            blocks.put(location, newData);
            saveBlocks();
            return;

        }

        HashMap<String, String> storedData = blocks.get(location);
        storedData.put(namespace, data);
        blocks.put(location, storedData);

        saveBlocks();

    }

    public static String readData(Block block, String namespace){

        readBlocks();

        if (blocks == null) return null;

        HashMap<String, String> storedData = blocks.get(SimpleLocation.fromBlock(block).toString());

        if (storedData == null) return null;

        return storedData.get(namespace);

    }

    private static void readBlocks(){

        try {

            Type type = new TypeToken<HashMap<String, HashMap<String, String>>>(){}.getType();
            FileReader reader = new FileReader(VersatileFramework.getBlockDataFile());
            blocks = new Gson().fromJson(reader, type);
            reader.close();

        } catch (IOException e) {throw new RuntimeException(e);}

    }

    private static void saveBlocks(){

        try (FileWriter writer = new FileWriter(VersatileFramework.getBlockDataFile())) {

            new GsonBuilder().setPrettyPrinting().create().toJson(blocks, writer);

        }catch (IOException exception) {throw new RuntimeException(exception);}

    }

}
