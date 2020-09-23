package com.model;

public class Contact {

    private Integer id;
    private String pname;
    private int pnumber1;
    private int pnumber2;

    public Contact(Integer id, String pname, int pnumber1, int pnumber2) {
        this.id = id;
        this.pname = pname;
        this.pnumber1 = pnumber1;
        this.pnumber2 = pnumber2;
    }

    public Contact(String pname, int pnumber1, int pnumber2) {
        this.pname = pname;
        this.pnumber1 = pnumber1;
        this.pnumber2 = pnumber2;
    }

    public Contact() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return pname;
    }

    public int getPnumber1() {
        return pnumber1;
    }

    public int getPnumber2() {
        return pnumber2;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String pname) {
        this.pname = pname;
    }

    public void setPnumber1(int pnumber1) {
        this.pnumber1 = pnumber1;
    }

    public void setPnumber2(int pnumber2) {
        this.pnumber2 = pnumber2;
    }

    @Override
    public String toString() {
        return "Contact {" + "id=" + id + ", name='" + pname + '\'' + ", Mobile=" + pnumber1 + ", Office =" + pnumber2 + '}';
    }
}
