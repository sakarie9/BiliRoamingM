<div align="center">

# 哔哩漫游M

</div>

> [!IMPORTANT]  
> 基于 [BiliRoamingX](https://github.com/BiliRoamingX/BiliRoamingX)
>
> 此 Fork 针对 Play 版本测试，仅进行维护性更新。

> [!NOTE]  
> **不提供** 预编译包
>
> **请勿在公开场合分享**，随时跑路

基于 ReVanced 实现的B站 Android 客户端增强模块。模块设置完美融入 APP 设置，功能丰富，自定义程度高。
得益于实现方式，对 APP 性能几乎没有影响，流畅、迅速、启动快。支持粉版、Play 版及 HD 版。

## 📖 主要功能

- 自由移除页面组件
- 自定义直播、视频默认清晰度
- 自定义播放速度
- 字幕样式调整，翻译、保存及导入
- 调整 APP 显示大小
- 自由复制评论及视频信息
- 双指缩放视频填充屏幕
- 调用外部下载器保存视频
- 加回频道功能
- 自动领取B币券
- 分享链接净化
- 推荐、热门、动态过滤
- 开屏页背景色跟随深色模式

## 💻 源码构建

```shell
git clone --recurse-submodules https://github.com/sakarie9/BiliRoamingM.git
cd BiliRoamingM
./gradlew dist
```

- Windows 系统上使用 `gradlew.bat` 命令而不是 `./gradlew`
- 构建产物在 `build` 目录下

## ⬇️ 下载使用

- 前往 [BiliRoamingM-PreBuilds Release](https://github.com/sakarie9/BiliRoamingM-PreBuilds/releases/latest) 下载
- 参照 [revanced-cli](https://github.com/ReVanced/revanced-cli/tree/main/docs) 文档打包
  1. 下载定制版 [revanced-cli.jar](https://github.com/zjns/revanced-cli/releases/latest)
  2. 从 [releases](https://github.com/sakarie9/BiliRoamingM/releases/latest) 下载 `integrations.apk` 和 `patches.jar`
  3. 执行终端命令 `java -jar revanced-cli.jar patch --merge integrations.apk --patch-bundle patches.jar --signing-levels 1,2,3 bilibili.apk`

## 📃 Licence

[![GitHub](https://img.shields.io/github/license/sakarie9/BiliRoamingM?style=for-the-badge)](https://github.com/sakarie9/BiliRoamingM/blob/main/LICENSE)
