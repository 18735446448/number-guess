package com.example.core;

import java.util.Random;

/**
 * 石头剪刀布的核心逻辑：随机生成电脑的出拳，并判定胜负。
 */
public class RockPaperScissors {

    /** 三种手势 */
    public enum Move {
        ROCK("石头"),
        SCISSORS("剪刀"),
        PAPER("布");

        private final String label;

        Move(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    /** 单局结果 */
    public enum Result {
        WIN("你赢了"),
        LOSE("你输了"),
        DRAW("平局");

        private final String label;

        Result(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    private final Random random = new Random();
    private final Move computerMove;

    public RockPaperScissors() {
        Move[] moves = Move.values();
        this.computerMove = moves[random.nextInt(moves.length)];
    }

    public Move getComputerMove() {
        return computerMove;
    }

    /**
     * 判定一局结果。
     *
     * @param playerMove 玩家出的手势
     * @return 胜 / 负 / 平
     */
    public Result judge(Move playerMove) {
        if (playerMove == computerMove) {
            return Result.DRAW;
        }
        boolean win = switch (playerMove) {
            case ROCK -> computerMove == Move.SCISSORS;
            case SCISSORS -> computerMove == Move.PAPER;
            case PAPER -> computerMove == Move.ROCK;
        };
        return win ? Result.WIN : Result.LOSE;
    }

    /** 按顺序返回全部手势，方便界面层展示菜单 */
    public static Move[] moves() {
        return Move.values();
    }
}
