import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Macierz Sąsiedztwa
public class MacierzS extends Graf {

    Graf toListS() {
        // do roboty
        Graf listaS = new Graf();

        for (var linia : macierz) {
            ArrayList<Integer> tempo = new ArrayList<>();
            for (int i = 0; i < linia.size(); i++) {
                if (!linia.get(i).equals(0))
                    tempo.add(i);
            }
            listaS.getMacierz().add(new ArrayList<>(tempo));
        }

        return listaS;
    }

    /*
    ListaS toListS(){
        // do roboty ?
        ListaS listaS = new ListaS();

        for(int i=0; i<macierz.size();i++){
            ArrayList<Integer> tempo = new ArrayList<>();
            for(int j=0 ; j<macierz.get(i).size(); j++){
                if(!macierz.get(i).get(j).equals(0))
                    tempo.add(i);
            }
            listaS.getMacierz().add(new ArrayList<>(tempo));
        }

        return listaS;
    }
     */
    static MacierzS randomizuj(MacierzS graf) {

        Random random = new Random();
        int size0 = graf.getMacierz().size();
        int a;
        int b;

        do {
            a = (int) (size0 * random.nextDouble());
            double p = random.nextDouble();
            b = (int) (p * graf.getMacierz().get(a).size());
        } while (a != b && graf.getMacierz().get(a).get(b) != 0);
        int c;
        int d;
        do {
            c = (int) (size0 * random.nextDouble());
            double p = random.nextDouble();
            d = (int) (p * graf.getMacierz().get(c).size());
        } while (c != d && graf.getMacierz().get(c).get(d) != 0 && a != c);
        int tempo = graf.getMacierz().get(a).get(b);
        graf.getMacierz().get(a).set(b, graf.getMacierz().get(c).get(d));
        graf.getMacierz().get(b).set(a, graf.getMacierz().get(c).get(d));

        graf.getMacierz().get(c).set(d, tempo);
        graf.getMacierz().get(d).set(c, tempo);
        graf.wypisz();
        return graf;
    }

    MacierzI toMacierzI() {
        MacierzI macierzI = new MacierzI();


        int edgeNumber = 0;
        ArrayList<ArrayList<Integer>> tempo = new ArrayList<>();

        for (int i = 0; i < macierz.size(); i++) {
            for (int j = 0; j <= i; j++) {

                if (macierz.get(i).get(j) > 0) {
                    System.out.println(edgeNumber);
                    macierzI.getMacierz().add(new ArrayList<>());
                    for (int k = 0; k < macierz.size(); k++)
                        macierzI.getMacierz().get(edgeNumber).add(0);
                    macierzI.getMacierz().get(edgeNumber).set(i, 1);
                    macierzI.getMacierz().get(edgeNumber).set(j, 1);
                    ++edgeNumber;
                }

            }
        }

        return macierzI;

    }
}
