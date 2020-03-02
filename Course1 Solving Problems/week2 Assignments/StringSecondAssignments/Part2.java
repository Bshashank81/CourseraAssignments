public class Part2
{
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
    void testHowMany()
    {
        String stringa,stringb;
        stringa="by";
        stringb="A story by Abby Long";
        int res;

        System.out.println("Stringa is: "+stringa+", Stringb is: "+stringb);
        res=howMany(stringa,stringb);
        System.out.println("The result is "+res);

        stringa="a";
        stringb="banana";
        System.out.println("Stringa is "+stringa+" Stringb is "+stringb);
        res=howMany(stringa,stringb);
        System.out.println("The result is "+res);

        stringa="atg";
        stringb="ctgtatgta";
        System.out.println("Stringa is "+stringa+" Stringb is "+stringb);
        res=howMany(stringa,stringb);
        System.out.println("The result is "+res);

        stringa="bus";
        stringb="car";
        System.out.println("Stringa is "+stringa+" Stringb is "+stringb);
        res=howMany(stringa,stringb);
        System.out.println("The result is "+res);

        stringa="GAA";
        stringb="ATGAACGAATTGAATC";
        System.out.println("Stringa is "+stringa+" Stringb is "+stringb);
        res=howMany(stringa,stringb);
        System.out.println("The result is "+res);

        stringa="AA";
        stringb="ATAAAA";
        System.out.println("Stringa is "+stringa+" Stringb is "+stringb);
        res=howMany(stringa,stringb);
        System.out.println("The result is "+res);

    }
    public static void main(String args[])
    {
        Part2 pobj2 = new Part2();
        pobj2.testHowMany();
    }

    
}
