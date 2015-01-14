package org.secbroframework.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.secbroframework.model.User;

public interface UserMapper extends SuperMapper{
    
    void deleteUser(int id);
    
    @Select("SELECT * FROM tb_user WHERE id = ${id}")
    User getUser(@Param("id")int id);
    
}
