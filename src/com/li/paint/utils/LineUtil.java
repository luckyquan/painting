package com.li.paint.utils;

import java.awt.*;

public class LineUtil {

            //dda算法
            public static void dda(Graphics g, int x0, int y0, int x1, int y1) {
                int dx, dy, eps1, k;
                float x, y, xlncre, ylncre;
                dx = x1 - x0;
                dy = y1 - y0;
                k = dy / dx;
                x = x0;
                y = y0;
                if (Math.abs(dx) > Math.abs(dy)) {
                    eps1 = Math.abs(dx);
                } else {
                    eps1 = Math.abs(dy);
                }
                xlncre = (float) dx / (float) eps1;
        ylncre = (float) dy / (float) eps1;
        for (k = 0; k <= eps1; k++) {
            g.drawOval((int) (x + 0.5), (int) (y + 0.5), 1, 1);
            y += ylncre;
            x += xlncre;
        }
    }

    //中点划线算法
    public static void MidpointLine(Graphics g, int x0, int y0, int x1, int y1) {
        int a, b, d1, d2, d, x, y;
        float m;
        if (x1 < x0) {
            d = x0;
            x0 = x1;
            x1 = d;
            d = y0;
            y0 = y1;
            y1 = d;
        }
        a = y0 - y1;
        b = x1 - x0;
        if (b == 0) m = -1 * a * 100;
        else m = (float) a / (x0 - x1);
        x = x0;
        y = y0;
        g.drawOval(x, y, 1, 1);
        if (m >= 0 && m <= 1) {
            d = 2 * a + b;
            d1 = 2 * a;
            d2 = 2 * (a + b);
            while (x < x1) {
                if (d <= 0) {
                    x++;
                    y++;
                    d += d2;
                } else {
                    x++;
                    d += d1;
                }
                g.drawOval(x, y, 1, 1);
            }
        } else if (m <= 0 && m >= -1) {
            d = 2 * a - b;
            d1 = 2 * a - 2 * b;
            d2 = 2 * a;
            while (x < x1) {
                if (d > 0) {
                    x++;
                    y--;
                    d += d1;
                } else {
                    x++;
                    d += d2;
                }
                g.drawOval(x, y, 1, 1);
            }
        } else if (m > 1) {
            d = a + 2 * b;
            d1 = 2 * (a + b);
            d2 = 2 * b;
            while (y < y1) {
                if (d > 0) {
                    x++;
                    y++;
                    d += d1;
                } else {
                    y++;
                    d += d2;
                }
                g.drawOval(x, y, 1, 1);
            }
        } else {
            d = a - 2 * b;
            d1 = -2 * b;
            d2 = 2 * (a - b);
            while (y > y1) {
                if (d <= 0) {
                    x++;
                    y--;
                    d += d2;
                } else {
                    y--;
                    d += d1;
                }
                g.drawOval(x, y, 1, 1);
            }
        }
    }

    //bresenham算法
    public static void bresenhan(Graphics g, int x0, int y0, int x1, int y1) {
        int x = x0;
        int y = y0;

        int w = x1 - x0;
        int h = y1 - y0;

        int dx1 = w < 0 ? -1 : (w > 0 ? 1 : 0);
        int dy1 = h < 0 ? -1 : (h > 0 ? 1 : 0);

        int dx2 = w < 0 ? -1 : (w > 0 ? 1 : 0);
        int dy2 = 0;

        int fastStep = Math.abs(w);
        int slowStep = Math.abs(h);
        if (fastStep <= slowStep) {
            fastStep = Math.abs(h);
            slowStep = Math.abs(w);

            dx2 = 0;
            dy2 = h < 0 ? -1 : (h > 0 ? 1 : 0);
        }
        int numerator = fastStep >> 1;

        for (int i = 0; i <= fastStep; i++) {
            g.drawOval(x, y, 1, 1);
            numerator += slowStep;
            if (numerator >= fastStep) {
                numerator -= fastStep;
                x += dx1;
                y += dy1;
            } else {
                x += dx2;
                y += dy2;
            }
        }
    }
}
