package com.example.druiddemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.druiddemo.entity.User;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author qzz
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 批量插入
     * @param list
     * @return
     */
    Integer insertBacth(List<User> list);
}
