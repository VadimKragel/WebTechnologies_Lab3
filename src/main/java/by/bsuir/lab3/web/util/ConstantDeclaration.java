package by.bsuir.lab3.web.util;

public final class ConstantDeclaration {

    public static final String SESSION_PARAM_CURRENT_USER = "current_user";

    public static final String REQUEST_PARAM_ACTION = "action";
    public static final String REQUEST_PARAM_CRUD_COMMAND = "crud_command";
    public static final String REQUEST_PARAM_ERROR_MESSAGE = "error_message";
    public static final String REQUEST_PARAM_SUCCESS_MESSAGE = "success_message";

    public static final String REQUEST_PARAM_USER_CHOSEN_FILM_SESSION_ID = "user_chosen_filmSession_id";
    public static final String REQUEST_PARAM_USER_CHOSEN_FILM_SESSION = "user_chosen_filmSession";

    public static final String REQUEST_PARAM_ROLE_LIST = "rolelist";
    public static final String REQUEST_PARAM_ROLE_ID = "role_id";
    public static final String REQUEST_PARAM_ROLE_NAME = "role_name";
    public static final String REQUEST_PARAM_FOUND_ROLE = "found_role";
    public static final String REQUEST_PARAM_COMMAND_NAME_CRUD_ROLE = "crud_role";

    public static final String REQUEST_PARAM_USER_LIST = "userlist";
    public static final String REQUEST_PARAM_USER_ID = "user_id";
    public static final String REQUEST_PARAM_USER_LOGIN = "user_login";
    public static final String REQUEST_PARAM_USER_EMAIL = "user_email";
    public static final String REQUEST_PARAM_USER_PASSWORD = "user_password";
    public static final String REQUEST_PARAM_USER_ROLE = "user_role";
    public static final String REQUEST_PARAM_FOUND_USER = "found_user";
    public static final String REQUEST_PARAM_COMMAND_NAME_CRUD_USER = "crud_user";

    public static final String REQUEST_PARAM_GENRE_LIST = "genrelist";
    public static final String REQUEST_PARAM_GENRE_ID = "genre_id";
    public static final String REQUEST_PARAM_GENRE_NAME = "genre_name";
    public static final String REQUEST_PARAM_FOUND_GENRE = "found_genre";
    public static final String REQUEST_PARAM_USER_CHOSEN_GENRE_ID = "user_chosen_genre_id";
    public static final String REQUEST_PARAM_USER_CHOSEN_GENRE = "user_chosen_genre";
    public static final String REQUEST_PARAM_COMMAND_NAME_CRUD_GENRE = "crud_genre";

    public static final String REQUEST_PARAM_FILM_LIST = "filmlist";
    public static final String REQUEST_PARAM_FILM_ID = "film_id";
    public static final String REQUEST_PARAM_FILM_NAME = "film_name";
    public static final String REQUEST_PARAM_FILM_DESCRIPTION = "film_description";
    public static final String REQUEST_PARAM_FILM_POSTER_URL = "film_poster_url";
    public static final String REQUEST_PARAM_FILM_GENRES = "film_genres";
    public static final String REQUEST_PARAM_FOUND_FILM = "found_film";
    public static final String REQUEST_PARAM_USER_CHOSEN_FILM = "user_chosen_film";
    public static final String REQUEST_PARAM_COMMAND_NAME_CRUD_FILM = "crud_film";

    public static final String REQUEST_PARAM_SEAT_LIST = "seatlist";

    public static final String REQUEST_PARAM_USER_CHOSEN_SEAT = "user_chosen_seat";
    public static final String REQUEST_PARAM_COMMAND_NAME_CRUD_SEAT = "crud_seat";

    public static final String REQUEST_PARAM_FILM_SESSION_LIST = "filmSessionlist";
    public static final String REQUEST_PARAM_COMMAND_NAME_CRUD_FILM_SESSION = "crud_film_session";

    public static final String REQUEST_PARAM_FILM_TICKET_LIST = "ticketList";
    public static final String REQUEST_PARAM_COMMAND_NAME_CRUD_TICKET = "crud_ticket";

    public static final String REQUEST_PARAM_FILM_TICKETS_ORDER_LIST = "ticketsOrderList";
    public static final String REQUEST_PARAM_COMMAND_NAME_CRUD_TICKETS_ORDER = "crud_tickets_order";

    public static final String REQUEST_PARAM_CURRENT_USER_CURRENT_ORDER = "current_user_current_order";
    public static final String REQUEST_PARAM_CURRENT_USER_CURRENT_ORDER_ID = "current_user_current_order_id";

    public static final String CRUD_OPERATION_NAME_CREATE = "create";
    public static final String CRUD_OPERATION_NAME_READ = "read";
    public static final String CRUD_OPERATION_NAME_UPDATE = "update";
    public static final String CRUD_OPERATION_NAME_DELETE = "delete";

    public static final String PAGE_USER_MAIN = "/WEB-INF/pages/user/main.jsp";
    public static final String PAGE_USER_FILM_PAGE = "/WEB-INF/pages/user/film_page.jsp";
    public static final String PAGE_ADMIN_CRUD_ROLE = "/WEB-INF/pages/admin/crud_role.jsp";
    public static final String PAGE_ADMIN_CRUD_USER = "/WEB-INF/pages/admin/crud_user.jsp";
    public static final String PAGE_ADMIN_CRUD_GENRE = "/WEB-INF/pages/admin/crud_genre.jsp";
    public static final String PAGE_ADMIN_CRUD_FILM = "/WEB-INF/pages/admin/crud_film.jsp";
    public static final String PAGE_ERROR = "/WEB-INF/pages/error.jsp";

    public static final String EMAIL_INPUT_VALIDATION_REGEX = "(\\w{5,})@(\\w+\\.)([a-z]{2,4})";

    public static final String SIGN_UP_CHECK_LOGIN_RESULT_STRING = "<span style=\"color:%s;\">%s</span>";
    public static final String SIGN_UP_CHECK_LOGIN_FAIL_COLOR = "#FF0000";
    public static final String SIGN_UP_CHECK_LOGIN_SUCCESS_COLOR = "#008B00";

    public static final String SIGN_UP_CHECK_EMAIL_RESULT_STRING = "<span style=\"color:#FF0000;\">%s</span>";

    public static final String SIGN_UP_CHECK_PASSWORD_RESULT_STRING = "<span style=\"color:%s;\">%s</span>";
    public static final String SIGN_UP_CHECK_PASSWORD_WEAK_STRENGTH_COLOR = "#FF0000";
    public static final String SIGN_UP_CHECK_PASSWORD_MIDDLE_STRENGTH_COLOR = "#FFFF00";
    public static final String SIGN_UP_CHECK_PASSWORD_STRONG_STRENGTH_COLOR = "#008B00";
    public static final int SIGN_UP_CHECK_PASSWORD_WEAK_STRENGTH = 1;
    public static final int SIGN_UP_CHECK_PASSWORD_MIDDLE_STRENGTH = 5;
    public static final int SIGN_UP_CHECK_PASSWORD_STRONG_STRENGTH = 7;

    public static final int DEFAULT_ROLE_ID = 2;
    public static final int MAX_COUNT_SEAT_ROW_IN_HALL = 20;
    public static final int MAX_COUNT_SEAT_NUMBER_IN_ROW = 25;
}
