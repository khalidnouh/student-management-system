package com.df.student.management.model.mapper;

import com.df.student.management.model.Course;
import com.df.student.management.model.User;
import com.df.student.management.model.dto.CourseDTO;
import com.df.student.management.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDTO toDTO(Course course);

    Course toEntity(CourseDTO courseDTO);
}