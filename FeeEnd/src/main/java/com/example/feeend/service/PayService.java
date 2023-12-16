package com.example.feeend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.feeend.domain.Pay;
import com.example.feeend.mapper.PayMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayService extends ServiceImpl<PayMapper, Pay> {

    /**
     * 查询所有收入记录
     */
    public List<Pay> PayAll() {
        return baseMapper.PayAll();
    }

    /**
     * 根据ID获取一条支出记录
     */
    public Pay PayById(Integer id) {
        return baseMapper.PayById(id);
    }

    /**
     * 根据标题模糊查询支出记录
     */
    public List<Pay> PayByItem(String item) {
        return baseMapper.PayByItem(item);
    }

    /**
     * 查询总支出
     * @return
     */
    public double SumPayout(){
        return baseMapper.SumPayout();
    }

    /**
     * 新增支出记录
     */
    public boolean PayAdd(Pay pay) {
        try {
            baseMapper.PayAdd(pay);
            return true;
        }catch (DuplicateKeyException e){
            return false;
        }
    }

    /**
     * 编辑支出记录
     */
    public boolean PayEdit(Pay pay) {
        try {
            baseMapper.PayEdit(pay);
            return true;
        }catch (DuplicateKeyException e){
            return false;
        }
    }

    /**
     * 删除支出记录
     */
    public boolean PayDel(Integer id) {
        int r =  baseMapper.PayDel(id);
        if(r>0){
            return true;
        }else {
            return false;
        }
    }
}
