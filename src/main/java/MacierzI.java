import java.util.ArrayList;

// macierz Inceydencji
public class MacierzI extends Graf {


    public static MacierzS toMacierzS(Graf graf)
    {
        MacierzS macierzS = new MacierzS();
        int edges = graf.macierz.size();
        assert(edges > 0);

        int vertices = graf.macierz.get(0).size();
        assert(vertices > 0);

        for(int i=0;i<vertices; i++)
        {
            macierzS.getMacierz().add(new ArrayList<>());
            for(int j=0; j<vertices; j++)
                macierzS.getMacierz().get(i).add(0);
        }
        for (ArrayList<Integer> integers : graf.macierz) {
            int a = -1, b = -1, vertex = 0;
            for (; vertex < vertices && a == -1; ++vertex) {
                if (integers.get(vertex) != 0) a = vertex;
            }
            for (; vertex < vertices && b == -1; ++vertex) {
                if (integers.get(vertex) != 0) b = vertex;
            }
            if (b == -1) b = a;
            macierzS.getMacierz().get(a).set(b, 1);
            macierzS.getMacierz().get(b).set(a, 1);
        }

        return macierzS;
    }

    public static Graf toListS(Graf graf){
     return MacierzS.toListS(MacierzI.toMacierzS(graf));
    }

}
