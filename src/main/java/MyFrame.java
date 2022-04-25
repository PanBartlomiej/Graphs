import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class MyFrame extends JFrame implements ActionListener {

    Button loadGraf = new Button("Wczytaj Graf");
    Button wypiszGraf = new Button("Wypisz Graf");
    Button rysujGraf = new Button("Rysuj Graf");
    Button losujGraf = new Button("Losuj Graf");
    Button wczytajCiag = new Button("Wczytaj CiagGraf");
    Button randomizujGraf = new Button("Randomizuj Graf");
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
            for(int i=0; i<10; i++)
            macierzS = MacierzS.randomizuj((MacierzS) graf);
            graf= macierzS;
            panel.drawGraf(graf);
        }
    }
}
