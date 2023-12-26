package com.example.feeend.controller;

import com.example.feeend.domain.Income;
import com.example.feeend.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Controller 一般应用在有返回界面的应用场景下
 * @RestController 如果只是接口，那么就用 RestController 来注解.
 */

@RestController
//将HTTP请求映射到控制器方法上
@RequestMapping("income")
@CrossOrigin("http://localhost:8080")
public class IncomeController {
    /**
     * 使用mybatispuls
     * 方法参数注入： 直接将依赖作为方法参数，Spring Boot将根据类型进行自动注入。
     * 这种方式在方法级别进行注入。
     */
    //构造注入所以要修饰成员变量
    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    /**
     * 获取所有收入记录
     */
    @GetMapping("/all")
    //将java对象转为json格式的数据
    @ResponseBody
    public List<Income> AllIncome() {
        return incomeService.IncomeAll();
    }

    /**
     * 根据ID获取一条收入记录
     * @RequestParam将指定的请求参数赋值给方法中的形参。
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
     *
     * @RequestBody最多只能有一个，而@RequestParam()可以有多个。
     * @RequestBody接收前端传递给后端的json字符串中的数据
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

    /**
     * 获取未完成和待处理的班费收入信息。
     * @param
     * @return
     */
    @GetMapping("/warning")
    @ResponseBody
    public List<Income> WarnStatus(){
        return incomeService.WarnStatus();
    }

    /**
     * 获取已经完成的缴费记录
     * @param
     * @return
     */
    @GetMapping("/success")
    @ResponseBody
    public List<Income> SuccessStatus(){
        return incomeService.SuccessStatus();
    }
}
