package practice;

/**
 * Ex09. 클래스와 객체
 *
 * 진짜 DcMotor는 로봇에서만 쓸 수 있으니, 비슷하게 생긴 "가짜 모터" 클래스를 직접 만들어 봅니다.
 */
public class Ex09_Objects {

    // 설계도(클래스)
    static class FakeMotor {
        String name;        // 필드: 객체가 가진 값
        double power = 0;

        FakeMotor(String name) {    // 생성자: 객체를 만들 때 실행
            this.name = name;
        }

        void setPower(double p) {   // 메서드: 객체가 할 수 있는 일
            power = Math.max(-1.0, Math.min(1.0, p));
            System.out.println(name + " 파워 = " + power);
        }
    }

    public static void main(String[] args) {
        // 설계도로 실제 물건(객체) 4개 만들기
        FakeMotor frontLeftMotor  = new FakeMotor("LeftFront");
        FakeMotor backLeftMotor   = new FakeMotor("LeftBack");
        FakeMotor frontRightMotor = new FakeMotor("RightFront");
        FakeMotor backRightMotor  = new FakeMotor("RightBack");

        // 점(.)은 "~의" → frontLeftMotor의 파워를 0.5로
        frontLeftMotor.setPower(0.5);
        backRightMotor.setPower(2.0);    // 1.0으로 잘린다
        System.out.println("frontLeftMotor의 파워는 " + frontLeftMotor.power);

        // TODO 1: backLeftMotor 와 frontRightMotor 도 setPower 해 보세요.
        // TODO 2: FakeMotor 에 stop() 메서드를 추가해서 파워를 0으로 만들어 보세요.
        // TODO 3: FakeMotor frontLeftMotor; 만 쓰고 new 없이 setPower 하면? (NullPointerException)
    }
}
