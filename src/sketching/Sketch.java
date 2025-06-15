/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sketching;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

/**
 *
 * @author dnll5
 */
public class Sketch extends PApplet{
    private Person character1;
    private Person character2;
    private Person rice;
    private boolean leaving = false;
    private boolean showInfo = false;
    private int doorX = 300;
    private int doorY = 500;
    private Backgrounds b1;
    private Dialogue dialogue;
    private Dialogue dialogue2;
    private int stage = 0;
    
    private boolean minigameStart = false;
    private ArrayList<ChickenDropping> droppings = new ArrayList<>();
    private int collectedCount = 0;
    private PImage poopImage;
    private int timer;
    
    public void settings() {
        size(1500, 800);
    }

    public void setup() {
        background(100, 100, 100);
        textSize(20);
        rice = new Person(this, 570, 570, 0, "images/rice.png");
        character1 = new Person(this, 1050, 600, 0, "images/Xiaojie.png");
        character2 = new Person(this, 410, 520, 4, "images/Lai.png");
        poopImage = loadImage("images/poop.png");
        ChickenDropping.setDefaultImage(poopImage);
        b1 = new Backgrounds(this, "images/background1.png");
        String[] speakers = {"Xiaojie", "Mama Lai", "Xiaojie"};
        String[] texts = {
            "Stop. Just what are you doing?",
            "I'm just taking what wasn't eaten home with me to give to my aunt. Your father lets me do so.",
            "Oh. Very well, then.",  
        };
        
        dialogue = new Dialogue(this, speakers, texts);
        
        String[] speakers2 = {"Xiaojie", "Mama Lai"};
        String[] texts2 = {
            "Go out to the storehouse. Make sure that you covered the jars of sugar. I believe you were the last one out there.",
            "Yes, of course, Xiaojie.",
            
        };
        dialogue2 = new Dialogue(this, speakers2, texts2);
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
            rice.draw();
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
            textSize(20);
            this.text("Press ENTER to go to next scene", width / 2 - 110, height / 2 + 30);
            character1.x = 900;
            character1.y = 600;
            
            if (key == ENTER){
                stage = 3;
            }
            // stage = 3;
        } else if (stage == 3) {
            textSize(20);
            b1.draw();
            rice.draw();
            if (character2 != null){
                if(leaving){
                    if (character2.x > doorX){
                        character2.move(-2, 0);
                    } else if (character2.y > doorY){
                        character2.move(0,-2);
                    } else if (character2.x <= doorX && character2.y <= doorY){
                        character2 = null;
                        
                        minigameStart = true;
                        droppings.add(new ChickenDropping(this, 400, 400, "images/poop.png"));
                        droppings.add(new ChickenDropping(this, 800, 300));
                        droppings.add(new ChickenDropping(this, 100, 600));
                    }
                }
                if (character2 != null){
                    character2.draw();
                }
            }
            
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
            
            if (minigameStart) {
                for (ChickenDropping d : droppings) {
                    d.draw();
                }
                fill(255);
                this.text("Droppings collected: " + collectedCount + "/3", 50, 100);
                if (collectedCount >= 3) {
                    fill(0);
                    rect(width/2 - 120, height - 110, 350, 60);
                    fill(255);
                    // Fix this, looks weird
                    this.text("You found all the droppings! ", width/2 - 100, height - 80);
                    this.text("*Xiaojie hides them in the rice*", width/2 - 110, height - 50);
                }
                if (collectedCount == 3){
                    timer++;
                    if (timer > 60){
                        stage = 4;
                    }
                }
            }
        } else if (stage == 4){
            background(30);
            fill(255);
            textSize(40);
            this.text("Mama Lai took the rice and returned home to her family", width/2 - 500, height / 2 - 20);
            textSize(20);
            this.text("Press enter to continue", width/2 - 110, height / 2 + 30);
        }
    }
    
    
    public void drawCollisions(){
        if (stage == 1){
            if (character2.isCollidingWith(character1)) {
                if (!dialogue.isActive()){
                    fill(255,255,255);
                    this.text("Press 'E' to interact.", character1.x, character1.y);
                }
            }
            if (dialogue.isActive()){
                dialogue.update();
            }
        } else if (stage == 3) {
            if (character2 == null)
                return;
            if (character2.isCollidingWith(character1)) {
                if (!dialogue.isActive()){
                    fill(255,255,255);
                    this.text("Press 'E' to interact.", character1.x, character1.y);
                }
            }
            
            if(dialogue2.isActive()) {
                dialogue2.update();
            }
            if (!dialogue2.isActive() && dialogue2.isLineFullyShown() && character2 != null){
                leaving = true;
            }
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
        } else if (stage == 3) {
            if (dialogue2 != null && character1.isCollidingWith(character2)) {
                if (!dialogue2.isActive() && key == 'e') {
                    dialogue2.start();
                } else if (dialogue2.isActive() && key == 'e') {
                    dialogue2.nextLine();
                }
            }
  
        }
    }
    
    public void mousePressed(){
        if (minigameStart){
            for (ChickenDropping d : droppings){
                if (d.isClicked(mouseX, mouseY) && !d.isCollected()){
                    d.collect();
                    collectedCount++;
                }
            }
        }
    }
}
    
        
    