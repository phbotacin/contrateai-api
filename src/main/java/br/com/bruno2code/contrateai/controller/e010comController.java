package br.com.bruno2code.contrateai.controller;

import br.com.bruno2code.contrateai.persist.e010comDAO;
import br.com.bruno2code.contrateai.security.JwtTokenUtil;
import br.com.bruno2code.contrateai.util.Util;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comercio")
public class e010comController {

    Util util = new Util();
    e010comDAO dao = new e010comDAO();

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestHeader("Authorization") String bearer, @RequestParam Map<String, Object> body) {

        Claims claims = new JwtTokenUtil().getAllClaimsFromToken(bearer);
        body.put("usuCad", claims.get("codUsu"));

        return new Gson().toJson(dao.saveFromApp(body));
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public String list(
            @RequestParam(name = "page", required = true) int page,
            @RequestParam(name = "per_page", required = true) int per_page,
            @RequestParam(name = "codCat", required = false) int pCodCat,
            @RequestParam(name = "nomCom", required = false) String pNomCom,
            @RequestParam(name = "locLat", required = false) double pLocLat,
            @RequestParam(name = "locLgn", required = false) double pLocLgn
    ) {

        return new Gson().toJson(dao.list(page, per_page, pCodCat, pNomCom, pLocLat, pLocLgn));
    }

    @GetMapping(value = "/integraGoogle", produces = MediaType.APPLICATION_JSON_VALUE)
    public String integraGoogle(@RequestHeader("Authorization") String bearer) {

        Claims claims = new JwtTokenUtil().getAllClaimsFromToken(bearer);
        return new Gson().toJson(new e010comDAO().saveFromGoogle(claims.get("codUsu")));

    }

}
