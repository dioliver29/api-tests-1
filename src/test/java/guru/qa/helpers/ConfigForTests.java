package guru.qa.helpers;

import org.aeonbits.owner.ConfigFactory;

public class ConfigForTests {
    public static ConfigProps config = ConfigFactory.create(ConfigProps.class, System.getProperties());

}
