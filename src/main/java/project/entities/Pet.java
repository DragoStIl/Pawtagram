package project.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String name;

    @Column(nullable = false)
    @Min(1)
    private int age;

    @ManyToOne
    private TypeEntity typeEntity;

    @Column(columnDefinition = "TEXT", unique = true, nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Photo> photos;




    public Pet() {
        this.photos = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public TypeEntity getType() {
        return typeEntity;
    }

    public void setType(TypeEntity typeEntity) {
        this.typeEntity = typeEntity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(TypeEntity typeEntity) {
        this.typeEntity = typeEntity;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
