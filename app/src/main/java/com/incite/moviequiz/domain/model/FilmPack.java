package com.incite.moviequiz.domain.model;

import java.util.ArrayList;

public class FilmPack {

    int size = 0;
    int number;
    ArrayList<Film> films;
    int ptr = 0;

    public FilmPack(int number){
        this.number = number;

        films = new ArrayList<>();
    }

    void addFilms(String ... filmData){

        for (int i = 0; i < filmData.length; i++) {
            StringBuilder filmName = new StringBuilder();
            StringBuilder drawableID = new StringBuilder();
            int p = 0;
            while (filmData[i].charAt(p)!='|') {
                filmName.append(filmData[i].charAt(p));
                p++;
            }
            p++;
            while (p < filmData[i].length()) {
                drawableID.append(filmData[i].charAt(p));
                p++;
            }
            films.add(new Film(filmName.toString(), Integer.parseInt(String.valueOf(drawableID))));
            size++;
        }
    }

    void addFilms(Film ... films_l){

        for (Film film : films_l) {
            films.add(film);
            size++;
        }

    }

    public int size() {
        return size;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

}
