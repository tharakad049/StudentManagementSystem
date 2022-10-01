package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Student implements Serializable {
    @Id
    @Column(name = "student_id")
     private String id;
     private String title;
     private String name;
     private String address;
     private String city;
     private String fee;
     private String age;

     @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
     Set<StudentProgram> studentPrograms=new HashSet<>();

    public Student() {
    }

    public Student(String id, String title, String name, String address, String city, String fee, String age) {
        this.setId(id);
        this.setTitle(title);
        this.setName(name);
        this.setAddress(address);
        this.setCity(city);
        this.setFee(fee);
        this.setAge(age);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", fee='" + fee + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
