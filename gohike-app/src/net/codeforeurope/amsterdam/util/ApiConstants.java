package net.codeforeurope.amsterdam.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ApiConstants {

	public static final String SUCCESS = "success";
	public static final String ERROR_MESSAGE = "error_message";
	public static final String ERROR = "error";
	public static final String BASE_URL = "http://gohike.herokuapp.com/api/%s";

	public static final String ACTION_CONTENT_LOADED = "data_loaded";
	public static final String GAME_DATA = "game_data";
	public static final String CURRENT_PROFILE = "current_profile";
	public static final String CURRENT_ROUTE = "current_route";
	public static final String CURRENT_TARGET = "current_target";
	public static final String CURRENT_VERSION = "version";
	public static final String ACTION_REMOTE_CONTENT_LOADED = "remote_data_loaded";
	public static final String ACTION_PING_COMPLETE = "ping_complete";
	public static final String PING_RESULT = "ping_result";
	public static final String CONTENT_VERSION = "content_version";
	public static final String ACTION_CONTENT_LOAD_ERROR = "data_load_error";
	public static final Locale NETHERLANDS_LOCALE = new Locale("nl");
	public static final String OUTSTANDING_CHECKINS = "outstanding_checkins";
	public static final String UPLOADED_CHECKINS = "uploaded_checkins";
	public static final String CHECKINS_UPLOADED = "checkins_uploaded";
	public static final String CHECKIN = "checkin";
	public static final String ACTION_CHECKIN_SAVED = "checkin_saved";
	public static final String ACTION_CHECKINS_UPLOADED = "checkins_uploaded";
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss Z");
	public static final String ACTION_LOAD_CHECKINS = "load_checkins";
	public static final String ACTION_CHECKINS_LOADED = "checkins_loaded";
	public static final String LOCAL_CHECKINS = "local_checkins";

}
