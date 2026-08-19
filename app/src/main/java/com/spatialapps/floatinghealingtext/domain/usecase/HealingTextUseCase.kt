package com.spatialapps.floatinghealingtext.domain.usecase

import com.spatialapps.floatinghealingtext.domain.model.HealingText
import com.spatialapps.floatinghealingtext.domain.repository.HealingTextRepository

class ObserveHealingTextsUseCase(private val repository: HealingTextRepository) {
    operator fun invoke() = repository.observeAll()
}

class SaveHealingTextUseCase(private val repository: HealingTextRepository) {
    suspend operator fun invoke(text: HealingText): Long = repository.upsert(text)
}
