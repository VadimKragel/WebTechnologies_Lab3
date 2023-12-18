package by.bsuir.lab3.web.util;

public class HttpRequestParamFormatter {

	private HttpRequestParamFormatter() {
	}

	public static int getInt(String param) {
		return Integer.parseInt(param);
	}

	public static String fixGoogleDriveUrl(String url) {
		if (url.contains("open?id="))
			url = url.replace("open?id=", "uc?id=");
		if (url.contains("file/d/"))
			url = url.replace("file/d/", "uc?id=");
		if (url.contains("/edit?usp=sharing"))
			url = url.replace("/edit?usp=sharing", "");
		return url;
	}
}
