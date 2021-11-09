package com.gtss.mnp.utility;


public final class Helper {
    private Helper() {
    }

    public static boolean isValidPortingRequest(String recipient, String donor, String currentMobileHolder) {
        return currentMobileHolder.equals(donor) && !recipient.equals(donor);
    }

    public static boolean operatorCanAcceptOrReject(String donor, String currentMobileHolder) {
        return currentMobileHolder.equals(donor);
    }

    public static boolean validOperator(String name, String prefix) {
        return name.equals("vodafone") && prefix.equals("010") ||
                name.equals("etisalat") && prefix.equals("011") ||
                name.equals("orange") && prefix.equals("012");
    }
}
