package com.example.viewobjects;

/**
 * Created by tooji on 3/9/2017.
 */
public class ViewPiece {

    private String id;
    private String color;
    private boolean valid;

    ViewPiece(String pId, String pColor){
        id = pId;
        color = pColor;
        valid = true;
    }

    ViewPiece(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setValid(boolean valid){this.valid = valid;}

    public boolean getValid(){return this.valid;}
}
