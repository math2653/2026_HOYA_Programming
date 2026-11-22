package practice;

/**
 * Ex11. 글자 다루기와 삼항 연산자
 */
public class Ex11_Strings {

    public static void main(String[] args) {
        double power = 0.123456;

        // 글자 + 숫자 = 글자
        String msg = "Power: " + power;
        System.out.println(msg);

        // %.2f : 소수점 둘째 자리까지 (telemetry.addData 에서도 똑같이 씀)
        System.out.println(String.format("Power: %.2f", power));

        // 조건 ? 참일 때 : 거짓일 때
        // (대회 코드처럼) LB를 누르고 있으면 로봇 중심, 아니면 필드 중심 주행
        boolean leftBumper = true;
        String mode = leftBumper ? "ROBOT" : "FIELD";
        // D-pad 아래를 누르면 로더 역방향 -0.8, 아니면 LT를 누른 만큼
        boolean dpadDown = false;
        double leftTrigger = 0.6;
        double loaderPower = dpadDown ? -0.8 : leftTrigger;
        System.out.println("mode = " + mode + ", loaderPower = " + loaderPower);

        // 주의: 글자 비교는 == 가 아니라 .equals()
        String name = "LeftFront";
        System.out.println(name.equals("LeftFront"));   // true
        System.out.println(name.equals("leftfront"));   // false (대소문자!)

        // TODO 1: leftBumper 를 false 로, dpadDown 을 true 로 바꿔 보세요.
        // TODO 2: "1" + 2 + 3 과 1 + 2 + "3" 을 출력해 보세요. 왜 결과가 다를까요?
    }
}
