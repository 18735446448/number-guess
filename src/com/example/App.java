package com.example;

import java.util.Scanner;

/**
 * 程序入口：控制台交互的猜数字小游戏。
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        System.out.println("=== 猜数字游戏 ===");
        System.out.printf("我在 %d ~ %d 之间想了一个数，你有 %d 次机会，开始吧！%n",
                Game.MIN, Game.MAX, Game.MAX_ATTEMPTS);

        while (true) {
            System.out.printf("第 %d 次尝试（还剩 %d 次），请输入：",
                    game.getAttempts() + 1, game.remainingAttempts());

            int input;
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  × 请输入有效数字");
                continue;
            }

            String result = game.guess(input);
            System.out.println("  → " + result);

            if (game.isWin(input)) {
                System.out.printf("恭喜！你用了 %d 次猜中答案 %d。%n",
                        game.getAttempts(), game.getAnswer());
                break;
            }

            if (game.isOutOfAttempts()) {
                System.out.printf("机会用完了，正确答案是 %d。再来一局？%n", game.getAnswer());
                break;
            }
        }

        scanner.close();
    }
}
