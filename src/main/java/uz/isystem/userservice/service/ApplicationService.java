package uz.isystem.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.isystem.userservice.model.Application;
import uz.isystem.userservice.repository.ApplicationRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application createApplication(Application application) {
        application.setCreatedAt(LocalDateTime.now());
        application.setStatus(true);
        applicationRepository.save(application);
        return application;
    }

    public Application get(Integer id) {
        Optional<Application> getAppication=applicationRepository.findById(id);
        if(getAppication.isEmpty()){
            throw new RuntimeException("Application not found");
        }
        return getAppication.get();
    }

    public Application updateApp(Application application, Integer id) {
        Application oldApplication=get(id);
        oldApplication.setUpdatedAt(LocalDateTime.now());
        oldApplication.setUserId(application.getUserId());
        oldApplication.setJobId(application.getJobId());
        oldApplication.setSalary(application.getSalary());
        oldApplication.setStartDate(application.getStartDate());
        oldApplication.setEndDate(application.getEndDate());
        applicationRepository.save(oldApplication);
        return oldApplication;
    }


    public String delete(Integer id) {
        Application application1=get(id);
        applicationRepository.delete(application1);
        return "Delet Application";
    }
}
