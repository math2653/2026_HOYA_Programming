# 2026 HOYA Programming

HOYA FTC 팀의 로봇 코드 저장소입니다.
공식 [FtcRobotController](https://github.com/FIRST-Tech-Challenge/FtcRobotController) SDK를 기반으로 하고, 우리 팀 코드는 모두 `TeamCode` 폴더에 작성합니다.

## 작업 방식

```
            GitHub: 2026_HOYA_Programming  (팀 공용 저장소 하나)
           ↗ Push   ↙ Pull        ↗ Push   ↙ Pull
   내 노트북 (클론)               팀원 노트북 (클론)
   MinsuDrive.java                JieunDrive.java
```

- 팀원 모두가 **같은 저장소를 각자 노트북에 클론**해서 작업합니다.
- 각자 **자기 이름으로 된 파일**을 만들고, 다 되면 Commit → Push로 팀 저장소에 올립니다.
- 다른 사람이 올린 코드는 Pull로 받아옵니다.

---

# Part A. 처음 한 번만

## A1. 준비물

| 항목 | 비고 |
| --- | --- |
| [Android Studio](https://developer.android.com/studio) | 최신 안정 버전 |
| [Git](https://git-scm.com/downloads) | 설치 후 아래 A2의 사용자 설정까지 |
| GitHub 계정 | 아이디를 강사에게 보내면 Collaborator로 초대해 줍니다. **초대 메일을 꼭 수락하세요.** |
| USB-C 케이블 | Control Hub 연결용 |

## A2. Git 사용자 설정

Android Studio 아래쪽 **Terminal** 탭에서 입력합니다.

```bash
git config --global user.name "홍길동"
git config --global user.email "GitHub에 등록한 이메일"
```

그리고 Android Studio에서 GitHub 계정을 연결합니다.
**Settings → Version Control → GitHub → `+` → Log In via GitHub**
(Mac은 Android Studio → Settings)

## A3. 저장소 클론하기

1. 이 페이지 위쪽의 초록색 **Code** 버튼 → HTTPS 주소 복사
   ```
   https://github.com/math2653/2026_HOYA_Programming.git
   ```
2. Android Studio 시작 화면 → **Clone Repository** (또는 File → New → Project from Version Control)
3. 주소를 붙여넣고 저장할 폴더를 고른 뒤 **Clone**
4. 프로젝트가 열리면 **Gradle Sync**가 끝날 때까지 기다립니다 (처음엔 몇 분 걸림)
5. 왼쪽 Project 창에 `TeamCode`가 보이면 성공

> Sync가 실패하면 **File → Sync Project with Gradle Files**를 다시 실행하세요.
> Push할 때 권한 에러가 나면 Collaborator 초대를 수락했는지 확인하세요.

---

# Part B. 매일 쓰는 것

## B1. 하루 작업 순서

1. **Pull** (`Ctrl+T`): 팀원들이 올린 최신 코드 받기
2. 내 파일에서 코드 작성
3. 로봇에 올려서 테스트 (B5)
4. **Commit** (`Ctrl+K`): 무엇을 했는지 메시지 한 줄
5. **Push** (`Ctrl+Shift+K`): 팀 저장소에 올리기

## B2. 폴더 구조

```
2026_HOYA_Programming/
├─ FtcRobotController/          ← FTC 앱 본체 (수정하지 않기)
├─ TeamCode/                    ← 우리 코드
│   └─ src/main/java/org/firstinspires/ftc/teamcode/
│       ├─ SharedConfig.java    ← 팀 공용 설정값
│       ├─ examples/            ← 강의 예제 (참고용)
│       │   ├─ ExampleHello.java
│       │   ├─ ExampleMecanumTeleOp.java
│       │   └─ ExampleMotorTest.java  ← 바퀴 하나씩 테스트
│       └─ 이름Hello.java, 이름Drive.java …  ← 각자 만드는 파일
├─ JavaPractice/                ← Java 연습 (노트북에서 바로 실행, 로봇과 무관)
│   └─ src/main/java/practice/Ex01_Hello.java …
├─ README_FTC_SDK.md            ← 원래 SDK README
└─ build.gradle 등              ← 설정 파일 (수정하지 않기)
```

- 새 파일: `teamcode` 폴더 우클릭 → **New → Java Class**
- **파일 이름은 자기 이름으로 시작**합니다. 예: `MinsuHello`, `MinsuDrive`
  (여러 명이 같은 파일을 고치면 충돌이 나기 때문입니다)
- FTC 공식 샘플은 `FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples`에 있습니다. `TeamCode`로 복사한 뒤 `@Disabled` 줄을 지우면 Driver Station 메뉴에 나타납니다.

## B3. 로봇 Configuration

Driver Station의 Configuration 이름과 코드의 따옴표 안 이름이 **한 글자도 다르지 않게** 같아야 합니다.
이름과 변수명은 **대회 저장소(HOYA_2026)와 똑같이** 맞춰 두었습니다. 강의에서 쓴 코드를 대회 코드로 그대로 옮길 수 있습니다.

| 장치 | 허브 · 포트 | 모터 / 종류 | Configuration 이름 | 코드 변수명 | 방향 |
| --- | --- | --- | --- | --- | --- |
| 왼쪽 앞 바퀴 | Control Hub (좌) | goBILDA 435rpm | `LeftFront` | `frontLeftMotor` | REVERSE |
| 왼쪽 뒤 바퀴 | Control Hub (좌) | goBILDA 435rpm | `LeftBack` | `backLeftMotor` | REVERSE |
| 오른쪽 앞 바퀴 | Expansion Hub (우) | goBILDA 435rpm | `RightFront` | `frontRightMotor` | FORWARD |
| 오른쪽 뒤 바퀴 | Expansion Hub (우) | goBILDA 435rpm | `RightBack` | `backRightMotor` | FORWARD |
| POLLEN 로더 (인테이크 · 이송) | Expansion Hub · 포트 2 | goBILDA 1150rpm | `PollenLoader` | `loaderMotor` | REVERSE |
| POLLEN 슈터 | Expansion Hub · 포트 3 | goBILDA 1620rpm + 외부 기어 2배 증속 (롤러 최대 3240rpm) | `PollenShooter` | `shooterMotor` | REVERSE |
| 오도메트리 컴퓨터 | Control Hub I2C | goBILDA Pinpoint | `pinpoint` | `pinpoint` | - (대회 코드에서만 사용) |

Configuration에서 모터 종류는 전부 `GoBILDA 5202/3/4 series`를 고릅니다.

```java
DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "LeftFront");
//      ↑ 코드 변수명                                   ↑ Configuration 이름과 똑같이
```

- 드라이브: 4륜 메카넘 (위에서 볼 때 롤러가 X자), goBILDA 435rpm 모터 × 4
- 엔코더: 바퀴 1회전당 약 384.5틱 (435rpm). 슈터는 모터 103.8틱 ÷ 기어 2 = **롤러 1회전당 51.9틱**
- 디바이더 서보와 공 센서는 없습니다. **G407**(동시에 4개 초과 제어 금지)은 조종수가 직접 지켜야 합니다

**Configuration 만드는 법** (Driver Station)
1. 오른쪽 위 `⋮` → Configure Robot → New → Control Hub Portal
2. **Control Hub** → Motors → `LeftFront`, `LeftBack` (꽂힌 포트에)
3. **Expansion Hub 2** → Motors → `RightFront`, `RightBack`, 포트 2 `PollenLoader`, 포트 3 `PollenShooter`
4. Done 여러 번 → Save. 메인 화면 위쪽에 설정 이름이 보이면 적용된 것입니다.

Expansion Hub가 목록에 없으면 Control Hub와 연결된 케이블(RS485)과 전원을 확인하세요.

## B4. Git 사용법 (Android Studio)

| 하고 싶은 것 | 단축키 | 메뉴 |
| --- | --- | --- |
| 풀 (팀원 코드 받기) | `Ctrl+T` | Git → Update Project |
| 커밋 | `Ctrl+K` | Git → Commit |
| 푸시 | `Ctrl+Shift+K` | Git → Push |
| 커밋 전 변경 버리기 | `Ctrl+Alt+Z` | Git → Rollback |
| 브랜치 만들기·바꾸기 | | Git → Branches |
| 기록 보기 | | Git 창 → Log |

Mac은 `Ctrl` 대신 `Cmd`.

**Push rejected**가 뜨면 다른 사람이 먼저 올렸다는 뜻입니다. Pull(`Ctrl+T`) 후 다시 Push 하세요.

## B5. 로봇에 코드 올리기

**USB**: Control Hub를 USB-C로 연결 → 위쪽 기기 목록에 `REV Control Hub`가 보이면 ▶ **Run** (Shift+F10)

**Wi-Fi**: 노트북을 로봇 Wi-Fi에 연결한 뒤 Terminal에서

```bash
adb connect 192.168.43.1:5555
```

기기 목록에 뜨면 ▶ **Run**. (`adb`를 찾을 수 없다고 나오면 Android SDK의 `platform-tools` 폴더 안의 adb를 직접 실행하세요.)

> 로봇에는 **내 컴퓨터의 프로젝트 전체**가 올라갑니다.
> Pull을 안 하고 올리면 팀원들의 OpMode가 로봇에서 사라지니, **올리기 전에 꼭 Pull** 하세요.

## B5-1. 로봇 구동하기

**Driver Station 실행 순서**

| 단계 | 하는 일 | 코드에서는 |
| --- | --- | --- |
| 게임패드 연결 | `Start + A` (두 번째 조종수는 `Start + B`) | `gamepad1` / `gamepad2` |
| 선택 | TeleOp 목록에서 내 OpMode 고르기 | `@TeleOp(name = ...)` |
| INIT | 준비 | `waitForStart()` 전까지 실행 |
| ▶ PLAY | 시작 | `while (opModeIsActive())` 반복 |
| ■ STOP | 정지 | 반복이 끝나고 모터 정지 |

**구동 전 체크리스트**

1. 배터리 전압 (Driver Station 위쪽) 12V 이상, 12V 아래면 충전된 배터리로 교체
2. 빠진 선, 바퀴에 걸리는 것 없는지
3. 위쪽에 우리 Configuration 이름이 보이는지
4. 게임패드 `Start + A` 했는지
5. 강의실에서는 항상 거치대 위에서 (바퀴가 바닥에 닿지 않게). 실제 주행 · 발사는 경기장에서
6. 슈터 롤러 근처에 손 대지 않기, Pollen은 빼 두기
7. PLAY 전에 "움직입니다!" 외치기, STOP 담당 한 명

**모터 방향 테스트** (`Example Motor Test`, 거치대 위에서)

X = LeftFront, Y = RightFront, A = LeftBack, B = RightBack, D-pad 위 = PollenLoader, D-pad 아래 = PollenShooter. 누르는 동안 그 모터만 돕니다.

| 결과 | 고치는 법 |
| --- | --- |
| 다른 위치의 바퀴가 돈다 | Configuration에서 그 포트의 이름 수정 |
| 뒤로 돈다, 엔코더가 −로 줄어든다 | 그 모터의 `setDirection`을 반대로 (TeleOp 파일도 같이) |
| 안 돈다 | 선, 포트 확인. 오른쪽 바퀴 · 로더 · 슈터가 모두 안 돌면 Expansion Hub 연결 |
| 다 앞으로 도는데 옆으로 갈 때 비틀거린다 | 바퀴 장착 확인: 위에서 볼 때 롤러가 X자 |

**게임패드 조작** (Gamepad 1 하나로, 대회 코드와 같음)

| 입력 | 동작 |
| --- | --- |
| 왼쪽 스틱 | 앞뒤 / 좌우 이동 (대회 코드는 필드 중심, `Example Mecanum TeleOp`은 로봇 중심) |
| 오른쪽 스틱 X | 회전 |
| RT (누른 만큼) | 정밀 감속, 최대 50% |
| LT (0.1 넘게, 누른 만큼) | 로더 정방향 수거 / 이송 (최대 0.8) |
| D-pad 아래 (홀드) | 로더 역방향 -0.8 (자동 발사 취소, 다른 입력보다 우선) |
| A | 슈터 정지 |
| B / X / Y | 슈터 프리셋 (롤러 3240rpm 대비, 현재 코드 값 90 / 90 / 85 %), 켜진 버튼 다시 누르면 정지 |
| RB (홀드) | 자동 발사: 슈터 READY(목표 ±120rpm, 0.15초) → 로더 0.8로 0.22초 급송 → 0.35초 회복 → 반복 |
| LB (홀드) | 비상용 로봇 중심 주행 (대회 코드) |
| Start | 위치 · 각도 영점 (대회 코드) |

**조작감 다듬기** (대회 코드 `HoyaFieldCentricDrive`와 같은 식)

```java
if (Math.abs(axial) < 0.05) axial = 0;                     // 데드존 0.05
axial = 0.75 * axial * axial * axial + 0.25 * axial;       // 큐빅 곡선: 살짝 밀면 아주 천천히
double speed = 1.0 - 0.5 * gamepad1.right_trigger;         // RT 정밀 감속 (최대 50%)
```

## B6. 우리 팀 Git 규칙

1. 작업 시작 전, 로봇에 올리기 전에 **Pull**
2. 작게, 자주 **Commit**. 메시지는 무엇을 했는지 한 줄로
   - 좋은 예: `Add MinsuDrive mecanum TeleOp`, `메카넘 방향 수정`
   - 나쁜 예: `수정`, `최종`, `asdf`
3. 빌드되고 로봇에서 돌아가는 걸 확인한 다음 **Push**
4. `main`에서 직접 실험하지 않기 → **브랜치** 사용 (예: `minsu/slow-mode`)
5. 남의 파일을 고칠 땐 먼저 말하기

## B7. 자주 나는 문제

| 증상 | 원인 / 해결 |
| --- | --- |
| 빨간 밑줄, 빌드 실패 | `;` 빠짐, 대소문자 오타 → 자동완성 사용 |
| `cannot find symbol` | 이름 오타 또는 import 누락 → `Alt+Enter` |
| INIT 누르자마자 에러 | Configuration 이름과 코드 속 이름 불일치 |
| `NullPointerException` | `hardwareMap.get(...)` 전에 객체를 사용 |
| 로봇이 이상한 방향으로 움직임 | 바퀴를 하나씩 `setPower(0.3)`으로 돌려 보고 `setDirection` 수정 |
| Push rejected | 다른 사람이 먼저 올림 → Pull 후 다시 Push |
| Driver Station에 로봇이 안 보임 | 로봇 전원, Driver Station Wi-Fi 연결 |
| 에러는 없는데 스틱에 반응 없음 | 게임패드 `Start + A`, PLAY 눌렀는지 |
| 내 OpMode가 목록에 없음 | `@TeleOp` 확인, `@Disabled` 지우기, 다시 Run |
| 평소보다 느림, 버벅임 | 배터리 전압 → 충전된 배터리로 교체 |
| 테스트 중 갑자기 멈춤 | 전원선, 배터리 커넥터 흔들림 |
| 오른쪽 바퀴 · 로더 · 슈터가 다 안 돎 | Expansion Hub 연결 케이블 · 전원, Configuration에 Expansion Hub 2가 있는지 |

## B8. Java 연습 (JavaPractice)

로봇 없이 노트북에서 Java 문법을 연습하는 곳입니다. 로봇 앱 빌드와는 상관없습니다.

1. `JavaPractice/src/main/java/practice/` 에서 연습 파일을 엽니다.
2. 코드 왼쪽(줄 번호 옆)의 초록색 ▶ → **Run** 을 누르면 아래 Run 창에 결과가 나옵니다.
   - **상단 바의 Run 버튼은 쓰지 마세요.** 그건 로봇용(TeamCode)이라 "기기가 없다"고 뜹니다.
   - 한 번 실행하고 나면 상단 실행 목록에 `Ex01_Hello` 같은 항목이 생기고, 그걸 고르면 상단 버튼으로도 실행됩니다.
   - 한글이 깨지면 **File → Sync Project with Gradle Files** 후 다시 실행하세요.
3. 파일 아래쪽 `// TODO` 를 하나씩 해 보고 다시 실행합니다.

| 파일 | 내용 |
| --- | --- |
| `Ex01_Hello` | Java 파일 구조, 출력 |
| `Ex02_Variables` | 변수와 자료형 |
| `Ex03_Operators` | 연산자, 정수 나눗셈, Math, 형변환 |
| `Ex04_Conditions` | if / else, 논리 연산자 |
| `Ex05_Loops` | while, for |
| `Ex06_Arrays` | 배열과 for |
| `Ex07_Scope` | 변수 범위와 엣지 검출 |
| `Ex08_Methods` | 메서드 |
| `Ex09_Objects` | 클래스와 객체 (가짜 모터) |
| `Ex10_Static` | public, static, final |
| `Ex11_Strings` | 문자열, `%.2f`, 삼항 연산자 |
| `Ex12_Quiz` | 확인 퀴즈 |
| `Ex13_Mecanum` | 메카넘 바퀴 파워 계산 |

> 연습 파일을 고친 건 **커밋하지 마세요.** 다음 사람도 원래 문제로 연습해야 합니다.
> 고친 걸 원래대로 돌리려면 Commit 창에서 파일 우클릭 → **Rollback**.

---

## 참고 자료

- [FTC Docs (공식 문서)](https://ftc-docs.firstinspires.org)
- [Game Manual 0](https://gm0.org) — 커뮤니티가 만든 FTC 종합 가이드
- [공식 FtcRobotController 저장소](https://github.com/FIRST-Tech-Challenge/FtcRobotController)
- [README_FTC_SDK.md](README_FTC_SDK.md) — 원래 SDK README (2026-2027 BIOBUZZ 시즌, 업데이트 내역 포함)
