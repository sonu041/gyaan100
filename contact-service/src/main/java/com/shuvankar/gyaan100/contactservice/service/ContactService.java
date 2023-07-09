package com.shuvankar.gyaan100.contactservice.service;

import com.shuvankar.gyaan100.contactservice.dto.ContactRequest;
import com.shuvankar.gyaan100.contactservice.dto.ContactResponse;
import com.shuvankar.gyaan100.contactservice.exception.ResourceNotFoundException;
import com.shuvankar.gyaan100.contactservice.model.Contact;
import com.shuvankar.gyaan100.contactservice.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.shuvankar.gyaan100.contactservice.config.AppConstants.ID;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    /** Add contact service */
    public ContactResponse addContact(ContactRequest contactRequest) {
        Contact contact = new Contact();
        contact.setName(contactRequest.getName());
        contact.setEmail(contactRequest.getEmail());
        contact.setPhone(contactRequest.getPhone());
        contact.setTeam(contactRequest.getTeam());
        contactRepository.save(contact);
        return mapToContactResponse(contact);
    }

    /** Get All Contact Service */
    public List<ContactResponse> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream().map(this::mapToContactResponse).toList();
    }

    /** Update Contact Service */
    public void updateContact(Long id, ContactRequest contactRequest) throws ResourceNotFoundException {
        findById(id).map(p -> {
            BeanUtils.copyProperties(contactRequest, p);
            return contactRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Contact", ID, id));
    }

    /** Hard Delete Contact */
    public void deleteContactByID(Long id) {
        contactRepository.deleteById(id);
    }

    /** Find Contact by id */
    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    /** Mapper method to map Contact object with Response */
    private ContactResponse mapToContactResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .name(contact.getName())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .team(contact.getTeam())
                .build();
    }
}
