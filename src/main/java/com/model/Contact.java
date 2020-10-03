package com.model;

public class Contact {

    private Integer Id;
    private String NAME;
    private long MOBILE;
    private long OFFICE;

    public Contact(Integer Id, String NAME, long MOBILE,long OFFICE ) {
        this.Id = Id;
        this.NAME = NAME;
        this.OFFICE = OFFICE;
        this.MOBILE = MOBILE;
    }

    public Contact(String NAME, int MOBILE,int OFFICE ) {
        this.NAME = NAME;
        this.OFFICE = OFFICE;
        this.MOBILE = MOBILE;
    }

    public Contact() {
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return NAME;
    }

    public long getOffice() {
        return OFFICE;
    }

    public long getMobile() {
        return MOBILE;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public void setName(String NAME) {
        this.NAME = NAME;
    }

    public void setOffice(long OFFICE) {
        this.OFFICE = OFFICE;
    }

    public void setMobile(long MOBILE) {
        this.MOBILE = MOBILE;
    }

    @Override
    public String toString() {
        return "Contact {" + "Id : " + Id + ", Name :'" + NAME + '\'' + ", Mobile : " + OFFICE + ", Office : " + MOBILE
                + '}';
    }
}
