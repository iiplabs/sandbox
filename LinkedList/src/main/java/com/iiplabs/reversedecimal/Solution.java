package com.iiplabs.reversedecimal;

public class Solution {

	public static void solution(int N) {
        int enable_print = N % 10;
        while (N > 0) {
        	if (enable_print == 0 && N % 10 != 0) {
                enable_print = 1;
            }
            if (enable_print >= 1) {
        		System.out.print(N % 10);
            }
            N = N / 10;
        }
    }
	
	public static void main(String[] args) {
		solution(54321);
		System.out.println("");
		solution(10011);
		System.out.println("");
		solution(1);
		System.out.println("");
		solution(100);
		System.out.println("");
		solution(102);
		System.out.println("");
		solution(1000000000);
		System.out.println("");
		solution(1000000001);
	}
	
}
