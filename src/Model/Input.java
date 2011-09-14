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
public class Input {
    
    public int nrOfClusters;
    public int nrOfPoints;
    public ArrayList< Point > points;
    
    public Input( int nrOfClusters, int nrOfPoints, ArrayList< Point > points){
        this.nrOfClusters = nrOfClusters;
        this.nrOfPoints = nrOfPoints;
        this.points = points;
    }
}
