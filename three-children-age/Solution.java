import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    int[] primeAge = {2, 3, 5, 7, 11, 13, 17, 19, 21, 23, 31};
    int ageDiffer = 16;
    int totalAge = 41;

    for (int ii = 0; ii < primeAge.length; ii++) {
        int childX = primeAge[ii];
        int childY = childX + ageDiffer;
        if (IntStream.of(primeAge).anyMatch(y -> y == childY)) {
            int childZ = totalAge - childX - childY;
            if (IntStream.of(primeAge).anyMatch(z -> z == childZ)) {
                System.out.println(String.format("%d %d %d", childX, childY, childZ));
            }
        }
    }
  }
}
