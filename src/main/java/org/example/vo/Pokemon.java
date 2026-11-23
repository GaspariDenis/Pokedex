package org.example.vo;

import jdk.jfr.Name;

import java.util.List;

public class Pokemon {
    public String id;
    public String name;

    public List<SlotType> types;

    public Integer hp;

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", hp: " + hp + "\ntypes: " + types.toString();
    }
}
