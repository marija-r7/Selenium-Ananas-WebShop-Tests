package tswprojekatmarija;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    RegisterUserTest.class,
    LoginUserTest.class,
    UserDataTest.class,
    AddToCartTest.class,
    PerformanceTest.class,
    AdditionalTest.class
})

public class RunAllTests{
	
}

