package com.example.college.major;

import com.example.college.subject.Subject;
import com.example.college.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;
    private final SubjectRepository subjectRepository;

    public void createSubjectAddToMajor(Major major, String name, int amountECTS) {
        Subject subject = new Subject(name, amountECTS);
        subject.setMajor(major);
        major.getSubjectList().add(subject);
        subjectRepository.save(subject);
        majorRepository.save(major);
    }
}