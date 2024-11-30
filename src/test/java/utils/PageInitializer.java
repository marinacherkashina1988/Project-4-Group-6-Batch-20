package utils;

import pages.*;

public class PageInitializer {
    public static LoginPage loginPage;
    public static AddEmployeePage addEmployeePage;
    public static EmployeeSearchPage employeeSearchPage;
    public static DashboardPage dashboardPage;
    public static EmployeeProfilePage employeeProfilePage;
    public static JobDetailsPage jobDetailsPage;
    public static MembershipPage membershipPage;
    public static EditEmployeePage editEmployeePage;
    public static MyInfoPage myInfoPage;
    public static AddDependentPage addDependentPage;

    public static void initializePageObjects() {

        loginPage = new LoginPage();
        addEmployeePage = new AddEmployeePage();
        employeeSearchPage = new EmployeeSearchPage();
        dashboardPage = new DashboardPage();
        addDependentPage = new AddDependentPage();
        employeeProfilePage = new EmployeeProfilePage();
        jobDetailsPage = new JobDetailsPage();
        membershipPage = new MembershipPage();
        editEmployeePage = new EditEmployeePage();
        myInfoPage = new MyInfoPage();
    }
}