package br.com.bruno2code.contrateai.controller;

import br.com.bruno2code.contrateai.persist.e020catDAO;
import br.com.bruno2code.contrateai.util.Util;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class e020catController {

    Util util = new Util();
    e020catDAO dao = new e020catDAO();

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String list(
            @RequestParam(name = "nomCat", required = false) String pNomCat,
            @RequestParam(name = "page", required = true) int page,
            @RequestParam(name = "per_page", required = true) int per_page) {

        return new Gson().toJson(dao.list(page, per_page, pNomCat));
    }
}
