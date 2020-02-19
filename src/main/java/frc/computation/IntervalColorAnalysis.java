package frc.computation;

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;

public class IntervalColorAnalysis {
    private BufferedImage bi;
    private int width, height;  
    private Vector<Object[]> colors;
    private HashMap<String, String> association;

    public IntervalColorAnalysis(File file) {
        try {
            bi = ImageIO.read(file);
        } catch(IOException e) {
            e.printStackTrace();
        }

        this.width = bi.getWidth()/2;
        this.height = bi.getHeight()/2;
    }

    public IntervalColorAnalysis(BufferedImage bi) {
        this.bi = bi;

        this.width = bi.getWidth()/2;
        this.height = bi.getHeight()/2;
    }

    public IntervalColorAnalysis(String file) {
        try {
            this.bi = ImageIO.read(new File(file));
        } catch(IOException e) {
            e.printStackTrace();
        }

        this.width = bi.getWidth()/2;
        this.height = bi.getHeight()/2;
    }

    public IntervalColorAnalysis(Image image) {
        this.bi = (BufferedImage) image;

        this.width = bi.getWidth()/2;
        this.height = bi.getHeight()/2;
    }

    public void overrideCenter(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setColorVector(Vector<Object[]> colorVector) {
        this.colors = colorVector;
    }
    
    public void setAssociationMap(HashMap<String, String> newMap) {
        association = newMap;
    }

    public String getColor(int tr, int tg, int tb) {
        int pix = bi.getRGB(width, height);

        int r = (pix >> 16) & 255;
        int g = (pix >> 8) & 255;
        int b =  pix & 255;

        for(Object[] i : colors) {
            int[] rbound = new int[]{(int)i[1]-tr, (int)i[1]+tr};
            int[] gbound = new int[]{(int)i[2]-tg, (int)i[2]+tg};
            int[] bbound = new int[]{(int)i[3]-tb, (int)i[3]+tb};

            if((int) i[1] >= rbound[0] && (int) i[1] <= rbound[1]) {
                if((int) i[2] >= gbound[0] && (int) i[2] <= gbound[1]) {
                    if((int) i[3] >= rbound[0] && (int) i[3] <= rbound[1]) {
                        return (String) i[0];
                    }   
                }
            }
        }
        return "None";
    }

    public String getColor(int tolerance) {
        int tr, tg, tb;
        tr = tg = tb = tolerance;

        int pix = bi.getRGB(width, height);

        int r = (pix >> 16) & 255;
        int g = (pix >> 8) & 255;
        int b =  pix & 255;

        for(Object[] i : colors) {
            int[] rbound = new int[]{(int)i[1]-tr, (int)i[1]+tr};
            int[] gbound = new int[]{(int)i[2]-tg, (int)i[2]+tg};
            int[] bbound = new int[]{(int)i[3]-tb, (int)i[3]+tb};
            
            if(r >= rbound[0] && r <= rbound[1]) {
                if(g >= gbound[0] && g <= gbound[1]) {
                    if(b >= bbound[0] && b <= bbound[1]) {
                        return (String) i[0];
                    }
                }
            }
        }
        return "None";
    }
    
    public String getColorAssociative(int tolerance) {
        return association.get(getColor(tolerance));
    }
    
    public String getColorAssociative(int tr, int tg, int tb) {
        return association.get(getColor(tr, tg, tb));
    }

    public static BufferedImage generatePane(int color) {
        BufferedImage bi = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < 250; x++) {
            for(int y = 0; y < 250; y++) {
                bi.setRGB(x, y, color);
            }
        }

        return bi;
    }
}