package com.casestudy.appraisal.controllers;

import com.casestudy.appraisal.DTO.TaskDTO;
import com.casestudy.appraisal.DTO.TaskListDTO;
import com.casestudy.appraisal.dao.AppDAO;
import com.casestudy.appraisal.dao.TaskDAO;
import com.casestudy.appraisal.entity.Task;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/employee/tasks")
public class TaskController {

    private TaskDAO taskDAO;

    @Autowired
    public TaskController(TaskDAO taskDAO){
        this.taskDAO=taskDAO;
    }

    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/{id}")
    public ResponseEntity<List<TaskDTO>> getTasksByEmployeeId(@PathVariable int id){
        List<Task> tasks=taskDAO.getTaskByEmployeeId(id);
        List<TaskDTO> taskDTOs = tasks.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(taskDTOs,HttpStatus.OK);
    }


    @GetMapping("/getTasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id){
        Task task=taskDAO.getTask(id);

        return new ResponseEntity<>(task,HttpStatus.OK);
    }

    @GetMapping("/appraisal/{id}")
    public ResponseEntity<?> getAppraisalTaskByEmployeeId(@PathVariable int id){
        List<Task> tasks=taskDAO.getAppraisalTasks(id);
        if(tasks.isEmpty()) return new ResponseEntity<>("No appraisal Tasks..",HttpStatus.NOT_FOUND);

        List<TaskDTO> taskDTOs = tasks.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(taskDTOs,HttpStatus.OK);
    }

    @DeleteMapping("/removeTask/{id}")
    public ResponseEntity<String> delTask(@PathVariable int id){

        taskDAO.removeTask(id);
        return new ResponseEntity<>("Its done", HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Task> addTasksByEmployeeId(@RequestBody Task theTask,@PathVariable int id){
        Task tsk=taskDAO.addTaskForEmployee(theTask,id);

        return new ResponseEntity<>(tsk,HttpStatus.OK);
    }

    @PostMapping("/makeAppraisal")
    public ResponseEntity<?> addAppraisalTask(@RequestBody TaskListDTO taskListDTO){
        List<Integer> taskIds=taskListDTO.getTaskIds();
        boolean save=taskDAO.makeTaskAppraisable(taskIds);
        System.out.println(taskIds.toString());

        if(save) return new ResponseEntity<>("Succesfully applied for appraisal",HttpStatus.OK);

        return new ResponseEntity<>("Not valid for appraisal",HttpStatus.BAD_REQUEST);
    }
}
