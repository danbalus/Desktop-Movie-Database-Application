package client.model.validators;

import java.util.regex.Pattern;

public interface Validators<T> {

    public void validate(T t);

   default public void validateId(int id) {
        Pattern pattern = Pattern.compile("[0-9]+");
        if (!pattern.matcher(Integer.toString(id)).matches())
        {
            throw new IllegalArgumentException("Acesta  NU este un id valid!");
        }
    }
}
