package Model;

import java.awt.Point;

/**
 *
 * @author nicky
 */
public class Algorithm {
    
    public Algorithm(){
        
    }
    
    public Output Algorithm1( Input input ){
        Output output = new Output(input.nrOfClusters);
        
        for( Point point : input.points ){
            int cluster = 1 + (int)(Math.random() * ((4 - 1) + 1));
            ClusterPoint clusterpoint = new ClusterPoint( point, cluster );
            output.add(clusterpoint);
        }
        
        return output;
    }
    
    public Output Algorithm2( Input input){
        ClusterCarl clusterCarl = new ClusterCarl();
        return clusterCarl.ClusterHierarchy(input);
    }
    
    public Output Algorithm3( Input input){
        return null;
    }
    
    public Output Algorithm4( Input input){
        return null;
    }
}
