package client.model.feature;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import client.model.entity.Movie;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * Clasa care tipareste chitanta
 */
public class TxtMovieGenerate implements Raport {

    private static String[] c;// = new String[900];
    private Random r = new Random();
    private static String FILE;//= "c:/Users/DanB/Downloads/aaaa/fisrt.pdf";

    public TxtMovieGenerate(String file) {
        FILE = file;
    }

    /**
     * metoda aceasta scrie in fisier chitanta propriu-zisa
     */

    public void start(ArrayList<Movie> movieList) {
        c = new String[movieList.size() * 5 + 2];
        c[0] = "Nr. of Raport: ";
        c[1] = "Info MOVIES:";
        c[2] = "\tName: ";
        c[3] = "\tGenre: ";
        c[4] = "\tInfo: ";
        c[5] = "\tRate: ";
        c[6] = "\tActors:";

        try {
            FileWriter fstream = new FileWriter(FILE);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(c[0] + r.nextInt(88888));
            out.newLine();
            out.newLine();
            out.write(c[1]);
            for (Movie movie : movieList) {
                out.newLine();
                out.write(c[2] + movie.getName() + "");
                out.newLine();
                out.write(c[3] + movie.getGenre() + "");
                out.newLine();
                out.write(c[4] + movie.getTitle() + "");
                out.newLine();
                out.write(c[5] + movie.getRating() + "");
                out.newLine();
               // out.write(c[6] + movie.getActorsNameAsStringList() + "");
                out.newLine();
                out.newLine();
            }
            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}

