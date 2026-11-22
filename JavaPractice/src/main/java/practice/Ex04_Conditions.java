package practice;

/**
 * Ex04. 조건문 if / else
 *
 * 로봇이 없으니 게임패드 입력을 변수로 흉내 냅니다.
 * 대회 코드(PollenLauncher)의 로더 조작과 같은 규칙입니다.
 *   D-pad 아래 : 로더 역방향 -0.8 (걸림 해소)
 *   LT        : 0.1보다 많이 누르면, 누른 만큼 로더 정방향 (최대 0.8)
 */
public class Ex04_Conditions {

    public static void main(String[] args) {
        boolean dpadDown = false;     // D-pad 아래를 안 누름
        double leftTrigger = 0.7;     // LT를 70% 누름
        double loaderPower;

        if (dpadDown) {
            loaderPower = -0.8;           // 역방향이 우선
        } else if (leftTrigger > 0.1) {
            loaderPower = Math.min(leftTrigger, 0.8);    // 누른 만큼, 최대 0.8
        } else {
            loaderPower = 0.0;            // 아무것도 안 누르면 정지
        }
        System.out.println("로더 파워 = " + loaderPower);

        // 파워 제한
        double power = 1.3;
        boolean precisionMode = false;
        if (power > 1.0 && !precisionMode) {
            power = 1.0;
        }
        System.out.println("파워 = " + power);

        // TODO 1: dpadDown 을 true 로 바꾸면 로더 파워는? (LT를 눌러도 역방향이 우선)
        // TODO 2: leftTrigger 를 0.05 로 바꾸면? 1.0 으로 바꾸면?
        // TODO 3: precisionMode 를 true 로 바꾸면 파워는 어떻게 될까요?
    }
}
