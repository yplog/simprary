package xyz.yplog.simprary.chest;

import static java.lang.Long.parseLong;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import xyz.yplog.simprary.entity.Author;
import xyz.yplog.simprary.entity.Book;
import xyz.yplog.simprary.entity.Publisher;
import xyz.yplog.simprary.entity.Users;
import xyz.yplog.simprary.repository.AuthorRepository;
import xyz.yplog.simprary.repository.BookRepository;
import xyz.yplog.simprary.repository.PublisherRepository;
import xyz.yplog.simprary.repository.UserRepository;


public class Trinkets {

    public static long catchId(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                                                                      .getExternalContext()
                                                                      .getRequest();
        
        long id = 0;
        if(request.getParameter("id") != null)
            id = parseLong(request.getParameter("id"));
        
        return id;
    }
    
    public static boolean isThereIsbn(String bookIsbn){
        BookRepository repo = new BookRepository();
        List<Book> bookList = repo.list();
        for(Book book : bookList){
            if(book.getBookIsbn().equals(bookIsbn)){
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean isThereAuthorName(String authorName){
        AuthorRepository repo = new AuthorRepository();
        List<Author> authorList = repo.list();
        for(Author author : authorList){
            if(author.getAuthorName().equals(authorName)){
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean isTherePublisherName(String publisherName){
        boolean name = false;
        PublisherRepository repo = new PublisherRepository();
        List<Publisher> publisherList = repo.list();
        for(Publisher publisher : publisherList){
            if(publisher.getPublisherName().equals(publisherName)){
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean isThereUserName(String userName){
        UserRepository repo = new UserRepository();
        List<Users> userList = repo.list();
        for(Users user : userList){
            if(user.getUserName().equals(userName)){
                return false;
            }
        }
        
        return true;
    }
}
