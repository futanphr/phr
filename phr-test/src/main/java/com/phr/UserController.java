package com.phr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
 
    @Autowired
    UserService userService;
 
    @GetMapping("getUser1")
    public String getUser1() {
        return userService.getUser1(003);
    }
    @GetMapping("getUser2")
    public String getUse1r() {
        return userService.getUser2(005);
    }
}
