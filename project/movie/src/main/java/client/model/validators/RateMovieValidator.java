package client.model.validators;

import client.model.entity.Movie;

public class RateMovieValidator implements Validators<Movie>{
        private static final int MAX_RATE = 10;
        private static final int MIN_RATE = 1;


        public void validate(Movie movie) {
            if (movie.getRating() < MIN_RATE || movie.getRating() > MAX_RATE) {
                throw new IllegalArgumentException("Nota introdusa  nu se incadreaza in limitele admise!");
            }

        }
}
