package sorting;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author Pierre Schaus
 * <p>
 * Given an array of (closed) intervals, you are asked to implement the union operation.
 * This operation will return the minimal array of sorted intervals covering exactly the union
 * of the points covered by the input intervals.
 * For example, the union of intervals [7,9],[5,8],[2,4] is [2,4],[5,9].
 * The Interval class allowing to store the intervals is provided
 * to you.
 */
public class Union {

    /**
     * A class representing an interval with two integers. Hence, the interval is
     * [min, max].
     */
    public static class Interval implements Comparable<Interval> {

        public final int min;
        public final int max;

        public Interval(int min, int max) {
            assert (min <= max);
            this.min = min;
            this.max = max;
        }

        @Override
        public boolean equals(Object obj) {
            return ((Interval) obj).min == min && ((Interval) obj).max == max;
        }

        @Override
        public String toString() {
            return "[" + min + "," + max + "]";
        }

        @Override
        public int compareTo(Interval o) {
            if (min < o.min) return -1;
            else if (min == o.min) return max - o.max;
            else return 1;
        }
    }

    /**
     * Returns the union of the intervals given in parameters.
     * This is the minimal array of (sorted) intervals covering
     * exactly the same points as the intervals in parameter.
     *
     * @param intervals the intervals to unite.
     */
    public static Interval[] union(Interval[] intervals) {
        // TODO
        if (intervals.length == 0) return new Interval[0];
        ArrayList<Interval> res = new ArrayList<>();
        Arrays.sort(intervals);
        int min = intervals[0].min;
        int max = intervals[0].max;
        int i = 1;

        while(i < intervals.length) {
            if(intervals[i].min > max) {
                res.add(new Interval(min,max));
                min = intervals[i].min;
                max = intervals[i].max;
            } else {
                max = Math.max(max, intervals[i].max);
            }
            i++;
        }
        res.add(new Interval(min,max));
        return  res.toArray(new Interval[0]);

    }

}
