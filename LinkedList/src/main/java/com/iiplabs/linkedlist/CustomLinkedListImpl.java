package com.iiplabs.linkedlist;

public class CustomLinkedListImpl<T> implements CustomLinkedList<T> {

	private Node<T> head;

    static class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;
        
        Node(T d) { 
        	value = d; 
            next = null;
            previous = null;
        }
        
    }
    
	@Override
	public String toString() {
		String s = "";
		
		if (null != head) {
			Node<T> last = head;
			boolean le = false;
			do {
				s += last.value;
				last = last.next;
				le = null == last.next;
			} while (!le);
			if (null != last) {
				s += last.value;
			}
		}
		
		return s;
	}

	@Override
	public synchronized void addFirst(T value) {
		head = new Node<T>(value);
	}

	@Override
	public synchronized void addLast(T value) {
		if (null == head) {
			addFirst(value);
		} else {
			Node<T> last = head;
			while (null != last.next) { 
				last = last.next;
            }
			Node<T> newNode = new Node<T>(value);
			newNode.previous = last;
			last.next = newNode;
		}
	}

	@Override
	public synchronized void insertBefore(T newValue, T beforeValue) {
		if (null != head) {
			Node<T> last = head;
			do {
				if (beforeValue.equals(last.value)) {
					boolean isHead = false;
					Node<T> newNode = new Node<T>(newValue);
					if (null != last.previous) {
						last.previous.next = newNode;
						newNode.previous = last.previous;
					} else {
						isHead = true;
					}
					newNode.next = last;
					
					if (isHead) {
						head = newNode;
					}
					
					break;
				}
				last = last.next;
            } while (null != last.next);
		}
	}

	@Override
	public synchronized void insertAfter(T newValue, T afterValue) {
		if (null != head) {
			Node<T> last = head;
			do {
				if (afterValue.equals(last.value)) {
					Node<T> newNode = new Node<T>(newValue);
					if (null != last.next) {
						last.next.previous = newNode;
						newNode.next = last.next;
					}
					last.next = newNode;					
					newNode.previous = last;
					
					break;
				}
				last = last.next;
            } while (null != last.next);
			if (afterValue.equals(last.value) && null == last.next) {
				Node<T> newNode = new Node<T>(newValue);
				newNode.previous = last;
				last.next = newNode;				
			}
		}
	}

	@Override
	public synchronized T remove(T value) {
		if (null != head) {
			Node<T> last = head;
			do {
				boolean isHead = last.previous == null;
				if (value.equals(last.value)) {
					if (null == last.previous && null == last.next) {
						head = null;
					} else {
						if (null != last.previous) {
							last.previous.next = last.next;
						}
						if (null != last.next) {
							if (isHead) {
								last.next.previous = null;
								head = last.next;
							} else {
								last.next.previous = last.previous;
							}
						}
					}
				}
				last = last.next;
			} while (null != last.next);
		}
		return value;
	}

	@Override
	public synchronized boolean contains(T value) {
		boolean ret = false;
		if (null != head) {
			Node<T> last = head;
			do {
				if (value.equals(last.value)) {
					return true;
				}
				last = last.next;
			} while (null != last.next);
			if (null != last && value.equals(last.value)) {
				ret = true;
			}
		}
		return ret;
	}

	@Override
	public synchronized void clear() {
		head = null;
	}

	@Override
	public synchronized void removeFirst() {
		if (null != head) {
			if (null == head.next) {
				clear();
			} else {
				head.next.previous = null;
				head = head.next;
			}
		}
	}

	@Override
	public synchronized void removeLast() {
		if (null != head) {
			if (null == head.next) {
				clear();
			} else {
				Node<T> last = head;
				do {
					last = last.next;
				} while (null != last.next);
				last.previous.next = null;
				last = null;
			}
		}
	}

}
