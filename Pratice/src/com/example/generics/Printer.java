package com.example.generics;

import java.util.*;

public class Printer<T extends Animal> {
	T thingsToPrint;
	public Printer(T thingsToPrint) {
		this.thingsToPrint = thingsToPrint;
	}
	
	public void print() {
		thingsToPrint.eat();
	}
	
	public static <T,V> T shout(T thingsToShout, V otherThingToShout) {
		System.out.println(thingsToShout);
		System.out.println(otherThingToShout);
		return thingsToShout;
	}
	
	public static void printList(List<? extends Animal> list) {
		System.out.print("test");
	}
	
	public static void main(String[] args) {
		Printer<Cat> catPrinter = new Printer<>(new Cat());
		catPrinter.print();
		
		
		Printer<Dog> dogPrinter = new Printer<>(new Dog());
		dogPrinter.print();
		
		shout("aaaa",123);
		shout(456,"bbbb");
		
//		List list = new ArrayList<>();
//		list.add(new Cat());
//		
//		printList(list);
	}
	
	

}
