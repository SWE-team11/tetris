package team11.tetris.view;

import team11.tetris.presenter.BoardPresenter;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import javax.swing.JTextPane;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainMenuView {

    public static void main(String[] args){
        int width = 420;
        int height = 600;
        int btnWidth = 320;
        int btnheight = 90;

        JFrame frame = new JFrame("TETRIS");
        JLabel backgroundImg = new JLabel();

        ImageIcon bgImg = new ImageIcon("C:/Users/예찬/tetris/src/team11/tetris/image/View.jpg");
        backgroundImg.setIcon(bgImg);
        backgroundImg.setSize(400,600);
        //backgroundImg.setBounds(0, 0, 400, 600);
        //backgroundImg.setHorizontalAlignment(JLabel.CENTER);

        JButton playBtn = new JButton(new ImageIcon("C:/Users/예찬/tetris/src/team11/tetris/image/play.png"));
        JTextPane mainPane = new JTextPane();
        Dimension dim = new Dimension(400,600);
        frame.setPreferredSize(dim);
        frame.pack();


        frame.setVisible(true);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPane.setSize(width, height);
        mainPane.setVisible(true);
        mainPane.setEditable(false);
        mainPane.setBackground(Color.BLACK);

        playBtn.setBounds(width/2-btnWidth/2-10, height/2-btnheight/2-10, btnWidth, btnheight); //start 버튼 위치, 크기

        playBtn.setBorderPainted(false); //버튼 테두리 없음
        playBtn.setContentAreaFilled(false); //버튼 색 없음


        //playBtn.setPressedIcon(new ImageIcon("C:/Users/예찬/tetris/src/team11/tetris/image/pushed_start_button.png"));

        frame.add(backgroundImg);
        //mainPane.add(playBtn);
        frame.add(mainPane);
   }

}
