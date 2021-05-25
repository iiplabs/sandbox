import java.util.Arrays;

import java.util.List;
import java.util.ArrayList;

import java.util.stream.Collectors;

class Solution {

  /*
   * Complete the 'numberOfItems' function below.
   *
   * The function is expected to return an INTEGER_ARRAY. The function accepts
   * following parameters: 1. STRING s 2. INTEGER_ARRAY startIndices 3.
   * INTEGER_ARRAY endIndices
   */
  static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
    // Write your code here
    List<Integer> result = new ArrayList<>();

    if (startIndices.size() == endIndices.size()) {
      for (int ii = 0; ii < startIndices.size(); ii++) {
        int endIndex = endIndices.get(ii);
        if (s.length() >= endIndex) {
          String invStr = s.substring(startIndices.get(ii) - 1, endIndex);
          System.out.println(String.format("invStr=%s", invStr));

          int firstIndex = invStr.indexOf("|");
          if (firstIndex > -1) {
            int lastIndex = invStr.lastIndexOf("|");
            String cc = invStr;
            if (lastIndex > -1 && lastIndex > firstIndex) {
              cc = invStr.substring(firstIndex + 1, lastIndex);
            }
            System.out.println(String.format("cc=%s firstIndex=%d lastIndex=%d", cc, firstIndex, lastIndex));
            result.addAll(Arrays.asList(cc.split("\\|")).stream()
              .map(num -> num.length()).collect(Collectors.toList()));
          }
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(numberOfItems("*|*|*|*", Arrays.asList(1, 1), Arrays.asList(1, 6)));
    System.out.println(numberOfItems("*|**|*|*", Arrays.asList(1, 1), Arrays.asList(1, 6)));
    System.out.println(numberOfItems("*|**|*|*", Arrays.asList(1, 1), Arrays.asList(1, 7)));
  }

}
