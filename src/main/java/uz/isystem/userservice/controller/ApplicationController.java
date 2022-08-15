package uz.isystem.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.isystem.userservice.model.Application;
import uz.isystem.userservice.service.ApplicationService;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;
    // Salom Java Backend
    // Bu yengi code uni ozinga olib ol
    // Bu yana yengi qator
    // Bu yerda sizning reklamangiz bolishi mumkun edi
    // Bu yerda funksiya mavjud
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Application application){
        Application result = applicationService.createApplication(application);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id")Integer id){
        Application result = applicationService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Application application,
                              @PathVariable ("id")Integer id){
        Application result = applicationService.updateApp(application,id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable ("id") Integer id){
        String result = applicationService.delete(id);
        return ResponseEntity.ok(result);
    }


}
