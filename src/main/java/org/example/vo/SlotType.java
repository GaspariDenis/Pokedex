package org.example.vo;

public class SlotType {
    public String slot;
    public TipoAPI type;

    @Override
    public String toString() {
        return type.toString();
    }
}
