package org.sopt.dosopttemplate.data.entity

data class DefaultData(
    private var _value: String? = null
) {
    val value
        get() = _value ?: ""
}
