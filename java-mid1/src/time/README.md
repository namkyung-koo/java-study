### 학습 목표
날짜와 시간 라이브러리를 사용해보자

### 날짜와 시간 라이브러리가 필요한 이유
날짜와 시간을 계산하는 것은 쉬워보이지만, 실제로는 매우 어렵고 복잡하다
1. 날짜와 시간 차이 계산
- 윤년, 각 달의 일수 등을 고려하면 간단한 뺄셈 연산으로는 정확한 결과를 얻기 어렵다.
2. 윤년 계산
- 지구가 태양 한 바퀴를 돌 때, 365일 5시간 48분 45초 정도 걸린다. 이로 인해 발생하는 오차를 해결하기 위해 윤년(leap year)을 도입
3. 일광 절약 시간(Daylight Saving Time, DST) 변환
- 태양이 일찍 뜨거나 상대적으로 늦게 뜨는 것에 맞추어 시간을 앞당기거나 늦추는 제도
4. 타임존 계산
- 각 타임존은 UTC(햡정 세계시)로부터의 시간 차이로 정의된다. 타임존 간의 날짜와 시간 변환을 정확히 계산하는 것은 복잡하다.

### GMT, UTC
London / UTC / GMT는 세계 시간의 기준이 되는 00:00 시간대이다.

### Class or Enum
- **LocalDate**: 날짜만 표현할 때 사용. 년, 월, 일을 다룬다. 예)`2013-11-21`
- **LocalTime**: 시간만 표현할 때 사용. 시, 분, 초를 다룬다. 예)`08:20:30.213`
  - 초는 밀리초, 나노초 단위도 포함할 수 있다.
- **LocalDateTime**: `LocalDate`와 `LocalTime`을 합한 개념. 예)`2013-11-21T08:20:30.213`

`Local`이 붙는 이유는 세계 시간대를 고려하지 않아 타임존이 적용되지 않기 때문이다.</br>
특정 지역의 날짜와 시간만 고려할 때 사용한다.
- 예. 애플리케이션 개발 시 국내 서비스만 고려할 때

- **ZonedDateTime**: 시간대를 고려한 날짜와 시간을 표현할 때 사용한다. 시간대를 표현하는 타임존이 포함된다.
  - `2013-11-21T08:20:30.213+9:00[Asia/Seoul]`
  - `+9:00`은 UTC로부터의 시간대 차이이다.
  - `Asia/Seoul`은 타임존이라 한다. 타임존을 알면 일광 절약 시간제에 대한 정보를 알 수 있다.
  - 일광 절약 시간제가 적용된다.
- **OffsetDateTime**: 시간대를 고려한 날짜와 시간을 표현할 때 사용한다. 타임존은 없고, UTC로부터의 시간대 차이인 오프셋만 포함한다.
  - `2013-11-21T08:20:30.213+9:00`
  - 일광 절약 시간제가 적용되지 않는다.
- **Year, Month, YearMonth, MonthDay**: 년, 월, 년월, 달일을 다룰 때 사용한다. 자주 사용하지는 않는다.
- **Instant**: UTC를 기준으로 하는, 시간의 한 지점을 나타낸다. `Instant`는 날짜와 시간을 나노초 정밀도로 표현하며, 1970년 1월 1일 0시 0분 0초(UTC)를 기준으로 경과한 시간으로 계산된다.

### 시간 개념의 분류
시간의 개념은 크게 2가지로 표현할 수 있다.
- 특정 시점의 시간(시각)
  - 이 프로젝트는 2013년 8월 16일까지 완료해야 해.
  - 다음 회의는 11시 30분에 진행한다.
  - 내 생일은 8월 16일이야.
- 시간의 간격(기간)
  - 앞으로 4년은 더 공부해야 해
  - 이 프로젝트는 3개월 남았어
  - 라면은 3분동안 끓여야 해
`Period`와 `Duration`은 시간의 간격(기간)을 표현하는데 사용된다.
  - **Period**: 두 날짜 사이의 간격을 년, 월, 일 단위로 나타낸다.
  - **Duration**: 두 시간 사이의 간격을 시, 분, 초(나노초) 단위로 나타낸다.

### 예시

```java
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ExMain {

    public static void main(String[] args) {
        LocalDate nowDate = LocalDate.now();
        System.out.println("오늘 날짜 = " + nowDate);

        LocalTime nowTime = LocalTime.now();
        System.out.println("현재 시간 = " + nowTime);

        LocalDateTime nowDt = LocalDateTime.now();
        System.out.println("현재 날짜시간 = " + nowDt);
    }
}
```
```java
오늘 날짜 = 2024-10-08
현재 시간 = 14:20:41.219602
현재 날짜시간 = 2024-10-08T14:20:41.219602
```

### ZonedDateTime

```java
public class ZonedDateTime {
    private final LocalDateTime dateTime;
    private final ZoneOffset offset;
    private final ZoneId zone;
}
```
```java
import java.time.ZonedDateTime;

public class ZonedDateTimeMain {

    public static void main(String[] args) {
        ZonedDateTime nowZdt = ZonedDateTime.now();
        System.out.println("nowZdt = " + nowZdt);
    }
}
```
```java
nowZdt = 2024-10-08T14:30:45.457712+09:00[Asia/Seoul]
```

### OffsetDateTime
```java
public class OffsetDateTime {
    private final LocalDateTime dateTime;
    private final ZoneOffset offset;
}
```
```java
import java.time.OffsetDateTime;

public class OffsetDateTimeMain {

    public static void main(String[] args) {
        OffsetDateTime nowOdt = OffsetDateTime.now();
        System.out.println("nowOdt = " + nowOdt);
    }
}
```
```java
nowOdt = 2024-10-08T14:30:45.422230+09:00
```

### 기계 중심의 시간 - Instant
```java
public class Instant {
    private final long seconds;
    private final int nanos;
}
```
#### Instant 특징
- 조금 특별한 시간, 기계 중심, UTC 기준
- 에포크 시간으로부터 흐른 시간을 초 단위로 저장
- 전세계 모든 서버 시간을 똑같이 맞출 수 있음. 항상 UTC 기준이므로 한국에 있는 `Instant`, 미국에 있는 `Instant`의 시간이 똑같음
- 서버 로그, epoch 시간 기반 계산이 필요할 때, 간단히 두 시간의 차이를 구할 때
- 단점: 초 단위의 간단한 연산 가능, 복잡한 연산은 못한다.
- 날짜 계산이 필요하면 `LocatDateTime`또는, `ZonedDateTime` 사용

### 날짜와 시간의 핵심 인터페이스
- 특정 시점의 시간: `Temporal(TemporalAccessor`포함) 인터페이스를 구현한다.
  - 구현으로 `LocalDateTime`, `LocalDate`, `LocalTime`, `ZonedDateTime`, `OffsetDateTime`, `Instant` 등이 있다.
- 시간의 간격(기간): `TemporalAmount` 인터페이스를 구현한다.
  - 구현으로 `Period`, `Duration`이 있다.

`TemporalAccessor`는 읽기 전용 접근, `Temporal`은 읽기와 쓰기(조작) 모두를 지원

### 날짜와 시간 문자열 파싱과 포맷팅
- 포맷팅: 날짜와 시간 데이터를 원하는 포맷의 문자열로 변경하는 것. `Date` -> `String`
- 파싱: 문자열을 날짜와 시간 데이터로 변경하는 것. `String` -> `Date`