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
public class Backgrounds {
    private PApplet app;
    private PImage image;
    private int width, height;
    
    public Backgrounds(PApplet p, String imagePath){
        this.app = p;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    public void draw(){
        app.image(image, 0, 0, app.width, app.height);
    }
}
