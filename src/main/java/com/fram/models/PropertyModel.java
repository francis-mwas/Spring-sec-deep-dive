package com.fram.models;

public class PropertyModel {
    private int propertyId;
    private String propertyName;
    private int size;

    public PropertyModel(int propertyId, String propertyName, int size) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.size = size;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PropertyController{" +
                "propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", size=" + size +
                '}';
    }
}
