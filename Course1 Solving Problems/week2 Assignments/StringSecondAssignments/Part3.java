public class Part3
{
    public String findGene(String dna)
    {
        int startIndex = dna.indexOf("ATG");
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
    void testFindStopCodon()
    {
        String dna = "AAAATGCTATAGTAAGCTATAGGC";
        int startIndex = dna.indexOf("ATG");
        String stopCodon = "TAA";
        int testcase = findStopCodon(dna,startIndex,stopCodon);
        if(testcase!=-1)
        {
            System.out.println("The string has genes");
        }

        dna = "ATGCAATGCAATAGGTTC";
        stopCodon = "TAG";
        testcase = findStopCodon(dna,startIndex,stopCodon);
        if(testcase!=-1)
            System.out.println("The string has gene");
        else
            System.out.println("The string do not has gene");
    }
    public void testFindGene()
    {
        String dna = "";
        String gene = "";

        dna = "ATATTAGAGCTTATTTGATTGAATTTGAACTTA";
        System.out.println("The strand is "+dna);
        gene = findGene(dna);
        System.out.println("The gene is: "+gene);
        printAllGenes(dna);

        dna = "AATATGCCTGTATAATGAAATGATTTTTTAGTATTA";
        System.out.println("\nThe strand is "+dna);
        gene = findGene(dna);
        System.out.println("The gene is: "+gene);
        printAllGenes(dna);

        dna = "AAAAATGTCTATATAGTATATGTTAAATTAGAAGCC";
        System.out.println("\nThe strand is "+dna);
        gene = findGene(dna);
        System.out.println("The gene is "+gene);
        printAllGenes(dna);

        dna = "AAAAATGTCTATAATAGTATATTTAAATTTGAAGCC";
        System.out.println("\nThe strand is "+dna);
        gene = findGene(dna);
        System.out.println("The gene is "+gene);
        printAllGenes(dna);

    }
    public void printAllGenes(String dna)
    {
        int startIndex = 0;
        System.out.println("All genes in the strand are ");
        while(true)
        {
            String currentGene = findGene(dna);
            if(currentGene.isEmpty())
                break;
            System.out.println(currentGene);
            dna = dna.substring(dna.indexOf(currentGene)+currentGene.length());
        }
    }
    public int countGenes(String dna)
    {
        int startIndex = 0;
        int geneCount=0;
        while(true)
        {
            String currentGene = findGene(dna);
            if(currentGene.isEmpty())
                break;
            geneCount++;
            dna = dna.substring(dna.indexOf(currentGene)+currentGene.length());
        }
        return geneCount;
    }
    public void testCountGenes()
    {
        int genecount = countGenes("ATGTAAGATGCCCTAGT");
        System.out.println("The number of genes in the strand is: "+genecount);
    }
    public static void main(String args[])
    {
        Part3 pobj3 = new Part3();
        pobj3.testCountGenes();

    }
}
