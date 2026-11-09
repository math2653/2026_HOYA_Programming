# 2026 HOYA Programming

HOYA FTC 팀의 로봇 코드 저장소입니다.
공식 [FtcRobotController](https://github.com/FIRST-Tech-Challenge/FtcRobotController) SDK를 기반으로 하고, 우리 팀 코드는 모두 `TeamCode` 폴더에 작성합니다.

---

## 1. 준비물

| 항목 | 비고 |
| --- | --- |
| [Android Studio](https://developer.android.com/studio) | 최신 안정 버전 |
| [Git](https://git-scm.com/downloads) | 설치 후 아래 2번의 사용자 설정까지 |
| GitHub 계정 | 아이디를 강사에게 보내면 Collaborator로 초대해 줍니다. **초대 메일을 꼭 수락하세요.** |
| USB-C 케이블 | Control Hub 연결용 |

## 2. Git 사용자 설정 (처음 한 번만)

Android Studio 아래쪽 **Terminal** 탭에서 입력합니다.

```bash
git config --global user.name "홍길동"
git config --global user.email "GitHub에 등록한 이메일"
```

그리고 Android Studio에서 GitHub 계정을 연결합니다.
**Settings → Version Control → GitHub → `+` → Log In via GitHub**
(Mac은 Android Studio → Settings)

## 3. 저장소 클론하기

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

## 4. 폴더 구조

```
2026_HOYA_Programming/
├─ FtcRobotController/          ← FTC 앱 본체 (수정하지 않기)
├─ TeamCode/                    ← 우리 코드
│   └─ src/main/java/org/firstinspires/ftc/teamcode/
│       ├─ SharedConfig.java    ← 팀 공용 설정값 (충돌 실습용)
│       ├─ examples/            ← 강의 예제 (참고용)
│       │   ├─ ExampleHello.java
│       │   └─ ExampleMecanumTeleOp.java
│       └─ 이름Hello.java, 이름Drive.java …  ← 각자 만드는 파일
└─ build.gradle 등              ← 설정 파일 (수정하지 않기)
```

- 새 파일: `teamcode` 폴더 우클릭 → **New → Java Class**
- **파일 이름은 자기 이름으로 시작**합니다. 예: `MinsuHello`, `MinsuDrive`
  (여러 명이 같은 파일을 고치면 충돌이 나기 때문입니다)
- FTC 공식 샘플은 `FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples`에 있습니다. `TeamCode`로 복사한 뒤 `@Disabled` 줄을 지우면 Driver Station 메뉴에 나타납니다.

## 5. 로봇 Configuration

Driver Station의 Configuration 이름과 코드 속 이름이 **한 글자도 다르지 않게** 같아야 합니다.

| 장치 | 종류 | 이름 |
| --- | --- | --- |
| 왼쪽 앞 바퀴 | goBILDA 5202/3/4 (435rpm) | `frontLeft` |
| 왼쪽 뒤 바퀴 | goBILDA 5202/3/4 (435rpm) | `backLeft` |
| 오른쪽 앞 바퀴 | goBILDA 5202/3/4 (435rpm) | `frontRight` |
| 오른쪽 뒤 바퀴 | goBILDA 5202/3/4 (435rpm) | `backRight` |
| 집게 서보 | Servo | `claw` |

- 드라이브: 4륜 메카넘, goBILDA Yellow Jacket 435rpm 모터 × 4
- 엔코더: 출력축 1회전당 약 384.5틱

## 6. 로봇에 코드 올리기

**USB**: Control Hub를 USB-C로 연결 → 위쪽 기기 목록에 `REV Control Hub`가 보이면 ▶ **Run** (Shift+F10)

**Wi-Fi**: 노트북을 로봇 Wi-Fi에 연결한 뒤 Terminal에서

```bash
adb connect 192.168.43.1:5555
```

기기 목록에 뜨면 ▶ **Run**. (`adb`를 찾을 수 없다고 나오면 Android SDK의 `platform-tools` 폴더 안의 adb를 직접 실행하세요.)

> 로봇에는 **내 컴퓨터의 프로젝트 전체**가 올라갑니다.
> Pull을 안 하고 올리면 팀원들의 OpMode가 로봇에서 사라지니, **올리기 전에 꼭 Pull** 하세요.

## 7. Git 기본 사용법 (Android Studio)

| 하고 싶은 것 | 단축키 | 메뉴 |
| --- | --- | --- |
| 커밋 | `Ctrl+K` | Git → Commit |
| 푸시 | `Ctrl+Shift+K` | Git → Push |
| 풀 (팀원 코드 받기) | `Ctrl+T` | Git → Update Project |
| 커밋 전 변경 버리기 | `Ctrl+Alt+Z` | Git → Rollback |
| 브랜치 만들기·바꾸기 | | Git → Branches |
| 기록 보기 | | Git 창 → Log |

Mac은 `Ctrl` 대신 `Cmd`.

**Push rejected**가 뜨면 다른 사람이 먼저 올렸다는 뜻입니다. Pull(`Ctrl+T`) 후 다시 Push 하세요.

## 8. 우리 팀 Git 규칙

1. 작업 시작 전, 로봇에 올리기 전에 **Pull**
2. 작게, 자주 **Commit**. 메시지는 무엇을 했는지 한 줄로
   - 좋은 예: `Add MinsuDrive mecanum TeleOp`, `메카넘 방향 수정`
   - 나쁜 예: `수정`, `최종`, `asdf`
3. 빌드되고 로봇에서 돌아가는 걸 확인한 다음 **Push**
4. `main`에서 직접 실험하지 않기 → **브랜치** 사용 (예: `minsu/slow-mode`)
5. 남의 파일을 고칠 땐 먼저 말하기

## 9. 자주 나는 문제

| 증상 | 원인 / 해결 |
| --- | --- |
| 빨간 밑줄, 빌드 실패 | `;` 빠짐, 대소문자 오타 → 자동완성 사용 |
| `cannot find symbol` | 이름 오타 또는 import 누락 → `Alt+Enter` |
| INIT 누르자마자 에러 | Configuration 이름과 코드 속 이름 불일치 |
| `NullPointerException` | `hardwareMap.get(...)` 전에 객체를 사용 |
| 로봇이 이상한 방향으로 움직임 | 바퀴를 하나씩 `setPower(0.3)`으로 돌려 보고 `setDirection` 수정 |

## 10. 참고 자료

- [FTC Docs (공식 문서)](https://ftc-docs.firstinspires.org)
- [Game Manual 0](https://gm0.org) — 커뮤니티가 만든 FTC 종합 가이드
- [공식 FtcRobotController 저장소](https://github.com/FIRST-Tech-Challenge/FtcRobotController)
- [README_FTC_SDK.md](README_FTC_SDK.md) — 원래 SDK README (2026-2027 BIOBUZZ 시즌, 업데이트 내역 포함)
