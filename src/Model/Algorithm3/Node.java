/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Algorithm3;

import java.awt.Point;

/**
 *
 * @author Ferry Timmers
 */
public class Node implements Comparable<Node> {
    public Point point;
    public double nearest;
    public int cluster;
    public Node next;

    public Node(Point point)
    {
        this.point = point;
        this.nearest = 0;
        this.cluster = 0;
        this.next = null;
    }

    public int compareTo(Node o)
    {
        return (int) (this.nearest - o.nearest);
    }

    public double distanceTo(Node o)
    {
        double vx = (this.point.x - o.point.x);
        double vy = (this.point.y - o.point.y);
        return Math.sqrt(vx*vx + vy*vy);
    }
}
