import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) 
    {
        int count=0;
        for(Point pt : s.getPoints())
        {
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
       
        double avg = getPerimeter(s);
        int count=getNumPoints(s);
        avg=avg/count;
        return avg;
    }

    public double getLargestSide(Shape s) 
    {
        
        double lar = 0.0;
        
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double sidelen = prevPt.distance(currPt);
            if(sidelen>lar)
            {
                lar=sidelen;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return lar;
    }

    public double getLargestX(Shape s) {
        
        double largeX=0;
        for(Point pt:s.getPoints())
        {
            double x=pt.getX();
            if(x>largeX)
                largeX=x;
        }
        return largeX;
    }

    public double getLargestPerimeterMultipleFiles() 
    {
        
        double large = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length>large)
                large=length;
        }
        
        return large;
    }

    public String getFileWithLargestPerimeter() 
    {
       
        
        File temp = null;   
        double largest = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length>largest)
            {
                largest=length;
                temp=f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int num= getNumPoints(s);
        System.out.println("Number of points in the shape = "+num); 
        double avglen =  getAverageLength(s);
        System.out.println("Average of all the sides of the shape = "+avglen);
        double lar = getLargestSide(s);
        System.out.println("The largest side of the shape = "+lar);
        double largeX = getLargestX(s);
        System.out.println("The largest X in the shape = "+largeX);
    }
    
    public void testPerimeterMultipleFiles() 
    {
        // Put code here
        double largest = getLargestPerimeterMultipleFiles();
        System.out.println("The largest perimeter of the all the shapes = "+largest);
    }

    public void testFileWithLargestPerimeter() 
    {
        // Put code here
        String f=getFileWithLargestPerimeter();
        System.out.println("The name of the file = "+f);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
