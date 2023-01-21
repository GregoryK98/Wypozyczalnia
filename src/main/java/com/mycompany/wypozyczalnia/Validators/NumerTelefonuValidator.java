package com.mycompany.wypozyczalnia.Validators;

public class NumerTelefonuValidator {

    private String[] _digits = new String[]{"0","1","2","3","4","5","6","7","8","9"};

    boolean validate(String numer) {
        if(numer == null)
            return false;
        if(numer.isEmpty())
            return false;
        numer.replace(" ", "");
        if(numer.length() < 2)
            return false;
        if(numer.startsWith("+"))
            numer = numer.substring(1);
        if(numer.matches(".*[^0-9].*"))
            return false;
        return true;
    }
}
