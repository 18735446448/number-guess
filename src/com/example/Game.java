package com.example;

import java.util.Random;

/**
 * 猜数字游戏的核心逻辑：在 [MIN, MAX] 区间内随机生成一个答案，玩家在限定次数内猜测。
 */
public class Game {

    public static final int MIN = 1;
    public static final int MAX = 100;
    public static final int MAX_ATTEMPTS = 10;

    private final Random random = new Random();
    private final int answer;
    private int attempts = 0;

    public Game() {
        this.answer = random.nextInt(MAX - MIN + 1) + MIN;
    }

    /**
     * 提交一次猜测。
     *
     * @param value 玩家猜的数字
     * @return 提示文案：太小了 / 太大了 / 猜中了
     */
    public String guess(int value) {
        attempts++;
        if (value < answer) {
            return "太小了";
        }
        if (value > answer) {
            return "太大了";
        }
        return "猜中了";
    }

    public boolean isWin(int value) {
        return value == answer;
    }

    /** 是否已经用完所有机会 */
    public boolean isOutOfAttempts() {
        return attempts >= MAX_ATTEMPTS;
    }

    public int remainingAttempts() {
        return Math.max(0, MAX_ATTEMPTS - attempts);
    }

    public int getAttempts() {
        return attempts;
    }

    public int getAnswer() {
        return answer;
    }
}
