package com.dau;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DauController {

    @Autowired
    private DauService service;

    //url example: http://localhost:8080/search?date=02/01/2020
    @RequestMapping(value="search", method = RequestMethod.GET)
    public @ResponseBody String getItem(@RequestParam("date") String inputDate){
        return service.searchDailyActiveUserByDate(inputDate);
    }

}
