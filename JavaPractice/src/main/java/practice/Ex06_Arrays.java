package practice;

/**
 * Ex06. 배열과 for
 */
public class Ex06_Arrays {

    public static void main(String[] args) {
        String[] motors = {"LeftFront", "LeftBack", "RightFront", "RightBack"};
        double[] powers = {0.5, -0.5, -0.5, 0.5};

        System.out.println("모터 개수 = " + motors.length);
        System.out.println("motors[0] = " + motors[0]);   // 0부터 센다!

        // 일반 for
        for (int i = 0; i < motors.length; i++) {
            System.out.println(motors[i] + " → " + powers[i]);
        }

        // 향상된 for: 하나씩 꺼내기
        for (String m : motors) {
            System.out.println(m + " 정지");
        }

        // TODO 1: motors[2] 는 어떤 모터일까요? 출력해서 확인해 보세요.
        // TODO 2: motors[4] 를 출력하면 어떤 에러가 날까요?
        // TODO 3: powers 배열의 모든 값을 두 배로 바꾼 뒤 출력해 보세요.
    }
}
