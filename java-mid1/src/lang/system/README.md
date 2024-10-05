### System 클래스
`System`클래스는 시스템과 관련된 기본 기능들을 제공한다.

- 표준 입력, 출력, 오류 스트림: `System.in`, `System.out`, `System.err`
- 시간 측정: `System.currentTimeMillis()`, `System.nanoTime()`
- 환경 변수 조회: `System.getenv()`
- 시스템 속성: `System.getProperites()`를 사용해 현재 시스템 속성을 얻거나 `System.getProperty(String key)로 특정 속성을 얻을 수 있다. 참고로 시스템 속성은 자바에서 사용하는 설정 값이다.
- 시스템 종류: `System.exit(int status)`
  - `0`: 정상 종료
  - `0이 아님`: 오류나 예외적인 종료
- 배열 고속 복사: `System.arraycopy`는 시스템 레벨에서 최적화된 메모리 복사 연산을 사용한다. 반복문을 통해 하나씩 복사하는 것이 아닌 통째로 복사해버린다.