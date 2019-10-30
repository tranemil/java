/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcamfilters;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.Size;

/**
 *
 * @author cstuser
 */
public class VideoProcessing {
    public final static int CONVERT_TO_GREYSCALES_FLAG      = 1 << 0;
    public final static int CANNY_EDGE_DETECTION_FLAG       = 1 << 1;
    public final static int ERODE_FLAG                      = 1 << 2;
    public final static int BLACKEN_FLAG                    = 1 << 3;
    public final static int SPIRAL_FLAG                     = 1 << 4;
    public final static int HOUGH_FLAG                      = 1 << 5;

    // add your own flags here ...

    
    private static VideoCapture capture = null;

    public static void initializeVideoCapture()
    {
        capture = new VideoCapture();
        capture.open(0);
    }

    public static void shutdownVideoCapture()
    {
        capture.release();
        capture = null;
    }
    
    public static Mat grabVideoFrame()
    {
        if (capture != null && capture.isOpened())
        {
            Mat matImage = new Mat();
            capture.read(matImage);

            // Convert Image Matrix to FX Image
            if (matImage.empty() == false)
            {
                return matImage;
            }
            else
            {
                System.out.println("Video capture returned a null image...");                
                return null;
            }
        }        
        else
        {
            System.out.println("Video capture is not opened...");
            return null;
        }      
    }
    
    public static BufferedImage grabVideoFrameBufferedImage()
    {
        Mat image = grabVideoFrame();
        return matToBufferedImage(image);
    }

    public static Image grabVideoFrameFxImage()
    {
        BufferedImage image = grabVideoFrameBufferedImage();
        return SwingFXUtils.toFXImage(image, null);
    }
    
    public static Image processVideoFrame(int imageProcessingAlgorithmsFlag, int preCannyBlur, double cannyThreshold)
    {
        Mat input = grabVideoFrame();
        Mat output = new Mat(input.width(), input.height(), input.type());

        if ((imageProcessingAlgorithmsFlag & CONVERT_TO_GREYSCALES_FLAG) == CONVERT_TO_GREYSCALES_FLAG)
        {
            Imgproc.cvtColor(input, output, Imgproc.COLOR_RGB2GRAY);
            input = output;
        }
        
        if ((imageProcessingAlgorithmsFlag & CANNY_EDGE_DETECTION_FLAG) == CANNY_EDGE_DETECTION_FLAG)
        {
            Mat blurred = new Mat(input.width(), input.height(), input.type());
            Imgproc.medianBlur(input, blurred, preCannyBlur);
            input = blurred;
            
            Imgproc.Canny(input, output, cannyThreshold, cannyThreshold);
            input = output;     
        }
        
        if ((imageProcessingAlgorithmsFlag & ERODE_FLAG) == ERODE_FLAG)
        {
            Imgproc.erode(input, output, new Mat());
            input = output;
        }
        
        if ((imageProcessingAlgorithmsFlag & BLACKEN_FLAG) == BLACKEN_FLAG)
        {
            Imgproc.morphologyEx(input, output, Imgproc.MORPH_GRADIENT, new Mat());
            input = output;
        }
            
        if ((imageProcessingAlgorithmsFlag & SPIRAL_FLAG) == SPIRAL_FLAG)
        {
            Size dsize = new Size(input.width(), input.height());
            Point center = new Point(input.width()/2, input.height()/2);
            Imgproc.warpPolar(input, output, dsize, center, input.width(), SPIRAL_FLAG);
            input = output;
        }
        
        if ((imageProcessingAlgorithmsFlag & HOUGH_FLAG) == HOUGH_FLAG)
        {
            Imgproc.cvtColor(input, output, Imgproc.COLOR_RGB2GRAY);
            Imgproc.medianBlur(output, output, 5);
            Mat circles = new Mat();
            
            Imgproc.HoughCircles(output, circles, Imgproc.HOUGH_GRADIENT, 1.0,
                (double)output.rows()/16, 100.0, 30.0, 1, 30);
            
            for (int x = 0; x < circles.cols(); x++)
            {
                double[] c = circles.get(0, x);
                Point center = new Point(Math.round(c[0]), Math.round(c[1]));
                Imgproc.circle(input, center, 1, new Scalar(0,100,100), 3, 8, 0 );
                int radius = (int) Math.round(c[2]);
                Imgproc.circle(input, center, radius, new Scalar(0,255,0), 3, 8, 0 );
            }
        }

        return SwingFXUtils.toFXImage(matToBufferedImage(input), null);
    }
    
    
    public static Mat bufferedImageToMat(BufferedImage input)
    {
        BufferedImage convertedImg = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        convertedImg.getGraphics().drawImage(input, 0, 0, null);
        
        Mat mat = new Mat(convertedImg.getHeight(), convertedImg.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) convertedImg.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        
        return mat;
    }
    
    public static BufferedImage matToBufferedImage(Mat original)
    {
        BufferedImage image = null;
        int width = original.width(), height = original.height(), channels = original.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        original.get(0, 0, sourcePixels);

        if (original.channels() > 1)
        {
            image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        }
        else
        {
            image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        }
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);

        return image;
    }
    
    public static Mat applyGaussianBlur(Mat input)
    {
        final int kernelSize = 7;
        Mat kernel = Imgproc.getGaussianKernel(kernelSize, 0.2);

        Mat output = new Mat(input.width(), input.height(), input.type());
        Imgproc.filter2D(input, output, -1, kernel);
    
        return output;
    }
    
}
