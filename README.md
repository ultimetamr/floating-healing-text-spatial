# 悬浮文字疗愈空间

![应用图标](app/src/main/res/mipmap-xxxhdpi/ic_healing_space_launcher_v2.png)

一个基于 PICO Spatial SDK 6.0.0 与 Kotlin 开发的 Shared Space 疗愈应用。用户可以创建自定义短句，将文字以柔和、半透明的空间形式放置在房间中，并通过 Stage Mixed 完成 Persistent Spatial Anchor 的保存与恢复。

- 包名：`com.spatialapps.floatinghealingtext`
- 当前版本：`1.1 (2)`
- 平台：PICO OS 6 / Android 16
- 空间容器：Volumetric WindowContainer + Stage Mixed

## 主要功能

- 自定义创建、编辑和删除悬浮文字
- 三条内置疗愈文案，分散显示且互不遮挡
- 无衬线、衬线和手写字体意图
- 白、暖黄、柔粉、清蓝和薄荷绿配色
- 大小、透明度、缓慢浮动和呼吸效果
- Billboard 面向用户、凝视增强与空间手势
- Android SpeechRecognizer 语音输入
- Room 数据持久化与 Persistent Spatial Anchor 生命周期
- PICO OS 6 分层启动图标与 SDF

## 空间模型

- Shared Space 中的 Volumetric `DefaultWindowContainer` 负责文字管理、样式编辑和非世界锁定预览。
- 显式 `StageStyle.Mixed` Stage 负责真实 Persistent Spatial Anchor 的创建、加载、更新、删除和重启恢复。
- Shared WindowContainer 不会被错误描述为能够持续显示世界锁定锚点。

## 构建

需要 Android Studio 2025.1.x、JDK 11+、Android SDK 35 和 PICO Spatial SDK 6.0.0。

```powershell
.\gradlew.bat testDebugUnitTest assembleDebug
```

生成的 APK 位于：`app/build/outputs/apk/debug/app-debug.apk`。

## 安装与启动

```powershell
pico-cli device list --format json
pico-cli app install app/build/outputs/apk/debug/app-debug.apk --replace --grant-permissions
pico-cli app launch com.spatialapps.floatinghealingtext --activity .platform.LaunchActivity
```

## 项目结构

- `app/src/main/java/.../ui`：Shared 管理界面、文字编辑器和预设入口
- `app/src/main/java/.../content`：Volumetric ECS 内容和 Stage Anchor 流程
- `app/src/main/java/.../data`：Room 数据库与仓库实现
- `app/src/main/java/.../voice`：语音输入
- `app/src/main/res`：主题、分层图标和 Android 资源
- `scripts`：可复现的 PICO 分层图标生成脚本
- `work/design-package`：需求、空间交互、视觉系统和原型审查文档

## 验证状态

项目已通过单元测试与 Debug 构建，并在 PICO B3110 真机上完成覆盖安装和启动验证。真机运行未发现即时崩溃。真实房间中的 Anchor 重定位精度、长期舒适性和 5 段以上文字的稳定 60fps 仍需持续设备测试。
