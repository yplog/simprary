package xyz.yplog.simprary.repository;

import java.util.List;


public interface RepositoryImpl {
    
    public <T> void create(T entityClass);
    
    public <T> T read(Long id);
    
    public <T> void update(Long id, T entityClass);
    
    public void delete(Long id);
    
    public <T> List<T> list();
    
}
