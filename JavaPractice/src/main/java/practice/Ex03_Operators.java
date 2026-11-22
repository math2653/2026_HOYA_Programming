package practice;

/**
 * Ex03. 연산자와 Math
 */
public class Ex03_Operators {

    public static void main(String[] args) {
        // 정수 나눗셈 함정
        int a = 7 / 2;
        double b = 7 / 2.0;
        int r = 7 % 2;
        System.out.println("7 / 2   = " + a);   // 3
        System.out.println("7 / 2.0 = " + b);   // 3.5
        System.out.println("7 % 2   = " + r);   // 1

        // 줄여 쓰기
        double power = 0.5;
        power += 0.1;
        int count = 0;
        count++;
        System.out.println("power = " + power + ", count = " + count);

        // Math 도구 상자
        System.out.println("Math.abs(-0.8)     = " + Math.abs(-0.8));
        System.out.println("Math.max(0.3, 0.9) = " + Math.max(0.3, 0.9));
        System.out.println("Math.min(1.6, 1.0) = " + Math.min(1.6, 1.0));
        System.out.println("Math.round(3.6)    = " + Math.round(3.6));

        // 형변환
        System.out.println("(int) 3.9      = " + (int) 3.9);       // 3 (버림)
        System.out.println("(double) 7 / 2 = " + (double) 7 / 2);  // 3.5

        // TODO 1: 1 / 2 를 출력해 보세요. 결과가 왜 0일까요?
        // TODO 2: 모터가 1000틱 돌았을 때 몇 바퀴인지 출력해 보세요. (한 바퀴 = 384.5틱)
        //         힌트: 1000 / 384.5
    }
}
