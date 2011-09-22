
package Algorithm32;

import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;
import Model.*;

/**
 *
 * @author Ferry Timmers
 */

public class AlgorithmFerry {

    public Input input;
    public Output output;

    public int minX, minY, maxX, maxY;

    private ArrayList<Node> nodes;

    public AlgorithmFerry(Input input)
    {
        this.input = input;
        this.output = new Model.Output(2);
        this.nodes = new ArrayList();
        this.minX = Integer.MIN_VALUE;
        this.minY = Integer.MIN_VALUE;
        this.maxX = Integer.MAX_VALUE;
        this.maxY = Integer.MAX_VALUE;
        Calculate();
    }

    public void Calculate()
    {
        long t1 = System.currentTimeMillis();

        AddNodes();

        long t2 = System.currentTimeMillis();
        System.out.printf("pass #1: %f sec\n", (t2 - t1) / 1000.0);

        NoiseReduction();

        long t3 = System.currentTimeMillis();
        System.out.printf("pass #2: %f sec\n", (t3 - t2) / 1000.0);

        OutputNodes();

        long t4 = System.currentTimeMillis();
        System.out.printf("pass #3: %f sec\n", (t4 - t3) / 1000.0);
        System.out.printf("Total: %f sec\n", (t4 - t1) / 1000.0);
    }

    public void AddNodes()
    {
        for (Point p: input.points)
        {
            minX = Math.min(minX, p.x);
            maxX = Math.max(maxX, p.x);
            minY = Math.min(minY, p.y);
            maxY = Math.max(maxY, p.y);
            Node node = new Node(p);
            //for (Node n: nodes)
            //    n.Measure(node);
            nodes.add(node);
        }
    }

    public void CreateSectors(int w, int h)
    {
        int W = (maxX - minX) / w;
        int H = (maxY - minY) / h;
        int x, y;
    }

    public void NoiseReduction()
    {
        PriorityQueue<Node> queue = new PriorityQueue(nodes);

        double maxd = Float.MIN_VALUE;
        double vd = 0;
        double d = 0;
        int range = 0;
        while (!queue.isEmpty())
        {
            vd = queue.peek().nearestDistance - d;
            d = queue.poll().nearestDistance;
            if (vd > maxd)
            {
                maxd = vd;
                range = queue.size();
            }
        }

        queue = new PriorityQueue(nodes);
        if (maxd > 2.0) range = queue.size();
        while (queue.size() > range)
        {
            Node node = queue.poll();
            nodes.remove(node);
            output.add(new ClusterPoint(node.point, 0));
        }
    }

    public void OutputNodes()
    {
        for (Node n: nodes)
            output.add(new ClusterPoint(n.point, n.cluster));
    }
}
