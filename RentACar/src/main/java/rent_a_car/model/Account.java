package rent_a_car.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account", schema = "public")
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "account")
    private List<Booking> bookingSet;

    public Account(){
        bookingSet= new ArrayList<Booking>();
    }

    public Account(Account account) {
        this.username= account.username;
        this.password= account.password;
        this.email= account.email;
        this.name= account.name;
        this.role= account.role;
    }

    public Account(String username, String password, String email, String name, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(List<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
