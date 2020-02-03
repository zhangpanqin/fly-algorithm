package com.fly.algorithm.stack;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 张攀钦
 * @date 2020-02-03-14:55
 * @description
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        Iterator<Object> iterator = objects.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
