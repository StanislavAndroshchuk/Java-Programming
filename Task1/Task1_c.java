package Task1;
import java.util.Arrays;

abstract class EducationalInstitution implements Comparable<EducationalInstitution> {
    private String name;
    private String address;
    private int foundingYear;

    public EducationalInstitution(String name, String address, int foundingYear) {
        this.name = name;
        this.address = address;
        this.foundingYear = foundingYear;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    public abstract String getType();

    @Override
    public int compareTo(EducationalInstitution other) {
        return Integer.compare(this.foundingYear, other.foundingYear);
    }

    @Override
    public String toString() {
        return getType() + ": " + name + " (" + foundingYear + ")";
    }
}

class School extends EducationalInstitution {
    private int schoolNumber;
    private int numberOfStudents;

    public School(String name, String address, int foundingYear, int schoolNumber, int numberOfStudents) {
        super(name, address, foundingYear);
        this.schoolNumber = schoolNumber;
        this.numberOfStudents = numberOfStudents;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    @Override
    public String getType() {
        return "СШ";
    }

    @Override
    public String toString() {
        return super.toString() + ", Номер: " + schoolNumber + ", Учні: " + numberOfStudents;
    }
}

class University extends EducationalInstitution {
    private String accreditationLevel;
    private int numberOfFaculties;

    public University(String name, String address, int foundingYear, String accreditationLevel, int numberOfFaculties) {
        super(name, address, foundingYear);
        this.accreditationLevel = accreditationLevel;
        this.numberOfFaculties = numberOfFaculties;
    }

    public String getAccreditationLevel() {
        return accreditationLevel;
    }

    @Override
    public String getType() {
        return "ВУЗ";
    }

    @Override
    public String toString() {
        return super.toString() + ", Рівень акредитації: " + accreditationLevel + ", Факультети: " + numberOfFaculties;
    }
}
public class Task1_c {

    public static void main(String[] args){
        EducationalInstitution[] institutions = {
                new School("Школа 1", "Адреса 1", 1950, 1001, 500),
                new School("Школа 2", "Адреса 3", 1905, 1448, 400),
                new University("Університет 1", "Адреса 2", 1905, "Акредитований", 13),
                new University("Університет 2", "Адреса 4", 1975, "Неакредитований", 6),
        };
        Arrays.sort(institutions); // 3.1


        School minStudentSchool = null; // 3.2
        int minStudents = Integer.MAX_VALUE;
        for (EducationalInstitution institution : institutions) {
            if (institution instanceof School) {
                School school = (School) institution;
                if (school.getNumberOfStudents() < minStudents) {
                    minStudents = school.getNumberOfStudents();
                    minStudentSchool = school;
                }
            }
        }

        String accreditationLevelToFind = "Акредитований"; // 3.3
        for (EducationalInstitution institution : institutions) {
            if (institution instanceof University) {
                University university = (University) institution;
                if (university.getAccreditationLevel().equals(accreditationLevelToFind)) {
                    System.out.println(university);
                }
            }
        }

        //Print
        System.out.println("Масив відсортований за роком заснування:");
        for (EducationalInstitution institution : institutions) {
            System.out.println(institution);
        }

        System.out.println("Школа з мінімальною кількістю учнів:");
        if (minStudentSchool != null) {
            System.out.println(minStudentSchool);
        } else {
            System.out.println("Шкіл не знайдено.");
        }
    }

}
