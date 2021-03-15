package com.ebi.interview.controller;

import com.ebi.interview.model.request.PersonRequest;
import com.ebi.interview.model.response.PersistedPersonResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

/** The type Person controller test. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @Autowired private TestRestTemplate restTemplate;

    /** Test persist person. */
    @Test
    public void testPersistPerson() {
        final PersonRequest request =
                PersonRequest.builder().firstName("JD").lastName("Sharma").age((short) 32).build();
        final PersistedPersonResponse response =
                this.restTemplate.postForObject(
                        "/manage/person", request, PersistedPersonResponse.class);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
    }
}
