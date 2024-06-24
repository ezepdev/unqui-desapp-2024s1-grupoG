package ar.edu.unq.desapp.grupog.backendapicryptoexchange.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.AbstractController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class NamingConventionArchTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("ar.edu.unq.desapp.grupog.backendapicryptoexchange");

    @Test
    void controllers_should_be_suffixed() {
        classes()
                .that().resideInAPackage("..controller..")
                .or().areAnnotatedWith(RestController.class)
                .or().areAssignableTo(AbstractController.class)
                .should().haveSimpleNameEndingWith("Controller")
                .check(classes);
    }
}
