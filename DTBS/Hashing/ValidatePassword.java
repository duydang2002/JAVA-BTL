package Hashing;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
// import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
public class ValidatePassword {

public static void main(String[] args) {
    try {
        System.out.println(validatePassword("matkhau01","1000:aa0f1e0008d669bc2fd44a8cf21b59e5:4ab2168b3d62e6a38bf69f96a18ad5c0e05fdb276217ef0ccaff9b89540c169c108bc0ea32932bf9b30336f5e9681edce030da2acf2f8e25746add4c6bc96817"));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
public boolean ValidatePassword(String originalPassword,String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException{
    return validatePassword(originalPassword, storedPassword);
}

private static boolean validatePassword(String originalPassword, String storedPassword) 
    throws NoSuchAlgorithmException, InvalidKeySpecException
{
    String[] parts = storedPassword.split(":");
    int iterations = Integer.parseInt(parts[0]);

    byte[] salt = fromHex(parts[1]);
    byte[] hash = fromHex(parts[2]);

    PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), 
        salt, iterations, hash.length * 8);
    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] testHash = skf.generateSecret(spec).getEncoded();

    int diff = hash.length ^ testHash.length;
    for(int i = 0; i < hash.length && i < testHash.length; i++)
    {
        diff |= hash[i] ^ testHash[i];
    }
    return diff == 0;
}
private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
{
    byte[] bytes = new byte[hex.length() / 2];
    for(int i = 0; i < bytes.length ;i++)
    {
        bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
    }
    return bytes;
}
}
