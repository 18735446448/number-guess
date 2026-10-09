# number-guess

一个零依赖的 Java 17 控制台小游戏：在 1~100 之间猜数字，总共 7 次机会。

## 项目结构

```
number-guess
├── src
│   └── com
│       └── example
│           ├── App.java     # 程序入口，负责控制台交互
│           └── Game.java    # 游戏核心逻辑
├── build.sh                 # 编译 + 运行脚本
└── .gitignore
```

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

## 玩法

程序随机生成 1~100 的整数，根据输入提示「太大了 / 太小了」，7 次机会内猜中即获胜。
