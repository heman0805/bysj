package com.heman.bysj.controller;

import com.heman.bysj.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value="/searchAllCollege")
    @ResponseBody
    public List<String> selectClassByProfession(){
        List<String> colleges = searchService.searchAllCollege();
        return colleges;
    }
    @RequestMapping(value="/searchProfessionesByCollege/{college}")
    @ResponseBody
    public List<String> searchProfessionesByCollege(@PathVariable("college") String college){
        List<String> profession = searchService.searchProfessionesByCollege(college);
        return profession;
    }
    @RequestMapping(value="/searchclassesByProfession/{profession}")
    @ResponseBody
    public List<String> searchclassesByProfession(@PathVariable("profession") String profession){
        List<String> classes = searchService.searchClassesByProfession(profession);
        return classes;
    }
    @RequestMapping(value="/searchclassesByProfessionAndGrade/{profession}/{grade}")
    @ResponseBody
    public List<String> searchclassesByProfessionAndGrade(@PathVariable("profession") String profession
                                                         ,@PathVariable("grade") int grade){
        List<String> classes = searchService.searchClassesByProfessionAndGrade(profession,grade);
        return classes;
    }
}
