#!/usr/bin/env bash
# 编译并运行猜数字游戏
set -e

cd "$(dirname "$0")"

echo ">>> 编译..."
rm -rf out
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")

echo ">>> 运行..."
java -Dfile.encoding=UTF-8 -cp out com.example.App
