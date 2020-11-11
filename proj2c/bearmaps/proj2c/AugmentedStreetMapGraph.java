package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;
import edu.princeton.cs.algs4.TrieSET;


import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    HashMap<Point, Node> pointMap;
    WeirdPointSet points;
    List<Node> nodes;
    MyTrieSet tries;
    HashMap<String, List<String>> nametoactual;
    HashMap<String, List<Node>> location;


    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        nodes = this.getNodes();
        List<Point> nodesWithNoNeighbors = new ArrayList<>();
        pointMap = new HashMap<>();
        location = new HashMap<>();
        for (Node i : nodes) {
            if (i.name() != null){
                if (i.name().length() == 0){
                    continue;
                }
                if (location.containsKey(cleanString(i.name()))){
                    List p = location.get(cleanString(i.name()));
                    p.add(i);
                    continue;
                }
                List<Node> l = new ArrayList<>();
                l.add(i);
                location.put(cleanString(i.name()),l);


            }

            if (this.neighbors(i.id()).size() == 0) {
                continue;
            }
            Point p = new Point(i.lon(), i.lat());
            pointMap.put(p, i);
            nodesWithNoNeighbors.add(p);
        }

        points = new WeirdPointSet(nodesWithNoNeighbors);
        tries = new MyTrieSet();
        nametoactual = new HashMap<>();
        String s;
        List l = new ArrayList();
        for (Node i : nodes) {
            if (i.name() != null) {
                s = cleanString(i.name());
                if (s.length()==0){
                    continue;
                }
                tries.add(s);
                if (nametoactual.containsKey(s)){
                    ((List) nametoactual.get(s)).add(i.name());
                    continue;
                }
                List list = new ArrayList();
                list.add(i.name());
                nametoactual.put(s,list);


            }
        }


    }




    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     *
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        return pointMap.get(points.nearest(lon, lat)).id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     *
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        Iterable<String> l = tries.keysWithPrefix(prefix.toLowerCase());
        List<String> results = new ArrayList<>();
        for (String i : l) {
            for (String j:nametoactual.get(i)) {
                results.add(j);
            }

        }
        return results;
    }


    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        if (!location.containsKey(cleanString(locationName))){
            return new ArrayList<>();
        }
        List<Node> e = location.get(cleanString(locationName));
        List<Map<String,Object>> restult = new ArrayList<Map<String, Object>>();
        for (Node i : e){
            HashMap<String, Object> a = new HashMap<String, Object>();
            a.put("lat", i.lat());
            a.put("lon",i.lon());
            a.put("name",i.name());
            a.put("id",i.id());
            restult.add(a);

        }


        return restult;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }


    public class MyTrieSet {
        private Node root;

        private class Node {
            private HashMap<Character, Node> letters;
            private boolean isLastLetter;

            public Node() {
                letters = new HashMap<Character, Node>();
                isLastLetter = false;
            }


        }

        public void clear() {
            root = null;
        }

        public boolean contains(String s) {
            return containsHelper(root, s);

        }

        private boolean containsHelper(Node n, String s) {
            if (root == null) {
                return false;
            }
            if (s.length() == 1) {
                if (n.letters.containsKey(s.charAt(0))) {
                    Node next = (Node) n.letters.get(s.charAt(0));
                    return (boolean) next.isLastLetter;
                }
                return false;
            } else if (n.letters.containsKey(s.charAt(0))) {
                return containsHelper((Node) n.letters.get(s.charAt(0)), s.substring(1));
            }
            return false;
        }


        public void add(String key) {
            if (root == null) {
                root = new Node();

            }
            addHelper(root, key);


        }

        private void addHelper(Node n, String s) {
            if (s.length() == 1) {
                if (n.letters.containsKey(s.charAt(0))) {
                    Node next = (Node) n.letters.get(s.charAt(0));
                    next.isLastLetter = true;
                    return;
                }
                Node next = new Node();
                n.letters.put(s.charAt(0), next);
                next.isLastLetter = true;

            } else if (n.letters.containsKey(s.charAt(0))) {
                addHelper((Node) n.letters.get(s.charAt(0)), s.substring(1));

            } else {
                Node next = new Node();
                n.letters.put(s.charAt(0), next);
                addHelper((Node) n.letters.get(s.charAt(0)), s.substring(1));

            }


        }

        public List<String> keysWithPrefix(String prefix) {
            Node p = root;
            List L = new ArrayList();
            int i = 0;
            String s = prefix;
            while (i < prefix.length()) {
                if (p.letters.containsKey(s.charAt(0))) {
                    p = (Node) p.letters.get(s.charAt(0));
                    s = s.substring(1);
                    i++;
                } else {
                    return L;

                }

            }
            keysWithPrefixHelper(p, prefix, L);
            return L;
        }

        private void keysWithPrefixHelper(Node n, String prefix, List L) {
            if (n == null) {
                return;
            }
            if (n.isLastLetter) {
                L.add(prefix);
            }
            for (Map.Entry<Character, Node> e : n.letters.entrySet()) {
                String newPrefic = prefix + e.getKey();
                Node N = e.getValue();
                keysWithPrefixHelper(N, newPrefic, L);
            }


        }
    }






}
