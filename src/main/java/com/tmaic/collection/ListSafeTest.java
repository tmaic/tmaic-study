package com.tmaic.collection;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: meichao
 * Date: 18/9/20
 * Time: 下午4:59
 */
public class ListSafeTest {

    public static class T1 extends Thread
    {
        private List<Integer> list;

        public T1(List<Integer> list)
        {
            this.list = list;
        }

        public void run()
        {
            for (Integer i : list)
            {

            }
        }
    }

    public static class T2 extends Thread{
        private List<Integer> list;

        public T2(List<Integer> list)
        {
            this.list = list;
        }

        public void run()
        {
            for (int i = 0; i < list.size(); i++)
            {
                list.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<Integer>();

        for (int i = 0; i < 10000; i++)
        {
            list.add(i);
        }

        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.remove();
        }

        T1 t1 = new T1(list);
        T2 t2 = new T2(list);
        t1.start();
        t2.start();
    }
}
