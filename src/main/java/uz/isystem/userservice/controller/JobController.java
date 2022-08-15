package uz.isystem.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.isystem.userservice.model.Job;
import uz.isystem.userservice.service.JobService;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody Job job){
        Job result =jobService.createJob(job);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJob(@PathVariable ("id")Integer id){
        Job result=jobService.getjob(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@RequestBody Job job,
                         @PathVariable ("id")Integer id){
        Job result=jobService.updateJob(job,id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delet(@PathVariable ("id")Integer id){
        Job result=jobService.delete(id);
        return ResponseEntity.ok(result);
    }
}
