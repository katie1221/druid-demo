package com.example.druiddemo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * 自定义公共字段填充处理器
 * @author qzz
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ......");

        //第二个参数写的是实体类里的属性名，不是对应数据库字段名
        this.fillStrategy(metaObject,"create_time", new Date());
        this.fillStrategy(metaObject,"update_time", new Date());

        this.setFieldValByName("create_user",getCurrentUserId(),metaObject);
        this.setFieldValByName("update_user",getCurrentUserId(),metaObject);

        log.info("end insert fill ......");
    }

    /**
     * 修改填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ......");
        this.fillStrategy(metaObject,"update_time", new Date());
        this.fillStrategy(metaObject,"update_user", getCurrentUserId());

        log.info("end update fill ......");
    }

    /**
     * 获取当前用户id
     * @return
     */
    private String getCurrentUserId() {
        return "1";
    }
}
