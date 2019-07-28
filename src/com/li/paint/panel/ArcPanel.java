package com.li.paint.panel;

import com.li.paint.panel.basic.GraphicPanel;

import java.awt.*;

public class ArcPanel extends GraphicPanel {

    private int startAngle;
    private int arcAngle;

    public ArcPanel(int startX, int startY, int endX, int endY) {
        super(startX, startY, endX, endY);

        //下侧
        if (endY > startY) {
            startAngle = 180;
            arcAngle = -180;
        }
        //上侧
        else {
            startAngle = -180;
            arcAngle = 180;
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawArc(graphicX, graphicY, graphicWidth, graphicHeight, startAngle, arcAngle);
    }
}
