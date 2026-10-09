package com.example.console;

import java.util.Scanner;

/**
 * 控制台输入工具：统一读取整数与字母，避免每个游戏重复写校验逻辑。
 *
 * <p>约定：输入流结束（例如管道耗尽）或用户输入 {@code 0} 时返回退出标记，
 * 调用方据此返回上级菜单。
 */
public final class Input {

    /** 输入结束或用户放弃时 readInt 的返回值 */
    public static final int EXIT = -1;

    /** 输入结束或用户放弃时 readLetter 的返回值 */
    public static final char EXIT_CHAR = '\0';

    private final Scanner scanner;

    public Input(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * 读取 [min, max] 区间内的整数。
     *
     * @param prompt 提示语
     * @return 合法的数字；输入流结束时返回 {@link #EXIT}
     */
    public int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.out.println();
                return EXIT;
            }
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // 落到下方提示，重新读取
            }
            System.out.printf("  输入无效，请输入 %d~%d 之间的数字%n", min, max);
        }
    }

    /**
     * 读取一个字母；输入 0 表示返回上级菜单。
     *
     * @param prompt 提示语
     * @return 合法字母；用户退出时返回 {@link #EXIT_CHAR}
     */
    public char readLetter(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.out.println();
                return EXIT_CHAR;
            }
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("  请输入一个字母");
                continue;
            }
            if ("0".equals(line)) {
                return EXIT_CHAR;
            }
            char letter = line.charAt(0);
            if (!Character.isLetter(letter)) {
                System.out.println("  只能输入字母哦");
                continue;
            }
            return letter;
        }
    }
}
