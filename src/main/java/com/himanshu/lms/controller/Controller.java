package com.himanshu.lms.controller;

import com.himanshu.lms.Response;
import com.himanshu.lms.entities.Book;
import com.himanshu.lms.entities.User;
import com.himanshu.lms.repository.BookRepository;
import com.himanshu.lms.repository.UserRepository;
import com.himanshu.lms.requests.Issue;
import com.himanshu.lms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController("/")
@Component
public class Controller {

    @Autowired
    BookService bookService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/issueBook")
    Response issueBook(@RequestBody Issue issueRequest){

        try{
            int userId = issueRequest.getUserId();

            if(!validUser(userId)) {
                Response resp= new Response(200,"Not a valid User");
                return resp;
            }

            if(issueNewBook(issueRequest))
            {
                Response resp= new Response(200,"Book issued successfully");
                return resp;
            }
            else{
                Response resp= new Response(200,"Book cannot be issued");
                return resp;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return new Response();
    }

    private boolean issueNewBook(Issue issueRequest)
    {
        Book book = bookRepository.findById(issueRequest.getBookId());
        User user = userRepository.findById(issueRequest.getUserId());
        if(book==null) return false;
        else {
            if (book.isAvailable()==0) return false;
            else {
                book.setAvailable(0);
                book.setIssueDate(new Date());
                user.setIssued(true);
                user.setBook(book);
                bookRepository.save(book);
                userRepository.save(user);
                return true;
            }
        }
    }

    private boolean validUser(int userId) {
        User user = userRepository.findById(userId);
        if(user==null) return false;
        else{
            if(user.isIssued()){
                return false;
            }
            else
                return true;
        }
    }

    @RequestMapping(value="/addBook",method= RequestMethod.POST)
    Boolean addBook(@RequestBody Book book){
        bookRepository.save(book);
        return true;
    }

    @RequestMapping(value="/addUser",method= RequestMethod.POST)
    Boolean addUser(@RequestBody User user){
        userRepository.save(user);
        return true;
    }
}
