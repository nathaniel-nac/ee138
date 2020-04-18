import java.util.*;
import javax.swing.*;
import java.awt.*;

public class GraphView extends JPanel implements Observer {

    private DataModel model;
    //private ArrayList<Integer> vals;
    private static final int HEIGHT = 20;
    private int[] vals;
    private int n;
    
    public GraphView(DataModel graphModel) {
        super();
        model = graphModel;
        n=0;
        vals = new int[n];
        //vals = new ArrayList<Integer>();
    }
    
    public int boundHeight() {     
        return ((n*HEIGHT)+HEIGHT)+60;
    }
    
    public int[] getVals() {
        return vals;
    }
    
    public int getMyHeight() {
        return HEIGHT;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        vals = model.getDataSet();
        n = vals.length;
        this.repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(int i=0; i<vals.length; i++) {
            Rectangle r = new Rectangle(0, i*HEIGHT, vals[i], HEIGHT);
            g2.draw(r);
        }
    }

}
