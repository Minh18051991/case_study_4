package org.example.case_study_4.service.my_module;

import org.example.case_study_4.model.MyModule;
import org.example.case_study_4.dto.my_module_dto.ResponseModuleDto;
import org.example.case_study_4.repository.my_module.IMyModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyModuleService implements IMyModuleService {
    @Autowired
    private IMyModuleRepository moduleRepository;
    @Override
    public List<ResponseModuleDto> findModuleByStudentId(Integer studentId) {
        return moduleRepository.findModuleByStudentId(studentId);
    }

    public void save(MyModule module) {
        moduleRepository.save(module);
    }

    @Override
    public Optional<MyModule> findById(Integer moduleId) {
        return moduleRepository.findById(moduleId);
    }

    @Override
    public List<MyModule> findActiveByCourseId(Integer courseId) {
        return moduleRepository.findByCourseIdAndIsDeleteFalse(courseId);
    }

    public List<MyModule> findByCourseId(Integer courseId) {
        return moduleRepository.findByCourseId(courseId);
    }

    @Override
    public Optional<MyModule> findActiveById(Integer moduleId) {
        return moduleRepository.findByIdAndIsDeleteFalse(moduleId);
    }
    @Override
    public void softDelete(Integer id) {
       moduleRepository.findById(id).ifPresent(module -> {
           module.setIsDelete(true);
           moduleRepository.save(module);
       });
    }

}
