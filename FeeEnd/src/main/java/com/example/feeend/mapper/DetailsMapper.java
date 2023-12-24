package com.example.feeend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.feeend.domain.Details;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsMapper extends BaseMapper<Details> {

    @Select("select * from details where incomeid = #{incomeid}")
    List<Details> DetailsByIncomeId(@Param("incomeid") Integer incomeid);


    @Update("update details set cashpay=#{cashpay},wechat=#{wechat},alipay=#{alipay},unpaid=#{unpaid} where id=#{id}")
    int DetailsEdit(Details details);
}
