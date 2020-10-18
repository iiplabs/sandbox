package com.iiplabs.linkedlist;

public interface CustomLinkedList<T> {

    void addFirst(T value);
    void addLast(T value);
    void insertBefore(T newValue, T beforeValue);
    void insertAfter(T newValue, T afterValue);
    T remove(T value);
    boolean contains(T value);
    void clear();
    void removeFirst();
    void removeLast();
    
}
