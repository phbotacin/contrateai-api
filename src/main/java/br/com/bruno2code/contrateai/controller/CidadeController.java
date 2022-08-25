package br.com.bruno2code.contrateai.controller;

import br.com.bruno2code.contrateai.persist.CidadeDAO;
import br.com.bruno2code.contrateai.util.Util;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    Util util = new Util();
    CidadeDAO dao = new CidadeDAO();

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public String list(@RequestParam(name = "page", required = true) int page,
            @RequestParam(name = "per_page", required = true) int per_page) {

        return new Gson().toJson(dao.list(page, per_page));
    }

}
