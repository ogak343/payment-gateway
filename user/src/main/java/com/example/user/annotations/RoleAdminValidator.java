package com.example.user.annotations;

import com.example.user.constants.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleAdminValidator implements ConstraintValidator<UserRole, Role> {

    @Override
    public void initialize(UserRole constraintAnnotation) {
    }

    @Override
    public boolean isValid(Role role, ConstraintValidatorContext constraintValidatorContext) {
        return role.equals(Role.USER);
    }
}
