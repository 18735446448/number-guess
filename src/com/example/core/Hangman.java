package com.example.core;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 猜单词（Hangman）核心逻辑：随机挑选一个英文单词，玩家逐个猜字母，猜错到上限即失败。
 *
 * <p>本类同样不依赖任何输入输出设施。
 */
public class Hangman {

    /** 允许的最大错误次数 */
    public static final int MAX_WRONG = 6;

    /** 单次猜测的结果 */
    public enum Result {
        HIT("猜对了"),
        MISS("没有这个字母"),
        REPEATED("这个字母已经猜过了"),
        WIN("你赢了"),
        LOSE("你输了");

        private final String label;

        Result(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    private static final String[] WORDS = {
            "JAVA", "GIT", "GITHUB", "COMMIT", "BRANCH", "MERGE",
            "SPRING", "MAVEN", "THREAD", "STREAM", "LAMBDA", "ENCAPSULATION"
    };

    private final Random random = new Random();
    private final String word;
    private final boolean[] revealed;
    private final Set<Character> guessed = new HashSet<>();
    private int wrongCount = 0;

    public Hangman() {
        this.word = WORDS[random.nextInt(WORDS.length)];
        this.revealed = new boolean[word.length()];
    }

    /**
     * 猜一个字母。
     *
     * @param letter 玩家猜测的字母（大小写均可）
     * @return 本次猜测的结果
     */
    public Result guess(char letter) {
        char upper = Character.toUpperCase(letter);
        if (!Character.isLetter(upper)) {
            return Result.MISS;
        }
        if (guessed.contains(upper)) {
            return Result.REPEATED;
        }

        guessed.add(upper);
        boolean hit = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == upper) {
                revealed[i] = true;
                hit = true;
            }
        }
        if (!hit) {
            wrongCount++;
        }

        if (isWon()) {
            return Result.WIN;
        }
        if (isLost()) {
            return Result.LOSE;
        }
        return hit ? Result.HIT : Result.MISS;
    }

    /** 用下划线遮蔽未猜出字母的展示形态，例如 "J _ V _" */
    public String getDisplay() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(revealed[i] ? word.charAt(i) : '_');
        }
        return sb.toString();
    }

    public boolean isWon() {
        for (boolean hit : revealed) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }

    public boolean isLost() {
        return wrongCount >= MAX_WRONG;
    }

    public boolean isFinished() {
        return isWon() || isLost();
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public int remainingAttempts() {
        return Math.max(0, MAX_WRONG - wrongCount);
    }

    public Set<Character> getGuessedLetters() {
        return new HashSet<>(guessed);
    }

    public String getWord() {
        return word;
    }
}
