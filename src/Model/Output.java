package Model;

import java.util.ArrayList;
import java.awt.Point;

/**
 *
 * @author  OGO 1.2 groep 1
 *          Carl van Dueren den Hollander
 *          Ferry
 *          Nicky Advokaat
 *          Roby Visser
 *          Tim v Dalen
 */
public class Output {
    
    public int nrOfClusters;
    public int nrOfPoints;
    public ArrayList< ClusterPoint > clusterPoints;
    
    public Output( int nrOfClusters ){
        this.nrOfClusters = nrOfClusters;
        nrOfPoints = 0;
        clusterPoints = new ArrayList< ClusterPoint >();
    }
    
    public Output( int nrOfClusters, int nrOfPoints, ArrayList< ClusterPoint > clusterPoints){
        this.nrOfClusters = nrOfClusters;
        this.nrOfPoints = nrOfPoints;
        this.clusterPoints = clusterPoints;
    }
    
    public void add( ClusterPoint clusterPoint ){
        clusterPoints.add(clusterPoint);
        nrOfPoints++;                
    }
}
