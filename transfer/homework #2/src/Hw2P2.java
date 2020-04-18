import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

public class Hw2P2 {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new FileReader(new File("args[0].txt")));
        DataModel model = new DataModel();//Observable
        TextView view1 = new TextView(model);//Observer1
        GraphView view2 = new GraphView(model);

        JFrame frame1 = new JFrame();
        JFrame frame2 = new JFrame();

        int val;
        String v;

        model.addObserver(view1);
        model.addObserver(view2);
        
        while(br.ready()) {
            v = br.readLine();
            val = Integer.parseInt(v);
            model.addData(val);
            JTextField textField = new JTextField();
            textField.setText(v);
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent arg0) {
                    // TODO Auto-generated method stub
                    try {
                        int x = textField.getY();
                        int y= textField.getHeight();
                        int index = x/y;
                        String str = textField.getText();
                        int v = Integer.parseInt(str);
                        model.changeData(index, v);//IllegalStateException
                        
                    } catch(IllegalStateException |ArithmeticException e) {} 
                }

                @Override
                public void insertUpdate(DocumentEvent doc) {
                    // TODO Auto-generated method stub 
                    try {
                        int x = textField.getY();
                        int y= textField.getHeight();
                        int index = x/y;
                        int v=0;
                        if(doc.getLength() == 0) {
                            v = 0;
                            model.changeData(index, v);
                        }
                        else {
                            String str = textField.getText();
                            v = Integer.parseInt(str);
                            model.changeData(index, v);
                        }
                    } catch(IllegalStateException|ArithmeticException e) {}
                }

                @Override
                public void removeUpdate(DocumentEvent doc) {
                    // TODO Auto-generated method stub
                    try {
                        int x = textField.getY();
                        int y= textField.getHeight();
                        int index = x/y;
                        int v=0;
                        if(doc.getLength() == 1) {
                            v = 0;
                            model.changeData(index, v);
                        }
                        else {
                            String str = textField.getText();
                            v = Integer.parseInt(str);
                            model.changeData(index, v);
                        }
                    } catch(IllegalStateException|ArithmeticException|NumberFormatException e) {}
                }
                
            });
            view1.add(textField);
        }
        br.close();
        
        view2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int data = e.getX();
                int x = e.getY();
                int y = view2.getMyHeight();
                int index = x/y;
                model.changeData(index, data);
            }
        });
        
        frame1.setContentPane(view1);
        frame1.pack();
        frame1.setVisible(true);
        frame1.addWindowListener(new 
                WindowAdapter(){
                    public void windowClosing(WindowEvent event) {
                        System.out.println("frame 1 has updated args[0]");
                        int[] data = model.getDataSet();
                        try {
                            //System.out.println("frame 2 window listener working!!!");
                            PrintWriter writer = new PrintWriter(new FileWriter("args[0].txt", false));
                            for(int i=0; i<data.length; i++) {
                                writer.println(data[i]);
                            }
                            writer.close();
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
            }
        });

        frame2.setContentPane(view2);
        frame2.setBounds(200, 0, 0, view2.boundHeight());
        frame2.setVisible(true);
        frame2.addWindowListener(new 
                WindowAdapter(){
                    public void windowClosing(WindowEvent event) {
                        System.out.println("frame 2 has updated args[0]");
                        int[] data = model.getDataSet();
                        try {
                            //System.out.println("frame 2 window listener working!!!");
                            PrintWriter writer = new PrintWriter(new FileWriter("args[0].txt", false));
                            for(int i=0; i<data.length; i++) {
                                writer.println(data[i]);
                            }
                            writer.close();
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
            }
        });
    }

}
