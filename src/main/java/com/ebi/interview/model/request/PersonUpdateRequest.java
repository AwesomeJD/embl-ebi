package com.ebi.interview.model.request;

import com.ebi.interview.constants.ErrorConstants;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/** The type Person update request. */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PersonUpdateRequest extends PersonRequest {
    @NotNull(message = ErrorConstants.THE_ID_CANNOT_BE_NULL)
    private Long id;
}
