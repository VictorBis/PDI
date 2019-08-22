/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watermark;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author VictorMolina
 */
public class Filter {
    private final String image; //image to be modified
    
    /**
     * Constructor
     * @param image - image to apply the filter
     */
    public Filter(String image){
        this.image = image;
    }
    
    /**
     * This method adds a water mark on an image
     * @param text - Text to be put on the image
     * @param intensity - transparency if the text
     * @param font - Font style of the water mark
     * @param color - Color of the Water mark
     * @return an image with a water mark
     * @throws IOException 
     */
    public Image addWaterMark(String text, float intensity, Font font,Color color) throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        Graphics2D g2d = (Graphics2D) bI.getGraphics();
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, intensity); //alpha value
        g2d.setComposite(alphaChannel);
        g2d.setColor(color);
        g2d.setFont(font);
        FontMetrics fontMetrics = g2d.getFontMetrics(); //values of the font
        Rectangle2D rect = fontMetrics.getStringBounds(text, g2d); //rectangle that contains the water mark
        //coordinates to center the watermark
        int centerX = (bI.getWidth() - (int) rect.getWidth()) / 2;
        int centerY = (bI.getHeight() + (int) rect.getHeight()) / 2;
        g2d.drawString(text, centerX, centerY); //paints the water Mark
        g2d.dispose();
        Image img = (Image) bI;
        return img;
    }
     
    /**
     * This method removes the water mark of an image
     * @return An image without water mark
     * @throws IOException 
     */
    public Image removeWaterMark() throws IOException{
        BufferedImage bI = null;
        File f = null;
        f = new File(image);
        bI = ImageIO.read(f);
        int width = bI.getWidth();
        int height = bI.getHeight();
        for(int i = 0; i<width;i++){
            for(int j = 0; j<height; j++){
                Color color = new Color(bI.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int difRG = Math.abs(red-green);
                if(difRG < 5 ){
                    continue;
                }else{
                    int prom = (int) (((red+green+blue)/3));
                    prom = Math.max(0, Math.min(255, prom));
                    if(prom>190){ //white part
                        red = prom+60; //setting the color almost white
                   }else if(prom>90 && prom<191){//gray part
                        red = prom+25; //making the color of the pixel brigther   
                    }else{ //black
                       red = prom-30;
                   }
                    red = Math.max(0, Math.min(255, red));
                    green = blue = red;                  
                }
                Color finalColor = new Color(red,green,blue);
                int pixel = finalColor.getRGB();
                bI.setRGB(i, j, pixel);
            }
        }
        String route = "output.jpg";
        f = new File(route); 
        ImageIO.write(bI, "jpg", f); //writes the fina image
        Image img = (Image) bI;
        return img;
    }
}
