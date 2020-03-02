import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class ExportData
{
    public void countryInfo(CSVParser parser,String country)
    {
        String countryname = "";
        String result = "";
        for(CSVRecord record : parser)
        {
            countryname = record.get("Country");
            if(countryname.contains(country))
            {
                String exports = record.get("Exports");
                String standard = record.get("Value (dollars)");
                result = countryname+":"+exports+":"+standard;
            }
            else
                result="Not Found";
            System.out.println(result);
        }

    }
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2)
    {
        for(CSVRecord record : parser)
        {
            String items = record.get("Exports");
            if(items.contains(exportItem1)&&items.contains(exportItem2))
            {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public int numberOfExporters(CSVParser parser,String exportItem)
    {
        int count=0;
        for(CSVRecord record : parser)
        {
            String item = record.get("Exports");
            if(item.contains(exportItem))
            {
                count++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser,String amount)
    {
        for(CSVRecord record : parser)
        {
            String curramt = record.get("Value (dollars)");
            if(curramt.length()>amount.length())
            {
                String country = record.get("Country");
                System.out.println(country+" "+curramt);
            }
        }
    }
    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        parser = fr.getCSVParser();
        System.out.println("The country information is ");
        countryInfo(parser,"Nauru");

        parser = fr.getCSVParser();
        System.out.println("The countries with both the exportitems are:");
        listExportersTwoProducts(parser,"gold","diamonds");

        parser = fr.getCSVParser();
        int res = numberOfExporters(parser,"gold");
        System.out.println("The number of countries that export particular item "+res);

        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }
    public static void main(String args[])
    {
        ExportData ed = new ExportData();
        ed.tester();
    }
}
