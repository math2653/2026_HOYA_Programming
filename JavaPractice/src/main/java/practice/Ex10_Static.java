package practice;

/**
 * Ex10. public, static, final
 */
public class Ex10_Static {

    // 팀 저장소의 SharedConfig.java 와 같은 모양
    static class Config {
        public static double SPEED = 0.5;                  // 바꿀 수 있음
        public static final double TICKS_PER_REV = 384.5;  // 못 바꿈 (상수)
    }

    public static void main(String[] args) {
        // 객체(new) 없이 클래스 이름으로 바로 사용
        System.out.println("SPEED = " + Config.SPEED);
        System.out.println("한 바퀴 틱 수 = " + Config.TICKS_PER_REV);

        Config.SPEED = 0.8;
        System.out.println("SPEED 변경 → " + Config.SPEED);

        // Config.TICKS_PER_REV = 400;   // ← 주석을 풀면 에러! final은 못 바꾼다

        // TODO 1: 위 주석을 풀어서 에러 메시지를 확인해 보세요.
        // TODO 2: 2바퀴를 돌려면 몇 틱인지 Config.TICKS_PER_REV 로 계산해 출력해 보세요.
    }
}
