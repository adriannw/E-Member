package id.co.sinarmaslife.eproposal.common.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author laurent.virot
 * Useful class to encrypt/decrypt AES and HEXA.
 * Encode/decode the communication with hyperplanning.
 */
public class Cryptage {
        /**
         * Kind of interface to use AES/HEXA algorithm in the same way.
         *
         */
		protected final static Log logger = LogFactory.getLog( Cryptage.class );
	
        public enum Algo{
                HEXA,
                AES;
                
                /**
                 * Decrypt String s with Key k. Key is useless if algo is HEXA
                 * @param s string to decrypt.
                 * @param k Key used to decrypt.
                 * @return Decrypted string.
                 */
                public String decrypt(String s, String k) throws Exception{
                        String decrypted = null;
                        
                        switch(this){
                                case HEXA:
                                        try {
                                                // We use commons codec.
                                                decrypted =  new String(Hex.decodeHex(s.toCharArray()), "ISO-8859-1");
                                        } catch (DecoderException e) {
                                        		logger.error( e );
                                                throw new Exception("Could not decrypt HEXA string. Cause:" + e.getMessage());
                                        } catch (UnsupportedEncodingException e) {
                                        		logger.error( e );
                                                throw new Exception("Could not decrypt HEXA string. Cause:" + e.getMessage());
                                        }
                                        break;
                                        
                                case AES:
                                        try {
                                                // We use the md5 hash of the given key as real key.
                                                SecretKeySpec skeySpec = new SecretKeySpec(DigestUtils.md5(k), "AES");
                                                
                                                // Determine the padding of the message.
                                                Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
                                                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                                                
                                                // Decrypt
                                                decrypted = new String(cipher.doFinal(Hex.decodeHex(s.toCharArray())));
                                        } catch (Exception e) {
                                        		logger.error( e );
                                                throw new Exception("Could not decrypt AES. Cause:" + e.getMessage());
                                        }
                                        break;
                        }
                        return decrypted;
                }
                
                
                /**
                 * Encrypt String s with Key k. Key is useless if algo is HEXA
                 * @param s string to encrypt
                 * @param k Key used to encrypt.
                 * @return Encrypted string.
                 * @throws Exception
                 */
                public String encrypt(String s, String k) throws Exception{
                        String encrypted = null;
                        
                        switch(this){
                                case HEXA:
                                        // We use commons codec.
                                        encrypted = (new String(Hex.encodeHex(s.getBytes()))).toUpperCase();
                                        break;
                                        
                                case AES:
                                        try {
                                                // We use the md5 hash of the given key as real key.
                                                SecretKeySpec skeySpec = new SecretKeySpec(DigestUtils.md5(k), "AES");

                                                // Determine the padding of the message.
                                                Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
                                                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

                                                // Encrypt
                                                encrypted = new String(Hex.encodeHex(cipher.doFinal(s.getBytes())));
                                        } catch (Exception e) {
                                        		logger.error( e );
                                                throw new Exception("Could not encrypt AES. Cause:" + e.getMessage());
                                        }
                                        break;
                        }
                        
                        return encrypted;
                }
                
        }
        
        /**
         * Encrypt a message for hyperplanning communication.
         * @param i integer to encrypt
         * @param a algorithm to use
         * @param key Key used to encrypt.
         * @return Encrypted message.
         * @throws Exception
         */
        public static String encrypt(int i, Cryptage.Algo a, String key)  throws Exception{
                return encrypt(Integer.toString(i), a, key);
        }
        
        /**
         * Encrypt a message without key for hyperplanning communication.
         * @param s string to encrypt
         * @param a algorithm to use
         * @return Encrypted message.
         * @throws Exception
         */
        public static String encrypt(String s, Cryptage.Algo a) throws Exception {
                return  encrypt(s, a, "");
        }
        
        /**
         * Encrypt a message for hyperplanning communication.
         * @param s string to encrypt
         * @param a algorithm to use
         * @param key Key used to encrypt.
         * @return Encrypted message.
         * @throws Exception
         */
        public static String encrypt(String s, Cryptage.Algo a, String key) throws Exception {
                // Fill the string to be 16 multiple length.
                String filled = fill(s);
                
                // Encrypt the filled strig
                String encrypted = a.encrypt(filled, key);
                
                // Return encrypted message : 
                // 1. md5 hash of filled string.
                // 2. length of the filled string.
                // 3. encrypted string.
                return String.format("%s%010d%s", DigestUtils.md5Hex(filled), filled.length(), encrypted);
        }
        
        /**
         * Decrypt a hyperplanning message without key
         * @param s message to decrypt
         * @param a algorithm to use
         * @return decrypted message
         * @throws Exception
         */
        public static String decrypt(String s, Cryptage.Algo a) throws Exception {
                return decrypt(s, a, "");
        }
        
        /**
         * Decrypt a hyperplanning message
         * @param s message to decrypt
         * @param a algorithm to use
         * @param key key yse to decrypt
         * @return decrypted message
         * @throws Exception
         */
        public static String decrypt(String s, Cryptage.Algo a, String key) throws Exception {
                String decrypted = null;
                String message = "";
                @SuppressWarnings("unused")
                String md5 = null;
                int length = 0;
                
                // Split the message in 3 parts.
                Pattern pattern = Pattern.compile("([A-Fa-f0-9]{32})([0-9]{10})([A-Fa-f0-9]+)");
                Matcher matcher = pattern.matcher(s);
                
                if(matcher.find()) {
                        // md5 hash
                        md5 = matcher.group(1);
                        
                        // message length
                        length = Integer.parseInt(matcher.group(2));
                        
                        // message
                        message = matcher.group(3).substring(0, 2*length);
                }

                // decrypt the message
                decrypted = a.decrypt(message, key);
                
                // split and return the non filled part of the message
                return decrypted.substring(0, decrypted.indexOf("##"));
        }
        
        /**
         * Fill a message to be 16 multiple length
         * @param s message to fill
         * @return
         */
        private static String fill(String s) {
                StringBuilder str = new StringBuilder(s);
                
                // Append '##' to localized to filled part.
                str.append("##");
                
                // Determine the next 16 multiple.
                int end = (16-str.length()%16)%16;
                
                // Fill with 'a'.
                for(int i = 0 ; i < end; i++) {
                        str.append("a");
                }
                
                return str.toString();
        }
}
