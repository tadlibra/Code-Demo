import java.util.Stack;

public class StudentManager {
    private Stack<Student> students = new Stack<>();

    // Thêm sinh viên
    public void addStudent(int id, String name, int grade) {
        students.push(new Student(id, name, grade));
    }

    // Lấy danh sách sinh viên
    public Stack<Student> getStudents() {
        return students;
    }

    // Xóa sinh viên theo ID
    public boolean deleteStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                return true;
            }
        }
        return false; // Không tìm thấy sinh viên với ID này
    }

    // Cập nhật sinh viên theo ID
    public boolean updateStudent(int id, String name, int grade) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(name);
                student.setGrade(grade);
                return true;
            }
        }
        return false; // Không tìm thấy sinh viên với ID này
    }
}