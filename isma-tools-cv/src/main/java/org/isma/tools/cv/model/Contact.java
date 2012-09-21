package org.isma.tools.cv.model;

public class Contact {

    private final String mail;
    private final String tel;

    public Contact(String mail, String tel) {
        this.mail = mail;
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public String getTel() {
        return tel;
    }
}
