package xyz.yplog.simprary.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import xyz.yplog.simprary.entity.Publisher;

public class PublisherRepository implements RepositoryImpl{

    @Override
    public <T> void create(T entityClass) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        entityManager.persist((Publisher)entityClass);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public <T> T read(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Publisher publisher = (Publisher) entityManager.find(Publisher.class, id);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
        
        return (T) publisher;
    }

    @Override
    public <T> void update(Long id, T entityClass) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Publisher publisher = (Publisher) entityManager.find(Publisher.class, id);
        publisher.setPublisherName(((Publisher)entityClass).getPublisherName());
        publisher.setPublisherContent(((Publisher)entityClass).getPublisherContent());
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void delete(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Publisher publisher = (Publisher) entityManager.find(Publisher.class, id);
        entityManager.remove(publisher);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public <T> List<T> list() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        List<Publisher> publisherList = entityManager.createQuery("select x from Publisher x", Publisher.class).getResultList();
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
        
        return (List<T>) publisherList;
    }

}
