### this

멤벼 변수와 메서드의 매게변수의 이름이 같으면 어떻게 구분할까 ?
- 멤버 변수보다 매개변수가 코드 블럭의 더 안 쪽에 있기 때문에 매개변수가 우선순위를 가진다.
- 멤버 변수에 접근하려면 this.이라고 해주면 된다. 여기서 this는 인스턴스 자신의 참조값을 가리킨다.

### 생성자
- 생성자의 이름은 클래스의 이름과 같아야 한다. 첫 글자도 대문자로 시작한다.
- 생성자는 반환 타입이 없다.
- 나머지는 메서드와 같다.

### 생성자 장점

#### 중복 호출 제거
- 생성자 덕분에 객체를 생성하면서 동시에 생성 직후에 필요한 작업을 한 번에 처리할 수 있게 되었다.

#### 직접 정의한 생성자를 반드시 호출해야 한다.