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
public class Sketch extends PApplet{
    private Person character1;
    private Person character2;
    private boolean showInfo = false;
    private Backgrounds b1;
    
    public void settings() {
        size(1500, 800);
    }

    public void setup() {
        background(100, 100, 100);
        textSize(20);
        character1 = new Person(this, 1050, 600, 0, "images/Xiaojie.png");
        character2 = new Person(this, 410, 520, 4, "images/Lai.png");
        b1 = new Backgrounds(this, "images/background1.png");
    }
    
    public void draw(){
        b1.draw();
        character2.draw();
        if (keyPressed) {
            if (keyCode == LEFT) {
                character1.move(-6, 0);
            } else if (keyCode == RIGHT) {
                character1.move(6, 0);
            } else if (keyCode == UP) {
                character1.move(0, 6);
            } else if (keyCode == DOWN) {
                character1.move(0, 6);
            }
        }
        character1.draw();
        drawCollisions();
    }
    
    public void drawCollisions(){
        if(character2.isCollidingWith(character1)) {
            fill(255,255,255);
            this.text("Press 'E' to interact.", character1.x, character1.y);
        }
    }
}
