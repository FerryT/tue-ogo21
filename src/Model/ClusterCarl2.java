package Model;

//Zozo Carl, flink bezig op de zondag avond


import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Carl van Dueren den Hollander
 *
 */
public class ClusterCarl2 {

    private class Noise extends Exception {}

    private class Cluster {
        private ArrayList<Point> Border;

        public ArrayList<Point> cPoints;
        public int clusterNr;

        private void Initialize() {
            Border = new ArrayList<Point>();
            cPoints = new ArrayList<Point>();
        }

        public Cluster(int clusterNr) {
            Initialize();

            this.clusterNr = clusterNr;
        }

        public Cluster(Point p, int clusterNr) throws Noise {
            Initialize();

            Border.add(p);
            System.out.println("Added!");
            this.clusterNr = clusterNr;


            if (traceBorder()) {
                addContainedPoints();
            } else {
                // NOISE
                throw new Noise();
            }
        }

        private boolean traceBorder() {
            // TODO
            Point previous, tracker = Border.get(0), leftClosest = getLeftClosest(tracker, null, points);
            if (leftClosest == null) return false;
            else {
                Border.add(leftClosest);
                points.remove(leftClosest);
            }

            int count = 0;

            while(true) {
            	previous = tracker;
                tracker = leftClosest;
                leftClosest = getLeftClosest(tracker, previous, points);
                
                if (leftClosest == Border.get(0)) {
                    points.remove(leftClosest);
                    
                    break;
                } else if (leftClosest == null) {
                    leftClosest = getLeftClosest(tracker, previous, Border);
                    Border.add(leftClosest);
                    if (leftClosest == Border.get(0)) break;
                } else {
                    Border.add(leftClosest);
                    points.remove(leftClosest);
                }

                System.out.println("Borderpoint " + count++ + " added.");
            }

            return true;
        }

        ArrayList<Point> inRange;

        private Point getLeftClosest(Point p, Point previousp, ArrayList<Point> pList) {
            int bound = getBound();
            //Collections.copy(inRange, getInRange(p.x-bound, p.x+bound, p.y-bound, p.y+bound));
            inRange = getInRange(p.x-bound, p.x+bound, p.y-bound, p.y+bound, pList);
            double minDirection = 361;
            Point minDirPoint = null;

            //for (Point pInRange : inRange) {
            for (int i = 0; i < inRange.size(); i++) {
                Point pInRange = inRange.get(i);
                int dx = pInRange.x - p.x, dy = pInRange.y - p.y;
                double distance = Math.sqrt(dx*dx + dy*dy);
                if (distance > getBound()) {
                    inRange.remove(pInRange);
                } else if (previousp == null) {
                    double direction = getDirection(p, pInRange); //System.out.println("First border point direction --- " + direction);
                    if (direction < minDirection) {
                        minDirection = direction;
                        minDirPoint = pInRange;
                    }
                } else {
                    double direction = getDirection(p, pInRange) - getDirection(previousp, p); //System.out.println("previous p != null; " + direction);
                    if (direction < minDirection) {
                        minDirection = direction;
                        minDirPoint = pInRange;
                    }
                }
            }

            return minDirPoint;
        }

        int count;

        private double getDirection(Point a, Point b) {
            /*
            if (b.x == a.x) { System.out.println(count++);
                if (b.y > a.y) return (0.5 * Math.PI);
                else return (1.5 * Math.PI);
            } else return Math.atan((b.y - a.y) / (b.x - a.x));
            */

            int deltax = b.x - a.x, deltay = b.y - a.y;
            double c, maxc;

            if (deltax > 0) {
                c = deltay / deltax;
                maxc = getBound(); //because deltax > 1
                if (deltay > 0) return (c / maxc) * 90;
                else return (c / maxc) * 90 + 360;
            } else if (deltax < 0) {
                c = deltay / deltax;
                maxc = getBound(); //because deltax > 1
                return (c / maxc) * 90 + 180;
            } else {
                if (deltay > 0) return 90;
                else return 270;
            }
        }

        private int getBound() {
            //return (int) (((points.get(points.size() - 1).x - points.get(0).x) * 2) / Math.sqrt(pNr));
            return 10;
        }

        private void addContainedPoints() {
            Polygon poly = new Polygon();
            for (Point p : Border) {
                poly.addPoint(p.x, p.y);
                cPoints.add(p);
                //points.remove(p);
            }

            for (Point p : points) if (poly.contains(p)) {
                cPoints.add(p);
                points.remove(p);
            }
        }

        public ArrayList<ClusterPoint> getClusterPoints() {
            ArrayList<ClusterPoint> clusterPoints = new ArrayList<ClusterPoint>();

            for (Point p : cPoints) {
                clusterPoints.add(new ClusterPoint(p, clusterNr));
            }

            return clusterPoints;
        }
    }

    ArrayList<ClusterPoint> clusterPoints;
    ArrayList<Point> points;
    ArrayList<Cluster> clusters;
    int pNr;

    public Output ClusterCarl2(Input in) {
        clusterPoints = new ArrayList<ClusterPoint>();
        points = in.points;
        sort(points);
        pNr = in.nrOfPoints;

        clusters = new ArrayList<Cluster>();
        clusters.add(new Cluster(0)); // Noise cluster

        if (points.get(0) == null) System.out.println("WTF");

        while (points.size() > 0) {
            int size = clusters.size();
            try {
                System.out.println(size);
                clusters.add(new Cluster(points.get(0), size));
            } catch (Noise n) {
                // Add noise to noise cluster
                clusters.get(0).cPoints.add(points.get(0));
                points.remove(0);
                System.out.println("Noise found");
            }/* catch (NullPointerException ex) {
                System.out.println(size);
                //System.exit(0);
            }*/
        }

        //Wrap up output format
        for (Cluster c : clusters)
            for (ClusterPoint cP : clusters.get(c.clusterNr).getClusterPoints())
                clusterPoints.add(cP);

        return new Output(in.nrOfClusters, in.nrOfPoints, clusterPoints);
    }

    private ArrayList<Point> getInRange(int x0, int x1, int y0, int y1, ArrayList<Point> pList){
        ArrayList<Point> range = new ArrayList<Point>();
        for (Point p : pList)  if (p.x >= x0 && p.x <= x1 && p.y >= y0 && p.y <= y1) range.add(p);
        return range;
    }

    private void sort(ArrayList<Point> points) {
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p0, Point p1) {
                if (p0.y < p1.y) return -1;
                else if (p0.y > p1.y) return 1;
                else { // p0.x == p1.x
                    if (p0.x < p1.x) return -1;
                    else if (p0.x > p1.x) return 1;
                    else return 0; // p0.y == p1.y
                }
            }
        });
    }
}
