package main;

import myUtil.MyLinkedList;

import java.util.ListIterator;

/**
 * Created by wookie on 6/3/16.
 */
public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.push_back(1);
        list.push_back(2);

        for(int i : list) {
            System.out.println(i);
        }

        ListIterator<Integer> iter = list.listIterator();
        while(iter.hasNext()) {
            iter.remove();
        }
        iter.remove();
        for(int i : list) {
            System.out.println(i);
        }
    }
}
