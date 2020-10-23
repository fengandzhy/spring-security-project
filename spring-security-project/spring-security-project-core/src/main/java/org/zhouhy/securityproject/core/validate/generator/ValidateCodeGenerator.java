package org.zhouhy.securityproject.core.validate.generator;

import org.springframework.web.context.request.ServletWebRequest;
import org.zhouhy.securityproject.core.validate.code.ValidateCode;

public interface ValidateCodeGenerator {

    public ValidateCode createCode(ServletWebRequest request);
}
