package domain;

import java.sql.Timestamp;

public final class Member {

    private String id;
    private String pw;
    private String name;
    private String contact;
    private Timestamp regTimestamp;

    public Member(String id, String pw, String name, String contact, Timestamp regTimestamp) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.contact = contact;
        this.regTimestamp = regTimestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Timestamp getRegTimestamp() {
        return regTimestamp;
    }

    public void setRegTimestamp(Timestamp regTimestamp) {
        this.regTimestamp = regTimestamp;
    }
}
