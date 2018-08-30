package cn.droidlover.xdroidmvp.utils;

import android.annotation.SuppressLint;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    public static int keySize = 16;
    public static String AES_MODE = "AES/ECB/PKCS5Padding";

    public static byte[] encrypt(String key, String content) {
        return encrypt(key, content, "UTF-8");
    }

    public static byte[] encrypt(String key, String content, String charEncode) {
        try {
            return encrypt(key.getBytes(charEncode),
                    content.getBytes(charEncode));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressLint("TrulyRandom")
    public static byte[] encrypt(byte[] keyByte, byte[] content) {
        try {
            SecretKeySpec key = new SecretKeySpec(make_kb(keyByte, keySize),
                    "AES");
            Cipher cipher = Cipher.getInstance(AES_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] keyByte, byte[] content) {
        try {
            SecretKeySpec key = new SecretKeySpec(make_kb(keyByte, keySize),
                    "AES");
            Cipher cipher = Cipher.getInstance(AES_MODE);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String decrypt(String key, byte[] content) {
        return decrypt(key, content, "UTF-8");
    }

    public static String decrypt(String key, byte[] content, String encode) {
        try {
            //改动"UTF-8"
            return new String(decrypt(key.getBytes(encode), content), encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] make_kb(byte[] key, int size) {
        byte[] kb = new byte[size];
        byte[] bytes = key;
        System.arraycopy(bytes, 0, kb, 0, bytes.length > size ? size
                : bytes.length);
        return kb;
    }

    public static byte[] encryptFile(byte[] keyByte, String inFile,
                                     String outFile) throws Exception {
        SecretKeySpec key = new SecretKeySpec(make_kb(keyByte, keySize), "AES");
        Cipher cipher = Cipher.getInstance(AES_MODE);
        InputStream in = new FileInputStream(inFile);
        DataOutputStream out = new DataOutputStream(new FileOutputStream(
                outFile));
        cipher.init(Cipher.ENCRYPT_MODE, key);
        crypt(in, out, cipher);
        in.close();
        out.close();
        return key.getEncoded();
    }

    public static void decryptFile(byte[] keyByte, String inFile, String outFile)
            throws GeneralSecurityException, IOException {

        Cipher cipher = Cipher.getInstance(AES_MODE);
        SecretKeySpec key = new SecretKeySpec(make_kb(keyByte, keySize), "AES");
        OutputStream out = new FileOutputStream(outFile);
        DataInputStream in = new DataInputStream(new FileInputStream(inFile));
        cipher.init(Cipher.DECRYPT_MODE, key);
        crypt(in, out, cipher);

        in.close();
        out.close();

    }

    public static void decryptFile(byte[] keyByte, InputStream ins,
                                   String outFile) throws GeneralSecurityException, IOException {

        Cipher cipher = Cipher.getInstance(AES_MODE);
        SecretKeySpec key = new SecretKeySpec(make_kb(keyByte, keySize), "AES");
        OutputStream out = new FileOutputStream(outFile);
        DataInputStream in = new DataInputStream(ins);
        cipher.init(Cipher.DECRYPT_MODE, key);
        crypt(in, out, cipher);
        in.close();
        out.close();

    }

    private static void crypt(InputStream in, OutputStream out, Cipher cipher)
            throws IOException, GeneralSecurityException {
        int blockSize = cipher.getBlockSize();
        int outputSize = cipher.getOutputSize(blockSize);
        byte[] inBytes = new byte[blockSize];
        byte[] outBytes = new byte[outputSize];
        int inLength = 0;
        boolean more = true;
        while (more) {
            inLength = in.read(inBytes);
            if (inLength == blockSize) {
                int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
                out.write(outBytes, 0, outLength);
            } else {
                more = false;
            }
        }
        if (inLength > 0)
            outBytes = cipher.doFinal(inBytes, 0, inLength);
        else
            outBytes = cipher.doFinal();
        out.write(outBytes);
    }

    /**
     * 二行制转十六进制字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }


}
