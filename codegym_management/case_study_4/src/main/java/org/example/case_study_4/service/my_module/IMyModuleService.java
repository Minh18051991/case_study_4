package org.example.case_study_4.service.my_module;

import org.example.case_study_4.dto.my_module_dto.ResponseModuleDto;
import org.example.case_study_4.model.MyModule;


import java.util.List;

public interface IMyModuleService {
    List<ResponseModuleDto> findModuleByStudentId(Integer studentId);

}

