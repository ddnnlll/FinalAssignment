/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sketching;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author dnll5
 */
public class Person {
    int x;
    int y;
    private int speed;
    protected PApplet app;
    protected PImage image;
    private int width, height;
    
    /**
     * Constructor for person class
     * @param p PApplet object
     * @param x x-coordinate
     * @param y y-coordinate
     * @param speed movement speed
     * @param imagePath file path for image
     */
    public Person(PApplet p, int x, int y, int speed, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    /**
     * Moves person with x and y
     * @param dx change in x
     * @param dy change in y
     */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    
    /**
     * Draws person image
     */
    public void draw() {
        app.image(image, x, y);
    }
    
    /**
     * Checks if person is colliding with another person
     * @param other The other person checking collision
     * @return True if boxes overlap, false otherwise
     */
    public boolean isCollidingWith(Person other){
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + width > other.x;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;
        
        return isLeftOfOtherRight && isRightOfOtherLeft
                && isAboveOtherBottom && isBelowOtherTop;
    }
}
