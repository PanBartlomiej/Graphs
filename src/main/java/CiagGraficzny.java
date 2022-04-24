import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class CiagGraficzny {

    static boolean czyGraficzny(Integer [] tab){

       tab = Arrays.stream(tab).sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
       int a;
       int iterator=0;


       while(true) {
            for(int i=0; i<tab.length; i++)
            {
                if(tab[i]!=0) break;
                if(tab[tab.length-1]==0) return true;
            }
            if(tab[0]<0 || tab[0]>= tab.length )
                return false;
            for(int i=1; i< tab.length; i++)
                if(tab[i]<0) return false;
            for(int i=1; i <= tab[0]; i++)
                tab[i]--;
            tab[0]=0;
           tab = Arrays.stream(tab).sorted(Comparator.reverseOrder()).toArray(Integer[]::new);

       }
    }
    static MacierzS toMacierzS(Integer[] tab){
        MacierzS macierzS = new MacierzS();
        int size = tab.length;
        tab = Arrays.stream(tab).sorted(Comparator.reverseOrder()).toArray(Integer[]::new);

        for(int i=0; i<size; i++)
        {

            tab = Arrays.stream(tab).sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
            macierzS.getMacierz().add(new ArrayList<>());
            for(int j=0;j<size;j++)
                macierzS.getMacierz().get(i).add(0);
        }
        tab = Arrays.stream(tab).sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
        for(int i=0; i<size; i++)
        {
            tab = Arrays.stream(tab).sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
            for(int j=i+1; j<tab[0]+i; j++)
            {
                macierzS.getMacierz().get(i).set(j, 1);
                macierzS.getMacierz().get(j).set(i, 1);
            }
            for(int j=1; j<tab[0]; j++)
                tab[j]--;
            tab[0]=0;

        }
        macierzS.wypisz();
            return macierzS;
    }

}
