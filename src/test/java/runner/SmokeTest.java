package runner;

import com.codeborne.selenide.Configuration;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"html:target/cucumber-report/smoketest", "json:target/cucumber.json"},
        features = "src/test/java/features",
        glue = "steps")

public class SmokeTest
{

    @BeforeClass
    static public void setupTimeout()
    {
        Configuration.timeout = 10000;
        System.setProperty("webdriver.chrome.driver", "./drv/chromedriver.exe");
        Configuration.browser = StringUtils.isEmpty(System.getProperty("browser"))?"chrome":System.getProperty("browser");

    }

//    @AfterClass
//    static public void gen() throws Exception {
//        CucumberResultsOverview results = new CucumberResultsOverview();
//        results.setOutputDirectory("target");
//        results.setOutputName("cucumber-results");
//        results.setSourceFile("./target/cucumber.json");
//        results.executeFeaturesOverviewReport();
//    }
}
