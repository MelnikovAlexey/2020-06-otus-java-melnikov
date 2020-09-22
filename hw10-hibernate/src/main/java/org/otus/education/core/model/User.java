package org.otus.education.core.model;

import java.util.List;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class User {

    private long id;
    private String name;
    private AddressDataSet addressDataSet;
    private List<PhoneDataSet> phoneDataSets;

    public User() {

    }

    public User(long id, String name, AddressDataSet addressDataSet, List<PhoneDataSet> phoneDataSets) {
        this.id = id;
        this.name = name;
        this.addressDataSet = addressDataSet;
        this.phoneDataSets = phoneDataSets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDataSet getAddressDataSet() {
        return addressDataSet;
    }

    public void setAddressDataSet(AddressDataSet addressDataSet) {
        this.addressDataSet = addressDataSet;
    }

    public List<PhoneDataSet> getPhoneDataSets() {
        return phoneDataSets;
    }

    public void setPhoneDataSets(List<PhoneDataSet> phoneDataSets) {
        this.phoneDataSets = phoneDataSets;
    }
}
