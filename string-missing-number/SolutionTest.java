import org.junit.jupiter.api.Test;

public class SolutionTest {

  @Test
  public void testSample() {
    String data = "1234567891011121415161718192021222324252627282930";

    int n = 30;

    System.out.println("Data length=" + data.length());
    int begin = 0, end = 0;
    for (int i = 1; i <= n; i++) {
      end = begin + String.valueOf(i).length();
      if (end > data.length()) {
        System.out.println("Missing number=" + i);
        break;
      }
      String s = data.substring(begin, end);
      // System.out.println("i=" + i + " " + begin + "-" + end + " s=" + s);
      if (i != Integer.parseInt(s)) {
        System.out.println("Missing number=" + i);
        break;
      }
      begin = end;
    }
  }

}
