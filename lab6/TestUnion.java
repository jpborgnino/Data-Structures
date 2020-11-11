import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestUnion {
    @Test
    public void Test(){
        UnionFind U = new UnionFind(8);
        assertFalse(U.connected(2,3));
        U.union(2,3);
        U.union(2,4);
        U.union(7,0);
        U.union(7,1);
        U.union(7,6);
        U.union(7,2);
        System.out.println(U.connected(4,3));
        int a =2;


        assertTrue(U.connected(2,0));


    }
}
