package com.heman.bysj.service.impl;

import com.heman.bysj.enums.TeacherPosition;
import com.heman.bysj.enums.UserGroup;
import com.heman.bysj.enums.UserRole;
import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooq.tables.records.TeacherRecord;
import com.heman.bysj.jooqService.StudentDao;
import com.heman.bysj.jooqService.TeacherDao;
import com.heman.bysj.service.UserService;
import com.heman.bysj.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Student selectStudentById(int id) {
        return studentDao.selectById(id).into(Student.class);
    }

    @Override
    public TeacherRecord selectTeacherById(int id) {
        return teacherDao.selectById(id);
    }

    @Override
    public Student getStudentByUsername(String name, String password) {
        StudentRecord studentRecord = studentDao.getStudnetByUsername(name,password);
        if(studentRecord!=null)
            return studentRecord.into(Student.class);
        else
            return null;
        //return studentDao.getStudnetByUsername(name,password).into(Student.class);
    }

    @Override
    public Teacher getTeacherByUsername(String name, String password) {
        TeacherRecord teacherRecord = teacherDao.getTeacherByUsername(name,password);
        if(teacherRecord!=null)
            return teacherRecord.into(Teacher.class);
        else
            return null;
        //return teacherDao.getTeacherByUsername(name,password).into(Teacher.class);
    }

    @Override
    public String userRegist(Map user) {
        System.out.println("user的key值:"+user.keySet()+",user的value值:"+user.values());
        String role = user.get("role").toString();
        String msg = "";
        if(role.equals(UserRole.STUDENT)){
            System.out.println("学生注册");
            //判断该账号是否以注册
            StudentRecord studentRecord = studentDao.getByUserName(user.get("userName").toString());
            if(studentRecord!=null){
                msg = "该账号已注册";
                return msg;
            }
            //数据类型转换
            Student student = Convert.registerStudent(user);
            //设置tid
            TeacherRecord teacher = teacherDao.selectByProfessionAndPositionAndGrade(student.getProfession(),"辅导员",student.getGrade());
            if(teacher==null){
                msg = "注册失败,设置辅导员失败";
                return msg;
            }
            student.setTid(teacher.getTid());
            //插入数据库
            StudentRecord studentRecord1 = new StudentRecord();
            studentRecord1.from(student);
            studentDao.insertStudent(studentRecord1);
        }
        else {
            System.out.println("教师注册");
            //判断该账号是否以注册
            TeacherRecord teacherRecord = teacherDao.getByUserName(user.get("userName").toString());
            if(teacherRecord!=null){
                msg = "该账号已注册";
                return msg;
            }
            String grade = null;
            String college = null;
            String profession=null;
            String position = null;
            if(user.get("grade")!=null&&user.get("grade")!="")
                grade = user.get("grade").toString();
            if(user.get("college")!=null)
                college = user.get("college").toString();
            if(user.get("profession")!=null)
                profession = user.get("profession").toString();
            if(user.get("position")!=null)
                position = user.get("position").toString();
            //查询该学院、专业、职位、年级的教师账户是否已存在
            TeacherRecord teacherRecord1 = teacherDao.selectByParam(grade, college, profession, position);
            if(teacherRecord1!=null){
                msg = "该职位的账号已存在";
                return msg;
            }
            //数据类型转换
            Teacher teacher = Convert.registerTeacher(user);
            //设置group
            teacherSetRoleAndGroup(teacher);
            //插入数据库
            TeacherRecord insert = new TeacherRecord();
            insert.from(teacher);
            int result = teacherDao.insertTeacher(insert);
            System.out.println("插入结果"+result);
        }
        msg="注册成功";
        return msg;
    }

    private void teacherSetRoleAndGroup(Teacher teacher){
        if(teacher.getPosition().equals(TeacherPosition.FU_DAO_YUAN)){
            teacher.setRole(UserRole.ADMIN);
            teacher.setGroup(UserGroup.GROUP_INSTRUCTOR);
        }else if(teacher.getPosition().equals(TeacherPosition.SHU_JI)){
            teacher.setRole(UserRole.ADMIN);
            teacher.setGroup(UserGroup.GROUP_SECRETARY);
        }else if(teacher.getPosition().equals(TeacherPosition.XI_ZHU_REN)){
            teacher.setRole(UserRole.ADMIN);
            teacher.setGroup(UserGroup.Group_DEPARTMENTDIRECTOR);
        }else if(teacher.getPosition().equals(TeacherPosition.YUAN_ZHANG)){
            teacher.setRole(UserRole.ADMIN);
            teacher.setGroup(UserGroup.GROUP_DEAN);
        }else if(teacher.getPosition().equals(TeacherPosition.CAI_WU_CHU)){
            teacher.setRole(UserRole.Office);
            teacher.setGroup(UserGroup.GROUP_FINANCE);
        }else if(teacher.getPosition().equals(TeacherPosition.FU_YUAN_ZHANG)){
            teacher.setRole(UserRole.ADMIN);
            teacher.setGroup(UserGroup.GROUP_VICEDEAN);
        }else if(teacher.getPosition().equals(TeacherPosition.JIAO_SHI)){
            teacher.setRole(UserRole.TEACHER);
            teacher.setGroup(UserGroup.GROUP_TEACHER);
        }else if(teacher.getPosition().equals(TeacherPosition.KE_JI_CHU)){
            teacher.setRole(UserRole.Office);
            teacher.setGroup(UserGroup.GROUP_SCIANDTECH);
        }else if(teacher.getPosition().equals(TeacherPosition.XUE_XIAO_JIAO_WU_BAN)){
            teacher.setRole(UserRole.Office);
            teacher.setGroup(UserGroup.GROUP_ADMINISTRATIONOFFICE);
        }else if(teacher.getPosition().equals(TeacherPosition.XUE_YUAN_JIAO_WU_BAN)){
            teacher.setRole(UserRole.Office);
            teacher.setGroup(UserGroup.GROUP_ACADEMICAFFAIRS);
        }
    }

    @Override
    public String changePassword(Map form, Map user) {
        String msg = "";
        String role = user.get("role").toString();
        String password = user.get("password").toString();
        String oldPassword = form.get("oldPassword").toString();
        String newPassword = form.get("newPassword").toString();
        String resPassword = form.get("resPassword").toString();
        if(!password.equals(oldPassword)){
            msg = "原密码错误";
            return msg;
        }
        if(!newPassword.equals(resPassword)){
            msg = "两次密码不一致";
            return msg;
        }
        //角色为学生,修改学生表密码
        if(role.equals(UserRole.STUDENT)){

            int sid = Integer.parseInt(user.get("sid").toString());
            StudentRecord studentRecord = studentDao.selectById(sid);
            if(studentRecord==null){
                msg = "修改失败，请重新登录后再试";
                return msg;
            }
            int result = studentDao.updatePasswordBySid(sid,newPassword);
            if(result!=1){
                msg = "修改失败请重新尝试";
                return msg;
            }
        }
        //修改教师表密码
        else{
            int tid = Integer.parseInt(user.get("tid").toString());
            TeacherRecord teacherRecord = teacherDao.selectById(tid);
            if(teacherRecord==null){
                msg = "修改失败，请重新登录后再试";
                return msg;
            }
            int result = teacherDao.updatePasswordByTid(tid,newPassword);
            if(result!=1){
                msg = "修改失败请重新尝试";
                return msg;
            }
        }
        msg = "修改成功";
        return msg;
    }
}
