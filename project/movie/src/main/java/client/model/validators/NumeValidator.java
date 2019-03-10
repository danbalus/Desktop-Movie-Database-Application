package client.model.validators;

import client.model.entity.EntityApp;

import java.util.regex.Pattern;

public class NumeValidator implements Validators<EntityApp> {
    private static final String NAME_PATTERN = "^[a-zA-Z ]*$";

    public void validate(EntityApp entityApp) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(entityApp.getName()).matches())
        {
            throw new IllegalArgumentException("Atestcesta  NU este un nume valid!");
        }
    }
}
