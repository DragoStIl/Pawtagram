package project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private String name;

    @Column()
    private int age;

    @ManyToOne
    private TypeEntity typeEntity;

    @Column(columnDefinition = "TEXT", unique = true)
    private String imageUrl;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "pet")
    private List<Comment> comments;

    public Pet() {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
