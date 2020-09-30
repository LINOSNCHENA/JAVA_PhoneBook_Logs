package com.model;

public class Contact {

    private Integer id;
    private String nameX;
    private int mobileX;
    private int officeX;

    public Contact(Integer id, String nameX, int officeX, int mobileX) {
        this.id = id;
        this.nameX = nameX;
        this.officeX = officeX;
        this.mobileX = mobileX;
    }

    public Contact(String nameX, int officeX, int mobileX) {
        this.nameX = nameX;
        this.officeX = officeX;
        this.mobileX = mobileX;
    }

    public Contact() {
    }

    public Integer getId() {
        return id;
    }

    public String getNamex() {
        return nameX;
    }

    public int getOfficex() {
        return officeX;
    }

    public int getMobilex() {
        return mobileX;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNamex(String nameX) {
        this.nameX = nameX;
    }

    public void setOfficex(int officeX) {
        this.officeX = officeX;
    }

    public void setMobilex(int mobileX) {
        this.mobileX = mobileX;
    }

    @Override
    public String toString() {
        return "Contact {" + "id=" + id + ", name='" + nameX + '\'' + ", Mobile=" + officeX + ", Office =" + mobileX
                + '}';
    }
}
