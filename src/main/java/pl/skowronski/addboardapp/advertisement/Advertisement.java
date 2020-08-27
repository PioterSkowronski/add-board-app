package pl.skowronski.addboardapp.advertisement;

import pl.skowronski.addboardapp.category.Category;
import pl.skowronski.addboardapp.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Integer id;
    @Column(length=100,
            nullable = false)
    private String title;
    @Column(length=1500,
            nullable = false)
    private String description;
    @Column(scale = 2,
            nullable = false)
    private double price;
    private LocalDateTime created;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() { created = LocalDateTime.now(); }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", created=" + created +
                ", category=" + category +
                ", user=" + user +
                '}';
    }
}
