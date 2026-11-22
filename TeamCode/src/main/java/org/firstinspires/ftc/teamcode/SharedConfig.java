package org.firstinspires.ftc.teamcode;

/**
 * 팀 공용 설정값 (강의용).
 *
 * 여러 OpMode에서 같이 쓰는 값을 한곳에 모아 둡니다.
 * 이름과 값은 대회 저장소(HOYA_2026)의 HoyaFieldCentricDrive / PollenLauncher 와 맞춰 두었습니다.
 * 대회 코드에서 값을 튜닝하면 여기도 같이 바꿔 주세요.
 */
public class SharedConfig {

    // ---------- 드라이브 ----------
    /** 드라이브 최대 속도 (0.0 ~ 1.0) */
    public static double SPEED = 1.0;

    /** 스틱 데드존: 이보다 작은 값은 0으로 */
    public static double DEADBAND = 0.05;

    /** RT 정밀 감속 모드: RT를 끝까지 누르면 50% 감속 */
    public static double PRECISION_MAX_REDUCTION = 0.5;

    // ---------- POLLEN 런처 (디바이더 없음: 로더 + 슈터) ----------
    /** 로더 수동 파워 한계 (LT / D-pad 아래) */
    public static double LOADER_MAX_POWER = 0.8;

    /** LT를 이보다 많이 눌러야 로더가 돈다 */
    public static double LOADER_TRIGGER_THRESHOLD = 0.1;

    /** 슈터 롤러 1회전당 엔코더 틱: 1620rpm 모터 103.8틱 ÷ 외부 기어 2배 증속 = 51.9 */
    public static final double SHOOTER_TICKS_PER_REV = 51.9;

    /** 슈터 롤러 이론상 최대 RPM (1620 x 2) */
    public static final double SHOOTER_MAX_RPM = 3240.0;

    /** A / B / X / Y 슈터 프리셋 (롤러 최대 RPM 대비 비율). A = 정지. 대회 코드 값과 같음 */
    public static final double[] SHOOTER_PRESETS = {0.00, 0.90, 0.90, 0.85};

    /** 목표 RPM ± 이 범위 안에 SHOOTER_READY_STABLE_SEC 동안 머물면 READY */
    public static double SHOOTER_READY_TOLERANCE_RPM = 120.0;
    public static double SHOOTER_READY_STABLE_SEC = 0.15;

    /** RB 자동 발사: FEED_POWER로 FEED_PULSE_SEC 동안 급송 → SHOT_SETTLE_SEC 쉬고 반복 */
    public static double FEED_POWER = 0.80;
    public static double FEED_PULSE_SEC = 0.22;
    public static double SHOT_SETTLE_SEC = 0.35;
}
