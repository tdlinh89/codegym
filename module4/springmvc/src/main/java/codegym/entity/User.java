package codegym.entity;

import codegym.validator.AgeFormat;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQueries({
        @NamedQuery(name="findCustomerByAge", query = "select u from User u where u.age = ?1"),
        @NamedQuery(name="findCustomerByName", query = "select u from User u where u.name like :name"),
})
public class User {
    @Id
    @GeneratedValue
    private int id;

    @NotEmpty(message = "Tên không được để trống")
    @Size(min = 4, max = 20, message = "Tên phải có độ dài từ 4 tới 20 ký tự")
    private String name;

//    @Min(value = 18, message = "Tuổi phải lớn hơn hoặc bằng 18")
//    @Max(value = 65, message = "Tuổi phải nhỏ hơn hoặc bằng 65")
    private int age;

    @NotBlank()
    private String address;

    public User() {
    }

    public User(int id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
