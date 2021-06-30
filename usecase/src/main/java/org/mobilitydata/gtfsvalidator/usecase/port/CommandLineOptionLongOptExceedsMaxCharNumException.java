package org.mobilitydata.gtfsvalidator.usecase.port;

/**
 * This exception is thrown when the developer creates a new {@code Option} with too long opt and longOpt names
 */
public class CommandLineOptionLongOptExceedsMaxCharNumException extends RuntimeException {
    public CommandLineOptionLongOptExceedsMaxCharNumException(final String message) {
        super(message);
    }
}
