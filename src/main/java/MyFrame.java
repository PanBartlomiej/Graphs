import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MyFrame extends JFrame implements ActionListener {

    Button loadGraf = new Button("Wczytaj Graf");
    Button wypiszGraf = new Button("Wypisz Graf");
    Button rysujGraf = new Button("Rysuj Graf");
    Button losujGraf = new Button("Losuj Graf");
    Button wczytajCiag = new Button("Wczytaj CiagGraf");
    Button randomizujGraf = new Button("Randomizuj Graf");
    Button generujGrafEulerowski = new Button("generujGrafEulerowski");
    Button GenerujGrafKRegularny = new Button("GenerujGrafKRegularny");
    Button GenerujGrafSpojny = new Button("GenerujGrafSpojny");
    Button znajdźCyklHamiltona = new Button("znajdźCyklHamiltona");
    Button znajdźCyklEulera = new Button("znajdźCyklEulera");
    Button znajdźSkładowe = new Button("znajdźSkładowe");
    JComboBox<String> comboBox = new JComboBox<>();
    JTextField liczbaKrawedzi;
    JTextField pKrawedzi;
    JTextField ciagGraficzny;
    MyPanel panel = new MyPanel();
    Graf graf ;
    FileDialog fileDialog ;
    MyFrame(){
        this.setLayout(null);
        this.setSize(950, 700);
        this.setLocation(400, 20);
        this.setTitle("Grafy ~ Projekty");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(30, 80, 30));
    //dodanie tytułu
        JLabel tytul = new JLabel("Manager Grafów");
        tytul.setForeground(new Color(229, 184, 1));
        tytul.setBounds(300, 10, 300, 30);
        tytul.setFont(new Font("Arial", Font.PLAIN, 30));
        this.add(tytul);
        //dodanie panela
        panel.setBackground(Color.WHITE);
        panel.setBounds(400, 100,500,500);
        panel.setVisible(true);
        this.add(panel);

        loadGraf.setBounds(20,100,100,20);
        loadGraf.addActionListener(this);
        this.add(loadGraf);

        comboBox.setBounds(125,100,122,20);
        comboBox.addItem("Macierz sąsiedztwa");
        comboBox.addItem("Lista sąsiedztwa");
        comboBox.addItem("Macierz incydencji");
        comboBox.setVisible(true);
        this.add(comboBox);
        wypiszGraf.setBounds(20,125,100,20);
        wypiszGraf.addActionListener(this);
        this.add(wypiszGraf);

        rysujGraf.setBounds(20,150,100,20);
        rysujGraf.addActionListener(this);
        this.add(rysujGraf);

        losujGraf.setBounds(20,225,100,20);
        losujGraf.addActionListener(this);
        this.add(losujGraf);

        liczbaKrawedzi = new JTextField("n");
        liczbaKrawedzi.setBounds(20,175,120,20);
        liczbaKrawedzi.setVisible(true);
        liczbaKrawedzi.setBackground(Color.WHITE);
        this.add(liczbaKrawedzi);
        pKrawedzi = new JTextField("p/l");
        pKrawedzi.setBounds(20,200,120,20);
        pKrawedzi.setVisible(true);
        this.add(pKrawedzi);

        ciagGraficzny = new JTextField("wpisz ciag");
        ciagGraficzny.setBounds(20,250,100,20);
        ciagGraficzny.setVisible(true);
        this.add(ciagGraficzny);

        wczytajCiag.setBounds(20,275,100,20);
        wczytajCiag.addActionListener(this);
        this.add(wczytajCiag);

        randomizujGraf.setBounds(20,300,100,20);
        randomizujGraf.addActionListener(this);
        this.add(randomizujGraf);

        generujGrafEulerowski.setBounds(20,325,140,20);
        generujGrafEulerowski.addActionListener(this);
        this.add(generujGrafEulerowski);

        GenerujGrafKRegularny.setBounds(20,350,140,20);
        GenerujGrafKRegularny.addActionListener(this);
        this.add(GenerujGrafKRegularny);

        GenerujGrafSpojny.setBounds(20,375,140,20);
        GenerujGrafSpojny.addActionListener(this);
        this.add(GenerujGrafSpojny);

        znajdźCyklHamiltona.setBounds(20,400,140,20);
        znajdźCyklHamiltona.addActionListener(this);
        this.add(znajdźCyklHamiltona);

        znajdźCyklEulera.setBounds(20,425,140,20);
        znajdźCyklEulera.addActionListener(this);
        this.add(znajdźCyklEulera);

        znajdźSkładowe.setBounds(20,450,140,20);
        znajdźSkładowe.addActionListener(this);
        this.add(znajdźSkładowe);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loadGraf){
            fileDialog = new FileDialog(this, "Choose destination", FileDialog.LOAD);


            fileDialog.setVisible(true);
            MacierzS m = null;

            if(comboBox.getSelectedItem().equals("Macierz sąsiedztwa")) m= new MacierzS();
            else if(comboBox.getSelectedItem().equals("Lista sąsiedztwa")) m= new MacierzS();
            else if(comboBox.getSelectedItem().equals("Macierz incydencji")) m= new MacierzS();

            m.fill(fileDialog.getFiles()[0]);
            graf= m;
        }
        if(e.getSource() == wypiszGraf)
        {
            panel.clean();
            Graphics2D g =(Graphics2D) panel.getGraphics();
            ArrayList<ArrayList<Integer>> macierz= graf.getMacierz();
            int y=20;
            for(var line :  macierz)
                g.drawString(line.toString(),20, y += g.getFontMetrics().getHeight());
        }
        if(e.getSource() == rysujGraf){
            panel.drawGraf(graf);
        }
        if(e.getSource() == losujGraf)
        {
            int n = Integer.parseInt(liczbaKrawedzi.getText());
            double p =Double.parseDouble(pKrawedzi.getText());
            if(p<=1)
            graf= (new GenerujGraf(n,p)).macierzS;
            else
                graf= (new GenerujGraf(n,(int)p)).macierzS;
            panel.drawGraf(graf);
        }

        if(e.getSource() == wczytajCiag){
            Scanner scanner = new Scanner(ciagGraficzny.getText());
            ArrayList<Integer> tab =new ArrayList<>();
            while(scanner.hasNextInt())
            tab.add(scanner.nextInt());
            Integer [] tempo = tab.toArray(Integer[]::new);
            MacierzS macierzS;
            if(CiagGraficzny.czyGraficzny(tempo)) {
                macierzS = CiagGraficzny.toMacierzS(tempo);
                panel.drawGraf(macierzS);
            }
            else {
                panel.getGraphics().drawRect(0,0,500,500);
                panel.getGraphics().drawString("Podany ciag nie jest graficzny",20,20);}
        }
        if(e.getSource()==randomizujGraf){

            MacierzS macierzS =null;
            for(int i=0; i<30; i++)
            macierzS = MacierzS.randomizuj((MacierzS) graf);
            graf= macierzS;
            panel.drawGraf(graf);
        }

        if(e.getSource() == generujGrafEulerowski)
        {
            try {
                int n = Integer.parseInt(liczbaKrawedzi.getText());
                graf = Algorytmy.generujGrafEulerowski(n);
                panel.drawGraf(graf);
            }catch (RuntimeException ex){
                panel.clean();
                Graphics2D g =(Graphics2D) panel.getGraphics();
                g.drawString(ex.toString(),20,20);
                System.out.println(ex);
            }
        }
        if(e.getSource() == GenerujGrafKRegularny)
        {
            try {
                int n = Integer.parseInt(liczbaKrawedzi.getText());
                double p = Double.parseDouble(pKrawedzi.getText());
                if (p <= 1)
                    graf = Algorytmy.GenerujGrafKRegularny(n, (int) (n * (n - 1) / 2.0 * p));
                else
                    graf = Algorytmy.GenerujGrafKRegularny(n, (int) p);
                panel.drawGraf(graf);
            }catch (RuntimeException ex){
                panel.clean();
                Graphics2D g =(Graphics2D) panel.getGraphics();
                g.drawString(ex.toString(),20,20);
                System.out.println(ex);
            }
        }
        if(e.getSource() == GenerujGrafSpojny)
        {try {
            int n = Integer.parseInt(liczbaKrawedzi.getText());
            double p = Double.parseDouble(pKrawedzi.getText());
            if (p <= 1)
                graf = Algorytmy.GenerujGrafSpojny(n, (int) (n * (n - 1) / 2.0 * p));
            else
                graf = Algorytmy.GenerujGrafSpojny(n, (int) p);
            panel.drawGraf(graf);
        }catch (RuntimeException ex){
            Graphics2D g =(Graphics2D) panel.getGraphics();
            panel.clean();
            g.drawString(ex.toString(),20,20);
            System.out.println(ex);
        }
        }
        if(e.getSource() == znajdźCyklHamiltona) {
            try {


                panel.clean();

                Graphics2D g = (Graphics2D) panel.getGraphics();

                if (graf != null) {
                    ArrayList<Integer> list ;
                    int y = 20;
                    list = (ArrayList<Integer>)new ArrayList<Integer>( Algorytmy.znajdźCyklHamiltona(graf));
                    g.drawString(list.toString(), 20, 20);
                } else
                    g.drawString("Nie wczytano grafu", 20, 20);

            }catch (RuntimeException ex){
                Graphics2D g =(Graphics2D) panel.getGraphics();
                panel.clean();
                g.drawString(ex.toString(),20,20);
                System.out.println(ex);
            }
        }

        if(e.getSource() == znajdźCyklEulera)
        {
            try {
                panel.clean();
                Graphics2D g =(Graphics2D) panel.getGraphics();

                if(graf != null) {
                    ArrayList<Integer> list ;
                    list = (ArrayList<Integer>) new ArrayList<Integer>( Algorytmy.znajdźCyklEulera(graf));
                    g.drawString(list.toString(),20,20);
                }
                else
                    g.drawString("Nie wczytano grafu",20,20);
            }catch (RuntimeException ex){
                Graphics2D g =(Graphics2D) panel.getGraphics();
                panel.clean();
                g.drawString(ex.toString(),20,20);
                System.out.println(ex);
            }
        }
        if(e.getSource() == znajdźSkładowe)
        {
            try {
                panel.clean();
                Graphics2D g =(Graphics2D) panel.getGraphics();

                if(graf != null) {
                    int[] list;
                    list =  Algorytmy.znajdźSkładowe(graf);
                    g.drawString(Arrays.toString(list),20,20);
                }
                else
                    g.drawString("Nie wczytano grafu",20,20);
            }catch (RuntimeException ex){
                Graphics2D g =(Graphics2D) panel.getGraphics();
                panel.clean();
                g.drawString(ex.toString(),20,20);
                System.out.println(ex);
            }
        }


    }
}
