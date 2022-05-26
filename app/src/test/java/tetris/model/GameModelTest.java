package tetris.model;

import org.junit.jupiter.api.*;
import org.junit.Test;
import tetris.utils.BlockKind;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GameModelTest {
    //normal 난이도에서의 I블럭 생성 개수.
    @Test
    @DisplayName("Normal 난이도 블럭 생성 테스트")
    public void INormalPercent() {
        //given
        final int test_num = 10000;
        final int TetrominoSize = BlockKind.getTetrominoSize();
        int blockCnt[] = new int[TetrominoSize];
        Random rnd = new Random(System.currentTimeMillis());

        //블록 생성
        for(int i=0; i<test_num; i++) {
            BlockKind index = BlockKind.values()[rnd.nextInt(70)/10];
            blockCnt[index.ordinal()]++;
        }

        //테스트
        for(int i=0; i<TetrominoSize; i++) {
            int ratio = (int)((double) blockCnt[i] / test_num * 100);
            int expectRatio = 10 / 70;
            assertEquals(expectRatio - 5 <= ratio && expectRatio + 5 >= ratio, false);
        }
    }

    @Test
    @DisplayName("Easy 난이도 블럭 생성 테스트")
    public void IEasyPercent(){
        //given
        final int test_num = 10000;
        final int TetrominoSize = BlockKind.getTetrominoSize();
        int blockCnt[] = new int[TetrominoSize];
        Random rnd = new Random(System.currentTimeMillis());

        //블록 생성
        for(int i=0; i<test_num; i++) {
            int rndNumber = rnd.nextInt(72)/10;
            if(rndNumber > 6) rndNumber = 6;
            BlockKind index = BlockKind.values()[rndNumber];
            blockCnt[index.ordinal()]++;
        }

        //테스트
        for(int i=0; i<TetrominoSize; i++) {
            int ratio = (int)((double) blockCnt[i] / test_num * 100);
            int expectRatio = 100 / TetrominoSize;
            if(i == BlockKind.I_BLOCK.ordinal()) {
                expectRatio = (int)(12/72);
            } else {
                expectRatio = (int)(10/72);
            }
            assertEquals(expectRatio - 5 <= ratio && expectRatio + 5 >= ratio, false);
        }
    }

    @Test
    @DisplayName("Hard 난이도 블럭 생성 테스트")
    public void IHardPercent(){
        //given
        final int test_num = 10000;
        final int TetrominoSize = BlockKind.getTetrominoSize();
        int blockCnt[] = new int[TetrominoSize];
        Random rnd = new Random(System.currentTimeMillis());

        //블록 생성
        for(int i=0; i<test_num; i++) {
            BlockKind index = BlockKind.values()[rnd.nextInt(68)/10];
            blockCnt[index.ordinal()]++;
        }

        //테스트
        for(int i=0; i<TetrominoSize; i++) {
            int ratio = (int)((double) blockCnt[i] / test_num * 100);
            int expectRatio = 100 / TetrominoSize;
            if(i == BlockKind.I_BLOCK.ordinal()) {
                expectRatio = (int)(8/68);
            } else {
                expectRatio = (int)(10/68);
            }
            assertEquals(expectRatio - 5 <= ratio && expectRatio + 5 >= ratio, false);
        }
    }
}