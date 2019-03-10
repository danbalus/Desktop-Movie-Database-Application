package client.model.validators;

import client.model.entity.User;

import java.util.regex.Pattern;

public class NicknameValidator implements Validators<User> {
    private static final String NICKNAME_PATTERN = "^[a-zA-Z0-9]*$";

    public void validate(User user) {
        Pattern pattern = Pattern.compile(NICKNAME_PATTERN);
        if (!pattern.matcher(user.getId()).matches())
        {
            throw new IllegalArgumentException("Atestcesta  NU este un id nickname valid!");
        }
    }
}
