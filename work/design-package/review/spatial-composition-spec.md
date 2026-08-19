# 空间组合规格 · 悬浮文字疗愈空间

> Role: spatial_design_system_designer | Active revision: 3 | Workflow coverage: Stage 10 composition_synthesis + bounded DS-02/DS-04 reconciliation + provenance-only DS-04R correction
>
> Provenance: Stage 10 generation consumed Interaction rev 3, Visual Language rev 1 and Visual System rev 1. The bounded DS-02 patch produced rev 2 against Interaction rev 6, Spatial Design System rev 2 and Visual System rev 4. CR-DS-04R changes only §6 active sizing authority to Interaction rev 7 against Critique rev 5; no design fact, value or layout changes.

## 1. Composition-wide rules

| Rule | Exact contract | Source |
|---|---|---|
| Primary focus | primaryFocusCount = 1 in every state; an open edit overlay, confirmation dialog, selected Stage text, or recovery decision becomes the sole primary focus. | VD-01, interaction P3/P4 |
| Root fill ownership | WC-MAIN Root alone fills the WindowContainer and owns system glass, 32dp safe inset, region clipping and responsive tier. | Interaction §9.3 |
| Spacing ownership | Root Grid owns 24dp inter-region gaps. A region owns no outer padding. Components own only their internal 16dp/24dp padding. Overlay owns its own 24dp inset. | Prevent double inset and drift |
| Depth ownership | Root assigns region Z; child components cannot self-promote in Z except documented focus/pressed deltas. Stage visual child animates without mutating AnchorPose. | Anchor truth + near=important |
| Density | One collection, one preview/detail and one action cluster maximum; no independent dashboard tiles. List shows at most 6 regular rows before scrolling. | T-04/T-14, VD-01 |
| Modal exclusivity | At most one OV-EDIT or Dialog. Opening it blocks background actions and preserves the source focus token. | Interaction §8/§10 |

## 2. WC-MAIN shell and design grid

| Property | Default | Compact / min | Large / max |
|---|---|---|---|
| Outer physical volume | 1.28×0.832×0.48m | 0.96×0.624×0.36m | 1.60×1.04×0.60m |
| Internal reference constraint | 1280×832dp | 960×624dp | 1600×1040dp |
| Note | Reference constraints are a project layout grid, not an official meter↔dp conversion; runtime density maps Compose constraints inside the measured volume. | same | same |
| Safe inset | 32dp each edge, root-owned | 32dp | 32dp |
| Inter-region gap | 24dp, root-owned | 16dp between stacked regions | 24dp |
| Body grid | 440dp collection + 24dp gap + 752dp preview | one visible content region, 896dp available width | 544dp collection + 24dp gap + 968dp preview |
| Header / body | 72dp header + 24dp gap + 672dp body | 64dp header + 16dp gap + 480dp body | 72dp header + 24dp gap + 880dp body |

~~~text
┌──────────────────────────────────────────────────────────────┐  WC-MAIN 1280×832dp reference
│ Root: system glass + 32dp inset + clipping                   │
│ ┌──────────────────────────────────────────────────────────┐ │
│ │ R-HEADER 72dp: AppIdentity · AnchorTruth · NewAction     │ │
│ └──────────────────────────────────────────────────────────┘ │
│                         gap 24dp                              │
│ ┌────────────────────┐ gap 24dp ┌──────────────────────────┐ │
│ │ R-COLLECTION 440dp │          │ R-PREVIEW 752dp         │ │
│ │ PhraseCollection   │          │ TextObjectPreview       │ │
│ │ max 6 rows + scroll│          │ PreviewLabel + Actions  │ │
│ └────────────────────┘          └──────────────────────────┘ │
│                 OV-EDIT: anchored overlay, one at a time     │
└──────────────────────────────────────────────────────────────┘
~~~

- **Region→component mapping**: R-HEADER → AppIdentity, AnchorTruthBadge, NewAction; R-COLLECTION → PhraseCollection/PhraseRow; R-PREVIEW → TextObjectPreview, PreviewTruthLabel, ContextActions; overlay layer → EditOverlay or Dialog, never both.
- **Depth**: shell/backing z=0; 2D regions z=0.016m; 3D preview center z=0.12m with full glow envelope kept inside the 0.48m volume; overlay z=0.18m; focus ring adds at most +0.004m.
- **R-PREVIEW fit**: Compact total TextObject3D envelope ≤0.60×0.28×0.12m inside the 0.896×0.480m single content region; Default ≤0.68×0.44×0.16m inside 0.752×0.672m; Large ≤0.88×0.60×0.20m inside 0.968×0.880m. Envelope includes ≤0.02m halo and bounded LookAt sweep. Overflow order is face-on clamp → wrap → minimum 0.08m glyph height → ≥56dp page controls, never clip/ellipsis.

## 3. Derived layouts

| Layout ID / states | Derivation (task/data/frequency/spatial constraint) | Primary focus | Regions / ownership | Density limit | Responsive transformation | Rejected alternative |
|---|---|---|---|---|---|---|
| L-SHARED-EMPTY · S-01 | No rows; T-01 source choice is the only decision; presets are local and frequent first-use content. | NewAction or one preset | Header + centered EmptyCreate region; preview region becomes a noninteractive explanation. | 20 presets in one internally scrolling built-in list/grid, no 20 floating objects | Compact uses one-column preset list; default/large may use 2 columns, target ≥56dp | Full-screen carousel rejected: hides alternatives and resembles media navigation |
| L-SHARED-LIBRARY · S-02/S-14 | T-04 scans local records while T-03 preview is secondary; anchor status is semantic and fresh/pending/missing. | selected row, otherwise NewAction | R-COLLECTION owns scroll/selection; R-PREVIEW owns only selected record preview/status; R-HEADER owns global New | max 6 visible rows default, 8 large; one selected preview | Compact toggles collection↔preview through selection/back, never side-by-side compression | Card dashboard rejected: duplicate status, too many foci |
| L-SHARED-EDIT · S-03–S-06/S-15 | Exact text, voice and style belong to one draft; popup must preserve source context. | OV-EDIT | 520×640dp default overlay, max 560×688dp; title 56dp, content flexible, action row 64dp; 24dp inset | one draft, one preview, max five color swatches + three font choices visible; 20 presets scroll | Compact overlay occupies content area 896×480dp and internally scrolls; no global scale | Persistent full-height Subwindow rejected; duplicates source and violates one-window restraint |
| L-STAGE-LOCATE-PLACE · S-08/S-09/S-13 | T-06/T-07 need room center, candidate pose and one recovery decision; no management chrome. | candidate text or recovery action | room is environment; one GuidancePanel within clear FOV; candidate ECS entity at target pose | one candidate plus already located anchors; non-target anchors dim to 70% and stop enhanced motion | World-adaptive; guidance remains body-relative only during recovery, then disappears | Permanent HUD rejected: occludes room and competes with text |
| L-STAGE-VIEW · S-10 | T-08 reads located entities; 5+ exist independently but only gaze target may highlight. | current gaze target; none when scanning | AnchorPose entities distributed by saved room transforms; no persistent panel | 5+ allowed; one enhanced hover; others normal or LOD; no global orbit | Distance LOD reduces glow/mesh/animation, not text/status; room placement unchanged | Center-stacking rejected: destroys anchor meaning |
| L-STAGE-SELECTED · S-11/S-12 | T-09/T-10 require unique object identity, edit fallback and destructive confirmation. | selected text then dialog if deleting | selected ECS text + AP-SELECTION placed beside/below; delete Dialog replaces panel actions | exactly one selected entity and one panel; no other interactive panels | Panel lateral placement flips to the clear side; if insufficient room, below text; dialog body-relative within central FOV | Always-on Toolbar and multi-selection rejected |
| L-STAGE-EXIT · S-16 | Dirty/in-flight operation is the only decision before stable return. | exit guard choice | selected/in-flight object freezes at last stable visual; compact guard panel shows save/wait/cancel/discard allowed options | one operation source, one primary action + alternatives | fixed central comfort area; Reduce Motion pure fade on close | Silent auto-save/auto-discard rejected |

## 4. Stage ECS placement geometry

| Entity / layer | Anchor / transform | Size / range | Orientation / depth | Content |
|---|---|---|---|---|
| AnchorRoot | persistent spatial AnchorPose | no visual geometry | immutable during view animation | spatial authority + UUID |
| TextMotionRoot | child of AnchorRoot | translation y ±0.025m normal | InitialRoll → FloatOffset; saved pose excludes animation | float state |
| TextFacingRoot | child of TextMotionRoot | width 0.30–1.20m; glyph height 0.08–0.30m | LookAt viewer with preserved roll/reference; smoothing does not move AnchorRoot | viewer-facing transform |
| GlyphFront | child z=+depth/2 | extrusion depth target 0.05–0.10m | normal front-facing | normal-order readable glyphs |
| GlyphBack | child z=−depth/2 | same bounds | separately oriented copy, not mirrored text | normal-order readable from back |
| GlowEnvelope | child, noninteractive | ≤0.02m beyond glyph silhouette | follows TextFacingRoot | soft edge glow, LOD-removable |
| AP-SELECTION | attached to selected TextFacingRoot or companion entity | target 360×420dp; hit ≥56dp | preferred local x = half text width +0.12m, z=+0.08m toward viewer; flip side or y=−0.22m fallback | style summary, Save/Cancel, Re-place, Delete, Reduce Motion, controller transform |
| RecoveryPanel | body-relative only while S-13 | target 520×280dp | central clear FOV; never saved to anchor | source, next step, retry/re-place/exit |

**Transform invariant**: AnchorPose → InitialRoll → FloatOffset → LookAt → SelectedScale. Breathe changes emission/brightness only; selected scale is temporary and never committed as anchor scale unless the user confirms an explicit size edit.

## 5. Responsive and failure composition

| Condition | Composition response | Semantics preserved |
|---|---|---|
| WC-MAIN Compact | one content region visible; selection opens preview; back returns to list; edit overlay takes content layer | selected ID, draft, anchor truth, targets ≥56dp |
| Long phrase | width-limited wrapping, max ~50 CJK chars per line; edit field scrolls | full exact text; no ellipsis in confirm/delete |
| Five-plus Stage texts | one primary gaze target; non-selected LOD and normal/static motion under pressure | content, location state, deletion/error status |
| Tracking lost | detach AP-SELECTION, freeze last stable UI snapshot as non-located guidance, show RecoveryPanel | no false world lock; return path |
| Partial delete | removed side is labeled complete; remaining side pending repair; record stays addressable | UUID/localId journal and source |
| Font/mesh unavailable | cached texture on 0.05m shallow box + separate readable back quad, constrained to the source Shared envelope or Stage range; explicit font fallback label | exact string, front/back readability |
| Reduce Motion | FloatOffset=0, selected scale=1, breath replaced by static/opacity state cue | focus/selected/error/save semantics |

## 6. Minimum Completeness Gate

| Check item | Evidence | Verdict |
|---|---|---|
| Each layout derived from tasks/data/frequency/spatial constraints | §3 | pass |
| Single primary focus and region ownership | §§1–3 | pass |
| Exact sizing alignment and root/spacing ownership | §2 matches Interaction rev 7 §9 | pass |
| Large/default/compact reflow | §§2–3 and §5 | pass |
| Stage ECS/AttachmentPanel geometry and fallback | §4–§5 | pass |
| Density limits, exceptions and rejected options | §3–§5 | pass |

| Field | Value |
|---|---|
| minimumCompletenessGate | pass for provenance-only rev 3 author self-check; fresh third Stage 12 rerun required |
