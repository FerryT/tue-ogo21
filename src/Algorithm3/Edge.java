/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm3;

/**
 *
 * @author Ferry Timmers
 */
public class Edge implements Comparable<Edge> {
    public Node n1, n2;
    public double distance;

    public Edge(Node n1, Node n2)
    {
        this.n1 = n1;
        this.n2 = n2;
        distance = n1.distanceTo(n2);
    }

    public int compareTo(Edge o)
    {
        return (int) ((o.distance - this.distance) * 1000.0);
    }

    public String Print()
    {
        return String.format("%s->%s", n1.Print(), n2.Print());
    }
}
