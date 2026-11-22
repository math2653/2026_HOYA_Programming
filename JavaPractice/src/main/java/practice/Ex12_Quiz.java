package practice;

/**
 * Ex12. 확인 퀴즈
 *
 * 실행하기 전에 각 값을 먼저 종이에 예상해 적어 보세요.
 * 그다음 ▶ 를 눌러 정답을 확인합니다.
 */
public class Ex12_Quiz {

    public static void main(String[] args) {
        // Q1
        int x = 5 / 2;
        System.out.println("Q1. x = " + x);

        // Q2
        double p = 0.8 * 2;
        p = Math.min(p, 1.0);
        System.out.println("Q2. p = " + p);

        // Q3
        int count = 0;
        for (int i = 0; i < 3; i++) count++;
        System.out.println("Q3. count = " + count);

        // Q4
        boolean b = !(3 > 2) || false;
        System.out.println("Q4. b = " + b);
    }
}
