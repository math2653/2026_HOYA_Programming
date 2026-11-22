package practice;

/**
 * Ex08. 메서드
 */
public class Ex08_Methods {

    // void: 돌려주는 값 없음
    static void drive(double power) {
        System.out.println("왼쪽 모터 = " + power + ", 오른쪽 모터 = " + power);
    }

    // double: double 값을 돌려줌
    static double half(double x) {
        return x / 2;
    }

    // 파워를 -1.0 ~ 1.0 사이로 자르기
    static double clip(double power) {
        return Math.max(-1.0, Math.min(1.0, power));
    }

    public static void main(String[] args) {
        drive(0.5);
        drive(half(0.8));          // 안쪽부터 계산 → drive(0.4)
        System.out.println("clip(1.7)  = " + clip(1.7));
        System.out.println("clip(-3.0) = " + clip(-3.0));

        // TODO 1: double 두 개를 받아서 더 큰 값을 돌려주는 메서드 bigger(a, b)를 만들어 보세요.
        // TODO 2: 파워를 받아서 "앞으로", "뒤로", "정지" 중 하나를 출력하는 메서드를 만들어 보세요.
    }
}
