package com.iiplabs.smallestint;

import java.util.stream.IntStream;

public class Solution {

	public static void main(String[] args) {
		int[] A = {-2, -1, 0, 1, 2, 3};
		
		bubbleSort(A);
		
		int first = 0;
		if (A.length > 0) {
			first = A[0];
		}
		int last = 0;
		if (A.length > 0) {
			last = A[A.length-1];
		}		
		System.out.println(first + " " +  last);
		
        for (int i=0; i < A.length; i++){  
            System.out.print(A[i] + " ");  
        }
        
        for (int zz=first+1; zz <= last+1; zz++) {
        	boolean found = false;
            for (int i=0; i < A.length; i++) {
            	if (zz == A[i]) {           		
            		found = true;
            		break;
            	}
            }
            if (!found) {
            	System.out.println("found " + zz);
            	break;
            }
        }
	}
	
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        IntStream.range(0, n - 1)
          .flatMap(i -> IntStream.range(1, n - i))
          .forEach(j -> {
              if (arr[j - 1] > arr[j]) {
                  int temp = arr[j];
                  arr[j] = arr[j - 1];
                  arr[j - 1] = temp;
              }
          });
    }
    
}
