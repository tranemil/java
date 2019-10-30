/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcamfilters;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author bergeron
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private ImageView webcamImageView;
    
    @FXML
    private Button startWebcamButton;
    
    @FXML
    private CheckBox spiralCheckBox;
    
    @FXML
    private CheckBox houghCheckBox;
    
    @FXML
    private CheckBox erodeCheckBox;
    
    @FXML
    private CheckBox blackenCheckBox;
    
    @FXML
    private CheckBox cannyEdgeDetectionCheckBox;
    
    @FXML
    private Slider cannyThresholdSlider;

    @FXML
    private Slider cannyBlurSlider;

    @FXML
    private CheckBox greyScalesCheckBox;       

    @FXML
    private Label messageLabel;

    // Video Capture Status
    private boolean cameraActive = false;
    ScheduledExecutorService timer = null;
    
    @FXML
    private void handleStartWebcamButtonAction(ActionEvent event) {
        if (cameraActive == false)
        {
            // Start grabbing video feed from webcam
            // This executes on a separate thread to not block the GUI

            startWebcamButton.setText("Stop Webcam");
            cameraActive = true;
            messageLabel.setText("Webcam started...");

            // Refresh image 30 times per second / every 33ms
            timer = Executors.newSingleThreadScheduledExecutor();
            timer.scheduleAtFixedRate(new Runnable(){
                @Override
                public void run() {
                    int ipFlags = 0;
                    
                    if (greyScalesCheckBox.isSelected())
                        ipFlags |= VideoProcessing.CONVERT_TO_GREYSCALES_FLAG;
                    
                    if (cannyEdgeDetectionCheckBox.isSelected())
                        ipFlags |= VideoProcessing.CANNY_EDGE_DETECTION_FLAG;
                    
                    if (erodeCheckBox.isSelected())
                        ipFlags |= VideoProcessing.ERODE_FLAG;
                    
                    if (blackenCheckBox.isSelected())
                        ipFlags |= VideoProcessing.BLACKEN_FLAG;
                    
                    if (spiralCheckBox.isSelected())
                        ipFlags |= VideoProcessing.SPIRAL_FLAG;
                    
                    if (houghCheckBox.isSelected())
                        ipFlags |= VideoProcessing.HOUGH_FLAG;                   
                                     
                    int cannyBlur = (int)cannyBlurSlider.getValue();
                    if (cannyBlur%2 == 0) ++cannyBlur;
                    
                    Image fxImage = VideoProcessing.processVideoFrame(ipFlags, cannyBlur, cannyThresholdSlider.getValue());
                    webcamImageView.setImage(fxImage);
                }
            }, 0, 33, TimeUnit.MILLISECONDS); 
        }
        else
        {
            // Stop grabbing video feed from webcam
            startWebcamButton.setText("Start Webcam");
            cameraActive = false;
            messageLabel.setText("Webcam Stopped");


            // Stop timer
            try
            {
                timer.shutdown();
                timer.awaitTermination(2, TimeUnit.SECONDS);
                timer = null;
            }
            catch(Exception e)
            {
                System.out.println("Error shutting down timer!");
            }
            webcamImageView.setImage(null);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VideoProcessing.initializeVideoCapture();
    }        
    
     public void shutdown() {
        
        if (timer != null)
        {
            try
            {
                timer.shutdown();
                timer.awaitTermination(2, TimeUnit.SECONDS);
                timer = null;
            }
            catch(Exception e)
            {
                System.out.println("Error stopping timer!");
            }
        }
        
        VideoProcessing.shutdownVideoCapture();
        Platform.exit();
    }
}
