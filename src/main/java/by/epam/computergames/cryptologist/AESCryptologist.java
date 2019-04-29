package by.epam.computergames.cryptologist;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AESCryptologist
{
    private static final String ALGORITHM="AES";
    private static final String ENCODING="UTF-8";
    private static final String BLA_BLA_BLA="SHA-1";//todo ЧТО ЭТО?
    private static final String SALT="je34h45hf4jqwqji4389nj";

    public String makeAs(String text) throws CryptologistException
    {
        String result;
        try
        {
            SecretKeySpec secretKeySpec=generateSecretKeySpec(text);
            Cipher cipher=Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[]output=cipher.doFinal(text.getBytes());
            result=new String(output, ENCODING);
        }
        catch(Exception e)
        {
            throw new CryptologistException("AESCryptologist couldn't "+
                    "encrypt text because "+e+".");//TODO правильно ли записывать ошибку в новую ошибку
        }
        return result;
    }

    private SecretKeySpec generateSecretKeySpec(String text) throws UnsupportedEncodingException,
                                                                    NoSuchAlgorithmException
    {
        byte[] key=(SALT+text).getBytes(ENCODING);
        MessageDigest sha=MessageDigest.getInstance(BLA_BLA_BLA);
        key=sha.digest(key);
        key= Arrays.copyOf(key, 16);//todo вынести 16

        return new SecretKeySpec(key, ALGORITHM);
    }
}