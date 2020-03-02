import edu.duke.StorageResource;

public class Part2
{

    public float cgRatio(String dna)
    {
        int total = 0;
        total = howMany("C",dna)+howMany("G",dna);
        float length = dna.length();
        return total/length;
    }
    public int countCTG(String dna)
    {
        int ctgcount = howMany("CTG",dna);
        return ctgcount;
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
    public static void main(String args[])
    {
        Part2 pobj2 = new Part2();
        float ratio=pobj2.cgRatio("ATGCCATAG");
        System.out.println("CG ratio is "+ratio);

        int countctg = pobj2.countCTG("ATCGCTGATCGCTGTAACTGGAA");
        System.out.println("CTG count is "+countctg);
    }


}
