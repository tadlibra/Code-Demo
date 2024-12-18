import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Tạo Scanner để nhận đầu vào từ người dùng
        Scanner scanner = new Scanner(System.in);

        // Nhập số lượng sinh viên từ người dùng
        System.out.print("Enter the number of students (e.g., 100, 1000, 10000): ");
        int numStudents = scanner.nextInt(); // Lấy số lượng sinh viên từ người dùng

        // Tạo mảng sinh viên ngẫu nhiên
        Student[] students = generateRandomStudents(numStudents);

        // Tạo bản sao mảng để không làm thay đổi mảng gốc
        Student[] bubbleSortedStudents = students.clone();
        Student[] mergeSortedStudents = students.clone();

        // Sắp xếp bằng Bubble Sort
        long startTime = System.nanoTime();
        bubbleSort(bubbleSortedStudents);
        long bubbleEndTime = System.nanoTime();

        // Sắp xếp bằng Merge Sort
        long mergeStartTime = System.nanoTime();
        mergeSort(mergeSortedStudents, 0, mergeSortedStudents.length - 1);
        long mergeEndTime = System.nanoTime();

        // Hiển thị thời gian chạy của các thuật toán
        System.out.println("\nBubble Sort Time: " + (bubbleEndTime - startTime) + " nanoseconds");
        System.out.println("Merge Sort Time: " + (mergeEndTime - mergeStartTime) + " nanoseconds");

        scanner.close(); // Đóng scanner sau khi sử dụng
    }

    // Tạo mảng sinh viên ngẫu nhiên
    private static Student[] generateRandomStudents(int numStudents) {
        Random random = new Random();
        Student[] students = new Student[numStudents];
        for (int i = 0; i < numStudents; i++) {
            int id = i + 1; // ID bắt đầu từ 1
            String name = "Student" + id;
            int grade = random.nextInt(101); // Điểm ngẫu nhiên từ 0 đến 100
            students[i] = new Student(id, name, grade);
        }
        return students;
    }

    // Thuật toán Bubble Sort
    private static void bubbleSort(Student[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j].getGrade() > array[j + 1].getGrade()) {
                    // Hoán đổi
                    Student temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Thuật toán Merge Sort
    private static void mergeSort(Student[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Chia mảng thành hai nửa
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            // Hợp nhất hai nửa đã sắp xếp
            merge(array, left, mid, right);
        }
    }

    // Hợp nhất hai mảng con đã sắp xếp
    private static void merge(Student[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Tạo mảng tạm thời
        Student[] leftArray = new Student[n1];
        Student[] rightArray = new Student[n2];

        // Sao chép dữ liệu vào các mảng tạm
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        // Kết hợp mảng tạm vào mảng gốc
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].getGrade() <= rightArray[j].getGrade()) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Sao chép phần còn lại của mảng trái, nếu có
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Sao chép phần còn lại của mảng phải, nếu có
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}