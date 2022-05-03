import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Macierz Sąsiedztwa
public class MacierzS extends Graf {

    ListaS toListS() {
        // do roboty
        ListaS listaS = new ListaS();

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

        MacierzS listaS = graf;
        Random random = new Random();
        int size0 = listaS.getMacierz().size();
        int a;
        int b;

        do {
            a = (int) (size0 * random.nextDouble());
            double p = random.nextDouble();
            b = (int) (p * listaS.getMacierz().get(a).size());
        } while (a != b && listaS.getMacierz().get(a).get(b) != 0);
        int c;
        int d;
        do {
            c = (int) (size0 * random.nextDouble());
            double p = random.nextDouble();
            d = (int) (p * listaS.getMacierz().get(c).size());
        } while (c != d && listaS.getMacierz().get(c).get(d) != 0 && a != c);
        int tempo = listaS.getMacierz().get(a).get(b);
        listaS.getMacierz().get(a).set(b, listaS.getMacierz().get(c).get(d));
        listaS.getMacierz().get(b).set(a, listaS.getMacierz().get(c).get(d));

        listaS.getMacierz().get(c).set(d, tempo);
        listaS.getMacierz().get(d).set(c, tempo);
        listaS.wypisz();
        return listaS;
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
