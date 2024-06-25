package ar.edu.unq.desapp.grupog.backendapicryptoexchange;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication.LoginRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication.RegisterRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void  testUserWithInvalidNameLaunchException(){
        String expectedError = "El campo nombre debe contener entre 3 y 30 caracteres";
        RegisterRequest request = new RegisterRequest("as", "avalidsurname", "valid@asd.com", "a valid address", "1234567891234578912345", "12345678", "Pepe1234!");
        Set<ConstraintViolation<RegisterRequest>> errores = validator.validate(request);
        ConstraintViolation<RegisterRequest> errorText = errores.iterator().next();
        assertEquals(1, errores.size());
        assertEquals(errorText.getMessage(), expectedError );
    }
    @Test
    void  testUserWithInvalidSurnameLaunchException(){
        String expectedError = "El campo nombre debe contener entre 3 y 30 caracteres";
        RegisterRequest request = new RegisterRequest("validuser", "as", "valid@asd.com", "a valid address", "1234567891234578912345", "12345678", "Pepe1234!");
        Set<ConstraintViolation<RegisterRequest>> errores = validator.validate(request);
        ConstraintViolation<RegisterRequest> errorText = errores.iterator().next();
        assertEquals(1, errores.size());
        assertEquals(errorText.getMessage(), expectedError );
    }

    @Test
    void  testUserWithInvalidEmailLaunchException(){
        String expectedError = "El campo email debe tener un formato de email";
        RegisterRequest request = new RegisterRequest("validuser", "asvalidusurname", "invalid", "a valid address", "1234567891234578912345", "12345678", "Pepe1234!");
        Set<ConstraintViolation<RegisterRequest>> errores = validator.validate(request);
        ConstraintViolation<RegisterRequest> errorText = errores.iterator().next();
        assertEquals(1, errores.size());
        assertEquals(errorText.getMessage(), expectedError );
    }

    @Test
    void  testUserWithInvalidPasswordLaunchException(){
        String expectedError = "El campo contraseña debe tener al menos 6 caracteres y debe contener letras, números y caracteres especiales";
        RegisterRequest request = new RegisterRequest("validuser", "asvalidusurname", "invalid@gmail.com", "a valid address", "1234567891234578912345", "12345678", "pepe1234!");
        Set<ConstraintViolation<RegisterRequest>> errores = validator.validate(request);
        ConstraintViolation<RegisterRequest> errorText = errores.iterator().next();
        assertEquals(1, errores.size());
        assertEquals(errorText.getMessage(), expectedError );
    }

    @Test
    void  testUserWithInvalidEmailLaunchExceptionAtLogin(){
        String expectedError = "El campo email debe tener un formato de email";
        LoginRequest request = new LoginRequest("asvalidusurname", "Pepe1234!");
        Set<ConstraintViolation<LoginRequest>> errores = validator.validate(request);
        ConstraintViolation<LoginRequest> errorText = errores.iterator().next();
        assertEquals(1, errores.size());
        assertEquals(errorText.getMessage(), expectedError );
    }

}
