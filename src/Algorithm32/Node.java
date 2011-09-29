
package Algorithm32;

import java.awt.Point;

/**
 *
 * @author Ferry Timmers
 */
public class Node implements Comparable<Node> {

    public Point point;
    public int cluster;

    public Node nearestNode;
    public double nearestDistance;
    public Node secondNode;
    public double secondDistance;

    public Node(Point point)
    {
        this.point = point;
        this.cluster = 1;
    }

    public void Measure(Node node)
    {
        double distance = point.distance(node.point);
        if (distance <= nearestDistance)
        {
            secondNode = nearestNode;
            secondDistance = nearestDistance;
            nearestNode = node;
            nearestDistance = distance;
        }
    }

    public int compareTo(Node o)
    {
        return (int) ((this.nearestDistance - o.nearestDistance) * 1000.0);
    }
}
