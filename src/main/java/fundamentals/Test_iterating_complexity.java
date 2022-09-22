package fundamentals;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Test_iterating_complexity {
    public Test_iterating_complexity() {

    }

    public static void main(String[] args) {
        LinkedList<Integer> shortlist = new LinkedList<>();
        LinkedList<Integer> longlist = new LinkedList<>();
        double[] time = {0.0, 0.0};
        double[] complexity = {0.0, 0.0, 0.0};

        // assume I insert n elements in the list here
        for (int i = 0; i < 20000; i++) {
            shortlist.add(i);
        }

        for (int i = 0; i < 40000; i++) {
            longlist.add(i);
        }

        time[0] = System.currentTimeMillis();
        for (Integer val : shortlist) {
            System.out.println(val);
        }
        time[0] = System.currentTimeMillis() - time[0];

        time[1] = System.currentTimeMillis();
        for (Integer val : longlist) {
            System.out.println(val);
        }
        time[1] = System.currentTimeMillis() - time[1];
        complexity[0] = Math.log(time[1] / time[0]);

        time[0] = System.currentTimeMillis();
        Iterator<Integer> itr = shortlist.iterator();
        while (itr.hasNext()) {
            Integer val = itr.next();
            System.out.println(val);
        }
        time[0] = System.currentTimeMillis() - time[0];

        time[1] = System.currentTimeMillis();
        Iterator<Integer> itr2 = longlist.iterator();
        while (itr2.hasNext()) {
            Integer val = itr2.next();
            System.out.println(val);
        }
        time[1] = System.currentTimeMillis() - time[1];
        complexity[1] = Math.log(time[1] / time[0]);

        time[0] = System.currentTimeMillis();
        for (int i = 0; i < shortlist.size(); i++) {
            Integer val = shortlist.get(i);
            System.out.println(val);
        }
        time[0] = System.currentTimeMillis() - time[0];

        time[1] = System.currentTimeMillis();
        for (int i = 0; i < longlist.size(); i++) {
            Integer val = longlist.get(i);
            System.out.println(val);
        }
        time[1] = System.currentTimeMillis() - time[1];
        complexity[2] = Math.log(time[1] / time[0]);

        System.out.println(Arrays.toString(complexity));
    }
}
