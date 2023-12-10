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
            " (label is null or label like concat('%',label,'%')) ")
    List<Pay> PayByLabel(@Param("explain")String label);


    /**
     * 新增
     */
    @Insert("insert into pay(label,payout)values (#{label},#{payout})")
    int PayAdd(Pay pay);

    /**
     * 编辑
     * @param pay
     * @return
     */
    @Update("update pay set label=#{label},payout=#{payout} where id=#{id}")
    int PayEdit(Pay pay);


    /**
     * 删除
     */
    @Delete("delete from pay where id = #{id}")
    int PayDel(@Param("id") Integer id);
}
