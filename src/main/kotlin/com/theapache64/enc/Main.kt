package com.theapache64.enc

import com.theapache64.enc.utils.Crypto
import com.theapache64.enc.utils.InputUtils
import java.io.File

val saltFile = File("salt.txt")

fun main(args: Array<String>) {
    val salt = if (saltFile.exists()) {
        saltFile.readText()
    } else {
        createSalt()
    }

    val crypto = Crypto(salt.toByteArray())
    val string = InputUtils.getString("Enter string", true)
    println(crypto.encrypt(string))
    println("Done 👍")
}

fun createSalt(): String {
    val newSalt = InputUtils.getString("Enter salt", true)
    saltFile.writeText(newSalt)
    return if (newSalt.length != 16) {
        println("Error: Salt length should be 16 chars. Given ${newSalt.length}")
        createSalt()
    } else {
        newSalt
    }
}
