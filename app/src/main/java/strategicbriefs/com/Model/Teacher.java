package strategicbriefs.com.Model;

import com.google.firebase.database.Exclude;

public class Teacher {
    private String name;
    private String imageURL;
    private String key;
    private String description;
    private String KeyFriends;
    private String Link;
    private int position;

    public Teacher() {
        //empty constructor needed
    }
    public Teacher (int position){
        this.position = position;
    }
    public Teacher(String name, String imageUrl ,String Des, String keyfriends, String link) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        this.name = name;
        this.imageURL = imageUrl;
        this.description = Des;
        this.KeyFriends = keyfriends;
        this.Link = link;
    }

    public String getKeyFriends() {
        return KeyFriends;
    }

    public void setKeyFriends(String keyFriends) {
        KeyFriends = keyFriends;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return imageURL;
    }
    public void setImageUrl(String imageUrl) {
        this.imageURL = imageUrl;
    }
    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}
