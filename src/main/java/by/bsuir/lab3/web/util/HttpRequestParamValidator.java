package by.bsuir.lab3.web.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.regex.Pattern;

import static by.bsuir.lab3.web.util.ConstantDeclaration.EMAIL_INPUT_VALIDATION_REGEX;

public final class HttpRequestParamValidator {
	private HttpRequestParamValidator() {

	}

	public static void validateRequestParamIdNotNull(int id) {
		if (id == 0) {
			throw new ValidateNullParamException("Empty param recieved");
		}
	}

	public static void validateRequestParamNotNull(String... str) {
		for (String s : str) {
			if (s == null) {
				throw new ValidateNullParamException("Empty param recieved");
			}
		}
	}

	public static void validateRequestParamNotNull(String[]... str) {
		for (String[] s : str) {
			if (s == null) {
				throw new ValidateNullParamException("Empty param recieved");
			}
		}
	}

	public static void validateRequestParamNotNull(Object... obj) {
		for (Object o : obj) {
			if (o == null) {
				throw new ValidateNullParamException("Empty param recieved");
			}
		}
	}

	public static boolean isPost(HttpServletRequest req) {
		return req.getMethod().equalsIgnoreCase("POST");
	}

	public static boolean checkEmailInput(String email) {
		return Pattern.matches(EMAIL_INPUT_VALIDATION_REGEX, email);
	}
}
