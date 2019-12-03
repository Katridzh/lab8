package com.company;

import java.util.ArrayList;

public class MaxSearch {
    int max;
    ArrayList<Integer> list;
    Threads[] threads;

    MaxSearch(int numberOfThread, ArrayList<Integer> l) {
        list = new ArrayList<>(l);
        threads = new Threads[numberOfThread];
    }

    public class Threads extends Thread {
        private int fIndex;
        private int lIndex;
        private int tmpMax;

        public Threads(int f, int l) {
            fIndex = f;
            lIndex = l;
        }

        public void run() {
            while (fIndex <= lIndex) {
                if (list.get(fIndex) > tmpMax)
                    tmpMax = list.get(fIndex);
                fIndex++;
            }
        }

        public int getTmpMax() {
            return tmpMax;
        }
    }

    public int findMax() {
        int k = 0;
        int ind = 0;
        int n = list.size() / threads.length;
        while (ind < list.size()) {
            if (k == (threads.length - 1) && (list.size() % threads.length) != 0) {
                threads[k] = new Threads(ind, (list.size() - 1));
                ind = list.size();
            } else {
                threads[k] = new Threads(ind, (ind + n - 1));
                ind += n;
            }
            threads[k].start();
            k++;
        }
        for (int i = 0; i < threads.length; ++i) {
            if (threads[i] != null) {
                try {
                    threads[i].join();
                    if (threads[i].getTmpMax() > max)
                        max = threads[i].getTmpMax();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        return max;
    }
}
