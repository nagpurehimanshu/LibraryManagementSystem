package com.himanshu.lms.repository;

import com.himanshu.lms.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    User findById(int id);
}
