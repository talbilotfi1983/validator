package org.mobilitydata.gtfsvalidator.web;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mobilitydata.gtfsvalidator.config.DefaultConfig;
import org.mobilitydata.gtfsvalidator.usecase.port.ExecParamRepository;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class ServiceManager {
    private final Logger logger = LogManager.getLogger();
    private DefaultConfig config;

    /**
     * Initiates the {@code DefaultConfig} needed to proceed to GTFS archive validation. {@link DefaultConfig} is
     * instantiate from a set of execution parameters provided by the user via interaction with the web ui.
     *
     * @param execParamAsString the set of execution parameters as a Json string
     * @return null (for requirement of the testing framework)
     */
    public String initializeConfig(final String execParamAsString) throws Exception {
        try {
            this.config = new DefaultConfig.Builder()
                    .execParamAsString(execParamAsString)
                    .logger(logger)
                    .build();

        } catch (Exception e) {
            if (execParamAsString == null) {
                throw (new IOException("Configuration file not provided"));
            } else {
                throw e;
            }
        }
        return null;
    }

    /**
     * Checks if DefaultConfig has been initialized i.e execution parameter configuration file has been provided by user
     *
     * @return true if the user provided configuration file, else returns false.
     */
    public boolean isConfigInitialized() {
        return config != null;
    }


    /**
     * Returns the validation report
     *
     * @return the validation report as a JSON string
     */
    public String displayReport() throws IOException {

        final File folder = new File(config.getExecParamValue(ExecParamRepository.OUTPUT_KEY));
        if (folder.listFiles() == null || Objects.requireNonNull(folder.listFiles()).length == 0) {
            return "No report was generated at:" + config.getExecParamValue(ExecParamRepository.OUTPUT_KEY);
        } else {
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                if (file.getName().endsWith(".json")) {

                    return Files.readString(
                            Path.of(config.getExecParamValue(ExecParamRepository.OUTPUT_KEY) +
                                    File.separator + file.getName()));
                }
            }
        }

        return null;
    }
}
