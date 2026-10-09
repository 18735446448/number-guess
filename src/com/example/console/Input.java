package com.example.console;

import java.util.Scanner;

/**
 * 控制台输入工具：统一读取指定范围内的整数，避免每个游戏重复写校验逻辑。
 *
 * <p>约定：输入流结束（例如管道耗尽）时返回 {@link #EXIT}，调用方据此退出当前游戏。
 */
public final class Input {

    /** 输入结束或用户放弃时的返回值 */
    public static final int EXIT = -1;

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
}
