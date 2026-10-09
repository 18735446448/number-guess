package com.example.console;

import com.example.core.NumberGuess;

/**
 * 猜数字的控制台交互层：负责输入输出与流程控制，规则全部交给 {@link NumberGuess}。
 */
public class NumberGuessConsole {

    private final Input input;

    public NumberGuessConsole(Input input) {
        this.input = input;
    }

    public void play() {
        NumberGuess game = new NumberGuess();
        System.out.println("\n=== 猜数字 ===");
        System.out.printf("我在 %d~%d 之间想了一个数，你有 %d 次机会。%n",
                NumberGuess.MIN, NumberGuess.MAX, NumberGuess.MAX_ATTEMPTS);

        while (true) {
            String prompt = String.format("第 %d 次尝试（还剩 %d 次），请输入：",
                    game.getAttempts() + 1, game.remainingAttempts());
            int value = input.readInt(prompt, NumberGuess.MIN, NumberGuess.MAX);
            if (value == Input.EXIT) {
                return;
            }

            String result = game.guess(value);
            System.out.println("  → " + result);

            if (game.isWin(value)) {
                System.out.printf("恭喜！用了 %d 次猜中答案 %d。%n", game.getAttempts(), game.getAnswer());
                return;
            }
            if (game.isOutOfAttempts()) {
                System.out.printf("机会用完了，正确答案是 %d。%n", game.getAnswer());
                return;
            }
        }
    }
}
