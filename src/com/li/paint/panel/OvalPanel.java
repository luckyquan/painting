package com.li.paint.panel;

import com.li.paint.panel.basic.GraphicPanel;
import com.li.paint.utils.Common;

import java.awt.*;

public class OvalPanel extends GraphicPanel {

    public OvalPanel(int startX, int startY, int endX, int endY) {
        super(startX, startY, endX, endY);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(graphicX, graphicY, graphicWidth, graphicHeight);

        switch (fillState) {
            case Common.FILL_COLOR:
                g.setColor(fillColor);
                g.fillOval(graphicX + locTrans, graphicY + locTrans,
                        graphicWidth - sizeTrans, graphicHeight - sizeTrans);
                break;

            case Common.FILL_IMG:

                break;

            case Common.FILL_NO:

                break;
        }
    }
}
