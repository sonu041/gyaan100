package com.shuvankar.gyaan100.contactservice.controller;

import com.shuvankar.gyaan100.contactservice.dto.ContactRequest;
import com.shuvankar.gyaan100.contactservice.dto.ContactResponse;
import com.shuvankar.gyaan100.contactservice.exception.ResourceNotFoundException;
import com.shuvankar.gyaan100.contactservice.model.Contact;
import com.shuvankar.gyaan100.contactservice.service.ContactService;
import com.shuvankar.gyaan100.contactservice.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shuvankar.gyaan100.contactservice.config.AppConstants.ID;


@RestController
@RequestMapping("/api/contact")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;
    /** Create Contact */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContactResponse> addContact(@RequestBody ContactRequest contactRequest) {
        ContactResponse response = new ContactResponse();
        response = contactService.addContact(contactRequest);
        return new ResponseEntity<ContactResponse>(response, HttpUtil.getDefaultResponseHeader(), HttpStatus.CREATED);
    }

    /** Get single contact */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contact getContact(@PathVariable Long id ) throws ResourceNotFoundException {
//        return contactService.getContact();
        return contactService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact", ID, id));
    }

    /** Get count of contact */
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public int getKnowledgeCount() throws ResourceNotFoundException {
        return contactService.getAllContacts().size();
    }

    /** Get all contact */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContactResponse> getAllContacts() {
        return contactService.getAllContacts();
    }

    /** Update Contact */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateContact(@PathVariable Long id, @RequestBody ContactRequest contactRequest) throws ResourceNotFoundException {
        contactService.updateContact(id, contactRequest);
    }

    /** Delete Contact */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteContact(@PathVariable Long id) throws ResourceNotFoundException {
        contactService.findById(id).map(p -> {
            contactService.deleteContactByID(id);
            return true;
        }).orElseThrow(() -> new ResourceNotFoundException("Product", ID, id));
    }
}
