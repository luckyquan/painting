package com.li.paint.panel;

import com.li.paint.panel.basic.GraphicPanel;

import java.awt.*;

public class StraightLinePanel extends GraphicPanel {

    public StraightLinePanel(int startX, int startY, int endX, int endY) {
        super(startX, startY, endX, endY);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //在起始点右侧
        if (endX > startX) {
            //下侧
            if (endY > startY) {
                g.drawLine(graphicX, graphicY, graphicX + graphicWidth, graphicY + graphicHeight);
//                LineUtil.dda(g, graphicX, graphicY, graphicX + graphicWidth, graphicY + graphicHeight);
            }
            //上侧
            else {
                g.drawLine(graphicX, graphicY + graphicHeight, graphicX + graphicWidth, graphicY);
//                LineUtil.dda(g, graphicX, graphicY + graphicHeight, graphicX + graphicWidth, graphicY);
            }
        }
        //在起始点左侧
        else {
            //下侧
            if (endY > startY) {
                g.drawLine(graphicX, graphicY + graphicHeight, graphicX + graphicWidth, graphicY);
//                LineUtil.dda(g, graphicX, graphicY + graphicHeight, graphicX + graphicWidth, graphicY);
            }
            //上侧
            else {
                g.drawLine(graphicX, graphicY, graphicX + graphicWidth, graphicY + graphicHeight);
//                LineUtil.dda(g, graphicX, graphicY, graphicX + graphicWidth, graphicY + graphicHeight);
            }
        }
    }
}