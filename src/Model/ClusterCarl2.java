
package Model;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Carl van Dueren den Hollander
 *
 */
public class ClusterCarl2 {
    ArrayList<ClusterPoint> clusterPoints;
    ArrayList<Point> points;

    public Output ClusterCarl2(Input in) {
        clusterPoints = new ArrayList<ClusterPoint>();
        this.points = in.points;

        

        return new Output( in.nrOfClusters, in.nrOfPoints, clusterPoints);
    }
}
