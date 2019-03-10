package client.model.validators;

import client.model.entity.EntityApp;

import java.util.regex.Pattern;

public class IdValidators implements Validators<EntityApp> {
    private static final String ID_PATTERN ="[0-9]+";

    public void validate(EntityApp entityApp) {
        Pattern pattern = Pattern.compile(ID_PATTERN);
        if (!pattern.matcher(Integer.toString(entityApp.getId())).matches())
        {
            throw new IllegalArgumentException("Acesta  NU este un numar de ID valid!");
           // throw new idException("f");
        }
    }
    public void validateId(int id) {
        Pattern pattern = Pattern.compile(ID_PATTERN);
        if (!pattern.matcher(Integer.toString(id)).matches())
        {
            throw new IllegalArgumentException("Acesta  NU este un numar de ID valid!");
            // throw new idException("f");
        }
    }

}