package com.rogers.media.supplychain.showdeliveryandtrackingapp.util;

import java.util.Arrays;

public class Solution {
    public int solution(String S, String [] grid) {
        int RECONST_NOT_POSSIBLE = -1;

        int totalMoves = 0;

        if (S.length() > 0) {
            String firstChar = S.substring(0, 1);
            int[][] firstCharAllPos = allPos(firstChar, grid);
            if (firstCharAllPos.length == 0 || firstCharAllPos.length > 2) {
                return  RECONST_NOT_POSSIBLE;
            }

            int[] prevPos = firstCharAllPos[0];
            // System.out.println(String.format("firstChar - %s, firstCharAllPos.length = %d", firstChar, firstCharAllPos.length));
            for (int i = 1; i < S.length(); i++) {
                String nextChar = S.substring(i, i + 1);
                int[][] nextPos = allPos(nextChar, grid);
                // character not found
                if (nextPos.length == 0 || nextPos.length > 2) {
                    return RECONST_NOT_POSSIBLE;
                }

                // System.out.println(String.format("nextChar - %s, nextPos.length = %d", nextChar, nextPos.length));
                /* for (int ii = 0; ii < nextPos.length; ii++) {
                    System.out.println(String.format("%s - %s", nextChar, Arrays.toString(nextPos[ii])));
                } */

                int[] minDistPos = findMinDistPos(prevPos, nextPos);
                int dist = calcDist(prevPos, minDistPos);
                System.out.println(dist);

                totalMoves = totalMoves + dist;

                prevPos = minDistPos;
            }
        }

        return totalMoves > 0 ? totalMoves : RECONST_NOT_POSSIBLE;
    }

    int[] findMinDistPos(int[] prevPos, int[][] allPos) {
        int minDistIndex = 0;
        int minDist = calcDist(prevPos, allPos[minDistIndex]);

        for (int ii = 0; ii < allPos.length; ii++) {
            int dist = calcDist(prevPos, allPos[ii]);
            if (dist < minDist) {
                minDist = dist;
                minDistIndex = ii;
            }
        }

        return allPos[minDistIndex];
    }

    int calcDist(int[] prevPos, int[] pos) {
        int xxDist = Math.abs(pos[0] - prevPos[0]);
        int yyDist = Math.abs(pos[1] - prevPos[1]);
        return xxDist + yyDist;
    }

    int[][] allPos(String ch, String[] grid) {
        int[][] allPos = new int[][] {};

        for (int yy = 0; yy < grid.length; yy++) {
            String gridElem = grid[yy];
            for (int xx = 0; xx < gridElem.length(); xx++) {
                String gridChar = gridElem.substring(xx, xx + 1);
                if (gridChar.equals(ch)) {
                    allPos = addNewPos(allPos, new int[] {xx, yy});
                }
            }
        }

        return allPos;
    }

    int[][] addNewPos(int[][] currentPos, int[] newPos) {
        int[][] pos = Arrays.copyOf(currentPos, currentPos.length + 1);
        pos[pos.length - 1] = newPos;
        return pos;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(String.format("total: %d",
                sol.solution("ABCA", new String[] { ".A.C", ".B..", "....", "...A" })));
        System.out.println(String.format("total: %d",
                sol.solution("KLLRMS", new String[] { "K....", "S...L", "....R", "LX...", "XM..S" })));
    }
}
