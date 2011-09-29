
package Algorithm32;

import java.awt.Point;
import java.util.PriorityQueue;

/**
 *
 * @author Ferry Timmers
 */
public class Node implements Comparable<Node> {

    public Point point;
    public int cluster;

    public Proxy nearest;
    public PriorityQueue<Proxy> cache;

    public Node(Point point)
    {
        this.point = point;
        this.cluster = 1;
        nearest = new Proxy();
        cache = new PriorityQueue();
    }

    public void Measure(Node node)
    {
        double distance = point.distance(node.point);
        if (distance <= nearest.distance)
        {
            nearest.node = node;
            nearest.distance = distance;
            Proxy p = new Proxy();
            p.distance = distance;
            p.node = node;
            cache.add(p);
        }
    }

    public int compareTo(Node o)
    {
        return (int) ((this.nearest.distance - o.nearest.distance) * 1000.0);
    }
}
