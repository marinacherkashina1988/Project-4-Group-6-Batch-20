package utils;

public class SharedData {
    private static SharedData instance;
    private String emp_id;

    private SharedData() {}

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public String getId() {
        return emp_id;
    }

    public void setId(String emp_id) {
        this.emp_id = emp_id;
    }
}
