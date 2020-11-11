import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    private class Node {
        private HashMap<Character,Node> letters;
        private boolean isLastLetter;
        public  Node(){
            letters = new HashMap<Character, Node>();
            isLastLetter = false;
        }


    }
    public void clear(){
        root = null;
    }
    public boolean contains(String s){
        return containsHelper(root, s);

    }
    private boolean containsHelper(Node n, String s){
        if (root == null) { return false;}
        if (s.length() == 1){
            if (n.letters.containsKey(s.charAt(0))){
                Node next =  (Node) n.letters.get(s.charAt(0));
                return (boolean) next.isLastLetter;
        }
        return false;
        }
        else if (n.letters.containsKey(s.charAt(0))) {
            return containsHelper((Node) n.letters.get(s.charAt(0)), s.substring(1));
        }
            return false;
    }


    public void add(String key) {
        if (root == null){
            root = new Node();

        }
        addHelper(root,key);


    }
    private void addHelper(Node n, String s) {
        if (s.length() == 1){
            if (n.letters.containsKey(s.charAt(0))){
                Node next = (Node) n.letters.get(s.charAt(0));
                next.isLastLetter = true;
                return;
            }
            Node next = new Node();
            n.letters.put(s.charAt(0), next);
            next.isLastLetter = true;

        }

        else if (n.letters.containsKey(s.charAt(0))){
            addHelper((Node) n.letters.get(s.charAt(0)), s.substring(1));

        } else {
            Node next = new Node();
            n.letters.put(s.charAt(0), next);
            addHelper((Node) n.letters.get(s.charAt(0)), s.substring(1));

        }


    }
    public List<String> keysWithPrefix(String prefix){
        Node p = root;
        List L = new ArrayList();
        int i = 0;
        String s = prefix;
        while (i < prefix.length()){
            if (p.letters.containsKey(s.charAt(0))){
                p = (Node) p.letters.get(s.charAt(0));
                s = s.substring(1);
                i ++;
            } else {
                return L;

            }

        }
        keysWithPrefixHelper(p,prefix,L);
        return L;
    }
    private void keysWithPrefixHelper(Node n,String prefix,List L){
        if (n == null){
            return;
        }
        if (n.isLastLetter){
            L.add(prefix);
        }
        for (Map.Entry<Character, Node> e : n.letters.entrySet()){
            String newPrefic = prefix + e.getKey();
            Node N = e.getValue();
            keysWithPrefixHelper(N,newPrefic,L);
        }



    }

    public String longestPrefixOf(String key){
        return "S";
    }

}



