package org.example;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MyMBeanImpl implements MyMBean {
    private static final Logger LOGGER = Logger.getLogger(MyMBeanImpl.class);
    @Override
    public void turnOnLog() {
        LogManager.getRootLogger().setLevel(Level.INFO);
        LOGGER.info("Change log level to INFO");
    }

    @Override
    public void turnOffLog() {
        LOGGER.info("Change log level to OFF");
        LogManager.getRootLogger().setLevel(Level.OFF);
    }
}
