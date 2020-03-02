/**
 * Print out the names for which 100 or fewer babies were born in a chosen CSV file of baby name data.
 *
 * @author Duke Software Team
 */
import edu.duke.*;
import org.apache.commons.csv.*;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;

class BabyBirths
{
    public void printNames ()
    {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100)
            {
                System.out.println("Name " + rec.get(0) +
                        " Gender " + rec.get(1) +
                        " Num Born " + rec.get(2));
            }
        }
    }
    public void totalBirths(FileResource fr)
    {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalGirlNames = 0;
        int totalBoyNames = 0;
        for(CSVRecord rec : fr.getCSVParser(false))
        {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M"))
            {
                totalBoys += numBorn;
                totalBoyNames++;
            }
            else
            {
                totalGirls += numBorn;
                totalGirlNames++;
            }

        }
        System.out.println("total girl names = "+totalGirlNames);
        System.out.println("total boy names = "+totalBoyNames);
        System.out.println("total births = "+totalBirths);
        System.out.println("total boy births = "+totalBoys);
        System.out.println("total girl births = "+totalGirls);
    }
    public void testTotalBirths()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public int getRank(int year,String name,String gender)
    {
        FileResource fr = new FileResource();
        int maleCount = 0;
        int femaleCount = 0;
        int femalerank=0;
        int malerank=0;
        int rank=0;
        for(CSVRecord rec : fr.getCSVParser(false))
        {
            if(rec.get(1).equals("M"))
                maleCount++;
            else
                femaleCount++;
            if((gender.equals("M"))&&(rec.get(0).equals(name))&&(rec.get(1).equals("M")))
            {
                rank = maleCount;
                break;
            }
            else if((gender.equals("F"))&&(rec.get(0).equals(name))&&(rec.get(1).equals("F")))
            {
                rank = femaleCount;
                break;
            }
            else
                rank = -1;
        }
        return rank;
    }
    public String getName(int year,int rank,String gender)
    {
        FileResource fr = new FileResource();
        String name=null;
        int maleRank = rank;
        int femaleRank = rank;
        for(CSVRecord rec : fr.getCSVParser(false))
        {

            if(gender.equals("M")&&(rec.get(1).equals("M")))
            {
                maleRank--;
                if(maleRank==0)
                {
                    name = rec.get(0);
                    break;
                }
            }
            else if(gender.equals("F")&&(rec.get(1).equals("F")))
            {
                femaleRank--;
                if(femaleRank==0)
                {
                    name = rec.get(0);
                    break;
                }

            }
            else
                name = "NO NAME";

        }
        return name;
    }
    public void whatIsNameInYear(String name,int year,int newYear,String gender)
    {
        int rank = getRank(year,name,gender);
        String newname = getName(newYear,rank,gender);
        System.out.println(name+" born in "+year+
                " would be "+newname+" if she was born in "+newYear+".");
    }

    public int yearOfHighestRank(String name,String gender)
    {
        int highest = 1000;
        String filename = null;
        int highestYear=0;
        DirectoryResource dr = new DirectoryResource();
        int year=1880;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            //filename = f.getName();
            //String yr = filename.substring(3,7);

            int rank = 0;
            int maleCount = 0;
            int femaleCount = 0;
            int femalerank=0;
            int malerank=0;
            for(CSVRecord rec : fr.getCSVParser(false))
            {
                if(rec.get(1).equals("M"))
                    maleCount++;
                else
                    femaleCount++;
                if((gender.equals("M"))&&(rec.get(0).equals(name))&&(rec.get(1).equals("M")))
                {
                    rank = maleCount;
                    break;
                }
                else if((gender.equals("F"))&&(rec.get(0).equals(name))&&(rec.get(1).equals("F")))
                {
                    rank = femaleCount;
                    break;
                }
                else
                    rank = -1;
            }
            if(rank == -1)
                return -1;
            if((rank<highest)&&(rank!=-1))
            {
                highest = rank;
                highestYear = year;
            }
            year++;


        }
        return highestYear;
    }
    public double getAverageRank(String name,String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        double avg = 0;
        int count=0;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            String file = f.getName();
            file= file.substring(3,7);
            int year = Integer.parseInt(file);
            int rank = getRank(year,name,gender);
            avg = avg + rank;
            count++;
        }
        avg = avg/count;
        return avg;
    }
    public int getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        int rank = getRank(year,name,gender);
        FileResource fr = new FileResource();
        int totalBirths = 0;
        for(CSVRecord rec : fr.getCSVParser())
        {
            if(rec.get(1).equals(gender))
            {
                int maleCount=0;
                int femaleCount = 0;
                int tempRank = 0;
                for(CSVRecord rec1 : fr.getCSVParser(false))
                {
                    if(rec1.get(1).equals("M"))
                        maleCount++;
                    else
                        femaleCount++;
                    if((gender.equals("M"))&&(rec1.get(0).equals(name))&&(rec.get(1).equals("M")))
                    {
                        tempRank = maleCount;
                        break;
                    }
                    else if((gender.equals("F"))&&(rec1.get(0).equals(name))&&(rec.get(1).equals("F")))
                    {
                        tempRank = femaleCount;
                        break;
                    }
                    else
                        tempRank = -1;
                }
                int currRank = tempRank;
                if(currRank<rank)
                {
                    totalBirths += Integer.parseInt(rec.get(2));
                }
            }
        }
        return totalBirths;
    }
    public static void main(String args[])
    {
        BabyBirths bbobj = new BabyBirths();
        bbobj.printNames();
        bbobj.testTotalBirths();
        int rank = bbobj.getRank(1990,"Emily","F");
        System.out.println("The rank is "+rank);
        String name = bbobj.getName(1982,450,"M");
        System.out.println("Name of the person with "+
                "given rank "+name);

        bbobj.whatIsNameInYear("Owen",1974,2014,"M");
        int highestRankIsInYear = bbobj.yearOfHighestRank("Genevieve","F");
        System.out.println("Highest rank is in year "+highestRankIsInYear);
        double averageOfRanks = bbobj.getAverageRank("Susan","F");
        System.out.println("The average of the ranks is "+averageOfRanks);
        int totalBirths = bbobj.getTotalBirthsRankedHigher(1990,"Emily","F");
        System.out.println("Total births: "+totalBirths);
    }
}
