package com.pixvs.viaticos.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * @author ???
 * @date ???
 * @fechaUltimaModificacion 19/08/2014
 * @version: 1.02.00
 */
public class Encriptador {

    // Frase de encriptacion:
    public static final String FRASE = "3.1416";
    // Parametros utilizados para el cifrado web compatible con MCrypt (PHP)
    static char[] CARACTERES_HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private final String vector_inicial = "f4kd9s882hd023jd";
    private final String SecretKey = "kmSf9u836zW6oKOD";
    private IvParameterSpec ivspec;
    private SecretKeySpec keyspec;
    // Instancia de la clase Cipher para realizar la encriptacion.
    private Cipher eCipher;
    // Instancia de la clase Cipher para realizar la encriptacion Web.
    private Cipher cipher_web;
    // Instancia de la clase Cipher para realizar la desencriptacion.
    private Cipher dCipher;

    public Encriptador() {
        this(FRASE);
    }

    /**
     * Constructor usado para crear este objeto. Responsable de inicializar el encriptador y
     * el desencriptador del objeto en base a una instancia de la clase SecretKey (Llave
     * Secreta) y un algoritmo.
     *
     * @param llaveSecreta Llave secreta utilizada para inicializar el encriptador y el
     * desencriptador.
     * @param algoritmo Algoritmo utilizado para crear el encriptador y el desencriptador.
     */
    public Encriptador(SecretKey llaveSecreta, String algoritmo) {
        try {
            eCipher = Cipher.getInstance(algoritmo);
            dCipher = Cipher.getInstance(algoritmo);
            eCipher.init(Cipher.ENCRYPT_MODE, llaveSecreta);
            dCipher.init(Cipher.DECRYPT_MODE, llaveSecreta);
        } catch (NoSuchPaddingException e) {
            System.out.println("EXCEPCION: NoSuchPaddingException");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("EXCEPCION: NoSuchAlgorithmException");
        } catch (InvalidKeyException e) {
            System.out.println("EXCEPCION: InvalidKeyException");
        }
    }

    /**
     * Constructor usado para crear este objeto. Responsable de inicializar el encriptador y
     * el desencriptador del objeto en base una Frase de Encriptacion y los algoritmos MD5 y
     * DES.
     *
     * @param fraseEncriptacion Frase de Encriptacion utilizada para inicializar el
     * sencriptador y el desencriptador.
     */
    public Encriptador(String fraseEncriptacion) {
        // Salto de 8 bytes, necesario para construir el objeto KeySpec.
        byte[] salto = {
                (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
                (byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03
        };
        // Contador de Iteraciones, necesario para construir el objeto KeySpec.
        int contadorIteraciones = 19;
        try {
            KeySpec keySpec = new PBEKeySpec(fraseEncriptacion.toCharArray(), salto, contadorIteraciones);
            // Llave Secreta en base a las especificaciones de keySpec.
            SecretKey llaveSecreta = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            ivspec = new IvParameterSpec(vector_inicial.getBytes());
            keyspec = new SecretKeySpec(SecretKey.getBytes(), "AES");

            // Construccion del encriptador y del desencriptador.
            eCipher = Cipher.getInstance(llaveSecreta.getAlgorithm());
            dCipher = Cipher.getInstance(llaveSecreta.getAlgorithm());
            cipher_web = Cipher.getInstance("AES/CBC/NoPadding");

            // Preparacion de los parametros para los objetos Cipher.
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salto, contadorIteraciones);
            // Inicializacion del encriptador y del desencriptador.
            eCipher.init(Cipher.ENCRYPT_MODE, llaveSecreta, paramSpec);
            dCipher.init(Cipher.DECRYPT_MODE, llaveSecreta, paramSpec);
        } catch (InvalidAlgorithmParameterException e) {
            System.out.println("EXCEPCION: InvalidAlgorithmParameterException");
        } catch (InvalidKeySpecException e) {
            System.out.println("EXCEPCION: InvalidKeySpecException");
        } catch (NoSuchPaddingException e) {
            System.out.println("EXCEPCION: NoSuchPaddingException");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("EXCEPCION: NoSuchAlgorithmException");
        } catch (InvalidKeyException e) {
            System.out.println("EXCEPCION: InvalidKeyException");
        }
    }

    /**
     * Este metodo toma un objeto de la clase String y regresa una version encriptada del
     * objeto tambien como un String.
     *
     * @param cadena Cadena a encriptar.
     * @return <code>String</code> Version encriptada de cadena enviada.
     */
    /*public String encripta(String cadena) {
        try {
            // Codifica la cadena en bytes utilizando UTF8.
            byte[] utf8 = cadena.getBytes("UTF8");
            // Encriptacion.
            byte[] enc = eCipher.doFinal(utf8);
            // Codifica la version encriptada a BASE64 para obtener un String.
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        return null;
    }*/

    /**
     * Este metodo toma un objeto de la clase String que representa una version encriptada
     * de otra cadena, la desencripta y regresa la cadena original.
     *
     * @param cadena Version encriptada de una cadena.
     * @return <code>String</code> Version desencriptada de la cadena.
     */
    /*public String desencripta(String cadena) {
        try {
            // Decodifica la cadena en BASE64 para obtener los bytes encriptados.
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(cadena);
            // Desencriptacion.
            byte[] utf8 = dCipher.doFinal(dec);
            // Decodifica los bytes en UTF8 para obtener la cadena original.
            return new String(utf8, "UTF8");
        } catch (BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        return null;
    }*/

    /**
     * Toma una cadena y la encripta con la compatibilidad con MCrypt Rijndael_128(PHP)
     *
     * @param texto
     * @return <code>String</code> Cadena encriptada.
     */
    public String encriptaWeb(String texto) {
        byte[] encriptado = null;
        try {

            cipher_web.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);

            encriptado = cipher_web.doFinal(padString(texto).getBytes());

        }
        catch (InvalidKeyException ex) {}
        catch (IllegalBlockSizeException ex) {}
        catch (BadPaddingException ex) {}
        catch (InvalidAlgorithmParameterException ex) {}

        return bytesAHex(encriptado);
    }

    /**
     * Toma una cadena que ha sido previamente encriptada y la desencripta
     *  con la compatibilidad con MCrypt Rijndael_128(PHP)
     *
     * @param cadena
     * @return <code>String</code> Cadena encriptada.
     */
    public String desencriptaWeb(String cadena) {

        byte[] desencriptado = null;

        try {

            cipher_web.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            desencriptado = cipher_web.doFinal(hexABytes(cadena));

            if (desencriptado.length > 0) {
                int trim = 0;
                for (int i = desencriptado.length - 1; i >= 0; i--) {
                    if (desencriptado[i] == 0) {
                        trim++;
                    }
                }

                if (trim > 0) {
                    byte[] newArray = new byte[desencriptado.length - trim];
                    System.arraycopy(desencriptado, 0, newArray, 0, desencriptado.length - trim);
                    desencriptado = newArray;
                }
            }

            return new String(desencriptado);
        }
        catch (IllegalBlockSizeException e) {}
        catch (BadPaddingException ex) {}
        catch (InvalidKeyException ex) {}
        catch (InvalidAlgorithmParameterException ex) {}

        return new String(desencriptado);
    }

    /**
     * Convierte un arreglo de bytes a una cadena String
     *
     * @param buffer
     * @retur <code>String</code>
     */
    public String bytesAHex(byte[] buffer) {
        char[] chars = new char[2 * buffer.length];
        for (int i = 0; i < buffer.length; ++i) {
            chars[2 * i] = CARACTERES_HEX[(buffer[i] & 0xF0) >>> 4];
            chars[2 * i + 1] = CARACTERES_HEX[buffer[i] & 0x0F];
        }
        return new String(chars);
    }

    /**
     * Convierte una cadena(String) a un arreglo de bytes.
     *
     * @param cadena
     * @retur <code>byte[]</code>
     */
    public byte[] hexABytes(String cadena) {
        if (cadena == null) {
            return null;
        } else if (cadena.length() < 2) {
            return null;
        } else {
            int len = cadena.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(cadena.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }
    }


    private String padString(String source) {
        char paddingChar = 0;
        int size = 16;
        int x = source.length() % size;
        int padLength = size - x;

        for (int i = 0; i < padLength; i++) {
            source += paddingChar;
        }

        return source;
    }
}