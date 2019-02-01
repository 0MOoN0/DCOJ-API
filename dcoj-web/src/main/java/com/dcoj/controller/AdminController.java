package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Smith
 **/
//@RestController
@Validated
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminController {

    @RequiresRoles(value = {"8", "9"}, logical = Logical.OR)
    @GetMapping("/overview")
    public ResponseEntity getOverview() {
        return new ResponseEntity(1,"you are in admin",null);
    }
}
