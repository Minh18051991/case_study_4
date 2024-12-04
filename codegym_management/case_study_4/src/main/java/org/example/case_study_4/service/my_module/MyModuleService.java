package org.example.case_study_4.service.my_module;

import org.example.case_study_4.dto.my_module_dto.ResponseModuleDto;
import org.example.case_study_4.model.MyModule;
import org.example.case_study_4.repository.my_module.IMyModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyModuleService implements IMyModuleService {
    @Autowired
    private IMyModuleRepository moduleRepository;
    @Override
    public List<ResponseModuleDto> findModuleByStudentId(Integer studentId) {
        return moduleRepository.findModuleByStudentId(studentId);
    }


}
