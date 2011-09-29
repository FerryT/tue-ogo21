/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm32;

/**
 *
 * @author s070506
 */
public class Proxy implements Comparable<Proxy> {
    public Node node;
    public double distance;

    public int compareTo(Proxy o)
    {
        return (int) ((this.distance - o.distance) * 1000.0);
    }
}
