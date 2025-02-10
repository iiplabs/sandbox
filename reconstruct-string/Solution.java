public class Solution {
  public int solution(String S, String [] grid) {
      int totalMoves = 0;

      if (S.length() > 0) {
          String firstChar = S.substring(0, 1);

          int[] prevPos = pos(firstChar, grid);
          for (int i = 1; i < S.length(); i++) {
              String nextChar = S.substring(i, i + 1);
              int[] nextPos = pos(nextChar, grid);
              // character not found
              if (nextPos[0] == 0 && nextPos[1] == 0) {
                  return 0;
              }

              int xxDist = Math.abs(nextPos[0] - prevPos[0]);
              int yyDist = Math.abs(nextPos[1] - prevPos[1]);

              totalMoves = totalMoves + xxDist + yyDist;

              prevPos = nextPos;
          }
      }

      return totalMoves;
  }

  int[] pos(String ch, String[] grid) {
      int xPos = 0;
      int yPos = 0;

      for (int yy = 0; yy < grid.length; yy++) {
          String gridElem = grid[yy];
          for (int xx = 0; xx < gridElem.length(); xx++) {
              String gridChar = gridElem.substring(xx, xx + 1);
              if (gridChar.equals(ch)) {
                  return new int[] {xx, yy};
              }
          }
      }

      return new int[] {xPos, yPos};
  }

  public static void main(String[] args) {
      String S = "ABCA";
      String[] grid = { ".A.C", ".B..", "....", "...A" };

      Solution sol = new Solution();

      System.out.println(sol.solution(S, grid));
  }
}
