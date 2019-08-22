/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 *
 * @author VictorMolina
 */
public class Filter {
    /* Image to be modified*/
    private final String image; //image to be modified
    /* List of images */
    private final LinkedList<Image> images;
    
    /**
     * Constructor
     * @param image - image to apply the filter
     */
    public Filter(String image){
        this.image = image;
        images = new LinkedList<>();
    }
    
     /**
     * Method to apply the Foto Mosaico filter to an image
     *
     * @param mWidth Width of the mosaic
     * @param mHeight Height of the mosaic
     * @param folder folder where the images will be taken
     * @return string with the code to build an html file and get the resulting image
     * @throws java.io.IOException error
     */
    public String fotoMosaicos(int mWidth, int mHeight, File folder) throws IOException {
         /* Get every file of the folder*/
        File[] files = folder.listFiles();
          /* Calculate the rgb values of the images*/
        calculateImgAvg(files);
        BufferedImage bI = null;
        File f = null;
        f = new File (image);
        bI = ImageIO.read(f);
        int height = bI.getHeight() / mHeight;
        int width = bI.getWidth() / mWidth;
        String[][] table = 
                new String[bI.getHeight() / mHeight][bI.getWidth() / mWidth];
        String str = 
                "<table border = \"0\" cellspacing=\"0\" cellpadding=\"0\"\n";
        int borderY = 0;
        for (int i = 0; i < bI.getWidth(); i += mWidth) {
            int borderX = 0;
            for (int j = 0; j < bI.getHeight(); j += mHeight) {
                int x = i + mWidth;
                int y = j + mHeight;
                int d1 = mWidth;
                int d2 = mHeight;
                //out of bounds
                if (x > bI.getWidth()) {
                    x = bI.getWidth();
                    d1 = bI.getWidth() - i;
                }
                if (y > bI.getHeight()) {
                    y = bI.getHeight();
                    d2 = bI.getHeight() - j;
                }
                if (borderX >= height || borderY >= width) {
                    break;
                }
                /* Put the image in the table */
                int r = 0;
                int g = 0;
                int b = 0;
                for (int iAux = i; iAux < x; iAux++) {
                    for (int jAux = j; jAux < y; jAux++) {
                        Color color = new Color(bI.getRGB(iAux, jAux));
                        r += color.getRed();
                        g += color.getGreen();
                        b += color.getBlue();
                    }
                }
                r /= d1*d2;
                g /= d1*d2;
                b /= d1*d2;
                /* Gets the best image */
                String id = findImage(r, g, b);
                table[borderX++][borderY] = String.format("\t\t<nobr><td><img src=\"%s\" width=\"20\", height=\"20\"></td></nobr>\n", 
                id);
            }
            borderY++;
        }
        for (int i = 0; i < bI.getHeight() / mHeight; i++) {
            str += "\t<tr>\n";
            for (int j = 0; j < bI.getWidth() / mWidth; j++) {
                str += table[i][j];
            }
            str += "</tr>";
        }
        str += "</table>";
        return str;
    }

    /* Method to calculate the rgb average value of the images
    * @param files Images to get the rgb average
    */
    private void calculateImgAvg(File[] files) throws IOException {
        /* Escogemos aquellos files que sean imágenes. Si se encontrase otra
         extensión, se agregará en futuras actualizaciones. */
        for (File f : files) {
            if (f.getName().contains(".JPG") || f.getName().contains(".jpg")
                    || f.getName().contains(".png") || f.getName().contains(".bmp")) {
                BufferedImage imgAux = ImageIO.read(f);
                 /* Calculate the avg rgb of an image */
                 int r = 0;
                    int g = 0;
                    int b = 0;
                    int width = imgAux.getWidth();
                    int height = imgAux.getHeight();
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            Color color = new Color(imgAux.getRGB(i, j));
                            r += color.getRed();
                            g += color.getGreen();
                            b += color.getBlue();
                        }
                    }
                    r /= width * height;
                    g /= width * height;
                    b /= width * height;
                    double[] values = {r, g, b};
                    images.add(new Image(f.getName(), values));

            }
        }
    }

    /* Finds the image with the smallest distance 
    * @param r Value of the avg red of the original image
    * @param g Value of the avg green of the original image
    * @param blue Value of the avg blue of the original image
    * @return The name of the image with the smallest distance
    */
    private String findImage(double r, double g, double b) {
        double min = distance(r, images.get(0).values[0], g, images.get(0).values[1], b, images.get(0).values[2]);
        String min_name = images.get(0).name;
        for (int i = 1; i < images.size(); i++) {
            double[] imgsVals = images.get(i).values;
            double aux = distance(r, imgsVals[0], g, imgsVals[1], b, imgsVals[2]); //distance between two images
            if (aux <= min) {
                min = aux;
                min_name = images.get(i).name;
            }
        }
        return min_name;
    }
    
    /* Gets the distance of two images
    * The method used is the Euclidian's distance
    * @param rO Average value of the red color in the original image
    * @param rC Average value of the red color in the image to be compared
    * @param gO Average value of the green color in the original image
    * @param gC Average value of the green color in the image to be compared
    * @param bO Average value of the blue color in the original image
    * @param bC Average value of the blue color in the image to be compared
    * @return distance value between two images
    */
    private double distance(double rO, double rC, double gO, double gC, double bO, double bC) {
        return Math.sqrt((rO - rC) * (rO - rC) + (gO - gC) * (gO - gC) + (bO - bC) * (bO - bC));
    }
}
