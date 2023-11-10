package org.sopt.dosopttemplate.data

data class DefaultData(
    private var _value: String? = null
) {
    val value
        get() = _value ?: ""
}
