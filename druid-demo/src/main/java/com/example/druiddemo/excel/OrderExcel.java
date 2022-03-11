package com.example.druiddemo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.example.druiddemo.annotation.CustomMerge;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 订单导出信息
 * @ExcelProperty：核心注解，value属性可用来设置表头名称，converter属性可以用来设置类型转换器
 * @ColumnWidth：用于设置表格列的宽度
 * @DateTimeFormat：用于设置日期转换格式
 * @author qzz
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderExcel {

    @ExcelProperty("订单ID")
    @ColumnWidth(10)
    @CustomMerge()
    private Integer id;

    @ExcelProperty("用户名")
    @ColumnWidth(20)
    private String name;

    @ExcelProperty("年龄")
    @ColumnWidth(10)
    private Integer age;

    @ExcelProperty("备注")
    @ColumnWidth(20)
    private String remarks;

    @ExcelProperty("创建者")
    @ColumnWidth(20)
    private String createUser;

    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    private Date createTime;

    @ExcelIgnore
    private String updateUser;

    @ExcelIgnore
    private Date updateTime;
}
