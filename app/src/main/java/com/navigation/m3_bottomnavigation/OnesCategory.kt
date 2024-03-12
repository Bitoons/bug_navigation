package com.navigation.m3_bottomnavigation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnesCategory(
    val backgroundImgUrl: String,
    val categoryType: String,
    val charityProjectCategoryId: Long,
    val iconCode: String,
    val onesAmount: Double,
    val onesDonated: Double,
    val summary: String,
    val title: String,
    val charityProjectsAssociated: Int
) : Parcelable {

    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}

class OnesCategoryType : NavType<OnesCategory>(isNullableAllowed = false) {
    override val name = "OnesCategoryType_string"
    override fun get(bundle: Bundle, key: String): OnesCategory? = bundle.parcelable(key)
    override fun parseValue(value: String): OnesCategory =
        Gson().fromJson(value, OnesCategory::class.java)

    override fun put(bundle: Bundle, key: String, value: OnesCategory) {
        bundle.putParcelable(key, value)
    }
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}