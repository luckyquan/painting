package com.li.paint.panel;

import com.li.paint.panel.basic.GraphicPanel;
import com.li.paint.utils.Common;

import java.awt.*;
//填充内部裁剪类zzq.add

public class RectanglePanel extends GraphicPanel {

    public RectanglePanel(int startX, int startY, int endX, int endY) {
        super(startX, startY, endX, endY);
    }

    public RectanglePanel(int startX, int startY, int endX, int endY,
                          String type, float lineWidth, int lineStyle, Color lineColor,
                          int fillState, Color fillColor, String filePath,
                          int panelX, int panelY, int panelWidth, int panelHeight,
                          int graphicX, int graphicY, int graphicWidth, int graphicHeight) {
        super(startX, startY, endX, endY,
                type, lineWidth, lineStyle, lineColor,
                fillState, fillColor, filePath,
                panelX, panelY, panelWidth, panelHeight,
                graphicX, graphicY, graphicWidth, graphicHeight);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(graphicX, graphicY, graphicWidth, graphicHeight);

        //内裁剪
        for (Rectangle rec : outerCutList) {
            g.clipRect((int) (rec.x * ratioWidth), (int) (rec.y * ratioHeight),
                    (int) (rec.width * ratioWidth), (int) (rec.height * ratioHeight));
            System.out.println("rectanglepanel");
        }

        switch (fillState) {
            case Common.FILL_COLOR:
                g.setColor(fillColor);
                g.fillRect(graphicX + locTrans, graphicY + locTrans,
                        graphicWidth - sizeTrans, graphicHeight - sizeTrans);
                break;

            case Common.FILL_IMG:
                if (!imageLabel.isVisible()) {
                    imageLabel.setVisible(true);
                }
                break;

            case Common.FILL_NO:
                if (imageLabel.isVisible()) {
                    imageLabel.setVisible(false);
                }
                break;
        }

        //外裁剪
        for (Rectangle rec : innerCutList) {
            g.clearRect((int) (rec.x * ratioWidth), (int) (rec.y * ratioHeight),
                    (int) (rec.width * ratioWidth), (int) (rec.height * ratioHeight));

        }
    }
}
