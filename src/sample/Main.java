package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("流浪地球");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);
//        MyThread one = new MyThread();
//        MyThread two = new MyThread();
//        one.start();
//        two.start();
        JFrame jFrame = new JFrame();
        jFrame.setSize(1200,1200);

        DrawPanel panel = new DrawPanel();
        Thread t = new Thread(panel);
        t.start();
        jFrame.add(panel);
        jFrame.setVisible(true);

    }
}
class MyJframe extends JFrame{
    public MyJframe() throws HeadlessException {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }
}

class DrawPanel extends JPanel implements Runnable{

    List<Double> angles1 = new ArrayList<>();
    List<Double> angles2 = new ArrayList<>();
    List<Integer> circles1 = new ArrayList<>();
    List<Integer> circles2 = new ArrayList<>();
    List<Color> colors = new ArrayList<>();
    Random random = new Random();

    public DrawPanel() {
        for (int i =0;i<10;i++){
            angles1.add((random.nextDouble()-0.5)*2*3.1415);
            angles2.add((random.nextDouble()-0.5)*2*3.1415);
            circles1.add(random.nextInt((i+1)*5));
            circles2.add(random.nextInt(15));
            colors.add(Color.getHSBColor(random.nextFloat(),random.nextFloat(),random.nextFloat()));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i =1;i<=10;i++) {

            int trackRadius = 25*i;
            int circleRadius = circles1.get(i-1);
            int circleRadius1 = circles2.get(i-1);
            Double angle1 = angles1.get(i-1);
            Double angle = angles2.get(i-1);
            g.drawOval(600 - trackRadius, 600 -trackRadius, 2*trackRadius, 2*trackRadius);
            g.setColor(colors.get(i-1));
            g.fillOval(600 + (int)(trackRadius*Math.cos(angle))-circleRadius, 600 + (int)(trackRadius*Math.sin(angle))-circleRadius, 2*circleRadius, 2*circleRadius);
            g.fillOval(600 + (int)(trackRadius*Math.cos(angle1))-circleRadius1, 600 + (int)(trackRadius*Math.sin(angle1))-circleRadius1, 2*circleRadius1, 2*circleRadius1);

            g.fillOval(600-25*i-5,600-5,10,10);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void run() {
        while (true){
            for (int i =0;i<10;i++){
                angles1.set(i,angles1.get(i)+3.14/180/2*(10-i));
                angles2.set(i,angles2.get(i)+3.14/180/2*(10-i));
//                angles1.set(i,angles1.get(i)+0.0000001*(10-i));
//                angles2.set(i,angles2.get(i)+0.0000001*(10-i));
                circles1.set(i,circles1.get(i)+(random.nextInt(3)-1));
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}





class MyThread implements Runnable{

    private Thread t;

    @Override
    public void run() {
        Application.launch(Main.class);
    }
    void start(){
        t = new Thread(this,"miao");
        t.start();
    }

}
