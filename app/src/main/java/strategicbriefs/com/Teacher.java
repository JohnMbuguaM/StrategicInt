package strategicbriefs.com;

import com.google.firebase.database.Exclude;

public class Teacher {

    private String name, description, keyFriends, key, OtherNames, imageURI;
    private int position;



    public Teacher(String trim, String s, String toString, String string, String s1) {
//        Empty constructor needed

    }


    public Teacher(int position) {
        this.position = position;

    }

    public Teacher(String Des, String keyFriends, String imageURI, String key) {

        if (name.trim().equals("")) {
            name = "No Name";
        }

        this.name = name;
        this.description = Des;
        this.keyFriends = keyFriends;
        this.imageURI = imageURI;
        this.OtherNames= OtherNames;
    }

    public String getOtherNames() {
        return OtherNames;
    }

    public void setOtherNames(String otherNames) {
        OtherNames = otherNames;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getKeyFirends() {
        return keyFriends;
    }

    public String getImageURI() {
        return imageURI;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKeyFirends(String keyFriends) {
        this.keyFriends = keyFriends;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
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



