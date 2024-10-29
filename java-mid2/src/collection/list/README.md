## 컬렉션 프레임워크 - List

### 리스트 추상화1 - 인터페이스 도입
자바 기본편에서 학습한 다형성과 OCP 원칙을 가장 잘 활용할 수 있는 곳 중 하나가 바로 자료 구조이다.
<br>
자료 구조에 다형성과 OCP 원칙이 어떻게 적용되는지 알아보자

#### List 자료 구조
지금까지 만든 `MyArrayList`와 `MyLinkedList`는 내부 구현만 다를 뿐 같은 기능을 제공하는 리스트이다.
<br>
이 둘의 공통 기능을 인터페이스로 뽑아서 추상화하면 다형성을 활용한 다양한 이득을 얻을 수 있다.

같은 기능을 제공하는 메서드 `MyList` 인터페이스로 뽑아보자.
```java
public interface MyList<E> {
    int size();
    
    void add(E e);
    
    void add(int index, E e);
    
    E get(int index);
    
    E set(int index, E element);
    
    E remove(int index);
    
    int indexOf(E o);
}
```
### 리스트 추상화2 - 의존관계 주입
`MyArrayList`를 활용해서 많은 데이터를 처리하는 `BatchProcessor`클래스를 개발하고 있다고 가정하자.
그런데 막상 프로그램을 개발하고 보니 데이터를 앞에서 추가하는 일이 많은 상황이라고 가정해보자.
데이터를 앞에서 추가하거나 삭제하는 일이 많다면 `MyLinkedList`를 사용하는 것이 훨씬 효율적이다.

#### 예시 코드 - 구체적인 MyArrayList에 의존하는 BatchProcessor 예시

```java
public class BatchProcessor {

    private final MyArrayList<Integer> list = new MyArrayList<>();
    
    public void logic(int size) {
        for (int i = 0; i < size; i++) {
            list.add(0, i); // 앞이 추가
        }
    }
}
```
`MyArrayList`를 사용해보니 성능이 너무 느려서 `MyLinkedList`를 사용하도록 코드를 변경해야 한다면 `BatchProcessor`의 내부 코드도 `MyArrayList`에서 `MyLinkedList`를 사용하도록 함께 변경해야 한다.

#### 예시 코드 - 구체적인 MyLinkedList에 의존하는 BatchProcessor 예시

```java
public class BatchProcessor {

    private final MyLinkedList<Integer> list = new MyLinkedList<>();
    
    ...
}
```

`BatchProcessor`가 구체적인 `MyArrayList` 또는 `MyLinkedList`를 사용하고 있다.
이것을 `BachProcessor`가 구체적인 클래스에 의존한다고 표현한다. 이렇게 구체적인 클래스에 직접 의존하면 `MyArrayList`를 `MyLinkedList`로 변경할 때마다 여기에 의존하는 `BatchProcessor`의 코드도 함께 수정해야 한다.
<br><br>

`BatchProcessor`가 구체적인 클래스에 의존하는 대신 추상적인 `MyList` 인터페이스에 의존하는 방법도 있다.

#### 예시 코드 - 추상적인 MyList에 의존하는 BatchProcessor 예시

```java
public class BatchProcessor {

    private final MyList<Integer> list;

    public BatchProcessor(MyList<Integer> list) {
        this.list = list;
    }
    
    ...
}
```

```java
main() {
    new BatchProcessor(new MyArrayList()); // MyArrayList를 사용하고 싶을 때
    new BatchProcessor(new MyLinkedList()); // MyLinkedList를 사용하고 싶을 때
}
```

다형성과 추상화를 활용하면 `BatchProcessor`코드를 전혀 변경하지 않고 리스트 전략(알고리즘)을 `MyArrayList`에서 `MyLinkedList`로 변경할 수 있다.

### 리스트 추상화3 - 컴파일 타임, 런타임 의존관계
의존관계는 크게 컴파일 타임 의존관계와 런타임 의존관계로 나눌 수 있다.
- **컴파일 타임(compile time)**: 코드 컴파일 시점을 뜻한다.
  - 컴파일 타임 의존관계는 자바 컴파일러가 보는 의존관계이다. 클래스에 모든 의존관계가 다 나타난다.
  - 쉽게 이야기해서 클래스에 바로 보이는 의존관계이다. 그리고 실행하지 않은 소스 코드에 정적으로 나타나는 의존관계이다.
  - `BatchProcessor`클래스를 보면 `MyList` 인터페이스만 사용한다. 따라서 `BatchProcessor`는 `MyList` 인터페이스에 의존한다.
- **런타임(runtime)**: 프로그램 실행 시점을 뜻한다.
  - 런타임 의존관계는 실제 프로그램이 작동할 때 보이는 의존관계다. 주로 생성된 인스턴스와 그것을 참조하는 의존관계이다.
  - 쉽게 이야기해서 프로그램이 실행될 때 인스턴스 간에 의존관계로 보면 된다.
  - 런타임 의존관계는 프로그램 실행 중에 계속 변할 수 있다.

#### 예시 코드 - 런타임 의존관계
```java
MyArrayList<Integer> list = new MyArrayList<>();
BatchProcessor processor = new BatchProcessor(list);
processor.logic(50_000);
```
- `BatchProcessor`인스턴스의 `MyList list`는 생성자를 통해 `MyArrayList(x001)` 인스턴스를 참조한다.
  - `BatchProcessor`인스턴스에 `MyArrayList(x001)` 의존관계를 주입한다.
- 따라서 이후 `logic()`을 호출하면 `MyArrayList` 인스턴스를 사용하게 된다.

#### 정리
- `BatchProcessor`에서 사용하는 리스트의 의존관계를 클래스에서 미리 결정하는 것이 아니라, 런타임에 객체를 생성하는 시점으로 미룬다. 따라서 런타임에 `MyList`의 구현체를 변경해도 `BatchProcessor`의 코드는 전혀 변경하지 않아도 된다.
- 이렇게 생성자를 통해 런타임 의존관계를 주입하는 것을 **생성자 의존관계 주입** 또는 줄여서 **생성자 주입**이라 한다.
- **클라이언트 클래스는 컴파일 타임에 추상적인 것에 의존하고, 런타임에 의존관계 주입을 통해 구현체를 주입받아 사용함으로써, 이런 이점을 얻을 수 있다.**

#### 배열 리스트 vs 연결 리스트
대부분의 경우 배열 리스트가 성능상 유리하다. 이런 이유로 실무에서는 주로 배열 리스트를 기본으로 사용한다.
<br>
만약 데이터를 앞쪽에서 자주 추가하거나 삭제할 일이 있다면 연결 리스트를 고려하자.

### 자바 리스트
#### List 인터페이스
`List`인터페이스는 `java.util` 패키지에 있는 컬렉션 프레임워크의 일부다.
<br>
`List`인터페이스는 `ArrayList`, `LinkedList`와 같은 여러 구현 클래스를 가지고 있으며, 각 클래스는 `List`인터페이스의 메서드를 구현한다.

#### 자바 ArrayList의 특징
- 배열을 사용해서 데이터를 관리한다.
- 기본 `CAPACITY`는 10이다.
  - `CAPACITY`를 넘어가면 배열을 50% 증가한다.
- 메모리 고속 복사 연산을 사용한다.
  - 배열의 요소 이동은 시스템 레벨에서 최적화된 메모리 고속 복사 연산을 사용해서 비교적 빠르게 수행된다.
    - `System.arraycopy()`를 사용한다.

#### 자바 LinkedList의 특징
- 이중 연결 리스트 구조
- 첫 번째 노드(**first**)와 마지막 노드(**last**) 둘 다 참조

#### 예시 코드 - 자바의 LinkedList 클래스
```java
class Node {
    E item;
    Node next;
    Node prev;
}

class LinkedList {
    Node first;
    Node last;
    int size;
}
```

#### 시간 복잡도와 실제 성능
- 이론적으로 `LinkedList`의 중간 삽입 연산은 `ArrayList`보다 빠를 수 있다. 그러나 실제 성능은 요소의 순차적 접근 속도, 메모리 할당 및 해제 비용, CPU 캐시 활용도 등 다양한 요소에 의해 영향을 받는다.
- `ArrayList`는 요소들이 메모리 상에서 연속적으로 위치하여 CPU 캐시 효율이 좋고, 메모리 접근 속도가 빠르다.
- 반면, `LinkedList`는 각 요소가 별도의 객체로 존재하고 다음 요소의 참조를 저장하기 때문에 CPU 캐시 효율이 떨어지고, 메모리 접근 속도가 상대적으로 느려질 수 있다.
- `ArrayList`의 경우 `CAPACITY`를 넘어서면 배열을 다시 만들고 복사하는 과정이 추가된다. 하지만 한 번에 50%씩 늘어나기 때문에 이 과정은 가끔 발생하므로, 전체 성능에 큰 영향을 주지는 않는다.