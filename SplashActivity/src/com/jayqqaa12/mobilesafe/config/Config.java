package com.jayqqaa12.mobilesafe.config;

import com.jayqqaa12.mobilesafe.app.App;

public class Config
{

	public static final String CONFIG = "config";
	public static final String PHONE_PATTERN = "^1[3458]\\d{9}$";
	public static final String NUMBER_PATTERN = "^\\d{3,15}$";
	

	public static final String APP_DIR_PATH = "mobliesafe";
	
	public static final String LOCATION_DB_RULE = ".12";
	public static final String DB_DIR = "data/data/" + App.getContext().getPackageName() + "/databases";

	public static final String INIT = "init";
	public static final String UPDATE_DATE = "update_date";
	public static final String FIRST_RUNNING = "first_running";
	
	public static final String PROTECTED_PWD = "lost_protected_password";
	public static final String PROTECTED = "lost_protected_is_protected";
	public static final String PROTECTED_NUMBER = "lost_protected_number";
	public static final String PROTECTED_GUIDE_SETUP = "lost_protected_is_setup";
	public static final String PROTECTED_SIM_NUMBER = "lost_protected_sim_number";
	public static final String PROTECTED_LOCATION = "lost_protected_location";
	public static final String PROTECTED_UNINSTALL = "lost_protected_uninstall";



	public static final String PHONE_LOCATION_DISPLAY_ENABLE = "phone_location_query_display_enable";
	public static final String PHONE_LOCATION_DISPLAY_TIMEOUT = "phone_location_query_display_timeout";
	public static final String PHONE_LOCATION_DISPLAY_STYLE = "phone_location_query_display_style";
	public static final String PHONE_LOCATION_DISPLAY_POSITION = "phone_location_query_display_position";

	public static final String PHONE_LOCATION_DISPLAY_DRAG_X = "phone_location_display_drag_x";
	public static final String PHONE_LOCATION_DISPLAY_DRAG_Y = "phone_location_display_drag_y";
	public static final int CENTER_X = 60;
	public static final int CENTER_Y = 120;

	public static final String INTERCEPT_SERVICE = "intercept_service_is_open";
	public static final String INTERCEPT_NOTICE = "intercept_notice_is_open";
	public static final String INTERCEPT_MODEL = "intercept_model";
	public static final String INTERCEPT_NIGHT_DISTURB = "intercept_night_disturb_is_open";
	public static final String INTERCEPT_NOGJT_DISTURB_START_TIME = "intercept_night_disturb_start_time";
	public static final String INTERCEPT_NOGJT_DISTURB_END_TIME = "intercept_night_disturb_end_time";

	public static final String INTERCEPT_LOG_SAVE_TIME = "intercept_log_save_time";
	public static final String INTERCEPT_METHOD = "intercept_method";

	public static final String SMS_BACKUP_DATE = "sms_backup_date";
	public static final String SMS_BACKUP_FILE_PATH = "/sdcard/mobliesafe/sms_backup.xml";
	public static final String SPACE_APPLOCK_OPEN = "space_applcok_open";
	public static final String TRAFFIC_OPEN = "traffic_open";
	public static final String TRAFFIC_START_DATE = "traffic_start_date";


}
