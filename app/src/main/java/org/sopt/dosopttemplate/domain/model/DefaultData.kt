package org.sopt.dosopttemplate.domain.model

data class DefaultData(
    private var _value: String? = null
) {
    val value
        get() = _value ?: ""
}
