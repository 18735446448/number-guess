package com.example.console;

import com.example.core.RockPaperScissors;
import com.example.core.RockPaperScissors.Move;

/**
 * 石头剪刀布的控制台交互层：可以连续对战，输入 0 返回上级菜单。
 */
public class RockPaperScissorsConsole {

    private final Input input;

    public RockPaperScissorsConsole(Input input) {
        this.input = input;
    }

    public void play() {
        System.out.println("\n=== 石头剪刀布 ===");
        Move[] moves = RockPaperScissors.moves();

        while (true) {
            StringBuilder prompt = new StringBuilder("请出拳：");
            for (int i = 0; i < moves.length; i++) {
                prompt.append(i + 1).append(".").append(moves[i].getLabel()).append("  ");
            }
            prompt.append("0.返回菜单：");

            int choice = input.readInt(prompt.toString(), 0, moves.length);
            if (choice <= 0) {
                return;
            }

            Move playerMove = moves[choice - 1];
            RockPaperScissors round = new RockPaperScissors();
            var result = round.judge(playerMove);
            System.out.printf("  你：%s  电脑：%s  → %s%n",
                    playerMove.getLabel(), round.getComputerMove().getLabel(), result.getLabel());
        }
    }
}
