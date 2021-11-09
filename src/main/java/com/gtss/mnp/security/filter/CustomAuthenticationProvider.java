package com.gtss.mnp.security.filter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String customToken = (String) authentication.getPrincipal();
        if (customToken == null) {
            throw new AccessDeniedException("Please provide organization header");
        }
        return getValidationToken(customToken);
    }

    private Authentication getValidationToken(String customToken) {
        if (!customToken.equals("vodafone") && !customToken.equals("orange") && !customToken.equals("etisalat")) {
            throw new AccessDeniedException("Invalid authentication token");
        } else {
            return new PreAuthenticatedAuthenticationToken(customToken, customToken);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.equals(authentication);
    }
}
