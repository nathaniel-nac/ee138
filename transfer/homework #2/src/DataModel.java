import java.io.*;
import java.util.*;

public class DataModel extends Observable{
    
    //private int data;
    private int[] dataSet;
    
    public DataModel() {
        dataSet = new int[0];
    }

    public int[] getDataSet() {
        return dataSet;
    }
    
    public int getDataSize() {
        return dataSet.length;
    }
    
    public int getData(int index) {
        return dataSet[index];
    }
    
    public void addData(int data) {
        dataSet = Arrays.copyOf(dataSet, dataSet.length+1);
        dataSet[dataSet.length - 1] = data;
        setChanged();
        notifyObservers();
    }
    
    public void changeData(int index, int data) {
        dataSet[index] = data;
        setChanged();
        notifyObservers();
    }
    
}
