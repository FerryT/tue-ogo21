package Model;

import java.awt.Point;

/**
 * A ClusterPoint is a point with an integer indicating the cluster the point is in
 * 
 * 
 * @author  OGO 1.2 groep 1
 *          Carl van Dueren den Hollander
 *          Ferry
 *          Nicky Advokaat
 *          Roby Visser
 *          Tim v Dalen
 */
public class ClusterPoint {
    public Point point;
    public int cluster;
    
    public ClusterPoint( Point point, int cluster){
        this.point = point;
        this.cluster = cluster;
    }
}
