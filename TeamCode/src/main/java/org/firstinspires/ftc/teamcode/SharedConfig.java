package org.firstinspires.ftc.teamcode;

/**
 * 팀 공용 설정값.
 *
 * 여러 OpMode에서 같이 쓰는 값을 한곳에 모아 둡니다.
 * 강의 Part 5의 충돌(conflict) 실습에서 두 사람이 같은 줄을 고쳐 보는 파일이기도 합니다.
 */
public class SharedConfig {

    /** 드라이브 최대 속도 (0.0 ~ 1.0) */
    public static double SPEED = 0.5;

    /** 느린 모드 배율 (오른쪽 범퍼를 누르고 있을 때) */
    public static double SLOW_MODE_SCALE = 0.4;

    /** 집게 서보 위치. 실제 로봇에서 telemetry로 확인하며 조정하세요. */
    public static double CLAW_OPEN = 1.0;
    public static double CLAW_CLOSED = 0.0;
}
