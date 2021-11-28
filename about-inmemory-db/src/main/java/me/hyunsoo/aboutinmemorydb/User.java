package me.hyunsoo.aboutinmemorydb;


public class User {
    private int id;
    private String name;
    private String age;
    private String speciality;
    private String work_at;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getWork_at() {
        return work_at;
    }

    public void setWork_at(String work_at) {
        this.work_at = work_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", speciality='" + speciality + '\'' +
                ", work_at='" + work_at + '\'' +
                '}';
    }
}
