package com.example.druiddemo.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.util.StringUtils;

/**
 * excel gender转换器
 * @author qzz
 */
public class GenderConverter implements Converter<Integer> {

    @Override
    public Class<?> supportJavaTypeKey() {
        //对象属性类型
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        //CellData属性类型
        return CellDataTypeEnum.STRING;
    }

    /**
     * CellData转Java对象属性
     * @param context
     * @return
     * @throws Exception
     */
    @Override
    public Integer convertToJavaData(ReadConverterContext<?> context) throws Exception {
        //CellData转对象属性
        String cellStr = context.getReadCellData().getStringValue();
        if(StringUtils.isEmpty(cellStr)){
            return null;
        }else if("男".equals(cellStr)){
            return 0;
        }else if("女".equals(cellStr)){
            return 1;
        }else{
            return null;
        }
    }

    /**
     * Java 对象属性转CellData
     * @param context
     * @return
     * @throws Exception
     */
    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) throws Exception {

        //Java 对象属性转CellData
        Integer cellValue = context.getValue();
        if(cellValue == null){
            return new WriteCellData<>("");
        }

        if(cellValue == 0){
            return new WriteCellData<>("男");
        }else if(cellValue == 1){
            return new WriteCellData<>("女");
        }else{
            return new WriteCellData<>("");
        }
    }
}
