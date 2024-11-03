## 컬렉션 프레임워크 - 순회, 정렬, 전체 정리

### 순회1 - 직접 구현하는 Iterable, Iterator
자료 구조에 들어있는 데이터를 차례대로 접근해서 처리하는 것을 `순회`라 한다.
<br>
다양한 자료 구조가 존재하고, 각각의 자료 구조마다 순회하는 방법이 모두 다르다.
<br>
#### 예시
- 배열 리스트: `index`값을 증가시키면서 순회한다.
- 연결 리스트: `node.next`값을 이용해 참조값을 옮겨가며 순회한다.

자바는 자료 구조의 구현과 관계 없이 모든 자료 구조를 동일한 방법으로 순회할 수 있는 일관성 있는 방법으로 `Iterable`과 `Iterator` 인터페이스를 제공한다.

#### 예제 코드 - Iterable 인터페이스의 주요 메서드
```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```
- 단순히 `Iterator` 반복자를 반환한다.

#### 예제 코드 - Iterator 인터페이스의 주요 메서드
```java
public interface Iterator<E> {
    boolean hasNext();
    E next();
}
```
- `hasNext()`: 다음 요소가 있는지 확인한다. 다음 요소가 없으면 `false`를 반환한다.
- `next()`: 다음 요소를 반환한다. 내부에 있는 위치를 다음으로 이동한다.

자료 구조에 들어있는 데이터를 처음부터 끝까지 순회하는 방법은 단순하다.
자료 구조에 다음 요소가 있는지 물어보고, 있으면 다음 요소를 꺼내는 과정을 반복하면 된다.

#### 예제 코드 - `Iterator`의 구현체
```java
import java.util.Iterator;

public class MyArrayIterator implements Iterator<Integer> {
    
    private int currentIndex = -1;
    private int[] targetArr;
    
    public MyArrayIterator(int[] targetArr) {
        this.targetArr = targetArr;
    }
    
    @Override
    public boolean hasNext() {
        return currentIndex < targetArr.length - 1;
    }
    
    @Override
    public Integer next() {
        return targetArr[++currentIndex];
    }
}
```
- `currentIndex`: 현재 인덱스, `next()`를 호출할 때마다 하나씩 증가한다.
- `hasNext()`: 다음 항목이 있는지 검사한다.
- `next()`: 다음 항목을 반환한다.
  - `currentIndex`를 하나 증가하고 항목을 반환한다.
  - 인덱스는 0부터 시작하기 때문에 `currentIndex`는 처음에는 -1을 가진다. 이렇게 하면 다음 항목을 조회했을 때 0이 된다. 따라서 처음 `next()`를 호출하면 0번 인덱스를 가리킨다.

`Iterator`는 단독으로 사용할 수 없다.

#### 예제 코드 - 간단한 자료 구조 구현

```java
import java.util.Iterator;

public class MyArray implements Iterable<Integer> {

    private int[] numbers;

    public MyArray(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyArrayIterator(numbers);
    }
}
```
- `Iterable` 인터페이스를 구현한다.
  - 이 인터페이스는 이 자료 구조에 사용할 반복자(`Iterator`)를 반환한다.
  - 앞서 만든 반복자인 `MyArrayIterator`를 반환한다.
  - 이 때 `MyArrayIterator`는 생성자를 통해 `MyArray`의 내부 배열인 `numbers`를 참조한다.

#### 예제 코드 - 간단한 main문

```java
import java.util.Iterator;

public class MyArrayMain {

    public static void main(String[] args) {
        MyArray myArray = new MyArray(new int[]{1, 2, 3, 4});
        
        Iterator<Integer> iterator = myArray.iterator();
        System.out.println("iterator 사용");
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            System.out.println("value = " + value);
        }
    }
}
```
#### 실행 결과
```markdown
iterator 사용
value = 1
value = 2
value = 3
value = 4
```
- `MyArray`는 `Iterable` 인터페이스를 구현한다. 따라서 `MyArray`는 반복할 수 있다는 의미가 된다.
- `Iterable` 인터페이스를 구현하면 `iterator()` 메서드를 구현해야 한다. 이 메서드는 `Iterator` 인터페이스를 구현한 반복자를 반환한다. 여기서는 `MyArrayIterator`를 생성해서 반환했다.
- `MyArrayIterator` 인스턴스는 내부에서 `MyArray`의 배열을 참조한다.

### 순회2 - 향상된 for문

#### Iterable과 향상된 for문(Enhanced For Loop)
for-each문으로 불리는 향상된 for문은 자료 구조를 순회하는 것이 목적이다.
<br>
자바는 `Iterable` 인터페이스를 구현한 객체에 대해서 향상된 for문을 사용할 수 있게 해준다.

#### 예제 코드 - 향상된 for문
```java
for (int value : myArray) {
    System.out.println("value = " + value);
}

// 자바는 컴파일 시점에 다음과 같이 코드를 변경한다.
while (iterator.hasNext()) {
    Integer value = iterator.next();
    System.out.println("value = " + value);
}
```
따라서 두 코드는 같은 코드이다. 모든 데이터를 순회한다면 깔끔한 `향상된 for문`을 사용하는 것이 좋다.

#### 정리
**만드는 사람이 수고로우면 쓰는 사람이 편하고, 만드는 사람이 편하면 쓰는 사람이 수고롭다.**

### 순회3 - 자바가 제공하는 Iterable, Iterator
- 자바는 컬렉션 프레임워크를 사용하는 개발자가 편리하고 일관된 방법으로 자료 구조를 순회할 수 있도록 `Iterable` 인터페이스를 제공하고, 각각의 구현체에 맞는 `Iterator`도 다 구현해두었다.
- 자바의 `Collection` 인터페이스 상위에 `Iterable`이 있다.
  - 즉 모든 컬렉션은 `Iterable`과 `Iterator`를 사용해서 순회할 수 있다.
- `Map`의 경우 `keySet(), `entrySet()`, `values()`를 호출하면 `Set`, `Collection`을 반환하기 때문에 `Key`나 `Value`를 정해서 순회할 수 있다.

### 정렬1 - Comparable, Comparator
데이터를 정렬하는 방법을 알아보자.
<br><br>
`Arrays.sort()`를 사용하면 배열에 들어있는 데이터를 순서대로 정렬할 수 있다.

#### 비교자 - Comparator
정렬을 할 때 1, 2, 3 순서가 아니라 내림차순 정렬을 하고 싶다면 어떻게 해야할까?
<br>
이 때는 비교자(`Comparator`)를 사용하면 된다. 이름 그대로 두 값을 비교할 때 `비교 기준`을 직접 제공할 수 있다.

#### 예제 코드 - Comparator 인터페이스
```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```
- 두 인수를 비교해서 결과 값을 반환하면 된다.
  - 첫 번째 인수가 더 작으면 음수, 예(`-1`)
  - 두 값이 같으면 `0`
  - 첫 번째 인수가 더 크면 양수, 예(`1`)

#### 예제 코드 - compare 메서드 오버라이딩
```java
public class SortMain {

    public static void main(String[] args) {
        
    }
    
    static class AscComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return (o1 < o2) ? -1 : ((o1 == o2) ? 0 : 1);
        }
    }
    
    static class DescComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return (o1 < o2) ? -1 : ((o1 == o2) ? 0 : 1) * -1;
        }
    }
}
```

`Arrays.sort()`를 사용할 때 비교자(`Comparator`)를 넘겨주면 알고리즘에서 어떤 값이 더 큰지 두 값을 비교할 때, 비교자를 사용한다.

#### 예제 코드 - Arrays.sort()

```java
Arrays.sort(array, new AscComparator());
Arrays.sort(array, new DescComparator());
```

### 정렬2 - Comparable, Comparator
`MyUser`와 같이 사용자가 직접 만든 객체를 정렬하려면 `Comparable` 인터페이스를 구현해야 한다.

#### 예제 코드 - Comparable 인터페이스
```java
public interface Comparable<T> {
    public int compareTo(T o);
}
```
- 자기 자신과 인수로 넘어온 객체를 비교해서 반환하면 된다.
  - 현재 객체가 인수로 주어진 객체보다 더 작으면 음수
  - 두 객체의 크기가 같으면 0
  - 현재 객체가 인수로 주어진 객체보다 더 크면 양수 반환

#### 예제 코드 - compareTo() 오버라이딩
```java
public class MyUser implements Comparable<MyUser> {
    
    private String id;
    private int age;
    
    @Override
    public int compareTo(MyUser o) {
        return (this.age < o.age) ? -1 : ((this.age == o.age) ? 0 : 1);
    }
}
```
- 정렬의 기준을 나이(`age`)로 정했다.
- `Comparable`을 통해 구현한 순서를 자연 순서(Natural Ordering)라 한다.

#### Arrays.sort(array)
기본 정렬을 시도한다. 이때는 객체가 스스로 가지고 있는 `Comparable` 인터페이스를 사용해서 비교한다.
<br>
`MyUser` 객체는 나이를 기준으로 오름차순으로 정렬된다.

### 다른 방식으로 정렬
객체가 가지고 있는 `Comparable` 기본 정렬이 아니라 다른 정렬을 사용하고 싶다면 `Comparator` 인터페이스를 구현하면 된다.

#### 에제 코드 - Comparator 인터페이스 구현
```java
import java.util.Comparator;

public class IdComparator implements Comparator<MyUser> {
    
    @Override
    public int compare(MyUser o1, MyUser o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
```

#### Arrays.sort(array, Comparator)
기본 정렬이 아닌 정렬 방식을 지정하고 싶다면 `Arrays.sort`의 인수로 비교자(`Comparator`)를 만들어서 넘겨주면 된다.
이렇게 비교자를 따로 전달하면 객체가 기본으로 가지고 있는 `Comparable`을 무시하고, 별도로 전달한 비교자를 사용해서 정렬한다.

#### 주의
만약 `Comparable`도 구현하지 않고, `Comparator`를 제공하지 않으면 런타임 오류가 발생한다.

#### 정리
객체의 기본 정렬 방법은 `Comparable`를 구현해서 정의한다.
<br>
기본 정렬 외에 다른 정렬 방법을 사용하는 경우 비교자(`Comparator`)를 별도로 구현해서 정렬 메서드에 전달하면 된다. 이 경우 전달한 `Comparator`가 항상 우선권을 가진다.

### 정렬3 - Comparable, Comparator
정렬은 배열 뿐만 아니라 순서가 있는 `List` 같은 자료 구조에도 사용할 수 있다.

#### Collections.sort(list)
- 이 메서드를 사용하면 기본 정렬이 적용된다.
- 이 방식보다는 객체 스스로 정렬 메서드를 가지고 있는 `list.sort()` 사용을 권장한다.

#### list.sort(null)
- 별도의 비교자가 없으므로 `Comparable`로 비교해서 정렬한다.

#### list.sort(new IdComparator())
- 전달한 비교자로 비교한다.

#### Tree 구조와 정렬
`TreeSet`과 같은 이진 탐색 트리 구조는 데이터를 보관할 때, 데이터를 정렬하면서 보관한다. 따라서 정렬 기준을 제공하는 것이 핋수적이다.
<br>
이진 탐색 트리는 데이터를 저장할 때 왼쪽 노드에 저장할 지, 오른쪽 노드에 저장할 지 비교가 필요하다.
<br>
따라서 `TreeSet`, `TreeMap`은 `Comparable` 또는 `Comparator`가 필수이다.

#### 예제 코드 - TreeSet과 Comparator
```java
// TreeSet을 생성할 때 별도의 비교자를 제공하지 않으면 객체가 구현한 Comparable을 사용한다.
new TreeSet<>()
// TreeSet을 생성할 때 별도의 비교자를 제공하면 Comparable 대신 Comparator를 사용해서 정렬한다.
new TreeSet<>(new IdComparator())
```

### 컬렉션 유틸
컬렉션을 편리하게 다룰 수 있는 다양한 기능을 알아보자.

#### Collections 정렬 관련 메서드
- `max`: 정렬 기준으로 최대 값을 찾아서 반환한다.
- `min`: 정렬 기준으로 최소 값을 찾아서 반환한다.
- `shuffle`: 컬렉션을 랜덤하게 섞는다.
- `sort`: 정렬 기준으로 컬렉션을 정렬한다.
- `reverse`: 정렬 기준의 반대로 컬렉션을 정렬한다.

#### 편리한 컬렉션 생성
- `List`, `Set`, `Map` 모두 `of()` 메서드를 지원한다.
- 단 불변 컬렉션이 생성되면 변경할 수 없다.
- 불변 리스트를 가변 리스트로 전환하려면 `new ArrayList<>()`를 사용하면 된다.
- 가변 리스트를 불변 리스트로 전환하려면 `Collections.unmodifiableList()`를 사용하면 된다.
  - 물론 다양한 `unmodifiableXxx()`가 존재한다.

### 자료 구조 실무 선택 가이드
- `List`의 경우 대부분 `ArrayList`를 사용한다.
- `Set`의 경우 대부분 `HashSet`을 사용한다.
- `Map`의 경우 대부분 `HashMap`을 사용한다.
- `Queue`의 경우 대부분 `ArrayDeque`를 사용한다.