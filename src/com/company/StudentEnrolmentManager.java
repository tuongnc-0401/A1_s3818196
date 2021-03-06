package com.company;

import java.util.List;
import java.util.Set;

public interface StudentEnrolmentManager {
    void add();
    void update();
    void delete();
    StudentEnrolment getOne();
    List<StudentEnrolment> getAll();
}
