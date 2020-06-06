package com.iiplabs.linkedlist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test2 {

	public static void main(String[] args) {

		/*Stream.of("a", "b", "c").filter(fruit -> {
			System.out.println("Fruit:"+fruit);
			return true;
		});*/
		
		
		List<String> fruits = new ArrayList<String>();
		fruits.add("a");
		fruits.add("b");
		fruits.add("c");
		
		Stream<String> fs = fruits.parallelStream();
		fs.filter(fruit->{
			System.out.println("Fruit:"+fruit);
			return false;
		}).forEach(fruit->{});
		
		/*a.stream().forEach((name)->{showSupplier(()->name);});*/
		/*a.forEach(System.out::println);*/
		
		/*Consumer<String> consumer = Test2::showConsumer;
		consumer.accept("Jack");
		consumer.accept("Jill");
		consumer.accept("Ram");*/
		
	}

	public static void showSupplier(Supplier<String> name) {
		System.out.println("Welcome:" + name.get());
	}
	
	public static void p(String name) {
		name = name + "a";
		
		Runnable r=() -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
	}
}
