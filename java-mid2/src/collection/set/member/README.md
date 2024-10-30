## 컬렉션 프레임워크 - HashSet

### 직접 구현하는 Set1 - MyHashSetV1

#### Set은 중복을 허용하지 않고, 순서를 보장하지 않는 자료 구조이다.

#### 이전에 구현한 MyHastSetV0의 단점
- `add()`로 데이터를 추가할 때마다 셋에 중복 데이터가 있는지 항상 확인해야 한다. O(n)
- `contains()`로 데이터를 찾을 때에도 셋에 있는 모든 데이터를 찾고 비교해야 한다. O(n)

이렇게 성능이 느린 `MyHastSetV0`를 해시 알고리즘을 사용해서 평균 O(1)로 개선해보자

#### 예시 코드 - 해시 알고리즘을 사용하도록 개선된 MyHastSetV1

```java
import java.util.Arrays;
import java.util.LinkedList;

public class MyHastSetV1 {

    static final int DEFAULT_CAPACITY = 16;
    LinkedList<Integer>[] buckets;
    private int size = 0;
    private int capacity = DEFAULT_CAPACITY;

    public MyHashSetV1() {
        initBuckets();
    }
    
    public MyHashSetV1(int capacity) {
        this.capacity = capacity;
        initBuckets();
    }
    
    private void initBuckets() {
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }
    
    public boolean add(int value) {
        int hashIndex = hashIndex(value);
        LinkedList<Integer> bucket = buckets[hashIndex];
        if (bucket.contains(value)) {
            return false;
        }
        bucket.add(value);
        size++;
        return true;
    }
    
    public boolean contains(int searchValue) {
        int hashIndex = hashIndex(searchValue);
        LinkedList<Integer> bucket = buckets[hashIndex];
        return bucket.contains(searchValue);
    }
    
    public boolean remove(int value) {
        int hashIndex = hashIndex(value);
        LinkedList<Integer> bucket = buckets[hashIndex];
        boolean result = bucket.remove(Integer.valueOf(value));
        if (result) {
            size--;
            return true;
        }
        return false;
    }
    
    private int hashIndex(int value) {
        return value % capacity;
    }
    
    // getSize()
    // toString()
}
```
- `buckets`: 연결 리스트를 배열로 사용한다.
  - 배열 안에 연결 리스트가 들어있고, 연결 리스트 안에 데이터가 저장된다.
- `initBuckets()`
  - 연결 리스트를 생성해서 배열을 채운다. 배열의 모든 인덱스 위치에는 연결 리스트가 들어있다.

`MyHashSetV1`은 해시 알고리즘을 사용한 덕분에 등록, 검색, 삭제 모두 평균 O(1)로 연산 속도를 크게 개선했다.

#### 남은 문제
아래의 예시와 같이 숫자가 아닌 문자열 데이터를 저장할 때, 해시 인덱스를 사용하려면 어떻게 해야할까 ?

```java
MyHashSetV1 set = new MyHashSetV1(10);
set.add("A");
set.add("B");
set.add("HELLO");
```

### 문자열 해시 코드
모든 문자는 본인만의 고유한 숫자 즉 `아스키 코드(Ascii Code)`로 표현할 수 있다.

### 용어 정리

#### 해시 함수(Hash Function)
- 해시 함수는 임의의 길이의 데이터를 입력 받아 고정된 길이의 해시 값(해시 코드)를 출력하는 함수이다.
- 같은 데이터를 입력하면 항상 같은 해시 코드가 출력된다.
- 다른 데이터를 입력해도 같은 해시 코드가 출력될 수 있다.

#### 해시코드(Hash Code)
- 해시 코드는 데이터를 대표하는 값을 뜻한다. 보통 해시 함수를 통해 만들어진다.

#### 해시 인덱스(Hash Index)
- 해시 인덱스는 데이터의 저장 위치를 결정하는데, 주로 해시 코드를 사용해서 만든다.
- 보통 해시 코드의 결과에 배열의 크기(capacity)를 나누어 구한다.

### 자바의 Object.hashCode()
자바는 모든 객체가 자신만의 해시 코드를 표현할 수 있는 기능 즉 `hashCode()` 메서드를 제공한다.
```java
public class Object {
    public int hashCode();
}
```
- 이 메서드를 그대로 사용하기 보다는 보통 오버라이딩(재정의)해서 사용한다.
- 이 메서드의 기본 구현은 **객체의 참조값을 기반으로 해시 코드를 생성한다.**
- 즉 객체의 인스턴스가 다르면 해시 코드도 다르다.

### 자바의 기본 클래스의 해시 코드
- `Integer`, `String`같은 자바의 기본 클래스들은 대부분 내부 값을 기반으로 해시 코드를 구할 수 있도록 hashCode() 메서드를 재정의해 두었다.
- 따라서 데이터 값이 같으면 같은 해시 코드를 반환한다.
- 해시 코드의 경우 정수를 반환하기 때문에 마이너스 값이 나올 수 있다.

### 직접 구현하는 Set3 - 직접 만든 객체 보관
#### 직접 만든 객체를 Set에 보관
`MyHastSetV2`는 `Object`를 받을 수 있다. 따라서 직접 만든 `Member`와 같은 객체도 보관할 수 있다.
<br>
여기서 주의할 점은 직접 만든 객체가 `hashCode()`, `equals()` 두 메서드를 반드시 재정의(오버라이딩)해야 한다는 점이다.