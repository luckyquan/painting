package com.li.paint.panel.main;

import com.li.paint.panel.ArcPanel;
import com.li.paint.panel.BrokenLinePanel;
import com.li.paint.panel.CharacterPanel;
import com.li.paint.panel.NamePanel;
import com.li.paint.panel.OvalPanel;
import com.li.paint.panel.PolygonPanel;
import com.li.paint.panel.RectanglePanel;
import com.li.paint.panel.RoundedRectanglePanel;
import com.li.paint.panel.StraightLinePanel;
import com.li.paint.panel.basic.BasicPanel;
import com.li.paint.panel.basic.GraphicPanel;
import com.li.paint.utils.Common;
import com.li.paint.utils.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends BasicPanel {

    private int startX = -1;
    private int startY = -1;
    private int endX = -1;
    private int endY = -1;
    private int midX = -1;
    private int midY = -1;
    private java.awt.Graphics gr;
    // ----------------------
    public BufferedImage pencilImage;
    public Graphics pencilG;

    private BasicStroke dottedLine;//虚线

    private int edgeNum = 5;
    private List<Point> pointList;

    public DrawPanel(int width, int height) {
        super(width, height);
    }


    //种子填充算法
    public  void  boundaryFill4  (int x, int y, Color fillColor) throws AWTException//递归算法
    {

        Color interiorColor=null;//当前坐标颜色
        interiorColor=getScreenPixel(x, y);
        System.out.print(interiorColor);
        gr.setColor(fillColor);



        if (interiorColor != fillColor && interiorColor != Color.BLACK){
            System.out.println("meihuadian ");

            gr.fillOval(x,y,3,3);// set color of pixel to fillcolor.

            System.out.println("hualdian ");
            boundaryFill4 (x+1, y, fillColor);
            boundaryFill4 (x-1, y, fillColor);
            boundaryFill4 (x, y+1, fillColor);
            boundaryFill4 (x, y-1, fillColor);
        }
    }



    public  Color getScreenPixel(int x, int y) throws AWTException { // 函数返回值为颜色的RGB值。
        Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
        rb = new Robot();
        Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
        Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
        System.out.println(di.width);
        System.out.println(di.height);
        Rectangle rec = new Rectangle(0, 0, di.width, di.height);
        BufferedImage bi = rb.createScreenCapture(rec);
        int pixelColor = bi.getRGB(x, y);
        Color color=new Color(16777216 + pixelColor);
        return color; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
    }

    public  int getrgb(int x, int y) throws AWTException { // 函数返回值为颜色的RGB值。
        Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
        rb = new Robot();
        Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
        Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
        System.out.println(di.width);
        System.out.println(di.height);
        Rectangle rec = new Rectangle(0, 0, di.width, di.height);
        BufferedImage bi = rb.createScreenCapture(rec);
        int pixelColor = bi.getRGB(x, y);

        return pixelColor; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
    }


    @Override
    protected void init() {
        super.init();
        setBackground(Color.WHITE);
        setLayout(null);

        dottedLine = new BasicStroke(
                1.0f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1.0f,
                new float[]{5, 5},
                0f);

        pencilImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        pencilG = pencilImage.getGraphics();

        pointList = new ArrayList<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setupGraphics(g);

        g.drawImage(pencilImage, 0, 0, null);

        if (startX > 0 && startY > 0 && endX > 0 && endY > 0) {
            Point point = Utils.getApex(startX, startY, endX, endY);
            int width = Math.abs(startX - endX);
            int height = Math.abs(startY - endY);

            switch (Common.type) {
                case Common.TYPE_STRAIGHT_LINE:
                    g.drawLine(startX, startY, endX, endY);
                    break;

                case Common.TYPE_RECTANGLE:
                    if (width > 0 && height > 0) {
                        g.drawRect(point.x, point.y, width, height);
                    }
                    break;

                case Common.TYPE_ROUNDED_RECTANGLE:
                    if (width > 0 && height > 0) {
                        g.drawRoundRect(point.x, point.y, width, height, width / 4, height / 4);
                    }
                    break;

                case Common.TYPE_BROKEN_LINE:
                case Common.TYPE_RIGHT_ANGLE_LINE:
                    if (midX > 0 && midY > 0) {
                        g.drawLine(startX, startY, midX, midY);
                        g.drawLine(midX, midY, endX, endY);
                    } else {
                        g.drawLine(startX, startY, endX, endY);
                    }
                    break;

                case Common.TYPE_CIRCULAR:
                    if (width > 0 && height > 0) {
                        g.drawOval(startX, startY, Math.max(width, height), Math.max(width, height));
                    }
                    break;

                case Common.TYPE_OVAL:
                    if (width > 0 && height > 0) {
                        g.drawOval(point.x, point.y, width, height);
                    }
                    break;

                case Common.TYPE_ARC:
                    if (width > 0 && height > 0) {
                        //下侧
                        if (endY > startY) {
                            g.drawArc(point.x, point.y, width, height, 180, -180);
                        }
                        //上侧
                        else {
                            g.drawArc(point.x, point.y, width, height, -180, 180);
                        }
                    }
                    break;

                case Common.TYPE_CHARACTER:
                case Common.TYPE_CUT_INNER:
                case Common.TYPE_CUT_OUTER:
                case Common.TYPE_NAME:
                    if (width > 0 && height > 0) {
                        g.setColor(Color.BLACK);
                        ((Graphics2D) g).setStroke(dottedLine);
                        g.drawRect(point.x, point.y, width, height);
                    }
                    break;

                case Common.TYPE_POLYGON:
                    if (null != pointList && pointList.size() > 0) {
                        Point prePoint = pointList.get(0);
                        for (Point nextPoint : pointList) {
                            g.drawLine(prePoint.x, prePoint.y, nextPoint.x, nextPoint.y);
                            prePoint = nextPoint;
                        }

                        if (pointList.size() < edgeNum) {
                            g.drawLine(prePoint.x, prePoint.y, endX, endY);
                        }
                    }
                    break;
            }
        }

        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (Common.type) {
            case Common.TYPE_PENCIL:
                if (startX > 0 && startY > 0) {
                    setupGraphics(pencilG);
                    pencilG.drawLine(startX, startY, e.getX(), e.getY());
                }
                startX = e.getX();
                startY = e.getY();
                break;

            case Common.TYPE_STRAIGHT_LINE:
            case Common.TYPE_RECTANGLE:
            case Common.TYPE_ROUNDED_RECTANGLE:
            case Common.TYPE_BROKEN_LINE:
            case Common.TYPE_RIGHT_ANGLE_LINE:
            case Common.TYPE_CIRCULAR:
            case Common.TYPE_OVAL:
            case Common.TYPE_ARC:
            case Common.TYPE_CHARACTER:
            case Common.TYPE_CUT_INNER:
            case Common.TYPE_CUT_OUTER:
            case Common.TYPE_NAME:
                if (startX >= 0 && startY >= 0) {
                    endX = e.getX() <= 0 ? 1 : e.getX();
                    endY = e.getY() <= 0 ? 1 : e.getY();
                } else {
                    startX = e.getX();
                    startY = e.getY();
                }
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int width = Math.abs(startX - endX);
        int height = Math.abs(startY - endY);

        if (startX > 0 && startY > 0 && endX > 0 && endY > 0) {
            switch (Common.type) {
                case Common.TYPE_STRAIGHT_LINE:
                    this.add(new StraightLinePanel(startX, startY, endX, endY));
                    break;

                case Common.TYPE_RECTANGLE:
                    this.add(new RectanglePanel(startX, startY, endX, endY));
                    break;

                case Common.TYPE_ROUNDED_RECTANGLE:
                    this.add(new RoundedRectanglePanel(startX, startY, endX, endY));
                    break;

                case Common.TYPE_BROKEN_LINE:
                case Common.TYPE_RIGHT_ANGLE_LINE:
                    if (midX < 0 && midY < 0) {
                        midX = endX;
                        midY = endY;
                    }
                    return;

                case Common.TYPE_CIRCULAR:
                    int max = Math.max(width, height);
                    endX = startX + max;
                    endY = startY + max;
                case Common.TYPE_OVAL:
                    this.add(new OvalPanel(startX, startY, endX, endY));
                    break;

                case Common.TYPE_ARC:
                    this.add(new ArcPanel(startX, startY, endX, endY));
                    break;

                case Common.TYPE_CHARACTER:
                    this.add(new CharacterPanel(startX, startY, endX, endY));
                    break;

                case Common.TYPE_CUT_INNER:
                case Common.TYPE_CUT_OUTER:
                    Point point = Utils.getApex(startX, startY, endX, endY);
                    addCutArea2Graphic(new Rectangle(point.x, point.y, width, height));
                    break;

                case Common.TYPE_POLYGON:
                    return;

                case Common.TYPE_NAME:
                    this.add(new NamePanel(startX, startY, endX, endY, Common.name));
                    break;
            }
        }

        startX = -1;
        startY = -1;
        endX = -1;
        endY = -1;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Common.type) {
            case Common.TYPE_BROKEN_LINE:
            case Common.TYPE_POLYGON:
                if (endX > 0 && endY > 0) {
                    endX = e.getX() <= 0 ? 1 : e.getX();
                    endY = e.getY() <= 0 ? 1 : e.getY();
                }
                break;

            case Common.TYPE_RIGHT_ANGLE_LINE:
                //斜率不存在
                if (startX == midX) {
                    endX = e.getX() <= 0 ? 1 : e.getX();
                    endY = midY;
                }
                //斜率为0
                else if (startY == midY) {
                    endX = midX;
                    endY = e.getY() <= 0 ? 1 : e.getY();
                }
                //常规
                else {
                    double k1 = (double) (midY - startY) / (double) (midX - startX);
                    double k2 = -1.0 / k1;//垂直

                    double x1 = e.getX() <= 0 ? 1 : e.getX();
                    double y1 = e.getY() <= 0 ? 1 : e.getY();
                    double b1 = y1 - k1 * x1;

                    double b2 = (double) midY - k2 * (double) midX;

                    double x, y;
                    x = (b2 - b1) / (k1 - k2);
                    y = k1 * x + b1;

                    endX = (int) x;
                    endY = (int) y;
                }
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        switch (Common.type) {
            case Common.TYPE_BROKEN_LINE:
            case Common.TYPE_RIGHT_ANGLE_LINE:
                int minX = Utils.getMin(startX, midX, endX);
                int maxX = Utils.getMax(startX, midX, endX);
                int minY = Utils.getMin(startY, midY, endY);
                int maxY = Utils.getMax(startY, midY, endY);
                this.add(new BrokenLinePanel(minX, minY, maxX, maxY,
                        new Point(startX, startY), new Point(midX, midY), new Point(endX, endY)));

                startX = -1;
                startY = -1;
                endX = -1;
                endY = -1;
                midX = -1;
                midY = -1;
                break;

            case Common.TYPE_POLYGON:
                if (pointList.size() < edgeNum) {
                    pointList.add(new Point(e.getX(), e.getY()));

                    if (pointList.size() == 1) {
                        startX = e.getX();
                        startY = e.getY();
                        endX = e.getX();
                        endY = e.getY();
                    }

                    if (pointList.size() == edgeNum) {
                        //添加panel
                        int pMinX = Integer.MAX_VALUE, pMaxX = Integer.MIN_VALUE,
                                pMinY = Integer.MAX_VALUE, pMaxY = Integer.MIN_VALUE;
                        for (Point point : pointList) {
                            pMinX = Math.min(pMinX, point.x);
                            pMaxX = Math.max(pMaxX, point.x);
                            pMinY = Math.min(pMinY, point.y);
                            pMaxY = Math.max(pMaxY, point.y);
                        }
                        this.add(new PolygonPanel(pMinX, pMinY, pMaxX, pMaxY, pointList));

                        pointList.clear();
                        startX = -1;
                        startY = -1;
                        endX = -1;
                        endY = -1;
                    }
                }
                break;
        }
    }

    private void setupGraphics(Graphics g) {
        //设置颜色
        g.setColor(Common.lineColor);

        //设置线型
        BasicStroke stroke;
        switch (Common.lineStyle) {
            case Common.LINE_NORMAL:
                stroke = new BasicStroke(
                        Common.lineWidth,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND);
                break;

            case Common.LINE_DOTTED:
                stroke = new BasicStroke(
                        Common.lineWidth,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND,
                        1.0f,
                        new float[]{5, 5},
                        0f);
                break;

            case Common.LINE_DOTTED1:
                stroke = new BasicStroke(
                        Common.lineWidth,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND,
                        1.0f,
                        new float[]{10, 10},
                        0f);
                break;

            case Common.LINE_DOTTED2:
                stroke = new BasicStroke(
                        Common.lineWidth,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND,
                        1.0f,
                        new float[]{15, 15},
                        0f);
                break;

            case Common.LINE_DOTTED3:
                stroke = new BasicStroke(
                        Common.lineWidth,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND,
                        1.0f,
                        new float[]{20, 20},
                        0f);
                break;

            default:
                stroke = new BasicStroke(
                        Common.lineWidth,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND);
                break;
        }
        ((Graphics2D) g).setStroke(stroke);
    }

    private void addCutArea2Graphic(Rectangle cutRec) {
        double x = cutRec.getX();
        double y = cutRec.getY();

        for (int i = 0; i < this.getComponentCount(); i++) {
            GraphicPanel graphicPanel = (GraphicPanel) this.getComponent(i);
            Rectangle graphicRec = new Rectangle(graphicPanel.panelX + Common.BORDER, graphicPanel.panelY + Common.BORDER,
                    graphicPanel.graphicWidth, graphicPanel.graphicHeight);

            if (graphicRec.contains(x, y) && graphicRec.contains(x + cutRec.width, y)
                    && graphicRec.contains(x, y + cutRec.height) && graphicRec.contains(x + cutRec.width, y + cutRec.height)) {

                Rectangle tmp = new Rectangle((int) x - graphicPanel.panelX, (int) y - graphicPanel.panelY,
                        cutRec.width, cutRec.height);

                switch (Common.type) {
                    case Common.TYPE_CUT_INNER:
                        graphicPanel.addInnerCutRec(tmp);
                        break;

                    case Common.TYPE_CUT_OUTER:
                        graphicPanel.addOuterCutRec(tmp);
                        break;
                }
                break;
            }
        }
    }

}
