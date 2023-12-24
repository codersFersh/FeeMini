package com.example.feeend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.feeend.domain.Income;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//持久层
@Repository
//在对应的Mapper上继承基本的类BaseMapper
public interface IncomeMapper extends BaseMapper<Income> {
    //所有的crud

    /**
     * 获取所有支出收入信息
     */
    @Select("select * from income ORDER BY id DESC")
    List<Income> IncomeAll();


    /**
     * 根据id获取一条记录
     */
    @Select("select * from income where id =#{id}")
    Income IncomeById (Integer id);


    /**
     * 根据title进行模糊查询
     */
    @Select("select * from income" +
            " where " +
            "(title is null or title like concat('%',#{title},'%') )")
    List<Income> IncomeByTitle(@Param("title")String title);


    /**
     * 查询总收入
     * @return
     */
    @Select("select sum(receipt) from income")
    double SumReceipt();

    /**
     * 获取总预收
     * @return
     */
    @Select("select sum(budget) from income")
    double SumBudget();

    /**
     * 新增
     */
    @Insert("insert into income(title,budget,classsize,status,state,descr) values(#{title},#{budget},#{classsize},#{status},#{state},#{descr})")
    int IncomeAdd (Income income);

    /**
     * 编辑
     */
    @Update("update income set" +
            " title=#{title}," +
            "budget=#{budget}," +
            "receipt=#{receipt}," +
            "paysize=#{paysize}, " +
            "status=#{status}," +
            "descr=#{descr} " +
            "where" +
            " id=#{id}")
    int IncomeEdit(Income income);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("delete from income where id =#{id}")
    int IncomeDel(@Param("id") Integer id);


    /**
     * 获取状态为未完成的班级收入记录
     * @param
     * @return
     */
    @Select("select * from income where status != 2")
    List<Income> WarnStatus ();

    /**
     * 已完成缴费
     * @param
     * @return
     */

    @Select("select * from income where status = 2")
    List<Income> SuccessStatus();

}
