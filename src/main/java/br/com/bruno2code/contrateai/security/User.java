package br.com.bruno2code.contrateai.security;

import java.util.Date;

public class User {

    private int codUsu;
    private String nomUsu;
    private String intNet;
    private String fonCel;
    private String senUsu;
    private Date datNas;
    private int tipPes;
    private String desTip;
    private String sitPes;

    public int getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(int codUsu) {
        this.codUsu = codUsu;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getIntNet() {
        return intNet;
    }

    public void setIntNet(String intNet) {
        this.intNet = intNet;
    }

    public String getFonCel() {
        return fonCel;
    }

    public void setFonCel(String fonCel) {
        this.fonCel = fonCel;
    }

    public String getSenUsu() {
        return senUsu;
    }

    public void setSenUsu(String senUsu) {
        this.senUsu = senUsu;
    }

    public Date getDatNas() {
        return datNas;
    }

    public void setDatNas(Date datNas) {
        this.datNas = datNas;
    }

    public int getTipPes() {
        return tipPes;
    }

    public void setTipPes(int tipPes) {
        this.tipPes = tipPes;
    }

    public String getDesTip() {
        return desTip;
    }

    public void setDesTip(String desTip) {
        this.desTip = desTip;
    }

    public String getSitPes() {
        return sitPes;
    }

    public void setSitPes(String sitPes) {
        this.sitPes = sitPes;
    }

}
