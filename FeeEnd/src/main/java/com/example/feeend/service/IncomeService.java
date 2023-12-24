package com.example.feeend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.feeend.domain.Income;
import com.example.feeend.mapper.IncomeMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService extends ServiceImpl<IncomeMapper, Income> {

    /**
     * 查询所有收入记录
     */
    public List<Income> IncomeAll() {
        return baseMapper.IncomeAll();
    }

    /**
     * 根据ID获取一条收入记录
     */
    public Income IncomeById(Integer id) {
        return baseMapper.IncomeById(id);
    }

    /**
     * 根据标题模糊查询收入记录
     */
    public List<Income> IncomeByTitle(String title) {
        return baseMapper.IncomeByTitle(title);
    }

    /**
     * 查询总收入
     * @return
     */
    public double SumReceipt(){
        return baseMapper.SumReceipt();
    }

    /**
     * 查询总预收
     * @return
     */
    public double SumBudget(){
        return baseMapper.SumBudget();
    }


    /**
     * 新增收入记录
     */
    public boolean IncomeAdd(Income income) {
        try {
            baseMapper.IncomeAdd(income);
            return true;
        }catch (DuplicateKeyException e){
            return false;
        }
    }

    /**
     * 编辑收入记录
     */
    public boolean IncomeEdit(Income income) {
        try {
            baseMapper.IncomeEdit(income);
            return true;
        }catch (DuplicateKeyException e){
            return false;
        }
    }

    /**
     * 删除收入记录
     */
    public boolean IncomeDel(Integer id) {
        int r =  baseMapper.IncomeDel(id);
        if(r>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取状态为未完成的班费记录
     * @param
     * @return
     */
    public List<Income> WarnStatus(){
        return baseMapper.WarnStatus();
    }

    /**
     * 已完成缴费
     * @param
     * @return
     */
    public List<Income> SuccessStatus(){
        return baseMapper.SuccessStatus();
    }
}
