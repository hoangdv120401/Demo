package com.example.demo.entity;

import com.example.demo.entity.Enum.Role;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;


@Entity
@Table(name = "`Account`")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;
    @Column(name = "`password`", nullable = false, length = 800)
    private String password;
    @Column(name = "first_name", length = 50, nullable = false)
    private String lastName;
    @Column(name = "last_name", length = 50, nullable = false)
    private String firstName;
    @Formula("concat(first_name,' ', last_name)")
    private String fullName;
    @Column(name = "`role`")
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;




}
