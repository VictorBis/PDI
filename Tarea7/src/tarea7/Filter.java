/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea7;

/**
 *
 * @author VictorMolina
 */

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
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
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                Color color = new Color(bI.getRGB(j, i));
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
                bI.setRGB(j, i, pixel); //change the color of the pixel
            }
        }
        return bI;
    }
    
    /**
     * Method that applies the dithering al azar filter to an image
     * The resulting only has two colors
     * @return - an image with only two colors
     * @throws java.io.IOException
     */
    public Image azar() throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        bI = gray(bI);
        int width = bI.getWidth();
        int height = bI.getHeight();
        Random rnd = new Random();
        //iterating pixel by pixel
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                Color color = new Color(bI.getRGB(j, i));
                int red = color.getRed();
                int number = rnd.nextInt(255);
                red = (number > red) ? 255 : 0;
                Color finalColor = new Color(red,red,red);
                bI.setRGB(j, i, finalColor.getRGB());
            }
        }
        return (Image)bI;
    }
    
    /**
     * Method that applies the dithering filter to an image
     * The resulting only has two colors
     * @param array - cluster array or dispersed
     * @return - an image with only two colors
     * @throws java.io.IOException
     */
    public Image dithering(int[] array) throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        bI = gray(bI);
        int width = bI.getWidth();
        int height = bI.getHeight();
        //iterating pixel by pixel
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                Color color = new Color(bI.getRGB(j, i));
                int red = color.getRed();
                red = (red/28 < array[i%3*3+j%3]) ? 0 : 255; //compare the values
                Color finalColor = new Color(red,red,red);
                bI.setRGB(j, i, finalColor.getRGB());
            }
        }
        return (Image)bI;
    }
   
    /**
     * Method that applies the difusion del error filter to an image
     * The resulting only has two colors
     * @return - an image with only two colors
     * @throws java.io.IOException
     */
    public Image error() throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        bI = gray(bI);
        int width = bI.getWidth();
        int height = bI.getHeight();
        //iterating pixel by pixel
        for(int i=0;i<height;i++){
            int errorDif = 0;
            int k = 1;
            for(int j=0;j<width;j++){
                Color color = new Color(bI.getRGB(j, i));
                int red = color.getRed();
                int newByte = errorDif + red;
                if(newByte >= 255){
                    newByte = 255;
                    red = 255;
                    errorDif = 0;
                }else if(newByte <= 0){
                    newByte = 0;
                    red = 0;
                    errorDif = 0;
                }else if((255-newByte) > 127){
                    red = 0;
                    k = 1;
                    errorDif = newByte * k;
                }else{
                    red = 255;
                    k = -1;
                    errorDif = 255 - newByte;
                    errorDif = errorDif * k;
                }
                Color finalColor = new Color(red,red,red);
                bI.setRGB(j, i, finalColor.getRGB());
            }
        }
        return (Image)bI;
    }
}