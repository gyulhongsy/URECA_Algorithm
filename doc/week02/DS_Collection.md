# 자바 컬렉션 기반 자료구조 정리

## List 컬렉션
- 객체를 인덱스로 관리
- 객체를 저장하면 인덱스 부여됨
- 인덱스로 객체를 검색, 삭제할 수 있는 기능 제공

### ArrayList
- ArrayList에 객체를 추가하면 내부 배열에 객체가 저장되고 제한 없이 객체를 추가할 수 있음
- 객체의 번지를 저장, 동일한 객체를 중복 저장 시 동일한 번지가 저장 됨.
- 값에 null 저장 가능
```java
// ArrayList 생성
List<E> list = new ArrayList<E>(); // E에 지장된 타입의 객체만 저장
List<E> list = new ArrayList<>(); // E에 지정된 타입의 객체만 저장
List list = new ArrayList(); // 모든 타입의 객체를 저장

list.add(객체); // 객체 삽입
list.size(); // 저장된 총 객체 수 반환
list.get(2); // 특정 인덱스의 객체 가져오기
list.remove(2); // 객체 삭제
```
- 객체를 추가하면 인덱스 0번부터 차례로 저장
    - 특정 인덱스의 객체를 제거하면 바로 뒤 인덱스부터 마지막 인덱스까지 모두 한 칸씩 앞으로 당겨짐
    - 특정 인덱스에 객체를 삽입하면 해당 인덱스부터 마지막 인덱스까지 모두 한 칸씩 뒤로 밀려남
    - ⇒ 빈번한 객체 삭제와 삽입이 일어나는 곳에는 적합하지 않음 (LinkedList가 더 적합)
 
### LinkedList
- 인접 객체를 체인처럼 연결해서 관리
- 특정 위치에서 객체를 삽입하거나 삭제하면 바로 앞 뒤 링크만 변경하면 됨
```java
// LinkedList 생성
List<E> list = new LinkedList<E>(); // E에 지장된 타입의 객체만 저장
List<E> list = new LinkedList<>(); // E에 지정된 타입의 객체만 저장
List list = new LinkedList(); // 모든 타입의 객체를 저장
```
<br>

## Map 컬렉션
- 키와 값으로 구성된 엔트리 객체를 저장
- 키는 중복 저장할 수 없지만 값은 중복 저장 가능
- 기존에 저장된 키와 동일한 키로 값을 저장하면 새로운 값으로 대치

### HashMap
- 키로 사용할 객체가 hashCode() 메소드의 리턴값이 같고 equals() 메소드가 true를 리턴할 경우, 동일 키로 보고 중복 저장을 허용하지 않는다.
```java
// HashMap 생성
Map<K, V> map = new HashMap<K, V>();
```
<br>

## Set 컬렉션
- 저장 순서가 유지되지 않음
- 객체 중복 저장x, 하나의 null만 가능

### HashSet
- 동등 객체를 중복 저장하지 않음
- 다른 객체라도 hashCode() 메소드의 리턴값이 같고, equals() 메소드가 true를 리턴하면 동일한 객체라고 판단하고 중복 저장하지 않음
```java
// HashSet 생성
Set<E> set = new HashSet<E>(); // E에 지장된 타입의 객체만 저장
Set<E> set = new HashSet<>(); // E에 지정된 타입의 객체만 저장
Set set = new HashSet(); // 모든 타입의 객체를 저장
```

### TreeSet
- 이진 트리를 기반으로 한 Set 컬렉션
- 여러 개의 노드가 트리 형태로 연결된 구조
- 루트 노드에서 시작해 각 노드에 최대 2개의 노드를 연결 가능
- TreeMap에 엔트리를 저장하면 자동 정렬되는데 부모 노드의 객체와 비교해서 낮은 것은 왼쪽, 높은 것은 오른 쪽 자식 노드에 저장
```java
// TreeSet 생성
TreeSet<E> treeSet = new TreeSet<E>();
```
---
## 도구 - 추가 내용

### Comparable과 Comparator
- Comparable
  - 객체 자체에 기본 정렬 기준을 정의하는 인터페이스
  - 객체가 Compatable 인터페이스를 구현하고 있어야 정렬 가능
  - Integer, Double, String 타입은 모두 Compatable을 구현하고 있기 때문에 상관 없음
  - 사용자 정의 객체를 저장할 때에는 반드시 Compatable을 구현하고 있어야 함
  - Compatable 인터페이스의 `compareTo()` 메소드를 재정의하여 구현
- Comparator
  - 정렬 기준을 외부에서 따로 정의 하는 인터페이스
  - 비교자: Compatator 인터페이스를 구현한 객체
  - Compatator 인터페이스에는 `compare()` 메소드가 정의되어 있어 재정의하여 구현

#### 차이점 비교
| 구분 | Comparable | Comparator |
|------|------------|------------|
| 정의 위치 | 클래스 내부 | 외부 |
| 매서드 | `compareTo()` | `compare()` |
| 정렬 기준 | 1개 (고정) | 여러 개 가능 |
| 사용 방식 | `sort(list)` | `sort(list, 기준)` |
| 코테 활용 | 적음 | 매우 많음 |

