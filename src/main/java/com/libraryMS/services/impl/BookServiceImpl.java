package com.libraryMS.services.impl;

import com.libraryMS.entities.Author;
import com.libraryMS.entities.Book;
import com.libraryMS.entities.Member;
import com.libraryMS.exception.ResourceNotFoundException;
import com.libraryMS.payloads.BookDto;
import com.libraryMS.payloads.BookResponse;
import com.libraryMS.repositories.AuthorRepo;
import com.libraryMS.repositories.BookRepo;
import com.libraryMS.repositories.MemberRepo;
import com.libraryMS.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public BookDto createBook(BookDto bookDto, Integer authorId) {
        Author author = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "author id", authorId));

        Book book = this.modelMapper.map(bookDto, Book.class);
        book.setAuthor(author);

        Book createBook = this.bookRepo.save(book);

        BookDto createdBook = this.modelMapper.map(createBook, BookDto.class);
        return createdBook;
    }

    @Override
    public BookDto updateBook(BookDto bookDto, Integer bookId) {
        Book selectedBook = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "bookid", bookId));

        selectedBook.setTitle(bookDto.getTitle());
        selectedBook.setDescription(bookDto.getDescription());
        selectedBook.setImageName(bookDto.getImageName());

        Book updatedBook = this.bookRepo.save(selectedBook);
        return this.modelMapper.map(updatedBook, BookDto.class);
    }

    @Override
    public BookDto lendBook(Integer memberId, Integer bookId) {
        Member member = this.memberRepo.findById(memberId).orElseThrow(() -> new ResourceNotFoundException("Member", "member id", memberId));
        Book book1 = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "Book id", bookId));

//        Book book = this.modelMapper.map(bookDto, Book.class);

        // Check if the book is already lent
        if (book1.getMemberList() != null && book1.getMemberList().contains(member)) {
            throw new RuntimeException("Book is already lent to the member.");
        }

        if (member.getBookList() == null) {
            member.setBookList(new HashSet<>());
        }
        member.getBookList().add(book1);

        if (book1.getMemberList() == null) {
            book1.setMemberList(new HashSet<>());
        }
        book1.getMemberList().add(member);

//        List<Member> memberList = new ArrayList<>();
//        memberList.add(member);
//
//        Set<Book> bookList = new HashSet<>();
//        bookList.add(book1);


//        book1.setMemberList(memberList);
//        member.setBookList(bookList);
//        book1.setLend_date(new Date());

        Book lendbook = this.bookRepo.save(book1);
        Member member1 = this.memberRepo.save(member);
        return this.modelMapper.map(lendbook, BookDto.class);
    }

    @Override
    public void deleteBook(Integer bookId) {
        this.bookRepo.delete(this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "Book id", bookId)));
    }

    @Override
    public BookDto getBookById(Integer bookId) {
        Book book = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "bookid", bookId));

        return this.modelMapper.map(book, BookDto.class);
    }


//    @Override
////    public List<BookDto> getAllBook() {
////        List<Book> allbooks = this.bookRepo.findAll();
////
////        List<BookDto> bookDtos = allbooks.stream().map((book) -> this.modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
////
////        return bookDtos;
////    }

    @Override
    public BookResponse getAllBook(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = null;

        //using param decide asc or desc
        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        // pagination
        //by default
//        int pageSize = 5;
//        int pageNumber = 1;

        // 1 page 5 records and also sort by post id using
        //this function ####Important
        //Sort.by(sortBy).descending() we can make descending order
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Book> pagepost = this.bookRepo.findAll(p);

        List<Book> allbooks = pagepost.getContent();

        List<BookDto> bookDtos = allbooks.stream().map((book) -> this.modelMapper.map(book, BookDto.class)).collect(Collectors.toList());

        BookResponse bookResponse = new BookResponse();

        bookResponse.setContent(bookDtos);
        //no of size in page
        bookResponse.setPageNumber(pagepost.getNumber());
        bookResponse.setPageSize(pagepost.getSize());
        bookResponse.setTotalElements(pagepost.getTotalElements());
        bookResponse.setTotalPages(pagepost.getTotalPages());
        bookResponse.setLastPage(pagepost.isLast());


        return bookResponse;
    }

    @Override
    public BookResponse searchBook(String keyword) {
        List<Book> books = this.bookRepo.searchByTitle("%" + keyword + "%");
        List<BookDto> bookDtos = books.stream().map((book) -> this.modelMapper.map(book, BookDto.class)).collect(Collectors.toList());

        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(bookDtos);

        return bookResponse;
    }

}
