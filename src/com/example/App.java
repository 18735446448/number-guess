package com.example;

import com.example.console.HangmanConsole;
import com.example.console.Input;
import com.example.console.NumberGuessConsole;
import com.example.console.RockPaperScissorsConsole;
import com.example.console.TicTacToeConsole;
import java.util.Scanner;

/**
 * 程序入口：展示游戏菜单，根据用户选择进入对应的小游戏。
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Input input = new Input(scanner);

        System.out.println("========== Java 小游戏合集 ==========");

        while (true) {
            printMenu();
            int choice = input.readInt("请选择：", 0, 4);
            if (choice <= 0) {
                break;
            }
            switch (choice) {
                case 1 -> new NumberGuessConsole(input).play();
                case 2 -> new RockPaperScissorsConsole(input).play();
                case 3 -> new TicTacToeConsole(input).play();
                case 4 -> new HangmanConsole(input).play();
                default -> {
                    // readInt 已限定范围，不会走到这里
                }
            }
        }

        System.out.println("再见！");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("""
                
                1. 猜数字
                2. 石头剪刀布
                3. 井字棋
                4. 猜单词
                0. 退出""");
    }
}
