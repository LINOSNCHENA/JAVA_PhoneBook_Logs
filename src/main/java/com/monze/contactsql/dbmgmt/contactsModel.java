package com.monze.contactsql.dbmgmt;

public class contactsModel {
    private String pname;
    private int pnumber1;
    private int pnumber2;

    public contactsModel(String pname, int pnumber1,int pnumber2) { this.pname = pname;
        this.pnumber1 = pnumber1; this.pnumber2 = pnumber2;
    }

    public contactsModel() {
    }

    // ONE
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    // TWO
    public int getPnumber1() {
        return pnumber1;
    }

    public void setPnumber1(int pnumber1) {
        this.pnumber1 = pnumber1;
    }

    //THREE

        public int getPnumber2() {
            return pnumber2;
        }
    
        public void setPnumber2(int pnumber2) {
            this.pnumber2 = pnumber2;
        }
}
