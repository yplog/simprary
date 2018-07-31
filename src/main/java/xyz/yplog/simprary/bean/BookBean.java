package xyz.yplog.simprary.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import xyz.yplog.simprary.chest.Trinkets;
import xyz.yplog.simprary.entity.Author;
import xyz.yplog.simprary.entity.Book;
import xyz.yplog.simprary.entity.Publisher;
import xyz.yplog.simprary.repository.AuthorRepository;
import xyz.yplog.simprary.repository.BookRepository;
import xyz.yplog.simprary.repository.PublisherRepository;

@ManagedBean
@RequestScoped
public class BookBean implements Serializable{
    private Book readBook;
    
    private long selectedAuthorId;
    private long selectedPublisherId;

    public BookBean() {
        if(Trinkets.catchId() != 0){
            BookRepository repo = new BookRepository();
            readBook = repo.read(Trinkets.catchId());
            if(readBook.getBookAuthor() != null)
                selectedAuthorId = readBook.getBookAuthor().getAuthorId();
            
            if(readBook.getBookPublisher() != null)
                selectedPublisherId = readBook.getBookPublisher().getPublisherId();
            
        } else {
            readBook = new Book();
        }
    }
    
    public String create(){
        if(Trinkets.isThereIsbn(readBook.getBookIsbn())){
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("There can not be two books with the same isbn"));
        
            return "/book/create.xhtml";
        }
        
        BookRepository repo = new BookRepository();
        
        if(selectedAuthorId != 0){
            AuthorRepository authorRepository = new AuthorRepository();
            Author author = authorRepository.read(selectedAuthorId);
            readBook.setBookAuthor(author);
        }
        
        if(selectedPublisherId != 0){
            PublisherRepository publisherRepository = new PublisherRepository();
            Publisher publisher = publisherRepository.read(selectedPublisherId);
            readBook.setBookPublisher(publisher);
        }
        
        repo.create(readBook);
        
        return "/book/list.xhtml?faces-redirect=true";
    }

    public String update(){
        BookRepository repo = new BookRepository();
        
        if(selectedAuthorId != 0){
            AuthorRepository authorRepository = new AuthorRepository();
            Author author = authorRepository.read(selectedAuthorId);
            readBook.setBookAuthor(author);
        } else {
            readBook.setBookAuthor(null);
        }
        
        if(selectedPublisherId != 0){
            PublisherRepository publisherRepository = new PublisherRepository();
            Publisher publisher = publisherRepository.read(selectedPublisherId);
            readBook.setBookPublisher(publisher);
        } else {
            readBook.setBookPublisher(null);
        }
        
        repo.update(readBook.getBookId(), readBook);
        
        return "/book/read.xhtml?id=" + readBook.getBookId();
    }
    
    public String delete(){
        BookRepository repo = new BookRepository();
        repo.delete(readBook.getBookId());
        
        return "/book/list.xhtml?faces-redirect=true";
    }
    
    public List<Book> list(){
        BookRepository repo = new BookRepository();
        
        return repo.list();
    }
    
    public List<SelectItem> authorSelectItems(){
        AuthorRepository authorRepo = new AuthorRepository();
        List<Author> authorList = authorRepo.list();
        
        List<SelectItem> authorItems = new ArrayList<>();
        authorItems.add(new SelectItem(0, "Select"));
        for(Author author : authorList){
            authorItems.add(new SelectItem(author.getAuthorId(), author.getAuthorName()));
        }
        
        return authorItems;
    }
    
    public List<SelectItem> publisherSelectItems(){
        PublisherRepository publisherRepo = new PublisherRepository();
        List<Publisher> publisherList = publisherRepo.list();
        
        List<SelectItem> publisherItems = new ArrayList<>();
        publisherItems.add(new SelectItem(0, "Select"));
        for(Publisher publisher : publisherList){
            publisherItems.add(new SelectItem(publisher.getPublisherId(), publisher.getPublisherName()));
        }
        
        return publisherItems;
    }

    public Book getReadBook() {
        return readBook;
    }

    public void setReadBook(Book readBook) {
        this.readBook = readBook;
    }

    public long getSelectedAuthorId() {
        return selectedAuthorId;
    }

    public void setSelectedAuthorId(long selectedAuthorId) {
        this.selectedAuthorId = selectedAuthorId;
    }

    public long getSelectedPublisherId() {
        return selectedPublisherId;
    }

    public void setSelectedPublisherId(long selectedPublisherId) {
        this.selectedPublisherId = selectedPublisherId;
    }
    
    
    
}
