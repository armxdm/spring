package com.exist.service;

import com.exist.dao.PersonDao;
import com.exist.dto.AddressDto;
import com.exist.dto.ContactDto;
import com.exist.dto.PersonDto;
import com.exist.dto.RoleDto;
import com.exist.model.Address;
import com.exist.model.Contact;
import com.exist.model.Person;
import com.exist.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    public List<PersonDto> getPersons(String order) {
        List<Person> persons = new ArrayList<>();
        if (order.equals("GWA")) {
            persons = personDao.getPersons("GWA");
        } else if (order.equals("Last Name")) {
            persons = personDao.getPersons("Last Name");
        } else if (order.equals("Date Hired")) {
            persons = personDao.getPersons("Date Hired");
        } else {
            persons = personDao.getPersons("ID");
        }

        return convertListToDto(persons);
    }

    public List<PersonDto> getPersonsByGwa(double gwa) {
        return convertListToDto(personDao.getPersonsByGwa(gwa));
    }

    public List<PersonDto> getPersonsByLastName(String lastName) {
        return convertListToDto(personDao.getPersonsByLastName(lastName));
    }

    public List<PersonDto> getPersonsByDateHired(Date dateHired) {
        return convertListToDto(personDao.getPersonsByDateHired(dateHired));
    }

    private List<PersonDto> convertListToDto(List<Person> persons) {
        List<PersonDto> personsDto = new ArrayList<>();
        for (Person person : persons) {
            personsDto.add(person.toDto());
        }
        return personsDto;
    }

    public PersonDto getPersonById(int personId) {
        return personDao.getPersonById(personId).toDto();
    }

    public ContactDto getContactById(int contactId) {
        return personDao.getContactById(contactId).toDto();
    }

    public void addPerson(PersonDto personDto) {
        personDao.addPerson(convertToEntity(personDto));
    }

    public void deletePerson(PersonDto personDto) {
        personDao.deletePerson(convertToEntity(personDto));
    }

    public void updatePerson(PersonDto personDto) {
        personDao.updatePerson(convertToEntity(personDto));
    }

    private Person convertToEntity(PersonDto dto) {
        Set<Role> roles = new HashSet<>();
        for (RoleDto role : dto.getRoles()) {
            roles.add(new Role(role.getId(), role.getRoleName()));
        }

        AddressDto homeAddressDto = dto.getHomeAddress();
        Address homeAddress = new Address(homeAddressDto.getNumber(), homeAddressDto.getStreet(), homeAddressDto.getBarangay(), homeAddressDto.getCity(),
                homeAddressDto.getZipCode());

        AddressDto workAddressDto = dto.getWorkAddress();
        Address workAddress = new Address(workAddressDto.getNumber(), workAddressDto.getStreet(), workAddressDto.getBarangay(), workAddressDto.getCity(),
                workAddressDto.getZipCode());

        Person person = null;
        if (dto.getId() > 0) {
            person = personDao.getPersonById(dto.getId());
            person.setData(dto.getFirstName(), dto.getMiddleName(), dto.getLastName(), dto.getSuffix(), dto.getTitle(), dto.getBirthday(), dto.getGwa(),
                    dto.getDateHired(), workAddress, homeAddress, dto.isCurrentlyEmployed(), roles);
            person.getContacts().clear();
        } else {
            person = new Person(dto.getId(), dto.getFirstName(), dto.getMiddleName(), dto.getLastName(), dto.getSuffix(), dto.getTitle(), dto.getBirthday(), dto.getGwa(),
                    dto.getDateHired(), workAddress, homeAddress, dto.isCurrentlyEmployed(), roles);
        }

        if (dto.getContacts() != null) {
            for (ContactDto contact : dto.getContacts()) {
                person.getContacts().add(new Contact(contact.getId(), contact.getInfo(), contact.getContactType().toString(), person));
            }
        }

        return person;
    }

}

