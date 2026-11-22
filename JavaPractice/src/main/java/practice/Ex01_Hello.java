package practice;

/**
 * Ex01. Java 파일의 구조
 *
 * 실행: main 옆의 초록색 ▶ 버튼 → Run
 * 결과는 아래 Run 창에 나옵니다.
 */
public class Ex01_Hello {

    public static void main(String[] args) {
        // System.out.println(...) : 괄호 안의 내용을 한 줄 출력
        System.out.println("Hello, HOYA!");
        System.out.println("코드는 위에서 아래로 실행됩니다.");

        int count = 3;              // 문장 끝에는 ;
        System.out.println(count);  // 3

        // TODO 1: 자기 이름을 출력하는 줄을 추가해 보세요.
        // TODO 2: 일부러 ; 하나를 지워 보고 어떤 에러가 나는지 확인한 뒤 다시 고치세요.
        // TODO 3: System 을 system 으로 바꿔 보세요. (대소문자 구분!)
    }
}
