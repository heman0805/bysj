package com.heman.bysj.service;

import java.util.List;

public interface SearchService {
    List<String> searchAllCollege();
    List<String> searchProfessionesByCollege(String college);
    List<String> searchClassesByProfession(String profession);
    List<String> searchClassesByProfessionAndGrade(String profession,int grade);
}
