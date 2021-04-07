package com.example.diverseapp.Base

import android.os.Build
import java.nio.charset.Charset
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.jvm.Throws

//This is where I attempted to perform encryption
final public class ChCrypto {
    val encorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Base64.getEncoder()
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    val decorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Base64.getDecoder()
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    @Throws(Exception::class)
    private fun cipher(opmode: Int, secretKey: String): Cipher {
        if (secretKey.length != 32) throw RuntimeException("SecretKey length is not 32 chars")
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val sk =
            SecretKeySpec(secretKey.toByteArray(), "AES")
        val iv = IvParameterSpec(
            secretKey.substring(0, 16).toByteArray()
        ) //0~16은 서버와 합의!
        c.init(opmode, sk, iv)
        return c
    }

    fun encrypt(str: String, secretKey: String): String? {
        return try {
            val encrypted =
                cipher(Cipher.ENCRYPT_MODE, secretKey)
                    .doFinal(str.toByteArray(charset("UTF-8")))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String(encorder.encode(encrypted))
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        } catch (e: Exception) {
            null
        }
    }

    fun decrypt(str: String, secretKey: String): String? {
        return try {
            val byteStr = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                decorder.decode(str.toByteArray())
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            String(
                cipher(Cipher.DECRYPT_MODE, secretKey).doFinal(byteStr),
                Charset.forName("UTF-8")
            )
        } catch (e: Exception) {
            null
        }
    }
}