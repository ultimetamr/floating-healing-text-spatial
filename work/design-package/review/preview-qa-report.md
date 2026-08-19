# Preview / QA Test Report · 悬浮文字疗愈空间

> Active artifact revision: 6 (third fresh-context Stage 14 review over Preview rev 3; generation rev 5 and historical findings preserved) | Roles: `prototype_frontend_engineer` → `prototype_qa_reviewer` | Workflow: Stage 14 pass; Stage 15 allowed only after receipt closure
>
> Scope: `web_design_validation_only`. Sources: unchanged Interaction rev 7; Visual System rev 4; Spatial Composition rev 3; Spatial Design System rev 2; Visual Language rev 1; Design Critique rev 6 Stage 12 pass; Design Critique rev 8 PQ-03R/PQ-04R finding origin (Critique rev 7 preserved historically). This record does not claim PICO runtime, real 3D, persistent anchors, physical comfort, device performance, or Web/PICO parity.

## 1. Direct Description of Outputs

The generation side declares the exact design-fact denominator and mapping; the active independent actuals and verdict are in §14. Historical reviews remain in §§8/12 and are not approval evidence for Preview rev 3.

## 2. Test Scope and Verdict

- Object under test: `work/design-package/preview/preview.html` rev 3
- Validation scope: `web_design_validation_only`
- Device validation: `not_performed`
- Overall verdict: **pass for Web preview implementation fidelity** under invocation `prototype-qa-review-20260816-03`; PQ-03R/PQ-04R are independently closed in §14. This is not device or PICO runtime validation.

### 2.0 Reviewer Invocation Evidence

| Review Gate | reviewerRole | invocationId | contextPolicy | reviewed artifact revision | Independently rebuilt evidence | Verdict |
|---|---|---|---|---|---|---|
| Preview implementation | prototype_qa_reviewer | prototype-qa-review-20260815-01 | fresh_context | `preview.html` rev 1 + QA generation record rev 1 + Interaction 7 + Visual System 4 + Composition 3 + Spatial Design System 2 + Visual Language 1 + Critique 6 | yes — independently re-read source facts and HTML, served on localhost, exercised controls in the in-app browser, rebuilt every denominator and recorded itemized actuals in §8.1 | block / patch required |
| Preview implementation rerun | prototype_qa_reviewer |  | unavailable | `preview.html` rev 2 + QA generation record rev 3 + exact unchanged design sources + Critique 7 finding origin | no | block until fresh Stage 14 rerun |
| Preview implementation second rerun | prototype_qa_reviewer | `prototype-qa-review-20260816-02` | fresh_context | `preview.html` rev 2 + QA generation record rev 3 + exact unchanged design sources + Critique 7 finding origin | yes — independently rebuilt and replayed exact denominators; actuals in §12 | block / PQ-03R and PQ-04R |
| Preview implementation third rerun | prototype_qa_reviewer | `prototype-qa-review-20260816-03` | fresh_context | Preview rev 3 SHA-256 `c8ee3f4a2167aff882d56af1b12872c0931d6ecf931c72815baa4a203247115f` + QA generation rev 5 SHA-256 `53d9310aeafe52b6a45038143309ed231dfc99f07dcfa9b9e61ef7d5f39b3d26` + unchanged design sources + Critique 8 finding origin | yes — independently served on port 8793 and replayed all current denominators; actuals §14 | pass |

### 2.1 Input Readiness Table

| Input Fact | Source Section / Version | Completeness Assertion | Verdict |
|---|---|---|---|
| Design-system review | Critique rev 6, Stage 12 third rerun | `design_system_review=pass`, invocation `design-coherence-review-20260815-03` | pass |
| States and transitions | Interaction rev 7 §§10–11 | 16 states include entry/exit/exception/return; 19 transitions include triggers and confirmations | pass |
| Core component 8-section structure | Visual System rev 4 §§6.1–6.8, §14 | 8/8 components × 8/8 fixed sections independently approved | pass |
| renderSpec.elements[] | Visual System rev 4 §§6.1–6.8 | 52 stable element IDs with label/type/binding/role | pass |
| dataBindings[] | Visual System rev 4 §§6.1–6.8 | 39 bindings include target, fallback and display/semantic kind | pass |
| variants / component-specific states | Visual System rev 4 §§6.1–6.8 | 48 variants, 57 state rows and 8 precedence combinations are explicit | pass |
| Responsive tiers / Reduce Motion | Interaction rev 7 §§9.1–9.3, §13; Visual System rev 4 §§5,9 | max/default/min map to Large/Compact/Constrained; Reduce Motion facts explicit | pass |
| tokens / colorSemantics / materials | Visual System rev 4 §§3–4 | five text colors, type roles, mutually exclusive Shared glass/Stage matte strategies | pass |

### 2.2 Preview Coverage Manifest

#### 2.2.1 State / transition denominator

| Type | ID | Source Fact Anchor | Trigger event / entry | Target / visible result | Confirm | Verdict |
|---|---|---|---|---|---|---|
| state | S-01 SharedEmpty | Interaction 7 §10.1 | first launch/no rows | empty collection, presets/New | N/A | included |
| state | S-02 SharedLibrary | Interaction 7 §10.1 | launch/return/save | rows + selected non-anchor preview | N/A | included |
| state | S-03 SharedDraft | Interaction 7 §10.1 | New/edit | exact-text overlay | N/A | included |
| state | S-04 VoiceListening | Interaction 7 §10.1 | voice support+permission | listening/cancel | permission | included |
| state | S-05 VoiceReview | Interaction 7 §10.1 | final recognition | final text review | user accept | included |
| state | S-06 SharedStyle | Interaction 7 §10.1 | confirmed text/edit | style controls + non-anchor preview | save | included |
| state | S-07 StageConsent | Interaction 7 §10.1 | true-anchor intent | explicit mode-switch dialog | yes | included |
| state | S-08 StageLocating | Interaction 7 §10.1 | Stage opens | locate progress/status | N/A | included |
| state | S-09 StagePlacing | Interaction 7 §10.1 | new/re-place | candidate pose guide | pose save | included |
| state | S-10 StageViewing | Interaction 7 §10.1 | locate/place succeeds | 5+ text entities | N/A | included |
| state | S-11 StageSelected | Interaction 7 §10.1 | select entity | selected text + panel | transform save | included |
| state | S-12 StageDeleteConfirm | Interaction 7 §10.1 | delete intent | identity + two-store consequences | yes | included |
| state | S-13 StageRecovery | Interaction 7 §10.1 | Stage failure | retry/re-place/repair/exit | conditional | included |
| state | S-14 SharedDataIssue | Interaction 7 §10.1 | Room/journal issue | error source + repair action | policy | included |
| state | S-15 VoiceFallback | Interaction 7 §10.1 | speech failure | reason + one-step keyboard | no | included |
| state | S-16 StageExitGuard | Interaction 7 §10.1 | back/exit/interrupt | dirty/in-flight decision | conditional | included |
| transition | TR-01 | Interaction 7 §10.2 | startDraft | S-01/S-02→S-03 | no | included |
| transition | TR-02 | Interaction 7 §10.2 | requestSpeech | S-03→S-04 | system permission | included |
| transition | TR-03 | Interaction 7 §10.2 | finalReceived | S-04→S-05 | no | included |
| transition | TR-04 | Interaction 7 §10.2 | speech.failed | S-04/S-05→S-15 | no | included |
| transition | TR-05 | Interaction 7 §10.2 | useKeyboard | S-15→S-03 | no | included |
| transition | TR-06 | Interaction 7 §10.2 | confirmExactText | S-03/S-05→S-06 | yes | included |
| transition | TR-07 | Interaction 7 §10.2 | saveLocalDraft | S-06→S-02 | yes | included |
| transition | TR-08 | Interaction 7 §10.2 | requestTrueAnchorTask | S-02/S-06→S-07 | no | included |
| transition | TR-09 | Interaction 7 §10.2 | confirmEnterStage | S-07→S-08 | yes | included |
| transition | TR-10 | Interaction 7 §10.2 | sessionResolved | S-08→S-09/S-10 | no | included |
| transition | TR-11 | Interaction 7 §10.2 | confirmPose | S-09→S-10 | yes | included |
| transition | TR-12 | Interaction 7 §10.2 | selectEntity | S-10→S-11 | no | included |
| transition | TR-13 | Interaction 7 §10.2 | saveOrCancelTransform | S-11→S-10 | save only | included |
| transition | TR-14 | Interaction 7 §10.2 | requestDelete | S-11→S-12 | no | included |
| transition | TR-15 | Interaction 7 §10.2 | confirmDelete | S-12→S-10 | yes | included |
| transition | TR-16 | Interaction 7 §10.2 | runtime.failure | any Stage→S-13 | no | included |
| transition | TR-17 | Interaction 7 §10.2 | retry/re-place/repair | S-13→S-08/S-09/S-12 | conditional | included |
| transition | TR-18 | Interaction 7 §10.2 | exit/back | any Stage→S-16 | dirty/in-flight | included |
| transition | TR-19 | Interaction 7 §10.2 | exitGuard.resolved | S-16→S-02/S-06/S-14 | yes | included |

#### 2.2.2 `renderSpec.elements[]` denominator

| Component | element id | Source | Label | bind | Hide/show | Verdict |
|---|---|---|---|---|---|---|
| PhraseCollection | collectionTitle | VS4 §6.1 | 我的文字 | derived count | always | included |
| PhraseCollection | collectionCount | VS4 §6.1 | N 段 | HealingText[].size | always | included |
| PhraseCollection | newAction | VS4 §6.1 | 新建文字 | startDraft | always | included |
| PhraseCollection | rowList | VS4 §6.1 | rows | HealingText[] | hidden empty | included |
| PhraseCollection | emptyAction | VS4 §6.1 | 选择一句开始 | preset/start | zero rows | included |
| PhraseCollection | repairAction | VS4 §6.1 | 处理未完成操作 | DeleteJournal.pending | pending only | included |
| PhraseRow | stateShape | VS4 §6.2 | semantic shape | AnchorLink.state | always | included |
| PhraseRow | phraseText | VS4 §6.2 | exact phrase | HealingText.content | always | included |
| PhraseRow | styleSummary | VS4 §6.2 | font · color | TextStyle | always | included |
| PhraseRow | stateLabel | VS4 §6.2 | human anchor status | AnchorLink.state | always | included |
| PhraseRow | editAction | VS4 §6.2 | 编辑 | user.edit | repair replaces | included |
| PhraseRow | roomAction | VS4 §6.2 | 在房间中查看 | trueAnchor intent | repair replaces | included |
| TextObject3D | frontGlyph | VS4 §6.3 | exact phrase | HealingText.content | visible when renderable | included |
| TextObject3D | backGlyph | VS4 §6.3 | rear readable phrase | HealingText.content | visible in audit rear view | included |
| TextObject3D | sideDepth | VS4 §6.3 | depth cue | TextStyle.depth | renderable only | included |
| TextObject3D | halo | VS4 §6.3 | contrast halo | contrast result | conditional | included |
| TextObject3D | glow | VS4 §6.3 | mild glow | color/focus | fallback may hide | included |
| TextObject3D | hitVolume | VS4 §6.3 | spoken phrase target | bounds | Stage/audit interaction | included |
| TextObject3D | previewTruth | VS4 §6.3 | 非锚定预览 | localOnly | Shared only | included |
| EditOverlay | exactText | VS4 §6.4 | 输入想对自己说的话 | InputDraft.content | always overlay | included |
| EditOverlay | presetPicker | VS4 §6.4 | 20 条预设 | PresetPhrase[] | create/preset | included |
| EditOverlay | voiceControl | VS4 §6.4 | 语音输入 | SpeechSession | create/edit | included |
| EditOverlay | fontChoice | VS4 §6.4 | 三种字体 | TextStyle.font | always | included |
| EditOverlay | colorChoice | VS4 §6.4 | 五种颜色 | TextStyle.color | always | included |
| EditOverlay | sizeSlider | VS4 §6.4 | 文字大小 | TextStyle.size | always | included |
| EditOverlay | opacitySlider | VS4 §6.4 | 透明度 | TextStyle.opacity | always | included |
| EditOverlay | previewLabel | VS4 §6.4 | 非锚定预览 | localOnly | always overlay | included |
| EditOverlay | cancelAction | VS4 §6.4 | 取消 | cancel | always | included |
| EditOverlay | saveAction | VS4 §6.4 | 保存 | save | disabled invalid | included |
| VoiceInputControl | micAction | VS4 §6.5 | 语音输入 | requestSpeech | idle | included |
| VoiceInputControl | speechStatus | VS4 §6.5 | human speech status | SpeechSession.state | session | included |
| VoiceInputControl | resultText | VS4 §6.5 | recognized draft | partial/final | partial/final | included |
| VoiceInputControl | cancelListening | VS4 §6.5 | 停止 | cancel | listening | included |
| VoiceInputControl | keyboardFallback | VS4 §6.5 | 使用键盘 | fallback | always recovery | included |
| AnchorTruthBadge | statusShape | VS4 §6.6 | semantic shape | semantic map | always | included |
| AnchorTruthBadge | statusLabel | VS4 §6.6 | human label | semantic label | always | included |
| AnchorTruthBadge | retryAction | VS4 §6.6 | 重试/处理 | recovery | actionable only | included |
| StageAttachmentPanel | selectedSummary | VS4 §6.7 | selected phrase | HealingText.content | unique selection | included |
| StageAttachmentPanel | anchorBadge | VS4 §6.7 | anchor truth | runtime state | unique selection | included |
| StageAttachmentPanel | sizeControl | VS4 §6.7 | 文字大小 | TextStyle.size | Transform | included |
| StageAttachmentPanel | rollControl | VS4 §6.7 | 参考角度 | initialRoll | Transform | included |
| StageAttachmentPanel | motionToggle | VS4 §6.7 | 减少动态 | MotionPreference | panel | included |
| StageAttachmentPanel | saveAction | VS4 §6.7 | 保存 | commit | dirty | included |
| StageAttachmentPanel | cancelAction | VS4 §6.7 | 取消 | rollback | panel | included |
| StageAttachmentPanel | replaceAction | VS4 §6.7 | 重新放置 | re-place | located | included |
| StageAttachmentPanel | deleteAction | VS4 §6.7 | 删除 | Dialog | located | included |
| DecisionDialog | dialogTitle | VS4 §6.8 | decision title | variant | dialog | included |
| DecisionDialog | affectedText | VS4 §6.8 | exact phrase | HealingText.content | identity variant | included |
| DecisionDialog | consequence | VS4 §6.8 | human consequence | variant/state | dialog | included |
| DecisionDialog | progress | VS4 §6.8 | Anchor/Room progress | journal | transaction | included |
| DecisionDialog | cancelAction | VS4 §6.8 | 取消/返回 | cancel | dialog | included |
| DecisionDialog | confirmAction | VS4 §6.8 | consequential action | confirm | enabled identity | included |

#### 2.2.3 `dataBindings[]` denominator

| Component | Source path | Target | Normal | Fallback/error | Kind | Verdict |
|---|---|---|---|---|---|---|
| PhraseCollection | HealingText[] | rowList.items | five rows | empty state | display | included |
| PhraseCollection | HealingText[].size | collectionCount.text | 5 段 | 0 段 | display | included |
| PhraseCollection | DeleteJournal.pendingCount | repairAction | hidden | 1 pending/visible | semantic | included |
| PhraseCollection | Room.loadState | collection state | ready | error+retry | semantic | included |
| PhraseRow | HealingText.content | phraseText | exact copy | 内容不可用+repair | display | included |
| PhraseRow | TextStyle.font/color | styleSummary | Sans·white | fallback font label | display | included |
| PhraseRow | AnchorLink.state | shape/label | localOnly | localOnly semantic | semantic | included |
| PhraseRow | DeleteJournal.state | roomAction/label | enabled | 需要修复 | semantic | included |
| TextObject3D | HealingText.content | front/back | exact copy | 内容不可用 object | display | included |
| TextObject3D | TextStyle.font | glyph resource | chosen family | explicit sans fallback | display | included |
| TextObject3D | TextStyle.color/opacity/size/depth | material/geometry | user values | token defaults/0.05m | display | included |
| TextObject3D | AnchorRuntimeState.pose | AnchorRoot | current located pose | not rendered located | semantic | included |
| TextObject3D | MotionPreference | motion/scale | normal | static | semantic | included |
| TextObject3D | RenderCache.key/state | glyph resource | cached | texture-box | semantic | included |
| EditOverlay | InputDraft.content/validity | field/save | valid draft | previous saved text | semantic | included |
| EditOverlay | PresetPhrase[] | presetPicker | exact 20 | bundled list required | display | included |
| EditOverlay | TextStyle.* | controls/preview | chosen values | sans/white/default/0.80 | display | included |
| EditOverlay | MotionPreference | preview motion | normal/reduce | static | semantic | included |
| EditOverlay | FontAvailability | choice/label | available | explicit sans fallback | semantic | included |
| VoiceInputControl | SpeechSession.support | mic/status | supported | unsupported label | semantic | included |
| VoiceInputControl | SpeechSession.permission | status/action | granted | keyboard fallback | semantic | included |
| VoiceInputControl | SpeechSession.partial/final | resultText | sample text | preserve prior draft | display | included |
| VoiceInputControl | SpeechSession.errorReason | status | source label | generic failure+keyboard | semantic | included |
| VoiceInputControl | InputDraft.content | keyboard handoff | preserved copy | empty only if initial empty | display | included |
| AnchorTruthBadge | AnchorRuntimeState | shape/label | located in Stage | localOnly outside Stage | semantic | included |
| AnchorTruthBadge | AnchorLink.lastLocatedAt | secondary copy | timestamp | 尚未在本次会话定位 | display | included |
| AnchorTruthBadge | DeleteJournal.state | repair semantic | clear | no false success | semantic | included |
| AnchorTruthBadge | currentSpaceState | located eligibility | Stage | Shared forces localOnly | semantic | included |
| StageAttachmentPanel | selection.localId/uuid | identity/actions | unique selected | panel hidden ambiguous | semantic | included |
| StageAttachmentPanel | HealingText.content | selectedSummary | exact phrase | full phrase confirmation | display | included |
| StageAttachmentPanel | TextStyle.size/initialRoll | controls | draft values | last committed | display | included |
| StageAttachmentPanel | AnchorRuntimeState | badge/save | located | recovery panel | semantic | included |
| StageAttachmentPanel | MotionPreference | toggle | selected value | static | semantic | included |
| StageAttachmentPanel | operation journal | actions/guard | idle | conflicting disabled | semantic | included |
| DecisionDialog | dialogVariant | title/consequence/actions | chosen variant | close invalid | semantic | included |
| DecisionDialog | HealingText.content | affectedText | exact phrase | id suffix+unavailable | display | included |
| DecisionDialog | DeleteJournal steps | progress | exact steps | unknown source | semantic | included |
| DecisionDialog | operation journal | exit choices | idle/dirty | wait/cancel operation | semantic | included |
| DecisionDialog | return token | post-close | exact Shared source | Shared library | semantic | included |

#### 2.2.4 Variants / component-specific states denominator

| Component | Variant / state / stacking | Source | Trigger | Observable result | Verdict |
|---|---|---|---|---|---|
| PhraseCollection | variant Empty | VS4 §6.1 | zero rows | preset/New focus | included |
| PhraseCollection | variant Filled | VS4 §6.1 | rows exist | scrollable rows | included |
| PhraseCollection | variant RepairPending | VS4 §6.1 | journal pending | repair pinned | included |
| PhraseCollection | variant Compact | VS4 §6.1 | min tier | single-region list | included |
| PhraseCollection | variant Large | VS4 §6.1 | max tier | up to 8 rows | included |
| PhraseCollection | state default | VS4 §6.1 | ready rows | transparent region | included |
| PhraseCollection | state focused | VS4 §6.1 | gaze/focus | native focus ring | included |
| PhraseCollection | state loading | VS4 §6.1 | Room loading | 正在读取 | included |
| PhraseCollection | state empty | VS4 §6.1 | zero rows | direct New | included |
| PhraseCollection | state error | VS4 §6.1 | Room failure | square+读取失败+retry | included |
| PhraseCollection | state overflow | VS4 §6.1 | row limit exceeded | internal scroll | included |
| PhraseCollection | state disabled | VS4 §6.1 | Dialog open | 55%/noninteractive | included |
| PhraseCollection | precedence | VS4 §6.1 | combined | Dialog>error>loading>focus>default | included |
| PhraseRow | variant LocalOnly | VS4 §6.2 | Shared no locate | dashed+非锚定预览 | included |
| PhraseRow | variant LocatedLastSession | VS4 §6.2 | stored timestamp | dashed+上次已定位 | included |
| PhraseRow | variant NeedsObservation | VS4 §6.2 | observation needed | diamond+进入房间并环视 | included |
| PhraseRow | variant RepairPending | VS4 §6.2 | journal incomplete | square+repair replaces actions | included |
| PhraseRow | variant Selected | VS4 §6.2 | unique selection | warm outline+leading bar | included |
| PhraseRow | state default | VS4 §6.2 | idle | no outline | included |
| PhraseRow | state focused | VS4 §6.2 | focus | clear-blue outline | included |
| PhraseRow | state selected | VS4 §6.2 | pinch/select | warm outline+已选择 | included |
| PhraseRow | state pressed | VS4 §6.2 | press | 90% brightness | included |
| PhraseRow | state disabled | VS4 §6.2 | blocking op | 55% opacity | included |
| PhraseRow | state error/repair | VS4 §6.2 | partial journal | repair label/action | included |
| PhraseRow | state overflow | VS4 §6.2 | long phrase | two-line list preview | included |
| PhraseRow | precedence | VS4 §6.2 | combined | repair overrides color; keeps selection bar | included |
| TextObject3D | variant SharedPreview | VS4 §6.3 | WC-MAIN | truth label; no AnchorRoot | included |
| TextObject3D | variant StageLocated | VS4 §6.3 | current pose resolves | true Stage pose/hit target | included |
| TextObject3D | variant StageCandidate | VS4 §6.3 | create/re-place | guide; float off; confirm | included |
| TextObject3D | variant TextureBoxFallback | VS4 §6.3 | mesh/font fallback | shallow box+rear quad | included |
| TextObject3D | variant ReduceMotion | VS4 §6.3 | reduce setting | Float=0; outline retained | included |
| TextObject3D | variant StaticPerformanceFallback | VS4 §6.3 | frame policy final step | no nonselected glow/motion | included |
| TextObject3D | state default | VS4 §6.3 | visible | mild glow+float/breathe | included |
| TextObject3D | state focused | VS4 §6.3 | gaze/controller | +8%+outline | included |
| TextObject3D | state selected | VS4 §6.3 | pinch | warm outline+panel | included |
| TextObject3D | state dragging | VS4 §6.3 | transform | guide+saved ghost | included |
| TextObject3D | state loading | VS4 §6.3 | cache build | placeholder+pending | included |
| TextObject3D | state unlocated | VS4 §6.3 | unresolved | entity hidden+recovery | included |
| TextObject3D | state error | VS4 §6.3 | render/anchor error | fallback/source error | included |
| TextObject3D | state performance | VS4 §6.3 | frame pressure | text intact; decoration reduced | included |
| TextObject3D | precedence | VS4 §6.3 | combined | unlocated/error>selected>focused; Reduce overrides displacement | included |
| EditOverlay | variant Create | VS4 §6.4 | New | empty draft+presets+voice | included |
| EditOverlay | variant EditExisting | VS4 §6.4 | selected record | prefilled snapshot+dirty | included |
| EditOverlay | variant PresetStart | VS4 §6.4 | preset chosen | exactText populated; no autosave | included |
| EditOverlay | variant Compact | VS4 §6.4 | min tier | one column+internal scroll | included |
| EditOverlay | variant FontFallback | VS4 §6.4 | font unavailable | inline sans fallback | included |
| EditOverlay | variant ReduceMotion | VS4 §6.4 | reduce/static | fade-only; preview static | included |
| EditOverlay | state default | VS4 §6.4 | valid/idle | Thick glass form | included |
| EditOverlay | state editing | VS4 §6.4 | changed | Save enabled+dirty | included |
| EditOverlay | state invalid | VS4 §6.4 | empty/invalid | inline error+Save disabled | included |
| EditOverlay | state loading | VS4 §6.4 | cache | stable form+placeholder | included |
| EditOverlay | state error | VS4 §6.4 | Room/render fail | source+retry; draft kept | included |
| EditOverlay | state overflow | VS4 §6.4 | long content | internal scroll | included |
| EditOverlay | state disabled | VS4 §6.4 | Dialog open | receded; modal focus | included |
| EditOverlay | precedence | VS4 §6.4 | combined | Dialog>error>invalid>editing>default | included |
| VoiceInputControl | variant Idle | VS4 §6.5 | no session | collapsed mic action | included |
| VoiceInputControl | variant CheckingSupport | VS4 §6.5 | mic request | dashed pending+keyboard | included |
| VoiceInputControl | variant PermissionPrompt | VS4 §6.5 | permission absent | purpose+keyboard | included |
| VoiceInputControl | variant Listening | VS4 §6.5 | recognizer active | status/cancel | included |
| VoiceInputControl | variant Partial | VS4 §6.5 | partial callback | 未确认 result | included |
| VoiceInputControl | variant FinalReview | VS4 §6.5 | final callback | 请确认+accept/edit | included |
| VoiceInputControl | variant Fallback | VS4 §6.5 | any speech failure | source reason+keyboard primary | included |
| VoiceInputControl | state idle | VS4 §6.5 | absent | mic action | included |
| VoiceInputControl | state checking | VS4 §6.5 | support query | pending semantics | included |
| VoiceInputControl | state permission | VS4 §6.5 | system request | keyboard visible | included |
| VoiceInputControl | state listening | VS4 §6.5 | active | calm icon pulse | included |
| VoiceInputControl | state partial | VS4 §6.5 | partial | not auto-committed | included |
| VoiceInputControl | state final | VS4 §6.5 | final | confirmation required | included |
| VoiceInputControl | state failures | VS4 §6.5 | denied/unsupported/network/timeout/language/error | shape+human label+keyboard | included |
| VoiceInputControl | state destroyed | VS4 §6.5 | overlay exit | no background indicator | included |
| VoiceInputControl | precedence | VS4 §6.5 | combined | failure/destroyed>final>partial>listening>checking>idle | included |
| AnchorTruthBadge | variant LocalOnly | VS4 §6.6 | Shared | dashed+非锚定预览 | included |
| AnchorTruthBadge | variant Located | VS4 §6.6 | current Stage locate | circle+已定位 | included |
| AnchorTruthBadge | variant NeedsObservation | VS4 §6.6 | locate guidance | diamond+开始环视 | included |
| AnchorTruthBadge | variant Missing | VS4 §6.6 | UUID unresolved | triangle+未找到 | included |
| AnchorTruthBadge | variant Pending | VS4 §6.6 | operation active | dashed+处理中 | included |
| AnchorTruthBadge | variant Repair | VS4 §6.6 | journal conflict | square+需要修复 | included |
| AnchorTruthBadge | variant Permission | VS4 §6.6 | permission denied | triangle+需要授权 | included |
| AnchorTruthBadge | variant Error | VS4 §6.6 | operation error | square+操作未完成 | included |
| AnchorTruthBadge | state default | VS4 §6.6 | semantic display | color+shape+label | included |
| AnchorTruthBadge | state focused | VS4 §6.6 | actionable focus | 2dp outline | included |
| AnchorTruthBadge | state pressed | VS4 §6.6 | retry press | native press | included |
| AnchorTruthBadge | state disabled | VS4 §6.6 | no action/mismatch | reason readable | included |
| AnchorTruthBadge | state aging | VS4 §6.6 | last-session | dashed+上次已定位 | included |
| AnchorTruthBadge | state error/repair | VS4 §6.6 | transaction issue | square+source/next | included |
| AnchorTruthBadge | precedence | VS4 §6.6 | combined | repair/error>aging>focused/default; Shared blocks Located | included |
| StageAttachmentPanel | variant Transform | VS4 §6.7 | dirty transform | size/roll/Save/Cancel | included |
| StageAttachmentPanel | variant StyleSummary | VS4 §6.7 | read/status | condensed read-only summary | included |
| StageAttachmentPanel | variant ReduceMotion | VS4 §6.7 | mode choice | normal/reduce/static | included |
| StageAttachmentPanel | variant RepairPending | VS4 §6.7 | journal/update fail | repair actions replace controls | included |
| StageAttachmentPanel | variant ControllerFocused | VS4 §6.7 | controller/keyboard | labels/tooltips/focus order | included |
| StageAttachmentPanel | state default | VS4 §6.7 | unique located selection | matte panel | included |
| StageAttachmentPanel | state focused | VS4 §6.7 | control focus | native focus | included |
| StageAttachmentPanel | state editing | VS4 §6.7 | dirty size/roll | Save+dirty label | included |
| StageAttachmentPanel | state saving | VS4 §6.7 | commit | pending+disabled actions | included |
| StageAttachmentPanel | state error | VS4 §6.7 | update fail | source+retry/cancel | included |
| StageAttachmentPanel | state disabled | VS4 §6.7 | tracking/ambiguity | panel detaches | included |
| StageAttachmentPanel | state overflow | VS4 §6.7 | constrained/long | scroll+fixed actions | included |
| StageAttachmentPanel | precedence | VS4 §6.7 | combined | tracking/error>saving>editing>focused; dialog suppresses | included |
| DecisionDialog | variant StageConsent | VS4 §6.8 | Shared Stage intent | glass disclosure Cancel/Enter | included |
| DecisionDialog | variant DeleteConfirm | VS4 §6.8 | Stage delete | matte phrase+two-store consequence | included |
| DecisionDialog | variant DeleteRepair | VS4 §6.8 | partial delete | completed/remaining+Retry/Exit | included |
| DecisionDialog | variant StageExitGuard | VS4 §6.8 | dirty/in-flight exit | safe choices+return token | included |
| DecisionDialog | variant RePlaceConfirm | VS4 §6.8 | re-place recovery | old-link consequence | included |
| DecisionDialog | variant PermissionExplanation | VS4 §6.8 | Shared permission context | purpose+fallback | included |
| DecisionDialog | state default | VS4 §6.8 | ready | isolated modal | included |
| DecisionDialog | state focused | VS4 §6.8 | action focus | native focus | included |
| DecisionDialog | state confirming | VS4 §6.8 | confirm | pending+disabled | included |
| DecisionDialog | state partial | VS4 §6.8 | one delete step fails | repair remaining step | included |
| DecisionDialog | state error | VS4 §6.8 | operation fail | source+next | included |
| DecisionDialog | state disabled | VS4 §6.8 | invalid identity/tracking | confirm disabled+reason | included |
| DecisionDialog | state overflow | VS4 §6.8 | long copy | body scroll+fixed actions | included |
| DecisionDialog | precedence | VS4 §6.8 | combined | partial/error>confirming>focused; back safe-cancel/repair | included |

#### 2.2.5 Responsive tiers / Reduce Motion denominator

| Scenario | Source | Window / content area | Trigger | Observable result | Verdict |
|---|---|---|---|---|---|
| Large | Interaction 7 §9 | max 1.60×1.04×0.60m; 1536×976dp content; 544/968dp regions | density control Max | 5/7 split, 8 rows, 0.88×0.60m preview envelope | included |
| Compact | Interaction 7 §9 | default 1.28×0.832×0.48m; 1216×768dp content; 440/752dp regions | density control Default | 2/5+3/5 split, one action row | included |
| Constrained | Interaction 7 §9 | min 0.96×0.624×0.36m; 896×560dp content; one 896×480dp region | density control Min | collection/preview switch, overlay fills content, scroll/wrap | included |
| Reduce Motion | Interaction 7 §13 / VS4 §9 | N/A | persistent toggle / R key | no float/scale; outline/status/fade retained | included |

### 2.3 Generation-side Declarative Checklist

> Stage 14 actual results below were rebuilt independently; detailed per-item evidence is in §8.1.

| Check Item | Source denominator | Preview selector / structure | Trigger | Expected | Actual / QA verdict |
|---|---|---|---|---|---|
| Manifest complete | §2.2: 16 states, 19 transitions, 52 elements, 39 bindings, 48 variants, 57 state rows, 8 precedence rows, 4 responsive/motion | this report + active design facts | independent recount | totals are 16/19/52/39/48/57/8/4 with zero count difference | pass |
| State machine exists | §2.2.1 | `#app[data-state]`, `#stateSelect`, `renderScene(id)` | selected all 16 S-IDs from a fresh page | 16 selectors render; S-11 direct selection lacks its required AttachmentPanel, so state fidelity is 15/16 | block |
| Transitions triggerable | §2.2.1 | product `[data-action]`, dialogs, voice controls | executed all reachable paths and inspected missing branches | 11/19 match; TR-05/06/08/10/14/16/17/19 are absent, partial, stale-state, or wrong-target | block |
| DOM lookup | §2.2.2 | all source `[data-preview-id]` plus dynamic states | Shared/Stage/edit/dialog/delete progress | all 52 IDs have selectors; halo/glow/previewTruth stay hidden and hitVolume is a zero-result span with no trigger | block |
| Data modes | §2.2.3 | 39 audit chips × Normal/Fallback/Error | clicked 117 combinations | each mode emits one generic sentence for all 39; no binding-specific target value/fallback/error result | block |
| Variants / states | §2.2.4 | 8 component selectors + 48 variant + 57 state + 8 precedence options | triggered all 113 rows | 8 precedence rows show exact text; 105 variant/state rows only show the same generic instruction, not row-specific visual/behavior/accessibility deltas | block |
| High-risk confirmation | §2.2.1 | `#decisionDialog` and Stage panels | exercised cancel/confirm for Stage entry, re-place, delete, exit | entry/re-place/delete block correctly; exit exposes only one return action and lacks specified dirty/in-flight choices; target state bookkeeping is also stale for entry/delete intents | block |
| Responsive / Reduce Motion | §2.2.5 | `[data-tier]`, `#motionButton`, body tier/reduce classes | switched Max/Default/Min and motion toggle | 544px/440px/block single-region reflow observed; targets remain 56px; animation changes 8s/6s → 0.001ms | pass |

### 2.4 Preview Denominator Reconciliation

| Denominator Type | Design-fact total | Generation Manifest | QA-rebuilt total | Difference | Verdict |
|---|---:|---:|---:|---:|---|
| States | 16 | 16 | 16 | 0 | pass count; implementation 15/16 |
| Transitions | 19 | 19 | 19 | 0 | pass count; implementation 11/19 |
| renderSpec.elements[] | 52 | 52 | 52 | 0 | pass count; implementation 48/52 |
| dataBindings[] | 39 | 39 | 39 | 0 | pass count; target-specific normal/fallback evidence 0/39 |
| Variants | 48 | 48 | 48 | 0 | pass count; expected-specific implementation 0/48 |
| Component state rows | 57 | 57 | 57 | 0 | pass count; expected-specific implementation 0/57 |
| Stacking precedence combinations | 8 | 8 | 8 | 0 | pass |
| Responsive / Reduce Motion | 4 | 4 | 4 | 0 | pass |

### 2.5 Preview Hard Gate

| hard gate | Generator evidence | Verdict |
|---|---|---|
| HG-PREVIEW-INPUT | §2.1 references exact active revisions and Critique 6 Stage 12 pass | pass |
| HG-PREVIEW-MANIFEST | §2.2 itemized denominators; independent recount in §2.4 | pass |
| HG-PREVIEW-CHECKS | §2.3 + §8.1 actual interaction evidence | block — core transition/binding/variant/state/style-preview gaps |
| HG-PREVIEW-DENOMINATOR | §2.4 all generator/QA totals equal, differences zero | pass |
| HG-PREVIEW-MAPS | §§3.1–3.5 + §8.1 per-item reverse lookup | block — names/selectors exist but several maps do not produce the declared result |

| Field | Value |
|---|---|
| previewImplementationFidelity | block |
| minimumCompletenessGate | block for Stage 14 reviewed revision |
| designStatusImpact | invalid until a bounded Preview patch and fresh Stage 13/14 rerun; no delivery status claimed |

## 3. Generation Implementation Mapping

### 3.1 State / transition → scenario mapping

| Source | Trigger | Stable selector / JS | Expected visible result | QA actual/verdict |
|---|---|---|---|---|
| S-01 | state selector / zero rows | `#app[data-state="S-01"]`; `renderScene` | empty collection + preset/New | pending |
| S-02 | launch/save/return | `#app[data-state="S-02"]` | Shared library + non-anchor preview | pending |
| S-03 | New/edit | `#editWrap`, `#exactText` | exact-text overlay | pending |
| S-04 | voice request | `#speechStatus`; `voiceState('checking/listening')` | listening + stop | pending |
| S-05 | simulated final | `#resultText`; `voiceState('final')` | review text, no autosave | pending |
| S-06 | style controls | `#fontChoice`, `#colorChoice`, sliders | exact styled non-anchor preview intent | pending |
| S-07 | room action | `#decisionDialog[data-variant="StageConsent"]` | explicit mode disclosure | pending |
| S-08 | Enter confirm | `#app[data-state="S-08"]` | locate status, Web boundary | pending |
| S-09 | re-place confirm | `#app[data-state="S-09"]` | candidate + pose confirm | pending |
| S-10 | located simulator | `#app[data-state="S-10"] .room-text` | 5+ Stage text simulations | pending |
| S-11 | select room text | `#app[data-state="S-11"] [data-preview-id="StageAttachmentPanel"]` | single selection + panel | pending |
| S-12 | Delete action | `#decisionDialog[data-variant="DeleteConfirm"]` | identity + two-store consequence | pending |
| S-13 | Error mode / recovery | `#app[data-state="S-13"]` | retry/re-place/exit | pending |
| S-14 | state selector | `#app[data-state="S-14"]` | Room/journal source + repair | pending |
| S-15 | 模拟失败 | `#speechStatus`; `voiceState('failure')` | human reason + keyboard | pending |
| S-16 | Exit/Escape | `#app[data-state="S-16"]` | safe exit + return disclosure | pending |
| TR-01 | New/edit | `#newButton`, `[data-action="edit"]` | S-03 | pending |
| TR-02 | mic | `#micAction` | S-04 after support check | pending |
| TR-03 | timer final sample | `voiceState('final')` | S-05 | pending |
| TR-04 | simulate voice fail | `#simulateVoiceFail` | S-15 | pending |
| TR-05 | keyboard | `#keyboardFallback` | focus `#exactText`, S-03 | pending |
| TR-06 | valid exact text/style path | `#saveAction` precondition + audit state | S-06 semantics before local commit | pending |
| TR-07 | save | `#saveAction` | phrase committed locally, S-02 | pending |
| TR-08 | 在房间中查看 | `[data-action="stage"]` | S-07 dialog | pending |
| TR-09 | Enter dialog confirm | `#dialogConfirm` StageConsent | S-08 | pending |
| TR-10 | 模拟定位完成 | `[data-action="located"]` | S-10 or S-13 in error mode | pending |
| TR-11 | 确认位置 | `[data-action="confirm-pose"]` | S-10 | pending |
| TR-12 | room text | `[data-action="select-stage"]` | S-11 + panel | pending |
| TR-13 | Save/Cancel panel | `[data-action="save-transform/cancel-select"]` | S-10 | pending |
| TR-14 | Delete panel | `[data-action="delete"]` | S-12 dialog | pending |
| TR-15 | Delete confirm | `#dialogConfirm`; progress steps | Anchor then Room simulated; S-10 | pending |
| TR-16 | Error data + locate | `[data-data-mode="error"]` then locate | S-13 | pending |
| TR-17 | Retry/re-place | S-13 action buttons | S-08/S-09 simulation | pending |
| TR-18 | Exit/Escape | `[data-action="exit-stage"]` | S-16 | pending |
| TR-19 | 确认返回 Shared | `[data-action="return-shared"]` | S-02; no anchor visibility promise | pending |

### 3.2 Component / renderSpec.elements[] → DOM mapping

| Component.element | Source | DOM selector | Conditional trigger | QA actual/verdict |
|---|---|---|---|---|
| PhraseCollection.collectionTitle | VS4 §6.1 | `[data-preview-id="collectionTitle"]` | Shared library | pending |
| PhraseCollection.collectionCount | VS4 §6.1 | `[data-preview-id="collectionCount"]` | Shared library | pending |
| PhraseCollection.newAction | VS4 §6.1 | `#newButton[data-preview-id="newAction"]` | always | pending |
| PhraseCollection.rowList | VS4 §6.1 | `[data-preview-id="rowList"]` | rows exist | pending |
| PhraseCollection.emptyAction | VS4 §6.1 | `[data-preview-id="emptyAction"]` | S-01 | pending |
| PhraseCollection.repairAction | VS4 §6.1 | `[data-preview-id="repairAction"]` | repair row exists | pending |
| PhraseRow.stateShape | VS4 §6.2 | `[data-preview-id="stateShape"]` | each row | pending |
| PhraseRow.phraseText | VS4 §6.2 | `[data-preview-id="phraseText"]` | each row | pending |
| PhraseRow.styleSummary | VS4 §6.2 | `[data-preview-id="styleSummary"]` | each row | pending |
| PhraseRow.stateLabel | VS4 §6.2 | `[data-preview-id="stateLabel"]` | each row | pending |
| PhraseRow.editAction | VS4 §6.2 | `[data-preview-id="editAction"]` | normal row | pending |
| PhraseRow.roomAction | VS4 §6.2 | `[data-preview-id="roomAction"]` | normal row | pending |
| TextObject3D.frontGlyph | VS4 §6.3 | `[data-preview-id="frontGlyph"]` | Shared hero | pending |
| TextObject3D.backGlyph | VS4 §6.3 | `[data-preview-id="backGlyph"]` | Shared hero rear layer | pending |
| TextObject3D.sideDepth | VS4 §6.3 | `[data-preview-id="sideDepth"]` | rendered text | pending |
| TextObject3D.halo | VS4 §6.3 | `[data-preview-id="halo"]` | audit/contrast conditional | pending |
| TextObject3D.glow | VS4 §6.3 | `[data-preview-id="glow"]` | audit/decorative conditional | pending |
| TextObject3D.hitVolume | VS4 §6.3 | `[data-preview-id="hitVolume"]` | text object | pending |
| TextObject3D.previewTruth | VS4 §6.3 | `[data-preview-id="previewTruth"]` + visible badge | Shared only | pending |
| EditOverlay.exactText | VS4 §6.4 | `#exactText` | overlay | pending |
| EditOverlay.presetPicker | VS4 §6.4 | `#presetPicker` | overlay | pending |
| EditOverlay.voiceControl | VS4 §6.4 | `[data-preview-id="voiceControl"]` | overlay | pending |
| EditOverlay.fontChoice | VS4 §6.4 | `#fontChoice` | overlay | pending |
| EditOverlay.colorChoice | VS4 §6.4 | `#colorChoice` | overlay | pending |
| EditOverlay.sizeSlider | VS4 §6.4 | `#sizeSlider` | overlay | pending |
| EditOverlay.opacitySlider | VS4 §6.4 | `#opacitySlider` | overlay | pending |
| EditOverlay.previewLabel | VS4 §6.4 | `[data-preview-id="previewLabel"]` | overlay | pending |
| EditOverlay.cancelAction | VS4 §6.4 | `#cancelAction` | overlay | pending |
| EditOverlay.saveAction | VS4 §6.4 | `#saveAction` | valid draft | pending |
| VoiceInputControl.micAction | VS4 §6.5 | `#micAction` | overlay | pending |
| VoiceInputControl.speechStatus | VS4 §6.5 | `#speechStatus` | voice flow | pending |
| VoiceInputControl.resultText | VS4 §6.5 | `#resultText` | partial/final/failure | pending |
| VoiceInputControl.cancelListening | VS4 §6.5 | `#cancelListening` | listening | pending |
| VoiceInputControl.keyboardFallback | VS4 §6.5 | `#keyboardFallback` | always recovery | pending |
| AnchorTruthBadge.statusShape | VS4 §6.6 | `[data-preview-id="statusShape"]` | Shared/Stage status | pending |
| AnchorTruthBadge.statusLabel | VS4 §6.6 | `[data-preview-id="statusLabel"]` | Shared/Stage status | pending |
| AnchorTruthBadge.retryAction | VS4 §6.6 | `[data-preview-id="retryAction"]` | recovery only | pending |
| StageAttachmentPanel.selectedSummary | VS4 §6.7 | `[data-preview-id="selectedSummary"]` | S-11 | pending |
| StageAttachmentPanel.anchorBadge | VS4 §6.7 | `[data-preview-id="anchorBadge"]` | S-11 | pending |
| StageAttachmentPanel.sizeControl | VS4 §6.7 | `[data-preview-id="sizeControl"]` | S-11 | pending |
| StageAttachmentPanel.rollControl | VS4 §6.7 | `[data-preview-id="rollControl"]` | S-11 | pending |
| StageAttachmentPanel.motionToggle | VS4 §6.7 | `[data-preview-id="motionToggle"]` | S-11 | pending |
| StageAttachmentPanel.saveAction | VS4 §6.7 | `[data-preview-id="StageAttachmentPanel"] [data-preview-id="saveAction"]` | S-11 | pending |
| StageAttachmentPanel.cancelAction | VS4 §6.7 | `[data-preview-id="StageAttachmentPanel"] [data-preview-id="cancelAction"]` | S-11 | pending |
| StageAttachmentPanel.replaceAction | VS4 §6.7 | `[data-preview-id="replaceAction"]` | S-11 | pending |
| StageAttachmentPanel.deleteAction | VS4 §6.7 | `[data-preview-id="deleteAction"]` | S-11 | pending |
| DecisionDialog.dialogTitle | VS4 §6.8 | `#dialogTitle` | dialog | pending |
| DecisionDialog.affectedText | VS4 §6.8 | `#affectedText` | identity dialog | pending |
| DecisionDialog.consequence | VS4 §6.8 | `#dialogConsequence` | dialog | pending |
| DecisionDialog.progress | VS4 §6.8 | `#dialogProgress` | transaction | pending |
| DecisionDialog.cancelAction | VS4 §6.8 | `#dialogCancel` | dialog | pending |
| DecisionDialog.confirmAction | VS4 §6.8 | `#dialogConfirm` | dialog | pending |

### 3.3 dataBindings[] → data / fallback mapping

| Source path | Target / selector | Normal trigger | Fallback/error trigger | QA verdict |
|---|---|---|---|---|
| HealingText[] (Collection) | `[data-binding="HealingText[]"]` audit chip + rowList | Normal | Fallback/Error modes | pending |
| HealingText[].size | `[data-binding="HealingText[].size"]`, collectionCount | Normal five rows | S-01 / Fallback | pending |
| DeleteJournal.pendingCount | matching audit chip, repairAction | Normal hidden/repair row sample | Error | pending |
| Room.loadState | matching audit chip, collection footer | Normal ready | Error shows 读取失败 | pending |
| HealingText.content (Row) | matching audit chip, phraseText | Normal exact | Fallback 内容不可用 | pending |
| TextStyle.font/color (Row) | matching audit chip, styleSummary | Normal values | Fallback label | pending |
| AnchorLink.state (Row) | matching audit chip, stateShape/stateLabel | status samples | Error/repair sample | pending |
| DeleteJournal.state (Row) | matching audit chip, repair row | Normal | Error/repair | pending |
| HealingText.content (TextObject) | matching audit chip, front/back | Normal exact | Fallback object/audit | pending |
| TextStyle.font (TextObject) | matching audit chip, glyph font | choose three fonts | FontFallback audit | pending |
| TextStyle.color/opacity/size/depth | matching audit chip, text object/styles | style controls | Fallback data mode | pending |
| AnchorRuntimeState.pose (TextObject) | matching audit chip, Stage text | Stage locate simulator | Error→Recovery | pending |
| MotionPreference (TextObject) | matching audit chip, body.reduce-motion | Normal | Reduce toggle / R | pending |
| RenderCache.key/state | matching audit chip, factStage | Normal | TextureBoxFallback | pending |
| InputDraft.content/validity | matching audit chip, exactText/save | valid input | empty invalid | pending |
| PresetPhrase[] | matching audit chip, presetPicker | exact 20 buttons | Fallback declaration | pending |
| TextStyle.* (Overlay) | matching audit chip, controls | adjust controls | defaults via new draft | pending |
| MotionPreference (Overlay) | matching audit chip, motion button | normal | reduce/static | pending |
| FontAvailability | matching audit chip, factStage | normal | FontFallback | pending |
| SpeechSession.support | matching audit chip, speechStatus | voice request | simulated failure | pending |
| SpeechSession.permission | matching audit chip, speechStatus | listening | simulated failure/keyboard | pending |
| SpeechSession.partial/final | matching audit chip, resultText | voice timer | fallback preserves draft | pending |
| SpeechSession.errorReason | matching audit chip, speechStatus | final | simulate failure | pending |
| InputDraft.content (Voice) | matching audit chip, exactText | recognized final | keyboard preserves draft | pending |
| AnchorRuntimeState (Badge) | matching audit chip, badge | Stage located | Shared LocalOnly/Error | pending |
| AnchorLink.lastLocatedAt | matching audit chip, factStage | LocatedLastSession | fallback label | pending |
| DeleteJournal.state (Badge) | matching audit chip, badge | clear | Repair/Error variants | pending |
| currentSpaceState | matching audit chip, Shared/Stage banner | Stage located | Shared forces LocalOnly | pending |
| selection.localId/uuid | matching audit chip, attachment | select one room text | disabled/ambiguous audit | pending |
| HealingText.content (Panel) | matching audit chip, selectedSummary | Stage select | fallback confirmation | pending |
| TextStyle.size/initialRoll | matching audit chip, panel sliders | S-11 | cancel uses last committed | pending |
| AnchorRuntimeState (Panel) | matching audit chip, anchorBadge | located | Error/disabled audit | pending |
| MotionPreference (Panel) | matching audit chip, motionToggle | normal | Reduce Motion | pending |
| operation journal (Panel) | matching audit chip, panel/actions | idle | Error/RepairPending | pending |
| dialogVariant | matching audit chip, decisionDialog | open each high-risk dialog | invalid closes by declaration | pending |
| HealingText.content (Dialog) | matching audit chip, affectedText | exact selected phrase | fallback id suffix audit | pending |
| DeleteJournal steps | matching audit chip, dialogProgress | Delete confirm | Error/partial audit | pending |
| operation journal (Dialog) | matching audit chip, exit guard | normal | dirty/in-flight state | pending |
| return token | matching audit chip, return-shared action | exact Shared selected phrase | Shared library fallback | pending |

### 3.4 Variants / component states → triggerable behavior mapping

> Stable trigger pattern for every row: choose the named component in `#componentSelect`, then the named option in `#variantSelect` or `#componentStateSelect`; `#factStage[data-variant][data-component-state][data-tone]` exposes the observable change. Product-specific entries are listed where present.

| Component | Fact | Stable selector / product entry | Expected observable result | QA verdict |
|---|---|---|---|---|
| PhraseCollection | variant Empty | `#variantSelect option[value="Empty"]`; S-01 | preset/New focus | pending |
| PhraseCollection | variant Filled | option Filled; S-02 | rows | pending |
| PhraseCollection | variant RepairPending | option RepairPending; repairAction | repair pinned | pending |
| PhraseCollection | variant Compact | option Compact; Min tier | single region | pending |
| PhraseCollection | variant Large | option Large; Max tier | 8-row capacity | pending |
| PhraseCollection | state default | state option default | ready region | pending |
| PhraseCollection | state focused | state option focused | focus tone | pending |
| PhraseCollection | state loading | state option loading | loading label | pending |
| PhraseCollection | state empty | state option empty; S-01 | empty action | pending |
| PhraseCollection | state error | state option error; Error data | error tone/source | pending |
| PhraseCollection | state overflow | state option overflow | scroll semantics | pending |
| PhraseCollection | state disabled | state option disabled | disabled precedence | pending |
| PhraseCollection | precedence | state option precedence | exact precedence text | pending |
| PhraseRow | variant LocalOnly | variant option; Shared rows | dashed non-anchor | pending |
| PhraseRow | variant LocatedLastSession | option; row sample 2 | dashed last-session | pending |
| PhraseRow | variant NeedsObservation | option; row sample 3 | diamond/observe | pending |
| PhraseRow | variant RepairPending | option; row sample 5 | square/repair | pending |
| PhraseRow | variant Selected | option; select row | warm outline/bar | pending |
| PhraseRow | state default | state option default | default label | pending |
| PhraseRow | state focused | state option focused | focus tone | pending |
| PhraseRow | state selected | state option selected | selected tone | pending |
| PhraseRow | state pressed | state option pressed | pressed label | pending |
| PhraseRow | state disabled | state option disabled | disabled tone | pending |
| PhraseRow | state error/repair | state option error/repair | error tone | pending |
| PhraseRow | state overflow | state option overflow | overflow label | pending |
| PhraseRow | precedence | state option precedence | repair/selection rule | pending |
| TextObject3D | variant SharedPreview | option; Shared hero | truth label/no AnchorRoot | pending |
| TextObject3D | variant StageLocated | option; S-10 | located subject | pending |
| TextObject3D | variant StageCandidate | option; S-09 | candidate/confirm | pending |
| TextObject3D | variant TextureBoxFallback | option | fallback explanation | pending |
| TextObject3D | variant ReduceMotion | option; motion toggle | Float=0 | pending |
| TextObject3D | variant StaticPerformanceFallback | option | decoration reduction text | pending |
| TextObject3D | state default | state option default | default motion label | pending |
| TextObject3D | state focused | state option focused; hover hero | focus tone | pending |
| TextObject3D | state selected | state option selected; S-11 | selection/panel | pending |
| TextObject3D | state dragging | state option dragging | guide semantics | pending |
| TextObject3D | state loading | state option loading | placeholder semantics | pending |
| TextObject3D | state unlocated | state option unlocated | error tone/hidden rule | pending |
| TextObject3D | state error | state option error; Error data | error/recovery | pending |
| TextObject3D | state performance | state option performance | static degradation | pending |
| TextObject3D | precedence | state option precedence | exact precedence text | pending |
| EditOverlay | variant Create | option; New | empty draft/defaults | pending |
| EditOverlay | variant EditExisting | option; row Edit | prefilled snapshot | pending |
| EditOverlay | variant PresetStart | option; preset button | populated/no autosave | pending |
| EditOverlay | variant Compact | option; Min tier | one column/scroll | pending |
| EditOverlay | variant FontFallback | option | explicit sans fallback | pending |
| EditOverlay | variant ReduceMotion | option; motion toggle | fade/static | pending |
| EditOverlay | state default | state option default | valid form | pending |
| EditOverlay | state editing | state option editing; input | dirty/save | pending |
| EditOverlay | state invalid | state option invalid; empty | disabled Save | pending |
| EditOverlay | state loading | state option loading | stable placeholder | pending |
| EditOverlay | state error | state option error | source/draft kept | pending |
| EditOverlay | state overflow | state option overflow | scroll | pending |
| EditOverlay | state disabled | state option disabled; Dialog | receded/modal | pending |
| EditOverlay | precedence | state option precedence | exact precedence text | pending |
| VoiceInputControl | variant Idle | option; open overlay | mic | pending |
| VoiceInputControl | variant CheckingSupport | option; mic | checking label | pending |
| VoiceInputControl | variant PermissionPrompt | option | permission+keyboard | pending |
| VoiceInputControl | variant Listening | option; voice timer | cancel/status | pending |
| VoiceInputControl | variant Partial | option; timer partial | unconfirmed result | pending |
| VoiceInputControl | variant FinalReview | option; timer final | confirmation label | pending |
| VoiceInputControl | variant Fallback | option; simulate fail | reason+keyboard | pending |
| VoiceInputControl | state idle | state option idle | mic | pending |
| VoiceInputControl | state checking | state option checking | pending tone | pending |
| VoiceInputControl | state permission | state option permission | permission copy | pending |
| VoiceInputControl | state listening | state option listening | calm status | pending |
| VoiceInputControl | state partial | state option partial | not committed | pending |
| VoiceInputControl | state final | state option final | confirm required | pending |
| VoiceInputControl | state failures | state option failures | source+keyboard | pending |
| VoiceInputControl | state destroyed | state option destroyed | no background listening | pending |
| VoiceInputControl | precedence | state option precedence | exact precedence text | pending |
| AnchorTruthBadge | variant LocalOnly | option; Shared | dashed label | pending |
| AnchorTruthBadge | variant Located | option; Stage | circle label | pending |
| AnchorTruthBadge | variant NeedsObservation | option; S-08 | diamond/action | pending |
| AnchorTruthBadge | variant Missing | option; Error locate | triangle/re-place | pending |
| AnchorTruthBadge | variant Pending | option; delete progress | dashed processing | pending |
| AnchorTruthBadge | variant Repair | option; S-13/S-14 | square repair | pending |
| AnchorTruthBadge | variant Permission | option | authorization label | pending |
| AnchorTruthBadge | variant Error | option | source error | pending |
| AnchorTruthBadge | state default | state option default | semantic cue | pending |
| AnchorTruthBadge | state focused | state option focused | focus tone | pending |
| AnchorTruthBadge | state pressed | state option pressed | pressed label | pending |
| AnchorTruthBadge | state disabled | state option disabled | reason | pending |
| AnchorTruthBadge | state aging | state option aging; row sample 2 | last-session cue | pending |
| AnchorTruthBadge | state error/repair | state option error/repair | error tone | pending |
| AnchorTruthBadge | precedence | state option precedence | Shared/repair rule | pending |
| StageAttachmentPanel | variant Transform | option; S-11 | sliders/actions | pending |
| StageAttachmentPanel | variant StyleSummary | option | condensed summary | pending |
| StageAttachmentPanel | variant ReduceMotion | option; panel toggle | motion options | pending |
| StageAttachmentPanel | variant RepairPending | option; S-13 | repair replaces controls | pending |
| StageAttachmentPanel | variant ControllerFocused | option | focus labels | pending |
| StageAttachmentPanel | state default | state option default; S-11 | matte panel | pending |
| StageAttachmentPanel | state focused | state option focused | focus tone | pending |
| StageAttachmentPanel | state editing | state option editing | dirty/save | pending |
| StageAttachmentPanel | state saving | state option saving | pending actions | pending |
| StageAttachmentPanel | state error | state option error | source/retry | pending |
| StageAttachmentPanel | state disabled | state option disabled | detach rule | pending |
| StageAttachmentPanel | state overflow | state option overflow; Min | scroll/fixed actions | pending |
| StageAttachmentPanel | precedence | state option precedence | dialog suppresses | pending |
| DecisionDialog | variant StageConsent | option; room action | Shared glass disclosure | pending |
| DecisionDialog | variant DeleteConfirm | option; panel Delete | Stage matte/two-store | pending |
| DecisionDialog | variant DeleteRepair | option | remaining step | pending |
| DecisionDialog | variant StageExitGuard | option; exit | safe choices | pending |
| DecisionDialog | variant RePlaceConfirm | option; re-place | old-link consequence | pending |
| DecisionDialog | variant PermissionExplanation | option | purpose/fallback | pending |
| DecisionDialog | state default | state option default | modal | pending |
| DecisionDialog | state focused | state option focused | focus tone | pending |
| DecisionDialog | state confirming | state option confirming; delete | progress/disabled | pending |
| DecisionDialog | state partial | state option partial | remaining step | pending |
| DecisionDialog | state error | state option error | source/next | pending |
| DecisionDialog | state disabled | state option disabled | confirm disabled | pending |
| DecisionDialog | state overflow | state option overflow | body scroll/actions | pending |
| DecisionDialog | precedence | state option precedence | exact safe-back rule | pending |

### 3.5 Responsive tiers / Reduce Motion → reflow mapping

| Scenario | Source | Trigger / selector | Must preserve | Structural result | QA verdict |
|---|---|---|---|---|---|
| Large / max | Interaction7 §9 | `[data-tier="max"]`; `body.tier-max` | focus, 56dp targets | 544px list + flexible preview, taller density, no new primary column | pending |
| Compact / default | Interaction7 §9 | `[data-tier="default"]`; `body.tier-default` | collection+preview | balanced two-region view | pending |
| Constrained / min | Interaction7 §9 | `[data-tier="min"]`; `body.tier-min` | target/type floor | one primary region, wrapped actions, full overlay/scroll | pending |
| Reduce Motion | Interaction7 §13 / VS4 §9 | `#motionButton`, R, `body.reduce-motion` | semantic cues/feedback | float/breathe/scale removed; focus/fade retained | pending |

## 4. Requirements Traceability

| Task / requirement | Priority | State(s) | Components | Generation validation entry | Coverage |
|---|---|---|---|---|---|
| T-01 source/new/preset | P0 | S-01/S-03 | Collection/EditOverlay | New + exact 20 presets | mapped |
| T-02 exact accept/edit/cancel | P0 | S-03/S-05 | EditOverlay/Voice | field, final review, Save/Cancel | mapped |
| T-03 font/color/size/opacity/non-anchor preview | P0 | S-06 | EditOverlay/TextObject | three fonts, five colors, sliders | mapped |
| T-04 multi-text selection/manage | P0 | S-02/S-14 | Collection/Row/Badge | five initial rows + repair state | mapped |
| T-05 explicit Stage entry | P0 | S-07 | Row/DecisionDialog | StageConsent modal | mapped |
| T-06 locate truth/recovery | P0 | S-08/S-13 | Badge/Dialog | locate/error data modes | mapped |
| T-07 pose confirm/cancel | P0 | S-09 | TextObject/Dialog | candidate + confirm/cancel | mapped |
| T-08 view/select/exit | P0 | S-10/S-16 | TextObject | 5+ room texts + exit | mapped |
| T-09 transform/save/cancel | P0 | S-11 | AttachmentPanel | size/roll + Save/Cancel | mapped |
| T-10 delete/repair consistency | P0 | S-12/S-13/S-14 | Panel/Dialog/Badge | two-step progress; no early success | mapped |
| T-11 retry/re-place/exit | P0 | S-13 | Badge/Dialog | explicit recovery actions | mapped |
| T-12 normal/reduce/static | P0 | Shared/Stage | TextObject/Overlay/Panel | toggle + R + audit variants | mapped |
| T-13 voice/fallback | P0 | S-04/S-05/S-15 | VoiceInputControl | listen/final/failure/keyboard | mapped |
| T-14 five-text performance policy | P0 | S-10 | TextObject | 5+ samples + performance fallback audit | mapped; Web performance not claimed |
| T-15 safe Stage exit/return | P0 | S-16 | DecisionDialog | exit guard + exact Shared return | mapped |

- Generation mapping coverage: 15/15 tasks.
- Device/runtime acceptance remains outside this preview.

## 5. Sample Data

| Source | Normal sample | Fallback / exception | Mapped element | Conversion |
|---|---|---|---|---|
| HealingText.content | “你已经做得很好了” | “内容不可用” + repair | phraseText/frontGlyph | exact human text |
| TextStyle | Serif / #FFE4B5 / 80% / 100% | Sans / white / 80% / 0.05m declared | controls/text preview | labels, not machine enum |
| AnchorLink.state | localOnly / last-session / observe | repair | shape+stateLabel | dashed/diamond/square + label |
| AnchorRuntimeState | located | missing/error → Recovery | Stage badge/entity | current Stage only |
| SpeechSession | listening → final | network/permission generic failure | speechStatus/resultText | human reason + keyboard |
| DeleteJournal | Anchor pending → complete; Room waiting → complete | partial/repair in audit | dialogProgress | explicit store step labels |
| MotionPreference | normal | reduce/static | body class + controls | human label |
| PresetPhrase[] | exact 20 bundled phrases | list required | presetPicker | immutable local copy |

## 6. Web Logic Consistency Tolerance

| Item | Range / standard |
|---|---|
| Logical relationship | `exact_id_relationship_match` |
| Visual token reference | `declared_group_reference_presence` |
| Exclusions | screenshot visual diff; CSS px→PICO physical size; device color delta; Web/PICO parity |

## 7. Device-Validation Boundary

| Validation Item | Ownership | Status |
|---|---|---|
| Real glyph extrusion/front-back readability/LookAt | target PICO runtime + device | not_performed |
| Persistent Anchor load/place/update/delete/restart relocation | target PICO runtime + device | not_performed |
| Shared↔Stage cross-space handoff/visibility | SDK+real-device evidence | not_performed; not promised |
| Physical distance/readability/occlusion | device validation | not_performed |
| Comfort/fatigue/10-minute observation | device study | not_performed |
| Hand/controller/gaze hit precision | device validation | not_performed |
| 60fps with 5+ text entities | device performance validation | not_performed |
| Web state/component/binding logic | Stage 14 independent preview review | pass for Preview rev 3 under §14; device claims remain excluded |

## 8. Defect List

| # | ID | Severity | Description | Reproduction | Owner | Status |
|---|---|---|---|---|---|---|
| 1 | PQ-01 | P0 | Eight transition rows do not reach every declared target or preserve target-state bookkeeping. | Execute TR-05/06/08/10/14/16/17/19 using product controls; compare `#app[data-state]`, label, branch and visible target. | prototype_frontend_engineer | author patch implemented in Preview 2; fresh reviewer closure required |
| 2 | PQ-02 | P0 | All 39 binding chips emit generic mode copy; none demonstrates its declared target normal/fallback/error value. | Open audit, click each binding under all three data modes; bodies are identical except component/path heading. | prototype_frontend_engineer | author patch implemented in Preview 2; fresh reviewer closure required |
| 3 | PQ-03 | P0 | All 105 non-precedence variant/state selectors emit generic instructions instead of the row-specific UI delta. | Trigger every component variant/state; compare `#factStage` to Visual System 4 §6 expected deltas. | prototype_frontend_engineer | author patch implemented in Preview 2; fresh reviewer closure required |
| 4 | PQ-04 | P0 | EditOverlay has no live `TextObject3D`; style changes do not update a draft preview, and size/opacity input resets active font/color to sans/white. | Open New, choose handwritten+pink, move sliders; overlay has no TextObject3D and active style resets while Shared hero stays unchanged. | prototype_frontend_engineer | author patch implemented in Preview 2; fresh reviewer closure required |
| 5 | PQ-05 | P1 | Four render elements have selectors without a demonstrable visible/interactive result: halo, glow, previewTruth, hitVolume. | Inspect Shared/Stage/overlay/error/motion states; the first three remain hidden and hitVolume has no rendered target/trigger. | prototype_frontend_engineer | author patch implemented in Preview 2; fresh reviewer closure required |

## 8.1 Stage 14 Independent Reviewer Record

- Invocation: `prototype-qa-review-20260815-01`; context: `fresh_context`; reviewed Preview 1 / QA generation 1 / Interaction 7 / Visual System 4 / Composition 3 / Spatial Design System 2 / Visual Language 1 / Critique 6.
- Independent method: served the exact local file at `127.0.0.1:18765`, used the in-app browser, reloaded between state cases, clicked product controls, all audit selectors, all 117 binding-mode combinations, all 20 presets, all three tiers and Reduce Motion. Generation-side self-check was not used as verdict evidence.
- Rebuilt denominators: states 16; transitions 19; render elements 52; bindings 39; variants 48; component states 57; precedence 8; responsive/motion 4; presets 20. Every difference from the generation Manifest is 0.
- Device validation: `not_performed`. This record makes no PICO runtime, anchor persistence, physical comfort, hit precision, 60fps or parity claim.

### 8.1.1 States

| State | Source | Trigger | Actual | Verdict |
|---|---|---|---|---|
| S-01 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-01` | empty collection + preset/New visible | pass |
| S-02 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-02` | five rows + explicit Shared non-anchor preview | pass |
| S-03 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-03` | edit dialog and exact-text field visible | pass |
| S-04 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-04` | listening status/cancel visible after mic | pass |
| S-05 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-05` | final review status and recognized draft visible | pass |
| S-06 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-06` | style overlay visible; live preview defect tracked as PQ-04 | pass |
| S-07 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-07` | Stage disclosure dialog visible | pass |
| S-08 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-08` | Stage locate panel and Web boundary visible | pass |
| S-09 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-09` | candidate confirmation panel visible | pass |
| S-10 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-10` | five room text targets visible | pass |
| S-11 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-11` | direct state selector renders no unique selection/panel because selectedStageId is null | block |
| S-12 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-12` | delete dialog with exact phrase and two-store consequence visible | pass |
| S-13 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-13` | recovery panel with retry/re-place/exit visible | pass |
| S-14 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-14` | Room/journal source and repair action visible | pass |
| S-15 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-15` | voice reason + keyboard action visible | pass |
| S-16 | Interaction 7 §10.1 | fresh reload → `#stateSelect=S-16` | exit guard copy and return action visible | pass |

### 8.1.2 Transitions

| Transition | Source | Trigger | Actual | Verdict |
|---|---|---|---|---|
| TR-01 | Interaction 7 §10.2 | product control / declared event | New → app S-03/edit visible | pass |
| TR-02 | Interaction 7 §10.2 | product control / declared event | mic → app S-04/listening | pass |
| TR-03 | Interaction 7 §10.2 | product control / declared event | final callback → S-05/review | pass |
| TR-04 | Interaction 7 §10.2 | product control / declared event | simulated failure → S-15/reason | pass |
| TR-05 | Interaction 7 §10.2 | product control / declared event | keyboard focuses field but app/label remain S-15 | block |
| TR-06 | Interaction 7 §10.2 | product control / declared event | no confirmExactText action; Save skips S-06 and commits to S-02 | block |
| TR-07 | Interaction 7 §10.2 | product control / declared event | S-06 Save → S-02 and row appears | pass |
| TR-08 | Interaction 7 §10.2 | product control / declared event | room action opens disclosure but app/label remain S-02, not S-07 | block |
| TR-09 | Interaction 7 §10.2 | product control / declared event | dialog confirm → S-08 Stage locating | pass |
| TR-10 | Interaction 7 §10.2 | product control / declared event | locate button reaches S-10 only; no new-session S-09 branch | block |
| TR-11 | Interaction 7 §10.2 | product control / declared event | candidate Confirm → S-10 | pass |
| TR-12 | Interaction 7 §10.2 | product control / declared event | room text select → S-11 + AttachmentPanel | pass |
| TR-13 | Interaction 7 §10.2 | product control / declared event | Save and Cancel each detach panel → S-10 | pass |
| TR-14 | Interaction 7 §10.2 | product control / declared event | Delete opens blocking dialog but app/label remain S-11, not S-12 | block |
| TR-15 | Interaction 7 §10.2 | product control / declared event | confirm shows pending Anchor/Room then removes one text → S-10 | pass |
| TR-16 | Interaction 7 §10.2 | product control / declared event | error from S-08 reaches S-13; no any-Stage failure trigger | block |
| TR-17 | Interaction 7 §10.2 | product control / declared event | re-place reaches S-09; retry remains S-13 and repair→S-12 is absent | block |
| TR-18 | Interaction 7 §10.2 | product control / declared event | Stage exit button → S-16 guard | pass |
| TR-19 | Interaction 7 §10.2 | product control / declared event | guard confirm always returns S-02; S-06/S-14 return-token branches absent | block |

### 8.1.3 renderSpec reverse lookup

| Component.element | Source | Selector / trigger | Actual | Verdict |
|---|---|---|---|---|
| PhraseCollection.collectionTitle | VS4 §6.1 | `[data-preview-id="collectionTitle"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| PhraseCollection.collectionCount | VS4 §6.1 | `[data-preview-id="collectionCount"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| PhraseCollection.newAction | VS4 §6.1 | `[data-preview-id="newAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| PhraseCollection.rowList | VS4 §6.1 | `[data-preview-id="rowList"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| PhraseCollection.emptyAction | VS4 §6.1 | `[data-preview-id="emptyAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| PhraseCollection.repairAction | VS4 §6.1 | `[data-preview-id="repairAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| PhraseRow.stateShape | VS4 §6.2 | `[data-preview-id="stateShape"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| PhraseRow.phraseText | VS4 §6.2 | `[data-preview-id="phraseText"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| PhraseRow.styleSummary | VS4 §6.2 | `[data-preview-id="styleSummary"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| PhraseRow.stateLabel | VS4 §6.2 | `[data-preview-id="stateLabel"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| PhraseRow.editAction | VS4 §6.2 | `[data-preview-id="editAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| PhraseRow.roomAction | VS4 §6.2 | `[data-preview-id="roomAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| TextObject3D.frontGlyph | VS4 §6.3 | `[data-preview-id="frontGlyph"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| TextObject3D.backGlyph | VS4 §6.3 | `[data-preview-id="backGlyph"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| TextObject3D.sideDepth | VS4 §6.3 | `[data-preview-id="sideDepth"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| TextObject3D.halo | VS4 §6.3 | `[data-preview-id="halo"]` + owning state | selector exists but remained hidden in every exercised state | block |
| TextObject3D.glow | VS4 §6.3 | `[data-preview-id="glow"]` + owning state | selector exists but remained hidden in every exercised state | block |
| TextObject3D.hitVolume | VS4 §6.3 | `[data-preview-id="hitVolume"]` + owning state | selector exists, but it is a zero-result span with no visible/interactive trigger | block |
| TextObject3D.previewTruth | VS4 §6.3 | `[data-preview-id="previewTruth"]` + owning state | selector exists but remained hidden in every exercised state | block |
| EditOverlay.exactText | VS4 §6.4 | `[data-preview-id="exactText"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| EditOverlay.presetPicker | VS4 §6.4 | `[data-preview-id="presetPicker"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| EditOverlay.voiceControl | VS4 §6.4 | `[data-preview-id="voiceControl"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| EditOverlay.fontChoice | VS4 §6.4 | `[data-preview-id="fontChoice"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| EditOverlay.colorChoice | VS4 §6.4 | `[data-preview-id="colorChoice"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| EditOverlay.sizeSlider | VS4 §6.4 | `[data-preview-id="sizeSlider"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| EditOverlay.opacitySlider | VS4 §6.4 | `[data-preview-id="opacitySlider"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| EditOverlay.previewLabel | VS4 §6.4 | `[data-preview-id="previewLabel"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| EditOverlay.cancelAction | VS4 §6.4 | `[data-preview-id="cancelAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| EditOverlay.saveAction | VS4 §6.4 | `[data-preview-id="saveAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| VoiceInputControl.micAction | VS4 §6.5 | `[data-preview-id="micAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| VoiceInputControl.speechStatus | VS4 §6.5 | `[data-preview-id="speechStatus"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| VoiceInputControl.resultText | VS4 §6.5 | `[data-preview-id="resultText"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| VoiceInputControl.cancelListening | VS4 §6.5 | `[data-preview-id="cancelListening"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| VoiceInputControl.keyboardFallback | VS4 §6.5 | `[data-preview-id="keyboardFallback"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| AnchorTruthBadge.statusShape | VS4 §6.6 | `[data-preview-id="statusShape"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| AnchorTruthBadge.statusLabel | VS4 §6.6 | `[data-preview-id="statusLabel"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| AnchorTruthBadge.retryAction | VS4 §6.6 | `[data-preview-id="retryAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| StageAttachmentPanel.selectedSummary | VS4 §6.7 | `[data-preview-id="selectedSummary"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| StageAttachmentPanel.anchorBadge | VS4 §6.7 | `[data-preview-id="anchorBadge"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| StageAttachmentPanel.sizeControl | VS4 §6.7 | `[data-preview-id="sizeControl"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| StageAttachmentPanel.rollControl | VS4 §6.7 | `[data-preview-id="rollControl"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| StageAttachmentPanel.motionToggle | VS4 §6.7 | `[data-preview-id="motionToggle"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| StageAttachmentPanel.saveAction | VS4 §6.7 | `[data-preview-id="saveAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| StageAttachmentPanel.cancelAction | VS4 §6.7 | `[data-preview-id="cancelAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| StageAttachmentPanel.replaceAction | VS4 §6.7 | `[data-preview-id="replaceAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| StageAttachmentPanel.deleteAction | VS4 §6.7 | `[data-preview-id="deleteAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| DecisionDialog.dialogTitle | VS4 §6.8 | `[data-preview-id="dialogTitle"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| DecisionDialog.affectedText | VS4 §6.8 | `[data-preview-id="affectedText"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| DecisionDialog.consequence | VS4 §6.8 | `[data-preview-id="consequence"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| DecisionDialog.progress | VS4 §6.8 | `[data-preview-id="progress"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| DecisionDialog.cancelAction | VS4 §6.8 | `[data-preview-id="cancelAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |
| DecisionDialog.confirmAction | VS4 §6.8 | `[data-preview-id="confirmAction"]` + owning state | stable selector found and visible/conditionally demonstrated in the owning Shared/Stage/edit/dialog state | pass |

### 8.1.4 dataBindings normal / fallback / error

| Binding | Source | Trigger | Actual | Verdict |
|---|---|---|---|---|
| PhraseCollection.HealingText[] | VS4 §6.1 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| PhraseCollection.HealingText[].size | VS4 §6.1 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| PhraseCollection.DeleteJournal.pendingCount | VS4 §6.1 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| PhraseCollection.Room.loadState | VS4 §6.1 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| PhraseRow.HealingText.content | VS4 §6.2 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| PhraseRow.TextStyle.font/color | VS4 §6.2 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| PhraseRow.AnchorLink.state | VS4 §6.2 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| PhraseRow.DeleteJournal.state | VS4 §6.2 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| TextObject3D.HealingText.content | VS4 §6.3 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| TextObject3D.TextStyle.font | VS4 §6.3 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| TextObject3D.TextStyle.color/opacity/size/depth | VS4 §6.3 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| TextObject3D.AnchorRuntimeState.pose | VS4 §6.3 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| TextObject3D.MotionPreference | VS4 §6.3 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| TextObject3D.RenderCache.key/state | VS4 §6.3 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| EditOverlay.InputDraft.content/validity | VS4 §6.4 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| EditOverlay.PresetPhrase[] | VS4 §6.4 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| EditOverlay.TextStyle.* | VS4 §6.4 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| EditOverlay.MotionPreference | VS4 §6.4 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| EditOverlay.FontAvailability | VS4 §6.4 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| VoiceInputControl.SpeechSession.support | VS4 §6.5 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| VoiceInputControl.SpeechSession.permission | VS4 §6.5 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| VoiceInputControl.SpeechSession.partial/final | VS4 §6.5 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| VoiceInputControl.SpeechSession.errorReason | VS4 §6.5 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| VoiceInputControl.InputDraft.content | VS4 §6.5 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| AnchorTruthBadge.AnchorRuntimeState | VS4 §6.6 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| AnchorTruthBadge.AnchorLink.lastLocatedAt | VS4 §6.6 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| AnchorTruthBadge.DeleteJournal.state | VS4 §6.6 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| AnchorTruthBadge.currentSpaceState | VS4 §6.6 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| StageAttachmentPanel.selection.localId/uuid | VS4 §6.7 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| StageAttachmentPanel.HealingText.content | VS4 §6.7 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| StageAttachmentPanel.TextStyle.size/initialRoll | VS4 §6.7 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| StageAttachmentPanel.AnchorRuntimeState | VS4 §6.7 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| StageAttachmentPanel.MotionPreference | VS4 §6.7 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| StageAttachmentPanel.operation journal | VS4 §6.7 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| DecisionDialog.dialogVariant | VS4 §6.8 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| DecisionDialog.HealingText.content | VS4 §6.8 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| DecisionDialog.DeleteJournal.anchorStep/roomStep | VS4 §6.8 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| DecisionDialog.operation journal | VS4 §6.8 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |
| DecisionDialog.return token | VS4 §6.8 | matching audit chip × Normal/Fallback/Error | all three modes show only the same mode-wide generic sentence; no target-specific value/fallback/error mutation | block |

### 8.1.5 Variants, component states and precedence

| Component fact | Source | Trigger | Actual | Verdict |
|---|---|---|---|---|
| PhraseCollection.variant.Empty | VS4 §6.1 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| PhraseCollection.variant.Filled | VS4 §6.1 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| PhraseCollection.variant.RepairPending | VS4 §6.1 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| PhraseCollection.variant.Compact | VS4 §6.1 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| PhraseCollection.variant.Large | VS4 §6.1 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| PhraseCollection.state.default | VS4 §6.1 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseCollection.state.focused | VS4 §6.1 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseCollection.state.loading | VS4 §6.1 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseCollection.state.empty | VS4 §6.1 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseCollection.state.error | VS4 §6.1 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseCollection.state.overflow | VS4 §6.1 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseCollection.state.disabled | VS4 §6.1 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseCollection.precedence | VS4 §6.1 | component + stacking precedence | exact component precedence sentence appears | pass |
| PhraseRow.variant.LocalOnly | VS4 §6.2 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| PhraseRow.variant.LocatedLastSession | VS4 §6.2 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| PhraseRow.variant.NeedsObservation | VS4 §6.2 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| PhraseRow.variant.RepairPending | VS4 §6.2 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| PhraseRow.variant.Selected | VS4 §6.2 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| PhraseRow.state.default | VS4 §6.2 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseRow.state.focused | VS4 §6.2 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseRow.state.selected | VS4 §6.2 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseRow.state.pressed | VS4 §6.2 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseRow.state.disabled | VS4 §6.2 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseRow.state.error/repair | VS4 §6.2 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseRow.state.overflow | VS4 §6.2 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| PhraseRow.precedence | VS4 §6.2 | component + stacking precedence | exact component precedence sentence appears | pass |
| TextObject3D.variant.SharedPreview | VS4 §6.3 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| TextObject3D.variant.StageLocated | VS4 §6.3 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| TextObject3D.variant.StageCandidate | VS4 §6.3 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| TextObject3D.variant.TextureBoxFallback | VS4 §6.3 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| TextObject3D.variant.ReduceMotion | VS4 §6.3 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| TextObject3D.variant.StaticPerformanceFallback | VS4 §6.3 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| TextObject3D.state.default | VS4 §6.3 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| TextObject3D.state.focused | VS4 §6.3 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| TextObject3D.state.selected | VS4 §6.3 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| TextObject3D.state.dragging | VS4 §6.3 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| TextObject3D.state.loading | VS4 §6.3 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| TextObject3D.state.unlocated | VS4 §6.3 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| TextObject3D.state.error | VS4 §6.3 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| TextObject3D.state.performance | VS4 §6.3 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| TextObject3D.precedence | VS4 §6.3 | component + stacking precedence | exact component precedence sentence appears | pass |
| EditOverlay.variant.Create | VS4 §6.4 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| EditOverlay.variant.EditExisting | VS4 §6.4 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| EditOverlay.variant.PresetStart | VS4 §6.4 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| EditOverlay.variant.Compact | VS4 §6.4 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| EditOverlay.variant.FontFallback | VS4 §6.4 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| EditOverlay.variant.ReduceMotion | VS4 §6.4 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| EditOverlay.state.default | VS4 §6.4 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| EditOverlay.state.editing | VS4 §6.4 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| EditOverlay.state.invalid | VS4 §6.4 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| EditOverlay.state.loading | VS4 §6.4 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| EditOverlay.state.error | VS4 §6.4 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| EditOverlay.state.overflow | VS4 §6.4 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| EditOverlay.state.disabled | VS4 §6.4 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| EditOverlay.precedence | VS4 §6.4 | component + stacking precedence | exact component precedence sentence appears | pass |
| VoiceInputControl.variant.Idle | VS4 §6.5 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| VoiceInputControl.variant.CheckingSupport | VS4 §6.5 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| VoiceInputControl.variant.PermissionPrompt | VS4 §6.5 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| VoiceInputControl.variant.Listening | VS4 §6.5 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| VoiceInputControl.variant.Partial | VS4 §6.5 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| VoiceInputControl.variant.FinalReview | VS4 §6.5 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| VoiceInputControl.variant.Fallback | VS4 §6.5 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| VoiceInputControl.state.idle | VS4 §6.5 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| VoiceInputControl.state.checking | VS4 §6.5 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| VoiceInputControl.state.permission | VS4 §6.5 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| VoiceInputControl.state.listening | VS4 §6.5 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| VoiceInputControl.state.partial | VS4 §6.5 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| VoiceInputControl.state.final | VS4 §6.5 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| VoiceInputControl.state.failures | VS4 §6.5 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| VoiceInputControl.state.destroyed | VS4 §6.5 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| VoiceInputControl.precedence | VS4 §6.5 | component + stacking precedence | exact component precedence sentence appears | pass |
| AnchorTruthBadge.variant.LocalOnly | VS4 §6.6 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| AnchorTruthBadge.variant.Located | VS4 §6.6 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| AnchorTruthBadge.variant.NeedsObservation | VS4 §6.6 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| AnchorTruthBadge.variant.Missing | VS4 §6.6 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| AnchorTruthBadge.variant.Pending | VS4 §6.6 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| AnchorTruthBadge.variant.Repair | VS4 §6.6 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| AnchorTruthBadge.variant.Permission | VS4 §6.6 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| AnchorTruthBadge.variant.Error | VS4 §6.6 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| AnchorTruthBadge.state.default | VS4 §6.6 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| AnchorTruthBadge.state.focused | VS4 §6.6 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| AnchorTruthBadge.state.pressed | VS4 §6.6 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| AnchorTruthBadge.state.disabled | VS4 §6.6 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| AnchorTruthBadge.state.aging | VS4 §6.6 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| AnchorTruthBadge.state.error/repair | VS4 §6.6 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| AnchorTruthBadge.precedence | VS4 §6.6 | component + stacking precedence | exact component precedence sentence appears | pass |
| StageAttachmentPanel.variant.Transform | VS4 §6.7 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| StageAttachmentPanel.variant.StyleSummary | VS4 §6.7 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| StageAttachmentPanel.variant.ReduceMotion | VS4 §6.7 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| StageAttachmentPanel.variant.RepairPending | VS4 §6.7 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| StageAttachmentPanel.variant.ControllerFocused | VS4 §6.7 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| StageAttachmentPanel.state.default | VS4 §6.7 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| StageAttachmentPanel.state.focused | VS4 §6.7 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| StageAttachmentPanel.state.editing | VS4 §6.7 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| StageAttachmentPanel.state.saving | VS4 §6.7 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| StageAttachmentPanel.state.error | VS4 §6.7 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| StageAttachmentPanel.state.disabled | VS4 §6.7 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| StageAttachmentPanel.state.overflow | VS4 §6.7 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| StageAttachmentPanel.precedence | VS4 §6.7 | component + stacking precedence | exact component precedence sentence appears | pass |
| DecisionDialog.variant.StageConsent | VS4 §6.8 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| DecisionDialog.variant.DeleteConfirm | VS4 §6.8 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| DecisionDialog.variant.DeleteRepair | VS4 §6.8 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| DecisionDialog.variant.StageExitGuard | VS4 §6.8 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| DecisionDialog.variant.RePlaceConfirm | VS4 §6.8 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| DecisionDialog.variant.PermissionExplanation | VS4 §6.8 | component + variant selector | factStage names the choice but shows the shared generic instruction; expected row-specific element/layout/binding/motion delta is not rendered | block |
| DecisionDialog.state.default | VS4 §6.8 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| DecisionDialog.state.focused | VS4 §6.8 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| DecisionDialog.state.confirming | VS4 §6.8 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| DecisionDialog.state.partial | VS4 §6.8 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| DecisionDialog.state.error | VS4 §6.8 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| DecisionDialog.state.disabled | VS4 §6.8 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| DecisionDialog.state.overflow | VS4 §6.8 | component + state selector | factStage names the choice but shows the shared generic instruction; expected row-specific visual/behavior/accessibility result is not rendered | block |
| DecisionDialog.precedence | VS4 §6.8 | component + stacking precedence | exact component precedence sentence appears | pass |

### 8.1.6 Responsive / motion and presets

| Fact | Source | Trigger | Actual | Verdict |
|---|---|---|---|---|
| Large / max | Interaction 7 §9 | Max | grid columns 544px + flexible preview; window min-height 920px; 56px New target | pass |
| Compact / default | Interaction 7 §9 | Default | grid columns 440px + flexible preview; window min-height 760px | pass |
| Constrained / min | Interaction 7 §9 | Min | layout becomes block; preview hidden for collection-primary view; window min-height 624px; 56px target retained | pass |
| Reduce Motion | Interaction 7 §13 / VS4 §9 | motion button | body/app flags change false→true; text animation duration changes 8s/6s→0.001ms | pass |
| 20 presets | VS4 §11 | click each preset | 20/20 exact strings populate exactText, set PresetStart and enable Save | pass |

### 8.1.7 Bounded patch goals

1. **PQ-01**: implement each missing/partial transition branch with correct `#app[data-state]`, visible target task, cancellation, confirmation, and return-token outcome; never use direct state selection as transition evidence.
2. **PQ-02**: replace generic binding assertions with 39 binding-specific target demonstrations; each must mutate its declared target under normal and fallback/error and preserve semantic color+shape+human label where applicable.
3. **PQ-03**: make all 105 variant/state triggers render their exact Visual System 4 §6 element, layout, binding, motion and accessibility deltas; retain the 8 exact precedence assertions.
4. **PQ-04**: add a bounded live draft TextObject3D inside EditOverlay; keep font/color/size/opacity state stable across every control change and bind it to the draft, not the previously selected phrase.
5. **PQ-05**: provide explicit audit triggers/results for halo, glow, hitVolume and previewTruth, or correct the upstream denominator through valid change control; selectors alone do not count.

- Recommendation: `block / patch_required`.
- Stage 15: prohibited. Any Preview implementation-input patch invalidates Preview 1 / QA 2 and requires a new Stage 13 build plus a fresh Stage 14 independent review.

## 9. Delivery and Recipients

- Generation deliverables: `preview/preview.html` rev 2 and this postPatch generation-side QA source record rev 3; rev 2 independent findings remain preserved.
- Run command: from `work/design-package/preview`, run `python -m http.server 4173`, then open `http://127.0.0.1:4173/preview.html`.
- Recipients: isolated `prototype_qa_reviewer`, then Design Lead/PM/engineering after later gates.
- Stage 14 completed as invocation `prototype-qa-review-20260815-01`; the independent recount and itemized actual/verdict evidence are in §8.1, `deviceValidation.status=not_performed`, and the recommendation is `block / patch_required`.

## 10. Generation-side Self-check (not Stage 14 evidence)

| Check | Result | Boundary |
|---|---|---|
| JavaScript parse | `JS_PARSE_OK` with local Node.js | syntax only, not independent QA |
| Static source | 46,292 bytes; 69 `data-preview-id` occurrences; exact revision/scope comment present | source inspection only |
| Declarative counts | 35 state/transition rows; 52 element rows; 39 binding rows; 113 variant/state/precedence rows; 4 responsive/motion rows; 20 preset literals | generator recount only |
| HTTP serving | `GET /preview.html` returned HTTP 200; scope and Persistent Anchor boundary copy present | local transport only |
| Headless interaction attempt | not performed because the bundled Playwright package has no installed browser executable | not a defect closure and not a Stage 14 review; isolated reviewer must use an available browser or manual interaction |

No runtime, device, screenshot-parity, comfort or performance conclusion was produced.

## 11. CR-PQ-01-05 PostPatch Generation Record · Preview rev 2

> This is a bounded `prototype_frontend_engineer` record, not a reviewer verdict. It preserves Preview QA rev 2 §8.1 and Critique rev 7 as finding origin, invalidates their approval relevance for changed Preview facts, and requires a fresh Stage 14 invocation.

### 11.1 Exact patch mapping

| Finding | Preview 2 implementation anchors | Generator-observable result | Status |
|---|---|---|---|
| PQ-01 | `setAppState`, `#confirmTextAction`, `openDialog`, `returnToken`, `[data-action=new-anchor/runtime-failure/retry-locate/repair-delete/save-exit]`, `returnFromStage`, S-11 direct selector initialization | TR-05 now sets S-03; TR-06 has exact-text confirm→S-06; TR-08/14 set S-07/S-12; TR-10 has S-09 and S-10 branches; every Stage surface exposes failure→S-13; recovery exposes S-08/S-09/S-12; return tokens visibly route S-02/S-06/S-14 | implemented; reviewer rerun required |
| PQ-02 | `bindingFacts[39]`, `renderBinding`, `[data-binding-target]`, `[data-binding-value]`, data-mode buttons | each binding owns a distinct target plus distinct normal/fallback/error sample; semantic items render shape + color + human label | implemented; reviewer rerun required |
| PQ-03 | `componentFacts.*.variantDeltas[48]`, `stateDeltas[57]`, eight preserved `precedence`, `renderFact`, `#factStage[data-fact-id][data-layout][data-motion][data-tone]` | every non-precedence choice emits its exact row-specific delta plus visible shape/tone/layout/motion/accessibility evidence; precedence sentences remain exact | implemented; reviewer rerun required |
| PQ-04 | `#draftPreview`, `draftStyle`, `renderDraftPreview`, `syncEdit`, `enterStyleState`, `commitDraft` | live draft TextObject follows InputDraft; font/color/size/opacity persist independently across typing, presets, voice and sliders; Save commits the current draft, not the old Shared hero | implemented; reviewer rerun required |
| PQ-05 | `.contrast-halo[data-preview-id=halo]`, `.glow-layer[data-preview-id=glow]`, `.object-truth[data-preview-id=previewTruth]`, `.hit-volume[data-preview-id=hitVolume]` | halo/glow/truth are visibly rendered; hitVolume exposes focus/hover boundary and click-to-select result | implemented; reviewer rerun required |

### 11.2 PostPatch generator checks

| Check | Result | Boundary |
|---|---|---|
| JavaScript parse | `JS_PARSE_OK` | syntax only |
| Denominator data integrity | 48 variants = 48 variant deltas; 57 states = 57 state deltas; 8 precedence; 39 bindings all have component/path/target/normal/fallback/error | generator static recount |
| HTTP | Preview rev 2 served with HTTP 200; patch anchors present | local transport/source only |
| Browser interaction | unavailable in this author context: Browser runtime reported no available browser; no substitute reviewer conclusion was fabricated | fresh Stage 14 must interact independently |
| Upstream mutation | none | PM3 / UXR3 / Interaction7 / Visual Language1 / Composition3 / Spatial DS2 / Visual4 unchanged |
| Device validation | `not_performed` | real 3D, Persistent Anchor, comfort, hit precision, 60fps and parity remain outside Web preview |

### 11.3 Fresh Stage 14 target

- Reviewer: isolated `prototype_qa_reviewer`, `fresh_context`.
- Exact revisions: Preview 2 + Preview QA generation record 3 + Interaction 7 + Visual System 4 + Composition 3 + Spatial Design System 2 + Visual Language 1 + Critique 7 finding origin + current trace.
- Required rebuild: 16 states; 19 product transitions; 52 render demonstrations; 39×3 binding target samples; 48 variants + 57 states + 8 precedence; four responsive/motion scenarios; live draft stability across all style controls; device status fixed `not_performed`.
- Stage 15 remains prohibited until that fresh Stage 14 review passes.

## 12. Fresh Stage 14 independent rerun · Preview QA revision 4

### 12.1 Invocation and exact target

| Field | Actual |
|---|---|
| reviewerRole | `prototype_qa_reviewer` |
| invocationId | `prototype-qa-review-20260816-02` |
| contextPolicy | `fresh_context` |
| reviewedRevision | Preview 2 SHA-256 `2728bd2dca598f9eec63e0bfbb6efd51b6a8b0820fd80a95ada9e5cb939f1911`; QA generation 3 SHA-256 `b4997def85ed0aa64dc044436a3a10b86b4ee79efb0d34bb16acd5ab94bdc8f7`; Critique 7 finding origin; Interaction 7 + Visual 4 + Composition 3 + Spatial DS 2 + Visual Language 1 |
| evidenceRebuilt | yes — independently recounted source facts and served/interacted with the exact Preview in the in-app Browser; generator verdicts were not reused |
| validation boundary | `web_design_validation_only`; `deviceValidation.status=not_performed` |
| recommendation | `block / patch_required`; Stage 15 prohibited |

### 12.2 Denominators and actuals

| Type | Design / Manifest / QA | Difference | Browser actual | Verdict |
|---|---:|---:|---|---|
| states | 16 / 16 / 16 | 0 | 16/16 reachable; S-05 verified by real TR-03 final path | pass |
| transitions | 19 / 19 / 19 | 0 | 19/19 product triggers; TR-17 all three targets and TR-19 S-02/S-06/S-14 tokens replayed | pass |
| render elements | 52 / 52 / 52 | 0 | 52/52 scoped selectors visible or conditionally revealed | pass |
| bindings | 39 / 39 / 39 | 0 | 117/117 binding×normal/fallback/error cases show exact target and value | pass |
| variants | 48 / 48 / 48 | 0 | **0/48**; every variant trigger renders a component-state fact | **block** |
| component states | 57 / 57 / 57 | 0 | 57/57 exact state facts | pass |
| precedence | 8 / 8 / 8 | 0 | 8/8 exact precedence sentences | pass |
| responsive / motion | 4 / 4 / 4 | 0 | 4/4 structural changes | pass |
| presets | 20 / 20 / 20 | 0 | 20/20 exact draft copy, no autosave | pass |

### 12.3 All 19 transition rows

| Transition | Trigger | Expected | Actual | Verdict |
|---|---|---|---|---|
| TR-01 | New | S-02→S-03 | exact draft overlay | pass |
| TR-02 | mic | S-03→S-04 | checking/listening | pass |
| TR-03 | final callback | S-04→S-05 | final phrase visible | pass |
| TR-04 | simulated speech failure | S-05→S-15 | reason + keyboard fallback | pass |
| TR-05 | keyboard fallback | S-15→S-03 | preserved draft field | pass |
| TR-06 | exact-text confirm | S-03→S-06 | live style state | pass |
| TR-07 | local save | S-06→S-02 | sixth row visible | pass |
| TR-08 | room action | S-02→S-07 | Stage disclosure Dialog | pass |
| TR-09 | enter confirm | S-07→S-08 | locating surface | pass |
| TR-10 | new/re-place | S-08→S-09/S-10 | S-09 candidate branch replayed; S-10 control present | pass |
| TR-11 | pose confirm | S-09→S-10 | five-plus room texts | pass |
| TR-12 | room-text select | S-10→S-11 | unique AttachmentPanel | pass |
| TR-13 | transform save | S-11→S-10 | panel detached | pass |
| TR-14 | delete | S-11→S-12 | exact identity + two-store consequence | pass |
| TR-15 | delete confirm | S-12→S-10 | blocking Anchor/Room progress before return | pass |
| TR-16 | runtime failure | any Stage→S-13 | recovery actions visible | pass |
| TR-17 | retry / re-place / repair | S-13→S-08/S-09/S-12 | all three targets separately observed | pass |
| TR-18 | Stage exit | any Stage→S-16 | five safe choices + token | pass |
| TR-19 | save-exit | S-16→S-02/S-06/S-14 | all three exact return destinations observed | pass |

### 12.4 Per-component render/binding actuals

| Component | `renderSpec` item actuals | Binding actuals | Verdict |
|---|---|---|---|
| PhraseCollection | `collectionTitle`, `collectionCount`, `newAction`, `rowList`, `emptyAction`, `repairAction` = 6/6 | four bindings × three modes = 12/12 | pass |
| PhraseRow | `stateShape`, `phraseText`, `styleSummary`, `stateLabel`, `editAction`, `roomAction` = 6/6 | 12/12 | pass |
| TextObject3D | `frontGlyph`, `backGlyph`, `sideDepth`, `halo`, `glow`, `hitVolume`, `previewTruth` = 7/7 | 18/18 | pass |
| EditOverlay | ten source element IDs = 10/10 | 15/15 | pass |
| VoiceInputControl | five source element IDs = 5/5 | 15/15 | pass |
| AnchorTruthBadge | three source element IDs = 3/3 | 12/12 | pass |
| StageAttachmentPanel | nine source element IDs = 9/9 | 18/18 | pass |
| DecisionDialog | six source element IDs = 6/6; progress revealed after confirm | 15/15 | pass |

Each binding was selected by its own `.binding-chip`, then Normal/Fallback/Error were clicked. `#factStage[data-binding][data-binding-target][data-data-mode]`, child target and value agreed for all 117 cases; examples include `AnchorRuntimeState.pose → AnchorRoot transform` and `return token → post-close destination`.

### 12.5 Variant/state/precedence actuals

| Component | Variant actual | Component states | Precedence | Verdict |
|---|---|---:|---:|---|
| PhraseCollection | 0/5; all return `PhraseCollection.state.default` | 7/7 | 1/1 | block |
| PhraseRow | 0/5; all return `PhraseRow.state.default` | 7/7 | 1/1 | block |
| TextObject3D | 0/6; all return `TextObject3D.state.default` | 8/8 | 1/1 | block |
| EditOverlay | 0/6; all return `EditOverlay.state.default` | 7/7 | 1/1 | block |
| VoiceInputControl | 0/7; all return `VoiceInputControl.state.idle` | 8/8 | 1/1 | block |
| AnchorTruthBadge | 0/8; all return `AnchorTruthBadge.state.default` | 6/6 | 1/1 | block |
| StageAttachmentPanel | 0/5; all return `StageAttachmentPanel.state.default` | 7/7 | 1/1 | block |
| DecisionDialog | 0/6; all return `DecisionDialog.state.default` | 7/7 | 1/1 | block |

Root-cause observation only: the visible fact renderer always chooses `stateDeltas[si]` while a component-state option exists, so all 48 `variantDeltas[vi]` are unreachable.

### 12.6 PQ-04/PQ-05 and responsive actuals

| Check | Expected | Actual | Verdict |
|---|---|---|---|
| draft content stability | content survives slider changes | “今天也请温柔一点” stayed exact from 112%/72% to 125%/65% | pass |
| draft color/opacity | selected values render | `#FFB6C1` and `0.65` stayed in live style | pass |
| draft font/size | hand font and `--preview-size:1.25` render | inline style ends at `--phrase-font:`; font and following size variable are absent | **block** |
| halo/glow/truth/hit | visible, triggerable | rgba halo, 32px glow blur/0.15 opacity, truth label, 506×233px hit boundary; click focuses/selects | pass |
| Large / Compact / Constrained | structural reflow | Max 544px grid; Default 440px grid; Min block with preview hidden; 56px target retained | pass |
| Reduce Motion | remove displacement, keep semantics | `data-reduce-motion=true`; duration `1e-06s`; color/truth retained | pass |

### 12.7 Finding closure and hard gate

| Finding | Fresh actual | Status |
|---|---|---|
| PQ-01 | 19/19 transitions | closed |
| PQ-02 | 117/117 target-specific binding cases | closed |
| PQ-03 | states 57/57 and precedence 8/8 pass; variants 0/48 | **open / block** |
| PQ-04 | content/color/opacity stable; font and size not rendered | **open / block** |
| PQ-05 | four effects demonstrated | closed |

| Field | Value |
|---|---|
| previewImplementationFidelity | block |
| minimumCompletenessGate | block for the reviewed Preview package |
| designStatusImpact | invalid |
| deviceValidation.status | not_performed |
| next action | bounded Preview-only patch for PQ-03 and PQ-04; then new Stage 13 and fresh Stage 14 rerun; Stage 15 prohibited |

## 13. CR-PQ-03R-04R PostPatch Generation Record · Preview rev 3

> Author-side generation evidence only. This section does not overwrite or self-close the independent findings in §12 and is not a Stage 14 verdict.

### 13.1 Exact bounded patch mapping

| Finding | Preview rev 3 implementation | Author-side targeted result | Gate status |
|---|---|---|---|
| PQ-03R | `currentFactKind`, `resolveFact(component, kind, value)`, explicit `renderFact('variant')`, explicit state/precedence event route, `#factStage[data-fact-kind][data-fact-id]` | every variant selector maps to its own `variantDeltas[i]` and `Component.variant.Value` key: 48/48; state route remains 57/57; precedence route remains 8/8 | author implemented; fresh reviewer required |
| PQ-04R | removed `fontValue()` and `--phrase-font` interpolation; `fontClass()` emits only `font-sans` / `font-serif` / `font-hand`; class rules own font-family; inline style now contains only color, opacity and `--preview-size`; Shared hero, live draft and Stage sample use the safe font token route | three fonts and three size samples produce intact output; content/color/opacity/size fields remain present together; no quoted font value can truncate the style attribute | author implemented; fresh reviewer required |

No PM, UXR, Interaction, Visual Language, Spatial Composition, Spatial Design System or Visual System fact/value/layout was changed. PQ-01/PQ-02/PQ-05 implementations and the already-passing transition, element, binding, state, precedence, responsive/motion and preset surfaces were not redesigned.

### 13.2 PostPatch generator checks

| Check | Author-side actual | Boundary |
|---|---|---|
| JavaScript parse | `JS_PARSE_OK` | syntax only |
| Explicit audit routing | `FACT_ROUTE_OK variants=48 states=57 precedence=8`; for every row `resolveFact` returned the matching source delta and kind-specific key | deterministic source execution; not browser interaction |
| Font/style safety | `fontValue(` count 0; `--phrase-font:` count 0; three safe font classes present; `data-font-intent` and `data-preview-size` emitted | deterministic source/string execution |
| Live draft / Shared renderer samples | `LIVE_DRAFT_RENDER_ROUTE_OK fonts=3 sizes=3 contentStable=3 colorOpacityStable=3` | renderer output samples; independent DOM/computed-style replay still required |
| HTTP | exact Preview rev 3 served from localhost with HTTP 200 and revision marker present | local transport/source only |
| Prior-surface static regression | component facts remain 48 variants / 57 states / 8 precedence; no transition, renderSpec, binding, responsive/motion or preset design denominator changed | author scope assertion; reviewer must replay prior passes |
| Interactive Browser | not performed in this author context; no independent conclusion fabricated | fresh Stage 14 must serve and interact with exact Preview rev 3 |
| Device validation | `not_performed` | real 3D, Persistent Anchor, comfort, hit precision, 60fps and Web/PICO parity remain out of scope |

### 13.3 Active provenance and fresh Stage 14 target

- Active generated artifacts: Preview rev 3 SHA-256 `c8ee3f4a2167aff882d56af1b12872c0931d6ecf931c72815baa4a203247115f`; this Preview QA generation record rev 5.
- Finding origin: Preview QA rev 4 §12 + Design Critique rev 8 PQ-03R/PQ-04R. Their Preview 2 approval conclusion is invalidated for changed implementation facts, but all observations remain preserved.
- Exact unchanged design sources: Interaction 7 + Visual System 4 + Composition 3 + Spatial Design System 2 + Visual Language 1; Critique 6 remains the Stage 12 pass.
- Next reviewer: isolated `prototype_qa_reviewer`, `fresh_context`, using Preview 3 + Preview QA generation 5 + Critique 8 finding origin + exact unchanged design sources + current trace.
- Required rebuild: PQ-03R 48 variant triggers plus regression of 57 states and 8 precedence; PQ-04R three fonts and size on both live draft and Shared hero while content/color/opacity/size survive successive controls; regress the prior passes for 19 transitions, 52 elements, 117 binding cases, four responsive/motion scenarios and 20 presets. Keep `deviceValidation.status=not_performed`.
- Stage 15 remains prohibited until this fresh Stage 14 returns pass.

## 14. Third fresh Stage 14 independent rerun · Preview QA revision 6

### 14.1 Invocation, target and boundary

| Field | Actual | Verdict |
|---|---|---|
| reviewer | `prototype_qa_reviewer`; `prototype-qa-review-20260816-03`; `fresh_context` | pass independence |
| exact target | Preview rev 3 SHA-256 `c8ee3f4a2167aff882d56af1b12872c0931d6ecf931c72815baa4a203247115f`; QA generation rev 5 SHA-256 `53d9310aeafe52b6a45038143309ed231dfc99f07dcfa9b9e61ef7d5f39b3d26`; Critique rev 8 finding origin; Interaction 7 + Visual 4 + Composition 3 + Spatial DS 2 + Visual Language 1 | pass freshness |
| independent method | localhost port `8793`; in-app Browser; validation台先展开再逐项触发；作者 self-check 未用作 verdict | pass |
| boundary | `web_design_validation_only`; `deviceValidation.status=not_performed` | pass boundary |

### 14.2 Rebuilt denominator and browser actuals

| Type | Design / Manifest / QA | Browser actual | Verdict |
|---|---:|---|---|
| top-level states | 16 / 16 / 16 | 16/16：每个 `#app[data-state]` 与独立可见主任务 marker 匹配 | pass |
| transitions | 19 / 19 / 19 | 19/19 product triggers；TR-17 的 S-08/S-09/S-12 与 TR-19 的 S-02/S-06/S-14 分支均实际到达；Stage/删除 Dialog 的 cancel+confirm 均回放 | pass |
| renderSpec elements | 52 / 52 / 52 | 52/52；按组件为 `6+6+7+10+5+3+9+6`，条件元素在 S-01/S-06/listening/S-08/S-11/S-12-confirming 中显露 | pass |
| dataBindings | 39 / 39 / 39 | 117/117；每个 `.binding-chip` 的 normal/fallback/error 均使 `#factStage[data-binding][data-binding-target][data-data-mode]` 与非空 target/value 一致 | pass |
| variants | 48 / 48 / 48 | 48/48 visible deltas；每项 `data-fact-kind=variant` 且 `data-fact-id=Component.variant.Value` | pass |
| component states | 57 / 57 / 57 | 57/57 visible state deltas；每项 exact state id | pass |
| precedence | 8 / 8 / 8 | 8/8 exact precedence sentences | pass |
| responsive / motion | 4 / 4 / 4 | Max: 544px grid；Default: 440px grid；Min: block reflow/preview hidden；均保留 56px target、无整体 transform；Reduce Motion `8s,6s → 1e-06s` 且颜色/真值标签不变 | pass |
| presets | 20 / 20 / 20 | 20/20 逐击后 live draft 与 exact bundled copy 一致、`PresetStart`，行数保持 5→5（未自动保存） | pass |

### 14.3 PQ-03R itemized component actuals

| Component | Variant actual | State actual | Precedence actual | Verdict |
|---|---|---|---|---|
| PhraseCollection | Empty/Filled/RepairPending/Compact/Large = 5/5 distinct variant ids/deltas | 7/7 | 1/1 | pass |
| PhraseRow | LocalOnly/LocatedLastSession/NeedsObservation/RepairPending/Selected = 5/5 | 7/7 | 1/1 | pass |
| TextObject3D | SharedPreview/StageLocated/StageCandidate/TextureBoxFallback/ReduceMotion/StaticPerformanceFallback = 6/6 | 8/8 | 1/1 | pass |
| EditOverlay | Create/EditExisting/PresetStart/Compact/FontFallback/ReduceMotion = 6/6 | 7/7 | 1/1 | pass |
| VoiceInputControl | Idle/CheckingSupport/PermissionPrompt/Listening/Partial/FinalReview/Fallback = 7/7 | 8/8 | 1/1 | pass |
| AnchorTruthBadge | LocalOnly/Located/NeedsObservation/Missing/Pending/Repair/Permission/Error = 8/8 | 6/6 | 1/1 | pass |
| StageAttachmentPanel | Transform/StyleSummary/ReduceMotion/RepairPending/ControllerFocused = 5/5 | 7/7 | 1/1 | pass |
| DecisionDialog | StageConsent/DeleteConfirm/DeleteRepair/StageExitGuard/RePlaceConfirm/PermissionExplanation = 6/6 | 7/7 | 1/1 | pass |

### 14.4 PQ-04R live-draft and Shared-hero actuals

| Combination | Live draft actual | Shared hero after Save | Verdict |
|---|---|---|---|
| Sans / 70% / white / 95% | `font-sans`; `Inter, Noto Sans SC, sans-serif`; style contains `#FFFFFF`, `0.95`, `0.7`; exact content retained | same class/font/content/style | pass |
| Serif / 100% / warm / 80% | `font-serif`; `Georgia, Noto Serif SC, serif`; style contains `#FFE4B5`, `0.8`, `1`; exact content retained | same class/font/content/style | pass |
| Hand / 135% / pink / 65% | `font-hand`; `KaiTi, STKaiti, cursive`; style contains `#FFB6C1`, `0.65`, `1.35`; exact content retained | same class/font/content/style | pass |

All six inspected style strings contained color + opacity + size together, contained no `--phrase-font`, and did not truncate. Content, color and opacity survived successive font/size changes.

### 14.5 Finding closure and active gate

| Finding | Fresh actual | Status |
|---|---|---|
| PQ-01 | 19/19 transitions, including branch/return-token regressions | closed |
| PQ-02 | 117/117 target-specific binding cases | closed |
| PQ-03R | 48/48 variants plus 57/57 states and 8/8 precedence | closed |
| PQ-04R | three font/size combinations on live draft and Shared hero; stable untruncated style | closed |
| PQ-05 | 52/52 elements, including halo/glow/truth/hitVolume conditional evidence | closed |

| Field | Active value |
|---|---|
| previewImplementationFidelity | pass |
| minimumCompletenessGate | pass for active Preview rev 3 / QA rev 6 |
| designStatusImpact | none |
| deviceValidation.status | not_performed |
| recommendation | pass; Stage 15 allowed after this Stage 14 receipt is closed; this review does not enter Stage 15 |
