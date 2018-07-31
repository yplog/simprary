package xyz.yplog.simprary.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import xyz.yplog.simprary.entity.Author;

public class AuthorRepository implements RepositoryImpl{

    @Override
    public <T> void create(T entityClass) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        entityManager.persist((Author)entityClass);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public <T> T read(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Author author = (Author) entityManager.find(Author.class, id);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
        
        return (T) author;
    }

    @Override
    public <T> void update(Long id, T entityClass) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Author author = (Author) entityManager.find(Author.class, id);
        author.setAuthorName(((Author)entityClass).getAuthorName());
        author.setAuthorBio(((Author)entityClass).getAuthorBio());
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void delete(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public <T> List<T> list() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        List<Author> authorList = entityManager.createQuery("select x from Author x", Author.class).getResultList();
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
        
        return (List<T>) authorList;
    }

}
