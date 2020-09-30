package org.otus.education.data.core.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author sergey
 * created on 03.02.19.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq",
            sequenceName = "SEQ_USER")
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet addressDataSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PhoneDataSet> phoneDataSets;

    public User() {

    }

    public User(String name, AddressDataSet addressDataSet, List<PhoneDataSet> phoneDataSets) {
        this.name = name;
        this.addressDataSet = addressDataSet;
        this.phoneDataSets = phoneDataSets;
        updateUserInPhones(this.phoneDataSets);
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
        updateUserInPhones(this.phoneDataSets);
    }

    private void updateUserInPhones(List<PhoneDataSet> phoneDataSet) {
        phoneDataSet.forEach(x -> x.setUser(this));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressDataSet=" + addressDataSet +
                ", phoneDataSets=" + phoneDataSets +
                '}';
    }
}
