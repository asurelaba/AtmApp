package com.solvd.db.model;

public class CardType {

    private int typeId;
    private String name;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CardType{" +
                "typeId=" + typeId +
                ", name='" + name + '\'' +
                '}';
    }
}
