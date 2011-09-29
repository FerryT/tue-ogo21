package GUI;

import Model.Algorithm;
import Model.ClusterPoint;
import Model.FileReader;
import Model.Input;
import Model.Output;
import java.awt.Point;
import java.io.FileWriter;

/**
 *
 * @author  OGO 1.2 groep 1
 *          Carl van Dueren den Hollander
 *          Ferry
 *          Nicky Advokaat
 *          Roby Visser
 *          Tim v Dalen
 */
public class Main {
    
    Input input;
    Output output;
    Algorithm algorithm;
    
    public void Main(){
        FileReader fr = new FileReader(); 
        algorithm = new Algorithm();
        
        //input = fr.readFromSystem();
        input = fr.readFromFile("puntjes.txt");
        //PrintInput();
        output = algorithm.Algorithm4(input);
        WriteOutput("output.txt");
    }
    
    public void PrintInput(){
        System.out.println("Searching " + input.nrOfClusters + " clusters");
        System.out.println("Within " + input.nrOfPoints + " points:");
        for( Point point : input.points ){
            System.out.println("(" + point.x + "," + point.y + ")");
        }
    }
    
    public void PrintOutput(){
        for( ClusterPoint clusterpoint : output.clusterPoints ){
            System.out.println(clusterpoint.point.x + " " + clusterpoint.point.y + " " + clusterpoint.cluster);
        }
    }
    
    public void WriteOutput(String filename)
    {
        FileWriter fw;
        try { fw = new FileWriter(filename);

        for (ClusterPoint clusterpoint : output.clusterPoints)
            fw.append(clusterpoint.point.x + " " + clusterpoint.point.y + " " + clusterpoint.cluster + "\n");
        } catch(Exception e) {}
    }
    
    public static void main(String[] args){
        new Main().Main();
    }
}
