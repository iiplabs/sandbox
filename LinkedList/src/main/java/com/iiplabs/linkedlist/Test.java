package com.iiplabs.linkedlist;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomLinkedList<Integer> c = new CustomLinkedListImpl<Integer>();
		c.addFirst(1);
		c.addLast(2);
		c.addLast(7);
		c.addLast(5);
		c.addLast(4);
		c.addLast(8);
		
		System.out.println(c.toString());
		//System.out.println(c);
	}

}
