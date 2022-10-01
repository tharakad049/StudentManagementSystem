package view.tm;

public class StudentTM implements Comparable<StudentTM>{
     private String id;
     private String title;
     private String name;
     private String address;
     private String city;
     private String fee;
     private String age;

    public StudentTM() {
    }

    public StudentTM(String id, String title, String name, String address, String city, String fee, String age) {
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
    public int compareTo(StudentTM o) {
        return 0;
    }
}