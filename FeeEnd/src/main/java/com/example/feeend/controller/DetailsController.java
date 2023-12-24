package com.example.feeend.controller;

import com.example.feeend.domain.Details;
import com.example.feeend.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("details")
@CrossOrigin("http://localhost:8080")
public class DetailsController {

    private final DetailsService detailsService;
    @Autowired
    public DetailsController(DetailsService detailsService){
        this.detailsService = detailsService;
    }

    @GetMapping("/incomeid")
    @ResponseBody
    public List<Details> DetailsByIncomeId(@RequestParam("incomeid") Integer incomeid){
        return detailsService.DetailsByIncomeId(incomeid);
    }

    @PostMapping("/edit")
    @ResponseBody
    public boolean DetailsEditEdit(@RequestBody Details details){
        return detailsService.DetailsEdit(details);
    }
}
