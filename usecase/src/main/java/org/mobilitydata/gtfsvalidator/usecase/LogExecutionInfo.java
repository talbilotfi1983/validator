package org.mobilitydata.gtfsvalidator.usecase;

import org.apache.logging.log4j.Logger;
import org.mobilitydata.gtfsvalidator.usecase.port.ExecParamRepository;

/**
 * Use case to log information about the validation process
 */
public class LogExecutionInfo {
    private final Logger logger;
    private final ExecParamRepository execParamRepo;

    /**
     * @param logger        logger used to log information
     * @param execParamRepo Repository holding execution parameters
     */
    public LogExecutionInfo(final Logger logger,
                            final ExecParamRepository execParamRepo) {
        this.logger = logger;
        this.execParamRepo = execParamRepo;
    }

    /**
     * Use case execution method: logs relevant information concerning the validation process.
     */
    public void execute() {
        if (execParamRepo.hasExecParamValue(execParamRepo.URL_KEY) & !execParamRepo
                .hasExecParamValue(execParamRepo.INPUT_KEY)) {
            logger.info("--url provided but no location to place zip (--zip option). Using default: " +
                    execParamRepo.getExecParamValue(execParamRepo.INPUT_KEY));
        }

        if (!execParamRepo.hasExecParamValue(execParamRepo.EXTRACT_KEY)) {
            logger.info("--input not provided. Will extract zip content in: " + execParamRepo
                    .getExecParamValue(ExecParamRepository.EXTRACT_KEY));
        }

        if (!execParamRepo.hasExecParamValue(execParamRepo.OUTPUT_KEY)) {
            logger.info("--output not provided. Will place execution results in: " + execParamRepo
                    .getExecParamValue(execParamRepo.OUTPUT_KEY));
        }
    }
}
