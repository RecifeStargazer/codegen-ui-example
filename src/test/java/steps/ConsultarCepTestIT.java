package steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features")
public class ConsultarCepTestIT {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
}
