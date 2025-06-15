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
    
    public ChickenDropping(PApplet p, int x, int y, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.image = app.loadImage(imagePath);
    }
    
    public ChickenDropping(PApplet p, int x, int y){
        this.app = p;
        this.x = x;
        this.y = y;
        this.image = defaultImg;
    }
    
    public static void setDefaultImage(PImage img){
        defaultImg = img;
    }
    public void draw(){
        if (!collected) {
            app.image(image, x, y);
        }
    }
    
    public void collect(){
        collected = true;
    }
    
    public boolean isClicked(int mouseX, int mouseY){
        int centerX = x +(image.pixelWidth/2);
        int centerY = y +(image.pixelHeight/2);
        
        float d = PApplet.dist(mouseX, mouseY, centerX, centerY);
        
        return !collected && d<16;
    }
    
    public boolean isCollected(){
        return collected;
    }
}
