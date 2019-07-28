package com.li.paint.panel;

import com.li.paint.panel.basic.GraphicPanel;
import com.li.paint.utils.Common;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

public class PolygonPanel extends GraphicPanel {

    private int[] xPoints;
    private int[] yPoints;

//多边形内裁剪zzq.add

    public PolygonPanel(int startX, int startY, int endX, int endY, List<Point> pointList) {
        super(startX, startY, endX, endY);

        if (null != pointList && pointList.size() > 0) {
            xPoints = new int[pointList.size()];
            yPoints = new int[pointList.size()];

            for (int i = 0; i < pointList.size(); i++) {
                Point point = pointList.get(i);
                xPoints[i] = point.x - panelX;
                yPoints[i] = point.y - panelY;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int[] ratioXPoints = new int[xPoints.length];
        int[] ratioYPoints = new int[yPoints.length];

        for (int i = 0; i < xPoints.length; i++) {
            ratioXPoints[i] = (int) ((double) xPoints[i] * ratioWidth);
        }

        for (int i = 0; i < yPoints.length; i++) {
            ratioYPoints[i] = (int) ((double) yPoints[i] * ratioHeight);
        }

        Polygon polygon1 = new Polygon(ratioXPoints, ratioYPoints, ratioXPoints.length);
        Area area1 = new Area(polygon1);
        //内裁剪
        for (Rectangle rec : outerCutList) {
            int x = (int) (rec.x * ratioWidth);
            int y = (int) (rec.y * ratioHeight);
            int width = (int) (rec.width * ratioWidth);
            int height = (int) (rec.height * ratioHeight);

            g.clipRect(x, y, width, height);
            System.out.println("polygonpanel");


            Area area2 = new Area(new Rectangle(x, y, width, height));
            area2.intersect(area1);

            List<Integer> xPoints = new ArrayList<>();
            List<Integer> yPoints = new ArrayList<>();

            //1
            for (int i = x; i <= x + width; i++) {
                if (area2.contains(i, y)) {
                    xPoints.add(i);
                    yPoints.add(y);
                    break;
                }
            }
            for (int i = x + width; i >= x; i--) {
                if (area2.contains(i, y)) {
                    xPoints.add(i);
                    yPoints.add(y);
                    break;
                }
            }

            //4
            for (int i = y; i <= y + height; i++) {
                if (area2.contains(x + width - 1, i)) {
                    xPoints.add(x + width - 1);
                    yPoints.add(i);
                    break;
                }
            }
            for (int i = y + height; i >= y; i--) {
                if (area2.contains(x + width - 1, i)) {
                    xPoints.add(x + width - 1);
                    yPoints.add(i);
                    break;
                }
            }

            //2
            for (int i = x + width; i >= x; i--) {
                if (area2.contains(i, y + height - 1)) {
                    xPoints.add(i);
                    yPoints.add(y + height - 1);
                    break;
                }
            }
            for (int i = x; i <= x + width; i++) {
                if (area2.contains(i, y + height - 1)) {
                    xPoints.add(i);
                    yPoints.add(y + height - 1);
                    break;
                }
            }

            //3
            for (int i = y + height; i >= y; i--) {
                if (area2.contains(x, i)) {
                    xPoints.add(x);
                    yPoints.add(i);
                    break;
                }
            }
            for (int i = y; i <= y + height; i++) {
                if (area2.contains(x, i)) {
                    xPoints.add(x);
                    yPoints.add(i);
                    break;
                }
            }

            int[] xs = new int[xPoints.size()];
            for (int i = 0; i < xs.length; i++) {
                xs[i] = xPoints.get(i);
            }
            int[] ys = new int[yPoints.size()];
            for (int i = 0; i < ys.length; i++) {
                ys[i] = yPoints.get(i);
            }
            g.drawPolygon(xs, ys, xs.length);
        }

        g.drawPolygon(polygon1);


        switch (fillState) {
            case Common.FILL_COLOR:
                g.setColor(fillColor);
                g.fillPolygon(ratioXPoints, ratioYPoints, ratioXPoints.length);
                break;

            case Common.FILL_IMG:

                break;

            case Common.FILL_NO:

                break;
        }


        //外裁剪
        for (Rectangle rec : innerCutList) {
            g.clearRect((int) (rec.x * ratioWidth), (int) (rec.y * ratioHeight),
                    (int) (rec.width * ratioWidth), (int) (rec.height * ratioHeight));

        }
    }
}
