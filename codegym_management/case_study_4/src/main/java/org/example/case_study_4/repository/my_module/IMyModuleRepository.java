package org.example.case_study_4.repository.my_module;

import org.example.case_study_4.model.MyModule;
import org.example.case_study_4.dto.my_module_dto.ResponseModuleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IMyModuleRepository extends JpaRepository<MyModule, Integer> {
    @Query(value = "select m.id as moduleId, m.name as moduleName\n" +
            "from student s \n" +
            "join class c on s.class_id = c.id\n" +
            "join course cou on c.course_id = cou.id\n" +
            "join module m on cou.id = m.course_id\n" +
            "where s.id = :studentId and c.is_delete = false and m.is_delete = false", nativeQuery = true)
    List<ResponseModuleDto> findModuleByStudentId(@Param("studentId") Integer studentId);

    List<MyModule> findByCourseId(Integer courseId);
    List<MyModule>findByCourseIdAndIsDeleteFalse(Integer courseId);
    Optional<MyModule> findByIdAndIsDeleteFalse(Integer id);

}
