/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sketching;

import java.io.FileWriter;
import java.io.IOException;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

/**
 *
 * @author dnll5
 */
public class Sketch extends PApplet{
    private Person character1, character2, auntie, rice;
    private Person priest;
    private boolean leaving = false;
    private boolean showInfo = false;
    private boolean transformMonkey = false;
    private int doorX = 300;
    private int doorY = 500;
    private Monkey monkey;
    private Monkey transform = null;
    private Backgrounds b1, b2, b3;
    private Dialogue dialogue, dialogue2, dialogue3a, dialogue3b, dialogue3c, dialogue4;
    private int stage = 0;
    
    // Game variables
    private boolean minigameStart = false;
    private final ArrayList<ChickenDropping> droppings = new ArrayList<>();
    private int collectedCount = 0;
    private PImage poopImage;
    private int timer;
    
    // Dialogue
    private boolean dialogue3aStart = false;
    private boolean dialogue3bStart = false;
    private boolean dialogue3cStart = false;
    private boolean dialogue4Start = false;
    private boolean dialogue4Done = false;
    private boolean laiWalkingToDoor = false;
    private boolean laiFinished = false;
    
    public void settings() {
        size(1500, 800); // Sets screen size
    }

    public void setup() {
        background(100, 100, 100); // Sets basic background
        textSize(20); // Sets text for dialogue
        
        // All images and characters
        rice = new Person(this, 570, 570, 0, "images/rice.png");
        character1 = new Person(this, 1050, 600, 0, "images/Xiaojie.png");
        character2 = new Person(this, 410, 520, 4, "images/Lai.png");
        auntie = new Person(this, 410, 520, 0, "images/Auntie.png");
        priest = new Person(this, 600, 400, 0, "images/priest.png");
        poopImage = loadImage("images/poop.png");
        ChickenDropping.setDefaultImage(poopImage);
        b1 = new Backgrounds(this, "images/background1.png");
        b2 = new Backgrounds(this, "images/background2.png");
        b3 = new Backgrounds(this, "images/background3.png");
        
        // All texts
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
        
        String [] speakers3a = {"Mama Lai", "", "Auntie Lai", "Mama Lai", "Auntie Lai",""};
        String [] texts3a = {
            "Here, Auntie, have your dinner.",
            "*She violently spat out the food*",
            "What are you trying to do? Are you trying to poison me after all the years I took care of you, now that I finally need you?",
            "I don't . . . I don't understand, Auntie . . . ",
            "You are feeding me chicken dirt!",
            "*Mama Lai checks and found it had chicken droppings. She quickly runs to the river to wash it*",
        };
        
        String [] speakers3b = {"Taoist Priest", "Taoist Priest","", "Mama Lai"};
        String [] texts3b = {
            "Hey! What are you doing?",
            "Don't rinse it. Allow me, please.",
            "*He does something to the rice, making it full of gleaming pearls*",
            "I'm rich!",
        };
        
        dialogue3a = new Dialogue(this, speakers3a, texts3a);
        dialogue3b = new Dialogue(this, speakers3b, texts3b);
        
        String [] speakers4 = {"Taoist Priest", "Xiaojie", "Taoist Priest", "", "Xiaojie", "", "Xiaojie", ""};
        String [] texts4 = {
            "Hold it! What are you doing?",
            "I'm rinsing my rice. My poor old mother was eating chicken droppings.",
            "Say no more. Allow me.",
            "*Does magic again*",
            "Huh. Whys mine not turning into pearls? This rice smells very good though.",
            "*Xiaojie eats the rice*",
            "This tastes good! Wait whats happening to me?",
            "*Xiaojie turns into a smelly, hairy monkey!*"
        };
        
        dialogue4 = new Dialogue(this, speakers4, texts4);
    }
    
    public void draw(){
        if (stage == 0) {
            background(30);
            fill(255);
            textSize(50);
            this.text("In a Bowl of Rice", width/2 - 150, height/2 - 20);
            textSize(20);
            this.text("Press ENTER to begin", width/2 - 80, height/2 + 30);
            
            this.text("Move with ARROW KEYS", width/2 - 80, height/2 + 60);
            this.text("Press 'E' to interact when near characters", width/2 - 150, height/2 + 90);
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
            
            // Moves onto next scene after dialogue ends
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
            // Resets Xiaojie's position
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
            // Mama Lai exits house
            if (character2 != null){
                if(leaving){
                    // Move toward door
                    if (character2.x > doorX){
                        character2.move(-2, 0);
                    } else if (character2.y > doorY){
                        character2.move(0,-2);
                    } else if (character2.x <= doorX && character2.y <= doorY){
                        // Once she reaches the door, start minigame
                        character2 = null;
                        minigameStart = true;
                        // Add poop for minigame
                        droppings.add(new ChickenDropping(this, 400, 400, "images/poop.png"));
                        droppings.add(new ChickenDropping(this, 800, 300));
                        droppings.add(new ChickenDropping(this, 100, 600));
                    }
                }
                // Error check
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
                this.text("Click on all 3 chicken droppings around the screen!", 50, 70);
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
                // Delay before next stage
                if (collectedCount == 3){
                    timer++;
                    if (timer > 60){
                        stage = 4;
                        timer = 0;
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
            if (key == ENTER){
                stage = 5;
            }
        } else if (stage == 5){
            if (character2 == null) {
                character2 = new Person (this, 700, 520, 0, "images/Lai.png");
            }
            b2.draw();
            auntie.draw();
            
            // Start dialogue with auntie
            if (!dialogue3aStart){
                dialogue3a.start();
                dialogue3aStart = true;
            }
            if (character2 != null)
                character2.draw();
            
            if (dialogue3a.isActive()) {
                dialogue3a.update();
           
            } else if (!laiWalkingToDoor) {
                laiWalkingToDoor = true;
            }
            
            // Move Mama Lai walking to door
            if (laiWalkingToDoor && !laiFinished && character2 != null) {
                if (character2.y > 300) {
                    character2.move(0, -2);
                } else {
                    laiFinished = true;
                    stage = 6;
                }
            character2.draw();
            }
        } else if (stage == 6) {
            b3.draw();
            character2.draw();
            priest.draw();
            
            // Start dialogue with Mama Lai and the priest
            if(!dialogue3bStart) {
                dialogue3b.start();
                dialogue3bStart = true;
            }
            // If it is running, update
            if(dialogue3b.isActive()) {
                dialogue3b.update();
            
            // If everything is done, start timer for next stage
            } else if (dialogue3b != null && !dialogue3b.isActive() && dialogue3b.isLineFullyShown())  {
                timer++;
                if (timer > 60)
                    stage = 7;
            }
        } else if (stage == 7) {
            background(30);
            fill(255);
            textSize(40);
            this.text("Mama Lai retired, telling Xiaojie what happened, and Xiaojie turns greedy", width/2 - 600, height / 2 - 20);
            textSize(20);
            this.text("Press enter to continue", width/2 - 110, height / 2 + 30);
            if (key == ENTER){
                stage = 8;
            }
        } else if (stage == 8) {
            b3.draw();
            priest.draw();
    
            // Starts dialogue 4 if not started yet
            if(!dialogue4Start){
                dialogue4.start();
                dialogue4Start = true;
            }
            
            // If Xiaojie hasn't transformed yet
            if(transform == null) {
                // Set Xiaojie's position
                character1.x = 700;
                character1.y = 400;
                character1.draw();
            // If she's transformed, draw monkey
            } else if (monkey != null){
                monkey.draw();
            }
            
            // Dialogue for scene
            if (dialogue4.isActive()) {
                dialogue4.update();
            } else {
                if(transform == null) {
                    // Replace Xiaojie with a monkey
                    transform = new Monkey(this, character1.x, character1.y, 0, "images/monkey.png");
                    transform.draw();
                    character1 = null; // Remove Xiaojie
                }
            transform.draw();
            timer++;
            if(timer > 90) {
                stage = 9;
                timer = 0;
            }
            }
  
        } else if (stage == 9) {
            background(30);
            fill(255);
            textSize(40);
            this.text("The End!", width / 2, height / 2);
        }
}
    
    
    public void drawCollisions(){
        if (stage == 1){
            // If Xiaojie touches Mama Lai, show prompt
            if (character2.isCollidingWith(character1)) {
                if (!dialogue.isActive()){
                    fill(255,255,255);
                    this.text("Press 'E' to interact.", character1.x, character1.y);
                }
            }
            // If dialogue is running, update
            if (dialogue.isActive()){
                dialogue.update();
            }
        } else if (stage == 3) {
            // If Mama Lai is gone, skip
            if (character2 == null)
                return;
            // If Xiaojie touches Mama Lai
            if (character2.isCollidingWith(character1)) {
                // Show interaction if dialogue2 hasn't started
                if (!dialogue.isActive()){
                    fill(255,255,255);
                    this.text("Press 'E' to interact.", character1.x, character1.y);
                }
            }
            // Update dialogue2 if active
            if (dialogue2.isActive()) {
                dialogue2.update();
            }
            // After, start Mama Lai's walking
            if (!dialogue2.isActive() && dialogue2.isLineFullyShown() && character2 != null){
                leaving = true;
            }
        }
    }
    
    @Override
    public void keyPressed(){
        if (stage == 0) {
            if (key == ENTER) {
                stage = 1;
            }
        } else if (stage == 1){
            if (dialogue != null && character1.isCollidingWith(character2)){
                // Start dialogue when e is pressed
                if(!dialogue.isActive() && key == 'e'){
                    dialogue.start();
                // Go to next line if e is pressed
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
  
        } else if (stage == 5){
            if (character2 != null && dialogue3a != null) {
                if (!dialogue3a.isActive() && key == 'e') {
                    dialogue3a.start();
                } else if (dialogue3a.isActive() && key == 'e') {
                    dialogue3a.nextLine();
                }
            }
        } else if (stage == 6) {
            if (dialogue3b.isActive() && key == 'e') {
                dialogue3b.nextLine();
            } else if (dialogue3c.isActive() && key == 'e') {
                dialogue3c.nextLine();
            }
        } else if (stage == 8) {
            if (dialogue4 != null && dialogue4.isActive() && key == 'e') {
                dialogue4.nextLine();
            // Skip to next scene if transformation happened
            } else if (dialogue4Done) {
                stage = 9;
            }
        }
        
    }
    
    public void mousePressed(){
        // If minigame is active
        if (minigameStart){
            for (ChickenDropping d : droppings){
                // If player clicks on poop
                if (d.isClicked(mouseX, mouseY) && !d.isCollected()){
                    d.collect(); // Mark as collected
                    collectedCount++; // Increase counter
                }
            }
        }
    }
    
    public void recordPoopGame(){
        int count = 0;
        
        try {
            // Write information into the file
            FileWriter writer = new FileWriter("poopGame.txt", true);
            writer.write(count + 1);
            // Close writer
            writer.close();
            // Error catching
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e);
        }
    }
}
    
        
    