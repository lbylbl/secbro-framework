package org.secbroframework.mapper;

import org.secbroframework.model.User;

public interface UserMapper extends SuperMapper{
    
    void deleteUser(int id);
    
    User getUser(int id);
    
}
