package com.example.accessingdatamysql;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    @Query("SELECT DISTINCT appuser FROM AppUser appuser WHERE LOWER(appuser.lastName) LIKE :lastName% ")
    @Transactional(readOnly = true)
    Page<AppUser> findByLastName(@Param("lastName") String lastName, Pageable pageable);

    @Query("SELECT DISTINCT appuser FROM AppUser appuser WHERE LOWER(appuser.firstName) LIKE :firstName% AND LOWER(appuser.lastName) LIKE :lastName% AND LOWER(appuser.address) LIKE :address% AND LOWER(appuser.city) LIKE :city% AND LOWER(appuser.telephone) LIKE :telephone% AND LOWER(appuser.email) LIKE :email% ")
    @Transactional(readOnly = true)
    Page<AppUser> findByDetails(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("address") String address, 
        @Param("city") String city, @Param("telephone") String telephone, @Param("email") String email, Pageable pageable);

    @Query("SELECT appuser FROM AppUser appuser WHERE appuser.id =:id")
    @Transactional(readOnly = true)
    Optional<AppUser> findById(@Param("id") Integer id);


    @Transactional(readOnly = true)
    @Cacheable("appUsers")
    Page<AppUser> findAll(Pageable pageable) throws DataAccessException;

}
