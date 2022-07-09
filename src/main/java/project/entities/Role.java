package project.entities;

import project.entities.enums.RoleCategory;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleCategory name;

    public Role() {
    }

    public Role(RoleCategory role) {
        this.name = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleCategory getName() {
        return name;
    }

    public void setName(RoleCategory name) {
        this.name = name;
    }
}
