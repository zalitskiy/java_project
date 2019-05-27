package ru.stqa.pft.mantis.model;


import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "mantis_user_table")
public class AccountData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "username")
    private String username;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Expose
    @Column(name = "password")
    @Type(type = "text")
    private String password;

    @Expose
    @Column(name = "enabled")
    @Type(type = "text")
    private String enabled;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountData that = (AccountData) o;
        return id == that.id &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
