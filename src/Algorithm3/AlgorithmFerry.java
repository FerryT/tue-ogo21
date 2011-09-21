/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm3;

import java.util.Collections;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;
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
    private Tree edges;

    public AlgorithmFerry(Input input)
    {
        this.input = input;
        this.output = new Output(0);
        Calculate();
    }

    public void Calculate()
    {
        CreateNodes();
        RemoveNoise();
        CreateEdges();
        //Kruskal(3);

        for (Node node: nodes)
            output.add(new ClusterPoint(node.point, node.cluster));

        output.nrOfPoints = output.clusterPoints.size();
        output.nrOfClusters = 2;
    }

    public void CreateNodes()
    {
        nodes = new ArrayList<Node>(input.points.size());
        for (Point point: input.points) nodes.add(new Node(point));
    }

    public void RemoveNoise()
    {
        for (Node n: nodes)
        {
            n.DefineNearestNode(nodes);
            n.nearestDistance *= -1;
        }
        
        Collections.sort(nodes);

        PriorityQueue<Node> queue = new PriorityQueue(nodes);

        int range = (int) (queue.size() * 0.9);
        while (queue.size() > range)
        {
            Node node = queue.poll();
            nodes.remove(node);
            
            output.add(new ClusterPoint(node.point, 0));
        } 
    }

    public void CreateEdges()
    {
        for (Node n: nodes)
            n.DefineNearestNode(nodes);

        edges = new Tree();
        PriorityQueue<Node> queue = new PriorityQueue(nodes);
        ArrayList<Tree> forest = new ArrayList<Tree>();
        
        forest.add(edges);
        int cluster = 1;
        while (!queue.isEmpty())
        {
            Node node = queue.poll();

            if (node.nearestNode.cluster > 0)
            {
                node.cluster = node.nearestNode.cluster;
                forest.get(node.nearestNode.cluster).add(new Edge(node, node.nearestNode));
            }
            else
            {
                node.cluster = cluster;
                node.nearestNode.cluster = cluster;
                queue.remove(node.nearestNode);
                Tree t = new Tree();
                t.add(new Edge(node, node.nearestNode));
                forest.add(t);
                cluster++;
            }
        }

        PriorityQueue<Edge> edgeQueue = new PriorityQueue();

        for (Tree t1: forest)
        {
            for (Tree t2: forest)
            {
                if (t1 == t2)
                    continue;

                edgeQueue.addAll(ConnectTrees(t1, t2, 1));
            }
        }

        while (forest.size() > 8)
        {
            Edge edge = edgeQueue.poll();
            Tree t1 = FindTree(forest, edge.n1);
            Tree t2 = FindTree(forest, edge.n2);
            if (t1 != t2)
            {
                Tree t = new Tree();
                t.addAll(t1);
                t.addAll(t2);
                forest.remove(t1);
                forest.remove(t2);
                forest.add(t);
            }
        }

        for (Tree t: forest)
        {
            int i = forest.indexOf(t);
            for (Edge e: t)
            {
                e.n1.cluster = i;
                e.n2.cluster = i;
            }
        }

        //edges.Print();
    }

    public void Kruskal(int count)
    {
        PriorityQueue<Edge> queue = new PriorityQueue(edges);
        ArrayList<Tree> forest = new ArrayList<Tree>();

        for (Edge e: edges)
        {
            Tree t = new Tree();
            t.add(e);
            forest.add(t);
        }

        while (!queue.isEmpty())
        {
            Edge edge = queue.poll();

            Tree t1 = FindTree(forest, edge.n1);
            Tree t2 = FindTree(forest, edge.n2);
            
            if (t1 != t2)
            {
                forest.remove(t1);
                forest.remove(t2);

                Tree t = new Tree();
                t.addAll(t1);
                t.add(edge);
                t.addAll(t2);

                forest.add(t);
            }
        }

        int i = 1;
        for (Tree t: forest)
        {
            for (Edge e: t)
            {
                e.n1.cluster = i;
                e.n2.cluster = i;
            }
            i++;
        }
    }

    public Tree FindTree(ArrayList<Tree> forest, Node node)
    {
        for (Tree t: forest)
            if (t.contains(node))
                return t;
        return null;
    }

    public Tree ConnectTrees(Tree t1, Tree t2, int number)
    {

        PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
        TreeSet<Node> ns1 = new TreeSet<Node>();
        TreeSet<Node> ns2 = new TreeSet<Node>();

        t1.Print();
        t2.Print();

        for (Edge e: t1)
        {
            ns1.add(e.n1);
            ns1.add(e.n2);
        }

        for (Edge e: t2)
        {
            ns2.add(e.n1);
            ns2.add(e.n2);
        }

        for (Node n1: ns1)
            for (Node n2: ns2)
                queue.add(new Edge(n1, n2));

        Tree t =  new Tree();
        
        while ((number > 0) && !queue.isEmpty())
        {
            t.add(queue.poll());
            number--;
        }

        return t;
    }
}
