package xyz.yplog.simprary.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import xyz.yplog.simprary.entity.Book;

public class BookRepository implements RepositoryImpl{

    @Override
    public <T> void create(T entityClass) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        entityManager.persist((Book)entityClass);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public <T> T read(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Book book = (Book) entityManager.find(Book.class, id);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
        
        return (T) book;
    }

    @Override
    public <T> void update(Long id, T entityClass) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Book book = (Book) entityManager.find(Book.class, id);
        book.setBookName(((Book)entityClass).getBookName());
        book.setBookSubName(((Book)entityClass).getBookSubName());
        book.setBookIsbn(((Book)entityClass).getBookIsbn());
        book.setBookContent(((Book)entityClass).getBookContent());
        
        if(((Book)entityClass).getBookAuthor() != null)
            book.setBookAuthor(((Book)entityClass).getBookAuthor());
        else
            book.setBookAuthor(null);
        
        if(((Book)entityClass).getBookPublisher() != null)
            book.setBookPublisher(((Book)entityClass).getBookPublisher());
        else
            book.setBookPublisher(null);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void delete(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Book book = (Book) entityManager.find(Book.class, id);
        entityManager.remove(book);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public <T> List<T> list() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        List<Book> bookList = entityManager.createQuery("select x from Book x", Book.class).getResultList();
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
        
        return (List<T>) bookList;
    }

    
}
