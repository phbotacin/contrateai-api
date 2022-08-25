package br.com.bruno2code.contrateai.api;

import br.com.bruno2code.contrateai.model.Message;
import br.com.bruno2code.contrateai.model.places.PlaceDetails;
import br.com.bruno2code.contrateai.persist.e030detDAO;
import br.com.bruno2code.contrateai.persist.e030plaDAO;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlaceDetailsApi {

    public Message getPlacesDetails() {

        try {
            final List<String> places = new e030plaDAO().getListPlaceId();

            for (String place : places) {
                final PlaceDetails placeDetails = new PlaceDetailsApi().getJson(place);
                new e030detDAO().save(placeDetails);

            }
            return new Message(true, "Ok");
        } catch (Exception e) {
            new LogApi().sendLog("PlaceDetailsApi", "getPlacesDetails", e.toString());
            return new Message(false, e.toString());
        }
    }

    public PlaceDetails getJson(String place_id) {

        PlaceDetails obj = new PlaceDetails();

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://maps.googleapis.com/maps/api/place/details/json?place_id=" + place_id + "&key=AIzaSyCgRz9Ruv4uSSMc2EzhaFUbSF3xZ_eOf7k")
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();

            if (response.code() == 200) {
                obj = new Gson().fromJson(response.body().string(), PlaceDetails.class);
            }
        } catch (JsonSyntaxException | IOException e) {
            new LogApi().sendLog("PlaceDetailsApi", "getJson", e.toString());
        }
        return obj;
    }
}
