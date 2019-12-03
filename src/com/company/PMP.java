package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PMP {
    private int threadCount;
    private UsualMatrix aMtr;
    private UsualMatrix bMtr;
    private UsualMatrix resMtr;

    public PMP(int count, UsualMatrix a, UsualMatrix b){
        threadCount = count;
        aMtr = a;
        bMtr = b;
        resMtr =  new UsualMatrix(aMtr.getRow(), bMtr.getColumn());
    }

    public UsualMatrix parallelProduct() throws InterruptedException{
        ExecutorService exServ = Executors.newFixedThreadPool(this.threadCount);
        for(int i = 0; i < aMtr.getColumn(); i++) {
            exServ.submit(new myThread(i));
        }
        exServ.shutdown();
        exServ.awaitTermination(10, TimeUnit.SECONDS);
        return this.resMtr;

    }
    private class myThread implements Runnable{
        private int stringIndex;

        public myThread(int index) {

            this.stringIndex = index;
        }
        @Override
        public void run(){
            int counter = 0;
            for(int j = 0; j < bMtr.getColumn(); j++){
                for(int k = 0; k < aMtr.getColumn(); k++){
                    counter = counter+aMtr.getElement(this.stringIndex, k)*bMtr.getElement(k, j);
                }
                resMtr.setElement(this.stringIndex, j, counter);
                counter = 0;
            }
        }
    }
}
