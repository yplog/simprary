package xyz.yplog.simprary.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Publisher implements Serializable{
    
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long publisherId;
    
    @Column(name = "PUBLISHERNAME", length = 100, nullable = false)
    private String publisherName;
    
    @Column(name = "PUBLISHERCONTENT", length = 250, nullable = true)
    private String publisherContent;
    
    @OneToMany(mappedBy = "bookPublisher", orphanRemoval = true)
    private List<Book> publisherBooks;

    public Publisher() {
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherContent() {
        return publisherContent;
    }

    public void setPublisherContent(String publisherContent) {
        this.publisherContent = publisherContent;
    }

    public List<Book> getPublisherBooks() {
        return publisherBooks;
    }

    public void setPublisherBooks(List<Book> publisherBooks) {
        this.publisherBooks = publisherBooks;
    }
    
}
