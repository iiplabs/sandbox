import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        int[] toArray() {
            return new int[] { x, y };
        }

        @Override
        public String toString() {
            return String.format("x=%d,y=%d", x, y);
        }
    }

    Map<String, Collection<Position>> buidDict(String [] grid) {
        Map<String, Collection<Position>> dict = new HashMap<>();
        for (int yy = 0; yy < grid.length; yy++) {
            String gridElem = grid[yy];
            for (int xx = 0; xx < gridElem.length(); xx++) {
                String gridChar = gridElem.substring(xx, xx + 1);
                if (!gridChar.equalsIgnoreCase(".")) {
                    Collection<Position> charAllPos = new ArrayList<>();
                    if (dict.containsKey(gridChar)) {
                        charAllPos.addAll(dict.get(gridChar));
                    }
                    charAllPos.add(new Position(xx, yy));
                    dict.put(gridChar, charAllPos);
                }
            }
        }
        return dict;
    }

    boolean checkSourceString(String S, Map<String, Collection<Position>> dict) {
        for (int ss = 0; ss < S.length(); ss++) {
            String ch = S.substring(ss, ss + 1);
            if (!dict.containsKey(ch)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Every character may appear in the grid no more than twice
     * @param dict
     * @return
     */
    boolean checkDict(Map<String, Collection<Position>> dict) {
        int MAX_DUPL_CHARS = 2;

        for (String ch : dict.keySet()) {
            if (dict.get(ch).size() > MAX_DUPL_CHARS) {
                return false;
            }
        }
        return true;
    }

    int calcDist(int[] prevPos, int[] pos) {
        int xxDist = Math.abs(pos[0] - prevPos[0]);
        int yyDist = Math.abs(pos[1] - prevPos[1]);
        return xxDist + yyDist;
    }

    int search(int[] curPos, int index, String S, Map<String, Collection<Position>> dict) {
        if (index == S.length() - 1) {
            return 0;
        }
        int ret = Integer.MAX_VALUE;

        String curChar = S.substring(index, index + 1);
        String nextChar = S.substring(index + 1, index + 1 + 1);
        if (curChar.equals(nextChar)) {
            return search(curPos, index + 1, S, dict);
        }
        for (Position nextPos : dict.get(nextChar)) {
            int d = calcDist(curPos, nextPos.toArray());
            int d2 = search(nextPos.toArray(), index + 1, S, dict);
            ret = Math.min(ret, d + d2);
        }
        return ret;
    }

    public int solution(String S, String [] grid) {
        int RECONST_NOT_POSSIBLE = -1;
        if (S.length() == 0) {
            return RECONST_NOT_POSSIBLE;
        }

        Map<String, Collection<Position>> dict = buidDict(grid);
        System.out.println(dict);
        if (!checkDict(dict)) {
            return RECONST_NOT_POSSIBLE;
        }

        if (!checkSourceString(S, dict)) {
            return RECONST_NOT_POSSIBLE;
        }

        int totalMoves = Integer.MAX_VALUE;
        String firstChar = S.substring(0, 1);
        for (Position startPos : dict.get(firstChar)) {
            totalMoves = Math.min(totalMoves, search(startPos.toArray(), 0, S, dict));
        }

        return totalMoves;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // -1
        System.out.println(String.format("total: %d",
                sol.solution("", new String[] { ".A.C", ".B..", "....", "...A" })));
        // 2
        System.out.println(String.format("total: %d",
                sol.solution("AC", new String[] { ".A.C", ".B..", "....", "...A" })));
        // 6
        System.out.println(String.format("total: %d",
                sol.solution("ABCA", new String[] { ".A.C", ".B..", "....", "...A" })));
        // 13
        System.out.println(String.format("total: %d",
                sol.solution("KLLRML", new String[] { "K....", "S...L", "....R", "LX...", "XM..S" })));
    }
}
