package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called appUserRepository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

}
