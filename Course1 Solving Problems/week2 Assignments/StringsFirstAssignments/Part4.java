import edu.duke.*;
public class Part4
{
    void printURL()
    {
        URLResource urlobj = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String line : urlobj.lines())
        {
            //System.out.println(line);
            String str=line;
            if(str.contains("YouTube")||str.contains("youtube")||str.contains("YOUTUBE"))
            {

                int startIndex=str.indexOf("href=");
                int endIndex=str.indexOf("\"",startIndex+6);
                String result = str.substring(startIndex+6,endIndex);
                System.out.println(result);
            }
        }
    }
    public  static void  main(String args[])
    {
        Part4 pobj4 = new Part4();
        pobj4.printURL();
    }
}
