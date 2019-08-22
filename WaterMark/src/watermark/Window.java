/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watermark;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import say.swing.JFontChooser;
import java.awt.Color;

/**
 *
 * @author VictorMolina
 */
public class Window extends javax.swing.JFrame {
    private String path = ""; //source path
    private int method; //choosen method by the user
    private float intensity = 0; //intensity of the water mark
    private String textWM = ""; //water mark text
    private Font font = new Font("Arial",Font.BOLD,100); //font of the Water Mark
    private Color colorWM;  //Color of the Water Mark
    /**
     * Creates new form Window
     */
    public Window() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        text = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        intensitySlider = new javax.swing.JSlider();
        intensityT = new javax.swing.JLabel();
        intensityValue = new javax.swing.JLabel();
        original = new javax.swing.JLabel();
        modified = new javax.swing.JLabel();
        file = new javax.swing.JButton();
        ok = new javax.swing.JButton();
        color = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        filter = new javax.swing.JMenu();
        cWM = new javax.swing.JMenuItem();
        rWM = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Zapf Dingbats", 0, 14)); // NOI18N
        jLabel1.setText("Introduce el Texto");

        text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textActionPerformed(evt);
            }
        });

        jButton1.setText("Seleccionar Tipo de Letra");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        intensitySlider.setPaintLabels(true);
        intensitySlider.setValue(0);
        intensitySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                intensitySliderStateChanged(evt);
            }
        });

        intensityT.setFont(new java.awt.Font("Zapf Dingbats", 0, 14)); // NOI18N
        intensityT.setText("Transparencia");

        intensityValue.setFont(new java.awt.Font("Zapf Dingbats", 0, 14)); // NOI18N
        intensityValue.setText("0");

        original.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        modified.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        file.setText("Seleccionar Archivo");
        file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileActionPerformed(evt);
            }
        });

        ok.setText("Aplicar");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        color.setText("Color");
        color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorActionPerformed(evt);
            }
        });

        filter.setText("Filtro");

        cWM.setText("Marca de Agua");
        cWM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cWMActionPerformed(evt);
            }
        });
        filter.add(cWM);

        rWM.setText("Quitar Marca de Agua");
        rWM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rWMActionPerformed(evt);
            }
        });
        filter.add(rWM);

        jMenuBar1.add(filter);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(intensityT, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(intensityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(text)
                            .addComponent(intensitySlider, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(color))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(original, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(modified, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(file, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ok)
                .addGap(194, 194, 194))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(color))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(intensityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(intensityT)
                    .addComponent(intensitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(original, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modified, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(file)
                    .addComponent(ok))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * This method resize the original size of the image to a
     * size that fits on the label
     * @param image - path of the image
     * @return an image that fits on the jlabel
     */
    private ImageIcon resizeOriginalImage(String image){
        ImageIcon myImage = new ImageIcon(image);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(original.getWidth(), original.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon finalImage = new ImageIcon(newImg);
        return finalImage;
    }
    
    /**
     * This method resize an image 
     * @param img - image to be resized
     * @return an image that fits on the jLabel
     */
    private ImageIcon resizeImage(Image img){
        Image newImg = img.getScaledInstance(original.getWidth(), original.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon finalImage = new ImageIcon(newImg);
        return finalImage;
    }
    
    /**
     * This method acts after the Marca de Agua filter is selected
     * @param evt 
     */
    private void cWMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cWMActionPerformed
        method = 1;
    }//GEN-LAST:event_cWMActionPerformed
    
    /**
     * This method acts after the Aplicar button is selected
     * @param evt 
     */
    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        double alphaValue = intensitySlider.getValue()*0.01;
        intensity = (float) alphaValue;
        textWM = text.getText();
        Filter fB = new Filter(path);
        switch(method){
            case 1:
        {
            try {
                modified.setIcon(resizeImage(fB.addWaterMark(textWM, intensity,font,colorWM)));
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;
            case 2:
                try {
                modified.setIcon(resizeImage(fB.removeWaterMark()));
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            default:
                JOptionPane.showMessageDialog(null, "Selecciona un Filtro");
            break;
        }
        if(original.getIcon() == null){
            JOptionPane.showMessageDialog(null, "Selecciona un archivo");
            return;
        }
    }//GEN-LAST:event_okActionPerformed
    
    /**
     * This method acts after the Selccionar Archivo button is selected
     * @param evt 
     */
    private void fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileActionPerformed
        JFileChooser fileC = new JFileChooser();
        fileC.setDialogTitle("Selecciona una Imagen");
        //Filter to show only files jpg and bmp
        FileNameExtensionFilter filterExtension = new FileNameExtensionFilter("jpg,jpeg,bmp,png","jpg","jpeg","bmp","png");
        fileC.setFileFilter(filterExtension);
        int result = fileC.showOpenDialog(null);
        //check if the user selects the accept button
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileC.getSelectedFile();
            path = selectedFile.getAbsolutePath(); //pathOutput of the file
            original.setIcon(resizeOriginalImage(path)); //resize the original file an sets the final image to the labell
            modified.setIcon(resizeOriginalImage(path));
        }
    }//GEN-LAST:event_fileActionPerformed
    
    /**
     * This method acts after the Quitar Marca de Agua filter is selected
     * @param evt 
     */
    private void rWMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rWMActionPerformed
        method = 2;
    }//GEN-LAST:event_rWMActionPerformed
    
    /**
     * This method acts after the Intensidad slider changes its state
     * @param evt 
     */
    private void intensitySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_intensitySliderStateChanged
       intensityValue.setText(Integer.toString(intensitySlider.getValue()));
    }//GEN-LAST:event_intensitySliderStateChanged
    
    private void textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textActionPerformed

    }//GEN-LAST:event_textActionPerformed
    
    /**
     * This method acts after the Font button is selected
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFontChooser fc = new JFontChooser();
        JOptionPane.showMessageDialog(null, fc,"Selecciona una Fuente",JOptionPane.PLAIN_MESSAGE);
        font = fc.getSelectedFont();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * This method acts after the Color button is selected
     * @param evt 
     */
    private void colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorActionPerformed
        Color c = JColorChooser.showDialog(null,"Escoge un Color", Color.WHITE);
        colorWM = c;
        
    }//GEN-LAST:event_colorActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cWM;
    private javax.swing.JButton color;
    private javax.swing.JButton file;
    private javax.swing.JMenu filter;
    private javax.swing.JSlider intensitySlider;
    private javax.swing.JLabel intensityT;
    private javax.swing.JLabel intensityValue;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel modified;
    private javax.swing.JButton ok;
    private javax.swing.JLabel original;
    private javax.swing.JMenuItem rWM;
    private javax.swing.JTextField text;
    // End of variables declaration//GEN-END:variables
}
