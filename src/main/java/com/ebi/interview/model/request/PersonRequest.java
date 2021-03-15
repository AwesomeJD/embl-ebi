package com.ebi.interview.model.request;

import com.ebi.interview.constants.ErrorConstants;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/** The type Person request. */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    @NotNull(message = ErrorConstants.THE_FIRST_NAME_CANNOT_BE_NULL)
    private String firstName;

    @NotNull(message = ErrorConstants.THE_LAST_NAME_CANNOT_BE_NULL)
    private String lastName;

    @Min(value = 1, message = ErrorConstants.THE_AGE_CANNOT_BE_NULL)
    private Short age;

    private String favouriteColour;
}
