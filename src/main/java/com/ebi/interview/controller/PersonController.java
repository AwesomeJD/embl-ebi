package com.ebi.interview.controller;

import com.ebi.interview.model.request.PersonRequest;
import com.ebi.interview.model.request.PersonUpdateRequest;
import com.ebi.interview.model.response.PersistedPersonResponse;
import com.ebi.interview.model.response.PersonResponse;
import com.ebi.interview.service.PersonService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** The type Person controller. */
@RestController
@RequestMapping("/manage/person")
public class PersonController {

    private final PersonService personService;

    /**
     * Instantiates a new Person controller.
     *
     * @param personService the person service
     */
    @Autowired
    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    /**
     * Gets person by id.
     *
     * @param id the id
     * @return the person by id
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable(value = "id") final Long id) {
        final PersonResponse response = personService.getPersonById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Gets person by ids.
     *
     * @param ids the ids
     * @return the person by ids
     */
    @RequestMapping()
    public ResponseEntity<List<PersonResponse>> getPersonByIds(
            @RequestParam(value = "ids") final List<Long> ids) {
        final List<PersonResponse> response = personService.getPersonsByIds(ids);
        return ResponseEntity.ok(response);
    }

    /**
     * Create person response entity.
     *
     * @param personRequest the person request
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<PersistedPersonResponse> createPerson(
            @Valid @RequestBody final PersonRequest personRequest) {
        final Long id = personService.addPerson(personRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PersistedPersonResponse.builder().id(id).build());
    }

    /**
     * Update person response entity.
     *
     * @param personUpdateRequest the person update request
     * @return the response entity
     */
    @PutMapping()
    public ResponseEntity<Void> updatePerson(
            @Valid @RequestBody final PersonUpdateRequest personUpdateRequest) {
        personService.updatePerson(personUpdateRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * Delete person response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable(value = "id") final Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok().build();
    }
}
