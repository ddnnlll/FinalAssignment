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
    private Dialogue dialogue;
    private int stage = 0;
    
    public void settings() {
        size(1500, 800);
    }

    public void setup() {
        background(100, 100, 100);
        textSize(20);
        character1 = new Person(this, 1050, 600, 0, "images/Xiaojie.png");
        character2 = new Person(this, 410, 520, 4, "images/Lai.png");
        b1 = new Backgrounds(this, "images/background1.png");
        String[] speakers = {"Xiaojie", "Mama Lai", "Xiaojie"};
        String[] texts = {
            "Stop. Just what are you doing?",
            "I'm just taking what wasn't eaten home with me to give to my aunt. Your father lets me do so.",
            "Oh. Very well, then."
        };
        dialogue = new Dialogue(this, speakers, texts);
    }
    
    public void draw(){
        if (stage == 0) {
            background(30);
            fill(255);
            textSize(50);
            this.text("In a Bowl of Rice", width/2 - 150, height/2 - 20);
            textSize(20);
            this.text("Press ENTER to begin", width/2 - 80, height/2 + 30);
        } else if (stage == 1) {
            b1.draw();
            character2.draw();
            if (keyPressed) {
                if (keyCode == LEFT) {
                    character1.move(-6, 0);
                } else if (keyCode == RIGHT) {
                    character1.move(6, 0);
                } else if (keyCode == UP) {
                    character1.move(0, -6);
                } else if (keyCode == DOWN) {
                    character1.move(0, 6);
                }
            }
            character1.draw();
            drawCollisions();
            
            if (dialogue != null && !dialogue.isActive() && dialogue.isLineFullyShown()){
                delay(2000);
                stage = 2;
            }

        } else if (stage == 2) {
            background(30);
            fill(255);
            textSize(50);
            this.text("The Next Night...", width/2 - 150, height/2 - 20);
            delay(5000);
            stage = 3;
        } else if (stage == 3) {
            
        }
    }
    
    public void drawCollisions(){
        if(character2.isCollidingWith(character1)) {
            if (!dialogue.isActive()){
            fill(255,255,255);
            this.text("Press 'E' to interact.", character1.x, character1.y);
            }
      
        }
        if (dialogue.isActive()){
            dialogue.update();
        }
    }
    
    public void keyPressed(){
        if (stage == 0) {
            if (key == ENTER) {
                stage = 1;
            }
        } else if (stage == 1){
            if (dialogue != null && character1.isCollidingWith(character2)){
                if(!dialogue.isActive() && key == 'e'){
                    dialogue.start();
                } else if (dialogue.isActive() && key == 'e'){
                    dialogue.nextLine();
                } 
            }
        }
    }
}
    
        
    