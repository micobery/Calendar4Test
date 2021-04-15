package com.uniqmaster.calendar4test.service;

import com.uniqmaster.calendar4test.entity.Student;
import com.uniqmaster.calendar4test.entity.Teacher;
import com.uniqmaster.calendar4test.entity.UserEntity;
import com.uniqmaster.calendar4test.repository.StudentRepository;
import com.uniqmaster.calendar4test.repository.TeacherRepository;
import com.uniqmaster.calendar4test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    public UserService(UserRepository userRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public UserEntity saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }


}
