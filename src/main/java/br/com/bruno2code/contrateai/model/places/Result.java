package br.com.bruno2code.contrateai.model.places;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private List<AddressComponent> address_components = new ArrayList();
    private String adr_address;
    private String business_status;
    private String formatted_address;
    private String formatted_phone_number;
    private Geometry geometry = new Geometry();
    private String icon;
    private String icon_background_color;
    private String icon_mask_base_uri;
    private String international_phone_number;
    private String name;
    private OpeningHours opening_hours = new OpeningHours();
    private List<Photo> photos = new ArrayList();
    private String place_id;
    private PlusCode plus_code = new PlusCode();
    private double rating;
    private String reference;
    private List<Review> reviews = new ArrayList();
    private List<String> types = new ArrayList();
    private String url;
    private int user_ratings_total;
    private int utc_offset;
    private String vicinity;
    private String website;

    public List<AddressComponent> getAddress_components() {
        return address_components;
    }

    public void setAddress_components(List<AddressComponent> address_components) {
        this.address_components = address_components;
    }

    public String getAdr_address() {
        return adr_address;
    }

    public void setAdr_address(String adr_address) {
        this.adr_address = adr_address;
    }

    public String getBusiness_status() {
        return business_status;
    }

    public void setBusiness_status(String business_status) {
        this.business_status = business_status;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getFormatted_phone_number() {
        return formatted_phone_number;
    }

    public void setFormatted_phone_number(String formatted_phone_number) {
        this.formatted_phone_number = formatted_phone_number;
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

    public String getInternational_phone_number() {
        return international_phone_number;
    }

    public void setInternational_phone_number(String international_phone_number) {
        this.international_phone_number = international_phone_number;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUser_ratings_total() {
        return user_ratings_total;
    }

    public void setUser_ratings_total(int user_ratings_total) {
        this.user_ratings_total = user_ratings_total;
    }

    public int getUtc_offset() {
        return utc_offset;
    }

    public void setUtc_offset(int utc_offset) {
        this.utc_offset = utc_offset;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Result [address_components=" + address_components + ", adr_address=" + adr_address
                + ", business_status=" + business_status + ", formatted_address=" + formatted_address
                + ", formatted_phone_number=" + formatted_phone_number + ", geometry=" + geometry + ", icon=" + icon
                + ", icon_background_color=" + icon_background_color + ", icon_mask_base_uri=" + icon_mask_base_uri
                + ", international_phone_number=" + international_phone_number + ", name=" + name + ", opening_hours="
                + opening_hours + ", photos=" + photos + ", place_id=" + place_id + ", plus_code=" + plus_code
                + ", rating=" + rating + ", reference=" + reference + ", reviews=" + reviews + ", types=" + types
                + ", url=" + url + ", user_ratings_total=" + user_ratings_total + ", utc_offset=" + utc_offset
                + ", vicinity=" + vicinity + ", website=" + website + "]";
    }

}
