package com.example.console;

import com.example.core.Hangman;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 猜单词的控制台交互层：绘制火柴小人、展示进度，判定全部交给 {@link Hangman}。
 */
public class HangmanConsole {

    /** 按错误次数递增的七个阶段 */
    private static final String[][] GALLOWS = {
            {"  +---+", "  |   |", "      |", "      |", "      |", "      |", "========="},
            {"  +---+", "  |   |", "  O   |", "      |", "      |", "      |", "========="},
            {"  +---+", "  |   |", "  O   |", "  |   |", "      |", "      |", "========="},
            {"  +---+", "  |   |", "  O   |", " /|   |", "      |", "      |", "========="},
            {"  +---+", "  |   |", "  O   |", " /|\\  |", "      |", "      |", "========="},
            {"  +---+", "  |   |", "  O   |", " /|\\  |", " /    |", "      |", "========="},
            {"  +---+", "  |   |", "  O   |", " /|\\  |", " / \\  |", "      |", "========="}
    };

    private final Input input;

    public HangmanConsole(Input input) {
        this.input = input;
    }

    public void play() {
        Hangman game = new Hangman();
        System.out.println("\n=== 猜单词 ===");
        System.out.println("猜一个英文技术词汇，猜错 6 次就输了（输入 0 返回菜单）");

        while (!game.isFinished()) {
            printStage(game.getWrongCount());
            System.out.printf("单词：%s%n", game.getDisplay());
            System.out.printf("已猜：%s    剩余机会：%d%n",
                    formatGuessed(game.getGuessedLetters()), game.remainingAttempts());

            char letter = input.readLetter("请猜一个字母：");
            if (letter == Input.EXIT_CHAR) {
                return;
            }
            System.out.println("  → " + game.guess(letter).getLabel());
        }

        printStage(game.getWrongCount());
        if (game.isWon()) {
            System.out.printf("恭喜！答案就是 %s。%n", game.getWord());
        } else {
            System.out.printf("很遗憾，答案是 %s。%n", game.getWord());
        }
    }

    private void printStage(int wrongCount) {
        System.out.println();
        for (String line : GALLOWS[Math.min(wrongCount, Hangman.MAX_WRONG)]) {
            System.out.println(line);
        }
        System.out.println();
    }

    private String formatGuessed(Set<Character> letters) {
        if (letters.isEmpty()) {
            return "（无）";
        }
        List<Character> sorted = new ArrayList<>(letters);
        Collections.sort(sorted);
        StringBuilder sb = new StringBuilder();
        for (Character letter : sorted) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(letter);
        }
        return sb.toString();
    }
}
