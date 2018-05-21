import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Noah Tyler
 * CSC 406.02 - Warshall Floyd
 */

public class WarshallFloyd {
    
    private int numOfNodes;
    private int[][] WDAMatrix;
    private int[][] predecessorMatrix;
    private PrintWriter writer = new PrintWriter("floydOutput.txt");
    
    public static void main(String[] args) throws FileNotFoundException{
        if(args.length != 1 || !new File(args[0]).exists()){
            System.out.println("Error: Invalid Filename!");
            System.exit(0);
        }
        
        new WarshallFloyd(new File(args[0]));
    }
    
    private WarshallFloyd(File dataFile) throws FileNotFoundException{
        shortestPath(dataFile);
        write("WDA Matrix: \r\n"+toString(WDAMatrix));
        write("Predecessor Matrix: \r\n"+toString(predecessorMatrix));
        writePath(4,2);
        writePath(2,4);
    }
    
    private void constructWDAMatrix(File data) throws FileNotFoundException{
        Scanner sc = new Scanner(data);
        while(sc.findInLine("c ") != null)
            sc.nextLine();
        numOfNodes = sc.nextInt();
        WDAMatrix = new int[numOfNodes+1][numOfNodes+1];
        predecessorMatrix = new int[numOfNodes+1][numOfNodes+1];
        for(int i = 1; i < numOfNodes+1; i++)
            for(int j = 1; j < numOfNodes+1; j++)
                if(i != j)
                    WDAMatrix[i][j] = Integer.MAX_VALUE;
        while(sc.hasNextInt()){
            int i = sc.nextInt();
            int j = sc.nextInt();
            WDAMatrix[i][j] = sc.nextInt();
            predecessorMatrix[i][j] = i;
        }
    }
    
    private void shortestPath(File dataFile) throws FileNotFoundException{
        constructWDAMatrix(dataFile);
        for(int k = 1; k < numOfNodes+1; k++)
            for(int i = 1; i < numOfNodes+1; i++)
                for(int j = 1; j < numOfNodes+1; j++){
                    int b = WDAMatrix[i][k] + WDAMatrix[k][j];
                    if(b > 0 && b < WDAMatrix[i][j]){
                        WDAMatrix[i][j] = b;
                        predecessorMatrix[i][j] = k;
                    }
                }
    }
    
    private String path(int i, int j){
        if(i == j)
            return i+" --("+WDAMatrix[i][j]+")--> "+j;
        return path(i, predecessorMatrix[i][j])
                +" --("+WDAMatrix[predecessorMatrix[i][j]][j]+")--> "+j;
    }
    
    private void writePath(int i, int j){
        write("\r\nPath From Node "+i+" To Node "+j+": \r\n"+path(i,j));
        write("Total Path Weight: "+WDAMatrix[i][j]);
    }
    
    private void write(String s){
        writer.println(s);
        writer.flush();
    }
    
    private String toString(int[][] array){
        String string = "";
        for(int i = 1; i < numOfNodes+1; i++){
            for(int j = 1; j < numOfNodes+1; j++){
                if(array[i][j] == Integer.MAX_VALUE)
                    string += "\u221e "; //unicode for infinite symbol
                else
                    string += array[i][j]+" ";
            }
            string += "\r\n";    
        }
        return string;
    }
}