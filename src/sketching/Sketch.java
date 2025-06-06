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
    private Xiaojie character1;
    private Lai character2;
    private Backgrounds b1;
    
    public void settings() {
        size(1500, 800);
    }

    public void setup() {
        background(100, 100, 100);
        textSize(20);
        character1 = new Xiaojie(this, 200, 200, 0, "images/Xiaojie.png");
        character2 = new Lai(this, 100, 100, 2, "images/Lai.png");
        b1 = new Backgrounds(this, "images/background1.png");
    }
    
    public void draw(){
        background(255);
        character2.draw();
        if (keyPressed) {
            if (keyCode == LEFT) {
                character1.move(-2, 0);
            } else if (keyCode == RIGHT) {
                character1.move(2, 0);
            } else if (keyCode == UP) {
                character1.move(0, -2);
            } else if (keyCode == DOWN) {
                character1.move(0, 2);
            }
        }
        character1.draw();
    }
}
