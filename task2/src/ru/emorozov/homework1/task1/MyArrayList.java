package ru.emorozov.homework1.task1;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MyArrayList<T> {
	private T[] content;
	private int size = 16;
	private int occupied = 0;

	public MyArrayList() {
		final T[] c = (T[]) Array.newInstance(Object.class, size);
		this.content = c;
	}

	public MyArrayList(int size) {
		this.size = size;
		final T[] c = (T[]) Array.newInstance(Object.class, size);
		this.content = c;
	}

	public boolean add(T element){
		if (occupied >= size) {
			expandContent(content);
		}
		content[occupied] = element;
		occupied++;
		return true;
	}

	public void add(T element, int index){
		content[index] = element;
	}

	public T get(int index) {
		return content[index];
	}

	public boolean remove(int index) {
		if (index < occupied) {
			for (int i = index; i < occupied; i++){
				content[i] = content[i + 1];
			}
			occupied--;
			return true;
		}
		return false;
	}

	public boolean remove(T element) {
		int index = indexOf(element);
		if (index != -1) {
			remove(index);
			return true;
		}
		return false;
	}

	private void expandContent(T[] content) {
		this.size *= 2;
		this.content = Arrays.copyOf(content, size);
	}

	public int size() {
		return occupied;
	}

	public int indexOf(T element){
		int index = -1;
		for (int i = 0; i < this.size(); i++) {
			if (element.equals(content[i])) {
				index = i;
				break;
			}
		}
		return index;
	}
}
