package com.dec24.jobportal.services;

import com.dec24.jobportal.entity.JobSeekerProfile;
import com.dec24.jobportal.entity.RecruiterProfile;
import com.dec24.jobportal.entity.Users;
import com.dec24.jobportal.repository.JobSeekerProfileRepository;
import com.dec24.jobportal.repository.RecruiterProfileRepository;
import com.dec24.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository= jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    public Users addNew(Users user) {
        user.setActive(true);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        Users savedUser = usersRepository.save(user);
        int userTypeId = user.getUserTypeId().getUserTypeId();
        if(userTypeId == 1) {
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        }
        else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }
        return savedUser;
    }

    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

}
