package org.railway.enums;

public enum ResetPassword {
    NEWPASSWORD("New Password"),
    CONFIRMPASSWORD("Confirm new password"),
    RESETBUTTON("Reset password");

    private String value;

    ResetPassword(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
