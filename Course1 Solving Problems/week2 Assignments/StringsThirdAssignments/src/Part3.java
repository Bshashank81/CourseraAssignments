import edu.duke.FileResource;
import edu.duke.StorageResource;

import java.io.File;

public class Part3
{
    public String findGene(String dna,int where)
    {
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex == -1)
            return "";
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");

        int minIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex))
            minIndex = tgaIndex;
        else
            minIndex = taaIndex;


        if(minIndex==-1 || (tagIndex != -1 && tagIndex < minIndex))
            minIndex = tagIndex;

        if(minIndex == -1)
            return "";

        return dna.substring(startIndex,minIndex+3);
    }
    public int findStopCodon(String dnaStr,int startIndex,String stopCodon)
    {
        int currIndex = dnaStr.indexOf(stopCodon,startIndex+3);
        while(currIndex!=-1)
        {
            int diff = currIndex - startIndex;
            if(diff%3==0)
                return currIndex;
            else
                currIndex = dnaStr.indexOf(stopCodon,currIndex+1);
        }
        return -1;
    }
    public StorageResource getAllGenes(String dna)
    {
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        System.out.println("All genes in the strand are ");
        while(true)
        {
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty())
                break;
            geneList.add(currentGene);
            //dna = dna.substring(dna.indexOf(currentGene)+currentGene.length());
            startIndex=dna.indexOf(currentGene,startIndex)+currentGene.length();
            System.out.println("gene is "+currentGene);
        }
        return geneList;
    }
    public float cgRatio(String dna)
    {
        int total = 0;
        total = howMany("C",dna)+howMany("G",dna);
        float length = dna.length();
        return total/length;
    }
    public int howMany(String stringa,String stringb)
    {
        int count=1;
        int iteratingNum=stringb.indexOf(stringa);
        int startIndex=iteratingNum;
        if(iteratingNum==-1)
            return 0;
        while(startIndex<stringb.length()&&(startIndex!=-1))
        {
            startIndex=stringb.indexOf(stringa,iteratingNum+stringa.length());
            iteratingNum=startIndex;
            if((startIndex!=-1))
                count++;
        }
        return count;
    }
    public void processGenes(StorageResource sr)
    {

        int count = 0;
        int max = 0;
        System.out.println("Strings with length>9 are :");
        for(String s : sr.data())
        {
            if(s.length()>9)
            {
                System.out.println("   "+s);
                count++;
            }

            if(s.length()>max)
            {
                max=s.length();
            }
        }
        System.out.println("No of strings with length>9: "+count);
        System.out.println("Strings with cgratio>0.35 ");
        for(String s : sr.data())
        {
            float cg_ratio = cgRatio(s);
            if(cg_ratio>0.35)
                System.out.println("   "+s);
        }
        System.out.println("the length of the longest gene in sr "+max);



    }
    public void testProcessGenes()
    {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();

        StorageResource genes = new StorageResource();

        //dna = "AAATGCGGAGGTAAGACTATGGAATGGTGACCCATGGCCTAG";
        genes = getAllGenes(dna);
        processGenes(genes);
        System.out.println("\n");

        dna = "ATGGCGTAATTGATGGGCTGAAAATGAAATAA";
        genes = getAllGenes(dna);
        processGenes(genes);
        System.out.println("\n");

        dna = "ATGAAATAAGCATGTTTTTATGACCATGTAAGGATGCCTCTGTTGTGA";
        genes = getAllGenes(dna);
        processGenes(genes);
        System.out.println("\n");
    }
    public static void main(String args[])
    {
        Part3 pobj3 = new Part3();
        pobj3.testProcessGenes();
    }


}
