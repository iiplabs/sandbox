package com.iiplabs.linkedlist;

public class Test {

	public static void main(String[] args) {
		CustomLinkedList<Integer> c = new CustomLinkedListImpl<Integer>();
		c.addFirst(1);
		c.addLast(2);
		c.addLast(7);
		c.addLast(5);
		c.addLast(4);
		c.addLast(8);
		// 127548
		System.out.println(c.toString());

		c.removeFirst();
		// 27548
		System.out.println(c.toString());
		
		c.removeLast();
		// 2754
		System.out.println(c.toString());
		
		c.insertBefore(3, 7);
		// 23754
		System.out.println(c.toString());
		
		c.insertAfter(6, 5);
		// 237564
		System.out.println(c.toString());
		
		c.insertAfter(1, 4);
		// 2375641
		System.out.println(c.toString());
		
		c.insertBefore(9, 2);
		// 92375641
		System.out.println(c.toString());
		
		c.remove(5);
		// 9237641
		System.out.println(c.toString());
		
		c.remove(9);
		// 237641
		System.out.println(c.toString());
		
		c.insertBefore(2, 2);
		// 2237641
		System.out.println(c.toString());
		
		c.remove(2);
		// 37641
		System.out.println(c.toString());
	}

}
