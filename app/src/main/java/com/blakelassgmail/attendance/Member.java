package com.blakelassgmail.attendance;

/**
 * Created by Blake Lassiter on 5/7/2016.
 */
public class Member {
    private String name;
    private String email;
    private String phone;
    private int id;

    public Member() {
        this.setName(null);
        this.setPhone(null);
        this.setEmail(null);
        this.setId(-1);
    }
    /**
     *
     * @param name
     * @param email
     * @param phone
     */
    public Member(String name, String email, String phone) {
        setEmail(email);
        setName(name);
        setPhone(phone);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }
}
