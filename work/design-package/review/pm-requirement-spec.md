# 空间应用需求规格 · 悬浮文字疗愈空间

> Role: `product_strategist` | Active source revision: 3 | Workflow coverage: Stage 1 `intent` + Stage 3 `quality_contract` + evidence-author patch `CR-EV-01-02`
>
> Source revisions: intent facts from this document rev 1; active research evidence/domain model from `uxr-research-report.md` rev 2; EV-01/EV-02 patch basis from `design-critique-report.md` rev 1。

## 0. 推理边界

- 本文冻结产品意图、范围、未知项与质量合同，不决定布局、组件或视觉方向。
- 用户明确要求优先于一般设计偏好；未给出的信息进入假设清单，不伪装成事实。
- 容器事实使用 PICO 设计术语：Shared Space、WindowContainer Planar / Volumetric、Stage Mixed / Progressive / Full。

## 1. 直接产出

本修订承载冻结意图与项目质量合同。质量合同不替代后续容器/概念推导；它定义不可妥协的用户结果、证据边界和验收方法。

## 2. 背景与问题

- **一句话需求描述**：让用户把自定义治愈短句或座右铭制作成柔和、轻微立体、会缓慢呼吸浮动的悬浮文字；Shared Space 的 Volumetric 只承担管理、样式预览与非真实锚定预览，用户须显式进入 Stage Mixed 才能查看、放置、更新、删除或重启后重定位真正固定在房间中的文字。
- **目标用户**：希望通过环境提示进行自我鼓励、情绪调节或习惯强化的 PICO 空间设备用户；具体年龄、无障碍需求和 XR 熟练度尚未给出。
- **使用场景**：在 Shared Space 中与其他应用共存地管理内容、编辑样式和查看非真实锚定预览；在用户显式打开的 Stage Mixed / Full Space 中查看、放置、编辑空间位姿、删除真正的锚定文字，并在重启后进入该模式执行重定位。退出 Stage 后锚定文字能否继续在 Shared Space 世界锁定可见，当前无证据且不承诺。
- **佩戴姿态**：创建与编辑时以坐姿或站姿为主；回看时可能坐、站或在安全范围内缓慢转头。移动中使用不是目标场景。
- **频率与时长**：预期“低频配置、 高频短暂扫视”；实际创建频率、单次编辑时长及连续佩戴时长未提供，列为待验证假设。
- **空间必要性初判**：核心候选价值来自“在 Stage Mixed 中让话语与房间位置形成稳定联结”，利用方向、距离、位置、轻量体积与时间变化；Shared Space 的 WindowContainer 只能证明管理与非锚定预览价值。该判断仍需 Stage 6 用三种诚实方案与 2D 反事实逐任务核验。

## 3. 关键时刻

- **屏幕难以实现的时刻**：用户显式进入真实锚定模式后，在 Stage Mixed 中转头或抬眼，便在自己选择的真实位置看到曾写下的话；退出 Stage 后不能承诺继续世界锁定可见。
- **沉浸谱系位置**：默认 `Shared Space + Volumetric WindowContainer` 仅承载管理、样式预览、预设选择和非真实锚定预览。真实 Persistent Anchor 的 load/view/place/update/delete/restart relocation 全部位于用户显式打开的 `Stage Mixed`，打开即进入 Full Space，完成/取消后可稳定退出；退出后的跨空间 handoff/visibility 未验证。
- **进入路径**：启动后从 Shared Space Volumetric 管理入口开始；用户明确确认后进入 Stage Mixed 完成锚定查看/创建/加载/更新/删除/重定位。退出回 Shared Space 后只回到管理与非真实锚定预览，不显示“已世界锁定”的房间文字。是否增加 Planar 界面或附件仍由 Stage 9 独立推导。

## 4. 产品研究基线

| 维度 | Stage 1 已知内容 | 来源 / 状态 |
|---|---|---|
| 同类功能基线 | 用户明确要求自定义、预设文案、样式调整、多文字、锚定与语音输入 | 用户原始需求；竞品证据待 Stage 2 |
| 决策时长基线 | 未知：需分别研究“抬眼辨读”“创建一句”“微调位置/样式”的时长锚点 | Stage 2 evidence gap |
| 安全与舒适基线 | 用户要求极缓慢浮动；视野占用、亮度、字体可读性、重复聚焦和长时间佩戴限制尚需证据 | Stage 2 evidence gap |

**Stage 2 research landing**

- 市场基线：移动端产品把“内容库 + 个性化 + 持续触达”作为基线；visionOS 26 相邻产品证明跨会话房间持久内容具有空间价值，但它不证明 PICO 实现兼容。
- 平台硬冲突：`uxr-research-report.md` P-02 证明当前 PICO Spatial SDK Spatial Anchor 只在 Full Space / Stage 中可用。Shared Space WindowContainer 不能被宣称可直接创建/加载 anchor。
- 当前受证据约束的产品空间模式：**默认 Volumetric Shared Space 管理/样式/非真实锚定预览 + 用户显式打开次级 Stage Mixed 的真实 Persistent Anchor 查看与操作**。跨空间 handoff/visibility 标为 `unverified`；没有目标 SDK 与真机证据前不得启用或承诺。
- 安全边界：自我肯定研究只支持有限、群体层面的非临床结果；应用不能承诺治疗焦虑、抑郁或其他疾病。

## 5. 意图定义（冻结项 · revision 1）

- **应用名称**：悬浮文字疗愈空间
- **包名**：`com.spatialapps.floatinghealingtext`
- **领域 / 子领域**：个人健康与情绪支持 / 非临床自我鼓励和环境化正向提示。
- **风险级别**：中低。内容创建本身风险低；但“疗愈”不能暗示医学治疗，空间放置、持续运动、真实环境遮挡、语音与房间锚点带来舒适、隐私和空间安全风险。
- **默认空间**：`Shared Space`，用户明确指定。
- **应用类型**：`Volumetric 管理/样式/非锚定预览 + Stage Mixed 真实空间锚定`；Volumetric WindowContainer 不承载或冒充世界锁定内容，真实锚定文字只在 Stage Mixed 中查看与操作。
- **核心场景**：Shared Space 中创建句子、选用 20 条预设、选择字体/颜色/大小/透明度和查看非锚定预览；Stage Mixed 中在房间放置、凝视、选择、拖拽、缩放、旋转、查看、删除真正锚定的文字并在重启后重定位；Stage 模式同时观看至少 5 段文字。
- **核心任务**：Shared Space 输入/挑选并预览一句话；显式进入 Stage Mixed 后决定真实空间位置、查看并持久保存；重启回访时再次进入 Stage Mixed 完成重定位和快速辨读；返回 Shared Space 管理多段文字元数据。
- **关键决策**：写什么；放在哪里；以何种字体/颜色/尺度/透明度展示；是否保存修改；是否删除；锚点失败时如何恢复。
- **数据**：文本内容、预设文案、字体/颜色/大小/透明度、锚点标识、相对位姿、初始朝向、创建/更新时间与恢复状态；用户要求使用 Room 持久化文字数据。
- **AI**：未要求生成式 AI；不作为本范围能力。
- **传感器 / 输入**：头部/相机视角用于 Billboard，凝视/手势用于交互，语音输入使用 Android SpeechRecognizer，键盘作为文本输入与语音失败回退。
- **权限**：麦克风（按需、仅语音输入时请求）；空间感知/锚点相关能力与权限须按目标 SDK/设备实际公开 API 确认；可能涉及手势/眼动能力，但精确权限与模拟器支持仍待平台验证。
- **协作**：这里的 Shared Space 指系统层多应用共存，不等同多人共享同一组锚点。用户没有要求多用户同步或云端共享，当前不纳入范围。
- **持续性目标**：每段真实锚定文字各自关联 Persistent Spatial Anchor；应用重启后，用户进入 Stage Mixed 才加载/重定位/查看。跨房间、设备重置、锚点失效与 Stage→Shared 可见性交接均未证实；后者不列为交付承诺。
- **性能目标**：同时至少 5 段文字保持 60fps；这是验收目标，不是在设计阶段已验证的事实。

## 6. 假设清单

| # | 假设 / 未知项 | Confidence | Impact | Validation Plan |
|---:|---|---|---|---|
| A-01 | 主要在私人室内房间使用，旁观者看到文本的社交压力较低 | medium | 决定默认隐私、可见性和语音输入提示 | 访谈 5–8 名目标用户，覆盖卧室/书房/办公室，并测试旁观者场景 |
| A-02 | “治愈”定位为非医疗情绪支持，不提供诊断、疗效承诺或危机干预 | high | 避免医学误导与不当安全承诺 | 产品/法务确认商店文案；所有可见文案审查禁用临床疗效表述 |
| A-03 | 每位用户常驻文字量在 5–20 段，单段以 2–30 个中文字符为主 | low | 影响纹理缓存、列表管理、锚点数量和阅读宽度 | 原型测试真实输入分布；记录 P50/P90 字符数与常驻数量 |
| A-04 | 20 条预设文案可以本地内置，不需要联网更新 | medium | 影响离线可用性与数据模型 | 与产品确认；离线冷启动验收预设可用 |
| A-05 | 文字从视觉正面始终正向阅读；用户所说“背面也有文字（镜像），确保从后面看也是正的）”需要双面几何/材质而非简单镜像字形 | low | 若语义理解错误会导致背面文字反读或渲染成本上升 | 在视觉原型和真机上分别从前后观察，要求两侧均能按正常字序阅读 |
| A-06 | 双指缩放/旋转在目标 PICO 输入能力上可用；否则需要单手捏合拖拽 + 编辑面板控件回退 | low | 影响核心手势可实现性与无障碍 | 查询目标 SDK 公共能力，模拟器/设备手势测试；保留控制器与面板回退 |
| A-07 | 眼动凝视可作为 hover 来源；设备/权限不支持时可由头向或控制器射线焦点等价回退 | low | 影响亮度微增与浮动增强的触发方式 | 运行时能力探测；记录 gaze/head/controller 三种路径的交互一致性 |
| A-08 | Android SpeechRecognizer 在目标系统镜像可用；网络依赖、语言包和隐私提示需按设备状态处理 | low | 影响快速创建成功率与离线行为 | 模拟器与设备分别测试普通话、拒权、无网、超时；键盘始终可用 |
| A-09 | **Research update**：PICO 当前公开 SDK 支持空间锚点创建/加载/删除，但限定 Full Space / Stage；目标项目具体锁定版本、真机行为和 Stage→Shared 可见性交接仍未知 | high for documented Stage limitation; low for target runtime/cross-space behavior | 否定“WindowContainer 内 anchor”与未经证明的跨空间交接，并要求显式空间状态切换 | Shared 仅管理/非锚定预览；真实操作全部 Stage Mixed；设备执行 Stage create→kill→restart→Stage re-entry→relocate，另测但不预设 Stage→Shared handoff |
| A-10 | Room 保存的是内容/样式/锚点引用与恢复状态，空间位姿的最终权威来源由 Anchor API 决定 | medium | 防止数据库位姿与真实锚点漂移冲突 | 架构评审定义单一权威与冲突规则；模拟锚点缺失/过期/重建 |
| A-11 | 默认透明度 80%、5cm 浮动幅度/8s 周期、6s 呼吸周期可能适合舒适观看，但无设备证据 | low | 影响可读性、眩光与周边视觉干扰 | Reduce Motion A/B；亮/暗房 10 分钟观察仅用于发现问题，不得解释为舒适证明或安全上限 |
| A-12 | 3D 厚度 5–10cm 是视觉目标范围，不代表所有字号下固定采用相同物理厚度 | medium | 固定厚度可能在小字号过重、大字号过薄 | 以字高比例 + 5–10cm 上限推导，真机多距离比较立体感与可读性 |
| A-13 | 用户没有要求多人或跨设备共享锚点 | high | 防止把 Shared Space 错解为协作同步 | 需求确认；若新增多人同步，作为独立范围变更 |
| A-14 | 数据默认仅存本机，不上云；语音音频不由应用持久保存 | medium | 影响隐私声明与数据删除能力 | 隐私评审与网络抓包；提供“删除全部本地文字/锚点”能力 |
| A-15 | “调整初始朝向”与 Billboard 的关系是保存绕局部视线轴的排版倾角/放置参考，而非允许文字长期背离用户 | medium | 避免旋转手势看似无效 | 交互原型验证旋转自由度，并以可见指示解释其效果 |

## 7. 质量合同

### 7.1 Required user outcomes

| ID | Acceptance-testable outcome |
|---|---|
| O-01 | 用户可用键盘输入自定义文字，或从 20 条本地预设中选用一句；语音可用时也能短时转写，且结果必须可校对。 |
| O-02 | 每段文字都可独立设置三类字体语气、五种指定颜色、大小和透明度，默认透明度为 80%。 |
| O-03 | 文字具有可辨识但不压迫的 3D 厚度/柔和光影，两侧均按正常字序可读；从不同观察角度保持面向用户。 |
| O-04 | 用户可创建、选择、编辑、拖拽、缩放、调整排版参考角度和删除多段文字；不可逆删除有明确确认或可撤销边界。 |
| O-05 | Shared Space 与其他应用共存时只提供管理、样式和非真实锚定预览；不得显示或声称其中的文字仍受真实房间 anchor 驱动。 |
| O-06 | 真实锚点 load/view/place/update/delete/restart relocation 全由用户显式进入 Stage Mixed 完成；无法定位时显示真实状态与恢复/重放路径。Stage→Shared 世界锁定可见性未验证且不承诺。 |
| O-07 | 文字以 5cm/8s 浮动、6s 呼吸、500ms 创建/删除、选中微放大为初始设计目标，同时提供 Reduce Motion/静止分支。 |
| O-08 | 至少 5 段文字同时存在时仍达到 60fps；纹理/网格复用与缓存策略不得破坏字体、双面或状态一致性。 |
| O-09 | 用户在亮/暗与复杂真实背景中仍可辨读；焦点、选中、锚点异常不能只靠颜色表达。 |
| O-10 | 数据默认本地持久；删除时清理 Room 关联与 anchor，语音原始音频不由应用持久保存。 |

### 7.2 Success / efficiency criteria

> 下列时长是项目可用性目标，不是 Stage 2 研究事实，需以后续测试验证。

| ID | Criterion | Measurement |
|---|---|---|
| E-01 | 预设句从创建入口到可放置状态 ≤20s（P75） | 8–12 人原型计时，排除首次权限教学 |
| E-02 | 10–20 中文字符的键盘自定义句到可放置状态 ≤45s（P75） | 同上 |
| E-03 | 回访用户进入 Stage Mixed 并完成重定位后，自然转头找到并读出指定句 ≤2s（P75） | 真机 Stage 房间任务；进入/重定位耗时另计，不以 Shared 管理列表替代 |
| E-04 | 单段样式编辑并保存 ≤30s（P75）；取消后原数据不变 | 模拟器/真机任务 |
| E-05 | anchor 成功时同设备同房间重启后，在 Stage Mixed 内恢复 5/5；失败时 100% 显示“未定位/靠近并环视/重新放置”之一 | 真机 Stage create→kill→restart→explicit Stage entry→relocate；另测 Stage→Shared 不可见/未验证边界 |
| E-06 | 5 段以上文字的目标场景持续 60fps，无 crash/ANR | 设备帧率与日志；模拟器只预检 |
| E-07 | 语音拒权、无网、服务不可用、语言不支持时，≤1 步切换键盘且草稿不丢 | 状态覆盖测试 |

### 7.3 Risks and must-not-fail

1. **R-ANCHOR-SPACE（blocking）**：不得声称 Shared Space 的 Volumetric WindowContainer 内可直接使用 Spatial Anchor，也不得声称退出 Stage 后真正锚定文字继续在 Shared Space 世界锁定可见。Shared 只做管理/样式/非锚定预览；真实 load/view/place/update/delete/restart relocation 全在显式 Stage Mixed。跨空间 handoff/visibility 只有目标 SDK + 真机证据通过后才可作为变更候选。
2. **R-ANCHOR-TRUTH**：Room `lastKnownTransform` 不能在锚点未定位时伪装为已恢复；Anchor UUID/事件是空间权威，Room 是内容/样式/关联元数据权威。
3. **R-SAFETY**：禁止相机自动移动、突然大范围遮挡、持续闪烁和动态危险情境使用；连续位移有 Reduce Motion。
4. **R-CLINICAL**：不得宣称治疗、诊断或替代专业帮助；“疗愈”只表示主观支持与鼓励。
5. **R-PRIVACY**：麦克风按需申请；说明 SpeechRecognizer 可能远端处理；不连续监听、不保存原音频；删除覆盖内容与 anchor 关联。
6. **R-READABILITY**：半透明、光晕和复杂 passthrough 不能牺牲辨读；关键状态使用颜色+形状/文字双通道。
7. **R-DELETE**：删除有清晰确认或可撤销策略；部分失败进入可恢复的一致性状态。

### 7.4 Preference for default visible primary windows

- 默认仅 **1 个主 Volumetric WindowContainer** 在 Shared Space 中可见，只承担管理、样式和非锚定预览。
- 次级 Stage Mixed 不默认打开；用户需查看、放置、移动、删除或重启恢复真实 Persistent Anchor 时显式打开。任务完成/取消可稳定退出回 Shared 管理态，但真实锚定文字不会被承诺继续可见。
- 是否在 Volumetric 内嵌 2D 管理区或增加 Planar 辅助 WindowContainer，仍须 Stage 9 的容器/附件矩阵证明；不默认 Toolbar/TabBar/Subwindow。

### 7.5 Domain-specialized component orientation

核心组件必须围绕领域任务：句子草稿与校对、3D 双面文字对象、样式/可读性预览、Anchor 会话与恢复、多对象选择/防重叠、隐私/语音状态。每个核心组件后续必须具备独立 8 段结构、任务/数据来源、fallback 和真实业务 substates，不能退化成通用卡片 dashboard。

### 7.6 Real-time data trust orientation

- 项目没有网络实时业务数据，但有高时效运行状态：anchor tracking、space state、input support、speech partial/final/error、save/delete progress。
- `located`、`unlocated`、`permission_denied`、`space_state_required`、`orphaned`、`error` 不得合并为一个“失败”。
- 异常必须给出来源（Anchor/Room/Speech/Tracking）和下一步；机器枚举转换为人可理解的中文标签。

### 7.7 PICO platform and spatial-design hard constraints

- Shared Space 使用 Planar/Volumetric；打开 Stage 即进入 Full Space。Shared Volumetric 只管理/样式/非锚定预览；真实 anchor 查看与操作只在 Stage Mixed。不伪造 WindowContainer anchor 或 Stage→Shared 世界锁定交接。
- 2D 管理 UI 后续实现必须使用 SpatialUI + PicoTheme，不用 Material/Material3。
- 所有交互目标支持 gaze+pinch，并提供 controller fallback、system back、text scaling、Reduce Motion、stable exit。
- 窗口尺寸经过 content type → scene tier → official baseline → clear FOV → readable/clickable floor → default/min/max/reflow；不得直接套固定 1280×720 或 1600×900。
- Planar 若存在：320×180–2700×1800dp、depth 640dp、hit ≥56×56dp、body ≥12dp；Volumetric 声明体积单位与统一缩放。
- 附件必须比较 None 与 InlineControl；不默认 Toolbar，不把 TabBar 当工具区。
- 模拟器截图不能声称锚点持久、物理 FOV、舒适或真机 60fps 已验证。

### 7.8 Originality requirement

- 差异化锚定 `uxr-research-report.md` §3A：吸收移动端的预设/个性化/持续触达与 XR 的空间持久存在；避免通知轰炸、2D widget 平移、表面矩形模板、无证据疗效、语音强制与持续监听。
- Stage 6 必须比较至少三种诚实方案：① 所有真实锚定文字的 view/place/update/delete/restart relocation 均在 Stage Mixed（牺牲同时 Shared 共存）；② Shared Space 只保留非真实房间锚定预览（牺牲 Shared 中任意位置持久锚定）；③ 仅当目标 SDK + 真机证明 Stage→Shared handoff/visibility 时才允许跨空间世界锁定。三者须在信息模型、路径、主交互、风险和工程成本上比较，不能把方案③预选为事实。
- 禁止复用 Apple widget 或任何竞品布局、状态顺序、组件组合、材质、配色或动效；禁止通用 dashboard IA。

### 7.9 Design / readability / downstream acceptance plan

1. 六个核心文档分别通过 Minimum Completeness Gate；17 个 stage receipt 实时、顺序、修订一致。
2. Stages 4/7/12/14/15/17 由隔离 reviewer 重建证据；缺独立调用即 `review_blocked`。
3. Web preview 只验证设计事实/状态/绑定/响应式/Reduce Motion 逻辑，标 `web_design_validation_only`。
4. 模拟器验证状态机、基础眼手/控制器输入、创建/编辑/删除和视觉截图；不得据此关闭真机 anchor/comfort/performance。
5. 真机验收覆盖 1–3m 阅读、亮/暗房、5+ 文字 60fps、语音失败矩阵、Stage Mixed 进入/退出、Stage 内 anchor create/load/view/update/delete、杀进程/重启/再次显式进入 Stage/重定位、坐标重校准和 partial-delete 恢复；Stage→Shared handoff/visibility 单列 `unverified`，通过前不进入承诺。
6. 下游只有在 `designStatus=ready_for_design_delivery` 且主线程 acceptance 允许时才可消费设计包。

## 8. 需求追踪

| Req ID | Mandatory requirement | Downstream semantic node(s) | Validation method |
|---|---|---|---|
| FR-01 | 应用名/包名固定 | product metadata | manifest/package inspection |
| FR-02 | 默认 Shared Space 管理/样式/非锚定预览，与其他应用共存；不显示真实锚定文字 | shared management architecture | state/container review + runtime evidence that anchor world content is not claimed/used |
| FR-03 | Volumetric 3D 非锚定预览；真正世界锚定 3D 文字在 Stage Mixed | preview volume + Stage text entities | separate Shared/Stage screenshot + geometry/depth inspection |
| FR-04 | 键盘自定义文字 | phrase draft/input | create task + Room record |
| FR-05 | 20 条本地预设 | preset library | enumerate exactly 20 + offline test |
| FR-06 | 无衬线/衬线/手写字体 | style selector/font fallback | three variants + missing-font test |
| FR-07 | 白/暖黄/柔粉/清蓝/薄荷绿指定 hex | color tokens/style | token inspection + visual sample |
| FR-08 | 大小可调 | scale style + gesture/fallback | min/default/max manipulation |
| FR-09 | 透明度可调、默认 80% | opacity style | default/assert + adjustment |
| FR-10 | 5–10cm 目标厚度与柔和立体光影 | extrusion/material | bbox/mesh/material + device view |
| FR-11 | 前后均按正常字序可读 | double-sided geometry/material | front/back observation |
| FR-12 | Billboard 始终正对用户 | billboard behavior | camera orbit logical test + device check |
| FR-13 | 5cm/8s 缓慢浮动 | float motion | animation params + Reduce Motion |
| FR-14 | 6s 轻微呼吸 | breath motion | params/visual + Reduce Motion |
| FR-15 | 创建/删除淡入淡出 500ms | lifecycle motion | timing inspection |
| FR-16 | 选中微放大+增亮 | selected state | gaze/pinch + non-color cue |
| FR-17 | 多文字、不同位置 | collection + per-object transform | create 5+ and inspect independence |
| FR-18 | 每段 Persistent Anchor 的查看/放置/更新/删除/重启恢复全部在 Stage Mixed | Stage anchor session/link/recovery | device Stage create→kill→restart→Stage re-entry→relocate；Stage→Shared handoff separately unverified |
| FR-19 | 捏合 + 语音/键盘创建 | create interaction/input modes | gesture/voice/keyboard branches |
| FR-20 | 点击选择、显示编辑菜单；锚定对象选择在 Stage Mixed | selected state/edit controls | separate Shared preview and Stage anchored transition tests |
| FR-21 | 锚定文字拖拽移动/更新只在 Stage Mixed | Stage manipulation/anchor update | Stage drag + save/cancel + anchor update |
| FR-22 | 双手缩放 | scale gesture/fallback | emulator dual-hand + device/controller fallback |
| FR-23 | 双手旋转参考角度 | rotate-reference gesture | visible effect + persistence |
| FR-24 | 长按删除；真实 anchor 删除只在 Stage Mixed | delete affordance + Stage anchor deletion | long-press/accessible alternative + confirmation + partial-failure recovery |
| FR-25 | 凝视增亮、浮动增强 | focused state | hover; no distraction + Reduce Motion |
| FR-26 | 语音快速输入 | SpeechRecognizer session | permission/network/language/support matrix + destroy lifecycle |
| FR-27 | Room 保存内容/样式/anchor 关联 | local repository/entities | schema/migration/CRUD/orphan consistency |
| FR-28 | 预生成/缓存纹理 | text render cache | cache hit/memory + invalidation |
| FR-29 | 创建/编辑/删除闭环 | task/state graph | happy/exception/back/return |
| FR-30 | ≥5 段仍 60fps | performance budget | device frame measurement |
| FR-31 | 动效自然且可关闭 | motion/accessibility | 10-min device observation + Reduce Motion |
| FR-32 | 锚点/语音/权限失败可恢复 | exception states | forced-error matrix + source/next-step |

## 9. Minimum Completeness Gate

| Check Item | Evidence Anchor | Current Verdict |
|---|---|---|
| 背景、用户、场景、姿态、频率/时长、空间必要性均有事实或显式假设 | §2–§6 | pass |
| 每个未知项都有 confidence / impact / validation plan；A-09 由研究更新 | §6 A-01–A-15 | pass |
| 九项质量合同完整、可验收、来源可追踪 | §7.1–§7.9 | pass |
| 每项强制需求进入追踪表且有验证方法 | §8 FR-01–FR-32 | pass |

| Field | Value |
|---|---|
| minimumCompletenessGate | pass |

## 10. 交付与接收方

- **当前交付**：Intent definition + Quality contract active revision 3。
- **下一接收方**：Stage 4 `evidence_integrity_reviewer`；隔离重审必须精确引用 PM rev 3 与 UXR rev 2，旧 PM rev 2 + UXR rev 1 review 不得复用。
