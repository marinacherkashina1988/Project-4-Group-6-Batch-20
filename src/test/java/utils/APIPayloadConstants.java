package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    public static String createUser(String name,
                                    String email,
                                    String password) {
        if (email.equalsIgnoreCase("dynamic")) {
            email = "user" + System.currentTimeMillis() + "@Anything.com";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("email", email);
        jsonObject.put("password", password.isEmpty() ? null : password);
        return jsonObject.toString();

    }

    public static String generateTokenPayload(String email, String password) {
        JSONObject obj = new JSONObject();
        obj.put("email", email);
        obj.put("password", password);
        return obj.toString();
    }

    public static String createEmployeeJsonPayloadDynamic(String emp_firstname,
                                                          String emp_lastname,
                                                          String emp_middle_name,
                                                          String emp_gender,
                                                          String emp_birthday,
                                                          String emp_status,
                                                          String emp_job_title) {
        JSONObject object = new JSONObject();
        object.put("emp_firstname", emp_firstname);
        object.put("emp_lastname", emp_lastname);
        object.put("emp_middle_name", emp_middle_name);
        object.put("emp_gender", emp_gender);
        object.put("emp_birthday", emp_birthday);
        object.put("emp_status", emp_status);
        object.put("emp_job_title", emp_job_title);

        return object.toString();

    }
}



