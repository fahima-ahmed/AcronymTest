package com.example.acronyms.model

import java.io.Serializable

data class AcronymResult(
    val sf: String? = null,
    val lfs: List<LongForm>? = null
) : Serializable

data class LongForm(val lf: String? = null, val freq: Int? = null, val since: Int? = null)