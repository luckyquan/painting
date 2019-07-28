package com.li.paint.panel;

import com.li.paint.panel.basic.GraphicPanel;
import com.li.paint.utils.Common;

import java.awt.*;

public class RoundedRectanglePanel extends GraphicPanel {

    public RoundedRectanglePanel(int startX, int startY, int endX, int endY) {
        super(startX, startY, endX, endY);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRoundRect(graphicX, graphicY, graphicWidth, graphicHeight,
                graphicWidth / 4, graphicHeight / 4);

        //内裁剪
        for (Rectangle rec : outerCutList) {
            g.clipRect((int) (rec.x * ratioWidth), (int) (rec.y * ratioHeight),
                    (int) (rec.width * ratioWidth), (int) (rec.height * ratioHeight));
            System.out.println("roundrectanglepanel");
        }

        switch (fillState) {
            case Common.FILL_COLOR:
                g.setColor(fillColor);
                g.fillRoundRect(graphicX, graphicY, graphicWidth, graphicHeight,
                        graphicWidth / 4, graphicHeight / 4);

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
