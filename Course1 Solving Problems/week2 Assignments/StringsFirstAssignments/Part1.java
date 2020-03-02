public class Part1
{
    public String findSampleGene(String dna)
    {
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if(startIndex==-1)
            return "";
        int endIndex = dna.indexOf("TAA",startIndex+3);
        if(endIndex == -1)
            return "";
        if((startIndex-endIndex)%3 == 0)
        {
            result = dna.substring(startIndex,endIndex+3);
            return result;
        }
        else
            return "";
    }
    public void testSimpleGene()
    {
        String dna="AAATAGAATAATTAATA";
        System.out.println("The strand is "+dna);
        String gene=findSampleGene(dna);
        System.out.println("The gene is "+gene);

        dna = "ATGATTATTATTGCTGCGCTATGG";
        System.out.println("The strand is "+dna);
        gene = findSampleGene(dna);
        System.out.println("The gene is "+gene);

        dna = "ATATTATTATTGCTGCGCTATGG";
        System.out.println("The strand is "+dna);
        gene = findSampleGene(dna);
        System.out.println("The gene is "+gene);

        dna = "ATGATTATTATTGCTGCGCGGTAAGG";
        System.out.println("The strand is "+dna);
        gene = findSampleGene(dna);
        System.out.println("The gene is "+gene);

        dna = "ATGATTATTATTGCTGCGCTAATGG";
        System.out.println("The strand is "+dna);
        gene = findSampleGene(dna);
        System.out.println("The gene is "+gene);
    }
    public static void main(String args[])
    {
        Part1 pobj=new Part1();
        pobj.testSimpleGene();
    }

}
