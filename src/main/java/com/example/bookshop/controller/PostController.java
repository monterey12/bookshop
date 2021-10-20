package com.example.bookshop.controller;

import com.example.bookshop.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

//    @RequestMapping(method = RequestMethod.POST, path = "/postMethod")
    @PostMapping(path = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {

        return searchParam;

    }

    @PutMapping("/putMethod")
    public void put(){

    }

    @PatchMapping("/patchMethod")
    public void patch(){

    }

    @DeleteMapping("/deleteMethod")
    public void delete(){

    }
}
