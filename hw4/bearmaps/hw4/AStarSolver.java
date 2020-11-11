package bearmaps.hw4;

import bearmaps.proj2ab.DoubleMapPQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.Stopwatch;


public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private double time;
    private HashMap<Vertex, Vertex> verterces;
    private HashMap<Vertex, Double> distances;
    private int numVisited;
    private SolverOutcome outcome;
    private DoubleMapPQ pq;
    private AStarGraph<Vertex> input;
    private Vertex end;
    private List<Vertex> solution;
    private double solutionweight;


    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch w = new Stopwatch();
        distances = new HashMap<>();
        this.input = input;
        this.end = end;
        numVisited = 0;
        verterces = new HashMap();
        pq = new DoubleMapPQ();
        pq.add(start, input.estimatedDistanceToGoal(start, end));
        verterces.put(start, start);
        distances.put(start, 0.0);
        Vertex current = start;
        while (pq.size() != 0 && (w.elapsedTime() / 1000) <= timeout) {
            current = (Vertex) pq.removeSmallest();
            if (current.equals(end)) {
                break;
            }
            numVisited += 1;
            List<WeightedEdge<Vertex>> edges = input.neighbors(current);

            for (WeightedEdge e : edges) {
                relax(e);

            }
        }
        time = w.elapsedTime();
        if ((w.elapsedTime() / 1000) >= timeout) {
            outcome = SolverOutcome.TIMEOUT;
            solution = new ArrayList();
            solutionweight = 0;
            time = w.elapsedTime() / 1000;
        } else if (current.equals(end)) {
            outcome = SolverOutcome.SOLVED;
            solution = solutionhelper(end, new LinkedList<>());
            solutionweight = distances.get(end);
            time = w.elapsedTime() / 1000;
        } else {
            outcome = SolverOutcome.UNSOLVABLE;
            solution = new ArrayList();
            solutionweight = 0;
            time = w.elapsedTime() / 1000;

        }


    }

    private void relax(WeightedEdge e) {
        Vertex p = (Vertex) e.from();
        Vertex q = (Vertex) e.to();
        double est = input.estimatedDistanceToGoal(q, end);
        if (q.equals(end)) {
            est = 0;
        }
        double w = e.weight();
        double distancefrom = distances.get(p) + w;
        if (!verterces.containsKey(q)) {
            verterces.put(q, p);
            distances.put(q, distancefrom);
            pq.add(q, distancefrom + est);
        } else if (distances.get(q) > distancefrom) {
            pq.changePriority(q, distancefrom + est);
            verterces.replace(q, p);
            distances.put(q, distancefrom);

        }

    }

    private List<Vertex> solutionhelper(Vertex v, LinkedList<Vertex> list) {
        Vertex next = verterces.get(v);
        if (next.equals(v)) {
            list.add(v);
            return list;
        }
        solutionhelper(next, list);
        list.add(v);
        return list;
    }

    public SolverOutcome outcome() {
        return outcome;
    }

    public List<Vertex> solution() {
        return solution;
    }

    public double solutionWeight() {
        return solutionweight;
    }


    public int numStatesExplored() {
        return numVisited;
    }

    public double explorationTime() {
        return time;
    }


}
