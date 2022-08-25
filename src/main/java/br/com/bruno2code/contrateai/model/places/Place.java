package br.com.bruno2code.contrateai.model.places;

import java.util.ArrayList;
import java.util.List;

public class Place {

    private String business_status;
    private Geometry geometry = new Geometry();
    private String icon;
    private String icon_background_color;
    private String icon_mask_base_uri;
    private String name;
    private OpeningHours opening_hours = new OpeningHours();
    private List<Photo> photos = new ArrayList<>();
    private String place_id;
    private PlusCode plus_code = new PlusCode();
    private double rating;
    private String reference;
    private String scope;
    private List<String> types = new ArrayList<>();
    private int user_ratings_total;
    private String vicinity;

    public String getBusiness_status() {
        return business_status;
    }

    public void setBusiness_status(String business_status) {
        this.business_status = business_status;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon_background_color() {
        return icon_background_color;
    }

    public void setIcon_background_color(String icon_background_color) {
        this.icon_background_color = icon_background_color;
    }

    public String getIcon_mask_base_uri() {
        return icon_mask_base_uri;
    }

    public void setIcon_mask_base_uri(String icon_mask_base_uri) {
        this.icon_mask_base_uri = icon_mask_base_uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(OpeningHours opening_hours) {
        this.opening_hours = opening_hours;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public PlusCode getPlus_code() {
        return plus_code;
    }

    public void setPlus_code(PlusCode plus_code) {
        this.plus_code = plus_code;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public int getUser_ratings_total() {
        return user_ratings_total;
    }

    public void setUser_ratings_total(int user_ratings_total) {
        this.user_ratings_total = user_ratings_total;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    @Override
    public String toString() {
        return "Place [business_status=" + business_status + ", geometry=" + geometry + ", icon=" + icon
                + ", icon_background_color=" + icon_background_color + ", icon_mask_base_uri=" + icon_mask_base_uri
                + ", name=" + name + ", opening_hours=" + opening_hours + ", photos=" + photos + ", place_id="
                + place_id + ", plus_code=" + plus_code + ", rating=" + rating + ", reference=" + reference + ", scope="
                + scope + ", types=" + types + ", user_ratings_total=" + user_ratings_total + ", vicinity=" + vicinity
                + "]";
    }

}
