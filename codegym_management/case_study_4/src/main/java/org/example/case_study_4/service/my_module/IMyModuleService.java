package org.example.case_study_4.service.my_module;

import org.example.case_study_4.dto.my_module_dto.ResponseModuleDto;
import org.example.case_study_4.model.MyModule;


import java.util.List;
import java.util.Optional;

public interface IMyModuleService {
    List<ResponseModuleDto> findModuleByStudentId(Integer studentId);

    void save(MyModule module);

    Optional<MyModule> findById(Integer moduleId);

    List<MyModule> findActiveByCourseId(Integer courseId);

    Optional<MyModule> findActiveById(Integer moduleId);

    void softDelete(Integer id);
}

