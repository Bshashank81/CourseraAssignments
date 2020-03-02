import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

class BatchInversions
{
    public ImageResource makeInversion(ImageResource inImage)
    {
        ImageResource outImage =new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel : outImage.pixels())
        {
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            pixel.setRed(255-inPixel.getRed());
            pixel.setGreen(255-inPixel.getGreen());
            pixel.setBlue(255-inPixel.getBlue());
        }
        return outImage;
    }
    public void selectAndConvert()
    {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            ImageResource image = new ImageResource(f);
            ImageResource invertedImage = makeInversion(image);
            invertedImage.draw();
            String filename = image.getFileName();
            String newName = "inverted-"+filename;
            invertedImage.setFileName(newName);
            invertedImage.save();
        }
    }
    public static void main(String args[])
    {
        BatchInversions biObj = new BatchInversions();
        biObj.selectAndConvert();
    }
}
