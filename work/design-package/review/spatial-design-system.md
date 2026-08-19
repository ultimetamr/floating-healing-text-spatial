# Spatial / Visual Design System · 悬浮文字疗愈空间

> Roles: visual_designer + spatial_design_system_designer | Active revision: 2 | Workflow coverage: Stages 8, 10 and 11 + bounded DS-01–DS-04 author patch
>
> Provenance: Stage 11 generation consumed PM rev 3, UXR rev 2, Interaction rev 4, Visual Language rev 1, Spatial Composition rev 1 and Critique rev 3. Interaction rev 5 then integrated this system’s eye-hand/motion facts without changing component denominators. The bounded DS-01–DS-04 patch consumes UXR rev 3 (provenance-only), Interaction rev 6 and Composition rev 2; this explicit reconciliation supersedes stale rev-4-only provenance wording while preserving H-A facts.

## 1. Platform and styling contract

| Axis | Required implementation | Prohibited |
|---|---|---|
| Theme | PicoTheme at the app root; route colors/type/shape through PicoTheme roles and the tokens below | Material3 theme or Material3 components |
| Components | Prefer SpatialUI built-ins for Button, IconButton, ListItem, TextField, Slider, choice control, Dialog and SpatialPopup; retain their focus, hover and haptic behavior | Rebuilding standard controls without a documented missing capability |
| Window material | WC-MAIN keeps WindowContainer system material background enabled by default; root uses Material.Regular | Device-side custom blur pretending to be system glass |
| Component background | choose exactly one of none, customColor or system glass; never customColor+glass on the same component | stacked translucent color and glass |
| Stage material | no WindowContainer glass in Stage; use matte contrast backing or Vibrant where supported | claiming Stage glyphs/panels use glass |
| Custom hover | only TextObject3D and AnchorTruthBadge add project-specific hover; built-ins keep native hover | double hover, hover haptic, scale over 1.03 |
| Haptics | built-in pinch/select haptic once; drag boundary/change commit uses one light tick; destructive confirmation uses Dialog/system confirmation haptic | continuous drag vibration, gaze-only haptic, duplicate app+system haptic |

## 2. Approved visual reference

- Visual concept: VD-01 “一字一息 / Letter & Breath”.
- One primary sentence or decision at a time; controls recede.
- Shared: calm authoring volume with a visibly labeled non-anchored preview.
- Stage: room-anchored text entities plus a single selection AttachmentPanel only when needed.
- Rejected visual directions remain VD-02 dense quote constellation and VD-03 conventional editorial desk; see Visual Language Report rev 1.

## 3. Design tokens

### 3.1 Core tokens

| Token | Exact value | Semantics |
|---|---|---|
| ui.text.primary | #FFFFFF | primary UI text |
| ui.text.secondary | #DCE6EB | supporting UI text |
| ui.surface.matte | #182127 | Stage panel/contrast backing |
| ui.surface.scrim | #0C1216CC | modal isolation when glass is unavailable |
| ui.focus.stroke | #B0E0E6 | custom focus outline, never sole focus cue |
| ui.selected.stroke | #FFE4B5 | selected outline plus size/text cue |
| text.white | #FFFFFF | user text color option |
| text.warmGold | #FFE4B5 | user text color option |
| text.softPink | #FFB6C1 | user text color option |
| text.clearBlue | #B0E0E6 | user text color option |
| text.mint | #98FB98 | user text color option |
| text.defaultOpacity | 0.80 | initial glyph opacity; user adjustable |
| text.depth.target | 0.05–0.10m | visual extrusion target |
| window.radius | 32dp | PICO window/system surface radius |
| focus.scale | 1.03 | maximum custom hover scale |
| selected.scale | 1.04 | temporary selection scale, not persisted |
| hitTarget.min | 56×56dp | all UI actions |
| body.min | 12dp | absolute type floor |

### 3.2 Typography

| Role | family | size | line | weight |
|---|---|---:|---:|---|
| display | sans / PicoTheme display | 40sp | 48sp | Medium |
| title | sans / PicoTheme title | 28sp | 36sp | Medium |
| section | sans / PicoTheme title-small | 22sp | 28sp | Medium |
| input | sans / PicoTheme body-large | 20sp | 28sp | Medium |
| body | sans / PicoTheme body | 17sp | 24sp | Medium for CJK |
| caption | sans / PicoTheme label | 14sp | 20sp | Medium |
| micro | sans / PicoTheme label-small | 12sp | 16sp | Medium |
| userText.sans | sans | world-size 0.08–0.30m glyph height | 1.20× glyph height | Regular/Medium |
| userText.serif | serif | world-size 0.08–0.30m glyph height | 1.20× glyph height | Regular |
| userText.handwritten | handwriting asset with CJK coverage | world-size 0.08–0.30m glyph height | 1.25× glyph height | Regular |

Font fallback contract: sans → Noto Sans CJK/system sans; serif → Noto Serif CJK/system serif; handwritten → licensed bundled CJK-capable asset if present, otherwise show “手写体不可用，已使用无衬线体” and use sans. Missing-glyph tofu is prohibited.

### 3.3 Semantic colors: color + shape + label

| Key | color | shape | Human label | Description | aliases |
|---|---|---|---|---|---|
| located | #98FB98 | circle | 已定位 | Anchor located in current Stage session | located, 已定位, resolved |
| needsObservation | #FFE4B5 | diamond | 需要环视 | User should approach/observe room | needs_observation, 需要环视, scanning |
| missing | #FFB6C1 | triangle | 未找到 | Anchor missing/unlocated | missing, unlocated, 未找到 |
| pending | #B0E0E6 | dashed | 处理中 | create/update/delete/relocalize in progress | pending, loading, 处理中 |
| repair | #FFB6C1 | square | 需要修复 | Room/Anchor transaction partially failed | partial, conflicting, repair, 需要修复 |
| permission | #FFE4B5 | triangle | 需要授权 | Required permission denied/not granted | permission_denied, denied, 需要授权 |
| localOnly | #DCE6EB | dashed | 非锚定预览 | Shared bounded preview or local-only record | not_world_anchored, local_only, 非锚定预览 |
| error | #FF8FA3 | square | 操作未完成 | explicit source error | error, failed, 操作未完成 |

### 3.4 Materials

| Material | Description | treatment | glassStyle | opacity |
|---|---|---|---|---:|
| WindowRoot | Shared main volume background | glass | Regular | 1.00 |
| EditOverlay | focused Shared editing surface | glass | Thick | 1.00 |
| SharedCriticalDialog | DecisionDialog variants hosted by WC-MAIN in Shared only | glass | Thickest | 1.00 |
| StageCriticalDialog | customColor #182127 DecisionDialog companion layer owned by ST-ROOM; it replaces/suppresses AP-SELECTION while open | matte | none | 0.96 |
| SharedDialogFallback | customColor #182127 used only when Shared system glass is unavailable; replaces SharedCriticalDialog rather than stacking with it | matte | none | 0.96 |
| StagePanel | AP-SELECTION and RecoveryPanel | matte | none | 0.92 |
| GlyphTranslucent | user text tint + mild emission; not a glass material | matte/translucent renderer | none | user 0.40–1.00, default 0.80 |
| ContrastHalo | local silhouette/backing only when environment contrast fails | matte | none | 0.20–0.45 |

### 3.5 Scale

| Scale | Exact tiers | Usage |
|---|---|---|
| spacing | xs 4 / s 8 / m 16 / l 24 / xl 32dp | all padding/gap |
| radius | s 12 / m 20 / l 32dp | controls/overlay/window |
| icon | s 20 / m 28 / l 32dp | tintable vector icons |
| stroke | subtle 1 / focus 2 / selected 2dp | visible plus shape/text cue |
| elevationZ | base 0 / content 0.016m / preview 0.12m / overlay 0.18m | root-owned depth |

## 4. Environment and readability

- Shared WC-MAIN uses WindowRoot Material.Regular with PicoTheme/Vibrant-compatible single-color text. OV-EDIT uses Material.Thick; critical Dialog uses Thickest.
- Stage does not use glass. TextObject3D evaluates contrast against passthrough; when insufficient, add ContrastHalo behind the glyph silhouette and raise opacity without changing the saved user color.
- Vibrant application: Shared primary/secondary UI text uses the platform default app-level Vibrant behavior and PicoTheme roles; regions with a gradient/image do not exist. StagePanel uses a solid matte backing and terminates Vibrant propagation.
- Bright room: strengthen halo/stroke, cap emission to avoid blooming. Dark room: reduce emission/glow and avoid large saturated areas. Outdoor/dynamic unsafe use receives a safety notice; no dynamic walking/traffic scenario is supported.
- Body text remains ≥12dp; normal CJK body is 17sp Medium. One line is capped near 50 Chinese characters, then wraps or scrolls.
- Color never carries status alone; every status combines semantic color, shape and human-readable label.

## 5. Window shell

| Field | Content |
|---|---|
| Window | WC-MAIN |
| Form | Volumetric |
| Outer size | default 1.28×0.832×0.48m; min 0.96×0.624×0.36m; max 1.60×1.04×0.60m |
| Internal reference constraints | 1280×832dp default; 960×624dp compact; 1600×1040dp large |
| Root ownership | full fill, WindowRoot system glass, 32dp inset, clipping, responsive tier and region gaps |
| Regions | 72dp header; 440×672dp collection; 752×672dp preview; 24dp gaps |
| Attachment | at most one OV-EDIT SpatialPopup/overlay; no TabBar/Toolbar/Subwindow/Augment |
| Reflow | compact displays collection or preview, not both; overlay takes the content layer; no whole-scene scale |

## 6. Core component definitions

### 6.1 Component: PhraseCollection

| Field | Content |
|---|---|
| derivedFromTasks | T-01, T-04, T-05, T-10 |
| derivedFromData | HealingText[], AnchorLink[], DeleteJournal[] |
| Purpose | Select one phrase, create a phrase, or begin a true-anchor task |
| layoutRole | primary_explore |
| Priority | primary |
| runtimeRole | decisionList |

**Anatomy · Layout**

~~~text
┌ PhraseCollection ─────────────────────┐
│ title/count             NewAction     │
├───────────────────────────────────────┤
│ PhraseRow                              │
│ PhraseRow                              │
│ PhraseRow             internal scroll │
├───────────────────────────────────────┤
│ empty/error/repair action              │
└───────────────────────────────────────┘
~~~

Grid: rows 56dp header / flexible list / 64dp status-action; one column; gap 8dp; root region supplies outer placement.

**Anatomy · Sizing**

| Tier | Width×Height | Window fit |
|---|---|---|
| Regular | 440×672dp | WC-MAIN default R-COLLECTION |
| Compact | 896×480dp | min window single-region content |
| Large | 544×880dp | max window; max 8 visible rows |
| Constrained | 896×480dp internal scroll | hit/type remain fixed |

**Anatomy · Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | none | WindowRoot provides glass |
| radius | 0dp | region, not card |
| padding | 0/0/0/0 | root owns outer inset |
| gap | s 8dp | rows |
| stroke | 0dp | row owns selection |
| icon | m 28dp | New/status |
| primary text | section 22/28 Medium | title |
| secondary text | caption 14/20 Medium | count/state |
| hitTarget | ≥56×56dp | New and row actions |

**Render Elements**

| id | Visible label | Type | Binding | Role |
|---|---|---|---|---|
| collectionTitle | 我的文字 | Text | derived count | heading |
| collectionCount | “5 段” | Text | HealingText[].size | display-only |
| newAction | 新建文字 | SpatialUI Button | user.startDraft | primary action |
| rowList | none | SpatialUI Lazy list | HealingText[] | rows |
| emptyAction | 选择一句开始 | SpatialUI Button | preset/start | empty action |
| repairAction | 处理未完成操作 | SpatialUI Button | DeleteJournal.pending | semantic action |

**Data Bindings**

| Source | Target/property | Fallback | Kind |
|---|---|---|---|
| HealingText[] | rowList.items | empty state | display-only |
| HealingText[].size | collectionCount.text | “0 段” | display-only |
| DeleteJournal.pendingCount | repairAction.visible/label | hidden when zero | semantic |
| Room.loadState | collection loading/error | error with retry | semantic |

**Variants**: Empty (preset/New focus); Filled (rows); RepairPending (repair action pinned); Compact (single-region list); Large (up to 8 rows).

**States**

| State | Trigger | Visual params | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | rows fresh | transparent region | none | none | heading+count |
| focused | gaze on New/row | native SpatialUI hover | ≤1.03 built-in limit | 120ms ease-out | focus ring + spoken label |
| loading | Room loading | progress + “正在读取” | none | 200ms crossfade | progress semantics |
| empty | zero rows | preset/New copy | none | 200ms fade | direct New action |
| error | Room failure | error square + “读取失败” + retry | none | none | source text |
| overflow | >6 default/>8 large | internal scroll | none | system scroll | scroll semantics |
| disabled | in blocking Dialog | 55% opacity, noninteractive | none | none | modal focus trap |

Stacking precedence: blocking Dialog > error > loading > focused > default; disabled suppresses hover and haptic.

### 6.2 Component: PhraseRow

| Field | Content |
|---|---|
| derivedFromTasks | T-04, T-05, T-10 |
| derivedFromData | HealingText, TextStyle, AnchorLink, DeleteJournal |
| Purpose | Identify one exact phrase and choose preview/edit/Stage action |
| layoutRole | decision_item |
| Priority | secondary |
| runtimeRole | selectableRecord |

**Anatomy · Layout**

~~~text
┌ PhraseRow ─────────────────────────────┐
│ statusShape  exact phrase text         │
│             font · color · statusLabel │
│             Edit   ViewInRoom          │
└────────────────────────────────────────┘
~~~

Grid: 28dp status column + flexible text column + action column; two text rows; actions align end; 8dp gaps.

**Anatomy · Sizing**

| Tier | Width×Height | Window fit |
|---|---|---|
| Regular | 440×88dp | collection default |
| Compact | 896×80dp | actions remain inline or wrap to second line at ≥160dp reserved |
| Large | 544×88dp | full labels |
| Constrained | minimum height 80dp | phrase wraps to 2 lines then truncates only in list |

**Anatomy · Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | none | host glass |
| radius | m 20dp | focus/selected outline |
| padding | m 16dp each side | component-owned internal |
| gap | s 8dp | status/text/action |
| stroke | default 0; focus 2dp ui.focus; selected 2dp ui.selected | dual cue |
| icon | s 20dp | status/action |
| primary text | body 17/24 Medium | phrase |
| secondary text | caption 14/20 | style/status |
| hitTarget | actions ≥56×56dp; row ≥56dp | PICO floor |

**Render Elements**

| id | Label | Type | Binding | Role |
|---|---|---|---|---|
| stateShape | semantic shape | ShapeIcon | AnchorLink.state | semantic |
| phraseText | exact phrase | Text | HealingText.content | display-only |
| styleSummary | font · color | Text | TextStyle | display-only |
| stateLabel | 已定位/非锚定预览/... | Text | AnchorLink.state | semantic human label |
| editAction | 编辑 | IconButton+tooltip | user.edit | action |
| roomAction | 在房间中查看 | Button | user.requestTrueAnchorTask | action |

**Data Bindings**

| Source | Target/property | Fallback | Kind |
|---|---|---|---|
| HealingText.content | phraseText.text | “内容不可用” + repair | display-only |
| TextStyle.font/color | styleSummary | fallback font label | display-only |
| AnchorLink.state | stateShape/stateLabel | localOnly semantic | semantic |
| DeleteJournal.state | roomAction enabled/state label | “需要修复” | semantic |

**Variants**

| Variant | Trigger / context | Visible-element delta | Binding / default / fallback delta | Layout / sizing delta | Motion / accessibility / stacking |
|---|---|---|---|---|---|
| LocalOnly | Shared record has no current Stage locate result | dashed stateShape + “非锚定预览”; roomAction remains visible | AnchorRuntimeState is ignored in Shared; AnchorLink falls back to localOnly | regular row; no size change | no status motion; may stack with Selected but localOnly label remains |
| LocatedLastSession | Room stores a prior located timestamp but current Stage is closed/not resolved | dashed stateShape + “上次已定位”; optional timestamp | binds AnchorLink.lastLocatedAt only; never binds current located | regular row; timestamp may use second caption line | spoken “上次已定位”; cannot stack with current Located |
| NeedsObservation | current Stage return metadata requests observation | diamond + “需要环视”; roomAction label becomes “进入房间并环视” | binds recovery reason; fallback to Missing when no UUID | action column reserves full label width or wraps | no pulse; label+shape; RepairPending overrides |
| RepairPending | DeleteJournal or update journal incomplete | square + “需要修复”; edit/room actions replaced by repairAction | binds exact remaining Anchor/Room step; no success fallback | row height may grow to 104dp for repair copy, within scrolling list | no decorative motion; highest variant precedence except disabled Dialog |
| Selected | user selects unique row | selected leading bar + warm outline; preview is bound to this row | selection.localId is required; ambiguity clears selection | same bounds; action column remains available | 160ms crossfade; stacks with LocalOnly/LastSession/NeedsObservation but not Repair color |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | not focused | no outline | none | none | phrase + state label |
| focused | gaze/controller | 2dp clear-blue outline | 1.02 | 120ms ease-out | shape+spoken phrase |
| selected | pinch | warm outline + leading bar | 1.00 | 160ms crossfade | “已选择” label |
| pressed | pinch down | 90% brightness | 0.99 | 80ms | native haptic once |
| disabled | blocking op | 55% opacity | none | none | reason tooltip |
| error/repair | partial journal | square + repair label | none | none | repair action |
| overflow | long phrase | 2-line list preview | none | none | full phrase in edit/delete |

Stacking: repair overrides selected color but retains selection bar; disabled suppresses focused; pressed may stack only with focused.

### 6.3 Component: TextObject3D

| Field | Content |
|---|---|
| derivedFromTasks | T-03, T-07, T-08, T-09, T-12, T-14 |
| derivedFromData | HealingText, TextStyle, AnchorLink, AnchorRuntimeState, MotionPreference, RenderCache |
| Purpose | Render one readable front/back text object as bounded preview or true Stage entity |
| layoutRole | primary_hero |
| Priority | primary |
| runtimeRole | spatialTextSubject |

**Anatomy · Layout**

~~~text
AnchorRoot (Stage only)
 └ InitialRoll
    └ FloatOffset ±0.025m
       └ LookAt viewer
          ├ GlyphFront normal-order
          ├ GlyphBack normal-order, separately oriented
          ├ Extrusion/side faces 0.05–0.10m
          ├ ContrastHalo optional
          └ HitVolume
~~~

World grid: width 0.30–1.20m; glyph height 0.08–0.30m; depth 0.05–0.10m; origin at text visual center. Shared preview is proactively bounded within its tier-specific R-PREVIEW envelope, never relies on clipping, and cannot own AnchorRoot.

**Anatomy · Sizing**

| Tier | Size | Context |
|---|---|---|
| SharedPreview Compact | total envelope ≤0.60×0.28×0.12m; glyph bounds ≤0.52×0.20m; depth 0.05–0.08m | min window’s single R-PREVIEW region 0.896×0.480m; reserves ≥0.148m sides and 0.200m vertical for label/actions/gaps |
| SharedPreview Regular | total envelope ≤0.68×0.44×0.16m; glyph bounds ≤0.60×0.36m; depth 0.05–0.08m | default R-PREVIEW 0.752×0.672m; reserves 0.036m each side and 0.232m vertical for label/actions/gaps |
| SharedPreview Large | total envelope ≤0.88×0.60×0.20m; glyph bounds ≤0.80×0.52m; depth 0.05–0.10m | large R-PREVIEW 0.968×0.880m; reserves 0.044m each side and 0.280m vertical for label/actions/gaps |
| Stage Regular | width 0.45–0.90m; glyph height 0.10–0.22m; depth 0.05–0.08m | unbounded Stage world-space subject; no WC-MAIN tier denominator |
| Stage Range | width 0.30–1.20m; glyph height 0.08–0.30m; depth 0.05–0.10m | Stage placement/user size range only |
| Constrained fallback | texture-box depth 0.05m; same owning-tier envelope as Shared or Stage source | compile/runtime mesh fallback |

SharedPreview fit policy: the envelope includes the 0.02m halo and the bounded LookAt sweep. Shared preview yaw/pitch is clamped to ±12°/±8°; if the rotated bounds would exceed the tier envelope, orientation eases toward face-on before any size change. Text wraps at about 50 CJK characters per line; it may reduce only down to 0.08m glyph height. Beyond that, it uses explicit 56dp previous/next page controls so every character remains reviewable—no clipping, ellipsis or below-floor shrink. Stage sizing remains independent and is never inferred from R-PREVIEW.

**Anatomy · Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | none; optional ContrastHalo customColor #182127 at 0.20–0.45 | no Stage glass |
| radius | N/A; glyph geometry | not panel |
| padding | hit volume +0.04m around silhouette | selection accessibility |
| gap | front/back separated by depth | 0.05–0.10m |
| stroke | focus silhouette 0.006m max | custom domain subject |
| icon | N/A; glyph object | no icon |
| primary text | userText font/size | exact content |
| secondary text | Shared-only localOnly label outside object | never baked into Stage glyph |
| hitTarget | collider ≥ glyph bounds +0.04m | gaze/controller |

**Render Elements**

| id | Label | Type | Binding | Role |
|---|---|---|---|---|
| frontGlyph | exact phrase | glyph mesh/texture | HealingText.content | primary readable face |
| backGlyph | exact phrase | separate glyph mesh/texture | HealingText.content | rear readable face |
| sideDepth | none | extrusion/box sides | TextStyle.depth | depth cue |
| halo | none | matte silhouette | contrast result | accessibility |
| glow | none | mild emission | TextStyle.color/focus | decorative/focus |
| hitVolume | spoken phrase | collider | bounds | interaction |
| previewTruth | 非锚定预览 | label, Shared only | localOnly | truth |

**Data Bindings**

| Source | Target/property | Fallback | Kind |
|---|---|---|---|
| HealingText.content | frontGlyph/backGlyph texture/mesh | readable “内容不可用” error object | display-only |
| TextStyle.font | glyph resource | explicit family fallback label | display-only |
| TextStyle.color/opacity/size/depth | material/scale/extrusion | token defaults, depth 0.05m | display-only |
| AnchorRuntimeState.pose | AnchorRoot transform | not rendered as located | semantic |
| MotionPreference | FloatOffset/breath/selectedScale | static | semantic |
| RenderCache.key/state | glyph resource | cached texture-box | semantic |

**Variants**

| Variant | Trigger / context | Visible-element delta | Binding / default / fallback delta | Layout / sizing delta | Motion / accessibility / stacking |
|---|---|---|---|---|---|
| SharedPreview | WC-MAIN R-PREVIEW only | previewTruth “非锚定预览” always visible; no AnchorRoot or Stage panel | binds HealingText/TextStyle/RenderCache only; current AnchorRuntimeState is prohibited | uses exact SharedPreview Compact/Regular/Large envelopes above; bounded LookAt ±12°/±8° | normal/reduce/static allowed; cannot stack with StageLocated/StageCandidate |
| StageLocated | current Stage session resolves UUID/pose | previewTruth removed; front/back/depth/halo at real pose; hitVolume active | binds current AnchorRuntimeState.pose as authority; lastKnown has no fallback role | uses Stage Regular/Range only; no WC-MAIN clipping denominator | full focus/select/drag states; may stack with ReduceMotion or StaticPerformance |
| StageCandidate | create/re-place before commit | candidate guide/pose reticle + pending AnchorTruthBadge; saved entity remains absent or ghosted | binds candidatePose and draft; Anchor UUID absent until commit | uses Stage Range but clamps candidate glyph ≥0.08m | float/breath off until commit; explicit confirm; cannot stack with StageLocated |
| TextureBoxFallback | extrusion/font outline/material symbol unavailable or cache fallback | shallow 0.05m box, front texture, separately oriented readable back quad; side detail minimal | binds same exact content/style; fallback font label remains visible in edit surface | obeys the source Shared envelope or Stage range; never expands bounds | glow optional; keeps hitVolume and rear readability; may stack with Shared/Stage variants |
| ReduceMotion | persistent preference is reduce | geometry unchanged; focus outline/brightness retained | MotionPreference=reduce; default fallback is static displacement | no sizing delta; selected scale fixed 1.00 | FloatOffset=0, no micro-scale; opacity-only breath/state; stacks with Shared/Stage/fallback |
| StaticPerformanceFallback | frame policy reaches final decorative degradation step | non-selected glow/side detail removed; glyphs and semantic cues retained | binds frame policy; text/style/anchor bindings unchanged | same bounds and collider; texture LOD may replace mesh | no non-selected float/breath; focused outline retained; performance stacks below error/unlocated |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | visible | opacity user value, mild glow | committed size | float 8s + breath 6s | exact front/back |
| focused | gaze/controller | +8% brightness, 0.006m outline | 1.03 | 120ms; float amplitude may rise only to 0.03m normal mode | outline+spoken phrase |
| selected | pinch | warm outline, AP-SELECTION appears | 1.04 temporary | 180ms | selected label/panel |
| dragging | direct/controller move | guide + saved pose ghost | committed size | direct 1:1, no float | numeric/controller fallback |
| loading | mesh/cache | readable placeholder + pending badge | bounds reserved | no float | “正在准备文字” |
| unlocated | Anchor not resolved | entity hidden; recovery/status panel only | none | none | never show last-known |
| error | render/anchor failure | fallback glyph or source error | fallback bounds | none | retry/exit |
| performance | frame pressure | reduce glow/mesh/animation | text unchanged | static non-selected | state preserved |

Stacking: unlocated/error > selected > focused > default; performance may stack with default/focused but never removes outline/text; Reduce Motion overrides all displacement.

### 6.4 Component: EditOverlay

| Field | Content |
|---|---|
| derivedFromTasks | T-01, T-02, T-03, T-12 |
| derivedFromData | InputDraft, PresetPhrase[], TextStyle, MotionPreference |
| Purpose | Confirm exact content and style while showing an honest bounded preview |
| layoutRole | critical_primary |
| Priority | primary |
| runtimeRole | detailPanel |

**Anatomy · Layout**

~~~text
┌ EditOverlay ───────────────────────────┐
│ title                         close    │
│ exact text field      VoiceInputControl│
│ preset picker (20 local phrases)       │
│ font: Sans / Serif / Handwritten       │
│ color: 5 swatches                      │
│ size slider · opacity slider           │
│ “非锚定预览” + mini TextObject3D       │
│ Cancel                         Save    │
└────────────────────────────────────────┘
~~~

Grid: 56dp title / flexible internally scrolling form / 64dp action row; two columns only at Large, one column Default/Compact; 16dp row gaps.

**Anatomy · Sizing**

| Tier | Width×Height | Context |
|---|---|---|
| Regular | 520×640dp | default anchored overlay |
| Compact | up to 896×480dp content layer, internal scroll | min WC-MAIN |
| Large | 560×688dp | max WC-MAIN, no extra columns beyond form+preview |
| Constrained | full available content layer | keep actions pinned |

**Anatomy · Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | glass Material.Thick | WindowContainer only |
| radius | l 32dp | overlay |
| padding | l 24dp | overlay-owned |
| gap | m 16dp | form rows |
| stroke | focus 2dp via built-ins | native |
| icon | m 28dp | close/mic |
| primary text | title 28/36 | heading |
| secondary text | body 17/24; caption 14/20 | controls/truth |
| hitTarget | ≥56×56dp | all controls/swatches |

**Render Elements**

| id | Label | Type | Binding | Role |
|---|---|---|---|---|
| exactText | 输入想对自己说的话 | SpatialUI TextField | InputDraft.content | exact input |
| presetPicker | 20 条预设 | built-in selectable list | PresetPhrase[] | source |
| voiceControl | 语音输入 | VoiceInputControl | SpeechSession | optional input |
| fontChoice | 无衬线/衬线/手写 | built-in choice control | TextStyle.font | style |
| colorChoice | 白/暖黄/柔粉/清蓝/薄荷绿 | 5 accessible swatches+labels | TextStyle.color | style |
| sizeSlider | 文字大小 | SpatialUI Slider + value | TextStyle.size | style |
| opacitySlider | 透明度 | SpatialUI Slider + percent | TextStyle.opacity | style |
| previewLabel | 非锚定预览 | AnchorTruthBadge | localOnly | truth |
| cancelAction | 取消 | SpatialUI Button | cancel | action |
| saveAction | 保存 | SpatialUI Button | save | action |

**Data Bindings**

| Source | Target/property | Fallback | Kind |
|---|---|---|---|
| InputDraft.content/validity | exactText/save enabled | retain previous saved text | semantic |
| PresetPhrase[] | presetPicker.items | bundled exact 20 list required | display-only |
| TextStyle.* | controls + preview | default sans/white/size/0.80 | display-only |
| MotionPreference | preview motion | static if unavailable | semantic |
| FontAvailability | fontChoice availability/label | explicit sans fallback | semantic |

**Variants**

| Variant | Trigger / context | Visible-element delta | Binding / default / fallback delta | Layout / sizing delta | Motion / accessibility / stacking |
|---|---|---|---|---|---|
| Create | New action from empty/library | title “新建文字”; empty exactText; presetPicker and voiceControl visible | new InputDraft; defaults sans/white/default size/0.80; cancel discards only new draft | Regular/Large overlay; one-column default | opening 220ms; may stack with Compact/ReduceMotion/FontFallback |
| EditExisting | unique HealingText selected | title “编辑文字”; exact saved content/style prefilled | binds existing localId and last committed TextStyle; cancel restores snapshot | same regular bounds; full text scroll if long | dirty indicator; save commit required; RepairPending row blocks entry |
| PresetStart | user selects one of exact 20 presets | selected preset checkmark; exactText immediately populated and focused for review | InputDraft.source=preset and exact bundled copy; user edit breaks preset linkage only | preset list may collapse after selection to free preview height | 160ms selection crossfade; never auto-saves |
| Compact | WC-MAIN width 0.96–1.11m | preview moves below controls; actions pinned; optional section headers collapse | same bindings; no control removed | up to 896×480dp content layer; internal scroll; one column | no whole-panel scaling; controller/keyboard order preserved |
| FontFallback | chosen family unavailable or glyph coverage incomplete | unavailable option disabled; inline “已使用无衬线体”; preview uses fallback | FontAvailability binds fallback sans and original intent remains recoverable | error copy adds one caption line; stays inside scroll | no motion; spoken fallback; stacks with Create/Edit/Preset/Compact |
| ReduceMotion | preference reduce/static | motion selector reflects mode; preview keeps outline/status | binds MotionPreference; static is ultimate fallback | no layout delta | overlay appear is fade-only; preview FloatOffset=0; stacks with all functional variants |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | valid/idle | Thick glass, form | tier size | 220ms appear | focus order |
| editing | text/style changed | Save enabled, dirty indicator | none | live preview throttled | value labels |
| invalid | empty/invalid | inline error + disabled Save | none | none | spoken error |
| loading | glyph cache | preview placeholder only | form stable | no layout shift | “正在准备预览” |
| error | Room/render failure | source + retry; draft retained | none | none | keyboard remains |
| overflow | long content | field/preset internal scroll | none | none | full text review |
| disabled | Dialog open | 55% background | none | none | focus trapped in Dialog |

Stacking: Dialog disabled > error > invalid > editing > focused/default; focused uses native control state only.

### 6.5 Component: VoiceInputControl

| Field | Content |
|---|---|
| derivedFromTasks | T-01, T-02, T-13 |
| derivedFromData | SpeechSession, InputDraft |
| Purpose | Run a one-shot speech-to-text attempt and preserve a keyboard path |
| layoutRole | supporting_input |
| Priority | secondary |
| runtimeRole | transientInput |

**Anatomy · Layout**

~~~text
┌ VoiceInputControl ─────────────────────┐
│ mic/statusShape  human status label    │
│ partial/final text                     │
│ Cancel listening   Use keyboard        │
└────────────────────────────────────────┘
~~~

Grid: status row + result row + action row; one column; 8dp gaps.

**Anatomy · Sizing**

| Tier | Width×Height | Context |
|---|---|---|
| Regular | 472×120dp | EditOverlay |
| Compact | available width×136dp | wrapped actions |
| Large | 512×120dp | full labels |
| Constrained | available width×152dp | actions stack; never hidden |

**Anatomy · Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | none | EditOverlay glass |
| radius | m 20dp | focus grouping |
| padding | m 16dp | component |
| gap | s 8dp | rows |
| stroke | 1dp ui.text.secondary; focus native | group |
| icon | m 28dp | microphone |
| primary text | body 17/24 | status |
| secondary text | caption 14/20 | partial/error |
| hitTarget | ≥56×56dp | mic/cancel/keyboard |

**Render Elements**

| id | Label | Type | Binding | Role |
|---|---|---|---|---|
| micAction | 语音输入 | SpatialUI IconButton | user.requestSpeech | action |
| speechStatus | 正在聆听/请确认/... | Text+shape | SpeechSession.state | semantic |
| resultText | recognized draft | Text | SpeechSession.partial/final | display-only |
| cancelListening | 停止 | Button | cancel | action |
| keyboardFallback | 使用键盘 | Button | fallback | primary recovery |

**Data Bindings**

| Source | Target/property | Fallback | Kind |
|---|---|---|---|
| SpeechSession.support | mic enabled/status | “此设备暂不支持语音” | semantic |
| SpeechSession.permission | status/action | keyboard fallback | semantic |
| SpeechSession.partial/final | resultText | preserve prior InputDraft | display-only |
| SpeechSession.errorReason | status label/shape | generic “语音未完成” + keyboard | semantic |
| InputDraft.content | keyboard handoff | empty only if user began empty | display-only |

**Variants**

| Variant | Trigger / context | Visible-element delta | Binding / default / fallback delta | Layout / sizing delta | Motion / accessibility / stacking |
|---|---|---|---|---|---|
| Idle | no SpeechSession | micAction + “语音输入”; result/actions hidden | session absent; InputDraft unchanged | 472×56dp collapsed regular, expands within reserved 120dp | no motion/haptic until pinch; base precedence |
| CheckingSupport | after mic pinch before permission | dashed pending shape + “正在检查”; keyboardFallback remains | binds support query; timeout routes Fallback | regular reserved 120dp prevents shift | 200ms crossfade; progress semantics; overrides Idle |
| PermissionPrompt | support yes, RECORD_AUDIO not granted | triangle + “需要麦克风授权”; permission and keyboard actions | binds permission state; reject preserves InputDraft | regular; actions may wrap compact | no decorative motion; system permission owns haptic/focus |
| Listening | permission granted, recognizer active | mic/status + cancel; keyboard remains secondary | session one-shot active; raw audio path is not persisted | regular 120dp; compact 136dp | 1200ms opacity pulse, Reduce=static; overrides Checking |
| Partial | recognizer partial callback | partial result text marked “未确认” + cancel/keyboard | binds partial display-only; never writes HealingText | expands result row; compact may reach 152dp | 150ms text crossfade; Listening semantics remain underneath |
| FinalReview | recognizer final callback | final text + “请确认”; accept/edit and keyboard actions | recognizer destroyed; final binds InputDraft only after user action | 120–152dp depending wrapping | no pulse; explicit confirmation; overrides Partial/Listening |
| Fallback | denied/unsupported/network/timeout/language/generic error | source-specific shape+human label; “使用键盘” becomes primary | recognizer destroyed; preserves prior InputDraft/partial when policy allows | 136–152dp; actions stack constrained | no motion; one-step keyboard and spoken reason; highest except parent Dialog |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| idle | no session | mic action | 56dp control | none | label |
| checking | support query | pending dashed + “正在检查” | stable | 200ms crossfade | progress |
| permission | system request | “需要麦克风授权” | stable | none | keyboard visible |
| listening | recognizer active | calm pulse on icon only | stable | 1200ms opacity, no scale; Reduce=static | status spoken once |
| partial | partial text | partial result | stable | 150ms text crossfade | not auto-committed |
| final | final text | review label | stable | none | confirm required |
| denied/unsupported/network/timeout/language/error | failure reason | semantic shape+human label+keyboard | stable | none | one-step keyboard |
| destroyed | overlay exit | no listening indicator | collapsed | none | no background listening |

Stacking: failure/destroyed > final > partial > listening > checking > idle; no hover haptic.

### 6.6 Component: AnchorTruthBadge

| Field | Content |
|---|---|
| derivedFromTasks | T-04, T-05, T-06, T-10, T-11 |
| derivedFromData | AnchorLink.state, AnchorRuntimeState, DeleteJournal |
| Purpose | State whether content is preview, located, needs observation, missing, pending or repair-required |
| layoutRole | status |
| Priority | secondary |
| runtimeRole | statusBadge |

**Anatomy · Layout**

~~~text
┌ AnchorTruthBadge ──────────┐
│ shape  human-readable label│
└────────────────────────────┘
~~~

Grid: 20dp shape + label, one row, 8dp gap.

**Anatomy · Sizing**

| Tier | Width×Height | Context |
|---|---|---|
| Regular | 140–220×40dp | row/header/preview |
| Compact | 120–200×40dp | labels may wrap only at semantic boundary |
| Large | 140–240×40dp | full source text optional |
| Constrained | min 120×40dp | never color-only |

**Anatomy · Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | none | host |
| radius | s 12dp | focus group only |
| padding | s 8dp horizontal | internal |
| gap | s 8dp | shape-label |
| stroke | semantic 1dp | redundant cue |
| icon | s 20dp semantic shape | status |
| primary text | caption 14/20 Medium | human label |
| secondary text | N/A | compact status |
| hitTarget | 56×56dp only when actionable; otherwise noninteractive | role |

**Render Elements**

| id | Label | Type | Binding | Role |
|---|---|---|---|---|
| statusShape | circle/diamond/triangle/dashed/square | ShapeIcon | semantic map | semantic |
| statusLabel | human label | Text | semantic label | semantic |
| retryAction | 重试/处理 | optional Button | recovery availability | action |

**Data Bindings**

| Source | Target/property | Fallback | Kind |
|---|---|---|---|
| AnchorRuntimeState | shape/label | localOnly outside Stage | semantic |
| AnchorLink.lastLocatedAt | optional secondary copy | “尚未在本次会话定位” | display-only |
| DeleteJournal.state | repair semantic | no false success | semantic |
| currentSpaceState | located eligibility | Shared always localOnly/last-session copy | semantic |

**Variants**

| Variant | Trigger / context | Visible-element delta | Binding / default / fallback delta | Layout / sizing delta | Motion / accessibility / stacking |
|---|---|---|---|---|---|
| LocalOnly | Shared preview or record without current Stage result | dashed + “非锚定预览”; no retry unless Stage entry is available | currentSpaceState=Shared forces localOnly; ignores located runtime | 140–220×40dp | no animation; cannot stack with Located |
| Located | current Stage locate succeeds | circle + “已定位”; retry hidden | binds current AnchorRuntimeState=located only | 120–180×40dp | 160ms semantic crossfade; spoken label |
| NeedsObservation | locate requests room observation | diamond + “需要环视”; retryAction label “开始环视” | binds observation reason/range guidance | up to 220×40dp; action has separate 56dp target | no pulse; label+shape; Repair overrides |
| Missing | UUID cannot be resolved after allowed observation | triangle + “未找到”; re-place/exit action available | binds missing reason; no lastKnown pose fallback | up to 200×40dp | static; spoken next step |
| Pending | create/update/delete/relocalize active | dashed + “处理中”; actions disabled except cancel where safe | binds operation journal current step | stable width to avoid shift | 200ms crossfade; progress semantics |
| Repair | DeleteJournal/Room+Anchor conflict | square + “需要修复”; retryAction visible | binds exact remaining store step and source | up to 220×40dp | no decorative motion; highest semantic precedence |
| Permission | required Stage permission denied | triangle + “需要授权”; authorize/settings action visible | binds permission name/status; fallback exit | up to 220×40dp | static; system prompt owns focus/haptic |
| Error | non-recoverable/current operation error | square + “操作未完成”; retry/exit when valid | binds explicit error source; generic fallback label only when source missing | up to 220×40dp | static; error overrides focus color but focus outline remains |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | semantic display | color+shape+label | stable | 160ms crossfade | spoken label |
| focused | actionable retry | 2dp focus outline | 1.02 | 120ms | action label |
| pressed | retry | native press | 0.99 | 80ms | haptic once |
| disabled | no action/current space mismatch | label stays readable | stable | none | reason |
| aging | last-session only | dashed + “上次已定位” | stable | none | not current |
| error/repair | transaction issue | square + repair label | stable | none | source/next step |

Stacking: repair/error > aging > focused/default; Shared space forcibly prevents current located variant.

### 6.7 Component: StageAttachmentPanel

| Field | Content |
|---|---|
| derivedFromTasks | T-09, T-10, T-12, T-15 |
| derivedFromData | selected HealingText/Style/AnchorLink, MotionPreference, operation journal |
| Purpose | Provide discoverable controller-accessible fallback for the uniquely selected Stage text |
| layoutRole | critical_primary |
| Priority | primary |
| runtimeRole | spatialDetailPanel |

**Anatomy · Layout**

~~~text
selected TextObject3D ─ 0.12m ─┌ StageAttachmentPanel ┐
                               │ phrase/style status  │
                               │ size / roll controls │
                               │ Reduce Motion        │
                               │ Save  Cancel         │
                               │ Re-place  Delete     │
                               └──────────────────────┘
~~~

Spatial region: preferred right side local x=half text width+0.12m, z=+0.08m toward viewer; flip left or y=−0.22m below. Viewer-facing, never persists as anchor content.

**Anatomy · Sizing**

| Tier | Width×Height | Context |
|---|---|---|
| Regular | 360×420dp | selected text at normal distance |
| Compact | 320×420dp | narrower labels/actions wrap |
| Large | 400×440dp | long phrase summary |
| Constrained | 320×360dp + internal scroll | preserve controls ≥56dp |

**Anatomy · Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | customColor #182127 at 0.92 | Stage has no glass |
| radius | l 32dp | panel |
| padding | l 24dp | internal |
| gap | m 16dp | groups |
| stroke | 1dp #DCE6EB; focus 2dp | contrast |
| icon | m 28dp | actions |
| primary text | section 22/28 | phrase summary |
| secondary text | body 17/24, caption 14/20 | controls/status |
| hitTarget | ≥56×56dp | all controls |

**Render Elements**

| id | Label | Type | Binding | Role |
|---|---|---|---|---|
| selectedSummary | selected phrase | Text | HealingText.content | identity |
| anchorBadge | 已定位/... | AnchorTruthBadge | runtime state | truth |
| sizeControl | 文字大小 | SpatialUI Slider/buttons | TextStyle.size | fallback transform |
| rollControl | 参考角度 | Slider/buttons | TextStyle.initialRoll | fallback transform |
| motionToggle | 减少动态 | SpatialUI Switch/choice | MotionPreference | accessibility |
| saveAction | 保存 | SpatialUI Button | commit | action |
| cancelAction | 取消 | SpatialUI Button | rollback | action |
| replaceAction | 重新放置 | SpatialUI Button | re-place | consequential |
| deleteAction | 删除 | SpatialUI Button | Dialog | destructive |

**Data Bindings**

| Source | Target/property | Fallback | Kind |
|---|---|---|---|
| selection.localId/uuid | identity/actions | panel hidden if ambiguous | semantic |
| HealingText.content | selectedSummary | full phrase in confirmation | display-only |
| TextStyle.size/initialRoll | controls | last committed values | display-only |
| AnchorRuntimeState | badge/save enable | recovery panel | semantic |
| MotionPreference | toggle | static | semantic |
| operation journal | actions/exit guard | disable conflicting operations | semantic |

**Variants**

| Variant | Trigger / context | Visible-element delta | Binding / default / fallback delta | Layout / sizing delta | Motion / accessibility / stacking |
|---|---|---|---|---|---|
| Transform | unique located text selected for size/roll/move | sizeControl, rollControl, Save/Cancel primary; style summary condensed | binds draft transform + committed snapshot; fallback buttons mirror gestures | Regular 360×420dp; Constrained scroll keeps actions pinned | direct preview throttled; stacks with ControllerFocused/ReduceMotion |
| StyleSummary | selection opened for reading/status without dirty transform | phrase, font/color/opacity, anchorBadge; transform controls collapsed until Edit | read-only TextStyle/AnchorRuntimeState; fallback to Transform on action | Compact 320×360dp possible | no decorative panel motion after appear; spoken summary |
| ReduceMotion | user enables reduce/static in Stage | motionToggle elevated; explicit normal/reduce/static choices; transform controls stay | binds persistent MotionPreference; static fallback | same owning tier; no hidden controls | panel fade-only; TextObject displacement off; stacks with Transform/StyleSummary |
| RepairPending | journal incomplete or update failure | repair source/progress + Retry/Exit replace transform/save/delete | binds operation journal; committed snapshot remains authority | 360×360–420dp; fixed repair actions | no float on selected; highest panel variant before DecisionDialog |
| ControllerFocused | controller ray/keyboard focus enters panel | focus order/tooltip/value labels always visible; gesture hints de-emphasized | binds controller focus and numeric values; gesture support remains | same tier, ensure 56dp targets and wrapped labels | native built-in hover/haptic only; stacks with non-repair variants |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | unique located selection | matte panel | tier | 220ms fade+≤16dp | focus order |
| focused | gaze/controller control | native focus | control 1.00 | 120ms | labels |
| editing | dirty size/roll | Save enabled + dirty label | stable | direct preview, throttled | numeric values |
| saving | commit | pending badge/actions disabled | stable | no float on selected | progress |
| error | update failure | source+retry/cancel | stable | none | recovery |
| disabled | tracking lost/ambiguous | panel detaches; RecoveryPanel | none | 160ms fade | no ghost controls |
| overflow | constrained/long phrase | internal scroll, fixed actions | stable | none | full summary accessible |

Stacking: tracking/error > saving > editing > focused/default; opening DecisionDialog disables and visually recedes panel.

### 6.8 Component: DecisionDialog

| Field | Content |
|---|---|
| derivedFromTasks | T-05, T-10, T-11, T-15 |
| derivedFromData | current space/task, HealingText identity, DeleteJournal, operation journal, return token |
| Purpose | Confirm Stage entry, destructive delete, recovery repair or dirty Stage exit |
| layoutRole | critical_primary |
| Priority | primary |
| runtimeRole | confirmationDialog |

**Anatomy · Layout**

~~~text
┌ DecisionDialog ────────────────────────┐
│ semantic shape + human title           │
│ exact affected phrase / consequence    │
│ source/status/progress                 │
│ secondary action       primary action  │
└────────────────────────────────────────┘
~~~

Grid: 56dp title / flexible body / optional progress / 64dp action row; one column, actions end-aligned or stacked compact.

**Anatomy · Sizing**

| Tier | Width×Height | Context |
|---|---|---|
| Regular | 520×280–420dp | default Shared or Stage panel |
| Compact | available width−64dp × up to 420dp | stacked actions |
| Large | 560×280–420dp | full consequence text |
| Constrained | 320×420dp internal scroll | actions fixed |

**Anatomy · Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | Shared-hosted variants: SharedCriticalDialog system glass Material.Thickest; if unavailable, replace with SharedDialogFallback matte customColor #182127 at 0.96. Stage-hosted variants: StageCriticalDialog matte customColor #182127 at 0.96 owned by ST-ROOM companion dialog layer, which suppresses AP-SELECTION. Strategies are mutually exclusive and never stacked. | space-correct |
| radius | l 32dp | dialog |
| padding | xl 32dp | dialog |
| gap | m 16dp | sections |
| stroke | 1dp semantic; focus native | dual cue |
| icon | l 32dp semantic shape | status |
| primary text | title 28/36 | title |
| secondary text | body 17/24, caption 14/20 | consequence/source |
| hitTarget | ≥56×56dp | actions |

**Render Elements**

| id | Label | Type | Binding | Role |
|---|---|---|---|---|
| dialogTitle | 进入房间锚定模式/删除这段文字?/完成退出?/修复未完成操作 | Text | variant | heading |
| affectedText | exact phrase | Text | HealingText.content | identity |
| consequence | human consequence | Text | variant/state | decision info |
| progress | Anchor/Room progress | progress+labels | journal | trust |
| cancelAction | 取消/返回 | SpatialUI Button | cancel | safe action |
| confirmAction | 进入/删除/退出/重试 | SpatialUI Button | confirm | consequential action |

**Data Bindings**

| Source | Target/property | Fallback | Kind |
|---|---|---|---|
| dialogVariant | title/consequence/actions | close if invalid | semantic |
| HealingText.content | affectedText | localId suffix + “内容不可用” | display-only |
| DeleteJournal.anchorStep/roomStep | progress | explicit unknown source | semantic |
| operation journal | exit choices | wait/cancel operation | semantic |
| return token | post-close destination | Shared library | semantic |

**Variants**

| Variant | Trigger / owner | Visible-element delta | Binding / default / fallback delta | Layout / sizing / material delta | Motion / accessibility / stacking |
|---|---|---|---|---|---|
| StageConsent | Shared WC-MAIN before opening Stage | title “进入房间锚定模式”; consequence explains Full Space switch; actions Cancel/Enter | binds saved draft + return token; no permission request yet | 520×280dp; SharedCriticalDialog glass Thickest, exclusive SharedDialogFallback if glass unavailable | 220ms; modal focus; cannot stack with Stage variants |
| DeleteConfirm | ST-ROOM companion dialog layer after AP-SELECTION Delete | exact phrase, Anchor+Room consequence; Cancel/Delete | binds unique localId/UUID and preflight journal; missing identity disables Delete | 520×320dp target; StageCriticalDialog matte customColor; AP-SELECTION disabled/suppressed | no glass; destructive system haptic only on confirm |
| DeleteRepair | ST-ROOM when one delete step failed | repair square, completed/remaining step, Retry/Exit | binds durable DeleteJournal exact state; never resets completed step | 520×360–420dp; StageCriticalDialog matte; AP-SELECTION suppressed | no decorative motion; highest transaction precedence |
| StageExitGuard | ST-ROOM on back/exit with dirty or in-flight work | affected operation, Save/Wait/Cancel operation/Discard permitted draft/Exit choices | binds operation journal + return token; unsafe actions disabled | 520×320–420dp; StageCriticalDialog matte | pure fade under Reduce; system back maps safe option, not silent exit |
| RePlaceConfirm | ST-ROOM recovery before replacing pose/UUID | title “重新放置这段文字?” + old-link consequence; Cancel/Re-place | binds current UUID, candidate intent and rollback snapshot | 520×320dp; StageCriticalDialog matte; AP-SELECTION suppressed | explicit confirmation; no camera/object jump |
| PermissionExplanation | Shared WC-MAIN before/around system permission request | permission name, purpose, Continue/Use without voice or Cancel as applicable | binds exact permission/support source; fallback preserves draft/Shared state | 520×280–360dp; SharedCriticalDialog glass or exclusive matte SharedDialogFallback | system prompt owns haptic/focus; spoken purpose |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | decision ready | isolated dialog | tier | 220ms fade/scale 0.98→1 | modal focus |
| focused | action gaze/controller | native focus | no dialog scale | 120ms | action+consequence |
| confirming | user confirms | pending progress; actions disabled | stable | no repeated press | progress |
| partial | one delete step fails | repair square + exact remaining step | stable | none | retry/exit |
| error | operation fails | source+next action | stable | none | no false success |
| disabled | invalid identity/tracking | confirm disabled | stable | none | reason |
| overflow | long phrase/consequence | body scroll, actions pinned | stable | none | full text |

Stacking: partial/error > confirming > focused/default; system back maps to safe cancel unless an irreversible step already completed, then shows repair state.

## 7. Coverage reconciliation

### 7.1 Data entity → binding

| Entity / variable | Timeliness | Consuming component | Presentation / semantic method | Gap disposition |
|---|---|---|---|---|
| HealingText | persistent Room | PhraseRow, TextObject3D, EditOverlay, panels/dialog | exact human text | covered |
| TextStyle | persistent Room | PhraseRow, TextObject3D, EditOverlay, StageAttachmentPanel | five colors, three font intents, size/opacity/depth/roll | covered |
| AnchorLink | persistent metadata, not pose authority | PhraseRow, AnchorTruthBadge, TextObject3D | human semantic state; UUID never primary copy | covered |
| AnchorRuntimeState | current Stage session | TextObject3D, AnchorTruthBadge, panels | located/observation/missing/error | covered; Shared cannot show current located |
| InputDraft | session + optional saved draft | EditOverlay, VoiceInputControl | exact draft/dirty/validity | covered |
| SpeechSession | ephemeral | VoiceInputControl | support/permission/listening/partial/final/error | covered; audio never stored |
| DeleteJournal | durable until reconciled | PhraseCollection/Row, Badge, Dialog | Anchor step + Room step + pending repair | covered |
| MotionPreference | persistent local | TextObject3D, EditOverlay, StageAttachmentPanel | normal/reduce/static | covered |
| RenderCache | runtime/cache | TextObject3D | loading/fallback/performance | covered |
| ReturnToken | Stage session | DecisionDialog/state graph | exact Shared focus/scroll/draft return | intentionally not user-visible; controls return behavior |
| PresetPhrase | bundled immutable | EditOverlay | exact 20-item list | covered |
| Camera/view transform | live Stage | TextObject3D LookAt child | orientation only | intentionally not displayed; no user decision value |
| Audio waveform/raw audio | none | none | not collected/stored | intentionally absent for privacy |

### 7.2 Task decision output → interaction

| Task | Read/action | Component behavior | Gap |
|---|---|---|---|
| T-01 source | actionable | PhraseCollection New + EditOverlay preset + VoiceInputControl | covered |
| T-02 accept/edit/cancel | actionable | EditOverlay exactText/save/cancel; Voice final review | covered |
| T-03 style/save/default/cancel | actionable | EditOverlay font/color/size/opacity/preview | covered |
| T-04 select/manage/filter/action | actionable | PhraseCollection/PhraseRow | filtering intentionally omitted from MVP because only 5+ requirement; scrolling covers density |
| T-05 enter/cancel | actionable | PhraseRow roomAction + DecisionDialog StageConsent | covered |
| T-06 located/retry/re-place/exit | actionable | AnchorTruthBadge + DecisionDialog/Recovery variant | covered |
| T-07 pose confirm/cancel | actionable | TextObject3D candidate + StageAttachmentPanel/Dialog | covered |
| T-08 read/select/exit | actionable | TextObject3D hitVolume + system back | covered |
| T-09 transform/save/cancel | actionable | direct gestures + StageAttachmentPanel fallback | covered |
| T-10 confirm/delete/repair | actionable | StageAttachmentPanel delete + DecisionDialog | covered |
| T-11 retry/authorize/re-place/rollback/exit | actionable | DecisionDialog recovery + badge | covered |
| T-12 normal/reduce/static/readability | actionable | EditOverlay + StageAttachmentPanel | covered |
| T-13 allow/reject/cancel/fallback | actionable | VoiceInputControl | covered |
| T-14 performance policy/visible focus | system+action | TextObject3D performance state; single selection | covered |
| T-15 save/wait/cancel op/discard/exit | actionable | DecisionDialog StageExitGuard | covered |

### 7.3 Primary substates

| Primary component → subcomponent | Runtime substates | Render primitive | Binding |
|---|---|---|---|
| PhraseCollection → rowList | loading, empty, filled, overflow, Room error, disabled | progress/empty action/list/scroll/error retry/scrim | Room.loadState, HealingText[] |
| TextObject3D → glyph resource | loading, cached mesh, texture fallback, error | placeholder/mesh/box+quads/error object | RenderCache |
| TextObject3D → anchor | candidate, located, observation, missing, unlocated, tracking loss | candidate/visible/guidance/hidden/recovery | AnchorRuntimeState |
| TextObject3D → interaction | focused, selected, dragging, saving, performance fallback | outline/panel/guide/progress/LOD | selection, operation, frame policy |
| EditOverlay → exactText | empty, editing, invalid, long overflow, saved, Room error | field/error/scroll/dirty/progress | InputDraft, Room |
| EditOverlay → presetPicker | loading not applicable (bundled), 20 ready, selected, overflow | built-in list/selection/scroll | PresetPhrase[] |
| VoiceInputControl → session | idle, checking, permission, listening, partial, final, denied, unsupported, network, timeout, language, destroyed | status shape/text/actions/result | SpeechSession |
| StageAttachmentPanel → operation | default, dirty, saving, error, tracking-disabled, overflow | controls/dirty/progress/error/detach/scroll | selection/journal |
| DecisionDialog → transaction | ready, confirming, partial, error, disabled, overflow | consequence/progress/repair/actions/scroll | variant/journal |

## 8. Interaction contract

- All actionable elements support gaze focus + pinch and controller ray/select. Keyboard supports text editing and focus traversal.
- Built-in SpatialUI controls retain PicoTheme hover/pressed/haptic. App-specific hover applies only to TextObject3D and actionable AnchorTruthBadge: 2dp/0.006m outline, brightness +8%, scale ≤1.03, 120ms ease-out; no gaze haptic.
- Pinch selects exactly one TextObject3D. Drag updates a candidate transform but commits only on Save; two-hand pinch changes size; two-hand rotation changes InitialRoll. Controller/AttachmentPanel sliders/buttons provide equivalent size/roll/re-place actions.
- Long-press Delete opens DecisionDialog; controller/menu and panel Delete are equivalent. No direct destructive gesture commits.
- System back: closes popup; then cancels/guards dirty edit; in Stage always routes to StageExitGuard. Back is never trapped.
- SpeechRecognizer is one-shot: support check → optional permission → listen → partial/final review → destroy. Any denied/unsupported/network/timeout/language/error state retains the draft and exposes “使用键盘” in one step.
- TextObject LookAt affects only TextFacingRoot. AnchorRoot remains persisted. Back face is a separate normal-order readable copy, not mirrored front texture.

## 9. Motion contract

| Scenario | Type | Duration | Easing | Amplitude | Reduce Motion | Performance fallback |
|---|---|---:|---|---|---|---|
| Shared → Stage | fade + environment-preserving scale cue | 500ms | (0,0,0.2,1) | UI scale 0.98→1; no camera motion | pure fade 250ms | pure fade 250ms |
| Stage → Shared | fade | 350ms | (0.4,0,0.2,1) | none | 180ms fade | 180ms fade |
| overlay/dialog appear | fade + ≤16dp slide | 220ms | (0,0,0.2,1) | ≤16dp | 120ms fade | 120ms fade |
| create/delete object | fade | 500ms | (0.4,0,0.2,1) | no translation | 250ms fade | 200ms fade |
| gaze hover | outline + brightness + micro-scale | 120ms | (0,0,0.2,1) | ≤1.03 | outline/brightness only | outline only |
| selected | outline + micro-scale | 180ms | (0,0,0.2,1) | ≤1.04 | outline only | outline only |
| floating | sine y | 8000ms loop | sinusoidal | ±0.025m (5cm peak-to-peak) | 0m | freeze non-selected |
| breathing | emission/brightness | 6000ms loop | sinusoidal | ±4% brightness | static | static non-selected |
| direct drag | hand/controller tracked | real time | 1:1 filtered | user movement | still direct, no decorative motion | hide glow/LOD |
| recovery/status | crossfade | 220ms | (0.4,0,0.2,1) | none | 120ms | immediate text/shape |

No camera motion, forced FOV displacement, sustained flashing or app-driven whole-window movement. Default motion parameters remain low-confidence targets pending device comfort evidence.

## 10. Data trust and consistency

| State / operation | Authority | User-visible truth | Recovery |
|---|---|---|---|
| Shared preview | Room + style | always “非锚定预览”; never current located | explicit Stage entry |
| Located anchor | current Stage Anchor runtime | “已定位” only after current successful locate | observation/retry/re-place/exit |
| Last session | Room timestamp only | “上次已定位”, dashed; no world entity | enter Stage |
| Create/update | Anchor result then journaled Room link/metadata | pending until both committed | retry/rollback; no success toast early |
| Delete | durable DeleteJournal tracks Anchor step and Room step | deleted only when both complete; partial = “需要修复” | retry remaining step or stable exit with pending |
| Render cache | cache is visual optimization, not data authority | exact text/style comes from Room | invalidate/rebuild/fallback texture |
| Voice | SpeechSession ephemeral | partial never persisted; final requires user confirmation | keyboard with preserved draft; destroy recognizer |
| Tracking loss | runtime | entity hidden/non-located; last stable pose not shown as current | recovery or exit |
| Stage close | operation journal + return token | Shared result/status only; no true entity visibility promise | return to exact source or Shared repair state |

Trust precedence: current Anchor runtime > durable operation journal > Room metadata > last-session display. Cache and last-known transforms never override spatial truth.

## 11. Preset content

The local immutable library contains exactly 20 non-clinical supportive phrases:

1. 你已经做得很好了
2. 慢一点也没关系
3. 此刻，先照顾好自己
4. 允许今天只是今天
5. 你值得被温柔对待
6. 每一次呼吸都在重新开始
7. 不必完美，也可以前进
8. 把注意力带回当下
9. 你的感受很重要
10. 休息不是停滞
11. 小小一步，也是进步
12. 相信自己的节奏
13. 今天也请为自己留一点光
14. 你可以选择重新开始
15. 让心在这里安静一会儿
16. 你不需要独自承担一切
17. 做真实的自己就很好
18. 所有努力都算数
19. 给自己一点时间
20. 此刻的你，已经足够

## 12. Asset and performance handoff

| Asset / resource | Format/target | Budget / fallback | Owner |
|---|---|---|---|
| UI icons | tintable vector, 28dp grid | PicoTheme semantic tint; no bitmap color baking | built-in controls |
| Sans/serif/handwritten fonts | licensed Android font resources with CJK coverage | explicit family fallback; no tofu | EditOverlay/TextObject |
| Glyph cache | pre-generated/cached per content+font+style key | atlas target ≤2048² per script bucket; invalidate on exact style/content change | TextObject3D |
| Extruded glyph mesh | runtime/prebuilt glyph geometry | project target ≤10k triangles per text at near LOD, ≤50k for 5 texts; device profile pending | TextObject3D |
| Mid/far LOD | simplified extrusion/texture box | drop glow/side detail before text; text correctness unchanged | TextObject3D |
| Runtime fallback | front texture on shallow 0.05m box + separate back quad | 12-triangle box + 2 quads; normal-order rear copy | TextObject3D |
| Materials | tintable translucent glyph + matte halo/panel | unlit tint fallback if lighting/material API unavailable | Stage/preview |
| Motion | code-driven sine/timeline | no sprite asset; Reduce/static path | TextObject3D |
| Environment/audio | none | passthrough only; no background music or stored audio | app |

Compile/runtime fallback is mandatory: target SDK symbols for extrusion, material, LookAt or attachment are validated after version lock. If unavailable, use the documented texture-box, unlit tint, visual-child orientation and built-in panel alternatives; never remove readable content, rear readability, stable exit or anchor truth.

## 13. PICO numeric and implementation checklist

- WC-MAIN: exact volume default/min/max from Interaction §9; uniform resizing only.
- Clear FOV: default ≈40.2°×26.7°, max ≈49.1°×33.1° at 1.75m, inside 65°×40°.
- Window radius 32dp; hit target ≥56×56dp; body ≥12dp; normal CJK body 17sp Medium.
- SpatialUI + PicoTheme only for Compose UI; no Material3 import/component.
- WindowContainer system material background remains enabled by default; glass tiers use platform Material.Regular/Thick/Thickest, not custom blur.
- Component customColor and glass are mutually exclusive.
- Stage panels use matte solid backing; Stage text never relies on glass.
- Built-in hover/haptics are not duplicated; custom hover/haptics follow §1 and §8.

## 14. Component structure completeness

| Core component | base fields | layout | sizing | metrics | renderSpec | bindings | variants | states/precedence | Verdict |
|---|---|---|---|---|---|---|---|---|---|
| PhraseCollection | yes | yes | yes | yes | yes | yes | yes | yes | pass |
| PhraseRow | yes | yes | yes | yes | yes | yes | yes | yes | pass |
| TextObject3D | yes | yes | yes | yes | yes | yes | yes | yes | pass |
| EditOverlay | yes | yes | yes | yes | yes | yes | yes | yes | pass |
| VoiceInputControl | yes | yes | yes | yes | yes | yes | yes | yes | pass |
| AnchorTruthBadge | yes | yes | yes | yes | yes | yes | yes | yes | pass |
| StageAttachmentPanel | yes | yes | yes | yes | yes | yes | yes | yes | pass |
| DecisionDialog | yes | yes | yes | yes | yes | yes | yes | yes | pass |

## 15. Minimum Completeness Gate

| Check | Evidence | Verdict |
|---|---|---|
| Visual direction selected/rejections/approval | §2 + Visual Language Report rev 1 | pass |
| Precise tokens/type/color semantics/materials/scale | §§3–4 | pass |
| Window structure and sizing alignment | §5 + Composition rev 2 + Interaction rev 6; TextObject3D Shared envelopes itemized in §6.3 | pass |
| Every core component has eight independent sections | §6.1–§6.8 and §14 | pass |
| Data/task/substate reconciliation | §7.1–§7.3 | pass |
| Eye-hand/controller/voice/system-back | §8 | pass |
| Motion/Reduce Motion/performance fallback | §9, §12 | pass |
| Data trust/Room+Anchor deletion consistency | §10 | pass |
| Five colors, three fonts, 20 presets | §§3.1–3.2, §11 | pass |
| PICO SpatialUI/PicoTheme/no Material3/glass rules | §§1, 13 | pass |

| Field | Value |
|---|---|
| minimumCompletenessGate | pass for patched rev 2 author self-check; independent Stage 12 rerun required |

## 16. Delivery

- Active revision: 2.
- Recipients: Prototype/Frontend Engineer, QA, Design Lead.
- No generated image asset is included; all visual assets are built-ins, vector/icons, font resources or runtime/cached glyph resources.
