package practice;

/**
 * Ex07. 변수가 살아 있는 범위 { } 와 엣지 검출
 *
 * 매 루프마다 A 버튼이 눌렸는지를 배열로 흉내 냅니다.
 * true가 여러 번 이어지는 건 버튼을 "누르고 있는" 상황입니다.
 * A를 누를 때마다 슈터가 켜졌다 꺼졌다 해야 합니다.
 */
public class Ex07_Scope {

    public static void main(String[] args) {
        boolean[] buttonA = {false, true, true, true, false, false, true, true, false};

        boolean shooterOn = false;  // 루프 밖: 계속 기억
        boolean prevA = false;      // 루프 밖: 계속 기억

        for (int loop = 0; loop < buttonA.length; loop++) {
            boolean a = buttonA[loop];      // 루프 안: 매번 새로 만들어짐

            if (a && !prevA) {              // 누른 "순간"에만
                shooterOn = !shooterOn;
            }
            prevA = a;

            System.out.println("루프 " + loop + ": A=" + a + " → 슈터 " + (shooterOn ? "ON" : "OFF"));
        }

        // System.out.println(a);   // ← 주석을 풀면 에러! a는 for { } 밖에서 못 씀

        // TODO 1: if (a && !prevA) 를 if (a) 로 바꿔서 실행해 보세요. 슈터가 어떻게 되나요?
        // TODO 2: boolean prevA = false; 를 for 안으로 옮기면 어떻게 될까요?
    }
}
