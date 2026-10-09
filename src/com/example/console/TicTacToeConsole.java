package com.example.console;

import com.example.core.TicTacToe;
import com.example.core.TicTacToe.Mode;

/**
 * 井字棋的控制台交互层：支持人机对战与双人对战。
 */
public class TicTacToeConsole {

    private final Input input;

    public TicTacToeConsole(Input input) {
        this.input = input;
    }

    public void play() {
        System.out.println("\n=== 井字棋 ===");
        int option = input.readInt("选择模式：1.人机对战  2.双人对战  0.返回菜单：", 0, 2);
        if (option <= 0) {
            return;
        }

        boolean vsComputer = option == 1;
        TicTacToe game = new TicTacToe(vsComputer ? Mode.VS_COMPUTER : Mode.TWO_PLAYERS);
        System.out.println(vsComputer ? "你是 X，电脑是 O" : "玩家 X 先手，玩家 O 后手");

        while (!game.isFinished()) {
            printBoard(game.getBoard());
            String prompt = vsComputer
                    ? "请落子："
                    : String.format("玩家 %c 请落子：", game.getCurrentPlayer());
            int position = input.readInt(prompt, 0, 9);
            if (position <= 0) {
                return;
            }
            if (!game.move(position)) {
                System.out.println("  这个位置不能下，换一个");
                continue;
            }
            if (vsComputer && !game.isFinished() && game.getComputerLastMove() > 0) {
                System.out.printf("  电脑下在了 %d 号位%n", game.getComputerLastMove());
            }
        }

        printBoard(game.getBoard());
        if (game.isDraw()) {
            System.out.println("平局！");
        } else if (!vsComputer) {
            System.out.printf("玩家 %c 获胜！%n", game.getWinner());
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
