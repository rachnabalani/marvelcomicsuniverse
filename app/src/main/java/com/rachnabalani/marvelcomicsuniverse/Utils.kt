package com.rachnabalani.marvelcomicsuniverse


import java.math.BigInteger
import java.security.MessageDigest


class Utils {

    val URL_MARVEL = "http://www.marvel.com"

    val PUBLIC_KEY = "getYourPUBLIC_KEYat-developer.marvel.com"
    val PRIVATE_KEY = "getYourPRIVATE_KEYat-developer.marvel.com"

    val TIMESTAMP = "ts"
    val API_KEY = "apikey"
    val HASH = "hash"

    private fun md5(s: String): String? {
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

    val genKeyUser: String?
        get() {
            val ts = (System.currentTimeMillis() / 1000).toString()
            val hash: String? = md5(ts + PRIVATE_KEY + PUBLIC_KEY)
            return "?" + TIMESTAMP + "=" + ts + "&" + API_KEY + "=" + PUBLIC_KEY + "&" +
                    HASH + "=" + hash

        }

}