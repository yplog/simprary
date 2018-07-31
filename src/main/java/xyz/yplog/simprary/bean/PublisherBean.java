package xyz.yplog.simprary.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import xyz.yplog.simprary.chest.Trinkets;
import xyz.yplog.simprary.entity.Publisher;
import xyz.yplog.simprary.repository.PublisherRepository;

@ManagedBean
@RequestScoped
public class PublisherBean implements Serializable{
    private Publisher readPublisher;

    public PublisherBean() {
        if(Trinkets.catchId() != 0){
            PublisherRepository repo = new PublisherRepository();
            readPublisher = repo.read(Trinkets.catchId());
        } else {
            readPublisher = new Publisher();
        }
    }
    
    public String create(){
        if(Trinkets.isTherePublisherName(readPublisher.getPublisherName())){
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("There can not be two authors with the same name"));
        
            return "/publisher/create.xhtml";
        }
        
        PublisherRepository repo = new PublisherRepository();
        repo.create(readPublisher);
        
        return "/publisher/list.xhtml?faces-redirect=true";
    }

    public String update(){
        PublisherRepository repo = new PublisherRepository();
        repo.update(readPublisher.getPublisherId(), readPublisher);
        
        return "/publisher/read.xhtml?id=" + readPublisher.getPublisherId();
    }
    
    public String delete(){
        PublisherRepository repo = new PublisherRepository();
        repo.delete(readPublisher.getPublisherId());
        
        return "/publisher/list.xhtml?faces-redirect=true";
    }
    
    public List<Publisher> list(){
        PublisherRepository repo = new PublisherRepository();
        
        return repo.list();
    }
    
    public Publisher getReadPublisher() {
        return readPublisher;
    }

    public void setReadPublisher(Publisher readPublisher) {
        this.readPublisher = readPublisher;
    }
    
}
