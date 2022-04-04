package team11.tetris.view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame{

    private Image background=new ImageIcon(MainView.class.getResource("../image/background.png")).getImage();//배경이미지
    //private Image playBtnImg =new ImageIcon(MainView.class.getResource("../image/play.png")).getImage();

    /*생성자*/
    public MainView() {
        homeframe();
    }

    public void homeframe() {
        setTitle("TETRIS");//타이틀
        setSize(400,600);//프레임의 크기
        setResizable(false);//창의 크기를 변경하지 못하게
        setLocationRelativeTo(null);//창이 가운데 나오게
        setLayout(null);//레이아웃을 내맘대로 설정가능하게 해줌.
        setVisible(true);//창이 보이게
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame이 정상적으로 종료되게
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

        playBtn.setBounds(30,300,320,90); //play 버튼 위치, 크기
        playBtn.setBorderPainted(false); //버튼 테두리 없음
        playBtn.setContentAreaFilled(false); //버튼 색 없음

        normal_clickedBtn.setBounds(30,215,150,60); //normal 버튼 위치, 크기
        normal_clickedBtn.setBorderPainted(false); //버튼 테두리 없음
        normal_clickedBtn.setContentAreaFilled(false); //버튼 색 없음

        itemBtn.setBounds(200,215,150,60); //item 버튼 위치, 크기
        itemBtn.setBorderPainted(false); //버튼 테두리 없음
        itemBtn.setContentAreaFilled(false); //버튼 색 없음

        settingBtn.setBounds(250,485,46,46); //setting 버튼 위치, 크기
        settingBtn.setBorderPainted(false); //버튼 테두리 없음
        settingBtn.setContentAreaFilled(false); //버튼 색 없음

        exitBtn.setBounds(305,485,46,46); //exit 버튼 위치, 크기
        exitBtn.setBorderPainted(false); //버튼 테두리 없음
        exitBtn.setContentAreaFilled(false); //버튼 색 없음

        btnPanel.setBounds(0,0,400, 600); //버튼 패널 크기
        btnPanel.setBackground(new Color(0,0,0,255));

        btnPanel.add(playBtn);
        btnPanel.add(normal_clickedBtn);
        btnPanel.add(itemBtn);
        btnPanel.add(settingBtn);
        btnPanel.add(exitBtn);
        add(btnPanel);
    }
    public void paint(Graphics g) {//그리는 함수
        g.drawImage(background, 0, 0, null);//background를 그려줌
        //g.drawImage(playBtnImg, 40, 320, null);
    }
    public static void main(String[] args){
        new MainView();
    }
}
