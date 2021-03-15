package com.ebi.interview.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** The type Persons list. */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonsList {
    private List<PersonResponse> persons;
}
