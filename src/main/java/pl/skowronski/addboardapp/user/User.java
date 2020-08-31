package pl.skowronski.addboardapp.user;


import org.hibernate.validator.constraints.Length;
import pl.skowronski.addboardapp.Role.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Imię jest wymagane")
    private String firstName;
    @NotBlank(message = "Nazwisko jest wymagane")
    private String lastName;
    @NotBlank(message = "Podaj hasło")
    @Length(min = 5, message = "Hasło musi składać się z co najmniej 5 znaków")
    private String password;
    @NotBlank(message = "Nazwa użytkownika jest wymagana")
    private String userName;
    @Length(min = 9, max = 18, message = "Numer telefonu powinien zawierać od 9 do 18 znaków")
    private String phoneNumber;
    @Email(message = "Email nieprawidłowy")
    @NotBlank(message = "Email jest wymagany")
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles;

    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
