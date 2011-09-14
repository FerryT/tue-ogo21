
package Model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.awt.Point;

/**
 *
 * @author Carl van Dueren den Hollander
 *
 */

public class ClusterCarl{
    private class Cluster {
        public double[] geoCenter = new double[] {0, 0};
        private double r = 0;
        public Point furthest;
        public ArrayList< Point > list;

        public Cluster(Point point) {
            list = new ArrayList<Point>();
            add(point);
        }

        public boolean add(Point point) {
            int size = list.size();
            geoCenter[0] = (geoCenter[0] * size + point.x) / (size+1);
            geoCenter[1] = (geoCenter[1] * size + point.y) / (size+1);

            if (d(point) > r) furthest = point;

            return list.add(point);
        }

        private double d(Point p0) {
            double dx = (p0.x-geoCenter[0]), dy = (p0.y-geoCenter[1]);
            return Math.sqrt(dx*dx + dy*dy);
        }

        public void merge(Cluster c) {
            for (Point point : c.list) {
                add(point);
            }
        }
    }

    ArrayList<Cluster> c;

    private void merge(Cluster c0, Cluster c1) {
        c0.merge(c1);
        c.remove(c1);
    }

    public Output ClusterHierarchy(Input input) {
        
        int nrOfClusters = input.nrOfClusters;
        int nrOfPoints = input.nrOfPoints;
        ArrayList clusterPoints = new ArrayList< ClusterPoint >();
        
        c = new ArrayList<Cluster>();

        for (Point point : input.points) {
            c.add(new Cluster(point));
        }

        int timer = 0;
        while (c.size() > nrOfClusters) {
            if (timer > nrOfPoints) break;

            for (int cNr = 0; cNr < c.size(); cNr++) {
                Cluster c0 = c.get(cNr);
                Cluster c1 = getClosestCluster(c0);
                double d = getDistance(c0, c1);
                if (d < getBound(c0) || d < getBound(c1)) merge(c0, c1);
            }

            timer++;
        }
        
        for( Cluster cluster : c){
            for( Point point : cluster.list){
                clusterPoints.add(new ClusterPoint( point, c.indexOf(cluster)));
            }
        }
        return new Output( nrOfClusters, nrOfPoints, clusterPoints);
    }

    private Cluster getClosestCluster(Cluster c0) {
        //TODO improve efficiency
        double d;
        double min = 0;
        Cluster minc = null;
        for (Cluster c1 : c) {
            if (c0 != c1) {
                d = getDistance(c0, c1);
                if (min == 0 || d < min) {
                    min = d;
                    minc = c1;
                }
            }
        }
        return minc;
    }

    private double getDistance(Cluster c0, Cluster c1) {
        double[] d = new double[] {c0.geoCenter[0] - c1.geoCenter[0], c0.geoCenter[1] - c1.geoCenter[1]};
        d = new double[] {d[0] * d[0], d[1] * d[1]};
        return Math.sqrt(d[0]+d[1]);
    }

    private double getBound(Cluster c0) {
        return (c0.r+1)*2;
    }

    public void printClusters(PrintStream out) {
        for (int cNr = 0; cNr < c.size(); cNr++) {
            for (Point point : c.get(cNr).list) {
                out.println(point.x + " " + point.y + " " + cNr);
            }
        }
    }
}
