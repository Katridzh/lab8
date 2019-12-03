package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UsualMatrix a = new UsualMatrix(3,3);
       UsualMatrix b = new UsualMatrix(3,3);
        for(int i = 0; i < 3; i++){
            for(int j = 0;  j < 3; j++) {
                    b.setElement(i, j, (int) (Math.random() * 10));
                a.setElement(j, i, (int)(Math.random() * 10));
            }
        }
        System.out.println("Matrix A:");
        System.out.println(a);
        System.out.println("Matrix B:");
        System.out.println(b);
        System.out.println("Matrix product A*B:");
        System.out.println(a.product(b));
        Scanner read = new Scanner(System.in);
        System.out.print("Enter the number of threads: ");
        int thread = read.nextInt();
        /*ParallelMatrixProduct p = new ParallelMatrixProduct(thread, a, b);
        System.out.println(p.product());
        System.out.println(a.product(b));*/
        a = new UsualMatrix(1000, 1000);
        b = new UsualMatrix(1000, 1000);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                a.setElement(i, j, (int) (Math.random() * 10));
                b.setElement(j, i, (int) (Math.random() * 10));
            }
        }
        long time1 = System.currentTimeMillis();
        a.product(b);
        time1 = System.currentTimeMillis() - time1;
        System.out.println("Time to product usual: " + time1 + "\n");
        for (int i = 1; i <= thread; i++) {
            ParallelMatrixProduct p = new ParallelMatrixProduct(i, a, b);
            long time2 = System.currentTimeMillis();
            p.product();
            time2 = System.currentTimeMillis() - time2;
            System.out.println("Time to product parallel " + i + ": " + time2 + "\n");
        }
    }
}
