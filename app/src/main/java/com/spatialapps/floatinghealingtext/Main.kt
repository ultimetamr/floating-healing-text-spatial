package com.spatialapps.floatinghealingtext

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spatialapps.floatinghealingtext.content.HealingAnchorStage
import com.spatialapps.floatinghealingtext.ui.HealingSpaceScreen
import com.pico.spatial.ui.design.PicoTheme
import com.pico.spatial.ui.foundation.dsl.DefaultWindowContainer
import com.pico.spatial.ui.foundation.dsl.SpatialAppScope
import com.pico.spatial.ui.foundation.dsl.Stage

const val HEALING_STAGE_ID = "healing_anchor_stage"

fun mainApp(scope: SpatialAppScope) =
    with(scope) {
        DefaultWindowContainer {
            PicoTheme {
                HealingSpaceScreen(
                    modifier = Modifier.windowConstraints(
                        minWidth = 1_600.dp,
                        minHeight = 1_040.dp,
                        maxWidth = 1_600.dp,
                        maxHeight = 1_040.dp,
                    ),
                )
            }
        }
        Stage(id = HEALING_STAGE_ID) {
            PicoTheme {
                HealingAnchorStage()
            }
        }
    }
