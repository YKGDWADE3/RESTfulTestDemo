package com.example.demo.controller;

import com.example.demo.UserNotFoundEx;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    UserRepository mRepository;

    @ApiOperation(value="新建", notes="")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    User saveCompany(@RequestBody User user) {
        return mRepository.save(user);
    }

    @ApiOperation(value="更新", notes="")
    @PutMapping("{id}")
    User putCompany(@PathVariable long id, @RequestBody User user) throws NotFoundException {
        Optional<User> byId = mRepository.findById(id);
        if (!byId.isPresent()) {

            throw new NotFoundException("not found");
        }
        user.setId(id);
        return mRepository.save(user);
    }

    @ApiOperation(value="查找所有", notes="")
    @GetMapping
    List<User> getUserList() {
        return mRepository.findAll();
    }

    @ApiOperation(value="查找某一个", notes="")
    @GetMapping(value = "{id}")
    User getUser(@PathVariable long id) throws NotFoundException {
        Optional<User> optionalUser = mRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundEx(id);
        } else {
            return optionalUser.get();
        }

    }

    @ApiOperation(value="删除某一个", notes="")
    @DeleteMapping("{id}")
    String  deleteUser(@PathVariable long id) {
        Optional<User> optionalUser = mRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundEx(id);
        } else {
            mRepository.delete(optionalUser.get());
            return "delete success";
        }
    }

    @ApiOperation(value="删除所有", notes="")
    @DeleteMapping
    String deleteAllUser() {
        mRepository.deleteAll();
        return "delete all success";
    }

    /*@GetMapping("{companyId}/employees")
    List<Employee> getEmployeesByCompanyId(@PathVariable long companyId) {
        return mCompanyService.getEmployeesByCompanyId(companyId);
    }*/
}
