## 컬렉션 프레임워크 - LinkedList

### 배열 리스트의 단점
- 사용하지 않는 공간 낭비
  - 배열은 필요한 배열의 크기를 미리 확보해야 한다.
- 배열의 중간에 데이터 추가
  - 배열의 앞이나 중간에 데이터를 추가하면 추가할 데이터의 공간을 확보하기 위해 기존 데이터들을 옮겨야 한다.

### 노드와 연결
낭비되는 메모리 없이 필요한 만큼만 메모리를 확보해서 사용하고, 앞이나 중간에 데이터를 추가하거나 삭제할 때 효율적인 자료 구조

#### 노드 클래스
```java
public class Node {
    Object item;
    Node next;
}
```

#### 리스트 자료 구조
순서가 있고, 중복을 허용하는 자료 구조를 리스트(List)라 한다.

#### 연결 리스트와 빅오
- Object get(int index)
  - 특정한 위치에 있는 데이터를 반환한다.
  - O(n)
- void add(Object e)
  - 마지막에 데이터를 추가한다.
  - O(n)
- Object set(int index, Object element)
  - 특정 위치에 있는 데이터를 찾아서 변경한다. 그리고 기존 값을 반환한다.
  - O(n)
- int indexOf(Object o)
  - 데이터를 검색하고, 검색된 위치를 반환한다.
  - O(n)
    - 모든 노드를 순회하면서 `equals()`를 사용해서 같은 데이터가 있는지 찾는다.

#### 배열 리스트 vs 연결 리스트 사용
데이터를 조회할 일이 많고, 뒷 부분에 데이터를 추가한다면 배열 리스트가 보통 더 좋은 성능을 제공한다.
앞 쪽의 데이터를 추가하거나 삭제할 일이 많다면 연결 리스트를 사용하는 것이 보통 더 좋은 성능을 제공한다.