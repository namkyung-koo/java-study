### final 변수와 상수
final 키워드는 이름 그대로 끝!이라는 뜻이다.</br>
변수에 final 키워드가 붙으면 더는 값을 변경할 수 없다.

#### 참고로 final은 class, method를 포함한 여러 곳에 붙을 수 있다.

### final - 필드(멤버 변수)
final을 필드에서 사용할 경우 해당 필드는 생성자를 통해서 한번만 초기화 될 수 있다.

### static final - 상수
JVM 상에서 하나만 존재하므로 중복과 메모리 비효율 문제를 모두 해결할 수 있다.

### final 변수와 참조
- final을 기본형 변수에 사용하면 값을 변경할 수 없다.
- final을 참조형 변수에 사용하면 참조값을 변경할 수 없다.