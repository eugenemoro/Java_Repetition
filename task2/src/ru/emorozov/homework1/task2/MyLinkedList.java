package ru.emorozov.homework1.task2;

public class MyLinkedList<T> {
	private class ListElement {
		private ListElement previous;
		private ListElement next;
		private T value;

		public ListElement() {
		}

		public ListElement(ListElement previous, ListElement next, T value) {
			this.previous = previous;
			this.next = next;
			this.value = value;
		}

		public ListElement getPrevious() {
			return previous;
		}

		public void setPrevious(ListElement previous) {
			this.previous = previous;
		}

		public ListElement getNext() {
			return next;
		}

		public void setNext(ListElement next) {
			this.next = next;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}
	}

	private int size;
	private ListElement header;

	public MyLinkedList() {
		this.size = 0;
		header = new ListElement();
		header.setNext(header);
		header.setPrevious(header);
	}

	public void add(T value) {
		ListElement element = new ListElement(header.getPrevious(), header, value);
		header.getPrevious().setNext(element);
		header.setPrevious(element);
		size++;
	}

	public void addFirst(T value){
		ListElement element = new ListElement(header, header.getNext(), value);
		header.getNext().setPrevious(element);
		header.setNext(element);
		size++;
	}

	public void addLast(T value){
		this.add(value);
	}

	public T get(int index) {
		if (index >= size) return null;
		ListElement result = header;
		if (index <= size / 2) {
			for (int i = 0; i <= index; i++) {
				result = result.getNext();
			}
		} else {
			for (int i = 0; i < size - index; i++) {
				result = result.getPrevious();
			}
		}
		return result.getValue();
	}

	public T getFirst() {
		return header.getNext().getValue();
	}

	public T getLast() {
		return header.getPrevious().getValue();
	}

	public int indexOf(T value) {
		for (int i = 0; i < size; i++) {
			if (this.get(i).equals(value)) return i;
		}
		return -1;
	}

	public T remove() {
		T result = this.getFirst();
		header.setNext(header.getNext().getNext());
		return result;
	}
}
