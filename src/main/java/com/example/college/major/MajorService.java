package com.example.college.major;

import com.example.college.subject.Subject;
import com.example.college.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.college.randomhelper.RandomHelper.generateRandomNumberFromRange;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;
    private final SubjectRepository subjectRepository;
    public void addSubjectsToAllMajors() {
        majorRepository.findAll().forEach(major -> {
                addSubject(major, "Przyrka");
                addSubject(major, "Matma");
                addSubject(major, "WOS");
        });
    }

    private void addSubject(Major major, String name) {
        Subject subject = new Subject(name,generateRandomNumberFromRange(1,10));
        subject.setMajor(major);
        major.getSubjectList().add(subject);
        subjectRepository.save(subject);
        majorRepository.save(major);
    }
}
