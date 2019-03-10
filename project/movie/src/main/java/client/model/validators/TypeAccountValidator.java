package client.model.validators;

import client.model.entity.User;


public class TypeAccountValidator implements Validators<User>  {
    private static final int ACC1 = 0;
    private static final int ACC2 = 1;


    public void validate(User t) {

        if (t.getTypeAccount() != ACC1 && t.getTypeAccount() != ACC2) {
            throw new IllegalArgumentException("account tip gresit!");
        }

    }

}