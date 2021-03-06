/*
 * Copyright (c) 2012 - 2015, Internet Corporation for Assigned Names and
 * Numbers (ICANN) and China Internet Network Information Center (CNNIC)
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * * Neither the name of the ICANN, CNNIC nor the names of its contributors may
 *  be used to endorse or promote products derived from this software without
 *  specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL ICANN OR CNNIC BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */
package org.restfulwhois.rdap.core.domain.validator;

import org.apache.commons.lang.StringUtils;
import org.restfulwhois.rdap.core.common.support.QueryParam;
import org.restfulwhois.rdap.core.common.util.DomainUtil;
import org.restfulwhois.rdap.core.common.util.StringUtil;
import org.restfulwhois.rdap.core.common.validation.HttpValidationError;
import org.restfulwhois.rdap.core.common.validation.ValidationResult;
import org.restfulwhois.rdap.core.common.validation.Validator;
import org.restfulwhois.rdap.core.domain.queryparam.DomainSearchByNsLdhNameParam;

/**
 * domain search by nameserver's LDH name validator.
 * 
 * @author jiashuo
 * 
 */
public class DomainSearchByNsLdhNameValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(DomainSearchByNsLdhNameParam.class);
    }

    @Override
    public void validate(QueryParam queryParam,
            ValidationResult validationResult) {
        String decodeDomain = queryParam.getQ();
        if (StringUtils.isBlank(decodeDomain)) {
            validationResult.addError(HttpValidationError.build400Error());
        }
        if (!StringUtil.checkIsValidSearchPattern(decodeDomain)) {
            validationResult.addError(HttpValidationError.build422Error());
        }
        if (!DomainUtil.validateSearchLdnName(decodeDomain)) {
            validationResult.addError(HttpValidationError.build400Error());
        }
        if (!DomainUtil.validateSearchStringIsValidIdna(decodeDomain)) {
            validationResult.addError(HttpValidationError.build400Error());
        }
    }

}
