package com.ebi.interview.constants;

/** The interface Error constants. */
public interface ErrorConstants {

    /** The constant GENERIC_ERROR_CODE. */
    String GENERIC_ERROR_CODE = "1000";
    /** The constant GENERIC_ERROR_MESSAGE. */
    String GENERIC_ERROR_MESSAGE = "Error in Application, please check logs.";
    /** The constant BAD_USER_INPUT_ERROR_CODE. */
    String BAD_USER_INPUT_ERROR_CODE = "1001";

    /** The constant THE_FIRST_NAME_CANNOT_BE_NULL. */
    String THE_FIRST_NAME_CANNOT_BE_NULL = "The first name cannot be null. ";
    /** The constant THE_LAST_NAME_CANNOT_BE_NULL. */
    String THE_LAST_NAME_CANNOT_BE_NULL = "The last name cannot be null. ";
    /** The constant THE_AGE_CANNOT_BE_NULL. */
    String THE_AGE_CANNOT_BE_NULL = "The age cannot be null. ";
    /** The constant THE_ID_CANNOT_BE_NULL. */
    String THE_ID_CANNOT_BE_NULL = "The id cannot be null. ";

    /** The constant NO_PERSON_FOUND_FOR_THE_ID_ERROR_CODE. */
    String NO_PERSON_FOUND_FOR_THE_ID_ERROR_CODE = "1001";
    /** The constant NO_PERSON_FOUND_FOR_THE_ID_ERROR_CODE_MESSAGE. */
    String NO_PERSON_FOUND_FOR_THE_ID_ERROR_CODE_MESSAGE = "No Person found for the Id provided.";

    /** The constant NO_PERSON_FOUND_FOR_THE_IDS_ERROR_CODE. */
    String NO_PERSON_FOUND_FOR_THE_IDS_ERROR_CODE = "1002";
    /** The constant NO_PERSON_FOUND_FOR_THE_IDS_ERROR_CODE_MESSAGE. */
    String NO_PERSON_FOUND_FOR_THE_IDS_ERROR_CODE_MESSAGE =
            "No Person found for the Id/Ids provided.";

    /** The constant PERSISTENCE_ERROR_CODE. */
    String PERSISTENCE_ERROR_CODE = "1003";
    /** The constant PERSISTENCE_ERROR_CODE_MESSAGE. */
    String PERSISTENCE_ERROR_CODE_MESSAGE = "There was an error while creating the Person.";

    /** The constant UPDATE_ERROR_CODE. */
    String UPDATE_ERROR_CODE = "1004";
    /** The constant UPDATE_ERROR_CODE_MESSAGE. */
    String UPDATE_ERROR_CODE_MESSAGE = "There was an error while updating the Person.";

    /** The constant DELETE_ERROR_CODE. */
    String DELETE_ERROR_CODE = "1005";
    /** The constant DELETE_CODE_MESSAGE. */
    String DELETE_CODE_MESSAGE = "There was an error while deleting the Person.";
}
