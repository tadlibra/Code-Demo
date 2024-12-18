import java.util.Stack;

public class SortAlgorithms {

    // Thuật toán Bubble Sort
    public static void bubbleSort(Stack<Student> stack) {
        int n = stack.size();

        // Chuyển Stack sang ArrayList để xử lý dễ dàng hơn
        Student[] array = new Student[n];
        for (int i = 0; i < n; i++) {
            array[i] = stack.pop();
        }

        // Bubble Sort: Sắp xếp Array theo điểm (grade)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].getGrade() > array[j + 1].getGrade()) {
                    // Hoán đổi nếu không đúng thứ tự
                    Student temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        // Chuyển lại Array đã sắp xếp vào Stack
        for (int i = n - 1; i >= 0; i--) {
            stack.push(array[i]);
        }
    }

    // Thuật toán Quick Sort
    public static Stack<Student> quickSort(Stack<Student> stack) {
        int n = stack.size();

        // Chuyển Stack sang ArrayList để xử lý dễ dàng hơn
        Student[] array = new Student[n];
        for (int i = 0; i < n; i++) {
            array[i] = stack.pop();
        }

        // Sắp xếp Quick Sort
        quickSortRecursive(array, 0, n - 1);

        // Chuyển lại Array đã sắp xếp vào Stack
        Stack<Student> sortedStack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            sortedStack.push(array[i]);
        }

        return sortedStack;
    }

    private static void quickSortRecursive(Student[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            // Đệ quy Quick Sort cho phần trái và phải
            quickSortRecursive(array, low, pi - 1);
            quickSortRecursive(array, pi + 1, high);
        }
    }

    private static int partition(Student[] array, int low, int high) {
        // Chọn phần tử cuối làm pivot
        Student pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j].getGrade() <= pivot.getGrade()) {
                i++;

                // Hoán đổi nếu phần tử nhỏ hơn hoặc bằng pivot
                Student temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Hoán đổi pivot với phần tử tiếp theo của i
        Student temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    // In kết quả Stack
    public static void printStack(Stack<Student> stack) {
        Stack<Student> tempStack = new Stack<>();

        // Di chuyển dữ liệu từ stack gốc sang stack tạm thời
        while (!stack.isEmpty()) {
            Student student = stack.pop();
            System.out.println(student);
            tempStack.push(student);
        }

        // Di chuyển lại dữ liệu từ stack tạm về stack gốc
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }
}