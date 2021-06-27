package com.example.seckilling.validator;

import com.example.seckilling.util.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Xiao Yijia
 * @create 2020/9/16 22:45
 */

public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isMobile(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }

}
