package sorting;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Author Pierre Schaus
 * <p>
 * Assume the following 5x5 matrix that represent a grid surface:
 * int [][] tab = new int[][] {{1,3,3,1,3},
 * {4,2,2,4,5},
 * {4,4,1,4,2},
 * {1,4,2,3,6},
 * {1,1,1,6,3}};
 * <p>
 * Each entry in the matrix represents an altitude point at the corresponding grid coordinate.
 * The goal is to implement a GlobalWarmingImpl class that extends the GlobalWarming abstract class described below.
 * <p>
 * Given a global water level, all positions in the matrix with a value <= the water level are flooded and therefore unsafe.
 * So, assuming the water level is 3, all safe points are highlighted between parenthesis:
 * <p>
 * 1 , 3 , 3 , 1 , 3
 * (4), 2 , 2 ,(4),(5)
 * (4),(4), 1 ,(4), 2
 * 1 ,(4), 2 , 3 ,(6)
 * 1 , 1 , 1 ,(6), 3}
 * <p>
 * The method you need to implement is nbSafePoints
 * - calculating the number of safe points for a given water level
 * <p>
 * Complete the code below. Pay attention to the expected time complexity of each method.
 * To meet this time complexity, you need to do some pre-computation in the constructor.
 * Feel free to create internal classes in GlobalWarmingImpl to make your implementation easier.
 * Feel free to use any method or data structure available in the Java language and API.
 */

abstract class GlobalWarming {


    protected final int[][] altitude;

    /**
     * @param altitude is a n x n matrix of int values representing altitudes (positive or negative)
     */
    public GlobalWarming(int[][] altitude) {
        this.altitude = altitude;
    }

    /**
     * @param waterLevel
     * @return the number of entries in altitude matrix that would be above
     * the specified waterLevel.
     * Warning: this is not the waterLevel given in the constructor/
     */
    public abstract int nbSafePoints(int waterLevel);

}


public class GlobalWarmingImpl extends GlobalWarming {
    private int[] alt_aux;

    public GlobalWarmingImpl(int[][] altitude) {
        super(altitude);
        // TODO
        // expected pre-processing time in the constructror : O(n^2 log(n^2))
        alt_aux = Stream.of(altitude).flatMapToInt(IntStream::of).toArray();
        Arrays.sort(this.alt_aux);
    }

    /**
     * Returns the number of safe points given a water level
     *
     * @param waterLevel the level of water
     */
    public int nbSafePoints(int waterLevel) {
        // TODO
        // expected time complexity O(log(n^2))
        int count = 0;
        if (waterLevel < this.alt_aux[0]) return this.alt_aux.length;
        if (waterLevel > this.alt_aux[this.alt_aux.length - 1]) return 0;
        int res = this.modifiedBinarySearch(this.alt_aux, waterLevel, 0, this.alt_aux.length - 1);
        while (res < this.alt_aux.length - 1 && this.alt_aux[res + 1] == waterLevel) {
            res++;
        }
        return this.alt_aux.length - res - 1;
    }

    private int modifiedBinarySearch(int[] arr, int x, int lo, int hi) {
        int mid = (lo + hi) / 2;
        while (lo <= hi) {
            if (x == arr[mid]) {
                return mid;
            } else if (x > arr[mid]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
            mid = (lo + hi) / 2;
        }
        return mid;
    }

}
