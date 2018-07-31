package xyz.yplog.simprary.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.security.crypto.bcrypt.BCrypt;
import xyz.yplog.simprary.chest.Trinkets;
import xyz.yplog.simprary.entity.Users;
import xyz.yplog.simprary.repository.UserRepository;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable{
    private Users readUser;
    private String userName;
    private String password;
    
    private boolean loggedIn;
    private boolean isAdmin;
    private List<Users> usrList;

    public UserBean() {
        
    }

    public String create(){
        UserRepository repo = new UserRepository();
        List<Users> userList = repo.list();
        
        if(userList.isEmpty()){
            Users user = new Users();
            user.setUserName(userName);
            user.setUserPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            user.setUserIsAdmin(true);
            
            repo.create(user);
            
            readUser = user;
            loggedIn = true;
            isAdmin = true;
            
            userName = null;
            password = null;
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                                                                  .put("admin", user.getUserName());
            
            return "index";
        } else {
            if(Trinkets.isThereUserName(userName)){
                Users user = new Users();
                user.setUserName(userName);
                user.setUserPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                user.setUserIsAdmin(false);
                
                repo.create(user);
                
                readUser = user;
                loggedIn = true;
                isAdmin = false;
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                                                                      .put("user", user.getUserName());
                
                userName = null;
                password = null;
                
                return "index";
            }
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("There is a user by that name."));
        
        userName = null;
        password = null;
                
        return "user/create";
    }
    
    public String read(){
        UserRepository repo = new UserRepository();
        List<Users> userList = repo.list();
        for(Users user : userList){
            if(user.getUserName().equals(userName) && BCrypt.checkpw(password, user.getUserPassword())){
                readUser = user;
                loggedIn = true;
                isAdmin = user.isUserIsAdmin();
                
                if(isAdmin)
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                                                                          .put("admin", user.getUserName());
                else
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                                                                          .put("user", user.getUserName());
                    
                return "index";
            }
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No such user was found."));
        
        return "user/login";
    }
    
    public String update(){
        UserRepository repo = new UserRepository();
        for(Users user : usrList){
            repo.update(user.getUserId(), user);
        }
        
        return "user/list";
    }
    
    public String logout(){
        readUser = null;
        loggedIn = false;
        isAdmin = false;
        
        userName = null;
        password = null;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                                                              .put("admin", null);
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                                                              .put("user", null);
        
        return "index";
    }
    
    public List<Users> list(){
        UserRepository repo = new UserRepository();
        usrList = repo.list();
        for(Users user : usrList){
            if(user.getUserName().equals(readUser.getUserName())){
                usrList.remove(user);
                
                return usrList;
            }
        }
        
        return usrList;
    }
    
    public String delete(){
        UserRepository repo = new UserRepository();
        repo.delete(Trinkets.catchId());
        
        return "user/list";
    }
    
    public Users getReadUser() {
        return readUser;
    }

    public void setReadUser(Users readUser) {
        this.readUser = readUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Users> getUsrList() {
        return usrList;
    }

    public void setUsrList(List<Users> userList) {
        this.usrList = userList;
    }

}
