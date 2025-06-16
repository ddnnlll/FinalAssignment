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
public class ChickenDropping {
    private PApplet app;
    private int x,y;
    private boolean collected = false;
    private PImage image;
    private static PImage defaultImg;
    
    // Constructor 1
    public ChickenDropping(PApplet p, int x, int y, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.image = app.loadImage(imagePath);
    }
    
    // Constructor 2
    public ChickenDropping(PApplet p, int x, int y){
        this.app = p;
        this.x = x;
        this.y = y;
        this.image = defaultImg;
    }
    
    /**
     * Set default image
    */
    public static void setDefaultImage(PImage img){
        defaultImg = img;
    }
    
    /**
     * Draws the poop on screen if not collected
     */
    public void draw(){
        if (!collected) {
            app.image(image, x, y);
        }
    }
    
    /**
     * Marks poop as collected
     */
    public void collect(){
        collected = true;
    }
    
    /**
     * Check if user clicked on the poop
     * @param mouseX x position of mouse click
     * @param mouseY y position o mouse click
     * @return True if poop was clicked
     */
    public boolean isClicked(int mouseX, int mouseY){
        int centerX = x +(image.pixelWidth/2);
        int centerY = y +(image.pixelHeight/2);
        
        float d = PApplet.dist(mouseX, mouseY, centerX, centerY);
        
        return !collected && d<16;
    }
    
    /**
     * Check if dropping has been collected already
     * @return True if collected
     */
    public boolean isCollected(){
        return collected;
    }
}
