package team11.tetris.view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame{

    private Image background=new ImageIcon(MainView.class.getResource("../image/background.png")).getImage();

    /*생성자*/
    public MainView() {
        homeframe();
    }

    public void homeframe() {
        setTitle("TETRIS");
        setSize(400,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btnPanel();
    }

    public void btnPanel(){
        JPanel btnPanel = new JPanel();
        JButton playBtn = new JButton(new ImageIcon(MainView.class.getResource("../image/play.png")));
        JButton normalBtn = new JButton(new ImageIcon(MainView.class.getResource("../image/normal.png")));
        JButton normal_clickedBtn = new JButton(new ImageIcon(MainView.class.getResource("../image/normal_clicked.png")));
        JButton itemBtn = new JButton(new ImageIcon(MainView.class.getResource("../image/item.png")));
        JButton item_clickedBtn = new JButton(new ImageIcon(MainView.class.getResource("../image/item_clicked.png")));
        JButton settingBtn = new JButton(new ImageIcon(MainView.class.getResource("../image/setting.png")));
        JButton exitBtn = new JButton(new ImageIcon(MainView.class.getResource("../image/exit.png")));

        playBtn.setBounds(30,300,320,90);
        playBtn.setBorderPainted(false);
        playBtn.setContentAreaFilled(false);

        normal_clickedBtn.setBounds(30,215,150,60);
        normal_clickedBtn.setBorderPainted(false);
        normal_clickedBtn.setContentAreaFilled(false);

        itemBtn.setBounds(200,215,150,60);
        itemBtn.setBorderPainted(false);
        itemBtn.setContentAreaFilled(false);

        settingBtn.setBounds(250,485,46,46);
        settingBtn.setBorderPainted(false);
        settingBtn.setContentAreaFilled(false);

        exitBtn.setBounds(305,485,46,46);
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);

        btnPanel.setBounds(0,0,400, 600);
        btnPanel.setBackground(new Color(0,0,0,255));

        btnPanel.add(playBtn);
        btnPanel.add(normal_clickedBtn);
        btnPanel.add(itemBtn);
        btnPanel.add(settingBtn);
        btnPanel.add(exitBtn);
        add(btnPanel);
    }
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }
    public static void main(String[] args){
        new MainView();
    }
}
