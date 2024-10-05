package com.df.student.management.model.mapper;

import com.df.student.management.model.Registration;
import com.df.student.management.model.User;
import com.df.student.management.model.dto.RegistrationDTO;
import com.df.student.management.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);

    RegistrationDTO toDTO(Registration registration);

    Registration toEntity(RegistrationDTO registrationDTO);
}