package com.example.wallettesting

import com.example.wallettesting.sr25519.HexUtils
import wallet.core.jni.CoinType
import wallet.core.jni.HDWallet
import wallet.core.jni.PublicKeyType

object Wallet{
    init {
        System.loadLibrary("TrustWalletCore");
    }

    @JvmStatic
    fun main(args: Array<String>) {
        //Main method
    }

    fun createWallet(mnemonic: String, path: String): String {
        val wallet = HDWallet(mnemonic, "")

        val addressEth: String = wallet.getAddressForCoin(CoinType.ETHEREUM)
        val key: wallet.core.jni.PrivateKey = wallet.getKey(CoinType.ETHEREUM, path)

        val publicKeyBase64: ByteArray = key.getPublicKeyByType(PublicKeyType.SECP256K1).data()
        val publicKey: String = HexUtils.bytesToHex(publicKeyBase64)

        val privateKeyBase64: ByteArray = key.data()
        val privateKey: String = HexUtils.bytesToHex(privateKeyBase64)

        return "$privateKey $publicKey $addressEth"
    }
}