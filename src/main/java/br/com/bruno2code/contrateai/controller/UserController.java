package br.com.bruno2code.contrateai.controller;

import br.com.bruno2code.contrateai.model.Message;
import br.com.bruno2code.contrateai.persist.UsuarioDAO;
import br.com.bruno2code.contrateai.security.Credencial;
import br.com.bruno2code.contrateai.security.JwtTokenUtil;
import br.com.bruno2code.contrateai.security.User;
import br.com.bruno2code.contrateai.util.Util;
import br.com.bruno2code.contrateai.util.ValidaPassword;
import com.google.gson.Gson;
import java.sql.Date;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    Util util = new Util();

    @PostMapping(value = ("/login"), consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestParam Map<String, String> body) {

        if (util.vazio(body.get("intNet"))) {
            return new Gson().toJson(new Message(false, "Preencha o email"));
        }

        if (util.vazio(body.get("senUsu"))) {
            return new Gson().toJson(new Message(false, "Preencha a senha"));
        }

        User u = new UsuarioDAO().getLogin(new Credencial(body.get("intNet"), body.get("senUsu")));

        if (u != null) {
            if (u.getSitPes().equals("A")) {
                new UsuarioDAO().registraUltLog(u.getIntNet());
                return new Gson().toJson(new Message(true, new JwtTokenUtil().generateToken(u), u));
            } else {
                return new Gson().toJson(new Message(false, "Usuario inativo!"));
            }
        } else {
            return new Gson().toJson(new Message(false, "Credenciais inválidas"));
        }
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestParam Map<String, String> body) {

        User u = new User();

        if (!util.vazio(body.get("nomUsu"))) {
            u.setNomUsu(body.get("nomUsu").toLowerCase());
        } else {
            return new Gson().toJson(new Message(false, "Informe o nome!"));
        }

        if (!util.vazio(body.get("intNet"))) {
            u.setIntNet(body.get("intNet").toLowerCase());
        } else {
            return new Gson().toJson(new Message(false, "Informe o email!"));
        }

        if (!util.vazio(body.get("fonCel"))) {
            u.setFonCel(body.get("fonCel").toLowerCase());
        } else {
            return new Gson().toJson(new Message(false, "Informe o telefone!"));
        }

        if (new ValidaPassword().isValid(body.get("senUsu"))) {
            u.setSenUsu(body.get("senUsu").toLowerCase());
        } else {
            return new Gson().toJson(new Message(false, "Senha muito fraca! "));
        }

        if (!util.vazio(body.get("datNas"))) {
            u.setDatNas(Date.valueOf(body.get("datNas").toLowerCase()));
        } else {
            return new Gson().toJson(new Message(false, "Informe a data de nascimento!"));
        }

        return new Gson().toJson(new UsuarioDAO().saveUser(u));
    }
}
