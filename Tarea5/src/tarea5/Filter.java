/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author VictorMolina
 */
public class Filter {
    private final String image; //path of the image to be modified
    
    /**
     * Constructor
     * @param image - path of the image to be modified
     */
    public Filter(String image){
        this.image = image;
    }
    
    /**
     * This method aplies the tonos de grises filter to an image
     * Ther resulting image has only gray tones 
     * @param bI - image to be modified
     * @return image - image with only gray tones
     * @throws IOException 
     */
    public BufferedImage gray(BufferedImage bI) throws IOException{
        int width = bI.getWidth();
        int height = bI.getHeight();
        //Iterating pixel by pixel
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                Color color = new Color(bI.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int gray = (red+green+blue)/3; //formula to get the gray color
                //the value of each color can't be neither higher than 255 nor less than 0
                gray = Math.min(Math.max(gray, 0),255);
                red = gray; 
                green = gray;
                blue = gray;
                Color finalColor = new Color(red,green,blue);
                int pixel = finalColor.getRGB();
                bI.setRGB(i, j, pixel); //change the color of the pixel
            }
        }
        return bI;
    }
    
    /**
     * Method that applies the sepia filter to an image
     * The resulting image seems to be an old image
     * @param cuantity - cuantity of sepia
     * @return - an image that seems to be older
     * @throws java.io.IOException
     */
    public Image sepia(int cuantity) throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        bI = gray(bI);
        int widht = bI.getWidth();
        int height = bI.getHeight();
        //iterating pixel by pixel
        for(int i=0;i<widht;i++){
            for(int j=0;j<height;j++){
                Color color = new Color(bI.getRGB(i, j));
                //changing the value oof the rgb components to get the sepia effect
                int red = color.getRed()+(cuantity*2);
                int green = color.getGreen()+cuantity;
                int blue = color.getBlue()-cuantity;
                //the value of each color can't be neither higher than 255 nor less than 0
                red =  Math.min(Math.max(red, 0),255);
                green =  Math.min(Math.max(green, 0),255);
                blue =  Math.min(Math.max(blue, 0),255);
                Color finalColor = new Color(red,green,blue);
                bI.setRGB(i, j, finalColor.getRGB());
            }
        }
        return (Image)bI;
    }
    
    /**
     * Method that applies the oil painting filter
     * The coonvlution matrix that is used is 7x7
     * @return Oil painted image
     * @throws java.io.IOException
     */
    public Image oleo() throws IOException {
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        int width = bI.getWidth();
        int height = bI.getHeight();
        //Iterating pixel by pixel
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color actual;
                //out of bounds
                if(i+7>width || j+7>height){
                    actual = new Color(bI.getRGB(i, j));
                }else{
                    actual = getMaxColor(bI,i,j);
                }
                bI.setRGB(i, j, actual.getRGB());
            }
        }
        return (Image)bI;
    }
    
    /**
     * Method that gets the color with more appearances given a region of an image
     * @param bI - Image where the region belongs to
     * @param x - initial width value
     * @param y - initial height value
     * @return the color with appears more times
     */
    private Color getMaxColor(BufferedImage bI, int x, int y){
        //Maps which contains the color and the number of times it appears in the region
        Map<Integer,Integer> redTimes = new HashMap<>();
        Map<Integer,Integer> greenTimes = new HashMap<>();
        Map<Integer,Integer> blueTimes = new HashMap<>();
        //iterating pixel by pixel of the region
        for(int i = x; i<x+7;i++){
            for(int j = y; j<y+7;j++){
                Color color = new Color(bI.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                //adding red values
                if(redTimes.containsKey(red)){
                    int times = redTimes.get(red);
                    redTimes.put(red, times+1);
                }else{
                    redTimes.put(red, 1);
                }
                //adding green values
                if(greenTimes.containsKey(green)){
                    int times = greenTimes.get(green);
                    greenTimes.put(green, times+1);
                }else{
                    greenTimes.put(green, 1);
                }
                //adding blue values
                if(blueTimes.containsKey(blue)){
                    int times = blueTimes.get(blue);
                    blueTimes.put(blue, times+1);
                }else{
                    blueTimes.put(blue, 1);
                }
            }
        }
        int maxRed = max(redTimes);
        int maxGreen = max(greenTimes);
        int maxBlue = max(blueTimes);
        Color maxColor = new Color(maxRed,maxGreen,maxBlue);
        return maxColor;
    }
    
    /**
     * Method that gets the color whith the maximum value
     * @param color - Map of the colors and the times it appears in the image
     * @return the color which appears more times
     */
    private int max(Map<Integer,Integer> color){
        int maxTimes = 0;
        int maxColor = 0;
        for(int key : color.keySet()){
            int times = color.get(key);
            if(times>maxTimes){
                maxTimes = times;
                maxColor = key;
            }
        }
        return maxColor;
    }
    
    //available characters
    private final String caracteres = "abcdefghijklmnopqrstuvwxyzŕâéčęëîďôůűü˙ç" + 
                                      "ABCDEFGHIJKLMNOPQRSTUVWXYZŔÂČÉĘËÎĎÔŮŰÜÇ" +
                                      "0123456789" +
                                      " ,.;:!?'Ťť\"" +
                                      "()+-*/=<>[]{}|%" +
                                      "&~#@\\^" +
                                      "\t\n\r" +
                                      "≠"; // Final caracter of the text
    
    /**
     * Returns the range of a character (position)
     * @param c character
     * @return 
     */
    private int range(char c) {
        int i = 0;
        while(i<caracteres.length() && caracteres.charAt(i)!=c) {
            i++;
        }
        return i;
    }
    
    /**
     * This method hides a character in a pixel
     * @param pixel pixel which will hide the character 
     * @param c character to be hidden
     * @return 
     */
    private Pixel hideCharacter(Pixel pixel, char c) {
        int r = pixel.getR(); 
        int g = pixel.getR();
        int b = pixel.getB();
        int cc = range(c);
        if (cc == caracteres.length()) {
            cc = range('?');
        }
        //Turns the value to 5 base
        int cr = (cc/25)%5; 
	int cg = (cc/5)%5;
	int cb = cc%5;
        //Replace the lsb 
        int rr = (r/5)*5+cr;
	int gg = (g/5)*5+cg;
	int bb = (b/5)*5+cb;
        //out of range
        if (rr>255) {
            rr = rr - 5;
	}
	if (gg > 255) {
            gg = gg-5;
	}
	if (bb > 255) {
            bb = bb-5;
	}
        return new Pixel(rr, gg, bb);
    }
    
    /**
     * Method to hide a message in an image
     * @param text - text to be hidden
     * @return an image with the message hidden on the bits
     * @throws IOException 
     */
    public Image hideMessage(String text) throws IOException {
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        int width = bI.getWidth();
        int height = bI.getHeight();
        int k = 0;
        text = text + "≠"; //concatenate the final character
        for (int i = 0 ; i < height && k<text.length()  ; i++) {
            for (int j = 0 ; j < width && k<text.length() ; j++) {
                Color color = new Color(bI.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                Pixel c = new Pixel(red,green,blue);
		Pixel p = hideCharacter(c, text.charAt(k)); //hide the caracter in the pixel
                red = p.getR();
                green = p.getG();
                blue = p.getB();
                Color finalColor = new Color(red,green,blue);
		bI.setRGB(i, j, finalColor.getRGB());
		k++;  
            }
	}
        return (Image)bI;
    }
    
    /**
     * Method to get a character from a pixel
     * @param pixel - pixel that contains the character
     * @return the character hidden in the pixel
     */
    private char getCharacter(Pixel pixel) {
        char c;
        int cr = pixel.getR();
        int cg = pixel.getG();
        int cb = pixel.getB();
        int range = (cr%5)*25 + (cg%5)*5 + cb%5; //range in 10 base
        c = caracteres.charAt(range);
        return c;
    }
    
    /**
     * Method that shows the messge hidden in an image
     * @return the message hidden
     * @throws IOException 
     */
    public String showMessage() throws IOException {
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        int width = bI.getWidth();
        int height = bI.getHeight();
        String text = "";
	boolean b = true;
        while(b){
            for (int i = 0 ; i<height && b ; i++) {
                for (int j = 0 ; j<width && b ; j++) {
                    Color color = new Color(bI.getRGB(i, j));
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    Pixel p = new Pixel(red,green,blue);
                    text = text + getCharacter(p);
                    if (getCharacter(p) == '≠') { //end of the text
                        b = false;
                    }
                }
            } 
        }
  	return text; 
    }
}
