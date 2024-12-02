package org.example.case_study_4.repository.my_module;

import org.example.case_study_4.model.MyModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMyModuleRepository extends JpaRepository<MyModule, Integer> {

}
