package br.com.bruno2code.contrateai.controller;

import br.com.bruno2code.contrateai.api.PlacesApi;
import br.com.bruno2code.contrateai.util.Util;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/places")
public class e030plaController {

    Util util = new Util();
    PlacesApi api = new PlacesApi();

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String list(
            @RequestParam(name = "latlgn", required = false) String latlgn,
            @RequestParam(name = "nexttoken", required = false) String nexttoken,
            @RequestParam(name = "radius", required = true) int radius
    ) {
        return new Gson().toJson(api.getPlaces(nexttoken, latlgn, radius));
    }
}
