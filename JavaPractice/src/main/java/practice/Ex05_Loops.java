package practice;

/**
 * Ex05. 반복문 while / for
 *
 * TeleOp의 while (opModeIsActive()) 루프를 흉내 냅니다.
 */
public class Ex05_Loops {

    public static void main(String[] args) {
        // while: 조건이 true인 동안 반복
        int loopCount = 1;
        while (loopCount <= 4) {            // opModeIsActive() 대신 4번만
            double stickY = loopCount * -0.25;  // 스틱을 점점 위로 미는 상황
            double power = -stickY;             // 위로 밀면 y가 음수 → 부호 뒤집기
            System.out.println("루프 " + loopCount + ": stickY = " + stickY + " → power = " + power);
            loopCount++;
        }
        System.out.println("STOP! 루프를 빠져나왔습니다.");

        // for: 정해진 횟수 반복
        for (int i = 0; i < 4; i++) {
            System.out.println("for " + i + "번째");
        }

        // TODO 1: while 조건을 loopCount <= 8 로 바꿔 보세요. 파워가 1.0을 넘으면 어떻게 해야 할까요?
        // TODO 2: for 문으로 1부터 10까지의 합을 구해 출력해 보세요. (정답 55)
        // 주의: loopCount++ 를 지우면 영원히 끝나지 않습니다! (빨간 ■ 버튼으로 멈추기)
    }
}
