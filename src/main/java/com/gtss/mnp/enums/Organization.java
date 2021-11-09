package com.gtss.mnp.enums;

public enum Organization {
    vodafone("012"), etisalat("011"), orange("012");


    private final String prefix;


    Organization(String prefix) {

        this.prefix = prefix;

    }

    public String getPrefix() {
        return prefix;
    }
}
