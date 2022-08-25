package br.com.bruno2code.contrateai.model.places;

import java.util.ArrayList;

public class Places {

    private String next_page_token = "";
    private ArrayList<Place> results = new ArrayList<>();

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public ArrayList<Place> getResults() {
        return results;
    }

    public void setResults(ArrayList<Place> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Places [next_page_token=" + next_page_token + ", results=" + results + "]";
    }

}
