package practice;

/**
 * Ex02. 변수와 자료형
 */
public class Ex02_Variables {

    public static void main(String[] args) {
        int ticks = 384;            // 정수
        double power = 0.5;         // 소수
        boolean isOpen = false;     // 참 / 거짓
        String name = "LeftFront";  // 글자

        System.out.println("ticks  = " + ticks);
        System.out.println("power  = " + power);
        System.out.println("isOpen = " + isOpen);
        System.out.println("name   = " + name);

        // 한 번 만든 변수는 자료형 없이 이름만 써서 값을 바꾼다
        power = power * 2;
        System.out.println("power를 두 배로 → " + power);

        // TODO 1: power 를 0.3 으로 바꾸고 다시 실행해 보세요.
        // TODO 2: isOpen 을 true 로 바꿔 보세요.
        // TODO 3: int ticks = 384.5; 로 바꾸면 어떤 에러가 날까요? (int에는 소수를 못 담는다)
    }
}
