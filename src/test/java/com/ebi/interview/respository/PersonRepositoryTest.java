package com.ebi.interview.respository;

import static org.junit.jupiter.api.Assertions.*;

import com.ebi.interview.entity.Person;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

/** The type Person repository test. */
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = "mem-db")
class PersonRepositoryTest {

    @Autowired private PersonRepository repository;

    /** Test persist of person success. */
    @Test
    public void testPersistOfPersonSuccess() {
        // given
        final Person person = new Person();
        // when
        final Person persistedPerson = repository.save(person);
        // then
        assertNotNull(persistedPerson);
    }

    /** Test get of person by id success. */
    @Test
    public void testGetOfPersonByIdSuccess() {
        // given
        // when
        final Optional<Person> person = repository.findById(1L);
        // then
        assertNotNull(person);
    }

    /** Test update of person success. */
    @Test
    public void testUpdateOfPersonSuccess() {
        // given
        final Person person = new Person();
        person.setFirstName("JD");
        // when
        final Person persistedPerson = repository.save(person);
        // then
        assertNotNull(persistedPerson);
        assertEquals("JD", persistedPerson.getFirstName());
    }

    /** Test delete of person success. */
    @Test
    public void testDeleteOfPersonSuccess() {
        // given
        final Person person = new Person();
        person.setFirstName("John");
        final Person persistedPerson = repository.save(person);
        final Long id = persistedPerson.getId();

        // when
        repository.deleteById(id);
        final Optional<Person> retrievedPerson = repository.findById(id);
        // then
        assertTrue(retrievedPerson.isEmpty());
    }
}
