### Math 클래스

1. 기본 연산 메서드
- `abs(x)`: 절대값
- `max(a, b)`: 최대값
- `min(a, b)`: 최소값

2. 지수 및 로그 연산 메서드
- `exp(x)`: e^x 계산
- `log(x)`: 자연 로그
- `log(10)`: 로그 10
- `pow(a, b)`: a의 b 제곱

3. 반올림 및 정밀도 메서드
- `ceil(x)`: 올림
- `floor(x)`: 내림
- `rint(x)`: 가장 가까운 정수로 반올림
- `round(x)`: 반올림

4. 삼각 함수 메서드
- `sin(x)`: 사인
- `cos(x)`: 코사인
- `tan(x)`: 탄젠트

5. 기타 유용한 메서드
- `sqrt(x)`: 제곱근
- `cbrt(x)`: 세제곱근
- `random()`: 0.0과 1.0 사이의 무작위 값 생성

### Random 클래스
`Random`클래스를 사용하면 더욱 다양한 랜덤값을 구할 수 있다. `Math.random()`도 내부에서는 `Random`클래스를 사용한다.

### Random 클래스의 주요 메서드
- `nextInt()`: 랜덤 `int`값을 반환 (0부터 시작)
- `nextDouble()`: 0.0d ~ 1.0d 사이의 랜덤 `double`값을 반환
- `nextBoolean()`: 랜덤 `boolean`값을 반환
- `nextInt(int bound)`: `0` ~ `bound`미만의 숫자를 랜덤으로 반환

### 씨드(Seed)
랜덤은 내부에서 씨드(Seed)값을 사용해서 랜덤 값을 구한다. 씨드 값이 같으면 항상 같은 결과가 출력된다.
- `new Random()`: 생성자를 비워두면 내부에서 `System.nanoTime()`에 여러가지 복잡한 알고리즘을 섞어서 씨드 값을 생성한다. 따라서 반복 실행해도 결과가 항상 달라진다.