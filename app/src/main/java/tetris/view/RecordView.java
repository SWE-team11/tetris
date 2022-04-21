package tetris.view;

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

public class RecordView extends JFrame {
    private RecordPresenter recordPresenter;
    
    private ImageIcon getResource(String path) {
        return new ImageIcon(getClass().getClassLoader().getResource(path));
    }

    private Image background = getResource("image/scoreBoard.png").getImage();
    
    public RecordView(final RecordPresenter presenter) {
        super("TETRIS");
        recordPresenter = presenter;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel configPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        configPanel.setLayout(null);

        setFocusable(true);
        requestFocus();
    }

    class KeyBindListener implements KeyListener {
        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {

        }

        @Override
        public void keyReleased(final KeyEvent e) {

        }
    }
}
