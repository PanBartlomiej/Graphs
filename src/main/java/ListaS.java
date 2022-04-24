import java.util.ArrayList;

//Lista sąsiędztwa
public class ListaS extends Graf {

    MacierzS toMacierzS(){
    MacierzS macierzS = new MacierzS() ;

    for(int i=0; i<macierz.size(); i++) {
        macierzS.getMacierz().add(new ArrayList<>());
        for (int j = 0; j < macierz.size(); j++)
           if(macierz.get(i).contains(j))
            macierzS.getMacierz().get(i).add(1);
           else macierzS.getMacierz().get(i).add(0);
    }
    return macierzS;
    }

}
