public class Part2
{
    public String findSampleGene(String dna,String startCodon,String endCodon)
    {
        char firstCharacter = dna.charAt(0);
        if(Character.isLowerCase(firstCharacter))
        {
            startCodon=startCodon.toLowerCase();
            endCodon=endCodon.toLowerCase();
        }
        String result = "";
        int startIndex = dna.indexOf(startCodon);
        if(startIndex==-1)
            return "";
        int endIndex = dna.indexOf(endCodon,startIndex+3);
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
        String startCodon="ATG";
        String endCodon="TAA";
        String gene="";

        System.out.println("The strand is "+dna);
        gene=findSampleGene(dna,startCodon,endCodon);
        System.out.println("The gene is "+gene);

        dna = "ATGATTATTATTGCTGCGCTATGG";
        System.out.println("The strand is "+dna);
        gene = findSampleGene(dna,startCodon,endCodon);
        System.out.println("The gene is "+gene);


        dna = "ATATTATTATTGCTGCGCTATGG";
        System.out.println("The strand is "+dna);
        gene = findSampleGene(dna,startCodon,endCodon);
        System.out.println("The gene is "+gene);

        dna = "ATGATTATTATTGCTGCGCGGTAAGG";
        System.out.println("The strand is "+dna);
        gene = findSampleGene(dna,startCodon,endCodon);
        System.out.println("The gene is "+gene);

        //dna = "ATGATTATTATTGCTGCGCGGTAATGG";

        dna = "atgattattattgctgcgcggtaatgg";
        System.out.println("\nThe strand is "+dna);
        gene = findSampleGene(dna,startCodon,endCodon);
        System.out.println("The gene is "+gene);
    }
    public static void main(String args[])
    {
        Part2 pobj=new Part2();
        pobj.testSimpleGene();
    }
}
