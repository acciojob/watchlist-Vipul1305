package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

    //DB
    HashMap<String,Movie> movieDB = new HashMap<>();//db to stores all movie
    HashMap<String,Director> directorDB = new HashMap<>();//db of all director
    HashMap<String, List<String>> listDB = new HashMap<>(); //db all director wise move

    public void addMovie(Movie movie){
        movieDB.put(movie.getName(),movie);
    }
    public void addDirector(Director director) {
        directorDB.put(director.getName(),director);
    }
    public void addMovieDirectorPair(String movieName, String directorName){
        if(!listDB.isEmpty() && listDB.containsKey(directorName)){
            List<String> list = listDB.get(directorName);
            list.add(movieName);
            listDB.put(directorName,list);
        }else {
            List<String> list = new ArrayList<>();
            list.add(movieName);
            listDB.put(directorName,list);
        }
    }
    public Movie getMovieByName(String name){
        return movieDB.get(name);
    }
    public Director getDirectorByName(String name){
        return directorDB.get(name);
    }
    public List<String> getMoviesByDirectorName(String director){
        return listDB.get(director);
    }
    public List<String> findAllMovies(){
        List<String> list = new ArrayList<>();
        for (String x: movieDB.keySet()){
            list.add(x);
        }
        return list;
    }
    public void deleteDirectorByName(String name){
        List<String> movie = listDB.get(name);
        for (String m: movie){
            movieDB.remove(m);
        }
        directorDB.remove(name);
        listDB.remove(name);
    }
    public void deleteAllDirectors(){
        for (String directorName: listDB.keySet()){
            for (String movie: listDB.get(directorName)){
                movieDB.remove(movie);
            }
        }
        directorDB.clear();
        listDB.clear();
    }
}
