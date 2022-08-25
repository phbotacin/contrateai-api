package br.com.bruno2code.contrateai.api;

import br.com.bruno2code.contrateai.model.Message;
import br.com.bruno2code.contrateai.model.places.Photo;
import br.com.bruno2code.contrateai.model.places.Place;
import br.com.bruno2code.contrateai.model.places.PlaceDetails;
import br.com.bruno2code.contrateai.model.places.Places;
import br.com.bruno2code.contrateai.persist.DAO;
import br.com.bruno2code.contrateai.persist.e010comDAO;
import br.com.bruno2code.contrateai.persist.e030detDAO;
import br.com.bruno2code.contrateai.persist.e030plaDAO;
import br.com.bruno2code.contrateai.persist.e031phoDAO;
import br.com.bruno2code.contrateai.persist.e032typDAO;
import br.com.bruno2code.contrateai.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlacesApi {

    e030plaDAO dao = new e030plaDAO();
    Util util = new Util();

    final String KEY = "AIzaSyCgRz9Ruv4uSSMc2EzhaFUbSF3xZ_eOf7k";

    public Message getPlaces(String nextToken, String latlgn, int radius) {

        final Places places = getJsonPlaces(latlgn, nextToken, radius);

        try {
            try ( Connection c = DAO.getConexaoMySql()) {
                for (Place result : places.getResults()) {
                    nextToken = places.getNext_page_token();
                    if (dao.save(c, result)) {
                        final PlaceDetails placeDetails = new PlaceDetailsApi().getJson(result.getPlace_id());

                        new e030detDAO().save(placeDetails);

                        for (Photo photo : result.getPhotos()) {
                            new e031phoDAO().save(c, result.getPlace_id(), photo);
                        }
                        for (String type : result.getTypes()) {
                            new e032typDAO().save(c, result.getPlace_id(), type);
                        }
                    }
                }
                new e032typDAO().preencheDesTyp(c);
                new e010comDAO().saveFromGoogle(1);
            }
        } catch (SQLException e) {
            new LogApi().sendLog("GoogleApi", "getPlaces", e.toString());
        }

        return new Message(true, nextToken);
    }

    public Places getJsonPlaces(String latLgn, String nextToken, int radius) {

        Places places = new Places();

        try {
            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?radius=" + radius + "&language=pt-br&location=" + latLgn + "&key=" + KEY;

            if (!util.vazio(nextToken)) {
                url += "&pagetoken=" + nextToken;
            }

            final OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            final Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .build();
            final Response response = client.newCall(request).execute();

            if (response.code() == 200) {
                places = new Gson().fromJson(response.body().string(), Places.class);
            }
        } catch (JsonSyntaxException | IOException e) {
            new LogApi().sendLog("GoogleApi", "getJsonPlaces", e.toString());
        }
        return places;
    }

}
