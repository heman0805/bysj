package com.heman.bysj.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    public String getToken(Object user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        if(user instanceof Student){
            Student student = (Student) user;
            token = JWT.create().withAudience(student.getSid().toString()).withIssuedAt(start).withExpiresAt(end)
                    .sign(Algorithm.HMAC256(student.getPassword()));
        }else{
            Teacher teacher = (Teacher) user;
            token = JWT.create().withAudience(teacher.getTid().toString()).withIssuedAt(start).withExpiresAt(end)
                    .sign(Algorithm.HMAC256(teacher.getPassword()));

        }
        return token;
    }

    public String getTokenS(Student user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(user.getSid().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
    public String getTokenT(Teacher user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(user.getTid().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
