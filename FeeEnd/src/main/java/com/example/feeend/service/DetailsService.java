package com.example.feeend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.feeend.domain.Details;
import com.example.feeend.domain.Income;
import com.example.feeend.mapper.DetailsMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsService extends ServiceImpl<DetailsMapper, Details> {
    /**
     * 根据incomeid获取详细缴费信息
     * @param incomeid
     * @return
     */
    public List<Details> DetailsByIncomeId (Integer incomeid) {
       return baseMapper.DetailsByIncomeId(incomeid);
    }


    public boolean DetailsEdit(Details details) {
        try {
            baseMapper.DetailsEdit(details);
            return true;
        }catch (DuplicateKeyException e){
            return false;
        }
    }
}
