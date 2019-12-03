package com.company;

public class ParallelMatrixProduct {
    private int NumberOfThread;

    private UsualMatrix left;
    private UsualMatrix right;
    private UsualMatrix res;
    private Threads[] threads;

    public class Threads extends Thread {
        private int NumberOfString;
        private int counter;

        public Threads(int number, int str) {
            NumberOfString = number;
            counter = str;
        }

        public void run() {
            while (NumberOfString <= counter) {
                for (int i = 0; i < right.getColumn(); i++) {
                    int value = 0;
                    for (int j = 0; j < left.getColumn(); ++j) {
                        value += left.getElement(NumberOfString, j) * right.getElement(j, i);
                        res.setElement(NumberOfString, i, value);
                    }
                }
                NumberOfString++;
            }
        }
    }

    public ParallelMatrixProduct(int N, UsualMatrix l, UsualMatrix r) {
        NumberOfThread = N;
        threads = new Threads[N];
        left = l;
        right = r;
        res = new UsualMatrix(left.getRow(), right.getColumn());
    }

    public UsualMatrix product() {
        int k = 0;
        int i = 0;
        int n = left.getRow() / NumberOfThread;
        while (i < left.getRow()) {
            if (k == NumberOfThread && (left.getRow() % NumberOfThread) != 0) {
                k = k - NumberOfThread;
                threads[k] = new Threads(i, (left.getRow() - 1));
            } else
                threads[k] = new Threads(i, (i + n - 1));
            threads[k].start();
            i += n;
            k++;
        }
        for (i = 0; i < threads.length; ++i) {
            if (threads[i] != null) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }
}
