package Model;

import java.awt.Point;

/**
 * Here are several solving algorithms named Algorithm1,Algorithm2...Algorithm(n)
 * Always use  public Output Algorithm(n)(Input) for use with graphic interface
 *
 * @author  OGO 1.2 groep 1
 *          Carl van Dueren den Hollander
 *          Ferry
 *          Nicky Advokaat
 *          Roby Visser
 *          Tim v Dalen
 */
public class Algorithm {
    
    public Algorithm(){
        
    }
    
    // randomnes
    public Output Algorithm1( Input input ){
        Output output = new Output(input.nrOfClusters);
        
        for( Point point : input.points ){
            int cluster = 1 + (int)(Math.random() * ((4 - 1) + 1));
            ClusterPoint clusterpoint = new ClusterPoint( point, cluster );
            output.add(clusterpoint);
        }
        
        return output;
    }
    
    // Carlnes
    public Output Algorithm2( Input input){
        ClusterCarl clusterCarl = new ClusterCarl();
        return clusterCarl.ClusterHierarchy(input);
    }
    
    public Output Algorithm3( Input input){
        Algorithm3.AlgorithmFerry alg3 = new Algorithm3.AlgorithmFerry(input);
        return alg3.output;
    }
    
    public Output Algorithm4( Input input){
        return null;
    }
}
