package br.com.bruno2code.contrateai.util;

import java.sql.Date;

public class Util {

    public boolean vazio(String pString) {

        boolean esta = false;

        if (pString == null) {
            esta = true;
        } else {
            if (pString.isEmpty() || pString.equals("0") || pString.equals("null")) {
                esta = true;
            }
        }
        return esta;
    }

    public boolean vazio(double pDouble) {
        return pDouble == 0;
    }

    public boolean vazio(int pInt) {
        return pInt == 0;
    }

    public boolean vazio(Date pDate) {
        return pDate.after(Date.valueOf("2000-01-01"));
    }

    public String spanValue(Object span, String tag) {

        String value = "";

        try {
            if (span != null) {
                if (!vazio(span.toString())) {
                    value = span.toString().split("<span class=\"" + tag + "\">")[1];
                    value = value.split("</span>")[0];
                }

            }
        } catch (Exception e) {
        }
        return value;
    }
}
