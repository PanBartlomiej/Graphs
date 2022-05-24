import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args){

        MyFrame frame = new MyFrame();

        frame.graf = new GenerujGraf(10,2).macierzS ;

        System.out.println("Macierz Sąsiedztwa");
        int tab [][]= {{0, 1, 1, 1, 1, 1, 1},{1, 0, 1, 0, 0, 1, 1},
        {1, 1, 0, 1, 1, 0, 0},
        {1, 0, 1, 0, 0, 0, 0},
        {1, 0, 1, 0, 0, 0, 0},
        {1, 1, 0, 0, 0, 0, 0},
        {1, 1, 0, 0, 0, 0, 0}};
        MacierzS macierz = new MacierzS();
        macierz.uzupelnij(tab);
        macierz.wypisz();
        System.out.println("Lista Sąsiedztwa");
        macierz.toListS().wypisz();
        System.out.println("Macierz Incydencji");
        macierz.toMacierzI().wypisz();
        assert (frame.graf != null);


    /*    Integer [] tab = {4, 2, 2, 3, 2, 1, 4, 2, 2, 2, 2};

        tab = Arrays.stream(tab).sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
        System.out.println(" czy jest graficzny" +Arrays.toString(tab)+ CiagGraficzny.czyGraficzny(tab));
        MacierzS macierzS = new MacierzS();
*/



    }
}
