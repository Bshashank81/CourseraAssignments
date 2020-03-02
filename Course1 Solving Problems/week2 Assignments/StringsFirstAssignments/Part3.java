public class Part3
{
    public boolean twoOccurrences(String stringa,String stringb)
    {
        int count=1;
        int iteratingNum=stringb.indexOf(stringa);
        int startIndex=iteratingNum;
        if(iteratingNum==-1)
            return false;
        while(startIndex<stringb.length())
        {
            startIndex=stringb.indexOf(stringa,iteratingNum+stringa.length());
            iteratingNum=startIndex;

            if((startIndex==-1))
                break;
            count=count+1;

        }
        if(count>=2)
            return true;
        else
            return false;
    }
    public String lastPart(String stringa,String stringb)
    {
        int firstOccurrence = stringb.indexOf(stringa);
        if(firstOccurrence==-1)
            return stringb;
        return stringb.substring(firstOccurrence+stringa.length());
    }
    void testing()
    {
        String stringa,stringb;
        stringa="by";
        stringb="A story by Abby Long";
        boolean res;

        System.out.println("Stringa is: "+stringa+", Stringb is: "+stringb);
        res=twoOccurrences(stringa,stringb);
        System.out.println("The result is "+res);

        stringa="a";
        stringb="banana";
        System.out.println("Stringa is "+stringa+" Stringb is "+stringb);
        res=twoOccurrences(stringa,stringb);
        System.out.println("The result is "+res);

        stringa="atg";
        stringb="ctgtatgta";
        System.out.println("Stringa is "+stringa+" Stringb is "+stringb);
        res=twoOccurrences(stringa,stringb);
        System.out.println("The result is "+res);

        stringa="bus";
        stringb="car";
        System.out.println("Stringa is "+stringa+" Stringb is "+stringb);
        res=twoOccurrences(stringa,stringb);
        System.out.println("The result is "+res);

        String result;
        stringa="an";
        stringb="banana";
        System.out.println("Stringa is "+stringa+", Stringb is "+stringb);
        result=lastPart(stringa,stringb);
        System.out.println("The part of the string after "+stringa+" is "+result);

        stringa="zoo";
        stringb="forest";
        System.out.println("Stringa is "+stringa+", Stringb is "+stringb);
        result=lastPart(stringa,stringb);
        System.out.println("The part of the string after "+stringa+" is "+result);
    }

    public static void main(String args[])
    {
        Part3 pobj3=new Part3();
        pobj3.testing();
    }
}
