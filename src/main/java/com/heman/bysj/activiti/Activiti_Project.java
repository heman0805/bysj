package com.heman.bysj.activiti;

import com.heman.bysj.entity.User;
import com.heman.bysj.enums.UserGroup;
import com.heman.bysj.enums.UserRole;
import com.heman.bysj.jooq.tables.pojos.Changemajors;
import com.heman.bysj.jooq.tables.pojos.Project;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.ProjectRecord;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooq.tables.records.TeacherRecord;
import com.heman.bysj.jooqService.ExamineDao;
import com.heman.bysj.jooqService.ProjectDao;
import com.heman.bysj.jooqService.StudentDao;
import com.heman.bysj.jooqService.TeacherDao;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class Activiti_Project {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ExamineDao examineDao;
    @Autowired
    private ProjectDao projectDao;
    /**
     * 启动项目申请流程
     * @param project
     */
    public  String startInstance(Project project,int group){
        String businessKey = project.getFormid();
        Map<String,Object> map = new HashMap<>();
        map.put("name",project.getUserid().toString());
        map.put("group",group);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("projectProcess",businessKey,map);
        log.info("转专业申请流程启动成功，申请人id:{}",project.getUserid());
        return processInstance.getId();
    }
    /**
     * 查询并完成任务
     * @param project
     */
    public void completeWrite(Project project){
        //1、通过userID查询任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("projectProcess")
                .taskAssignee(project.getUserid().toString())
                .list();
        for (Task task:taskList) {
            String pid = task.getProcessInstanceId() ;
            if(pid.equals(project.getProcessinstanceid()))
                taskService.complete(task.getId());
        }
        log.info("完成转专业申请任务");
    }
    /**
     1、查找当前用户任务
     * 2、通过任务获取流程实例id
     * 3、通过流程实例ID查找holiday表得到对应userid及role
     * 4、查询对应用户表判断是否应为当前用户审批
     * 5、进行拾取(任务处理人设置为teacher.username--username唯一)
     * @param definitionKey
     * @param teacher 登录教师信息（eg:辅导员-Group_Instructor）
     */
    public void teacherClaimTask(String definitionKey, Teacher teacher){
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(definitionKey)
                .taskCandidateGroup(teacher.getGroup())
                .list();
        log.info("登录用户信息组为："+teacher.getGroup());
        for (Task task:taskList) {
            log.info("待拾取任务ID："+task.getId());
            String processInstanceId = task.getProcessInstanceId();
            ProjectRecord projectRecord = projectDao.selectByProcessInstanceId(processInstanceId);
            User user = new User();
            if(projectRecord==null)
                continue;
            if(projectRecord.getRole().equals(UserRole.TEACHER)){
                TeacherRecord t = teacherDao.selectById(projectRecord.getUserid());
                user.setCollege(t.getCollege());
                user.setProfession(t.getProfession());
                user.setUsername(t.getUsername());
                user.setRole(t.getRole());
                user.setUserId(t.getTid());
                user.setName(t.getName());
            }
            else if(projectRecord.getRole().equals(UserRole.STUDENT)){
                StudentRecord s = studentDao.selectById(projectRecord.getUserid());
                user.setCollege(s.getCollege());
                user.setProfession(s.getProfession());
                user.setUsername(s.getUsername());
                user.setRole(s.getRole());
                user.setUserId(s.getSid());
                user.setName(s.getName());
            }

            //判断学生/教师与审批教师是否为同一专业
            if(teacher.getGroup().equals(UserGroup.GROUP_INSTRUCTOR)||teacher.getGroup().equals(UserGroup.Group_DEPARTMENTDIRECTOR)){//辅导员、系主任
                if(teacher.getProfession().equals(user.getProfession())){
                    taskService.claim(task.getId(),teacher.getUsername());
                }
            }
            else if(teacher.getGroup().equals(UserGroup.GROUP_VICEDEAN)){
                if(teacher.getCollege().equals(user.getCollege())){
                    taskService.claim(task.getId(),teacher.getUsername());
                }
            }else if(teacher.getGroup().equals(UserGroup.GROUP_FINANCE)||teacher.getGroup().equals(UserGroup.GROUP_SCIANDTECH)){
                taskService.claim(task.getId(),teacher.getUsername());
            }
        }
    }
    /**
     1、查找当前用户任务
     * 2、通过任务获取流程实例id
     * 3、通过流程实例ID查找holiday表得到对应userid及role
     * 4、查询对应用户表判断是否应为当前用户审批
     * 5、进行任务放回
     * @param definitionKey
     * @param teacher 登录教师信息（eg:辅导员-Group_Instructor）
     */
    public void putPool(String definitionKey, Teacher teacher){
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(definitionKey)
                .taskAssignee(teacher.getUsername())
                .list();
        System.out.println("释放请假任务，查询任务数量："+taskList.size());
        log.info("登录用户信息组为："+teacher.getGroup());
        for (Task task:taskList) {
            log.info("待拾取任务ID："+task.getId());
            String processInstanceId = task.getProcessInstanceId();
            ProjectRecord projectRecord = projectDao.selectByProcessInstanceId(processInstanceId);
            User user = new User();
            if(projectRecord==null)
                continue;
            if(projectRecord.getRole().equals(UserRole.TEACHER)){
                TeacherRecord t = teacherDao.selectById(projectRecord.getUserid());
                user.setCollege(t.getCollege());
                user.setProfession(t.getProfession());
                user.setUsername(t.getUsername());
                user.setRole(t.getRole());
                user.setUserId(t.getTid());
                user.setName(t.getName());
            }
            else if(projectRecord.getRole().equals(UserRole.STUDENT)){
                StudentRecord s = studentDao.selectById(projectRecord.getUserid());
                user.setCollege(s.getCollege());
                user.setProfession(s.getProfession());
                user.setUsername(s.getUsername());
                user.setRole(s.getRole());
                user.setUserId(s.getSid());
                user.setName(s.getName());
            }

            //判断学生/教师与审批教师是否为同一专业
            if(teacher.getGroup().equals(UserGroup.GROUP_INSTRUCTOR)||teacher.getGroup().equals(UserGroup.Group_DEPARTMENTDIRECTOR)){//辅导员、系主任
                if(teacher.getProfession().equals(user.getProfession())){
                    taskService.setAssignee(task.getId(),null);
                }
            }
            else if(teacher.getGroup().equals(UserGroup.GROUP_VICEDEAN)){
                if(teacher.getCollege().equals(user.getCollege())){
                    taskService.setAssignee(task.getId(),null);
                }
            }else if(teacher.getGroup().equals(UserGroup.GROUP_FINANCE)||teacher.getGroup().equals(UserGroup.GROUP_SCIANDTECH)){
                taskService.setAssignee(task.getId(),null);
            }
        }
    }
    /**
     * 教师查询待处理任务（待审批任务）
     * @param processDefinitionKey
     * @param teacher
     * @return
     */
    public List<Task> findTask(String processDefinitionKey,Teacher teacher){
        //3.查询当前用户的任务
        //任务拾取后通过assignee进行查找

        System.out.println("进入查询任务方法");
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(processDefinitionKey)
                //.taskCandidateGroup(teacher.getGroup())
                .taskAssignee(teacher.getUsername())
                .list();

        System.out.println("查询待办任务列表为："+taskList);
        return taskList;
    }
    /**
     * 通过processDefinitionKey及processInstanceID查找任务并完成任务
     * @param processDefinitionKey
     * @param processInstanceId
     * @return
     */
    public String teacherCompleteTask(String processDefinitionKey,String processInstanceId){
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(processDefinitionKey)
                .processInstanceId(processInstanceId)
                .singleResult();
        taskService.complete(task.getId());
        return task.getId();
    }
    /**
     * 通过processDefinitionKey及processInstanceID查找任务并拒绝任务
     * @param processDefinitionKey
     * @param processInstanceId
     * @return
     */
    public String stopProcessInstance(String processDefinitionKey,String processInstanceId){
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(processDefinitionKey)
                .processInstanceId(processInstanceId)
                .singleResult();
        runtimeService.deleteProcessInstance(processInstanceId,"结束流程");
        return task.getId();
    }
    /**查询历史任务*/
    public List<HistoricTaskInstance> findHistoryTaskByBusinessKey(String processInstanceId){
        List<HistoricTaskInstance> majorProcess = historyService//与历史数据（历史表）相关的Service
                .createHistoricTaskInstanceQuery()
                .processDefinitionKey("projectProcess")
                .processInstanceId(processInstanceId)
                .list();

        return majorProcess;
    }

}
