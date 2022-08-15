package uz.isystem.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.isystem.userservice.exception.BadRequest;
import uz.isystem.userservice.model.Application;
import uz.isystem.userservice.model.Job;
import uz.isystem.userservice.model.User;
import uz.isystem.userservice.repository.ApplicationRepository;
import uz.isystem.userservice.repository.UserRepository;
import uz.isystem.userservice.type.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationRepository applicationRepository;

    public User create(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus(true);
        user.setUserType(UserType.VISITOR);
        userRepository.save(user);
        return user;
    }

    public User get(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequest("User not found");
        }
        return optional.get();
    }


    public User update(Integer id, User user) {
        User oldUser = get(id);
        oldUser.setFirstname(user.getFirstname());
        oldUser.setLastname(user.getLastname());
        oldUser.setBirth(user.getBirth());
        oldUser.setAvatar(user.getAvatar());
        oldUser.setAddress(user.getAddress());
        oldUser.setAddress2(user.getAddress2());
        oldUser.setCityId(user.getCityId());
        oldUser.setUpdatedAt(LocalDateTime.now());
        userRepository.save(oldUser);
        return oldUser;
    }

    public String delete(Integer id) {
        User user = get(id);
        userRepository.delete(user);
        return "User deleted";
    }

    public List<User> page(Integer size, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageRequest);
        List<User> userList = new LinkedList<>();
        for (User u : userPage) {
            userList.add(u);
        }
        return userList;
//        return userPage.stream().collect(Collectors.toList());
    }

    public List<Job> getJobs(Integer id) {
        get(id);
        List<Application> applicationList = applicationRepository.findByUserId(id);
        if (applicationList.isEmpty()) {
            throw new BadRequest("User has not any job");
        }
        List<Job> jobList = new LinkedList<>();
        for (Application application : applicationList) {
            jobList.add(application.getJob());
        }
        return jobList;
//        return applicationList.stream().map(Application::getJob).collect(Collectors.toList());
    }

    public Double getSumSalary(Integer id) {
        get(id);
        List<Application> applicationList = applicationRepository.findByUserId(id);
        if (applicationList.isEmpty()) {
            throw new BadRequest("User has not any job");
        }
        double sum = 0.0;
        for (Application application : applicationList) {
            if (application.getEndDate() == null) {
                long month = ChronoUnit.MONTHS.between(application.getStartDate(), LocalDate.now());
                sum += month * application.getSalary();
            } else {
                long month = ChronoUnit.MONTHS.between(application.getStartDate(), application.getEndDate());
                sum += month * application.getSalary();
                sum += application.getSalary() / application.getEndDate().lengthOfMonth() *
                        application.getEndDate().getDayOfMonth();
            }
        }
        return sum;
    }

    public Double getSalaryByMonth(Integer id, LocalDate date) {
        get(id);
        List<Application> applicationList = applicationRepository.applicationByUserIdAndMonth(id, date);
        double sum = 0.0;
        for (Application application : applicationList) {
            sum += application.getSalary();
        }
        return sum;
    }

    public List<Job> getJobByDate(Integer id, LocalDate date) {
        get(id);
        List<Application> applicationList = applicationRepository.applicationByUserIdAndMonth(id, date);
        List<Job> jobList = new LinkedList<>();
        for (Application application : applicationList) {
            jobList.add(application.getJob());
        }
        return jobList;
//        return applicationList.stream().map(Application::getJob).collect(Collectors.toList());
    }
}
