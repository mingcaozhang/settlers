package com.example.viewobjects;

/**
 * Created by G on 17/03/11.
 */
public class ViewHex {

    private double x;
    private double y;
    private String stroke;
    private String stroke_width;
    private String fill;
    private String points;
    private String id;
    private String terrain_type;
    private int number;

    public ViewHex(double x, double y, String stroke, String stroke_width, String fill, String points, String id, String terrainType, int number) {
        this.x = x;
        this.y = y;
        this.stroke = stroke;
        this.stroke_width = stroke_width;
        this.fill = fill;
        this.points = points;
        this.id = id;
        this.terrain_type = terrainType;
        this.number = number;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public String getStroke_width() {
        return stroke_width;
    }

    public void setStroke_width(String stroke_width) {
        this.stroke_width = stroke_width;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerrainType() {
        return terrain_type;
    }

    public void setTerrainType(String terrainType) {
        this.terrain_type = terrainType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
