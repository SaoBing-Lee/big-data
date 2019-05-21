package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.po.LikesRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LikesRecordMapper extends MyMapper<LikesRecord> {
}
