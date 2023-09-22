# javafx-native-media-demo
JavaFX Native For Media.

# 测试通过环境
## 先决条件
> 0.Windows操作系统一台（当前使用的是Windows11）

> 1.下载并安装 graalvm-jdk-17_windows-x64_bin.zip，解压将bin路径放入path

> 2.设置好maven环境

> 3.安装好Visual Studio 22，其中通用平台必选


## 编译方法
> 4.打开X64 Native Tools Command Prompt

> 5.进入项目目录

> 6.执行`mvn clean gluonfx:build`(下载javafx静态库会有些慢，耐心等待)

> 7.等待编译结果

> 8.直接点击 target/gluonfx/x86_64-windows/javafx-native-media.exe

> 9.可以点击中间按钮播放不同的声音


## 如果觉得本项目很有帮助，请不要吝啬打星哦😊