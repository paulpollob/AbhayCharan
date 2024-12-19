package com.abhaycharanvoice.abhaycharan.Util;

import java.util.UUID;

public class GenerateValue {
    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public static String generateDamageInvoice(){
        return "DMG-"+UUID.randomUUID().toString();
    }
}
