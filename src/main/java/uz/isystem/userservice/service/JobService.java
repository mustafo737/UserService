package uz.isystem.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.isystem.userservice.exception.BadRequest;
import uz.isystem.userservice.model.Job;
import uz.isystem.userservice.repository.JobRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job) {
        job.setCreatedAt(LocalDateTime.now());
        job.setStatus(true);
        jobRepository.save(job);
        return job;
    }


    public Job getjob(Integer id) {
        Optional<Job> oldJob= jobRepository.findById(id);
        if(oldJob.isEmpty()){
            throw new BadRequest("Job not found");
        }
        return oldJob.get();
    }

    public Job updateJob(Job job, Integer id) {
        Job job1=getjob(id);
        job1.setUpdatedAt(LocalDateTime.now());
        job1.setTitle(job.getTitle());
        job1.setDescription(job.getDescription());
        jobRepository.save(job1);
        return job1;
    }


    public Job delete(Integer id) {
        Job job=getjob(id);
        jobRepository.deleteById(id);
        throw new BadRequest("Delet Job");
    }
}
