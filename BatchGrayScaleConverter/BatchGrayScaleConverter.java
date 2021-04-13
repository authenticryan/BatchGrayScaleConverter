/**
 * Write a description of BatchGrayScaleConverter here.
 * 
 * @author Ryan Dsouza
 * @version 1.0
 * Stable for small sizes. Threads handling needed for big size images
 */

import edu.duke.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class BatchGrayScaleConverter {  
    public void processBatchFiles() {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            ImageResource ir = new ImageResource(f);
            ImageResource grayScaleImage = convertToGrayScale(ir);
            grayScaleImage.setFileName("gray_"+ir.getFileName());
            grayScaleImage.draw();
            grayScaleImage.save();
        }
    }

    public ImageResource convertToGrayScale(ImageResource colorImage) {
        ImageResource grayScaleImage = new ImageResource(colorImage.getWidth(), colorImage.getHeight());
        for(Pixel blankPixel : grayScaleImage.pixels()){
            Pixel colorPixel = colorImage.getPixel(blankPixel.getX(), blankPixel.getY());
            int averageColor = (colorPixel.getRed() + colorPixel.getBlue() + colorPixel.getGreen()) / 3;
            
            blankPixel.setRed(averageColor);
            blankPixel.setGreen(averageColor);
            blankPixel.setBlue(averageColor);
        }
        return grayScaleImage;
    }

}
