package com.solvd.db.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardType cardType = (CardType) o;
        return typeId == cardType.typeId && Objects.equals(name, cardType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, name);
    }

}
