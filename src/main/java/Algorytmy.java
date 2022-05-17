import java.util.*;

public class Algorytmy {
    private Algorytmy(){}

    public static int[] znajdźSkładowe(Graf graf){
        int nr=0;
        int[] comp =new int[graf.macierz.size()];
        for(int i=0;i<graf.macierz.size();i++) {
            comp[i]=-1;
        }

        for(int i=0;i<graf.macierz.size();i++) {
            if (comp[i]==-1){
                nr++;
                comp[i]=nr;
                components_R(nr, i, graf, comp);
            }
        }
        return comp;
    }

    private static void components_R(final int nr, final int v, final Graf graf, final int[] comp) {
        for(int i=0;i<graf.macierz.get(v).size();i++)
            if (graf.macierz.get(v).get(i) ==1 && comp[i]==-1) {
                comp[i]=nr;
                components_R(nr, i, graf, comp);
            }
    }

    public static List<Integer> znajdźCyklEulera(Graf graf){
        List<Integer> wierzchołki = new ArrayList<>();
        for(int i=0;i<graf.macierz.size();i++) wierzchołki.add(i+1);

        List<Integer> cykl = new ArrayList<>();
        int w =0;
        cykl.add(1);
        graf.macierz.get(0).set(w, 0);
        graf.macierz.get(w).set(0, 0);
        while(graf.macierz.stream().flatMap(Collection::stream).count() != 1){
            for(int i=0;i<graf.macierz.get(w).size();i++) {
                if (graf.macierz.get(w).get(i) == 1) {
                    graf.macierz.get(i).set(w, 0);
                    graf.macierz.get(w).set(i, 0);
                    int[] składowe = Algorytmy.znajdźSkładowe(graf);
                    if (Arrays.stream(składowe).allMatch(x -> x == 1)) {
                        cykl.add(wierzchołki.get(i));
                        w=i;
                        break;
                    } else if(Arrays.stream(składowe).filter(x -> x == 2).count()==1){
                        graf.macierz.remove(w);
                        for(int j=0;j<graf.macierz.size();j++) graf.macierz.get(j).remove(w);
                        cykl.add(wierzchołki.get(i));
                        wierzchołki.remove(w);
                        if(w>i) w=i;
                        else w=i-1;
                        break;
                    } else {
                        graf.macierz.get(i).set(w, 1);
                        graf.macierz.get(w).set(i, 1);
                    }
                }
            }
        }
        return cykl;
    }

    /* A utility function to check if the vertex v can be
       added at index 'pos'in the Hamiltonian Cycle
       constructed so far (stored in 'path[]') */
    public static boolean wierzcholekMozeBycDodany(int v, Graf graf, List<Integer> cykl, int pos)
    {
        /* Check if this vertex is an adjacent vertex of
           the previously added vertex. */
        if (graf.macierz.get(cykl.get(pos - 1)).get(v) == 0)
            return false;

        /* Check if the vertex has already been included.
           This step can be optimized by creating an array
           of size V */
        for (int i = 0; i < pos; i++)
            if (cykl.get(i) == v)
                return false;

        return true;
    }

    /* A recursive utility function to solve hamiltonian
       cycle problem */
    private static boolean rozwiazCyklHamiltiona(Graf graf, List<Integer> cykl, int pos)
    {
        /* base case: If all vertices are included in
           Hamiltonian Cycle */
        if (pos == graf.macierz.size())
        {
            // And if there is an edge from the last included
            // vertex to the first vertex
            if (graf.macierz.get(cykl.get(pos - 1)).get(cykl.get(0)) == 1) {
                cykl.add(cykl.get(0));
                return true;
            }
            else
                return false;
        }

        // Try different vertices as a next candidate in
        // Hamiltonian Cycle. We don't try for 0 as we
        // included 0 as starting point in hamCycle()
        for (int v = 1; v < graf.macierz.size(); v++)
        {
            /* Check if this vertex can be added to Hamiltonian
               Cycle */
            if (wierzcholekMozeBycDodany(v, graf, cykl, pos))
            {
                cykl.set(pos,v);

                /* recur to construct rest of the path */
                if (rozwiazCyklHamiltiona(graf, cykl, pos + 1) == true)
                    return true;

                /* If adding vertex v doesn't lead to a solution,
                   then remove it */
                cykl.set(pos,-1);
            }
        }

        /* If no vertex can be added to Hamiltonian Cycle
           constructed so far, then return false */
        return false;
    }


    public static List<Integer> znajdźCyklHamiltona(Graf graf)
    {
        List<Integer> cykl = new ArrayList<>();
        for (int i = 0; i < graf.macierz.size(); i++)
            cykl.add(-1);

        cykl.set(0,0);
        if (rozwiazCyklHamiltiona(graf, cykl, 1) == false)
        {
            System.out.println("\nBrak cyklu Hamiltiona!");
            return null;
        }

        for(int i=0;i<cykl.size();i++) cykl.set(i, cykl.get(i)+1);
        return cykl;
    }

    public static MacierzS generujGrafEulerowski(int n) {
        Random random = new Random();
//        //tu trzeba jakies losowanko tego l ale nwm jakie zeby działało :c to co napisalam jest zle
////          na razie dalam przykladowe l=10
        int l = random.nextInt((n - 1) * n / 2+1);
        while(l < n ||l*2.0/n*1.0 % 2 != 0 ) {l = random.nextInt((n - 1) * n / 2+1);
            System.out.println(l + " " + n);}
//        int l = new Random().nextInt(n-1)*2;
//        while (l*2.0/n*1.0 % 2 != 0 || l < n) {
//            System.out.println(l);
//                    l = new Random().nextInt(n-1)*2;
//        }
//        int l = n*2;
        System.out.println(l + " " + n);
        if (n < 3) {
            throw new RuntimeException("Liczba wierzchołków musi być większa niż 2");
        }
        MacierzS macierzS = new GenerujGraf(n, l).macierzS;
        boolean iloscKrawedziParzysta = sprawdźCzyIloscKrawedziParzysta(macierzS);
        while(!iloscKrawedziParzysta || !sprawdźCzySpojny(macierzS)){
            macierzS = MacierzS.randomizuj(macierzS);
            iloscKrawedziParzysta = sprawdźCzyIloscKrawedziParzysta(macierzS);
        }
        return macierzS;
    }

    private static boolean sprawdźCzyIloscKrawedziParzysta(Graf graf){
        for(int i=0;i<graf.macierz.size();i++){
            int count=0;
            for(int j=0;j<graf.macierz.get(i).size();j++){
                if(graf.macierz.get(i).get(j)==1) count++;
                if(graf.macierz.get(i).get(j)==1 && i==j ) return false;
            }
            if(count%2!=0 || count==0) return false;
        }
        return true;
    }


    public static MacierzS GenerujGrafKRegularny(int n, int k) {
        if (1 > k || k > n || (n*k%2 == 1 )) {
            throw new RuntimeException("nie da się utworzć grafu k-regularnego");
        }
        MacierzS macierzS = new GenerujGraf(n, n*k/2).macierzS;
        boolean jestKRegularny = sprawdźCzyKRegularny(macierzS,k);
        while(!jestKRegularny){
            macierzS = MacierzS.randomizuj(macierzS);
            jestKRegularny = sprawdźCzyKRegularny(macierzS,k);
        }
        return macierzS;
    }

    private static boolean sprawdźCzyKRegularny(Graf graf,int k){
        for(int i=0;i<graf.macierz.size();i++){
            int count=0;
            for(int j=0;j<graf.macierz.get(i).size();j++){
                if(graf.macierz.get(i).get(j)==1) count++;
            }
            if(count!=k) return false;
        }
        return true;
    }

    public static MacierzS GenerujGrafSpojny(int n, int l) {
        MacierzS macierzS = new GenerujGraf(n, l).macierzS;
        if (l < n-1) {
            throw new RuntimeException("zamała liczba krawędzi");
        }
        if (l > n*(n-1)) {
            throw new RuntimeException("zaduża liczba krawędzi");
        }
        boolean jestSpojny = sprawdźCzySpojny(macierzS);
        while(!sprawdźCzySpojny(macierzS)){
            macierzS = MacierzS.randomizuj(macierzS);
            jestSpojny = sprawdźCzySpojny(macierzS);
        }
        return macierzS;
    }

    private static boolean sprawdźCzySpojny(Graf graf){
        return Arrays.stream(znajdźSkładowe(graf)).allMatch(x -> x==1);
    }
}
