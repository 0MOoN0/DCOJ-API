package com.dcoj.service;


import com.dcoj.entity.TestCaseEntity;

import java.util.List;

/**
 * @author Smith
 **/
public interface TestCasesService {
    int save(int pid, String stdin, String stdout, int strength);

    int countProblemTestCases(int pid);

    List<TestCaseEntity> listAll(int pid);

    void updateTestCaseByTidPid(int tid, int pid, String stdin, String stdout, int strength);

    void deleteTestCase(int tid, int pid);

    void deleteProblemTestCases(int pid);
}
