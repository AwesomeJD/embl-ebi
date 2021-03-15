package com.ebi.interview.service;

import com.ebi.interview.model.request.PersonRequest;
import com.ebi.interview.model.request.PersonUpdateRequest;
import com.ebi.interview.model.response.PersonResponse;
import java.util.List;

/** The interface Person service. */
public interface PersonService {
    /**
     * Gets person by id.
     *
     * @param id the id
     * @return the person by id
     */
    PersonResponse getPersonById(final Long id);

    /**
     * Gets persons by ids.
     *
     * @param ids the ids
     * @return the persons by ids
     */
    List<PersonResponse> getPersonsByIds(final List<Long> ids);

    /**
     * Add person long.
     *
     * @param personRequest the person request
     * @return the long
     */
    Long addPerson(final PersonRequest personRequest);

    /**
     * Update person.
     *
     * @param personUpdateRequest the person update request
     */
    void updatePerson(final PersonUpdateRequest personUpdateRequest);

    /**
     * Delete person.
     *
     * @param id the id
     */
    void deletePerson(final Long id);
}
