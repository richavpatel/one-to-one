package com.spring.onetoone.repository;

import com.spring.onetoone.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//new Object[] { id }: We are passing the id as a parameter to the query
//new BeanPropertyRowMapper<Student>(Student.class):
// We are using a BeanPropertyRowMapper to map the results from ResultSet to the Student bean.
@Repository
public class StudentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class StudentRowMapper implements RowMapper<Student>{

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {

            Student student = new Student();
            student.setId(resultSet.getLong("id"));
            student.setName(resultSet.getString("name"));
            student.setPassportNumber(resultSet.getString("passport_number"));
            return student;
        }
    }

    public Student findById(long id){
        return jdbcTemplate.queryForObject("select * from student where id = ?", new Object[] {id},
                new BeanPropertyRowMapper<Student>(Student.class));
    }

    public List<Student> getAll(){
        return jdbcTemplate.query("select * from student", new StudentRowMapper());

    }

    public int addStudent(Student student){
        int student1 = jdbcTemplate.update("insert into student(id, name, passport_number)" +" values (?,?,?)",
                new Object[]{
                      student.getId(), student.getName(), student.getPassportNumber()});
        return  student1;
    }

    public Student updateStudent(Long id,Student student){
        jdbcTemplate.update("update student" + " set name = ?, passport_number = ?" + "where id = ?" , new Object[]{
                student.getName(), student.getPassportNumber(), student.getId()
        });
        return findById(student.getId());
    }

    public int deleteById(Long id){
        return  jdbcTemplate.update("delete from student where id = ?", new Object[]{id});
    }


}
