
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
    static Sector[][] table; // Allocated staticly because I expect it can grow bigger that way

    public AlgorithmFerry(Input input)
    {
        this.input = input;
        this.output = new Model.Output(2);
        this.nodes = new ArrayList();
        this.minX = Integer.MAX_VALUE;
        this.minY = Integer.MAX_VALUE;
        this.maxX = Integer.MIN_VALUE;
        this.maxY = Integer.MIN_VALUE;
        Calculate();
    }

    public void Calculate()
    {
        long t1 = System.currentTimeMillis();

        AddNodes();

        long t2 = System.currentTimeMillis();
        System.out.printf("pass #1: %f sec\n", (t2 - t1) / 1000.0);

        CreateSectors((maxX - minX) / 128, (maxY - minY) / 128);

        long t3 = System.currentTimeMillis();
        System.out.printf("pass #2: %f sec\n", (t3 - t2) / 1000.0);

        //NoiseReduction();

        long t4 = System.currentTimeMillis();
        System.out.printf("pass #3: %f sec\n", (t4 - t3) / 1000.0);

        OutputNodes();

        long t5 = System.currentTimeMillis();
        System.out.printf("pass #4: %f sec\n", (t5 - t4) / 1000.0);

        long t6 = System.currentTimeMillis();
        //System.out.printf("pass #5: %f sec\n", (t6 - t5) / 1000.0);
        System.out.printf("Total: %f sec\n", (t6 - t1) / 1000.0);

        
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
            nodes.add(node);
        }
        System.out.printf("Dimentions: %d-%d x %d-%d\n", minX, maxX, minY, maxY);
    }

    public void CreateSectors(int w, int h)
    {
        int W = ((maxX - minX) / w) + 1;
        int H = ((maxY - minY) / h) + 1;
        System.out.printf("Sector size: %d x %d\nNumber: %d x %d\n", w, h, W, H);
        table = new Sector[H][W];

        for (int y = 0; y < H; ++y)
        for (int x = 0; x < W; ++x)
        {
            Sector s = new Sector();
            table[y][x] = s;
            if (x > 0)
            {
                s.w = table[y][x-1];
                table[y][x-1].e = s;
            }
            if (y > 0)
            {
                s.n = table[y-1][x];
                table[y-1][x].s = s;
            }
        }

        for (Node n: nodes)
        {
            int x = (n.point.x - minX) / w;
            int y = (n.point.y - minY) / h;
            table[y][x].add(n);
        }

        for (int y = 0; y < H; ++y)
        for (int x = 0; x < W; ++x)
            table[y][x].MeasureNodes();
    }

    public void NoiseReduction()
    {
        PriorityQueue<Node> queue = new PriorityQueue(nodes);

        double maxd = Float.MIN_VALUE;
        double mind = Float.MAX_VALUE;
        double vd = 0;
        double d = 0;
        int range = 0;
        while (!queue.isEmpty())
        {
            vd = queue.peek().nearest.distance - d;
            d = queue.poll().nearest.distance;
            if (vd > maxd)
            {
                maxd = vd;
                range = queue.size();
            }
            if (vd < mind)
                mind = vd;
        }

        queue = new PriorityQueue(nodes);
        if (maxd > mind * 2.0) range = queue.size();
        while (queue.size() > range)
        {
            Node node = queue.poll();
            nodes.remove(node);
            output.add(new ClusterPoint(node.point, 0));
        }
    }

    public void OutputNodes()
    {
        System.out.printf("Outputing %d nodes.\n", nodes.size());
        for (Node n: nodes)
            output.add(new ClusterPoint(n.point, 1));
    }
}
