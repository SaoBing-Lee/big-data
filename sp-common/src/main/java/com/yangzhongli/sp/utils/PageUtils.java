package com.yangzhongli.sp.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtils {

    /**
     * 分页参数校验
     * @param pageNum
     * @param pageSize
     */
    public static Page startPage(Integer pageNum, Integer pageSize){
        if(pageNum == null || pageNum < 1 ){
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1){
            pageSize = 10;
        }
        return PageHelper.startPage(pageNum, pageSize, true);
    }

    /**
     * 获取分页对象，由于pageHelper 不能进行数据转换，所以自己封装一层
     * @param list 需要转换的原集合
     * @param tClass 转换后的类
     * @param <E>需要转换的原集合对象
     * @param <T>转换后的对象
     * @return
     */
    public static <E, T> PageInfo<T> getPageInfo(List<E> list, Class<T> tClass) {
        MyPageInfo<T> pageInfo = new MyPageInfo<>(list, tClass, 8);
        return ConverterUtil.convert(PageInfo.class, pageInfo);
    }
}
