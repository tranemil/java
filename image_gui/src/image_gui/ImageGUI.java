package image_gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class ImageGUI extends javax.swing.JFrame {
    private static ImageGUI instance = null;
    
    public static void displayMessage(String message)
    {
        if (instance != null)
        {
            instance.messageLabel.setForeground(Color.darkGray);
            instance.messageLabel.setText(message);
        }
    }
    
    public static void displayError(String error)
    {
        if (instance != null)
        {
            instance.messageLabel.setForeground(Color.red);
            instance.messageLabel.setText(error);
        }
    }

    
    public ImageGUI() {
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

        openButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();
        applyButton = new javax.swing.JButton();
        sourceImagePanel = new image_gui.ImagePanel();
        destinationImagePanel = new image_gui.ImagePanel();
        gammaCorrectionLabel = new javax.swing.JLabel();
        gammaCorrectionSlider = new javax.swing.JSlider();
        greyScaleLabel = new javax.swing.JLabel();
        gaussianBlurCheckBox = new javax.swing.JCheckBox();
        gaussianBlurLabel = new javax.swing.JLabel();
        greyScalesCheckBox = new javax.swing.JCheckBox();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        openButton.setText("Open");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        messageLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLabel.setText("Application Loaded!");

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        sourceImagePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout sourceImagePanelLayout = new javax.swing.GroupLayout(sourceImagePanel);
        sourceImagePanel.setLayout(sourceImagePanelLayout);
        sourceImagePanelLayout.setHorizontalGroup(
            sourceImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sourceImagePanelLayout.setVerticalGroup(
            sourceImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        destinationImagePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout destinationImagePanelLayout = new javax.swing.GroupLayout(destinationImagePanel);
        destinationImagePanel.setLayout(destinationImagePanelLayout);
        destinationImagePanelLayout.setHorizontalGroup(
            destinationImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        destinationImagePanelLayout.setVerticalGroup(
            destinationImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        gammaCorrectionLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        gammaCorrectionLabel.setText("Gamma Correction:");

        gammaCorrectionSlider.setMajorTickSpacing(1);
        gammaCorrectionSlider.setMaximum(200);
        gammaCorrectionSlider.setMinimum(1);
        gammaCorrectionSlider.setValue(100);

        greyScaleLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        greyScaleLabel.setText("Convert to Grey Scales");

        gaussianBlurCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        gaussianBlurLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        gaussianBlurLabel.setText("Apply Gaussian Blur");

        greyScalesCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        fileMenu.setText("File");
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                    .addComponent(applyButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(openButton, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(sourceImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(destinationImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(gaussianBlurLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gaussianBlurCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(gammaCorrectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(greyScaleLabel))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(gammaCorrectionSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(greyScalesCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(destinationImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sourceImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gammaCorrectionLabel)
                    .addComponent(gammaCorrectionSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(greyScaleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(greyScalesCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gaussianBlurLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gaussianBlurCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(applyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        JFileChooser openFile = new JFileChooser();
        openFile.setCurrentDirectory(new File("./images"));

        if (openFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            String filename = openFile.getSelectedFile().getPath();
            
            sourceImagePanel.setImage(filename);
            //get path of image
            Path path = new File(filename).toPath();
            try {
                //set name to mimetype
                sourceImagePanel.setName(Files.probeContentType(path));
            } catch (IOException ex) {
                Logger.getLogger(ImageGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            destinationImagePanel.setImage(filename);
            displayMessage(openFile.getSelectedFile().getName() + " succesfully loaded!");            
        }
    }//GEN-LAST:event_openButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser saveFile = new JFileChooser();
        //get mimetype
        String extension = sourceImagePanel.getName().substring(6);
        System.out.println(extension);
                
        if (saveFile.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            String filePath = saveFile.getSelectedFile().getPath();
            String fileName = saveFile.getSelectedFile().getName() + "." + extension;
            System.out.println(fileName);
            //save image
            BufferedImage savedImage = destinationImagePanel.getBufferedImage();
            try {
            ImageIO.write(savedImage, fileName, new File(filePath));
            } catch (IOException ex) {
                Logger.getLogger(ImageGUI.class.getName()).log(Level.SEVERE, null, ex);
            }           
            displayMessage(fileName + " successfully saved!");
        }
        else {
            System.out.println("Cancelled");
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        
        BufferedImage processedImage = sourceImagePanel.getBufferedImage();
        long ms = System.currentTimeMillis();
        String message = "Image Processed in ";
        
        // Gamma Correction slider varies between 1 and 200, we should 
        // consider the values between 0.01 and 2 by dividing by 100
        if (gaussianBlurCheckBox.isSelected())
        {
            processedImage = ImageProcessing.applyGaussianBlur(processedImage);
        }
        
        if (gammaCorrectionSlider.getValue() != 100)
        {
            float gamma = 100.0f / gammaCorrectionSlider.getValue();
            
            processedImage = ImageProcessing.applyGammaCorrection(processedImage, gamma);
        }
            
        if (greyScalesCheckBox.isSelected())
        {
            processedImage = ImageProcessing.convertToGreyScale(processedImage);
        }

        destinationImagePanel.setImage(processedImage);
        displayMessage(message + " successful, executed in "+ (System.currentTimeMillis() - ms) +"ms.");

    }//GEN-LAST:event_applyButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                instance = new ImageGUI();
                instance.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private image_gui.ImagePanel destinationImagePanel;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel gammaCorrectionLabel;
    private javax.swing.JSlider gammaCorrectionSlider;
    private javax.swing.JCheckBox gaussianBlurCheckBox;
    private javax.swing.JLabel gaussianBlurLabel;
    private javax.swing.JLabel greyScaleLabel;
    private javax.swing.JCheckBox greyScalesCheckBox;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton openButton;
    private javax.swing.JButton saveButton;
    private image_gui.ImagePanel sourceImagePanel;
    // End of variables declaration//GEN-END:variables
}