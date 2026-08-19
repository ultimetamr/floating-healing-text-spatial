package com.spatialapps.floatinghealingtext

import com.spatialapps.floatinghealingtext.domain.model.HealingColor
import com.spatialapps.floatinghealingtext.domain.model.HealingFont
import com.spatialapps.floatinghealingtext.domain.model.HealingPresets
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HealingPresetsTest {
    @Test
    fun shipsExactRequestedPresetAndStyleCounts() {
        assertEquals(3, HealingPresets.size)
        assertEquals(3, HealingPresets.distinct().size)
        assertEquals(3, HealingFont.entries.size)
        assertEquals(5, HealingColor.entries.size)
        assertTrue(HealingPresets.all { it.isNotBlank() })
    }
}
