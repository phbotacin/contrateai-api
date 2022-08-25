package br.com.bruno2code.contrateai.model.places;

import java.util.ArrayList;
import java.util.List;

public class PlaceDetails {

    private List<Object> html_attributions = new ArrayList();
    private Result result = new Result();
    private String status;

    public List<Object> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<Object> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PlaceDetails [html_attributions=" + html_attributions + ", result=" + result + ", status=" + status
                + "]";
    }

}
