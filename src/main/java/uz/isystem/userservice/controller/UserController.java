package uz.isystem.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.isystem.userservice.model.Job;
import uz.isystem.userservice.model.User;
import uz.isystem.userservice.service.UserService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        User result = userService.create(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id) {
        User result = userService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody User user) {
        User result = userService.update(id, user);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        String result = userService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> page(@RequestParam("size") Integer size,
                                  @RequestParam("page") Integer page) {
        List<User> result = userService.page(size, page);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/jobs")
    public ResponseEntity<?> getJobs(@PathVariable("id") Integer id){
        List<Job> result = userService.getJobs(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/salary/sum")
    public ResponseEntity<?> getSumSalary(@PathVariable("id") Integer id){
        Double result = userService.getSumSalary(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/salary/{date}")
    public ResponseEntity<?> getSumSalary(@PathVariable("id") Integer id,
                                          @PathVariable("date") String date){
        Double result = userService.getSalaryByMonth(id, LocalDate.parse(date));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/salary")
    public ResponseEntity<?> getPresentSalary(@PathVariable("id") Integer id){
        Double result = userService.getSalaryByMonth(id, LocalDate.now());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/job/{date}")
    public ResponseEntity<?> getJobByDate(@PathVariable("id") Integer id,
                                          @PathVariable("date") String date){
        List<Job> result = userService.getJobByDate(id, LocalDate.parse(date));
        return ResponseEntity.ok(result);
    }

}
