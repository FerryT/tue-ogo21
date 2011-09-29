
package Algorithm32;

import java.util.ArrayList;

/**
 *
 * @author Ferry Timmers
 */

public class Sector extends ArrayList<Node> {

    public Sector n, e, s, w;

    public void MeasureNodes()
    {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.addAll(this);

        if (n != null)
        {
            if (n.e != null)
                nodes.addAll(n.e);
            nodes.addAll(n);
            if (n.w != null)
                nodes.addAll(n.w);
        }

        if (w != null)
            nodes.addAll(w);

        if (e != null)
            nodes.addAll(e);

        if (s != null)
        {
            if (s.e != null)
                nodes.addAll(s.e);
            nodes.addAll(s);
            if (s.w != null)
                nodes.addAll(s.w);
        }

        for (Node n1: this)
        {
            for (Node n2: nodes)
            {
                if (n1 == n2)
                    continue;

                n1.Measure(n2);
            }
        }
    }
}
