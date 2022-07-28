package project.entities.views;

public class PetView {

    private String name;
    private int age;
    private int picNumber;

    public PetView() {
    }

    public PetView(String name, int age, int picNumber) {
        this.name = name;
        this.age = age;
        this.picNumber = picNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPicNumber() {
        return picNumber;
    }

    public void setPicNumber(int picNumber) {
        this.picNumber = picNumber;
    }
}
