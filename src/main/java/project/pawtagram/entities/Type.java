package project.pawtagram.entities;

import project.pawtagram.entities.enums.AnimalType;

import javax.persistence.*;

@Entity
@Table(name = "types")
public class Type {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalType type;

    public Type() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }
}
