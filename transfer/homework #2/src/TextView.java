import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class TextView extends Box implements Observer{
    
    private DataModel model;
    private String val;
    private int n;
    
    public TextView(DataModel textModel) {
        super(BoxLayout.Y_AXIS);
        model = textModel;
    }
    
    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub
        if(model.getDataSize() <= this.getComponentCount()) {
            for(Component comp: this.getComponents()) {
                JTextField text = (JTextField) comp;
                int x = text.getY();
                int y= text.getHeight();
                int index = x/y;
                String str = text.getText();
                int textFieldVal = Integer.parseInt(str);
                int dataModelVal = model.getData(index);
                if(textFieldVal != dataModelVal) {
                    String val = Integer.toString(dataModelVal);
                    text.setText(val);
                }
            }
            
        }
        /*Component[] textViewData = this.getComponents();
        int[] dataModelSet = model.getDataSet();
        for(int i=0; i<textViewData.length; i++) {
            JTextField field = (JTextField) textViewData[i];
            String str1 = field.getText();
            int val2 = dataModelSet[i];
            String str2 = Integer.toString(val2);
            if(str1.contentEquals(str2))
                field.setText(str2);//IllegalStateException
        }*/
    }
    
}
