package hello.core.singleton;

public class SingletonService {

    //static이므로 하나만 만들어짐
    private static final SingletonService instance = new SingletonService();

    // instance 조회
    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){
        //이렇게 private화하여 다른 파일에서의 new생성을 막을 수 있다
    }
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
