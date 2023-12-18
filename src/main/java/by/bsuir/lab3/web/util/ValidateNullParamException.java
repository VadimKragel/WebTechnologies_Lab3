package by.bsuir.lab3.web.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serial;

public class ValidateNullParamException extends RuntimeException {

	private static final Logger logger = LogManager.getLogger();

	public ValidateNullParamException() {
	}

	public ValidateNullParamException(String message, Throwable cause) {
		logger.error(message + " " + cause);
	}

	public ValidateNullParamException(String message) {
		logger.error(message);
	}

	public ValidateNullParamException(Throwable cause) {
		logger.error(cause);
	}

}
