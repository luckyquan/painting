package com.li.paint.panel;

import com.li.paint.panel.basic.GraphicPanel;

import java.awt.*;

public class BrokenLinePanel extends GraphicPanel {

    private int x1, y1, x2, y2, x3, y3;

    public BrokenLinePanel(int startX, int startY, int endX, int endY, Point start, Point mid, Point end) {
        super(startX, startY, endX, endY);

        x1 = (int) start.getX() - panelX;
        y1 = (int) start.getY() - panelY;
        x2 = (int) mid.getX() - panelX;
        y2 = (int) mid.getY() - panelY;
        x3 = (int) end.getX() - panelX;
        y3 = (int) end.getY() - panelY;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawLine((int) ((double) x1 * ratioWidth), (int) ((double) y1 * ratioHeight),
                (int) ((double) x2 * ratioWidth), (int) ((double) y2 * ratioHeight));
        g.drawLine((int) ((double) x2 * ratioWidth), (int) ((double) y2 * ratioHeight),
                (int) ((double) x3 * ratioWidth), (int) ((double) y3 * ratioHeight));
    }
}
