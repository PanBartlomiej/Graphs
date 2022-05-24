import java.util.Random;

public class GenerujGraf {
    MacierzS macierzS = new MacierzS();
    GenerujGraf(int n, int l)
    {

        if(l>(n-1)*n/2.0)
            throw new RuntimeException("Za dużo");
        double p = ((double)l/((double)(n*(n-1))/2.0));
        int[][] tab = new int[n][n];
        for(int i=0; i<n; i++)
            tab[i]= new int[n];
        Random r = new Random();
        int liczbaKrawedzi = 0;
do {
    liczbaKrawedzi=0;
    for (int i = 0; i < n; i++)
        for (int j = i; j < n; j++)
            if (r.nextDouble() < p && i!=j) {
                tab[i][j] = 1;
                tab[j][i] = 1;
            }
            else {
                tab[i][j] = 0;
                tab[j][i] =0;
            }
            //ma być liczba krawedzi koniecznie = l
    for (int i = 0; i < n; i++)
        for (int j = i; j < n; j++)
            if (tab[i][j] == 1) liczbaKrawedzi++;

}while(liczbaKrawedzi != l);

macierzS.wypisz();
        macierzS.uzupelnij(tab);
    }

    GenerujGraf(int n, double p)
    {
        int[][] tab = new int[n][n];
        for(int i=0; i<n; i++)
            tab[i]= new int[n];
        Random r = new Random();

            for (int i = 0; i < n; i++)
                for (int j = i+1; j < n; j++)
                    if (r.nextDouble() < p ) {
                        tab[i][j] = 1;
                        tab[j][i] = 1;
                    } else {
                        tab[i][j] = 0;
                        tab[j][i] =0;
                    }
        macierzS.uzupelnij(tab);
    }

}
