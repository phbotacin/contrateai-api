package br.com.bruno2code.contrateai.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaPassword {

    final String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";

    public boolean isValid(String password) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
