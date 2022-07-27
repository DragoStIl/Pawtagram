package project.entities.views;

public class UserView {

    private String username;

    private String fullName;

    private int age;

    private int ownedAnimals;

    private int postedPictures;

    public UserView() {
    }

    public UserView(String username, String fullName, int age, int ownedAnimals, int postedPictures) {
        this.username = username;
        this.fullName = fullName;
        this.age = age;
        this.ownedAnimals = ownedAnimals;
        this.postedPictures = postedPictures;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getOwnedAnimals() {
        return ownedAnimals;
    }

    public void setOwnedAnimals(int ownedAnimals) {
        this.ownedAnimals = ownedAnimals;
    }

    public int getPostedPictures() {
        return postedPictures;
    }

    public void setPostedPictures(int postedPictures) {
        this.postedPictures = postedPictures;
    }
}


