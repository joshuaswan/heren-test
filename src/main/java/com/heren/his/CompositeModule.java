package com.heren.his;

import com.google.inject.Injector;
import com.heren.i0.config.Configuration;
import com.heren.i0.config.util.LogLevel;
import com.heren.i0.container.grizzly.EmbeddedGrizzly;
import com.heren.i0.container.grizzly.WebSocket;
import com.heren.i0.core.Application;
import com.heren.i0.core.ApplicationModule;
import com.heren.i0.core.GuiceModule;
import com.heren.i0.core.Servlet3;
import com.heren.i0.jersey.RestApi;
import com.heren.i0.jpa.JpaConfiguration;
import com.heren.i0.jpa.JpaPersist;
import com.heren.i0.jpa.config.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import static com.heren.i0.config.Configuration.config;
import static com.heren.i0.config.Configuration.read;
import static com.heren.i0.jpa.DatabaseConfiguration.database;

@Application("heren-test")
@GuiceModule
@Servlet3
@RestApi
@JpaPersist(unit = "domain")
@EmbeddedGrizzly(assets = {@EmbeddedGrizzly.Asset(uri = "/javascripts", resource = "javascripts"),
        @EmbeddedGrizzly.Asset(uri = "/test", resource = "test"),
        @EmbeddedGrizzly.Asset(uri = "/styles", resource = "styles"),
        @EmbeddedGrizzly.Asset(uri = "/introduce",resource = "introduce")}
)
@WebSocket(packages = {"com.heren.his.Endpoint"})
public class CompositeModule extends ApplicationModule<JpaConfiguration> {
    public static final String DEFAULT_CONFIG_FOLDER = "./";
    public static final String DEFAULT_YML_FILENAME = "config.yml";
    public static Injector injector = null;
    private static Logger logger = LoggerFactory.getLogger(CompositeModule.class);

    @Override
    protected JpaConfiguration createDefaultConfiguration(Configuration.ConfigurationBuilder config) {
        JpaConfiguration jpaConfiguration = null;
        try {
            URL configFile = this.getClass().getClassLoader().getResource(DEFAULT_CONFIG_FOLDER + DEFAULT_YML_FILENAME);
            if (configFile != null) {
                logger.info("Reading configuration from project config");
                jpaConfiguration = readConfigurationFromFile(configFile.getFile());
            } else {
                logger.warn("config file in project url is null");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (jpaConfiguration == null) {
            jpaConfiguration = defaultConfiguration();
        }
        return jpaConfiguration;
    }

    private JpaConfiguration readConfigurationFromFile(String path) {
        JpaConfiguration jpaConfiguration = null;
        try {
            File file = new File(path);
            if (logger.isInfoEnabled()) {
                logger.info("Reading configuration from file {}", file.getCanonicalPath());
            }
            if (file.exists()) {
                jpaConfiguration = read(new FileInputStream(file), JpaConfiguration.class);
            } else {
                logger.warn("config file {} not exist", file.getCanonicalPath());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return jpaConfiguration;
    }

    private static JpaConfiguration defaultConfiguration() {
        if (logger.isInfoEnabled())
            logger.info("No configuration was found, will use default configuration.");

        return new JpaConfiguration(config().http().port(9999).end()
                .logging().level(LogLevel.DEBUG).console().end().end().build(),
                database().user("herendh").password("herendh").driver("oracle.jdbc.driver.OracleDriver")
                        .url("jdbc:oracle:thin:@localhost:1521:orcl").with(Hibernate.showSql, Hibernate.dialect("Oracle10g")).build());
    }
}
