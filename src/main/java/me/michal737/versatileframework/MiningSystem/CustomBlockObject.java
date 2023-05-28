package me.michal737.versatileframework.MiningSystem;

import com.google.gson.Gson;

import java.util.Objects;

public class CustomBlockObject {

    private int hardness, resistance;
    private String id;

    public CustomBlockObject(int hardness, int resistance, String id) {
        this.hardness = hardness;
        this.resistance = resistance;
        this.id = id;
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

}
