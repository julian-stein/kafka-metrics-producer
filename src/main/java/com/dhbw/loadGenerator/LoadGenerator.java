package com.dhbw.loadGenerator;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class LoadGenerator implements Runnable{
    private final byte[] keyBytes;
    public LoadGenerator(byte[] keyBytes) {
        this.keyBytes = keyBytes;
    }

    @Override
    public void run() {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, "AES"));
            while(true) {
                ArrayList<String> mylist = new ArrayList<>();
                byte[] text = RandomStringUtils.randomAlphabetic(10000, 1000000).getBytes(StandardCharsets.UTF_8);
                for(int i = 0; i < ThreadLocalRandom.current().nextInt(10000, 1000000); ++i) {
                    text = cipher.update(text);
                    mylist.add(RandomStringUtils.randomAlphabetic(1, 10000));
                }
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
