package com.exist.model;

import com.exist.dto.ContactDto;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Cache(region = "Contact", usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "contact_id_seq", sequenceName = "contact_id_seq")
    @GeneratedValue(strategy = AUTO, generator = "contact_id_seq")
    private int id;

    @Column(name = "info")
    private String info;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Contact() {
    }

    public Contact(ContactType type, String info) {
        this.type = type;
        this.info = info;
    }

    public Contact(String type, String info, Person person) {
        for (ContactType contactType : ContactType.values()) {
            if (contactType.getName().equals(type)) {
                this.type = contactType;
                break;
            }
        }
        this.info = info;
        this.person = person;
    }

    public Contact(int id, String info, String type, Person person) {
        this.id = id;
        for (ContactType contactType : ContactType.values()) {
            if (contactType.toString().equals(type)) {
                this.type = contactType;
                break;
            }
        }
        this.info = info;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ContactType getContactType() {
        return type;
    }

    public void setContactType(ContactType type) {
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ContactDto toDto() {
        return new ContactDto(this.id, this.info, this.type.toString(), this.person.toDto());
    }

    @Override
    public String toString() {
        return type.toString() + ": " + info;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        Contact obj2 = (Contact) obj;
        return (this.id == obj2.getId()) && (this.info.equals(obj2.getInfo()));
    }

    @Override
    public int hashCode() {
        int tmp = 0;
        tmp = (id + info).hashCode();
        return tmp;
    }

}
