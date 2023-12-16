package com.example.feeend.controller;

import com.example.feeend.domain.Pay;
import com.example.feeend.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pay")
@CrossOrigin("http://localhost:8080/")
public class PayController {
    private final PayService payService;

    @Autowired
    public PayController(PayService payService) {
        this.payService = payService;
    }

    /**
     * 获取所有支出记录
     */
    @GetMapping("/all")
    @ResponseBody
    public List<Pay> PayAll() {
        return payService.PayAll();
    }

    /**
     * 根据ID获取一条支出记录
     */
    @GetMapping("/searchId")
    @ResponseBody
    public Pay IncomeById(@RequestParam Integer id) {
        return payService.PayById(id);
    }

    /**
     * 根据标题模糊查询支出记录
     */
    @GetMapping("/searchItem")
    @ResponseBody
    public List<Pay> PayByItem(@RequestParam(required = false) String item) {
        return payService.PayByItem(item);
    }

    /**
     * 查询总支出
     * @return
     */
    @GetMapping("/sum")
    @ResponseBody
    public double SumPayout(){
        return payService.SumPayout();
    }

    /**
     * 新增支出记录
     */
    @PostMapping("/add")
    @ResponseBody
    public boolean PayAdd(@RequestBody Pay pay) {
        return payService.PayAdd(pay);
    }

    /**
     * 编辑支出记录
     */
    @PostMapping("/edit")
    @ResponseBody
    public boolean PayEdit(@RequestBody Pay pay) {
        return payService.PayEdit(pay);
    }

    /**
     * 删除支出记录
     */
    @PostMapping("/del")
    @ResponseBody
    public boolean PayDel(@RequestBody Pay pay) {
        if (pay.getId() != null) {
            boolean s = payService.PayDel(pay.getId());
            return s;
        } else {
            return false;
        }
    }

}
