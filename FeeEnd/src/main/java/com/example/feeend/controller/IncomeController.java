package com.example.feeend.controller;

import com.example.feeend.domain.Income;
import com.example.feeend.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("income")
@CrossOrigin("http://localhost:8080")
public class IncomeController {
    /**
     * 使用mybatispuls
     * 方法参数注入： 直接将依赖作为方法参数，Spring Boot将根据类型进行自动注入。这种方式在方法级别进行注入。
     */
    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    /**
     * 获取所有收入记录
     */
    @GetMapping("/all")
    @ResponseBody
    public List<Income> AllIncome() {
        return incomeService.IncomeAll();
    }

    /**
     * 根据ID获取一条收入记录
     */
    @GetMapping("/searchId")
    @ResponseBody
    public Income IncomeById(@RequestParam Integer id) {
        return incomeService.IncomeById(id);
    }

    /**
     * 根据标题模糊查询收入记录
     */
    @GetMapping("/searchTitle")
    @ResponseBody
    public List<Income> IncomeByTitle(@RequestParam String title) {
        return incomeService.IncomeByTitle(title);
    }

    /**
     * 查询总收入
     * @return
     */
    @GetMapping("/sum")
    @ResponseBody
    public double SumReceipt(){
        return incomeService.SumReceipt();
    }
    /**
     * 查询总预收
     * @return
     */
    @GetMapping("/count")
    @ResponseBody
    public double SumBudget(){
        return incomeService.SumBudget();
    }

    /**
     * 新增收入记录
     */
    @PostMapping("/add")
    @ResponseBody
    public boolean IncomeAdd(@RequestBody Income income) {
        return incomeService.IncomeAdd(income);
    }

    /**
     * 编辑收入记录
     */
    @PostMapping("/edit")
    @ResponseBody
    public boolean IncomeEdit(@RequestBody Income income) {


        return incomeService.IncomeEdit(income);
    }



    /**
     * 删除收入记录
     */
    @PostMapping("/del")
    @ResponseBody
    public boolean IncomeDel(@RequestBody Income income) {
        if (income.getId() != null) {
            boolean s = incomeService.IncomeDel(income.getId());
            return s;
        } else {
            return false;
        }
    }

}
