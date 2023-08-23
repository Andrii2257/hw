package org.example;


import org.apache.log4j.Logger;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private Scanner scanner;

    private static final Logger LOGGER = Logger.getLogger(App.class);
    public App() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {

        MBeanServer mbs = madeMBeanServer();
        App app = new App();
        String input = app.readLine();
        String result;
        while (!input.equalsIgnoreCase("exit")) {
            LOGGER.info("Before: " + input);
            result = app.makeUpperCaseEvenChars(input);
            LOGGER.info("After: " + result);
            System.out.println("result = " + result);
            input = app.readLine();
        }
    }

    private static MBeanServer madeMBeanServer() throws NotCompliantMBeanException, MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        StandardMBean mBean = new StandardMBean(new MyMBeanImpl(), MyMBean.class);

        ObjectName mBeanName = new ObjectName("org.example:type=MyMBean");

        mbs.registerMBean(mBean, mBeanName);

        return mbs;
    }

    private String readLine() {
        return scanner.nextLine();
    }

    private String makeUpperCaseEvenChars(String value) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            if (i % 2 == 0) result.append(String.valueOf(value.charAt(i)).toLowerCase());
            else result.append(String.valueOf(value.charAt(i)).toUpperCase());
        }
        return result.toString();
    }
}
