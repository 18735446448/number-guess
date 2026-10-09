package com.example.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 井字棋（3x3）核心逻辑：玩家执 X，电脑执 O，玩家先手。
 *
 * <p>棋盘用长度为 9 的一维数组表示，落子位置为 1~9，与界面显示的九宫格顺序一致：
 *
 * <pre>
 *   1 | 2 | 3
 *  ---+---+---
 *   4 | 5 | 6
 *  ---+---+---
 *   7 | 8 | 9
 * </pre>
 *
 * <p>电脑采用简单策略：能赢就赢 → 堵住玩家 → 占中心 → 占角 → 其余随机。
 */
public class TicTacToe {

    public static final char EMPTY = ' ';
    public static final char PLAYER = 'X';
    public static final char COMPUTER = 'O';

    private static final int[][] LINES = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    private final char[] board = new char[9];
    private final Random random = new Random();
    private char winner = EMPTY;
    private boolean finished = false;

    public TicTacToe() {
        Arrays.fill(board, EMPTY);
    }

    /**
     * 玩家在指定位置落子，随后电脑自动应手。
     *
     * @param position 1~9 的落子位置
     * @return true 表示落子成功；false 表示位置非法或已被占用
     */
    public boolean playerMove(int position) {
        if (finished || position < 1 || position > 9 || board[position - 1] != EMPTY) {
            return false;
        }
        board[position - 1] = PLAYER;
        checkResult();
        if (!finished) {
            computerMove();
        }
        return true;
    }

    private void computerMove() {
        List<Integer> empty = emptyCells();
        if (empty.isEmpty()) {
            return;
        }
        int choice = pickMove(empty);
        board[choice] = COMPUTER;
        checkResult();
    }

    private int pickMove(List<Integer> empty) {
        for (int index : empty) {
            if (isWinningMove(index, COMPUTER)) {
                return index;
            }
        }
        for (int index : empty) {
            if (isWinningMove(index, PLAYER)) {
                return index;
            }
        }
        if (board[4] == EMPTY) {
            return 4;
        }
        List<Integer> corners = new ArrayList<>();
        for (int index : new int[] {0, 2, 6, 8}) {
            if (board[index] == EMPTY) {
                corners.add(index);
            }
        }
        if (!corners.isEmpty()) {
            return corners.get(random.nextInt(corners.size()));
        }
        return empty.get(random.nextInt(empty.size()));
    }

    /** 试算：把该位置交给 who，是否立刻形成三连 */
    private boolean isWinningMove(int index, char who) {
        board[index] = who;
        boolean win = hasLine(who);
        board[index] = EMPTY;
        return win;
    }

    private boolean hasLine(char who) {
        for (int[] line : LINES) {
            if (board[line[0]] == who && board[line[1]] == who && board[line[2]] == who) {
                return true;
            }
        }
        return false;
    }

    private void checkResult() {
        if (hasLine(PLAYER)) {
            winner = PLAYER;
            finished = true;
        } else if (hasLine(COMPUTER)) {
            winner = COMPUTER;
            finished = true;
        } else if (emptyCells().isEmpty()) {
            finished = true;
        }
    }

    private List<Integer> emptyCells() {
        List<Integer> cells = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == EMPTY) {
                cells.add(i);
            }
        }
        return cells;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isDraw() {
        return finished && winner == EMPTY;
    }

    /** 胜者：PLAYER / COMPUTER / EMPTY（未结束或平局） */
    public char getWinner() {
        return winner;
    }

    public char[] getBoard() {
        return board.clone();
    }
}
