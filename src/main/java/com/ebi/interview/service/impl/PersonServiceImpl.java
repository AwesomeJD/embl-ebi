package com.ebi.interview.service.impl;

import com.ebi.interview.constants.ErrorConstants;
import com.ebi.interview.entity.Person;
import com.ebi.interview.exception.ApplicationException;
import com.ebi.interview.model.request.PersonRequest;
import com.ebi.interview.model.request.PersonUpdateRequest;
import com.ebi.interview.model.response.PersonResponse;
import com.ebi.interview.respository.PersonRepository;
import com.ebi.interview.service.PersonService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/** The type Person service. */
@Service
public class PersonServiceImpl implements PersonService {

    private final Logger LOGGER = LogManager.getLogger();
    private final PersonRepository repository;

    /**
     * Instantiates a new Person service.
     *
     * @param repository the repository
     */
    @Autowired
    public PersonServiceImpl(final PersonRepository repository) {
        this.repository = repository;
    }

    /**
     * Gets person by id.
     *
     * @param id the id
     * @return the person by id
     */
    @Override
    public PersonResponse getPersonById(final Long id) {
        LOGGER.info("Retrieving the Person by Id:" + id);
        final Optional<Person> person = repository.findById(id);
        if (person.isEmpty()) {
            throw new ApplicationException(
                    ErrorConstants.NO_PERSON_FOUND_FOR_THE_ID_ERROR_CODE,
                    ErrorConstants.NO_PERSON_FOUND_FOR_THE_ID_ERROR_CODE_MESSAGE);
        }
        return PersonResponse.builder()
                .id(person.get().getId())
                .firstName(person.get().getFirstName())
                .lastName(person.get().getLastName())
                .age(person.get().getAge())
                .favouriteColour(person.get().getFavouriteColour())
                .build();
    }

    /**
     * Gets persons by ids. Iterate over all the persons obtained from DB and form response.
     *
     * @param ids the ids
     * @return the persons by ids
     */
    @Override
    public List<PersonResponse> getPersonsByIds(final List<Long> ids) {
        LOGGER.info("Retrieving Persons by Ids:" + ids);
        final List<Person> persons = (List<Person>) repository.findAllById(ids);
        if (CollectionUtils.isEmpty(persons)) {
            throw new ApplicationException(
                    ErrorConstants.NO_PERSON_FOUND_FOR_THE_IDS_ERROR_CODE,
                    ErrorConstants.NO_PERSON_FOUND_FOR_THE_IDS_ERROR_CODE_MESSAGE);
        }
        return persons.stream()
                .map(
                        person -> {
                            return PersonResponse.builder()
                                    .id(person.getId())
                                    .firstName(person.getFirstName())
                                    .lastName(person.getLastName())
                                    .age(person.getAge())
                                    .favouriteColour(person.getFavouriteColour())
                                    .build();
                        })
                .collect(Collectors.toList());
    }

    /**
     * Add person long.
     *
     * @param personRequest the person request
     * @return the long
     */
    @Override
    public Long addPerson(PersonRequest personRequest) {
        LOGGER.info("Persisting the Person:" + personRequest);
        Person person = new Person();
        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());
        person.setAge(personRequest.getAge());
        person.setFavouriteColour(personRequest.getFavouriteColour());
        Person createdPerson = null;
        try {
            createdPerson = repository.save(person);
        } catch (Exception ex) {
            throw new ApplicationException(
                    ErrorConstants.PERSISTENCE_ERROR_CODE,
                    ErrorConstants.PERSISTENCE_ERROR_CODE_MESSAGE,
                    ex);
        }
        final Long id = createdPerson.getId();
        LOGGER.info("Person created with Id:" + id);
        return id;
    }

    /**
     * Update person.
     *
     * @param personUpdateRequest the person update request
     */
    @Override
    public void updatePerson(PersonUpdateRequest personUpdateRequest) {
        LOGGER.info("Updating the Person:" + personUpdateRequest);
        final Person personToUpdate = new Person();
        personToUpdate.setFirstName(personUpdateRequest.getFirstName());
        personToUpdate.setLastName(personUpdateRequest.getLastName());
        personToUpdate.setAge(personUpdateRequest.getAge());
        personToUpdate.setId(personUpdateRequest.getId());
        personToUpdate.setFavouriteColour(personUpdateRequest.getFavouriteColour());
        try {
            repository.save(personToUpdate);
        } catch (Exception ex) {
            throw new ApplicationException(
                    ErrorConstants.UPDATE_ERROR_CODE, ErrorConstants.UPDATE_ERROR_CODE_MESSAGE, ex);
        }
        LOGGER.info("Person update successful with Id:" + personUpdateRequest.getId());
    }

    /**
     * Delete person.
     *
     * @param id the id
     */
    @Override
    public void deletePerson(Long id) {
        LOGGER.info("Deleting the person with Id:" + id);
        if (repository.findById(id).isEmpty()) {
            throw new ApplicationException(
                    ErrorConstants.NO_PERSON_FOUND_FOR_THE_IDS_ERROR_CODE,
                    ErrorConstants.NO_PERSON_FOUND_FOR_THE_IDS_ERROR_CODE_MESSAGE);
        }
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new ApplicationException(
                    ErrorConstants.DELETE_ERROR_CODE, ErrorConstants.DELETE_CODE_MESSAGE, ex);
        }
        LOGGER.info("Person successfully deleted with Id:" + id);
    }
}
