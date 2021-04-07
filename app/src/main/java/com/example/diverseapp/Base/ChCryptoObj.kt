package com.example.diverseapp.Base

import android.os.Build
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object ChCryptoObj{
    @JvmStatic fun aesEncrypt(v:String, secretKey:String) = AES256.encrypt(v, secretKey)
    @JvmStatic fun aesDecrypt(v:String, secretKey:String) = AES256.decrypt(v, secretKey)
}

private object AES256{
    private val encorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Base64.getEncoder()
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    private val decorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Base64.getDecoder()
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    private fun cipher(opmode:Int, secretKey:String):Cipher{
        if(secretKey.length != 32) throw RuntimeException("SecretKey length is not 32 chars")
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val sk = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
        val iv = IvParameterSpec(secretKey.substring(0, 16).toByteArray(Charsets.UTF_8))
        c.init(opmode, sk, iv)
        return c
    }
    fun encrypt(str:String, secretKey:String):String{
        val encrypted = cipher(Cipher.ENCRYPT_MODE, secretKey).doFinal(str.toByteArray(Charsets.UTF_8))
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String(encorder.encode(encrypted))
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }
    fun decrypt(str:String, secretKey:String):String{
        val byteStr = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            decorder.decode(str.toByteArray(Charsets.UTF_8))
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return String(cipher(Cipher.DECRYPT_MODE, secretKey).doFinal(byteStr))
    }
}