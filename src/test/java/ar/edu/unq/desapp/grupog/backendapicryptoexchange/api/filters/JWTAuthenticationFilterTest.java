package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.filters;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.config.SecurityAppContext;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.JWTService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@SpringBootTest
class JWTAuthenticationFilterTest {

        @InjectMocks
        JWTAuthenticationFilter jwtAuthenticationTokenFilter;
        @Mock
        UserService userService;
        @Mock
        SecurityAppContext securityAppContext;
        @Mock
        JWTService jwtService;


        HttpServletRequest request;
        HttpServletResponse response;
        FilterChain chain;

        @BeforeEach
        public void before() {
            request = mock(HttpServletRequest.class);
            response = mock(HttpServletResponse.class);
            chain = mock(FilterChain.class);
        }

        @ParameterizedTest
        @ValueSource(strings = { "", "123", "dasdasd" })
        void doFilterInternalTest(String token) throws ServletException, IOException {
            when(request.getHeader("Authorization")).thenReturn(token);
            jwtAuthenticationTokenFilter.doFilterInternal(request, response, chain);
            verify(chain).doFilter(request, response);
        }
        @Test
        void doFilterInternalAuthenticationNotNull() throws ServletException, IOException {
            UserDetailsService userDetailsService = mock(UserDetailsService.class);
            String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg";
            when(request.getHeader(AUTHORIZATION)).thenReturn(token);

            SecurityContext context = mock(SecurityContext.class);
            when(securityAppContext.getContext()).thenReturn(context);
            Authentication authentication = mock(Authentication.class);
            when(context.getAuthentication()).thenReturn(authentication);
            when(jwtService.extractUserName(anyString())).thenReturn("username");
            when(userService.userDetailsService()).thenReturn(userDetailsService);
            when(userDetailsService.loadUserByUsername("username")).thenReturn(User.builder().build());
            jwtAuthenticationTokenFilter.doFilterInternal(request, response, chain);
            verify(chain).doFilter(request, response);
            verify(context, never()).setAuthentication(any(Authentication.class));
        }

        @Test
        void doFilterInternalAuthenticationNullUser() throws ServletException, IOException {
            String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg";
            when(request.getHeader(AUTHORIZATION)).thenReturn(token);
            when(request.getRemoteAddr()).thenReturn("localhost");

            SecurityContext context = mock(SecurityContext.class);
            when(securityAppContext.getContext()).thenReturn(context);
            when(context.getAuthentication()).thenReturn(null);

            jwtAuthenticationTokenFilter.doFilterInternal(request, response, chain);
            verify(context, never()).setAuthentication(any(Authentication.class));
            verify(chain).doFilter(request, response);
        }

        @Test
        void doFilterInternalAuthenticationSuccess() throws ServletException, IOException {
            String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg";
            UserDetailsService userDetailsService = mock(UserDetailsService.class);

            when(request.getHeader(AUTHORIZATION)).thenReturn(token);
            when(request.getRemoteAddr()).thenReturn("localhost");

            SecurityContext context = mock(SecurityContext.class);
            when(securityAppContext.getContext()).thenReturn(context);
            when(context.getAuthentication()).thenReturn(null);
            when(jwtService.isTokenValid(anyString(), any(UserDetails.class))).thenReturn(true);
            when(jwtService.extractUserName(any())).thenReturn("username");
            when(userService.userDetailsService()).thenReturn(userDetailsService);
            when(userDetailsService.loadUserByUsername("username")).thenReturn(User.builder().build());

            jwtAuthenticationTokenFilter.doFilterInternal(request, response, chain);

            verify(chain).doFilter(request, response);
        }
}
