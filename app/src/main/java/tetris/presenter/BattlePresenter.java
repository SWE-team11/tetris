package tetris.presenter;

import tetris.model.BattleModel;
import tetris.model.ConfigModel;
import tetris.utils.Presenter;
import tetris.view.BattleView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class BattlePresenter implements Presenter {
    private BattleModel battleModelP1, battleModelP2;
    private BattleView battleView;
    private final Timer mainTimerP1;
    private final Timer deleteTimerP1;
    private final Timer weightItemTimerP1;
    private final Timer mainTimerP2;
    private final Timer deleteTimerP2;
    private final Timer weightItemTimerP2;
    private final Timer timeAttackTimer;
    private int seconds;

    private static final double INIT_INTERVAL = 1000 / ConfigModel.gameSpeed;

    public BattlePresenter() {
        initPresent();

        mainTimerP1 = new Timer((int)INIT_INTERVAL, new MainTimerActionListener(true));
        deleteTimerP1 = new Timer((int)INIT_INTERVAL / 3, new DeleteTimerActionListener(true));
        weightItemTimerP1 = new Timer((int)INIT_INTERVAL / 5, new WeightItemTimerActionListener(true));
        mainTimerP2 = new Timer((int)INIT_INTERVAL, new MainTimerActionListener(false));
        deleteTimerP2 = new Timer((int)INIT_INTERVAL / 3, new DeleteTimerActionListener(false));
        weightItemTimerP2 = new Timer((int)INIT_INTERVAL / 5, new WeightItemTimerActionListener(false));
        timeAttackTimer = new Timer(1000, new TimeAttackActionListener());
    }

    public class MainTimerActionListener implements ActionListener {
        private boolean isPlayer1;
        public MainTimerActionListener(boolean isPlayer1) {
            this.isPlayer1 = isPlayer1;
        }

        @Override
        public final void actionPerformed(final ActionEvent e) {
            moveDown(isPlayer1);
        }
    }

    public class DeleteTimerActionListener implements ActionListener {
        private boolean isPlayer1;
        public DeleteTimerActionListener(boolean isPlayer1) {
            this.isPlayer1 = isPlayer1;
        }

        @Override
        public final void actionPerformed(final ActionEvent e) {
            if(isPlayer1) battleModelP1.runDelete();
            else battleModelP2.runDelete();
            drawView();
        }
    }

    public class WeightItemTimerActionListener implements ActionListener {
        private boolean isPlayer1;
        public WeightItemTimerActionListener(boolean isPlayer1) {
            this.isPlayer1 = isPlayer1;
        }

        @Override
        public final void actionPerformed(final ActionEvent e) {
            if(isPlayer1) battleModelP1.moveWeightItemDown();
            else battleModelP2.moveWeightItemDown();
            drawView();
        }
    }

    public class TimeAttackActionListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            seconds--;
            battleView.drawTimer(seconds);
            if(seconds == 0) {
                gameOver();
            }
        }
    }

    @Override
    public void initPresent() {
        this.seconds = 180;
        this.battleModelP1 = new BattleModel(this, true);
        this.battleModelP2 = new BattleModel(this, false);
        this.battleModelP1.setOpposite(battleModelP2);
        this.battleModelP2.setOpposite(battleModelP1);

        this.battleView = new BattleView(this);
        this.battleView.drawBoard(this.battleModelP1.getBoard(), true);
        this.battleView.drawBoard(this.battleModelP2.getBoard(), false);
    }

    @Override
    public final void setVisible(final boolean visible) {
        if (visible) {
            battleView.setVisible(true);
            gameStart();
        } else {
            battleView.setVisible(false);
            gameStop();
        }
    }

    public final void gameStart() {
        battleView.stopPauseKeyListen();
        battleView.startPlayerKeyListen(true);
        battleView.startPlayerKeyListen(false);
        battleView.setVisiblePauseDialog(false);
        battleView.drawBoard(battleModelP1.getBoard(), true);
        battleView.drawBoard(battleModelP2.getBoard(), false);
        mainTimerP1.start();
        deleteTimerP1.start();
        mainTimerP2.start();
        deleteTimerP2.start();
        if(ConfigModel.isTimeAttackMode) {
            timeAttackTimer.start();
        }
    }

    public final void gameStop() {
        battleView.stopPlayerKeyListen(true);
        battleView.stopPlayerKeyListen(false);
        battleView.startPauseKeyListen();
        battleView.setVisiblePauseDialog(true);
        mainTimerP1.stop();
        deleteTimerP1.stop();
        mainTimerP2.stop();
        deleteTimerP2.stop();
        if(ConfigModel.isTimeAttackMode) {
            timeAttackTimer.stop();
        }
    }

    public final void gameOver() {
        battleView.drawScrollDialog((int) battleModelP1.getScore(), (int) battleModelP2.getScore());
        battleView.stopPlayerKeyListen(true);
        battleView.stopPlayerKeyListen(false);
        battleView.stopPauseKeyListen();
        battleView.setVisiblePauseDialog(false);
        battleView.setVisibleScoreDialog(true);
        mainTimerP1.stop();
        mainTimerP2.stop();
        timeAttackTimer.stop();
    }

    public final void weightItemStart(boolean isPlayer1) {
        if(isPlayer1) {
            battleView.stopPlayerKeyListen(true);
            mainTimerP1.stop();
            weightItemTimerP1.start();
        } else {
            battleView.stopPlayerKeyListen(false);
            mainTimerP2.stop();
            weightItemTimerP2.start();
        }
    }

    public final void weightItemStop(boolean isPlayer1) {
        if(isPlayer1) {
            battleView.startPlayerKeyListen(true);
            mainTimerP1.start();
            weightItemTimerP1.stop();
        } else {
            battleView.startPlayerKeyListen(false);
            mainTimerP2.start();
            weightItemTimerP2.stop();
        }
    }

    public void drawView() {
        battleView.drawBoard(battleModelP1.getBoard(), true);
        battleView.drawBoard(battleModelP2.getBoard(), false);
        battleView.drawNextBlock(battleModelP1.getNextBlock(), true);
        battleView.drawNextBlock(battleModelP2.getNextBlock(), false);
        battleView.drawScore(battleModelP1.getScore(), true);
        battleView.drawScore(battleModelP2.getScore(), false);
        battleView.drawDeletedRaw(battleModelP1.getDeletedRaw(), true);
        battleView.drawDeletedRaw(battleModelP2.getDeletedRaw(), false);
        battleView.drawAttack(battleModelP1.getAttack(), true);
        battleView.drawAttack(battleModelP2.getAttack(), false);
    }

    public final void moveRotate(boolean isPlayer1) {
        if(isPlayer1) {
            battleModelP1.moveRotate();
        } else {
            battleModelP2.moveRotate();
        }
        drawView();
    }

    public final void moveDown(boolean isPlayer1) {
        if(isPlayer1) {
            battleModelP1.moveDownAndCheck();
        } else {
            battleModelP2.moveDownAndCheck();
        }
        drawView();
    }

    public final void moveLeft(boolean isPlayer1) {
        if(isPlayer1) {
            battleModelP1.moveLeft();
        } else {
            battleModelP2.moveLeft();
        }
        drawView();
    }

    public final void moveRight(boolean isPlayer1) {
        if(isPlayer1) {
            battleModelP1.moveRight();
        } else {
            battleModelP2.moveRight();
        }
        drawView();
    }

    public final void moveStraightDown(boolean isPlayer1) {
        if(isPlayer1) {
            battleModelP1.moveStraightDown();
        } else {
            battleModelP2.moveStraightDown();
        }
        drawView();
    }

}
