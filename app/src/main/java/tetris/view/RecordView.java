package tetris.view;

import org.checkerframework.checker.units.qual.C;
import tetris.App;
import tetris.model.ConfigModel;
import tetris.model.RecordModel;
import tetris.presenter.RecordPresenter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class RecordView extends JFrame {
    private RecordPresenter recordPresenter;
    private ArrayList<JPanel> recordPanelList;
    private JButton toMainButton;
    
    private ImageIcon getResource(String path) {
        return new ImageIcon(getClass().getClassLoader().getResource(path));
    }

    private Image background = getResource("image/scoreBoard.png").getImage();
    
    public RecordView(final RecordPresenter presenter) {
        super("TETRIS");
        recordPresenter = presenter;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel recordPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        recordPanel.setLayout(null);

        recordPanelList = new ArrayList<>();
        System.out.println(RecordModel.rankedRecords.size());
        for(int i=0; i<Math.min(RecordModel.rankedRecords.size(), 11); i++) {
            recordPanelList.add(new JPanel());
            JPanel currentPanel = recordPanelList.get(i);
            currentPanel.setLayout(null);
            currentPanel.setBounds(35, 110 + 35*i, 330, 25);
            currentPanel.setBackground(Color.black);
            currentPanel.setBorder(new TitledBorder(new LineBorder(Color.white,2)));

            JTextPane rank = new JTextPane();
            rank.setText(Integer.toString(i+1));
            rank.setOpaque(false);
            rank.setForeground(Color.white);
            rank.setBounds(15, 5, 50, 25);

            JTextPane name = new JTextPane();
            name.setText(RecordModel.rankedRecords.get(i).name);
            name.setOpaque(false);
            name.setForeground(Color.white);
            name.setBounds(50, 5, 50, 25);

            JTextPane score = new JTextPane();
            score.setText(Integer.toString(RecordModel.rankedRecords.get(i).score));
            score.setOpaque(false);
            score.setForeground(Color.white);
            score.setBounds(110, 5, 50, 25);

            JTextPane lines = new JTextPane();
            lines.setText(Integer.toString(RecordModel.rankedRecords.get(i).deletedLine));
            lines.setOpaque(false);
            lines.setForeground(Color.white);
            lines.setBounds(170, 5, 50, 25);

            JTextPane mode = new JTextPane();
            mode.setText(RecordModel.rankedRecords.get(i).gameMode.name());
            mode.setOpaque(false);
            mode.setForeground(Color.white);
            mode.setBounds(200, 5, 50, 25);

            JTextPane level = new JTextPane();
            level.setText(RecordModel.rankedRecords.get(i).gameDifficulty.name());
            level.setOpaque(false);
            level.setForeground(Color.white);
            level.setBounds(245, 5, 50, 25);

            JTextPane date = new JTextPane();
            date.setText(RecordModel.rankedRecords.get(i).createdAt);
            date.setOpaque(false);
            date.setForeground(Color.white);
            date.setBounds(290, 5, 50, 25);

            System.out.print(RecordModel.rankedRecords.get(i).id);
            System.out.print(", ");
            System.out.println(RecordModel.lastId);

            if(RecordModel.rankedRecords.get(i).id == RecordModel.lastId) {
                rank.setForeground(Color.decode("#1BB7ED"));
                name.setForeground(Color.decode("#1BB7ED"));
                score.setForeground(Color.decode("#1BB7ED"));
                lines.setForeground(Color.decode("#1BB7ED"));
                mode.setForeground(Color.decode("#1BB7ED"));
                level.setForeground(Color.decode("#1BB7ED"));
                date.setForeground(Color.decode("#1BB7ED"));
            }
            currentPanel.add(rank);
            currentPanel.add(name);
            currentPanel.add(score);
            currentPanel.add(lines);
            currentPanel.add(mode);
            currentPanel.add(level);
            currentPanel.add(date);
            recordPanel.add(currentPanel);
        }

        toMainButton = new JButton("");
        toMainButton.setOpaque(false);
        toMainButton.setContentAreaFilled(false);
        toMainButton.setBorderPainted(false);
        toMainButton.setBounds(160, 495, 85, 40);
        toMainButton.addActionListener(e -> {
            App.navigate(App.View.MAIN);
        });

        recordPanel.add(toMainButton);
        this.setContentPane(recordPanel);
        setFocusable(true);
        requestFocus();
    }

}
