package xyz.yplog.simprary.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import xyz.yplog.simprary.chest.Trinkets;
import xyz.yplog.simprary.entity.Author;
import xyz.yplog.simprary.repository.AuthorRepository;

@ManagedBean
@RequestScoped
public class AuthorBean implements Serializable{
    private Author readAuthor;

    public AuthorBean() {
        if(Trinkets.catchId() != 0){
            AuthorRepository repo = new AuthorRepository();
            readAuthor = repo.read(Trinkets.catchId());
        } else {
            readAuthor = new Author();
        }
    }
    
    public String create(){
        if(Trinkets.isThereAuthorName(readAuthor.getAuthorName())){
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("There can not be two authors with the same name"));
        
            return "/author/create.xhtml";
        }
        
        AuthorRepository repo = new AuthorRepository();
        repo.create(readAuthor);
        
        return "/author/list.xhtml?faces-redirect=true";
    }
    
    public String update(){
        AuthorRepository repo = new AuthorRepository();
        repo.update(readAuthor.getAuthorId(), readAuthor);
        
        return "/author/read.xhtml?id=" + readAuthor.getAuthorId();
    }
    
    public String delete(){
        AuthorRepository repo = new AuthorRepository();
        repo.delete(readAuthor.getAuthorId());
        
        return "/author/list.xhtml?faces-redirect=true";
    }
    
    public List<Author> list(){
        AuthorRepository repo = new AuthorRepository();
        
        return repo.list();
    }

    public Author getReadAuthor() {
        return readAuthor;
    }

    public void setReadAuthor(Author readAuthor) {
        this.readAuthor = readAuthor;
    }
    
}
