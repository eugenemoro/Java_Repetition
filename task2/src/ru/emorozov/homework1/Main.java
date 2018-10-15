package ru.emorozov.homework1;

import ru.emorozov.homework1.task1.MyArrayList;
import ru.emorozov.homework1.task2.MyLinkedList;

public class Main {
	public static void main(String[] args) {
		MyArrayList<String> arrayList = new MyArrayList<>();
		for (int i = 0; i < 100; i++) {
			arrayList.add("al " + i);
		}
		System.out.println(arrayList.indexOf("al 50"));

		MyLinkedList<String> linkedList = new MyLinkedList<>();
		for (int i = 0; i < 10; i++){
			linkedList.add("ll " + i);
		}
		linkedList.addFirst("boom");
		for (int i = 0; i < 11; i++) {
			System.out.println(linkedList.get(i));
		}
		System.out.println(linkedList.getFirst() + " " + linkedList.getLast() + " index of " + linkedList.remove());
	}
}
