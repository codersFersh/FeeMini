package com.example.feeend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.feeend.domain.Pay;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayMapper extends BaseMapper<Pay> {
    //所有的crud

    /**
     * 获取所有支出信息
     */
    @Select("select * from pay")
    List<Pay> PayAll();


    /**
     * 根据id获取一条支出记录
     */
    @Select("select * from pay where id = #{id}")
    Pay PayById(@Param("id")Integer id);


    /**
     * 根据标签进行模糊查找获取支出记录
     */
    @Select("select * from pay" +
            " where" +
            " (`explain` is null or `explain` like concat('%',`explain`,'%')) ")
    List<Pay> PayByExplain(@Param("explain")String explain);


    /**
     * 新增
     */
    @Insert("insert into pay(explain,payout)values (#{expalin},#{payout})")
    int PayAdd(Pay pay);

    /**
     * 编辑
     * @param pay
     * @return
     */
    @Update("update pay set" +
            " explain=#{explain}" +
            "payout=#{payout}" +
            "where" +
            "id=#{id}")
    int PayEdit(Pay pay);


    /**
     * 删除
     */
    @Delete("delete from pay where id = #{id}")
    int PayDel(@Param("id") Integer id);
}
