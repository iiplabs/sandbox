import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionTest {

    @Test
    public void solutionTestOn2() {
        int[] elements = {5, 3, 1, 2, 1, 2, 3, 3};
        int[] uniqueElements = {};
        int[] reps = {};

        int rep = 0;
        for (int ii = 0; ii < elements.length; ii++) {
            int el = elements[ii];
            rep = 0;
            for (int jj = 0; jj < elements.length; jj++) {
                if (el == elements[jj]) {
                    rep++;
                }
            }
            if (rep > 1 && !ArrayUtils.contains(uniqueElements, el)) {
                uniqueElements = ArrayUtils.add(uniqueElements, el);
                reps = ArrayUtils.add(reps, rep);
            }
        }

        /* for (int ii = 0; ii < uniqueElements.length; ii++) {
            System.out.println(uniqueElements[ii] + " -> " + reps[ii]);
        } */

        assertTrue(Arrays.equals(new int[]{3, 1, 2}, uniqueElements));
        assertTrue(Arrays.equals(new int[]{3, 2, 2}, reps));
    }

    @Test
    public void solutionTestOn() {
        int[] elements = {5, 3, 1, 2, 1, 2, 3, 3};

        Map<Integer, Integer> counters = new HashMap<>();

        for (int ii = 0; ii < elements.length; ii++) {
            int el = elements[ii];
            Integer count = counters.get(el);
            if (count == null) {
                counters.put(el, 1);
            } else {
                counters.put(el, ++count);
            }
        }

        /* counters.entrySet().stream().filter(e -> { return e.getValue() > 1; })
                .forEach(e -> System.out.println(e.getKey() + ":" + e.getValue())); */

        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 2);
        expected.put(2, 2);
        expected.put(3, 3);

        assertTrue(counters.entrySet().stream().filter(e -> { return e.getValue() > 1; })
                .allMatch(e -> e.getValue().equals(expected.get(e.getKey()))));
    }

}
