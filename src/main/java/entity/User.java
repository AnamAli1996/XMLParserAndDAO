package entity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@NamedQueries( {
        @NamedQuery(name = "User.findAll", query = "select o from User o"),
        @NamedQuery(name = "User.findByEmail", query = "select o from User o where o.email=:email"),
        @NamedQuery(name = "User.findById", query = "select o from User o where o.id=:id")

})

@XmlRootElement(name = "user")

@Entity
public class User {

    //every entity requires an id, and we can make it auto generated
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToOne
    Library library;

    public User() {

    }


    public User(String fn, String ln, String email, String password) {
        super();
        this.firstName = fn;
        this.lastName = ln;
        this.password = password;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public void setFirstName(String fn) {
        this.firstName = fn;
    }


    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public void setLastName(String ln) {
        this.lastName = ln;
    }

    @XmlElement
    public String getEmail() {
        return email;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }


    @XmlElement
    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



