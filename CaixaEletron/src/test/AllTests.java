package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClienteDAOTest.class, ClienteTest.class, ClienteTOTest.class, ConnectionFactoryTest.class,
		ContaDAOTest.class, ContaTest.class, ContaTOTest.class, ExtratoDAOTest.class, ExtratoTest.class,
		ExtratoTOTest.class, SaqueTest.class })
public class AllTests {

}
