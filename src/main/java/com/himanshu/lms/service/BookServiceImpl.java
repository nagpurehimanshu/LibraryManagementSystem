package com.himanshu.lms.service;

import com.himanshu.lms.requests.Issue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService {

    @Override
    public boolean issueBook(Issue issueRequest) {

        return false;
    }
}
