import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void TestRandom() {
        StudentArrayDeque<Integer> Student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> Actual = new ArrayDequeSolution<>();
        int p = 0;
        String message = "";
        for (int i = 0; i < 300; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                Student.addLast(i);
                message = (message + "addLast" + "(" + i + ")\n");
                Actual.addLast(i);
                p += 1;
                if (p > 3) {
                    Student.removeFirst();
                    message = (message + "removeFirst()\n");
                    Actual.removeFirst();
                    Student.removeFirst();
                    message = (message + "removeFirst()\n");
                    Actual.removeFirst();
                    p = 0;
                }
            } else {
                Student.addFirst(i);
                message = (message + "addFirst" + "(" + i + ")\n");
                Actual.addFirst(i);
                p += 1;
                if (p > 3) {
                    Student.removeFirst();
                    message = (message + "removeFirst()\n");
                    Actual.removeFirst();
                    Student.removeLast();
                    message = (message + "removeLast()\n");
                    Actual.removeLast();
                    p = 0;
                }
            }
        }
        int Test = StdRandom.uniform(Student.size());
        int ActualVal = Actual.get(Test);
        message = (message + "get" + "(" + Test + ")\n");
        int ActualStudent = Student.get(Test);

        assertEquals("\n"+message, ActualVal, ActualStudent);
    }

}


