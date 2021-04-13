
/**
 * Write a description of BatchInversionConverter here.
 * 
 * @author Ryan Dsouza 
 * @version 1.0 - Final Sign off - free of defects for small images. Thread handling required for large images
 */

import edu.duke.*;
import java.io.File;

public class BatchInversionConverter {  
    public void processBatchFiles() {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            ImageResource ir = new ImageResource(f);
            ImageResource invertedImage = inversion(ir);
            invertedImage.setFileName("inverted_"+ir.getFileName());
            invertedImage.draw();
            invertedImage.save();
        }
    }

    public ImageResource inversion(ImageResource colorImage) {
        ImageResource invertedImage = new ImageResource(colorImage.getWidth(), colorImage.getHeight());
        for(Pixel blankPixel : invertedImage.pixels()){
            Pixel colorPixel = colorImage.getPixel(blankPixel.getX(), blankPixel.getY());

            blankPixel.setRed(255-colorPixel.getRed());
            blankPixel.setGreen(255-colorPixel.getGreen());
            blankPixel.setBlue(255-colorPixel.getBlue());
        }
        return invertedImage;
    }
}

