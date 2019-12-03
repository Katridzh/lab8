package com.company;

public class UsualMatrix {
    private int [][] array;
    private int row;
    private int column;

    public UsualMatrix(int r, int c) {
        row=r;
        column=c;
        array = new int [row][column];
    }

    public UsualMatrix product(UsualMatrix a) {
        if(column == a.getRow()) {
            UsualMatrix res = new UsualMatrix(row, a.getColumn());
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < a.getColumn(); j++) {
                    res.setElement(i,j,0);
                    for (int k = 0; k < column; k++) {
                        res.setElement(i, j, res.getElement(i, j) + (this.getElement(i, k) * a.getElement(k, j)));
                    }
                }
            }
            return res;
        }
        else
            throw new RuntimeException("matrix's sizes do not match!");
    }

    public int getRow() { return row; }

    public int getColumn() { return column; }

    public void setElement(int row1, int column1, int value){ array[row1][column1] = value; }

    public int getElement(int row1, int column1){ return array[row1][column1]; }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                res.append(" ").append(getElement(i,j));
            res.append("\n");
        }
        return  res.toString();
    }

    public boolean equals(Object obj) {
        if(obj == null||this.getClass()!=obj.getClass()){
            return(false);
        }
        if(this==obj){
            return true;
        }
        else{
            UsualMatrix tmp=(UsualMatrix) obj;
            if(row!=tmp.row|| column!=tmp.column){
                return false;
            }
            for(int i = 0; i <row; i++) {
                for (int j = 0; j < column; j++) {
                    if (this.getElement(i,j) != tmp.getElement(i,j)) {
                        return (false);
                    }
                }
            }
        }
        return(true);
    }
}
