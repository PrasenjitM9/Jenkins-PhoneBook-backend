package com.phonebookbackend.service;

import com.phonebookbackend.model.ContactPerson;
import com.phonebookbackend.repository.ContactPersonRepository;
import com.phonebookbackend.services.impl.ContactPersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *BDDMockito  It defines a clearly structured way of writing tests following three sections
 * (Arrange, Act, Assert):
 * -- given some preconditions (Arrange)
 * -- when an action occurs (Act)
 * -- then verify the output (Assert)
 *
 * */
@ExtendWith(MockitoExtension.class)
public class ContactPersonServiceTest {
    @Mock
    private ContactPersonRepository contactPersonRepository;

    @InjectMocks
    private ContactPersonServiceImpl contactPersonService;

    @Test
    void shouldReturnFindAll(){
        List<ContactPerson> datas = new ArrayList<>();
        datas.add(new ContactPerson("1","Xing","Hu","+32888"));
        datas.add(new ContactPerson("22","Xi","JinPing","+3288778"));
        datas.add(new ContactPerson("33","Obama","Hu","+32885618"));
        datas.add(new ContactPerson("44","Trump","Hu","+32884208"));
        given(contactPersonRepository.findAll()).willReturn(datas);
        List<ContactPerson> expected = contactPersonService.getAll();
        assertEquals(expected, datas);

    }

    @Test
    void findContactPersonById(){
        final String id = "1";
        final ContactPerson contactPerson = new ContactPerson("1","Xing","Hu","+32888");
        given(contactPersonRepository.findById(id)).willReturn(Optional.of(contactPerson));
        final Optional<ContactPerson> expected = contactPersonService.getById(id);
        assertNotNull(expected);
    }

    @Test
    void shouldBeDelete(){
        final String id = "1";
        contactPersonService.delete(id);
        contactPersonService.delete(id);
        verify(contactPersonRepository, times(2)).deleteById(id);
    }

    @Test
    void updateContactPerson(){
        final  ContactPerson contactPerson = new ContactPerson("1","Xing","Hu","+32888");
        given(contactPersonRepository.save(contactPerson)).willReturn(contactPerson);
        final ContactPerson expected = contactPersonService.update(contactPerson);
        assertNotNull(expected);
        verify(contactPersonRepository).save(any(ContactPerson.class));
    }
}
