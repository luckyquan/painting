package com.li.paint;

import com.li.paint.utils.Utils;

public class Test {

    public static void main(String[] args) {
        int[][] data = new int[64][64];

        int[] col = new int[64];
        for (int j = 0; j < col.length; j++) {
            col[j] = 1;
        }

        for (int i = 0; i < 20; i++) {
            data[i] = col;
        }

        Utils.createImg(data);
    }
}