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
public class Lai {
    int x;
    int y;
    private int speed;
    private PApplet app;
    private PImage image;
    private int width, height;
    
    public Lai(PApplet p, int x, int y, int speed, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }

    public void draw() {
        app.image(image, x, y);

    }
}
