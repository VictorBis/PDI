/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letras;

import java.awt.Color;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import java.util.Queue;

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
     * This method aplies the tonos de grises filter to an image
     * Ther resulting image has only gray tones 
     * @param bI Image to apply the filtere
     * @return The image with the filter applied
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
     * This method aplies the mosaico filter to an image
     * The resulting image is not as clear as the original
     * @param ancho - width of the matrix
     * @param alto - height of the matrix
     * @return The image with the filter applied
     * @throws IOException 
     */
    public BufferedImage mosaico(int ancho, int alto) throws IOException{
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
        return bI;
    }
    
    /**
     * This method aplies the colores sin letra filter to an image
     * Ther resulting image has only the letter M with the color of it's pixels's position 
     * Creates a html file wich shows the resulting image
     * @param x Witdth of the mosaic
     * @param y Height of the mosaic
     * @param lSize Size of the letter
     * @throws IOException 
     */
    public void coloresLetra(int x, int y,int lSize) throws IOException{
        BufferedImage bI = mosaico(x,y); //mosaico filter applied
        BufferedWriter bw = null;
        try{
            FileWriter file = new FileWriter("../../Downloads/Letras/Imagen.html",false); //Open the file
            bw = new BufferedWriter(file);
            int width = bI.getWidth();
            int height = bI.getHeight();
            //Iterating pixel by pixel
            for(int i = 0; i<height; i++){
                for(int j = 0; j<width; j++){
                    Color color = new Color(bI.getRGB(j, i));
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    bw.write("<font size="+lSize+" style=color:rgb("+red+","+green+","+blue+");>M</font>"); //write the letter
                }
                bw.write("<br>");//next line
            }
        }catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }finally{
            if(bw!=null){
                bw.close();
            }
        }
    }
    
    /**
     * This method applies the Tonos de Grises filter to an image
     * Ther resulting image has only the letter M with gray colors 
     * Creates a html file which shows the resulting image
     * @param x Width of the mosaic
     * @param y Height of the mosaic
     * @param lSize Size of the letter
     * @throws IOException 
     */
    public void grisesLetra(int x, int y,int lSize) throws IOException{
        BufferedImage bI = mosaico(x,y); //mosaico filter applied
        bI = gray(bI); //gray filter applied
        BufferedWriter bw = null;
        try{
            FileWriter file = new FileWriter("../../Downloads/Letras/Imagen.html",false);//Open the file
            bw = new BufferedWriter(file);
            int width = bI.getWidth();
            int height = bI.getHeight();
            //Iterating pixel by pixel
            for(int i = 0; i<height; i++){
                for(int j = 0; j<width; j++){
                    Color color = new Color(bI.getRGB(j, i));
                    int red = color.getRed();//we only need on value as the image is gray
                    bw.write("<font size="+lSize+" style=color:rgb("+red+","+red+","+red+");>M</font>");//write the letter
                }
                bw.write("<br>");//next line
            }
        }catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }finally{
            if(bw!=null){
                bw.close();
            }
        }
    }
    
    /**
     * This method applies the Imagen con Letras filter
     * The resulting image has diffrerent letters and colors instead of the original pixel
     * Creates a html file which shows the resulting image
     * @param x
     * @param y
     * @param lSize
     * @throws IOException 
     */
    public void imagenLetra(int x, int y,int lSize) throws IOException{
        BufferedImage bI = mosaico(x,y); //mosaico filter applied
        BufferedWriter bw = null;
        try{
            FileWriter file = new FileWriter("../../Downloads/Letras/Imagen.html",false);//Open the file
            bw = new BufferedWriter(file);
            int width = bI.getWidth();
            int height = bI.getHeight();
            //Iterating pixel by pixel
            for(int i = 0; i<height; i++){
                for(int j = 0; j<width; j++){
                    Color color = new Color(bI.getRGB(j, i));
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    int gray = (red+green+blue)/3;
                    String letter = findLetter(gray);
                    bw.write("<font size="+lSize+" style=color:rgb("+red+","+green+","+blue+");>"+letter+"</font>");//write the letter
                }
                bw.write("<br>");//next line
            }
        }catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }finally{
            if(bw!=null){
                bw.close();
            }
        }
    }
    
    /**
     * This method returns the letter that corresponds to the gray value of the pixel
     * @param gray gray value
     * @return the corresponding letter
     */
    private String findLetter(int gray){
        String letter = " ";
        if(gray<16){
            letter = "M";
        }else if(gray>15 && gray<32){
            letter = "N";
        }else if(gray>31 && gray<48){
            letter = "H";
        }else if(gray>47 && gray<64){
            letter = "#";
        }else if(gray>63 && gray<80){
            letter = "Q";
        }else if(gray>79 && gray<96){
            letter = "U";
        }else if(gray>95 && gray<112){
            letter = "A";
        }else if(gray>111 && gray<128){
            letter = "D";
        }else if(gray>127 && gray<144){
            letter = "0";
        }else if(gray>143 && gray<160){
            letter = "Y";
        }else if(gray>159 && gray<176){
            letter = "2";
        }else if(gray>175 && gray<192){
            letter = "$";
        }else if(gray>191 && gray<210){
            letter = "%";
        }else if(gray>209 && gray<226){
            letter = "+";
        }else if(gray>225 && gray<240){
            letter = ".";
        }
        return letter;
    }
    
    /**
     * This method applie the Domino Blancas filter to an image
     * The resulting image has only domino's tokens
     * Creates a html file which contains the resulting image
     * @param x width of teh mosaic
     * @param y height of the mosaic
     * @param lSize size of the letters
     * @throws IOException 
     */
    public void dominoB(int x, int y,int lSize) throws IOException{
        BufferedImage bI = mosaico(x,y); //mosaico filter applied
        bI = gray(bI);
        BufferedWriter bw = null;
        try{
            FileWriter file = new FileWriter("../../Downloads/Letras/Imagen.html",false);//Open the file
            bw = new BufferedWriter(file);
            int width = bI.getWidth();
            int height = bI.getHeight();
             bw.write("<PRE><style>@font-face{font-family: 'Playcrds';src: url('dominos-cartas/lasvwd__.TTF') "
                     + "format('truetype');}font{font-family: 'Playcrds'}</style>"); //white domino font
            //Iterating pixel by pixel
            for(int i = 0; i<height; i++){
                for(int j = 0; j<width; j++){
                    Color color = new Color(bI.getRGB(j, i));
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    int gray = (red+green+blue)/3;
                    String letter = findDominoB(gray);
                    bw.write("<font size="+lSize+">"+letter+"</font>");//write the letter
                }
                bw.write("<br>");//next line
            }
        }catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }finally{
            if(bw!=null){
                bw.close();
            }
        }
    }
    
    /**
     * This method find the domino's value of a pixel 
     * @param gray value of gray color
     * @return the value of the pixel
     */
    private String findDominoB(int gray){
        String letter = "0)";
        if(gray<37){
            letter = "6^";
        }else if(gray>36 && gray<73){
            letter = "5%";
        }else if(gray>72 && gray<109){
            letter = "4$";
        }else if(gray>108 && gray<145){
            letter = "3#";
        }else if(gray>144 && gray<181){
            letter = "2@";
        }else if(gray>180 && gray<217){
            letter = "1!";
        }
        return letter;
    }
    
    /**
     * This method applie the Domino Negras filter to an image
     * The resulting image has only domino's tokens
     * Creates a html file which contains the resulting image
     * @param x width of teh mosaic
     * @param y height of the mosaic
     * @param lSize size of the letters
     * @throws IOException 
     */
    public void dominoN(int x, int y,int lSize) throws IOException{
        BufferedImage bI = mosaico(x,y); //mosaico filter applied
        bI = gray(bI);
        BufferedWriter bw = null;
        try{
            FileWriter file = new FileWriter("../../Downloads/Letras/Imagen.html",false);//Open the file
            bw = new BufferedWriter(file);
            int width = bI.getWidth();
            int height = bI.getHeight();
             bw.write("<PRE><style>@font-face{font-family: 'Playcrds';src: url('dominos-cartas/lasvbld_.TTF') "
                     + "format('truetype');}font{font-family: 'Playcrds'}</style>"); //white domino font
            //Iterating pixel by pixel
            for(int i = 0; i<height; i++){
                for(int j = 0; j<width; j++){
                    Color color = new Color(bI.getRGB(j, i));
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    int gray = (red+green+blue)/3;
                    String letter = findDominoN(gray);
                    bw.write("<font size="+lSize+">"+letter+"</font>");//write the letter
                }
                bw.write("<br>");//next line
            }
        }catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }finally{
            if(bw!=null){
                bw.close();
            }
        }
    }
    
    /**
     * This method find the domino's value of a pixel 
     * @param gray value of gray color
     * @return the value of the pixel
     */
    private String findDominoN(int gray){
        String letter = "6^";
        if(gray<37){
            letter = "0)";
        }else if(gray>36 && gray<73){
            letter = "1!";
        }else if(gray>72 && gray<109){
            letter = "2@";
        }else if(gray>108 && gray<145){
            letter = "3#";
        }else if(gray>144 && gray<181){
            letter = "4$";
        }else if(gray>180 && gray<217){
            letter = "5%";
        }
        return letter;
    }
    
    /**
     * This method applies the filter naipes
     * The return image has a card in every pixel
     * Creates an html with the reulting image
     * @param x Width of the mosaic
     * @param y Height of the mosaic
     * @param lSize Size of the letter
     * @throws IOException 
     */
    public void naipes(int x, int y,int lSize) throws IOException{
        BufferedImage bI = mosaico(x,y); //mosaico filter applied
        bI = gray(bI);
        BufferedWriter bw = null;
        try{
            FileWriter file = new FileWriter("../../Downloads/Letras/Imagen.html",false);//Open the file
            bw = new BufferedWriter(file);
            int width = bI.getWidth();
            int height = bI.getHeight();
             bw.write("<PRE><style>@font-face{font-family: 'Playcrds';src: url('dominos-cartas/Playcrds.TTF') "
                     + "format('truetype');}font{font-family: 'Playcrds'}</style>"); //naipes font
            //Iterating pixel by pixel
            for(int i = 0; i<height; i++){
                for(int j = 0; j<width; j++){
                    Color color = new Color(bI.getRGB(j, i));
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    int gray = (red+green+blue)/3;
                    String letter = findNaipesL(gray);
                    bw.write("<font size="+lSize+">"+letter+"</font>");//write the letter
                }
                bw.write("<br>");//next line
            }
        }catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }finally{
            if(bw!=null){
                bw.close();
            }
        }
    }
    
    /**
     * This method returns the letter that corresponds to the gray value of the pixel
     * @param gray gray value
     * @return the corresponding letter
     */
    private String findNaipesL(int gray){
        String letter = "A";
        if(gray<21){
            letter = "M";
        }else if(gray>20 && gray<41){
            letter = "L";
        }else if(gray>40 && gray<61){
            letter = "K";
        }else if(gray>60 && gray<81){
            letter = "J";
        }else if(gray>80 && gray<101){
            letter = "I";
        }else if(gray>100 && gray<121){
            letter = "H";
        }else if(gray>120 && gray<141){
            letter = "G";
        }else if(gray>140 && gray<161){
            letter = "F";
        }else if(gray>160 && gray<181){
            letter = "E";
        }else if(gray>180 && gray<201){
            letter = "D";
        }else if(gray>200 && gray<221){
            letter = "C";
        }else if(gray>220 && gray<241){
            letter = "B";
        }
        return letter;
    }
    
    /**
     * This method applies the Colores con Texto filter to an image
     * The return image has a character of the input file in each pixel
     * Creates an html with the reulting image
     * @param path path of the input file
     * @param x width of the mosaic
     * @param y heicght of the mosaic
     * @param lSize size of the letter
     * @throws IOException 
     */
    public void coloresTexto(String path,int x, int y,int lSize) throws IOException{
        BufferedImage bI = mosaico(x,y); //mosaico filter applied
        Queue<Character> queue = new LinkedList<>();
        BufferedWriter bw = null;
        try{
            BufferedReader bf = new BufferedReader(new FileReader(path)); //read the text
            List<String> lines = new ArrayList<>(); //lines of the text
            String line;
            while((line = bf.readLine()) != null){
                for(String s : line.split(" ")){
                    lines.add(s); //add the string without spaces
                }
            }
            bf.close();
            List<Character> chars = new ArrayList<>();
            for(String s : lines){
                for(char c : s.toCharArray()){
                    chars.add(c); //adds each character of the text
                }
            }
            for(char c : chars){
                queue.add(c); 
            }
            FileWriter file = new FileWriter("../../Downloads/Letras/Imagen.html",false);//Open the file
            bw = new BufferedWriter(file);
            int width = bI.getWidth();
            int height = bI.getHeight();
            //Iterating pixel by pixel
            for(int i = 0; i<height; i++){
                for(int j = 0; j<width; j++){
                    Color color = new Color(bI.getRGB(j, i));
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    char letter = queue.poll(); //gets the first character of the queue
                    bw.write("<font size="+lSize+" style=color:rgb("+red+","+green+","+blue+");>"+letter+"</font>");//write the letter
                    queue.add(letter);//readd the character to the queue
                }
                bw.write("<br>");//next line
            }
        }catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }finally{
            if(bw!=null){
                bw.close();
            }
        }
    }
}
