/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watermark;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author VictorMolina
 */
public class WaterMark {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Window window = new Window();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setTitle("Filtros");
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2); //Center the window
        window.setVisible(true);
        window.setResizable(false);
    }
    
}
