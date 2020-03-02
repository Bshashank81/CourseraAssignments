import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class ColdTemperatures
{
    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow : parser)
        {
            coldestSoFar=getSmallestOfTwo(currentRow,coldestSoFar);
        }
        return coldestSoFar;
    }
    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEDT"));
    }
    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar)
    {
        if(coldestSoFar == null)
            coldestSoFar = currentRow;
        else
        {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if(currentTemp<smallestTemp && currentTemp != -9999)
            {
                coldestSoFar=currentRow;
            }

        }
        return coldestSoFar;
    }
    public String fileWithColdestTemperature()
    {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        File smallestFile=null;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);

            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            smallestSoFar=getSmallestOfTwo(currentRow,smallestSoFar);
            smallestFile = f;
        }
        return smallestFile.getName();
    }
    public void testFileWithColdestTemperature()
    {
        String filename = fileWithColdestTemperature();
        System.out.println("Coldest day was in file "+filename);
        FileResource fr = new FileResource();
        CSVRecord coldesthour = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was "+coldesthour.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were:");
        filename="nc_weather/2014/"+filename;
        FileResource frr = new FileResource(filename);
        CSVParser rec = frr.getCSVParser();
        for(CSVRecord curr : rec)
        {
            System.out.println(curr.get("DateUTC")+": "+curr.get("TemperatureF"));
        }
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
          CSVRecord lowestHumiditySoFar=null;

        for(CSVRecord currRow : parser)
        {
            lowestHumiditySoFar = getSmallestHumidityOfTwo(currRow,lowestHumiditySoFar);
        }
        return lowestHumiditySoFar;
    }
    public CSVRecord getSmallestHumidityOfTwo(CSVRecord currentRow,CSVRecord lowestHumiditySoFar)
    {
        if(lowestHumiditySoFar == null)
            lowestHumiditySoFar = currentRow;
        else
        {
            double currentHumidity;
            if(currentRow.get("Humidity").equals("N/A"))
                 currentHumidity = 1000;
            else
                currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double lowestHumidity = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
            if(currentHumidity<lowestHumidity)
            {
                lowestHumiditySoFar = currentRow;
            }
        }
        return lowestHumiditySoFar;
    }
    public void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord humidityRec = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was "+humidityRec.get("Humidity")+" at "+humidityRec.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles()
    {
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestHumiditySoFar = getSmallestHumidityOfTwo(currentRow,lowestHumiditySoFar);
        }
        return lowestHumiditySoFar;
    }
    public void testLowestHumidityInManyFiles()
    {
        CSVRecord lowestHumidityInManyFilesValue = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+lowestHumidityInManyFilesValue.get("Humidity")+" at "+lowestHumidityInManyFilesValue.get("DateUTC"));
    }
    public double averageTemperatureInFile(CSVParser parser)
    {
        double avgTemperature = 0.0;
        int count = 0;
        for(CSVRecord rec : parser)
        {
            double temperature = Double.parseDouble(rec.get("TemperatureF"));
            avgTemperature += temperature;
            count++;
        }
        return avgTemperature/count;
    }
    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        double avg = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average Temperature in file is "+avg);
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value)
    {
        double highTemperatureSum = 0.0;
        int count = 0;
        for(CSVRecord rec : parser)
        {

            int currHumidity = Integer.parseInt(rec.get("Humidity"));
            if(currHumidity>=value)
            {
                highTemperatureSum += Double.parseDouble(rec.get("TemperatureF"));
                count++;
            }
        }


        if(count==0)
            return 0.0;
        else
            return highTemperatureSum/count;
    }
    public void testAverageTemperatureWithHighHumidityInFile()
    {
        int value = 80;
        FileResource fr = new FileResource();
        double averageTempAboveCertainValue = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),value);
        if(averageTempAboveCertainValue > 0.0)
        {   System.out.println("Average Temp when high" +
                    " Humidity is "
                    +averageTempAboveCertainValue);
        }
        else
            System.out.println("No temperatures with that humidity");
    }
    public static void main(String args[])
    {
        ColdTemperatures ct = new ColdTemperatures();
        //ct.testColdestHourInFile();
        ct.testFileWithColdestTemperature();
        //ct.testLowestHumidityInFile();
        //ct.testLowestHumidityInManyFiles();
        //ct.testAverageTemperatureInFile();
        //ct.testAverageTemperatureWithHighHumidityInFile();
    }
}
