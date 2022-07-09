package project.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import project.entities.User;

@SessionScope
@Component
public class LoggedUser {


    private long id;
    private String username;


    public void login(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public void logout(){
        this.id = 0;
        this.username = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
