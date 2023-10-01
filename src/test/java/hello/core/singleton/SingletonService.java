package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
    private static final SingletonService instance = new SingletonService();

    // 2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다.
    // 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private를 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    // private으로 하면 객체가 1개만 생성 => 싱글톤이구나~
    // public으로 하면 객체가 여러개 생성됨
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
