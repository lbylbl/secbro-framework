package org.secbroframework.service;

import org.secbroframework.model.User;

public interface UserService {
    
    void deleteUser(int id);
    
    User getUser(int id);

}
