package br.com.adalbertofjr.marvelheroes.util

import br.com.adalbertofjr.marvelheroes.BuildConfig
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class HashUtil {
    companion object {
        fun getTs(): Long {
            return Calendar.getInstance().timeInMillis
        }

        fun generateHash(ts: Long): String {
            val rawHash = "$ts${BuildConfig.PRIVATE_KEY_API_VALUE}${BuildConfig.PUBLIC_KEY_API_VALUE}"
            val md5: MessageDigest
            return try {
                md5 = MessageDigest.getInstance("MD5")
                val bytes = md5.digest(rawHash!!.toByteArray())
                bytesToHex(bytes)
            } catch (e: NoSuchAlgorithmException) {
                ""
            }
        }

        fun bytesToHex(bts: ByteArray): String {
            var des = ""
            var tmp: String
            for (i in bts.indices) {
                tmp = Integer.toHexString(bts[i].toInt() and 0xFF)
                if (tmp.length == 1) {
                    des += "0"
                }
                des += tmp
            }
            return des
        }
    }
}