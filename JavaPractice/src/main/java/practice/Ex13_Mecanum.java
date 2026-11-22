package practice;

/**
 * Ex13. 메카넘 계산 미리 해 보기 (Part 4)
 *
 * 로봇 없이 스틱 값을 넣어 보고, 바퀴 4개에 어떤 파워가 들어가는지 확인합니다.
 * ExampleMecanumTeleOp.java 의 계산 부분과 똑같습니다.
 */
public class Ex13_Mecanum {

    static void mecanum(String label, double stickX, double stickY, double turnX) {
        double axial   = -stickY;  // 앞뒤 (위로 밀면 y가 음수)
        double lateral =  stickX;  // 좌우
        double yaw     =  turnX;   // 회전

        double lf = axial + lateral + yaw;
        double rf = axial - lateral - yaw;
        double lb = axial - lateral + yaw;
        double rb = axial + lateral - yaw;

        double max = Math.max(1.0, Math.max(Math.max(Math.abs(lf), Math.abs(rf)),
                                            Math.max(Math.abs(lb), Math.abs(rb))));

        System.out.println(String.format("%-14s LF %5.2f | RF %5.2f | LB %5.2f | RB %5.2f",
                label, lf / max, rf / max, lb / max, rb / max));
    }

    public static void main(String[] args) {
        //           설명               왼쪽X  왼쪽Y  오른쪽X
        mecanum("앞으로",            0.0,  -1.0,  0.0);
        mecanum("오른쪽 평행이동",    1.0,   0.0,  0.0);
        mecanum("제자리 회전",        0.0,   0.0,  1.0);
        mecanum("오른쪽 앞 대각선",   1.0,  -1.0,  0.0);

        // TODO 1: "뒤로", "왼쪽 평행이동" 줄을 추가해 보세요.
        // TODO 2: max 로 나누지 않으면 대각선에서 값이 어떻게 되는지 확인해 보세요.
    }
}
