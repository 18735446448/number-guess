package com.example.console;

import com.example.core.TicTacToe;

/**
 * 井字棋的控制台交互层：展示棋盘、读取落子位置，胜负判定由 {@link TicTacToe} 负责。
 */
public class TicTacToeConsole {

    private final Input input;

    public TicTacToeConsole(Input input) {
        this.input = input;
    }

    public void play() {
        TicTacToe game = new TicTacToe();
        System.out.println("\n=== 井字棋 ===");
        System.out.println("你是 X，电脑是 O，输入 1~9 落子，0 返回菜单");

        while (!game.isFinished()) {
            printBoard(game.getBoard());
            int position = input.readInt("请落子：", 0, 9);
            if (position <= 0) {
                return;
            }
            if (!game.playerMove(position)) {
                System.out.println("  这个位置不能下，换一个");
            }
        }

        printBoard(game.getBoard());
        if (game.isDraw()) {
            System.out.println("平局！");
        } else if (game.getWinner() == TicTacToe.PLAYER) {
            System.out.println("你赢了！");
        } else {
            System.out.println("电脑赢了，再来一局？");
        }
    }

    private void printBoard(char[] board) {
        System.out.println();
        for (int row = 0; row < 3; row++) {
            if (row > 0) {
                System.out.println("---+---+---");
            }
            System.out.printf(" %c | %c | %c %n",
                    cell(board, row * 3), cell(board, row * 3 + 1), cell(board, row * 3 + 2));
        }
        System.out.println();
    }

    /** 空格显示位置编号，已落子显示棋子 */
    private char cell(char[] board, int index) {
        return board[index] == TicTacToe.EMPTY ? (char) ('1' + index) : board[index];
    }
}
