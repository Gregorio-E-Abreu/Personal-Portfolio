public class Pioneer {

    private String name;
    private String country;
    private String bio;
    private int photoResId;

    public Pioneer(String name, String country, String bio, int photoResId) {
        this.name = name;
        this.country = country;
        this.bio = bio;
        this.photoResId = photoResId;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getBio() {
        return bio;
    }

    public int getPhotoResId() {
        return photoResId;
    }
}

