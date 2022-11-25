import model.CompetitiveExamCourses;

import java.time.Duration;
import java.util.ArrayList;

public class Lambda_assessment1 {

    public static void main(String args[]){
        ArrayList<CompetitiveExamCourses> courses = new ArrayList<>();

        courses.add(new CompetitiveExamCourses(1001, "JEE_MAINS", "SriDhar Raj", Duration.ofDays(400), 250000));
        courses.add(new CompetitiveExamCourses(1002, "JEE_ADVANCED", "Aman Dhattarwal", Duration.ofDays(200), 300000));
        courses.add(new CompetitiveExamCourses(1003, "NEET", "Ankush Mishra", Duration.ofDays(650), 650000));
        courses.add(new CompetitiveExamCourses(1004, "CAT", "Rakesh Sharma", Duration.ofDays(100), 350000));
        courses.add(new CompetitiveExamCourses(1005, "SSC CGL", "Aditya Nath", Duration.ofDays(150), 150000));
        courses.add(new CompetitiveExamCourses(1006, "NDA", "Raj Gopal", Duration.ofDays(250), 25000));
        courses.add(new CompetitiveExamCourses(1007, "CDS", "Patrick Burrows", Duration.ofDays(350), 35000));
        courses.add(new CompetitiveExamCourses(1008, "UPSC_CIVIL_SERVICES", "Nidhi Saini", Duration.ofDays(500), 610000));
        courses.add(new CompetitiveExamCourses(1009, "CTET", "Rajkumar", Duration.ofDays(220), 20000));
        courses.add(new CompetitiveExamCourses(1010, "SSC_JE", "Arif Khan", Duration.ofDays(300), 40000));
        courses.add(new CompetitiveExamCourses(1011, "GATE", "Sachin Satish", Duration.ofDays(200), 120000));
        courses.add(new CompetitiveExamCourses(1012, "UGC_NET", "HappyMan Jacob", Duration.ofDays(305), 200000));
        courses.add(new CompetitiveExamCourses(1013, "CSIR_NET", "Sonali Chauhan", Duration.ofDays(210), 25000));
        courses.add(new CompetitiveExamCourses(1014, "IBPS_RRB", "Smriti Mahdavan", Duration.ofDays(345), 45000));
        courses.add(new CompetitiveExamCourses(1015, "SBI PO", "Ashok Kumar", Duration.ofDays(200), 50000));


        System.out.println("__________________ASCENDING ORDER____________________");
        sortInAscendingOrder(courses);
        System.out.println("Sorting courses as per lowest price ");
        for (CompetitiveExamCourses course : courses){
            System.out.println(course.getCourse_name()+" Course_Fees " +course.getTotal_fees());
        }

        System.out.println("/_____________________DESCENDING ORDER___________________");
        sortInDescendingOrder(courses);
        System.out.println("Sorting courses as per highest price ");
        for (CompetitiveExamCourses course : courses){
            System.out.println(course.getCourse_name()+" Course_Fees " +course.getTotal_fees());
        }

        System.out.println("___________________COURSE DURATION FROM SHORTEST TO LONGEST__________________");
        sortInAscendingOrder_Duration(courses);
        for (CompetitiveExamCourses course : courses) {
            System.out.println(course.getCourse_name() +" Course Duration: "+course.getCourse_duration().toDays()+ " days");
        }

    }

    private static ArrayList<CompetitiveExamCourses> sortInAscendingOrder(ArrayList<CompetitiveExamCourses> coursesList){
        coursesList.sort((CompetitiveExamCourses c1, CompetitiveExamCourses c2)-> c1.getTotal_fees().compareTo(c2.getTotal_fees()));
        return coursesList;

    }

    private static ArrayList<CompetitiveExamCourses> sortInDescendingOrder(ArrayList<CompetitiveExamCourses> coursesList){
        coursesList.sort((CompetitiveExamCourses c1, CompetitiveExamCourses c2)-> c2.getTotal_fees().compareTo(c1.getTotal_fees()));
        return coursesList;

    }

    private static ArrayList<CompetitiveExamCourses> sortInAscendingOrder_Duration(ArrayList<CompetitiveExamCourses> coursesList){
        coursesList.sort((CompetitiveExamCourses c1, CompetitiveExamCourses c2)-> c1.getCourse_duration().compareTo(c2.getCourse_duration()));
        return coursesList;

    }


}
