package com.example.druiddemo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.druiddemo.dao.UserMapper;
import com.example.druiddemo.entity.User;
import com.example.druiddemo.excel.UserExcel;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qzz
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 通过Class的方式初始化日志对象
     */
//    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 查看用户列表
     */
    @ApiOperation("查看用户列表")
    @GetMapping ("/listAll")
    public Map<String,Object> listAll(){
        log.info("查看用户列表");
        Map<String,Object> result = new HashMap<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> list= userMapper.selectList(queryWrapper);
        result.put("code","200");
        result.put("data",list);
        log.debug("返回结果集个数："+list.size());
        return result;
    }

    /**
     * 添加用户信息
     */
    @ApiOperation("添加用户信息")
    @PostMapping("add")
    public Map<String,Object> addProduct(@RequestBody User user){
        Map<String,Object> result = new HashMap<>();
        int n = userMapper.insert(user);
        if(n>0){
            result.put("code","200");
            result.put("id",user.getId());
        }else{
            result.put("code","400");
            result.put("msg","添加用户失败");
        }
        return result;
    }

    /**
     * 编辑商品信息
     */
    @ApiOperation("编辑商品信息")
    @PostMapping("update")
    public Map<String,Object> updateProduct(@RequestBody User user){
        Map<String,Object> result = new HashMap<>();
        int n = userMapper.updateById(user);
        if(n>0){
            result.put("code","200");
        }else{
            result.put("code","400");
            result.put("msg","编辑用户失败");
        }
        return result;
    }

    /**
     * 导出用户列表信息（简单导出）
     * @param response
     */
    @ApiOperation("导出用户列表信息")
    @GetMapping("/exportUserList")
    @SneakyThrows(Exception.class)
    public void exportUserList(HttpServletResponse response){
        setExcelRespProp(response,"用户列表");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> list= userMapper.selectList(queryWrapper);
        EasyExcel.write(response.getOutputStream()).head(UserExcel.class)
                .excelType(ExcelTypeEnum.XLSX).sheet("用户列表")
                .doWrite(list);
    }

    /**
     * 设置excel下载响应头属性
     * @param response
     * @param sheetName
     */
    private void setExcelRespProp(HttpServletResponse response, String sheetName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        String fileName = URLEncoder.encode(sheetName,"UTF-8").replaceAll("\\+","%20");
        response.setHeader("Content-disposition","attachment;filename*=utf-8''"+fileName+".xlsx");
    }

    /**
     * 从Excel导入用户列表（简单导入）
     * @param file
     * @return
     */
    @ApiOperation("从Excel导入用户列表")
    @PostMapping("/importUserList")
    @SneakyThrows(Exception.class)
    public Map<String,Object> importUserList(@RequestPart("file")MultipartFile file){
        Map<String,Object> result = new HashMap<>();
        List<User> userList = EasyExcel.read(file.getInputStream()).head(UserExcel.class)
                .sheet().doReadSync();
        //把excel中的用户列表插入数据库
        //批量插入
        userMapper.insertBacth(userList);
        result.put("code",0);
        result.put("message","success");
        return result;
    }

    /**
     * 导出用户列表信息（复杂导出）
     * @param response
     */
    @ApiOperation("导出用户列表信息")
    @GetMapping("/exportUserList1")
    @SneakyThrows(Exception.class)
    public void exportUserList1(HttpServletResponse response){
        setExcelRespProp(response,"用户列表");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> list= userMapper.selectList(queryWrapper);
        EasyExcel.write(response.getOutputStream()).head(UserExcel.class)
                .excelType(ExcelTypeEnum.XLSX).sheet("用户列表")
                .doWrite(list);
    }
}
