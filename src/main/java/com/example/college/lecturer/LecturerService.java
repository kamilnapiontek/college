package com.example.college.lecturer;

import com.example.college.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LecturerService {
    private static final int MAX_TEACHING_SUBJECTS = 3;
    private final LecturerRepository lecturerRepository;
    private final SubjectRepository subjectRepository;

    public void createLecturer(Lecturer lecturer) {
        lecturerRepository.save(lecturer);
    }

    public void assignSubjectToLecturer(long subjectId, long lecturerId) {
        Optional<Lecturer> lecturerOptional = lecturerRepository.findById(lecturerId);
        lecturerOptional.ifPresentOrElse(lecturer -> {
            if (lecturer.getCurrentlyTeachingSubjects().size() < MAX_TEACHING_SUBJECTS) {
                subjectRepository.findById(subjectId).ifPresentOrElse(subject -> {
                    subject.getCurrentlyTeachingLecturers().add(lecturer);
                    lecturer.getCurrentlyTeachingSubjects().add(subject);
                    subjectRepository.save(subject);
                    lecturerRepository.save(lecturer);
                }, () -> {
                    throw new NoSuchElementException("Subject not found");
                });
            } else throw new MaxNumberOfSubjectsExceededException("Lecturer has too many subjects");
        }, () -> {
            throw new NoSuchElementException("Lecturer not found");
        });
    }
}
