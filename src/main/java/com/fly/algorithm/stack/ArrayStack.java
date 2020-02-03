package com.fly.algorithm.stack;

import sun.misc.SharedSecrets;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;


/**
 * @author 张攀钦
 * @date 2020-02-03-13:05
 * @description 数组实现栈
 */
public class ArrayStack<E> implements Stack<E> , Serializable {

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Shared empty array instance used for empty instances.
     */
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    /**
     * @author 张攀钦
     * @date 2020/2/3-13:16
     * 最大数组大小
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private static final long serialVersionUID = -5563254263807566605L;

    /**
     * @author 张攀钦
     * @date 2020/2/3-13:22
     * 判断是否并发修改
     */
    protected transient int modCount = 0;


    private int size;

    transient Object[] elementData;

    public ArrayStack( ){
            this.elementData = EMPTY_ELEMENT_DATA;
    }

    public ArrayStack( int initialCapacity){
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }
    private  Object[] copyOfRange(Object[] original,int newLength) {
        Object[] copy=new Object[newLength];
        int desIndex=newLength-size;
        System.arraycopy(original, getIndex(), copy, desIndex, size);
        return copy;
    }
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0){
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0){
            newCapacity = hugeCapacity(minCapacity);
        }
        // minCapacity is usually close to size, so this is a win:
        elementData = copyOfRange(elementData, newCapacity);
    }
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0){
            grow(minCapacity);
        }
    }

    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if(elementData==EMPTY_ELEMENT_DATA){
            Math.max(DEFAULT_CAPACITY, minCapacity);
        }
       return minCapacity;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private int getIndex(){
        return elementData.length-size;
    }
    @Override
    public Stack<E> push(E e) {
        ensureCapacityInternal(size+1);
        size++;
        elementData[getIndex()]=e;
        return this;
    }

    @Override
    public E pop() {
        modCount++;
        E e= (E) elementData[getIndex()];
        elementData[getIndex()]=null;
        size--;
        return e;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException{
        // Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // Write out size as capacity for behavioural compatibility with clone()
        s.writeInt(size);

        int index = getIndex();
        int length=index+size;
        // Write out all elements in the proper order.
        for (int i=index; i<length; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        elementData = EMPTY_ELEMENT_DATA;

        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // Read in capacity
        s.readInt(); // ignored

        if (size > 0) {
            // be like clone(), allocate array based upon size not capacity
            int capacity = calculateCapacity(elementData, size);
            SharedSecrets.getJavaOISAccess().checkArray(s, Object[].class, capacity);
            ensureCapacityInternal(size);

            Object[] a = elementData;
            int index = getIndex();
            int length=index+size;
            // Read in all elements in the proper order.
            for (int i=index; i<length; i++) {
                a[i] = s.readObject();
            }
        }
    }
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }
    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        Itr() {
            cursor=ArrayStack.this.getIndex();
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size){
                throw new NoSuchElementException();
            }
            Object[] elementData = ArrayStack.this.elementData;
            if (i >= elementData.length){
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        @Override
        public void remove() {
            if (lastRet < 0){
                throw new IllegalStateException();
            }
            checkForComodification();

            try {
                lastRet=ArrayStack.this.getIndex();
                ArrayStack.this.pop();
                cursor = lastRet+1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super E> consumer) {
            Objects.requireNonNull(consumer);
            final int size = ArrayStack.this.size;
            int i = cursor;
            if (i >= size) {
                return;
            }
            final Object[] elementData = ArrayStack.this.elementData;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            while (i != size && modCount == expectedModCount) {
                consumer.accept((E) elementData[i++]);
            }
            // update once at end of iteration to reduce heap write traffic
            cursor = i;
            lastRet = i - 1;
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
        }
    }

}
