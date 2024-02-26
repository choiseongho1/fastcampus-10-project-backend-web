package org.example.password;

public class PasswordValidator {

    public static final String WRONG_PASSWORD_LENGTH_EXCEPTION_MESSAGE = "비밀번호는 최소 8자 최대 12자 이하여야한다.";

    public static void validate(String password) throws Exception{
        int length = password.length();

        if(length < 8 || length > 12){
            throw new IllegalAccessException(WRONG_PASSWORD_LENGTH_EXCEPTION_MESSAGE);
        }
    }
}
