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
public class Dialogue {
    // Stores the line on who is talking and what they say
    private class Line {
        String speaker;
        String txt;
        
        /**
         * Stores line values
         * @param speaker the speaking person
         * @param txt the text they want to say
         */
        Line(String speaker, String txt){
            this.speaker = speaker;
            this.txt = txt;
        }
    }
    
    private Line[] dialogue;
    private int currentLine = 0;
    private int charIndex = 0;
    private int lastCharTime = 0;
    private int delay = 30;
    private boolean lineFullyShown = false;
    private boolean isActive = false;
    private PApplet parent;
    
    /**
     * Sets up dialogue using speaker names and lines
     * @param parent Main processing sketch
     * @param speakers Array of speaker names
     * @param texts Array of lines characters will say
     */
    public Dialogue(PApplet parent, String[] speakers, String [] texts){
        this.parent = parent;
        dialogue = new Line[speakers.length];
        for (int i = 0; i < speakers.length; i++){
            dialogue[i] = new Line(speakers[i], texts[i]);
        }
    }
    
    /**
     * Start of the dialogue
     */
    public void start(){
        currentLine = 0;
        charIndex = 0;
        isActive = true;
        lineFullyShown = false;
    }
    
    /**
     * Updates the dialogue on screen with each letter
     * Typewriter effect
     */
    public void update(){
        if(!isActive) 
            return;
        
        // Combines name and line into one string
        String fullText = dialogue[currentLine].speaker + ": " + dialogue[currentLine].txt;
        
        // Adds the next letter if the amount of milliseconds has passed
        if (parent.millis() - lastCharTime > delay && charIndex < fullText.length()){
            charIndex++;
            lastCharTime = parent.millis();
        }
        
        // Check if line is finished
        if (charIndex == fullText.length()){
            lineFullyShown = true;
        }
        
        // Draw box and text
        parent.fill(0);
        parent.rect(100, parent.height - 150, parent.width - 200, 100, 10);
        parent.fill(255);
        parent.text(fullText.substring(0, charIndex), 120, parent.height - 110);
        
        // If line is fully shown, provide instructions with next steps
        if (lineFullyShown){
            parent.text("Press 'e' to continue", parent.width - 280, parent.height - 60);
        }
    }
    
    /**
     * Goes to next line if current one is done
     */
    public void nextLine(){
        if (!isActive)
            return;
        
        if (lineFullyShown){
            currentLine++;
            if (currentLine >= dialogue.length){
                    // No more lines are left
                    isActive = false;
            } else {
                // Start the next line
                charIndex = 0;
                lineFullyShown = false;
            }
        }
    }
    
    /**
     * Checks if dialogue is running
     * @return if dialogue is running
     */
    public boolean isActive(){
        return isActive;
    }
    
    /**
     * Checks if the full line is on screen
     * @return the full line
     */
    public boolean isLineFullyShown(){
        return lineFullyShown;
    }
}
