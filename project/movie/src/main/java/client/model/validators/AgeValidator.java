package client.model.validators;
import client.model.entity.User;

public class AgeValidator implements Validators<User> {
    private static final int MAX_AGE = 121;
    private static final int MIN_AGE = 12;


    public void validate(User user) {
        if (user.getAge() < MIN_AGE || user.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("Varsta introdusa  nu se incadreaza in limitele admise!");
        }

    }



}
