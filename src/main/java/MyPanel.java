import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {

    MyPanel(){
        super();
        this.setPreferredSize(new Dimension(500,500));
        this.setBackground(Color.WHITE);
    }
    @Override
    public void paint(Graphics g) {

       Graphics2D graphics2D = (Graphics2D) g;
       graphics2D.setColor(Color.WHITE);
       graphics2D.fillRect(0,0,500,500);
       graphics2D.setColor(Color.BLACK);

    }
    public void clean(){
        Graphics2D graphics2D =(Graphics2D) this.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0,0,500,500);
    }
    public void drawGraf(Graf graf){

        this.clean();
        int xs = 250;
        int ys = 250;
        double r  = 200;
        Graphics2D graphics2D =(Graphics2D) this.getGraphics();
        int amount = graf.getMacierz().size();
        ListaS lista;
        if(graf instanceof MacierzS)
            lista=((MacierzS)graf).toListS();//.getMacierz();
        else if(graf instanceof MacierzI )
            lista=((MacierzI)graf).toListS();//.getMacierz();
        else
            lista =(ListaS) graf;
        lista.wypisz();
        for(int i=0 ; i<amount;i++)
        {

            int x1 =(int)(250.0+r*Math.cos(Math.toRadians(i*360.0/amount)));
            int y1 = (int)(250+r*Math.sin(Math.toRadians(i*360.0/amount)));
            graphics2D.fillOval(x1,y1,20,20);

            for(int j=0; j<graf.getMacierz().get(i).size(); j++){
                int x2= (int)(250.0+r*Math.cos(Math.toRadians(lista.getMacierz().get(i).get(j)*360.0/amount))+10);
                int y2= (int)(250.0+r*Math.sin(Math.toRadians(lista.getMacierz().get(i).get(j)*360.0/amount))+10);
                graphics2D.drawLine(x1+10,y1+10,x2,y2);
             }


        }

    }



}
