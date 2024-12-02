package org.example.case_study_4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "account_role", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"account_id", "role_id"})
})
public class AccountRole {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_id")
    private Integer accountId;
    @Column(name = "role_id")
    private Integer roleId;
}
