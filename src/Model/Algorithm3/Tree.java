/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Algorithm3;

import java.util.ArrayList;

/**
 *
 * @author Ferry Timmers
 */
public class Tree extends ArrayList<Edge> {

    //@Override
    public boolean contains(Node node)
    {
        for (Edge e: this)
            if ((e.n1 == node) || (e.n2 == node))
                return true;
        return false;
    }

    public String Print()
    {
        String str = "[";
        for (Edge e: this)
            str += String.format(" %s ", e.Print());
        str += "]";
        return str;
    }
}
