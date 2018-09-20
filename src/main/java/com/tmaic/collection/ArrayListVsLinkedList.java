package com.tmaic.collection;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: meichao
 * Date: 18/9/19
 * Time: 下午4:52
 * Desc: 插入删除，linkedList一定比ArrayList快吗
 */
public class ArrayListVsLinkedList {


    public static void main(String[] args) {

        System.out.println("顺序插入数据>>>>>>>>>>>Start");

        LinkedList ll = new LinkedList();
        ArrayList al = new ArrayList();

        for (int i= 0;i<1000000;i++){
            ll.add(i);
            al.add(i);
        }

        long start3 = System.currentTimeMillis();

        for (int i= 600000;i<1000000;i++){
            ll.add(i+900);
        }

        System.out.println("LinkedList time = [" + (System.currentTimeMillis() - start3) + "]");

        long start4 = System.currentTimeMillis();

        for (int i= 600000;i<1000000;i++){
            al.add(i+900);
        }
        System.out.println("ArrayList time = [" + (System.currentTimeMillis() - start4) + "]");


        System.out.println("顺序插入数据>>>>>>>>>>>End");



        /**************   我是分隔符     ********/


        System.out.println("插入的数据都在后半部分>>>>>>>>>>>Start");

        LinkedList ll1 = new LinkedList();
        ArrayList al1 = new ArrayList();

        for (int i= 0;i<100000;i++){
            ll1.add(i);
            al1.add(i);
        }

        long start = System.currentTimeMillis();

        for (int i= 60000;i<100000;i++){
            ll1.add(i,i+900);
        }

        System.out.println("LinkedList time = [" + (System.currentTimeMillis() - start) + "]");

        long start1 = System.currentTimeMillis();

        for (int i= 60000;i<100000;i++){
            al1.add(i,i+900);
        }

        System.out.println("ArrayList time = [" + (System.currentTimeMillis() - start1) + "]");


        System.out.println("插入的数据都在后半部分>>>>>>>>>>>End");
    }

}
