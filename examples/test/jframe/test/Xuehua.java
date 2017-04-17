package test.jframe.test;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
public class Xuehua extends Applet implements Runnable{
    Thread mainThread;
    Image offScreen,gAlc[];
    Random rand;
    int stopFlag,snows,wind,threadSleep,snowSize;
    int[] snowX,snowY;
    long stopTime =0;
    Dimension dim;
    MediaTracker mt;
    public Xuehua(){
    }
    int getParameter(String s1,int s2){
        String s=null;
        try{
            s=getParameter(s1);
        }catch(NullPointerException ex){
        }
        return(s !=null)?Integer.parseInt(s):s2;
    }
    int getParameter(String s1, int s2, int max, int min){
        String s=null;
        try{
            s=getParameter(s1);
        }catch(NullPointerException ex){
        }
        if(s!=null){
            if((s2=Integer.parseInt(s))>max){
                return max;
            }else if (s2<min){
                return min;
            }else{
                return s2;
            }
             
        }else{
            return s2;
        }
    }
    String getParameter(String s1 , String s2){
        String s=null;
        try{
            s=getParameter(s1);
        }catch(NullPointerException ex){
        }
        return (s!=null)?s:s2;
    }
     
    public void init(){
        rand =new Random();
        dim =getSize();
        snows       =getParameter("snows", 100, 500,0);
        snowSize    =getParameter("snowSize",3,10,3);
        threadSleep =getParameter("threadSleep",80,1000,10);
        snowX = new int [snows];
        snowY = new int [snows];
        for( int i=0;i<snows;i++){
            snowX[i]=rand.nextInt()%(dim.width/2)+dim.width/2;
            snowY[i]=rand.nextInt()%(dim.height/2)+dim.height/2;
        }
        mt =new MediaTracker(this);
        gAlc= new Image[1];
 
        try{
            gAlc[0]=getImage(getDocumentBase(), getParameter("graphic","test.gif"));
            offScreen=createImage(dim.width, dim.height);
        }catch(Exception ex){
            gAlc[0]=new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB );
            offScreen=new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB );
        }
        mt.addImage(gAlc[0],0);
        try{
            mt.waitForID(0);
        }catch(InterruptedException ex) {
            return;
        }
        stopFlag =0;
    }
 
    public void start(){
        if (mainThread==null){
            mainThread=new Thread(this);
            mainThread.start();
        }
    }
    public void stop(){
        mainThread =null;
    }
    public void run(){
        while(mainThread !=null){
            try{
                Thread.sleep(threadSleep);
            }catch(InterruptedException ex) {return;}
            repaint();
        }
    }
    public void drawBackSnow(Graphics g){
        g.setColor(Color.white);
        for(int i=0;i<snows;i++){
            g.fillOval(snowX[i],snowY[i],snowSize,snowSize);
            snowX[i]+=rand.nextInt()%2+wind;
            snowY[i]+=(rand.nextInt()%6+5)/5+1;
            if(snowX[i]>=dim.width) snowX[i]=0;
            if(snowX[i]<0) snowX[i]=dim.width -1;
            if(snowY[i]>=dim.height ||snowY[i]<0){
                snowX[i] =Math.abs(rand.nextInt()%dim.width);
                snowY[i] =0;
            }
        }
        wind =rand.nextInt()%5 -2;
    }
    public void paint(Graphics g){
        offScreen.getGraphics().setColor(Color.black);
        offScreen.getGraphics().fillRect(0,0,dim.width,dim.height);
        offScreen.getGraphics().drawImage(gAlc[0],0,0,this);
        drawBackSnow(offScreen.getGraphics());
        g.drawImage(offScreen,0,0,null);
    }
 
    public void update(Graphics g){
        paint(g);
    }
     
    public static void main(String args[]){
        Xuehua snow=new Xuehua();
        snow.setBounds(0,0, 500, 500);
        snow.init();
        JFrame jf=new JFrame("SnowPic");
        jf.setSize(500, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(snow);
        jf.setVisible(true);
        snow.start();
    }
}