/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5;

/**
 *
 * @author VictorMolina
 */
public class Pixel {
    private int red; 
    private int green; 
    private int blue;
    
    /**
     * Constructor
     * @param red 
     * @param green
     * @param blue
     */
    public Pixel(int red, int green, int blue){
	this.red=red; 
        this.green=green; 
        this.blue=blue;
    }
    
    /**
     * Gets the red value of the pixel
     * @return red value
     */
    public int getR(){
        return red;
    }
    
    /**
     * Gets the green value of the pixel
     * @return green value
     */
    public int getG(){
        return green;
    }
    
    /**
     * Gets the blue value of the pixel
     * @return blue value
     */
    public int getB(){
        return blue;
    }
}
