/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sketching;

import processing.core.PApplet;

/**
 *
 * @author dnll5
 */
public class Monkey extends Person {
    /**
     * Constructor for Monkey
     * @param app PApplet sketch
     * @param x x-coordinate
     * @param y y-coordinate
     * @param dir speed(not used)
     * @param imgPath File path for image
     */
    public Monkey (PApplet app, int x, int y, int dir, String imgPath) {
        super(app,x,y,dir,imgPath);
    }
    
    /**
     * Draws monkey on screen
     * Overrides draw method from Person class
     */
    @Override
    public void draw(){
        if(image != null){
            app.image(image, x ,y);
        }
    }
}
