package com.ebi.interview.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** The type Persisted person response. */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersistedPersonResponse {
    private Long id;
}
