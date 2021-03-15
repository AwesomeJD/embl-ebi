package com.ebi.interview.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ebi.interview.entity.Person;
import com.ebi.interview.model.request.PersonRequest;
import com.ebi.interview.model.response.PersonResponse;
import com.ebi.interview.respository.PersonRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/** The type Person service test. */
@SpringBootTest
class PersonServiceTest {

    @MockBean private PersonRepository repository;

    @Autowired private PersonService personService;

    /** Test get person by id success. */
    @Test
    public void testGetPersonByIdSuccess() {
        // given
        final PersonRequest request = PersonRequest.builder().build();
        final Person person = new Person();
        person.setFirstName("JD");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(person));
        // when
        final PersonResponse response = personService.getPersonById(1L);
        // then
        assertEquals("JD", response.getFirstName());
    }

    /** Test get person by ids success. */
    @Test
    public void testGetPersonByIdsSuccess() {
        // given
        final PersonRequest request = PersonRequest.builder().build();
        final Person person = new Person();
        person.setFirstName("JD");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(person));
        // when
        final PersonResponse response = personService.getPersonById(1L);
        // then
        assertEquals("JD", response.getFirstName());
    }
}
