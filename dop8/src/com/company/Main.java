package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
                list.add((int) (Math.random() * 78));
        }
        list.add(10);
        int max=0;
        MaxSearch mS = new MaxSearch(1, list);
        long time = System.nanoTime();
        max=mS.findMax();
        time=System.nanoTime() - time;
        System.out.println("Однопоточный поиск: Максимум --"+ max+" время: "+time);
        for (int i = 1; i <= 4; i++) {
            mS = new MaxSearch(i, list);
            time = System.nanoTime();
            max=mS.findMax();
            System.out.println("Многопоточный поиск "+ i + " потоков: максимум --"+ max+" время: "+ (System.nanoTime() - time));
       }
    }
}
