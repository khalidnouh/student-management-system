package com.df.student.management.model.mapper;

import com.df.student.management.model.Registration;
import com.df.student.management.model.dto.RegistrationDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T07:16:26+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class RegistrationMapperImpl implements RegistrationMapper {

    @Override
    public RegistrationDTO toDTO(Registration registration) {
        if ( registration == null ) {
            return null;
        }

        RegistrationDTO registrationDTO = new RegistrationDTO();

        registrationDTO.setId( registration.getId() );
        registrationDTO.setCanceled( registration.getCanceled() );

        return registrationDTO;
    }

    @Override
    public Registration toEntity(RegistrationDTO registrationDTO) {
        if ( registrationDTO == null ) {
            return null;
        }

        Registration registration = new Registration();

        registration.setId( registrationDTO.getId() );
        registration.setCanceled( registrationDTO.getCanceled() );

        return registration;
    }
}
