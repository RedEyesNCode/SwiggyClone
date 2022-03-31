package com.swiggy.swiggyClone.dataModel.oneToOneRelation;


import javax.persistence.*;

@Entity
@Table
public class ChildTable {


    @Id
    @SequenceGenerator(name = "child_data_sequence", sequenceName = "child_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "child_data_sequence")
    private Long child_id;

    private String address;
    private String email;

    public ChildTable(String address, String email) {
        this.address = address;
        this.email = email;
    }

    public ChildTable() {
    }

    public ChildTable(Long child_id, String address, String email) {
        this.child_id = child_id;
        this.address = address;
        this.email = email;
    }

    public Long getChild_id() {
        return child_id;
    }

    public void setChild_id(Long child_id) {
        this.child_id = child_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
