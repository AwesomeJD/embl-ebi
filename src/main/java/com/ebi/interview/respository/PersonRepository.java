package com.ebi.interview.respository;

import com.ebi.interview.constants.ApplicationConstants;
import com.ebi.interview.entity.Person;
import java.util.Optional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/** The interface Person repository. */
public interface PersonRepository
        extends PagingAndSortingRepository<Person, Long>, JpaRepository<Person, Long> {

    /**
     * Save person.
     *
     * @param person the person
     * @return the person
     */
    @CachePut(
            value = ApplicationConstants.CACHE_NAME_PERSONS,
            key = "#p0",
            condition = "#result.id != null")
    Person save(Person person);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    @Cacheable(value = ApplicationConstants.CACHE_NAME_PERSONS, key = "#id")
    Optional<Person> findById(Long id);

    /**
     * Delete by id.
     *
     * @param id the id
     */
    @CacheEvict(value = ApplicationConstants.CACHE_NAME_PERSONS, key = "#id")
    void deleteById(Long id);
}
