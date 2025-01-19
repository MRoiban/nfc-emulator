package com.example.nfcemulator;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import java.util.Arrays;

public class MyHostApduService extends HostApduService {
    private static final byte[] SELECT_APDU = {
        (byte) 0x00, // CLA
        (byte) 0xA4, // INS
        (byte) 0x04, // P1
        (byte) 0x00, // P2
        (byte) 0x07, // Lc
        (byte) 0xD2, (byte) 0x76, (byte) 0x00, (byte) 0x00, 
        (byte) 0x85, (byte) 0x01, (byte) 0x01,
        (byte) 0x00  // Le
    };

    private static final byte[] SUCCESS_SW = {(byte) 0x90, (byte) 0x00};

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
        if (Arrays.equals(SELECT_APDU, commandApdu)) {
            String message = "Hello NFC Reader!";
            byte[] messageBytes = message.getBytes();
            byte[] response = new byte[messageBytes.length + 2];
            System.arraycopy(messageBytes, 0, response, 0, messageBytes.length);
            System.arraycopy(SUCCESS_SW, 0, response, messageBytes.length, 2);
            return response;
        }
        return SUCCESS_SW;
    }

    @Override
    public void onDeactivated(int reason) {
    }
}