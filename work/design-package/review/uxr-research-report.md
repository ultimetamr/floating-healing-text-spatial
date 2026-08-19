# 用户研究报告 · 悬浮文字疗愈空间

> Role: `research_analyst` | Active source revision: 3 | Workflow stage: Stage 2 `research` + `CR-EV-01-02` + provenance-only `CR-DS-01-04`
>
> Source intent: original research used PM rev 1; EV-01/EV-02 facts were reconciled with PM rev 3. CR-DS-01-04 changes only the active revision/delivery provenance labels required by Stage 12 DS-04; all evidence, confidence, gaps and frozen research facts are byte-for-byte semantically unchanged from rev 2。观察截止：2026-08-15。

## 0. 推理边界

- 只形成 market / user / domain / platform / safety 证据、证据缺口和领域模型，不决定最终容器架构、布局或视觉方向。
- 时间敏感的平台能力按所见文档版本和访问日限定；未在目标 PICO 设备验证的能力不提升为事实。
- “疗愈”按非临床健康支持研究，不把应用描述为治疗手段。

## 1. 直接产出

本修订交付五类证据、4 个相邻产品基准、领域工作流与决策变量、数据实体/时效、风险、心智模型，以及基于证据边界的临时 Persona / Journey。所有经验性未知均以 gap 标记。

## 2. 研究目标与问题

- **待验证假设**：`pm-requirement-spec.md` A-01–A-15，重点是使用情境、文字数量/长度、双面文字语义、语音离线性、手势可用性、锚点与 Shared Space 的兼容性。
- **本阶段方法**：用户材料分析；PICO/Android/Apple 官方文档案头研究；同任务或相邻任务产品的一方说明比较；自我肯定干预的系统综述与随机对照研究摘要检查。
- **未执行方法**：用户访谈、目标设备可用性测试、模拟器/真机测试、性能测试。它们在本阶段均为 `not_performed`。
- **样本说明**：无招募样本。任何 Persona 仅是证据约束的招募假设，不可当作用户事实。

## 3. 五类研究证据（含缺口）

| ID | Category | Evidence / Gap (single claim) | Source | Source Type | Scope | Confidence | Observation Time | Applies To | Validation Plan |
|---|---|---|---|---|---|---|---|---|---|
| M-01 | market | 相邻的肯定语/励志产品普遍把“精选内容 + 提醒/小组件 + 个性化”作为持续触达基线，说明本产品不能只做一次性文本创建 | [I am App Store](https://apps.apple.com/us/app/i-am-daily-affirmations/id874656917); [Motivation official](https://motivation.app/) | official (first-party listings/sites) | 2026-08-15 可见的 iOS/移动产品说明；不代表实际留存或疗效 | medium | 2026-08-15 access | 预设文案、快速回看、个性化 | 对各竞品做一次实际安装走查；访谈用户确认“提醒”与“环境常驻”的相对价值 |
| M-02 | market | 空间持久内容已成为相邻 XR 基线：visionOS 26 widgets 可放在房间表面、重启/重入房间后再现，并支持样式/框架个性化 | [Apple Support: Use widgets on Apple Vision Pro](https://support.apple.com/en-us/124816); [Apple Developer HIG: Widgets](https://developer.apple.com/design/human-interface-guidelines/widgets?changes=_4) | official | visionOS 26；相邻平台，只支持表面固定的 widget，不等同 PICO 自由悬浮文字 | high | Apple support published 2025-09-20; accessed 2026-08-15 | 持久空间存在、远距离扫视、位置恢复 | 作为需求机会而非实现依据；PICO 上独立验证锚点与容器限制 |
| U-01 | user | 用户材料明确目标是“随时抬眼看到鼓励自己的话”，但这是产品愿望，不是 Shared Space 跨空间可见性的技术证据 | 用户原始需求 | user_supplied | 本项目产品意图，不证明平台可行性 | high for intent; none for cross-space behavior | 2026-08-15 | 扫视价值、进入成本、文字优先级 | 在显式 Stage Mixed 进入并完成重定位后测试“无需打开管理面板即可找到目标句”；另测进入成本 |
| U-02 | user | 目标人群的年龄、视力、色觉、XR 熟练度、房间类型、社交隐私和实际文字数量均未知 | none | assumption (evidence gap) | 所有潜在目标用户 | low | 2026-08-15 | 字体/尺度、输入、隐私、锚点容量 | 招募 8–12 人分层访谈与原型测试；覆盖新手/熟练、亮/暗房、独处/旁观者 |
| D-01 | domain | 非临床一般人群中，自我肯定干预与自我认知和总体幸福感的改善存在小幅统计关联，但效应受人群与地区影响，不能推导个体疗效承诺 | [PubMed: The impact of self-affirmation interventions on well-being](https://pubmed.ncbi.nlm.nih.gov/41143765/) | external (meta-analysis) | 67 篇研究的非临床一般人群；不是本产品试验 | medium-high | PMID 41143765; accessed 2026-08-15 | 产品措辞、风险边界、评估指标 | 商店/应用文案禁用治疗承诺；单独做可用性与主观支持感评估，不外推医学结果 |
| D-02 | domain | 中国青少年 RCT 报告某些生活满意度/自尊结果改善，但对抑郁、焦虑和孤独临床指标没有效果，进一步支持“辅助而非治疗”的边界 | [PubMed: Chinese adolescents RCT](https://pubmed.ncbi.nlm.nih.gov/38155488/) | external (primary RCT) | 特定疫情时期中国青少年；不可泛化至所有成人或 PICO 用户 | medium | published record accessed 2026-08-15 | “疗愈”语义、危机/临床边界 | 法务与健康传播审阅；若未来宣称健康效果须另做合规研究 |
| P-01 | platform | PICO Spatial SDK 的 Shared Space 允许 WindowContainer 多任务共存，Planar/Volumetric 用于有边界内容；打开 Stage 会进入 Full Space | [PICO OS 6 Overview](https://developer.picoxr.com/document/discover/pico-os-6-overview/); bundled `official-rules.json` PICO-SPACESTATE-001/002 | official | PICO OS 6 / 当前 Spatial SDK 文档 | high | accessed 2026-08-15 | Shared Space、Volumetric、Stage 边界 | 后续设计只用设计术语；实施前以实际 SDK 版本和模板验证合法组合 |
| P-02 | platform | **硬冲突证据**：当前 PICO Spatial SDK 文档声明 spatial anchor 只能在 Full Space（Stage）中创建/加载；因此不能声称 Shared Space WindowContainer 内直接使用 Persistent Spatial Anchor，也不能从该文档推导退出 Stage 后锚定内容仍在 Shared Space 世界锁定可见 | [PICO Spatial SDK: Spatial anchor](https://developer.picoxr.com/document/spatial-sdk/spatial-anchor/); [AnchorComponent 0.10.7](https://developer.picoxr.com/spatial-api/0.10.7/spatialpack/core/com.pico.spatial.core.ecs/-anchor-component/index.html) | official | 当前公开 Spatial SDK；精确版本与目标镜像仍需实施时确认 | high | accessed 2026-08-15 | 用户“Shared Space + Persistent Anchor”组合 | Shared 只管理/样式/非锚定预览；真实 load/view/place/update/delete/restart relocation 全在 Stage Mixed。Stage 6 必须比较三种诚实方案，不预选跨空间交接 |
| P-02A | platform | **Evidence gap**：目标 Spatial SDK 版本和真实设备是否支持 Stage 中的 anchor/entity 在关闭 Stage 后 handoff 到 Shared Space 并继续世界锁定可见，当前来源没有证明 | none | assumption (evidence gap) | target SDK + target device only | low | 2026-08-15 | Stage→Shared handoff/visibility | 锁定 SDK 后构建最小实验：Stage 创建/加载可见实体→关闭 Stage→检查 Shared 是否存在、是否仍 world-locked→kill/restart/re-enter room；只有日志+真机视觉证据同时通过才可提升为事实 |
| P-03 | platform | 锚点创建后位置存于设备磁盘，可按 UUID 加载；每应用最多 1024，坐标重校准会触发 anchor update，应用需更新关联对象 | [PICO Spatial SDK: Spatial anchor](https://developer.picoxr.com/document/spatial-sdk/spatial-anchor/) | official | PICO Spatial SDK current public page | high | accessed 2026-08-15 | 数据模型、锚点恢复、事件处理 | 实施期以项目锁定 SDK 的 API 参考验证；测试 create→save UUID→kill→load→coordinate recalibration |
| P-04 | platform | 官方建议锚点放置时尽量让用户/视点在 3m 内建立地图；检索范围与用户观察范围相关，最大半径描述为 5m，超出可能失败 | [PICO Spatial SDK: Spatial anchor](https://developer.picoxr.com/document/spatial-sdk/spatial-anchor/) | official | 当前 PICO 空间锚点设计建议 | high | accessed 2026-08-15 | 放置引导、失败恢复、房间边界 | 将 3m 引导与“靠近并环视”恢复纳入后续状态；真房间多距离验证 |
| P-05 | platform | PICO 空间交互建议 gaze 作为光标、pinch/drag 等替代触摸，并需清晰但不抢注意的 hover；Spatial SDK 公开说明碰撞 + 可交互组件与多模态输入统一路径 | [PICO Spatial Design](https://developer.picoxr.com/document/spatial-design/); [PICO 3D interaction paradigm](https://developer.picoxr.com/blog/a-new-3d-interaction-paradigm-for-spatial-applications/) | official | PICO Spatial SDK 设计和技术指南；具体 API 随版本变化 | medium-high | accessed 2026-08-15 | 凝视、拖拽、缩放、旋转、控制器回退 | 后续只把交互语义定为设计事实；实施时按锁定 API 校验符号并测试 hit/collision |
| P-06 | platform | PICO Emulator 可模拟眼手、控制器与双手 pinch，但其渲染 FOV 不是设备物理 FOV；模拟器截图不能证明真机舒适或锚点重定位 | [PICO Emulator UI](https://developer.picoxr.com/document/spatial-toolkit/pico-emulator-ui/) | official | PICO Emulator current docs | high | accessed 2026-08-15 | 验收边界、手势验证 | 模拟器验证逻辑/基本输入；锚点持久与舒适最终保留真机验证 |
| P-07 | platform | Android SpeechRecognizer 需要 `RECORD_AUDIO`，可能把音频流向远端，不适合连续识别，必须在不用时 `destroy()`；可检查/创建 on-device recognizer，但设备可用性不能假定 | [Android SpeechRecognizer](https://developer.android.com/reference/android/speech/SpeechRecognizer.html) | official | Android API；目标 PICO 镜像服务可用性未知 | high | last updated 2026-02-26; accessed 2026-08-15 | 快速语音输入、权限、隐私、回退 | 运行时 `checkRecognitionSupport` / `isOnDeviceRecognitionAvailable`；拒权/无网/超时/语言不支持转键盘 |
| P-08 | platform | Room entity 将对象映射为表行并支持带主键的本地结构化持久化，适合保存文字/样式/锚点 UUID 关联元数据；它不替代空间锚点的空间权威 | [Android: Define data using Room entities](https://developer.android.com/training/data-storage/room/defining-data) | official | AndroidX Room | high | accessed 2026-08-15 | 本地数据模型与冲突规则 | 用 repository contract 明确 Room 保存 UUID/内容，Anchor API 保存空间；做丢锚/孤儿行一致性测试 |
| S-01 | safety | PICO 空间设计禁止突然全景遮挡、无预告大范围位移、暗环境高饱和大色块和在楼梯/过街/驾驶等动态危险情境使用 | [PICO Spatial Design](https://developer.picoxr.com/document/spatial-design/) | official | PICO spatial apps | high | accessed 2026-08-15 | 浮动/呼吸、Shared Space 遮挡、使用提示 | Reduce Motion；限制大面积/高亮/高速运动；首启安全提示与运动中使用禁用文案 |
| S-02 | safety | 默认 5cm/8s 浮动、6s 呼吸和 80% 透明度的实际舒适度、亮暗房对比度与周边视觉干扰没有设备证据 | none | assumption (evidence gap) | 本项目动效/材质 | low | 2026-08-15 | 动效参数、可读性、疲劳 | 暗/亮房 10 分钟观察仅用于发现问题并记录辨读/不适，不得当作舒适证明或安全上限；始终保留 Reduce Motion/静止分支 |
| S-03 | safety | 用户自定义文字可能包含自伤、羞辱或敏感个人内容；本地展示和语音处理的隐私期望未知 | none | assumption (evidence gap) | 私人/半公开房间 | low | 2026-08-15 | 内容隐私、删除、旁观者 | 隐私访谈；默认本地存储、不保存音频；一键删除文字+锚点；不在通知中外显完整文本 |

- **市场证据使用边界**：只用于需求覆盖、机会和风险，不直接决定本项目布局、状态图、组件、颜色或动效。
- **源冲突处理**：用户要求“Shared Space + Persistent Anchor”；PICO 官方把 Spatial Anchor 限定在 Full Space/Stage，且没有证明 Stage→Shared handoff/visibility。受证据约束的当前分工是 Shared 只管理/样式/非锚定预览，真实查看与操作全部 Stage Mixed。跨空间可见性保持 `unverified`，不能把用户愿望改写成已支持能力。
- **Stage 6 强制比较集**：① 所有真正 world-locked 文字的查看/放置/更新/删除/重启重定位都在 Stage Mixed；② Shared Space 只提供不具备真实房间锚定的预览；③ 仅当目标 SDK + 真机证据证明 Stage→Shared handoff/visibility 后才允许跨空间。方案③在证据出现前不得进入 Selected Concept 的已支持事实。

## 3A. 竞品 / 相邻产品基准（4 个）

| # | Competitor / Platform | Feature needs | Interaction experience | Visual experience (observation only) | Spatial-capability usage | Source / type / time |
|---:|---|---|---|---|---|---|
| C-01 | Apple visionOS 26 Widgets / Apple Vision Pro | 把信息固定到房间表面；跨佩戴/重入房间再现；支持框宽、颜色、凹入等定制；缺口：不是自由悬浮自定义 3D 文字应用 | 凝视/捏合添加、长按后移动或删除；需要表面检测，暗环境可能放置失败 | 强调跨房间距离可扫视的大字、真实尺度、表面安装和轻微阴影；不复用具体外观 | 真正使用位置、方向、真实尺度与跨会话持久；但必须贴墙/桌/地，不能自由悬浮 | [Apple Support](https://support.apple.com/en-us/124816); [WidgetKit visionOS](https://developer.apple.com/documentation/widgetkit/updating-your-widgets-for-visionos); official, accessed 2026-08-15 |
| C-02 | I am – Daily Affirmations / iOS, iPadOS, Watch, widgets | 提供肯定语、意图分类、定时提醒和 widget；缺口：空间位置/立体文字/房间锚点 | 主路径为选择内容与接收提醒；精确创建/编辑路径在公开说明中是 gap | App Store 强调正向内容和 widget 持续触达；具体密度、字体/动效实测是 gap，不作为复用来源 | 主要是 2D 屏幕/小组件；“抬眼可见”依赖设备表面，不利用房间方向/距离 | [App Store](https://apps.apple.com/us/app/i-am-daily-affirmations/id874656917), first-party listing, accessed 2026-08-15 |
| C-03 | Motivation – Daily Quotes / mobile | 内容分类、背景个性化、提醒与 widgets；缺口：自定义空间摆放、持久 3D | 选择需要激励的生活领域，配置提醒和 widget；公开资料未证实空间输入或精细编辑 | 以背景主题/小组件做个性化；具体视觉层级实测是 gap，不作为复用来源 | 2D 持续触达，没有方向/距离/房间语义 | [Motivation official](https://motivation.app/), first-party, accessed 2026-08-15 |
| C-04 | ThinkUp – Daily Affirmations / Android | 精选与自定义肯定语、用户自己的声音录制、音乐、闹钟；缺口：房间常驻文字与空间锚点 | 核心是选择/录音/播放，麦克风和媒体权限较重；与本项目 Speech-to-Text 路径不同 | 公开商店资料强调音频练习，具体 UI/视觉密度为 gap，不作为复用来源 | 音频时间维度明显，但仍是 2D/音频会话，不利用房间位置与方向 | [Google Play](https://play.google.com/store/apps/details?id=com.think.up), first-party listing, updated 2025-09-02, accessed 2026-08-15 |

**逐产品吸收 / 避免**

| # | 值得吸收（需求/机会层） | 要避免的不足 / anti-pattern |
|---:|---|---|
| C-01 | 持久位置、跨距离扫视、放置/重排/删除闭环、环境识别失败提示 | 不把所有文字强制吸附表面；不把其他平台的 surface widget 布局与视觉当 PICO 模板；不隐瞒 PICO Anchor 只能 Full Space 的差异 |
| C-02 | 预设库、意图分类/收藏（如后续证据支持）、低打扰持续触达 | 不用订阅墙/通知轰炸替代环境价值；不做无证据疗效承诺 |
| C-03 | 快速个性化、按需要选择内容主题 | 不让背景主题压过文字可读性；不以换肤冒充空间化 |
| C-04 | 自定义内容、语音参与和键盘/语音多路径 | 不把语音设为必需；不连续监听；不持久保存原始音频；不把“自己的声音更有效”宣传话术当研究事实 |

- **我们的差异化机会**：在 Stage Mixed 中把移动端“提醒我去看”改成与房间位置形成记忆线索的 3D 双面文字；Shared Space 只提供低打扰管理/样式/非锚定预览。是否能把真正 world-locked 内容跨空间带回 Shared 不是差异化承诺，只有目标 SDK + 真机证据成立后才可重新评估；语音保持短时创建入口而非持续监听。
- **样本与缺口**：达到 4 个样本；仅 1 个 XR 相邻平台，3 个为 2D/音频同任务产品。各产品真实上手路径、付费边界、留存、无障碍表现与当前版本截图未独立测试。
- **吸收边界声明**：仅吸收需求覆盖和机会，禁止复制任何竞品布局、状态顺序、组件组合、配色、材质或动效。

## 4. 领域模型

### 4.1 Domain workflow

1. **触发与选句**：用户感到需要环境提示 → 决定自写、语音转写或从预设中选句。
2. **成形**：校对文字 → 选择字体语气、颜色、大小、透明度 → 预览立体厚度与双面可读。
3. **空间放置**：选择视线可达且不阻碍真实任务的位置 → 必要时进入受解释的锚定空间状态 → 放置、确认、保存 UUID。
4. **Shared 管理**：退出 Stage 后回到 Shared Space → 仅管理内容、样式和非真实锚定预览；不显示或暗示 world-locked 房间文字。
5. **回访辨读**：用户显式进入 Stage Mixed → 加载/重定位 → 转头/抬眼快速读到目标句。
6. **维护**：Shared 可改内容/样式草稿；真实锚定对象的查看、移动、缩放、旋转、更新全部在 Stage Mixed，保存或取消后可退出。
7. **恢复**：重启/重入房间 → Shared 读取 Room 元数据 → 用户显式进入 Stage Mixed → 加载锚点 → 成功恢复或“未定位/需靠近环视/重新放置”。
8. **删除**：Shared 可发起删除意图；真实 anchor 删除与一致性确认在 Stage Mixed 完成，本地记录失败时显示可重试状态。

### 4.2 决策变量

| Variable | Meaning | Decision affected |
|---|---|---|
| phraseMeaning | 句子是否真能代表用户当下目标 | 自写/预设/是否保存 |
| roomContext | 房间、表面、光照、旁观者和真实任务 | 位置、隐私、对比度 |
| viewingDistance | 预计观看距离与视角 | 字号、挤出厚度、发光、换行 |
| attentionCost | 文字是否遮挡/过亮/持续运动 | 位置、透明度、浮动/呼吸、Reduce Motion |
| anchorCapabilityState | Shared/Full、权限、跟踪/定位、UUID 状态 | 能否创建/加载/移动/删除锚点 |
| inputAvailability | 键盘、SpeechRecognizer、眼手、控制器可用性 | 创建/编辑路径与回退 |
| textCountAndDensity | 同时可见段数、字符长度、互相遮挡 | 布局、LOD、纹理缓存、性能 |
| privateContentRisk | 内容敏感度、旁观者可见性、语音外放 | 是否显示、遮蔽、删除、语音入口 |

### 4.3 数据实体与时效

| Entity | Key fields | Timeliness / authority | Failure semantics |
|---|---|---|---|
| HealingText | localId, content, presetId?, createdAt, updatedAt | Room 本地；编辑后立即持久 | 文本为空/过长/字符缺失需可见校验 |
| TextStyle | fontFamily, colorToken, size, opacity, extrusionDepth, glow | Room；保存后稳定，编辑预览为临时草稿 | 字体缺失回退到兼容无衬线并告知 |
| AnchorLink | localId, anchorUUID, anchorName, lastKnownTransform, anchorState | UUID/实际位姿权威来自 Anchor API；Room 保存关联与 last-known 仅作恢复线索 | unlocated/missing/permissionDenied/spaceStateRequired/orphaned |
| PresentationState | billboard mode, localRoll, floatPhase, breathPhase, reduceMotion | 运行时；每帧行为不应写 Room 高频更新 | 跟踪丢失时冻结安全姿态、禁用增强动效 |
| InputDraft | source keyboard/speech/preset, partialText, confidence?, error | 会话级；语音结果需用户确认后才入库 | timeout/noNetwork/languageUnsupported/permissionDenied |
| PresetPhrase | id, content, locale, category?, enabled | 本地打包；版本更新时迁移 | 缺少 locale 时显示通用列表，不联网阻断 |
| PlacementSession | candidatePose, distance, mappingGuidance, saveProgress | Full Space/Stage 会话；退出前必须有完成/取消结果 | anchor create/load timeout、用户取消、跟踪不足 |

### 4.4 专业风险

- **空间状态误导**：最严重风险是把 Shared WindowContainer 说成可直接 Persistent Anchor，或暗示退出 Stage 后仍 world-locked 可见；必须以官方限制为准，把 Shared 限为管理/非锚定预览并显示受控 Stage/Full Space 转换。
- **医疗误导**：应用不能声称治疗焦虑、抑郁或其他疾病。
- **锚点一致性**：Room 行存在但 UUID 丢失、锚点删除但本地行残留、坐标重校准后实体未更新。
- **可读性与骚扰**：半透明+发光在明亮/复杂背景上失去对比；周边视野持续浮动造成干扰或不适。
- **碰撞与误操作**：无 collision/hit 证据时拖拽对象不可靠；多对象重叠会选错。
- **隐私**：语音可能远端处理；敏感句子在共享房间或截屏中泄露。
- **恢复失败**：用户回到错误房间、观察范围不足或锚点无法定位时，不能假装恢复成功。

### 4.5 用户心智模型

- 首选比喻是“在房间里摆放一段会轻轻呼吸的字”，而不是“打开一个励志 dashboard”。
- 位置属于“房间”，内容/样式属于“这段文字”；删除应同时处理两者但允许解释部分失败。
- Billboard 意味着“它总让我读得正”，而不是对象在世界里随意旋转；用户的旋转手势需要被解释成排版倾角/参考朝向。
- 语音意味着“说一句帮我转成字”，不是持续监听或录音日记。

### 4.6 成熟模式与 anti-pattern

- **可参考语义**：预设+自定义、编辑前预览、显式保存/取消、锚点恢复引导、键盘回退、每对象独立状态、Reduce Motion。
- **anti-pattern**：泛用 dashboard；为“空间感”堆浮窗；把所有句子跟头锁定；静默持续麦克风；锚点失败仍显示 last-known 位置为“已恢复”；用光晕/颜色单通道表达选中；删除无需确认；强制全屏沉浸。

## 5. Persona（招募假设，不是用户事实）

### Persona hypothesis 1：低打扰的日常回看者

| Dimension | Content |
|---|---|
| 证据身份 | 招募假设，来源 U-01 + 用户需求；无访谈样本 |
| 基本信息 | 年龄/职业/XR 经验未知，需覆盖新手与熟练用户 |
| 使用场景与频率 | 私人室内；偶尔创建，频繁短扫视（待验证） |
| 目标 / 动机 | 不打开管理应用也能看到自己挑选的话 |
| 痛点 | 手机提醒要主动查看；普通便签不随设备空间恢复 |
| 空间习惯 | 坐/站、缓慢转头；不在楼梯/过街/驾驶时使用 |
| 无障碍 | 视力、色觉、动作能力未知；招募时分层 |
| Key quote | 用户材料：“随时抬眼看到鼓励自己的话。”（stakeholder prompt，非访谈原话） |

### Persona hypothesis 2：集中配置的创作者

| Dimension | Content |
|---|---|
| 证据身份 | 招募假设，来源用户的自定义/样式/多文字要求 |
| 基本信息 | 愿意一次配置多段文字；实际数量、语言与字符长度未知 |
| 使用场景与频率 | 进入创建/放置会话，连续微调字体、颜色、大小、透明度与位置 |
| 目标 / 动机 | 让每句话在房间中有不同位置与语气，同时可随时维护 |
| 痛点 | 双手手势/语音服务失败、锚点空间状态切换、对象重叠误选 |
| 空间习惯 | 坐姿/站姿；需要控制器与键盘回退 |
| 无障碍 | Reduce Motion、文本缩放、非颜色焦点和稳定退出为默认合同候选 |
| Key quote | 用户材料：“输入自定义文字内容，放置在空间任意位置。”（stakeholder prompt，非访谈原话） |

## 6. Journey Map（证据约束的待测路径）

| Stage | Awareness / entry | First hands-on | Core use | Persistent placement / recovery | Exit / return |
|---|---|---|---|---|---|
| User goal | 查看管理状态或新建 | 快速得到一句可读预览 | 调整内容/样式 | 在真实房间位置查看、放置或恢复 | 安全回到 Shared 管理；日后再次进入 Stage 查看 |
| User behavior | 启动 Shared 管理 | 选预设、键盘或短语音 | Shared 非锚定预览；确认后进入 Stage | 在明确提示下于 Stage Mixed 查看/锚定/加载/移动/删除 | 保存/取消并退出；重启回访时再次显式进入 Stage |
| Touchpoint | Shared Space Volumetric 管理根 | 创建入口 | 非真实锚定 3D 预览 | Stage Mixed / Full Space 真实锚定 | Shared 只管理/样式/非锚定预览；不承诺 world-locked 显示 |
| Thoughts | “我的文字还在吗？” | “这句对不对？” | “它会不会挡住我？” | “为什么要进入另一个空间状态？” | “我改动保存了吗？” |
| Emotion | 😐 | 😀 / 😐 | 😀 | 😐 / 😞（潜在低点） | 😀 或 😞（恢复失败） |
| Pain point | 锚点加载延迟 | 语音误识别 | 手势误选、亮度/运动干扰 | 用户要求与平台限制冲突、空间切换打断 | orphan/missing anchor |
| Opportunity | 显示真实恢复状态 | 语音结果必须校对，键盘回退 | 单一主焦点、非颜色选中、Reduce Motion | 把切换原因、3m 引导、完成/取消讲清 | 一步“靠近并环视”恢复或重新放置 |

- **情绪低点**：真正查看锚定文字也要求进入 Full Space/Stage，与“Shared 中自然抬眼”预期冲突；另一个低点是回访重定位失败。
- **关键机会**：把空间模式与锚点状态从隐形技术细节变成诚实、可恢复的任务；明确 Shared 预览与 Stage 真实锚定的差别。

## 7. 关键发现

| # | Finding | Evidence | Confidence | Design implication |
|---:|---|---|---|---|
| F-01 | 产品愿望的独特价值是环境位置回看，但当前证据只支持在 Stage Mixed 中实现真正 world-locked 回看 | U-01, P-02/P-02A, C-02–C-04 | high | Shared 只管理/非锚定预览；Stage 入口须低摩擦但不可隐藏；避免 dashboard 化 |
| F-02 | 用户的 Shared 共存愿望与真实 Persistent Anchor 观看存在平台冲突，跨空间交接未验证 | 用户原始需求 + P-01/P-02/P-02A | high | Stage 6 比较：全真实观看在 Stage；Shared-only 非锚定预览；有 SDK+真机证据才允许跨空间 |
| F-03 | “疗愈”应降格为主观支持与自我鼓励，不可宣称医学疗效 | D-01/D-02 | high | 商店与 UI 文案非临床；没有“改善抑郁/焦虑”的指标 |
| F-04 | 语音是短时可选输入，不是持续能力 | P-07, C-04 | high | 按需权限、支持检测、拒权/无网/超时回键盘，不保存音频 |
| F-05 | 模拟器可以验证眼手/控制器逻辑，但无法关闭真实锚点和舒适验收 | P-06, S-02 | high | 后续证据分层：Web/模拟器/真机各自明确边界 |

## 8. 佩戴姿态与视野洞察

- **常见姿态**：用户指定空间任意位置但未给分布；当前限定坐姿/站姿与缓慢转头，禁止动态危险场景。
- **手臂活动范围**：缺少测量；设计优先远距 gaze+pinch，减少持续抬臂，手控/控制器回退待测试。
- **中心视野容量**：后续窗口尺寸须遵循技能官方摘要的核心 `65°×40°`、次要不超过 `85°×55°`；多段文字不能默认同时争夺中心焦点。
- **疲劳阈值**：无本项目用户数据；连续 10 分钟原型观察和更长真机会话均待实施验证。

## 9. 眼手交互可用性

- **gaze + pinch 命中率**：本项目未测量，明确 gap；不能填入虚构百分比。
- **低负担依据**：PICO 官方设计把 gaze+pinch/drag 作为远距路径，并强调 hover 不应抢注意（P-05）。
- **误触与反馈**：每段文字需 collision/hit 边界、轻量 hover 和选中双通道；对象重叠、凝视抖动和双手识别必须在模拟器/真机记录。
- **模拟器边界**：可模拟眼手、双手和控制器，但不能替代设备的真实 gaze 精度和物理 FOV（P-06）。

## 10. 时长基线（待验证数值不伪造）

| Decision Type | Duration Anchor | Source / status |
|---|---|---|
| Stage 内抬眼辨读目标句 | 未知；目标候选 ≤2s，属于 Stage 3 产品效率目标而非研究事实；Stage 进入/重定位耗时另计 | evidence gap U-02/P-02A；后续计时任务 |
| 预设快速创建 | 未知；目标候选 ≤30s（不含锚定环境重定位） | evidence gap；后续可用性测试 |
| 样式/位置微调 | 未知；按单段完成时长记录 P50/P90 | evidence gap；后续可用性测试 |
| 锚点恢复 | 平台给出观察/距离建议，但未给完成时长；必须在显式 Stage Mixed 中测 | P-04；真房间 Stage create→restart→Stage re-entry 测试 |

## 11. 晕动、疲劳与安全

- **高风险动效**：大面积突然位移、全景遮挡、持续闪烁；均禁止。文字只允许小幅局部正弦位移，并必须有 Reduce Motion / 静止分支。
- **High Motion 标记**：本项目不应设计 High Motion 场景；若实现出现相机运动或大范围内容平移，判为违反合同。
- **休息节奏**：本项目连续使用阈值无证据，不提供虚构分钟数；首轮 10 分钟观察仅用于发现问题，不代表安全上限。
- **真实环境**：不得在楼梯、道路、驾驶等动态危险场景使用；Shared Space 内容要控制遮挡。
- **验证边界**：舒适、疲劳、光学对比、物理命中、锚点重定位均保留 `not_performed`，直到设备验证。

## 12. Minimum Completeness Gate

| Check Item | Minimum Pass Condition | Evidence Anchor | Verdict |
|---|---|---|---|
| Five categories of evidence | market/user/domain/platform/safety 均有证据或明确 gap | §3 M/U/D/P/S entries | pass |
| Competitive benchmark | ≥3 products and four dimensions; migration gaps explicit | §3A C-01–C-04 | pass |
| Domain model | workflow, variables, entities/timeliness, risks, mental model, anti-patterns complete | §4 | pass |
| User evidence | Persona/Journey clearly labeled hypotheses, sources and gaps not invented | §5–§7 | pass |
| Quantitative and safety | missing durations/hit/comfort are explicit gaps with validation plans | §8–§11 | pass |

| Field | Value |
|---|---|
| minimumCompletenessGate | pass |

## 13. 交付与接收方

- **交付**：Research evidence + competitive benchmark + domain model active revision 3；rev 3 仅修正 provenance，研究事实与 rev 2 相同。
- **下游**：PM rev 3、Interaction rev 6、Spatial/Visual System patched revisions；下一门为精确引用 UXR rev 3 的 fresh-context Stage 12 rerun。
