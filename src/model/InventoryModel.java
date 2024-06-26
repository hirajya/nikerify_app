package model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;

public class InventoryModel {

    private final SimpleObjectProperty<ImageView> picture;
    private final SimpleObjectProperty<String> modelID;
    private final SimpleObjectProperty<String> shoeName;

    public InventoryModel(ImageView picture, String modelID, String shoeName) {
        this.picture = new SimpleObjectProperty<>(picture);
        this.modelID = new SimpleObjectProperty<>(modelID);
        this.shoeName = new SimpleObjectProperty<>(shoeName);
    }

    public ImageView getPicture() {
        return picture.get();
    }

    public SimpleObjectProperty<ImageView> pictureProperty() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture.set(picture);
    }

    public String getModelID() {
        return modelID.get();
    }

    public SimpleObjectProperty<String> modelIDProperty() {
        return modelID;
    }

    public void setModelID(String modelID) {
        this.modelID.set(modelID);
    }

    public String getShoeName() {
        return shoeName.get();
    }

    public SimpleObjectProperty<String> shoeNameProperty() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName.set(shoeName);
    }
}
