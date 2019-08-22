/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author VictorMolina
 * This class contains the mplementation of evry filter
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
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image brillo(int brillo) throws IOException{
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
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * This method aplies the tonos de grises filter to an image
     * Ther resulting image has only gray tones 
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image grisesMetodo1() throws IOException{
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
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * This method aplies the tonos de grises filter to an image
     * Ther resulting image has only gray tones 
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image grisesMetodo2() throws IOException{
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
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * This method aplies the tonos de grises filter to an image
     * Ther resulting image has only gray tones 
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image grisesMetodo3() throws IOException{
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
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * This method aplies the inverso filter to an image
     * Ther resulting image is in black and withe colors 
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image inverso() throws IOException{
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
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * This method aplies the alto contraste filter to an image
     * Ther resulting image is in black and withe colors 
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image altoContraste() throws IOException{
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
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * This method aplies the mosaico filter to an image
     * The resulting image is not as clear as the original
     * @param ancho - width of the matrix
     * @param alto - height of the matrix
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image mosaico(int ancho, int alto) throws IOException{
        if(ancho <=0 || alto<=0){
            return null;
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
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * This method aplies the rgb filter to an image
     * Ther resulting image has the value of every component different
     * @param r - value of red
     * @param g - value of green
     * @param b - value of blue
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image rgb(int r,int g, int b) throws IOException{
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
       Image img = (Image) bI;
       return img;
    }
        
    /**
     * This method applies the Blur filter
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image blur() throws IOException{
        float[]matrix = {0,0,1f/13f,0,0,
                         0,1f/13f,1f/13f,1f/13f,0,
                         1f/13f,1f/13f,1f/13f,1f/13f,1f/13f,
                         0,1f/13f,1f/13f,1f/13f,0,
                         0,0,1f/13f,0,0}; //filter matrix
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        Kernel kernel = new Kernel (5,5,matrix); //kernel with the data
        ConvolveOp op = new ConvolveOp(kernel);  //borders pixel's values are 0
        bI = op.filter(bI, null);//applies the convolution
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * This method applies the motionBlur filter
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image motionBlur() throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        float[]matrix = {1f/9f,0,0,0,0,0,0,0,0,
                         0,1f/9f,0,0,0,0,0,0,0,
                         0,0,1f/9f,0,0,0,0,0,0,
                         0,0,0,1f/9f,0,0,0,0,0,
                         0,0,0,0,1f/9f,0,0,0,0,
                         0,0,0,0,0,1f/9f,0,0,0,
                         0,0,0,0,0,0,1f/9f,0,0,
                         0,0,0,0,0,0,0,1f/9f,0,
                         0,0,0,0,0,0,0,0,1f/9f}; //filter matrix
        Kernel kernel = new Kernel(9,9,matrix);
        ConvolveOp op = new ConvolveOp(kernel); //borders pixel's values are 0
        bI = op.filter(bI, null); //applies the convolution
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * This method applies the findEdges filter
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image findEdges() throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        float[] matrix = {-1,-1,-1,
                          -1,8,-1,
                          -1,-1,-1}; //filter matrix
        Kernel kernel = new Kernel(3,3,matrix);
        ConvolveOp op = new ConvolveOp(kernel);
        bI = op.filter(bI, null);
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * This method applies the sharpen filter
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image sharpen() throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        float[] matrix = {-1,-1,-1,
                          -1,9,-1,
                          -1,-1,-1}; //filter matrix
        Kernel kernel = new Kernel(3,3,matrix);
        ConvolveOp op = new ConvolveOp(kernel);
        bI = op.filter(bI, null);
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * This method applies the emboss filter
     * @return The image with the filter applied
     * @throws IOException 
     */
    public Image emboss() throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        float[] matrix = {-1,-1,0,
                          -1,0,1,
                          0,1,1}; //filter matrix
        Kernel kernel = new Kernel(3,3,matrix);
        ConvolveOp op = new ConvolveOp(kernel);
        bI = op.filter(bI, null);
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * Filtro de convolucion mediana de imagen con una matriz de 3x3.
     * @return The image with the filter applied
     * @throws java.io.IOException
     */
    public Image mediana() throws IOException {
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        int w = bI.getWidth(), h = bI.getHeight(), tam = 3, rad = tam/2, r[], g[], b[];
        //Save the original colors
        Color[][] original = new Color[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) original[x][y] = new Color(bI.getRGB(x, y));
        }
        //Iterating pixdl by pixel and hetting the new colors
        for (int x = 0; x < w; x++) {
            //Horizontal limits
            int xi = (x < rad)? rad - x : 0, xf = ((w - x) <= rad)? rad + w - x : tam;
            for (int y = 0; y < h; y++){
                //Vetical limits
                int yi = (y < rad)? rad - y : 0, yf = ((h - y) <= rad)? rad + h - y : tam;
                //Auxiliar arrays to calculate the median color of evry component
                r = new int[(xf - xi) * (yf - yi)]; g = new int[r.length]; b = new int[r.length];
                for (int i = 0, px = x - rad; (i + xi) < xf; i++) {
                    for (int j = 0, py = y - rad; (j + yi) < yf; j++) {
                        r[j + (yf - yi) * i] = original[px + i + xi][py + j + yi].getRed();
                        g[j + (yf - yi) * i] = original[px + i + xi][py + j + yi].getGreen();
                        b[j + (yf - yi) * i] = original[px + i + xi][py + j + yi].getBlue();
                    }
                } bI.setRGB(x, y, new Color(mediana(r), mediana(g), mediana(b)).getRGB());
            }
        } 
        Image img = (Image) bI;
        return img;
    }
    
    /**
     * mediana's auxiliar method
     * Gets the mediana of a number
     * @param x - number
     * @return - the mediana
     */
    private static int mediana(int ... x) {
        int xn = x.length, m = -1;
        for (int i = 0, j = 0; i <= (xn / 2); i++, j = i) {
            for (int k = i + 1; k < xn; k++) { if(x[j] > x[k]) j = k; }
            m = x[j]; x[j] = x[i]; x[i] = m;
        } if((xn % 2) == 0) return (m + x[xn / 2 - 1]) / 2;
        return m;
    }
    
}
