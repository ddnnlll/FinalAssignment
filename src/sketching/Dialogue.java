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
    private class Line {
        String speaker;
        String txt;
        
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
    
    public Dialogue(PApplet parent, String[] speakers, String [] texts){
        this.parent = parent;
        dialogue = new Line[speakers.length];
        for (int i = 0; i < speakers.length; i++){
            dialogue[i] = new Line(speakers[i], texts[i]);
        }
    }
    
    public void start(){
        currentLine = 0;
        charIndex = 0;
        isActive = true;
        lineFullyShown = false;
    }
    
    public void update(){
        if(!isActive) 
            return;
        
        String fullText = dialogue[currentLine].speaker + ": " + dialogue[currentLine].txt;
        
        if (parent.millis() - lastCharTime > delay && charIndex < fullText.length()){
            charIndex++;
            lastCharTime = parent.millis();
        }
        
        if (charIndex == fullText.length()){
            lineFullyShown = true;
        }
        
        parent.fill(0);
        parent.rect(100, parent.height - 150, parent.width - 200, 100, 10);
        parent.fill(255);
        parent.text(fullText.substring(0, charIndex), 120, parent.height - 110);
        
        if (lineFullyShown){
            parent.text("Press 'e' to continue", parent.width - 280, parent.height - 60);
        }
    }
    
    public void nextLine(){
        if (!isActive)
            return;
        
        if (lineFullyShown){
            currentLine++;
            if (currentLine >= dialogue.length){
                    isActive = false;
            } else {
                charIndex = 0;
                lineFullyShown = false;
            }
        }
    }
    
    public boolean isActive(){
        return isActive;
    }
    
    public boolean isLineFullyShown(){
        return lineFullyShown;
    }
}
