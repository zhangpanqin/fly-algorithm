package com.fly.algorithm.stack;

/**
 * @author 张攀钦
 * @date 2020-02-03-12:22
 * @description 定义栈
 */
public interface Stack<E> extends Iterable<E> {


    /***
     * 添加元素到栈顶
     * @author 张攀钦
     * @title push
     * @param e
     * @return com.fly.algorithm.stack.Stack<E>
     */

    Stack<E> push(E e);

    /**
     * @author 张攀钦
     * @date 2020/2/3-12:27
     * 弹出栈顶元素
     */
    /**
     * 弹出栈顶元素
     *
     * @return E
     * @author 张攀钦
     * @title pop
     */
    E pop();

    /**
     * 栈中元素数量
     *
     * @return int
     * @author 张攀钦
     * @title size
     */

    int size();

    /**
     * 判断是否为空
     * @return boolean
     * @author 张攀钦
     * @title isEmpty
     */

    boolean isEmpty();
}
