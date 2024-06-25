package ar.edu.unq.desapp.grupog.backendapicryptoexchange;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ServletInitializerTest {

        @Mock
        private SpringApplicationBuilder springApplicationBuilder;

        @Test
        void testIt() {
            ServletInitializer servletInitializer = new ServletInitializer();
            when(springApplicationBuilder.sources(BackendApiCryptoexchangeApplication.class)).thenReturn(springApplicationBuilder);

            SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);

            verify(springApplicationBuilder).sources(BackendApiCryptoexchangeApplication.class);
            assertEquals(springApplicationBuilder,result);
        }

}
