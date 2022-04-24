import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Graf {

    ArrayList<ArrayList<Integer>> macierz = new ArrayList<ArrayList<Integer>>();

    void fill(File file){

        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                ArrayList<Integer> linia = new ArrayList<>();
                String s = scanner.nextLine();
                Scanner scanner1 = new Scanner(s);
                while(scanner1.hasNextInt()){
                    linia.add(scanner1.nextInt());
                }
                macierz.add(new ArrayList<>(linia));
            }
        } catch (FileNotFoundException e) {
            System.out.println("nie udało się odczytac pliku");
        }
    }
    void uzupelnij(int[][]tab){
        for(int i=0; i< tab.length;i++)
        {
            macierz.add(new ArrayList<>());
            for(int j=0; j<tab[i].length; j++)
                macierz.get(i).add(tab[i][j]);
        }
    }
    void wypisz(){

        for(var a :macierz){
            System.out.println(a);
        }
    }

    public ArrayList<ArrayList<Integer>> getMacierz() {
        return macierz;
    }
}
