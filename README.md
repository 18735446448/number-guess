# Java 小游戏合集

零依赖的 Java 17 控制台小游戏合集，包含猜数字、石头剪刀布、井字棋、猜单词四个游戏。

## 项目结构

```
src/com/example/
├── App.java                            # 程序入口：游戏菜单
├── core/                               # 纯逻辑层（不含任何输入输出）
│   ├── NumberGuess.java                #   猜数字：答案生成、大小判定、次数统计
│   ├── RockPaperScissors.java          #   石头剪刀布：电脑出拳、胜负判定
│   ├── TicTacToe.java                  #   井字棋：棋盘状态、胜负判定、电脑 AI
│   └── Hangman.java                    #   猜单词：词库抽取、字母揭示、错误计数
└── console/                            # 交互层（负责输入输出）
    ├── Input.java                      #   统一的整数 / 字母读取与校验
    ├── NumberGuessConsole.java
    ├── RockPaperScissorsConsole.java
    ├── TicTacToeConsole.java
    └── HangmanConsole.java
```

**分层原则**：`core` 里的类不认识 `Scanner`，也不调用 `System.out`，只维护规则与状态；
`console` 里的类只管把人的输入翻译成数据、把结果翻译成文字。
因此把游戏换成 GUI 或 Web 界面时，`core` 一行都不用改。

## 环境要求

- JDK 17 及以上（`java -version` 检查）

## 运行

```bash
chmod +x build.sh
./build.sh
```

或手动执行：

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -Dfile.encoding=UTF-8 -cp out com.example.App
```

## 游戏说明

| 菜单 | 游戏 | 玩法 |
|------|------|------|
| 1 | 猜数字 | 在 1~100 中猜出答案，共 10 次机会，每次提示「太大了 / 太小了」 |
| 2 | 石头剪刀布 | 输入 `1` 石头、`2` 剪刀、`3` 布，`0` 返回菜单，可连续对战 |
| 3 | 井字棋 | 支持人机对战与双人对战，输入 1~9 落子 |
| 4 | 猜单词 | 猜英文技术词汇，累计猜错 6 次即失败，附火柴小人进度 |

- 井字棋电脑 AI 策略：`能赢就赢 → 堵住玩家 → 占中心 → 占角 → 随机`
- 猜单词已猜过的字母会记录并展示，重复猜测不计入错误次数

## 通用操作

菜单与游戏内输入 `0` 即可返回上级，输入流结束（Ctrl+D）也会安全退出。
