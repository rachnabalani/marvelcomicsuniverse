package com.rachnabalani.marvelcomicsuniverse.tools


import android.content.Context
import android.util.Log
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.collections.Map


class Utils {
    val PUBLIC_KEY = "your_pub_key"
    val PRIVATE_KEY = "your_private_key"

    val TIMESTAMP = "ts"
    val API_KEY = "apikey"
    val HASH = "hash"


    private fun md5(s: String): String {
        val m = MessageDigest.getInstance("MD5")
        m.reset()
        m.update(s.toByteArray())
        val digest = m.digest()
        val bigInt = BigInteger(1, digest)
        var hashtext: String = bigInt.toString(16)
        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length < 32) {
            hashtext = """0$hashtext"""
        }
        return hashtext
    }

    fun getGenKeyUser(): Map<String, String>? {
        val ts = (System.currentTimeMillis() / 1000).toString()
        val hash: String = md5(ts + PRIVATE_KEY + PUBLIC_KEY)
        val w = Log.w(
            "api is -->> ", "&" + TIMESTAMP + "=" + ts + "&" + API_KEY + "=" + PUBLIC_KEY + "&" +
                    HASH + "=" + hash
        )
        return hashMapOf(TIMESTAMP to ts, API_KEY to PUBLIC_KEY, HASH to hash)
        
    }

    fun getProgressDrawable(context: Context) : CircularProgressDrawable {
        return CircularProgressDrawable(context).apply {
            strokeWidth = 10f
            centerRadius = 50f
            start()
        }
    }

}