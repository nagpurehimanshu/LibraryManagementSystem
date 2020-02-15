package com.himanshu.lms.repository;

import com.himanshu.lms.entities.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {

    Book findById(int id);

}
