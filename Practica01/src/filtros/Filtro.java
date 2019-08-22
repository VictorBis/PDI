/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author VictorMolina
 */
public class Filtro {
    private final String image; //image to be modified
    
    /**
     * Constructor
     * @param image - image to apply the filter
     */
    public Filtro(String image){
        this.image = image;
    }
    
    /**
     * This method aplies the brillo filter to an image
     * Ther resulting image has more bright than the original
     * @param brillo
     * @throws IOException 
     */
    public void brillo(int brillo) throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        int width = bI.getWidth();
        int height = bI.getHeight();
        //Iterating pixel by pixel
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                Color color = new Color(bI.getRGB(i, j));
                int red = color.getRed() + brillo;
                int green = color.getGreen() + brillo;
                int blue = color.getBlue() + brillo;
                //the value of each color can't be higher than 255
                red = Math.min(red, 255); 
                green = Math.min(green, 255);
                blue = Math.min(blue, 255);
                Color finalColor = new Color(red,green,blue);
                int pixel = finalColor.getRGB();
                bI.setRGB(i, j, pixel); //change the color of the pixel
            }
        }
        String route = "output.jpg";
        f = new File(route);
        ImageIO.write(bI, "jpg", f);//writes the fina image
    }
    
    /**
     * This method aplies the tonos de grises filter to an image
     * Ther resulting image has only gray tones 
     * @throws IOException 
     */
    public void grisesMetodo1() throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
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
        String route = "output.jpg";
        f = new File(route);
        ImageIO.write(bI, "jpg", f);
    }
    
    /**
     * This method aplies the tonos de grises filter to an image
     * Ther resulting image has only gray tones 
     * @throws IOException 
     */
    public void grisesMetodo2() throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        int width = bI.getWidth();
        int height = bI.getHeight();
        //Iterating pixel by pixel
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                Color color = new Color(bI.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int gray = (int) Math.round(red*0.3 + green*0.59 +blue*0.11); //formula to get the gray color
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
        String route = "output.jpg";
        f = new File(route);
        ImageIO.write(bI, "jpg", f);
    }
    
    /**
     * This method aplies the tonos de grises filter to an image
     * Ther resulting image has only gray tones 
     * @throws IOException 
     */
    public void grisesMetodo3() throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        int width = bI.getWidth();
        int height = bI.getHeight();
        //Iterating pixel by pixel
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                Color color = new Color(bI.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int gray = (Math.max(red,Math.max(green, blue)) + Math.min(red,Math.min(green, blue)))/2; //formula to get the gray color
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
        String route = "output.jpg";
        f = new File(route);
        ImageIO.write(bI, "jpg", f);
    }
    
    /**
     * This method aplies the inverso filter to an image
     * Ther resulting image is in black and withe colors 
     * @throws IOException 
     */
    public void inverso() throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        int width = bI.getWidth();
        int height = bI.getHeight();
        //Iterating pixel by pixel
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                Color color = new Color(bI.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                double average = (red+green+blue)/3; //average number of the rgb value
                //if the average color of the pizel is lower than 127, then the pixels turns white. 
                //Otherwise, the pixels gets black
                if(average < 127){
                    Color finalColor = new Color(255,255,255);
                    int pixel = finalColor.getRGB();
                    bI.setRGB(i, j, pixel); //change the color of the pixel
                }else{
                    Color finalColor = new Color(0,0,0);
                    int pixel = finalColor.getRGB();
                    bI.setRGB(i, j, pixel); //change the color of the pixel
                }
                
            }
        }
        String route = "output.jpg";
        f = new File(route);
        ImageIO.write(bI, "jpg", f);
    }
    
    /**
     * This method aplies the alto contraste filter to an image
     * Ther resulting image is in black and withe colors 
     * @throws IOException 
     */
    public void altoContraste() throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        int width = bI.getWidth();
        int height = bI.getHeight();
        //Iterating pixel by pixel
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                Color color = new Color(bI.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                double average = (red+green+blue)/3; //average number of the rgb value
                //if the average color of the pizel is lower than 127, then the pixels turns white. 
                //Otherwise, the pixels gets black
                if(average >=127){
                    Color finalColor = new Color(255,255,255);
                    int pixel = finalColor.getRGB();
                    bI.setRGB(i, j, pixel); //change the color of the pixel
                }else{
                    Color finalColor = new Color(0,0,0);
                    int pixel = finalColor.getRGB();
                    bI.setRGB(i, j, pixel); //change the color of the pixel
                }
                
            }
        }
        String route = "output.jpg";
        f = new File(route);
        ImageIO.write(bI, "jpg", f);
    }
    
    /**
     * This method aplies the mosaico filter to an image
     * The resulting image is not as clear as the original
     * @param ancho
     * @param alto
     * @throws IOException 
     */
    public void mosaico(int ancho, int alto) throws IOException{
        if(ancho <=0 || alto<=0){
            return;
        }
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        int width = bI.getWidth();
        int height = bI.getHeight();
        for(int i=0;i<width;i+=ancho){
            for(int j=0;j<height;j+=alto){
                //size of the mosaic
                int x = i + ancho; 
                int y = j + alto;
                int anchoAux = ancho;
                int altoAux = alto;
                //check that the mosaic isn't out of the size of the image
                if(x > width){
                    x = width;
                    anchoAux = width-1;
                }
                if(y > height){
                    y = height;
                    altoAux = height -1;
                }
                int total = ancho*alto; //total of pixels
                //values of evry rgb component of the region
                int red = 0;
                int green = 0;
                int blue = 0;
                //Iterating every pixel of the region
                for(int n = i; n < x; n++){
                    for(int m = j; m < y; m++){
                        Color color = new Color(bI.getRGB(i, j));
                        red += color.getRed();
                        green += color.getGreen();
                        blue += color.getBlue();
                    }
                }
                //average of evry component
                red /= total;
                green /= total;
                blue /= total;
                for(int n = i; n < x; n++){
                    for(int m = j; m < y; m++){
                        Color finalColor = new Color(red,green,blue);
                        int pixel = finalColor.getRGB();
                        bI.setRGB(n, m, pixel); //change the color of the pixel
                    }
                }
            }
        }
        String route = "output.jpg";
        f = new File(route); 
        ImageIO.write(bI, "jpg", f); //writes the fina image
    }
    
    /**
     * This method aplies the rgb filter to an image
     * Ther resulting image has the value of every component different
     * @param r - value of red
     * @param g - value of green
     * @param b - value of blue
     * @throws IOException 
     */
    public void rgb(int r,int g, int b) throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        int width = bI.getWidth();
        int height = bI.getHeight();
        //Iterating pixel by pixel
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                Color color = new Color(bI.getRGB(i, j));
                int red = color.getRed() & r;
                int green = color.getGreen() & g;
                int blue = color.getBlue() & b;
                //the value of each color can't be neither higher than 255 nor less than 0
                red = Math.max(Math.min(red, 255),0); 
                green = Math.max(Math.min(green, 255),0);
                blue = Math.max(Math.min(blue, 255),0);
                Color finalColor = new Color(red,green,blue);
                int pixel = finalColor.getRGB();
                bI.setRGB(i, j, pixel); //change the color of the pixel
            }
        }
        String route = "output.jpg";
        f = new File(route);
        ImageIO.write(bI, "jpg", f);
    }
}
