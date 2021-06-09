package com.app.Exception;

import com.framework.Exception.FormException;

public class PhoneNumberDigitsException extends FormException {
    public PhoneNumberDigitsException() {
        super("Numéro de téléphone devant faire 10 chiffres");
    }

}
