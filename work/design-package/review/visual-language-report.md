# 视觉语言报告 · 悬浮文字疗愈空间

> Role: `visual_designer` | Active revision: 1 | Workflow coverage: Stage 8 `visual_direction`
>
> Sources: Interaction / Spatial Spec rev 2 selected H-A, PM rev 3, UXR rev 2 §3A, Design Critique rev 3 Stage 7 pass. This report selects an approved visual reference through a structured design-effect review; it does not contain generated imagery or external visual assets.

## 1. Visual intent and non-negotiable truth

- The experience must feel minimal, warm, quiet, and spatially legible without resembling a dashboard or a floating mobile screen.
- Shared Space is a calm authoring and management volume. Any sample lettering inside it is explicitly a bounded `not_world_anchored` preview.
- Only Stage Mixed shows true room-anchored text. No direction may visually imply that a Shared preview remains world locked after Stage closes.
- “Therapeutic” is a mood and self-support framing, not a medical efficacy claim.
- Visual references are derived from the product semantics and UXR evidence; no competitor composition, component appearance, material, color system, or motion is copied.

## 2. Spatial visual direction candidates

| ID · Direction | Spatial thesis | First-view composition | Container relationship | Depth plan | Information hierarchy | Interaction cues | Spatial value | Dashboard risk | Preview / render instruction |
|---|---|---|---|---|---|---|---|---|---|
| VD-01 **一字一息 / Letter & Breath** | One chosen sentence is treated as a quiet spatial object; tools recede until requested. | Shared opens with one primary text preview as the visual focus, a restrained phrase collection, and a compact edit surface; Stage removes the management shell and leaves room-placed lettering plus a small contextual AttachmentPanel only on selection. | Volumetric Shared root uses system glass and bounded 3D preview; explicit Stage Mixed uses ECS text entities and no glass-backed environment shell. | Shared: shell/back plane → content → selected preview at modest positive Z; Stage: room → anchored base pose → animated child offset → front/back glyph faces → optional selection panel nearer than text. | Sentence first; current anchor truth second; edit controls third; diagnostics only on error. | Gaze gives a soft outline/brightness lift; pinch selects; drag/scale/roll only in Stage; controller ray and inline controls remain equivalent fallbacks. | Strong only where real direction/distance/position matter; management remains 2D-efficient. | Low: one primary focus, progressive disclosure, no card wall. | Render a bright neutral passthrough room with one softly lit double-sided 3D sentence, minimal warm glow, a restrained translucent authoring volume, and an explicit “非锚定预览” label; no wallpaper, no dashboard, no people. |
| VD-02 **句子星群 / Quote Constellation** | Every phrase is represented as an orbiting spatial mote around the user, with density encoding recency. | Shared first view surrounds the volume with many phrase previews; Stage expands them into a room constellation. | Multiple preview clusters around the WindowContainer, then dense Stage entities. | Deep radial layers with animated drift. | Collection density first, selected sentence second. | Gaze pulls a phrase forward; hand gesture clusters/sorts. | Uses direction/depth continuously, even for management. | **High**: decorative spatialization, competing foci, visual crowding, motion discomfort and 5+ performance risk. | Render a radial constellation of quotes around the viewer with depth-coded clusters; deliberately test crowding and focus competition. |
| VD-03 **静心书桌 / Quiet Editorial Desk** | The app behaves like an elegant notebook: compose on a flat editorial surface, then place a sentence in Stage. | A large reading/editing panel dominates Shared; 3D preview is a small inset. | Planar-like authoring surface inside a shallow volume; Stage is a separate placement utility. | Mostly flat hierarchy; minimal Z. | Text editor and typography controls first; spatial preview second. | Keyboard/controller first, gaze secondary. | Excellent for editing but underuses the requested Volumetric root and weakens the “letter as object” concept. | Medium: risks becoming a conventional settings/editor page. | Render a serene editorial sheet with generous type, a small 3D preview well, and minimal controls; no decorative room objects. |

## 3. Structured design-effect review

> Scale: 1 weak/high-risk → 5 strong/low-risk. This review supplies the structured approval required when no manual visual confirmation has yet been provided.

| Criterion | VD-01 | VD-02 | VD-03 | Evidence / adjudication |
|---|---:|---:|---:|---|
| Alignment with selected H-A | 5 | 2 | 4 | VD-01 cleanly separates Shared authoring from Stage truth; VD-02 leaks spatiality into management; VD-03 preserves the split but weakens Volumetric value. |
| Readability and comfort | 5 | 1 | 5 | UXR S-01/S-02 favor a single focus, restrained motion, and a static branch; VD-02 introduces peripheral motion and density. |
| Spatial originality without pseudo-spatiality | 5 | 2 | 2 | VD-01 makes the glyph object—not a card wall—the spatial subject; VD-02 spatializes sorting; VD-03 is largely a 2D editor. |
| Accessibility and fallback clarity | 4 | 2 | 5 | VD-01 supports gaze/controller/keyboard and reduced motion; VD-02 requires spatial search; VD-03 is easiest but loses the intended object experience. |
| Five-plus text performance fit | 4 | 2 | 5 | VD-01 can cache glyphs and reduce non-selected effects; VD-02 multiplies moving layers; VD-03 keeps most content flat. |
| Data / anchor truth legibility | 5 | 2 | 4 | VD-01 provides explicit non-anchored/located/pending states; VD-02’s persistent-looking constellation risks false world-lock cues. |
| Total /30 | **28** | 11 | 25 | VD-01 wins on product truth plus spatial value; VD-03 remains a useful anti-overreach reference. |

- **Approved visual reference**: `VD-01 · 一字一息 / Letter & Breath`.
- **Approval basis**: structured design-effect review above, accepted for downstream design reasoning; human confirmation remains welcome but is not silently fabricated.
- **VD-02 rejected**: too many simultaneous foci, gratuitous depth, motion/comfort and performance risk; it violates the task-first spatial value decision.
- **VD-03 rejected**: efficient and comfortable, but makes the Volumetric request ornamental and approaches a conventional editorial/settings surface.

## 4. Approved visual reference contract

### 4.1 Visual grammar

| Dimension | Approved rule | Prohibited |
|---|---|---|
| Composition | One primary sentence or one primary decision at a time; collection and controls remain secondary. | Card wall, radial dashboard, decorative floating controls, multiple simultaneous hero texts. |
| Shape | Soft continuous forms, generous negative space, rounded SpatialUI built-ins; the glyph silhouette provides identity. | Themed pictograms, ornamental frames, copied widget shapes. |
| Color | Text palette must include pure white `#FFFFFF`, warm gold `#FFE4B5`, soft pink `#FFB6C1`, clear blue `#B0E0E6`, mint `#98FB98`; status semantics use separate color+shape+label tokens later. | Large high-saturation fields, color-only status, gradients behind critical text. |
| Typography | UI uses PicoTheme typography; user text offers sans-serif, serif, handwritten families with explicit fallback. | Material3 typography/components, decorative font for system/status copy, silent missing-font substitution. |
| Material | Shared root keeps the system glass background enabled by default; key text/edit surfaces use one system glass tier or one custom backing, never both. Stage glyphs use translucent emissive material with local contrast backing only when needed. | Custom full-window glass imitation on device, glass in Stage, stacked customColor+glass. |
| Depth | User lettering visual target thickness 5–10cm; hierarchy uses near=selected/important. | Exaggerated extrusion, thickness as a fake hit area, controls scattered through depth. |
| Motion | Float target ±2.5cm around the saved pose (5cm peak-to-peak), 8s sine; breath 6s; lifecycle fade 500ms; Reduce Motion/static is always equivalent. | Camera motion, sudden displacement, flashing, motion-only state. |

### 4.2 3D text truth and fallback

- **Primary target**: generated/cached glyph geometry with 0.05–0.10m visual extrusion depth, front-facing readable geometry plus a separately oriented back-facing readable copy so both viewing sides read in normal order.
- **Orientation**: LookAt/viewer-facing yaw/pitch policy is applied to a visual child of the saved anchor pose; initial roll/reference angle remains an authored offset. Anchor transform is never overwritten every frame by Billboard behavior.
- **Motion transform stack**: `AnchorPose → InitialRoll → FloatOffset → LookAt → SelectedScale`; persistent pose is stored before float/breathe offsets.
- **Compile/runtime fallback**: if target SDK/font extrusion symbols or the chosen font’s glyph outline path are unavailable, use a cached front texture on a shallow 0.05m box plus a separate reversed-normal back quad carrying a non-mirrored copy. If lighting/material symbols fail, use a single translucent unlit/tintable material with a subtle solid contrast halo; never drop readable text or anchor truth.
- **Font fallback**: Sans → Noto Sans CJK/system sans; Serif → Noto Serif CJK/system serif; Handwritten → bundled CJK-capable handwritten asset if licensed and present, otherwise explicit UI label “手写体不可用，已使用无衬线体”; no missing glyph tofu.

### 4.3 Competitive differentiation boundary

- C-01 contributes distance-readable placement and honest placement/rearrange/delete states, not Apple’s surface-widget composition.
- C-02/C-03 contribute quick presets/personalization, not notification-heavy layouts or background-theme dominance.
- C-04 contributes optional voice participation, not recording/player visuals or persistent microphone affordances.
- Evidence refs: UXR §3A C-01–C-04 and “我们的差异化机会”; real usage screenshots and accessibility behavior remain evidence gaps.

## 5. Downstream checkpoints

| Stage | Required adherence |
|---|---|
| Stage 9 spatial structure | Exactly one main Shared Volumetric root by default; one edit popup/overlay at a time; explicit Stage Mixed entry/exit; no Stage inside Shared. |
| Stage 10 composition | One primary focus; root owns fill/inset/region spacing; child components do not add duplicate outer padding. |
| Stage 11 design system | SpatialUI/PicoTheme and built-ins; no Material3; system glass default; explicit hover/haptic/fallback states; exact tokens and component anatomy. |
| Stage 13 preview | Web approximation labeled `web_design_validation_only`; preview must expose non-anchored vs Stage states and Reduce Motion, not claim runtime parity. |

## 6. Minimum Completeness Gate

| Check item | Evidence | Verdict |
|---|---|---|
| 2–3 materially different directions | §2: glyph-object, spatial constellation, editorial desk differ in composition/container/depth/interaction/cost | pass |
| Selected reference and approval evidence | §3 structured review selects VD-01 without fabricating human approval | pass |
| At least two rejected directions | §3 VD-02/VD-03 with bounded rationale | pass |
| Visual thesis and render instruction | §2 each candidate includes spatial thesis through render instruction | pass |
| H-A and evidence-truth consistency | §§1, 4.2, 5 | pass |
| Competitive reference boundary | §4.3 | pass |

| Field | Value |
|---|---|
| minimumCompletenessGate | pass for Stage 8 owned output |

## 7. Delivery

- Active artifact: `visual-language-report.md` rev 1.
- Approved visual reference: `VD-01 · 一字一息 / Letter & Breath`.
- No image generation or external visual asset was used.

