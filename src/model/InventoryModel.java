package model;

import javafx.scene.image.ImageView;

public class InventoryModel {
    private ImageView imageView; // Changed from 'picture' to 'imageView'
    private String modelID;
    private String shoeName;

    public InventoryModel(ImageView imageView, String modelID, String shoeName) {
        this.imageView = imageView;
        this.modelID = modelID;
        this.shoeName = shoeName;
    }

    // Getters and setters
    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getModelID() {
        return modelID;
    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }
}
