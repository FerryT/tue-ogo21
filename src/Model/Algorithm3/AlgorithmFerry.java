/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Algorithm3;

import java.util.Collections;
import java.util.ArrayList;
import java.awt.Point;
import Model.Input;
import Model.Output;
import Model.ClusterPoint;

/**
 *
 * @author Ferry Timmers
 */
public class AlgorithmFerry {

    public Input input;
    public Output output;

    private ArrayList<Node> nodes;

    public AlgorithmFerry(Input input)
    {
        this.input = input;
        this.output = new Output(0);
        Calculate();
    }

    public void Calculate()
    {
        EvaluatePoints();

        for (Node node: nodes)
            output.add(new ClusterPoint(node.point, 1));

        output.nrOfPoints = output.clusterPoints.size();
        output.nrOfClusters = 2;
    }

    public void EvaluatePoints()
    {
        nodes = new ArrayList<Node>(input.points.size());
        for (Point point: input.points) nodes.add(new Node(point));

        for (Node n1: nodes)
        {
            n1.nearest = Integer.MAX_VALUE;
            for (Node n2: nodes)
            {
                if (n1 == n2)
                    continue;

                double d = n1.distanceTo(n2);

                if (d < n1.nearest)
                {
                    n1.next = n2;
                    n1.nearest = d;
                }
            }
        }
        
        Collections.sort(nodes);

        int range = (int) (nodes.size() * 0.9);
        while (nodes.size() > range)
        {
            Node node = nodes.get(nodes.size() - 1);
            output.clusterPoints.add(new ClusterPoint(node.point, node.cluster));
            nodes.remove(node);
        }

        for (Node n1: nodes)
        {
            if (nodes.contains(n1.next))
                    continue;

            n1.nearest = Integer.MAX_VALUE;
            for (Node n2: nodes)
            {
                if (n1 == n2)
                    continue;

                double d = n1.distanceTo(n2);

                if (d < n1.nearest)
                {
                    n1.next = n2;
                    n1.nearest = d;
                }
            }
        }
    }

    public void Kruskal()
    {
    }

    public void KruskalConnect()
    {
        
    }
}
