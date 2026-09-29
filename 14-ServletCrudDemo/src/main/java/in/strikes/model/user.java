package in.strikes.model;

public class user {

    private Integer id;
    private String name;
    private String mobno;
    private String email;

    public user(String email, Integer id, String mobno, String name) {
        this.email = email;
        this.id = id;
        this.mobno = mobno;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}