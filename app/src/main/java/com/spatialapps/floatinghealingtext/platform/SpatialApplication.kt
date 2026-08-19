package com.spatialapps.floatinghealingtext.platform

import android.app.Application
import com.spatialapps.floatinghealingtext.data.HealingTextRepositoryImpl
import com.spatialapps.floatinghealingtext.data.local.HealingDatabase
import com.spatialapps.floatinghealingtext.domain.repository.HealingTextRepository
import com.pico.spatial.ui.foundation.dsl.launch
import com.spatialapps.floatinghealingtext.mainApp

class SpatialApplication : Application() {
    val healingTextRepository: HealingTextRepository by lazy {
        HealingTextRepositoryImpl(HealingDatabase.get(this).healingTextDao())
    }

    override fun onCreate() {
        super.onCreate()
        launch(::mainApp)
    }
}
