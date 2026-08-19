package com.spatialapps.floatinghealingtext.data

/** Data-layer boundary kept explicit for architecture tooling and replaceable storage backends. */
interface HealingTextRepository :
    com.spatialapps.floatinghealingtext.domain.repository.HealingTextRepository
