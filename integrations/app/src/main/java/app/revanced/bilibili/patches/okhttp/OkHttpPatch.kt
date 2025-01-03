package app.revanced.bilibili.patches.okhttp

import android.util.Pair
import androidx.annotation.Keep
import app.revanced.bilibili.api.BrotliInputStream
import app.revanced.bilibili.patches.main.ApplicationDelegate
import app.revanced.bilibili.patches.okhttp.hooks.*
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.safeContent
import java.io.InputStream
import java.util.zip.GZIPInputStream
import java.util.zip.InflaterInputStream

@Suppress("unused")
object OkHttpPatch {
    @JvmStatic
    private val hooks = arrayOf(
        BangumiCards,
        BangumiMaterial,
        DisableAvif,
        DmAd,
        DmQoeShow,
        FeedIndex,
        GarbSuitDetail,
        GrpcPlayViewUnite,
        ReplyAdd,
        RoomPlayInfo,
        SearchAll,
        SearchDefaultWords,
        SearchRecommend,
        SearchSquare,
        SeasonRecommend,
        ShareChannels,
        ShareClick,
        Skin,
        Subtitle,
        Upgrade,
        ViewLikeTriple,
        VipAds,
        VipPrivilegeInfo,
    )

    @Keep
    @JvmStatic
    fun shouldHook(url: String, code: Int): Boolean {
        if (!ApplicationDelegate.attached())
            return false
        Logger.debug { "OkHttpPatch.shouldHook, code: $code, url: ${url.safeContent}" }
        return (code == 200 && Settings.Debug()) || hooks.any { it.shouldHook(url, code) }
    }

    @JvmStatic
    fun hook(url: String, code: Int, request: String, response: String): String {
        return hooks.firstOrNull { it.shouldHook(url, code) }
            ?.hook(url, code, request, response) ?: response
    }

    @Keep
    @JvmStatic
    fun hook(
        url: String,
        code: Int,
        reqEncoding: String?,
        reqStream: InputStream,
        respEncoding: String?,
        respStream: InputStream
    ): String {
        val request = if (reqStream.available() == 0) {
            ""
        } else (when (reqEncoding) {
            "gzip" -> GZIPInputStream(reqStream)
            "deflate" -> InflaterInputStream(reqStream)
            "br" -> BrotliInputStream(reqStream)
            else -> reqStream
        }).bufferedReader().use { it.readText() }
        val response = (when (respEncoding) {
            "gzip" -> GZIPInputStream(respStream)
            "deflate" -> InflaterInputStream(respStream)
            "br" -> BrotliInputStream(respStream)
            else -> respStream
        }).bufferedReader().use { it.readText() }
        Logger.debug { "OkHttpPatch.hook, code: $code, url: ${url.safeContent}" }
        Logger.debug { "OkHttpPatch.hook, request, encoding: $reqEncoding, content: ${request.safeContent}" }
        Logger.debug { "OkHttpPatch.hook, response, encoding: $respEncoding, content: ${response.safeContent}" }
        return hook(url, code, request, response)
    }

    @Keep
    @JvmStatic
    fun hookBefore(url: String, headers: Array<String>): Pair<String, Array<String>> {
        if (!ApplicationDelegate.attached())
            return Pair.create(url, headers)
        return hooks.find { it.shouldHookBefore(url, headers) }
            ?.hookBefore(url, headers) ?: Pair.create(url, headers)
    }
}
