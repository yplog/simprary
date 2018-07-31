package xyz.yplog.simprary.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import xyz.yplog.simprary.entity.Users;


public class UserRepository implements RepositoryImpl{

    @Override
    public <T> void create(T entityClass) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        entityManager.persist((Users)entityClass);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public <T> T read(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Users user = (Users) entityManager.find(Users.class, id);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
        
        return (T) user;
    }

    @Override
    public <T> void update(Long id, T entityClass) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Users user = (Users) entityManager.find(Users.class, id);
        user.setUserName(((Users)entityClass).getUserName());
        user.setUserPassword(((Users)entityClass).getUserPassword());
        user.setUserIsAdmin(((Users)entityClass).isUserIsAdmin());
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void delete(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Users user = (Users) entityManager.find(Users.class, id);
        entityManager.remove(user);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public <T> List<T> list() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("xyz.yplog_simprary_war_1.0-SNAPSHOTPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        List<Users> userList = entityManager.createQuery("select x from Users x", Users.class).getResultList();
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
        
        return (List<T>) userList;
    }

}
