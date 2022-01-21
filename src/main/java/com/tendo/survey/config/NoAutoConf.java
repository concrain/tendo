package com.tendo.survey.config;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.shell.SpringShellAutoConfiguration;
import org.springframework.shell.jcommander.JCommanderParameterResolverAutoConfiguration;
import org.springframework.shell.jline.JLineShellAutoConfiguration;
import org.springframework.shell.standard.FileValueProvider;
import org.springframework.shell.standard.StandardAPIAutoConfiguration;
import org.springframework.shell.standard.commands.StandardCommandsAutoConfiguration;

@Configuration
@Import({
        // Core runtime
        SpringShellAutoConfiguration.class,
        JLineShellAutoConfiguration.class,
        // Various Resolvers
        JCommanderParameterResolverAutoConfiguration.class,
        StandardAPIAutoConfiguration.class,
        // Built-In Commands
        StandardCommandsAutoConfiguration.class,
        FileValueProvider.class,
})
public class NoAutoConf {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NoAutoConf.class, args);
    }

}
