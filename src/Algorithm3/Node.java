/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm3;

import java.util.ArrayList;
import java.awt.Point;

/**
 *
 * @author Ferry Timmers
 */
public class Node implements Comparable<Node> {
    public Point point;
    public int cluster;

    public double nearestDistance;
    public Node nearestNode;
    public ArrayList<Node> children;

    public Node(Point point)
    {
        this.point = point;
        this.cluster = 0;

        this.nearestDistance = Integer.MAX_VALUE;
        this.nearestNode = null;
        this.children = new ArrayList<Node>();
    }

    public int compareTo(Node o)
    {
        return (int) ((this.nearestDistance - o.nearestDistance) * 1000.0);
    }

    public double distanceTo(Node o)
    {
        double vx = (this.point.x - o.point.x);
        double vy = (this.point.y - o.point.y);
        return Math.sqrt(vx*vx + vy*vy);
    }

    public void DefineNearestNode(ArrayList<Node> nodes)
    {
        if (nodes.contains(nearestNode))
            return;
        
        nearestDistance = Integer.MAX_VALUE;

        for (Node n: nodes)
        {
            if (n == this)
                continue;

            double d = distanceTo(n);
            if (d < nearestDistance)
            {
                if (nearestNode != null)
                    nearestNode.children.remove(this);
                nearestDistance = d;
                nearestNode = n;
                nearestNode.children.add(this);
            }
        }
    }

    public String Print()
    {
        return String.format("(%d %d)", point.x, point.y);
    }
}
