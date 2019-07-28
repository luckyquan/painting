package com.li.paint.utils;

import com.li.paint.panel.RectanglePanel;
import com.li.paint.panel.basic.GraphicPanel;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static Point getApex(int startX, int startY, int endX, int endY) {
        Point point = new Point();

        int width = Math.abs(startX - endX);
        int height = Math.abs(startY - endY);

        if (width > 0 && height > 0) {
            //在起始点右侧
            if (endX > startX) {
                //下侧
                if (endY > startY) {
                    point.x = startX;
                    point.y = startY;
                }
                //上侧
                else {
                    point.x = startX;
                    point.y = startY - height;
                }
            }
            //在起始点左侧
            else {
                //下侧
                if (endY > startY) {
                    point.x = startX - width;
                    point.y = startY;
                }
                //上侧
                else {
                    point.x = startX - width;
                    point.y = startY - height;
                }
            }
        } else {
            System.out.println("panel高度或宽度为0");
        }

        return point;
    }

    public static int getMin(int... values) {
        int min = Integer.MAX_VALUE;
        for (int i : values) {
            if (i < min) min = i;
        }
        return min;
    }

    public static int getMax(int... values) {
        int max = Integer.MIN_VALUE;
        for (int i : values) {
            if (i > max) max = i;
        }
        return max;
    }

    public static BufferedImage symmetryImg(String path) {
        BufferedImage bufImage = null;
        try {
            FileInputStream in = new FileInputStream(new File(path));

            bufImage = ImageIO.read(in);
            int width = bufImage.getWidth();
            int height = bufImage.getHeight();

            int[] rgbs = bufImage.getRGB(0, 0, width, height, null, 0, width);

            // 对图片的像素矩阵进行水平镜像
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width / 2; col++) {
                    int temp = rgbs[row * width + col];
                    rgbs[row * width + col] = rgbs[row * width + (width - 1 - col)];
                    rgbs[row * width + (width - 1 - col)] = temp;
                }
            }

            bufImage.setRGB(0, 0, width, height, rgbs, 0, width);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bufImage;
    }

    public static double[][] matrix(double a[][], double b[][]) {
        //当a的列数与矩阵b的行数不相等时，不能进行点乘，返回null
        if (a[0].length != b.length)
            return null;
        //c矩阵的行数y，与列数x
        int y = a.length;
        int x = b[0].length;
        double c[][] = new double[y][x];
        for (int i = 0; i < y; i++)
            for (int j = 0; j < x; j++)
                //c矩阵的第i行第j列所对应的数值，等于a矩阵的第i行分别乘以b矩阵的第j列之和
                for (int k = 0; k < b.length; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }

    public static void saveGraphics(List<GraphicPanel> list) {
        //1
        Document doc = DocumentHelper.createDocument();
        //2
        Element root = doc.addElement("list");
        //3
        for (GraphicPanel graphicPanel : list) {
            Element recEle = root.addElement(graphicPanel.type);

            recEle.addElement("startX").addText(graphicPanel.startX + "");
            recEle.addElement("startY").addText(graphicPanel.startY + "");
            recEle.addElement("endX").addText(graphicPanel.endX + "");
            recEle.addElement("endY").addText(graphicPanel.endY + "");
            recEle.addElement("type").addText(graphicPanel.type);
            recEle.addElement("lineWidth").addText(graphicPanel.lineWidth + "");
            recEle.addElement("lineStyle").addText(graphicPanel.lineStyle + "");
            recEle.addElement("lineColor").addText(graphicPanel.lineColor.getRGB() + "");
            recEle.addElement("fillState").addText(graphicPanel.fillState + "");
            recEle.addElement("fillColor").addText(graphicPanel.fillColor.getRGB() + "");
            recEle.addElement("filePath").addText(graphicPanel.filePath);
            recEle.addElement("panelX").addText(graphicPanel.panelX + "");
            recEle.addElement("panelY").addText(graphicPanel.panelY + "");
            recEle.addElement("panelWidth").addText(graphicPanel.panelWidth + "");
            recEle.addElement("panelHeight").addText(graphicPanel.panelHeight + "");
            recEle.addElement("graphicX").addText(graphicPanel.graphicX + "");
            recEle.addElement("graphicY").addText(graphicPanel.graphicY + "");
            recEle.addElement("graphicWidth").addText(graphicPanel.graphicWidth + "");
            recEle.addElement("graphicHeight").addText(graphicPanel.graphicHeight + "");

            //empEle.addAttribute("id", GraphicPanel.getId() + "");
        }
        try {
            //4
            XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
            FileOutputStream fos = new FileOutputStream("paint.xml");
            writer.setOutputStream(fos);
            //5
            writer.write(doc);
            System.out.println("保存完毕");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<GraphicPanel> loadGraphics() {
        List<GraphicPanel> graphicPanelList = new ArrayList<>();
        try {
            //1
            SAXReader reader = new SAXReader();
            //2
            Document doc = reader.read(new File("paint.xml"));
            Element root = doc.getRootElement();

            for (Element e : root.elements()) {
                String startX = e.element("startX").getText();
                String startY = e.element("startY").getText();
                String endX = e.element("endX").getText();
                String endY = e.element("endY").getText();
                String type = e.element("type").getText();
                String lineWidth = e.element("lineWidth").getText();
                String lineStyle = e.element("lineStyle").getText();
                String lineColor = e.element("lineColor").getText();
                String fillState = e.element("fillState").getText();
                String fillColor = e.element("fillColor").getText();
                String filePath = e.element("filePath").getText();
                String panelX = e.element("panelX").getText();
                String panelY = e.element("panelY").getText();
                String panelWidth = e.element("panelWidth").getText();
                String panelHeight = e.element("panelHeight").getText();
                String graphicX = e.element("graphicX").getText();
                String graphicY = e.element("graphicY").getText();
                String graphicWidth = e.element("graphicWidth").getText();
                String graphicHeight = e.element("graphicHeight").getText();

                graphicPanelList.add(new RectanglePanel(
                        Integer.parseInt(startX), Integer.parseInt(startY), Integer.parseInt(endX), Integer.parseInt(endY),
                        type, Float.parseFloat(lineWidth), Integer.parseInt(lineStyle), new Color(Integer.parseInt(lineColor)),
                        Integer.parseInt(fillState), new Color(Integer.parseInt(fillColor)), filePath,
                        Integer.parseInt(panelX), Integer.parseInt(panelY), Integer.parseInt(panelWidth), Integer.parseInt(panelHeight),
                        Integer.parseInt(graphicX), Integer.parseInt(graphicY), Integer.parseInt(graphicWidth), Integer.parseInt(graphicHeight)));
            }

            System.out.println("解析完毕");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return graphicPanelList;
    }

    public static BufferedImage createImg(int[][] data) {
        int width = data[0].length;
        int height = data.length;
        int[] imgData = new int[width * height];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                switch (data[i][j]) {
                    case 0:
                        imgData[i * width + j] = Color.WHITE.getRGB();
                        break;

                    case 1:
                        imgData[i * width + j] = Color.BLACK.getRGB();
                        break;
                }
            }
        }

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferedImage.setRGB(0, 0, width, height, imgData, 0, width);
        try {
            System.out.println("创建图片");
            ImageIO.write(bufferedImage, "png", new File("img.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferedImage;
    }
}
