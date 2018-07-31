package xyz.yplog.simprary.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import xyz.yplog.simprary.entity.Author;
import xyz.yplog.simprary.entity.Book;
import xyz.yplog.simprary.entity.Publisher;
import xyz.yplog.simprary.repository.AuthorRepository;
import xyz.yplog.simprary.repository.BookRepository;
import xyz.yplog.simprary.repository.PublisherRepository;

@ManagedBean
@SessionScoped
public class SearchBean implements Serializable{
    private String searchString;
    private List<Book> bookList;
    private List<Publisher> publisherList;
    private List<Author> authorList;

    public SearchBean() {
        bookList = new ArrayList<>();
        publisherList = new ArrayList<>();
        authorList = new ArrayList<>();
    }
    
    public String search(){
        bookList.clear();
        publisherList.clear();
        authorList.clear();
        
        if(!searchString.isEmpty()){
            AuthorRepository authorRepo = new AuthorRepository();
            PublisherRepository publisherRepo = new PublisherRepository();
            BookRepository bookRepo = new BookRepository();
        
            List<Book> allBook = bookRepo.list();
            List<Publisher> allPublisher = publisherRepo.list();
            List<Author> allAuthor = authorRepo.list();
            
            for(Book book : allBook){
                if(book.getBookName().toLowerCase().contains(searchString))
                    bookList.add(book);
                else if(book.getBookSubName().toLowerCase().contains(searchString))
                    bookList.add(book);
                else if(book.getBookIsbn().toLowerCase().contains(searchString))
                    bookList.add(book);
            }
            
            for(Author author : allAuthor){
                if(author.getAuthorName().toLowerCase().contains(searchString))
                    authorList.add(author);
            }
            
            for(Publisher publisher : allPublisher){
                if(publisher.getPublisherName().toLowerCase().contains(searchString))
                    publisherList.add(publisher);
            }
        } else
            return "easteregg";
        
        searchString = "";
        
        return "result";
    }
    
    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Publisher> getPublisherList() {
        return publisherList;
    }

    public void setPublisherList(List<Publisher> publisherList) {
        this.publisherList = publisherList;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
    
}
