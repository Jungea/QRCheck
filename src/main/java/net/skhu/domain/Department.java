package net.skhu.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data                                                   //@JsonIgnore하는 이유
@ToString(exclude={"students","courses","professors"})  //데이터를 문자열 화 할 때 오류가 날 수 있다.
@EqualsAndHashCode(exclude={"students","courses","professors"})
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

//속성명과 디비 필드명이 다를 경우
    @Column(name = "departmentName")
    String name;

    @JsonIgnore
    @OneToMany(mappedBy="department", fetch = FetchType.LAZY)
    List<Student> students;

    @JsonIgnore
    @OneToMany(mappedBy="department", fetch = FetchType.LAZY)
    List<Course> courses;

    @JsonIgnore
    @OneToMany(mappedBy="department", fetch = FetchType.LAZY)
    List<Professor> professors;
}

