package com.li.paint.panel.basic;

import com.li.paint.utils.Common;
import com.li.paint.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class GraphicPanel extends JPanel {

    public String type;

    public int startX;
    public int startY;
    public int endX;
    public int endY;

    public int panelX;
    public int panelY;
    public int panelWidth;
    public int panelHeight;

    public int graphicX = Common.BORDER;
    public int graphicY = Common.BORDER;
    public int graphicWidth;
    public int graphicHeight;

    public MouseListener mouseListener;
    public MouseMotionListener mouseMotionListener;

    //线型
    public float lineWidth;
    public int lineStyle;

    private BasicStroke line;
    private BasicStroke dottedLine;//虚线
    private BasicStroke dottedLine1;//虚线
    private BasicStroke dottedLine2;//虚线
    private BasicStroke dottedLine3;//虚线

    private BasicStroke borderLine;

    public Color lineColor;

    //填充
    public int fillState;
    public Color fillColor;

    public String filePath;
    protected JLabel imageLabel;

    protected int locTrans;
    protected int sizeTrans;

    //选中
    public boolean isSelected = false;

    //缩放按钮
    private JLabel lb_scale;
    private int scaleButtonLength = 10;

    protected double ratioWidth;
    protected double ratioHeight;

    //旋转
    private double theta = 0d;
//    private boolean isRotated = false;


    //对称
    public boolean isSymmetry = false;

    //裁剪
    protected List<Rectangle> innerCutList;
    protected List<Rectangle> outerCutList;

    public GraphicPanel(int startX, int startY, int endX, int endY,
                        String type, float lineWidth, int lineStyle, Color lineColor,
                        int fillState, Color fillColor, String filePath,
                        int panelX, int panelY, int panelWidth, int panelHeight,
                        int graphicX, int graphicY, int graphicWidth, int graphicHeight) {
        super();
        //设置属性
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.type = type;
        this.lineWidth = lineWidth;
        this.lineStyle = lineStyle;
        this.lineColor = lineColor;
        this.fillState = fillState;
        this.fillColor = fillColor;
        this.filePath = filePath;
        this.panelX = panelX;
        this.panelY = panelY;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.graphicX = graphicX;
        this.graphicY = graphicY;
        this.graphicWidth = graphicWidth;
        this.graphicHeight = graphicHeight;

        //背景透明
        this.setBackground(null);
        this.setOpaque(false);

        //绝对位置
        this.setLayout(null);
        this.setBounds(panelX, panelY, panelWidth, panelHeight);

        System.out.println("panel location:" + this.getX() + "," + this.getY() + "---size:" + this.getWidth() + "," + this.getHeight());

        init();
        setupEvent();
        finish();
    }

    public GraphicPanel(int startX, int startY, int endX, int endY) {
        super();
        //设置属性
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.type = Common.type;
        this.lineWidth = Common.lineWidth;
        this.lineStyle = Common.lineStyle;
        this.lineColor = Common.lineColor;
        this.fillState = Common.fillState;
        this.fillColor = Common.fillColor;
        this.filePath = Common.filePath;

        this.graphicWidth = Math.abs(startX - endX);
        this.graphicHeight = Math.abs(startY - endY);
        this.panelWidth = graphicWidth + Common.BORDER * 2;
        this.panelHeight = graphicHeight + Common.BORDER * 2;

        //绝对位置
        this.setLayout(null);

        //背景透明
        this.setBackground(null);
        this.setOpaque(false);

        Point point = Utils.getApex(startX, startY, endX, endY);
        panelX = point.x - Common.BORDER;
        panelY = point.y - Common.BORDER;
        this.setBounds(panelX, panelY, panelWidth, panelHeight);

        System.out.println("panel location:" + this.getX() + "," + this.getY() + "---size:" + this.getWidth() + "," + this.getHeight());

        init();
        setupEvent();
        finish();
    }

    protected void init() {
        line = new BasicStroke(
                lineWidth,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND);

        dottedLine = new BasicStroke(
                lineWidth,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1.0f,
                new float[]{5, 5},
                0f);

        dottedLine1 = new BasicStroke(
                lineWidth,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1.0f,
                new float[]{10, 10},
                0f);

        dottedLine2 = new BasicStroke(
                lineWidth,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1.0f,
                new float[]{15, 15},
                0f);

        dottedLine3 = new BasicStroke(
                lineWidth,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1.0f,
                new float[]{20, 20},
                0f);

        borderLine = new BasicStroke(
                1.0f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1.0f,
                new float[]{5, 5},
                0f);


        //监听器
        mouseListener = new MyMouseListener();
        mouseMotionListener = new MyMouseMotionListener();


        //填充位移
        int trans = (int) Common.lineWidth / 2;
        locTrans = trans == 0 ? 1 : trans;
        //locTrans = (int) Common.lineWidth / 2 + 1;
        sizeTrans = (int) Common.lineWidth;


        //图片填充
        int imageX = graphicX + locTrans;
        int imageY = graphicY + locTrans;
        int imageWidth = graphicWidth - sizeTrans;
        int imageHeight = graphicHeight - sizeTrans;
        Image image = Toolkit.getDefaultToolkit().createImage(filePath);
        ImageIcon icon = new ImageIcon(image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT));
        imageLabel = new JLabel(icon) {
            @Override
            public void paint(Graphics g) {
                //外裁剪
                int preWidth = Math.abs(startX - endX);
                int preHeight = Math.abs(startY - endY);

                double ratioWidth = (double) graphicWidth / (double) preWidth;
                double ratioHeight = (double) graphicHeight / (double) preHeight;

                for (Rectangle rec : outerCutList) {
                    g.clipRect((int) ((rec.x - locTrans - Common.BORDER) * ratioWidth), (int) ((rec.y - locTrans - Common.BORDER) * ratioHeight),
                            (int) ((rec.width + sizeTrans) * ratioWidth), (int) ((rec.height + sizeTrans) * ratioHeight));
                }

                super.paint(g);
            }
        };
        imageLabel.setBackground(null);
        imageLabel.setOpaque(false);
        imageLabel.setVisible(false);
        imageLabel.setBounds(imageX, imageY, imageWidth, imageHeight);


        //缩放按钮
        int scaleX = panelWidth - 1 - scaleButtonLength;
        int scaleY = panelHeight - 1 - scaleButtonLength;
        lb_scale = new JLabel();
        lb_scale.setBackground(null);
        lb_scale.setOpaque(false);
        lb_scale.setIcon(new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setColor(Color.BLACK);
                g.fillRect(x, y, scaleButtonLength, scaleButtonLength);
            }

            @Override
            public int getIconWidth() {
                return scaleButtonLength;
            }

            @Override
            public int getIconHeight() {
                return scaleButtonLength;
            }
        });
        lb_scale.setBounds(scaleX, scaleY, scaleButtonLength, scaleButtonLength);
        lb_scale.setVisible(false);

        //裁剪
        innerCutList = new ArrayList<>();
        outerCutList = new ArrayList<>();
    }

    protected void setupEvent() {
        //拖动缩放
        lb_scale.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (Common.TYPE_CIRCULAR.equals(type)) {
                    x = y = Math.max(x, y);
                }

                //最小大小
                panelWidth = Math.max(panelWidth + x, scaleButtonLength);
                panelHeight = Math.max(panelHeight + y, scaleButtonLength);
                GraphicPanel.this.setBounds(panelX, panelY, panelWidth, panelHeight);

                graphicWidth = Math.max(graphicWidth + x, 0);
                graphicHeight = Math.max(graphicHeight + y, 0);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    protected void finish() {
        this.add(lb_scale);
        this.add(imageLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //旋转
        double theta = Math.toRadians(this.theta);
        ((Graphics2D) g).rotate(theta, (double) panelWidth / 2.0, (double) panelHeight / 2.0);

        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //虚线边框
        if (isSelected) {
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(borderLine);
            g.drawRect(0, 0, panelWidth - 1, panelHeight - 1);
        }

        //旋转
        double theta = Math.toRadians(this.theta);
        ((Graphics2D) g).rotate(theta, (double) panelWidth / 2.0, (double) panelHeight / 2.0);


//        AffineTransform affineTransform = new AffineTransform();
//        affineTransform.rotate(Math.toRadians(this.theta), (double) panelWidth / 2.0, (double) panelHeight / 2.0);
//        affineTransform.getMatrix(mat);
//        calculateRec();

        //对称
        if (isSymmetry) {
            g.translate(panelWidth - 1, 0);
            ((Graphics2D) g).scale(-1, 1);
        }

        //缩放
        if (isSelected) {
            lb_scale.setVisible(true);
            int scaleX = panelWidth - 1 - scaleButtonLength;
            int scaleY = panelHeight - 1 - scaleButtonLength;
            lb_scale.setBounds(scaleX, scaleY, scaleButtonLength, scaleButtonLength);
        } else {
            lb_scale.setVisible(false);
        }

        //填充
        if (isSelected) {
            this.fillState = Common.fillState;
            switch (fillState) {
                case Common.FILL_COLOR:
                    this.fillColor = Common.fillColor;
                    break;

                case Common.FILL_IMG:
                    this.filePath = Common.filePath;
                    int imageX = graphicX + locTrans;
                    int imageY = graphicY + locTrans;
                    int imageWidth = graphicWidth - sizeTrans;
                    int imageHeight = graphicHeight - sizeTrans;
                    Image image;
                    if (isSymmetry) {
                        image = Utils.symmetryImg(filePath);
                    } else {
                        image = Toolkit.getDefaultToolkit().createImage(filePath);
                    }
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT));
                    imageLabel.setIcon(icon);
                    imageLabel.setBounds(imageX, imageY, imageWidth, imageHeight);
                    break;

                case Common.FILL_NO:

                    break;
            }
        }

        //线型
        g.setColor(lineColor);
        switch (lineStyle) {
            case Common.LINE_NORMAL:
                ((Graphics2D) g).setStroke(line);
                break;

            case Common.LINE_DOTTED:
                ((Graphics2D) g).setStroke(dottedLine);
                break;

            case Common.LINE_DOTTED1:
                ((Graphics2D) g).setStroke(dottedLine1);
                break;

            case Common.LINE_DOTTED2:
                ((Graphics2D) g).setStroke(dottedLine2);
                break;

            case Common.LINE_DOTTED3:
                ((Graphics2D) g).setStroke(dottedLine3);
                break;

            default:
                ((Graphics2D) g).setStroke(line);
                break;
        }

        //缩放比例
        int preWidth = Math.abs(startX - endX);
        int preHeight = Math.abs(startY - endY);
        ratioWidth = (double) graphicWidth / (double) preWidth;
        ratioHeight = (double) graphicHeight / (double) preHeight;
    }

    private int preX = -1;
    private int preY = -1;

    protected class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            JPanel drawPanel = (JPanel) GraphicPanel.this.getParent();
            for (int i = 0; i < drawPanel.getComponentCount(); i++) {
                GraphicPanel graphicPanel = (GraphicPanel) drawPanel.getComponent(i);
                graphicPanel.isSelected = false;
            }
            isSelected = true;
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            preX = -1;
            preY = -1;
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class MyMouseMotionListener implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (isSelected) {
                if (preX > 0 && preY > 0) {
                    panelX = panelX + (e.getX() - preX);
                    panelY = panelY + (e.getY() - preY);
                    GraphicPanel.this.setLocation(panelX, panelY);
                } else {
                    preX = e.getX();
                    preY = e.getY();
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    private double[] mat = new double[6];

    private void calculateRec() {
//        Point point1 = new Point(panelX, panelY);
//        Point point2 = new Point(panelX + panelWidth, panelY);
//        Point point3 = new Point(panelX, panelY + panelHeight);
//        Point point4 = new Point(panelX + panelWidth, panelY + panelHeight);

        Point point1 = new Point(0, 0);
        Point point2 = new Point(panelWidth, 0);
        Point point3 = new Point(0, panelHeight);
        Point point4 = new Point(panelWidth, panelHeight);

        Point pointRotate1 = calculateRotate(point1);
        Point pointRotate2 = calculateRotate(point2);
        Point pointRotate3 = calculateRotate(point3);
        Point pointRotate4 = calculateRotate(point4);

        if (null == pointRotate1 || null == pointRotate2 || null == pointRotate3 || null == pointRotate4) {
            return;
        }

        System.out.println(pointRotate1.x + "," + pointRotate1.y);
        System.out.println(pointRotate2.x + "," + pointRotate2.y);
        System.out.println(pointRotate3.x + "," + pointRotate3.y);
        System.out.println(pointRotate4.x + "," + pointRotate4.y);

        pointRotate1 = new Point(panelX + pointRotate1.x, panelY + pointRotate1.y);
        pointRotate2 = new Point(panelX + panelWidth + pointRotate2.x, panelY + pointRotate2.y);
        pointRotate3 = new Point(panelX + pointRotate3.x, panelY + panelHeight + pointRotate3.y);
        pointRotate4 = new Point(panelX + panelWidth + pointRotate4.x, panelY + panelHeight + pointRotate4.y);

        int minX = Utils.getMin(pointRotate1.x, pointRotate2.x, pointRotate3.x, pointRotate4.x);
        int maxX = Utils.getMax(pointRotate1.x, pointRotate2.x, pointRotate3.x, pointRotate4.x);
        int minY = Utils.getMin(pointRotate1.y, pointRotate2.y, pointRotate3.y, pointRotate4.y);
        int maxY = Utils.getMax(pointRotate1.y, pointRotate2.y, pointRotate3.y, pointRotate4.y);

        int panelWidth = maxX - minX;
        int panelHeight = maxY - minY;

        this.setBounds(minX, minY, panelWidth, panelHeight);
    }

    private Point calculateRotate(Point srcPoint) {
        double[][] a = new double[][]{{mat[0], mat[2], mat[4]}, {mat[1], mat[3], mat[5]}, {0.0, 0.0, 1.0}};
        double[][] b = new double[][]{{(double) srcPoint.x}, {(double) srcPoint.y}, {1.0}};
        double[][] c = Utils.matrix(a, b);

        if (c == null) {
            return null;
        }
        return new Point((int) c[0][0], (int) c[1][0]);
    }

    public void addInnerCutRec(Rectangle rec) {
        this.innerCutList.add(rec);
    }

    public void addOuterCutRec(Rectangle rec) {
        this.outerCutList.add(rec);
    }

    /*if("多边形".equals(zhiling)) {

        if(x4==0&&y4==0){

            x4 = e.getX(); y4 = e.getY();
            start_x = x4; start_y = y4;
            //gr.drawLine(x3, y3, x4, y4);
        }
        else {
            //System.out.println("Here is duobianxing ");
            x3 = x4; y3 = y4; x4 = e.getX(); y4 = e.getY();
            bresenhan(x3, y3, x4, y4);
            ShArr[index++] = new shape(x3, y3, x4, y4, zhiling);
        }
        if(e.getClickCount()==2){
            //System.out.println("双击");
            //System.out.println("双击");
            x4 =0; y4=0;
            bresenhan(start_x, start_y, e.getX(), e.getY());

        }
    }*/

}
