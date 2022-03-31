package com.swiggy.swiggyClone.dataModel.oneToOneRelation;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class ParentTable {


    @Id
    @SequenceGenerator(name = "parent_data_sequence", sequenceName = "parent_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_data_sequence")
    private Long parentId;



    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "child_id")
    private ChildTable childTable;

    public ParentTable() {
    }

    public ParentTable(Long parentId, String firstName, String lastName, ChildTable childTable) {
        this.parentId = parentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.childTable = childTable;
    }

    public ParentTable(String firstName, String lastName, ChildTable childTable) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.childTable = childTable;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ChildTable getChildTable() {
        return childTable;
    }

    public void setChildTable(ChildTable childTable) {
        this.childTable = childTable;
    }
}
