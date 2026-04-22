# 정렬

## 버블 정렬 (Bubble Sort)
- 인접한 두 원소를 비교하여 큰 값을 뒤로 이동
- 가장 큰 값이 한 번의 순회마다 뒤로 확정됨
- 시간복잡도: O(n²)
- 구현 간단하지만 비효율적

### 코드
```java
public static void bubbleSort(int[] arr) {
  int n = arr.length;
  for (int i = 0; i < n - 1; i++) {
    for (int j = 0; j < n - 1 - i; j++) {
      if (arr[j] > arr[j + 1]) {
        int tmp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = tmp;
      }
    }
  }
}
```

## 선택 정렬 (Selection Sort)
- 가장 작은 값을 찾아 앞쪽으로 교환
- 시간복잡도: O(n²)
- 교환 횟수는 적음

### 코드
```java
public static void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        int minIdx = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }
        int temp = arr[i];
        arr[i] = arr[minIdx];
        arr[minIdx] = temp;
    }
}
```

## 삽입 정렬 (Insertion Sort)
- 아직 정렬되지 않은 부분의 첫 번째 요소를 정렬한 부분의 알맞은 위치에 삽입
- 시간복잡도: O(n²)
- 거의 정렬된 경우 빠름

### 코드
```java
public static void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
        int key = arr[i];
        int j = i - 1;

        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}
```

## 셸 정렬 (Shell Sort)
- 삽입 정렬의 장점을 살리고 단점을 보완한 정렬 알고리즘
- 일정한 간격(gap)으로 서로 떨어져 있는 두 요소를 그룹으로 묶어 정렬 후 간격을 좁혀 그룹의 수를 줄이며 정렬 반복
- 시간복잡도: O(n^1.5)

### 코드
```java
public static void shellSort(int[] arr) {
    int n = arr.length;

    for (int gap = n / 2; gap > 0; gap /= 2) {
        for (int i = gap; i < n; i++) {
            int temp = arr[i];
            int j = i;

            while (j >= gap && arr[j - gap] > temp) {
                arr[j] = arr[j - gap];
                j -= gap;
            }
            arr[j] = temp;
        }
    }
}
```

## 퀵 정렬 (Quick Sort)
- 피벗을 기준으로 두 그룹으로 나눠 분할 정렬
- 시간복잡도: O(n log n) / 최악: O(n²)
- 속도가 빨라 일반적으로 폭넓게 사용됨

### 코드
```java
public static void quickSort(int[] arr, int left, int right) {
    if (left >= right) return;

    int pivot = arr[(left + right) / 2];
    int i = left, j = right;

    while (i <= j) {
        while (arr[i] < pivot) i++;
        while (arr[j] > pivot) j--;

        if (i <= j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    quickSort(arr, left, j);
    quickSort(arr, i, right);
}
```

## 병합 정렬 (Merge Sort)
- 배열을 앞부분과 뒷부분 둘로 나누어 각각 정렬한 다음 병합하는 작업을 반복하여 정렬
- 시간복잡도: O(n log n)
- 추가 메모리 필요

### 코드
```java
public static void mergeSort(int[] arr, int left, int right) {
    if (left >= right) return;

    int mid = (left + right) / 2;
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, left, mid, right);
}

public static void merge(int[] arr, int left, int mid, int right) {
    int[] temp = new int[right - left + 1];
    int i = left, j = mid + 1, k = 0;

    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) temp[k++] = arr[i++];
        else temp[k++] = arr[j++];
    }

    while (i <= mid) temp[k++] = arr[i++];
    while (j <= right) temp[k++] = arr[j++];

    for (int t = 0; t < temp.length; t++) {
        arr[left + t] = temp[t];
    }
}
```

## 힙 정렬 (Heap Sort)
- 힙의 특성을 이용해 정렬
- 시간복잡도: O(n log n)
- 선택 정렬을 응용한 알고리즘

### 코드
```java
public static void heapSort(int[] arr) {
    int n = arr.length;

    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i);
    }

    for (int i = n - 1; i > 0; i--) {
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;

        heapify(arr, i, 0);
    }
}

public static void heapify(int[] arr, int n, int i) {
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if (left < n && arr[left] > arr[largest]) largest = left;
    if (right < n && arr[right] > arr[largest]) largest = right;

    if (largest != i) {
        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;

        heapify(arr, n, largest);
    }
}
```

## 도수 정렬 (Counting Sort)
- 요소의 대소 관계를 판단하지 않고 빠르게 정렬할 수 있는 알고리즘
- 시간복잡도: O(n + k)
- 정수, 범위 제한 있을 때 사용

### 코드
```java
public static void countingSort(int[] arr, int max) {
    int[] count = new int[max + 1];

    for (int num : arr) {
        count[num]++;
    }

    int idx = 0;
    for (int i = 0; i <= max; i++) {
        while (count[i]-- > 0) {
            arr[idx++] = i;
        }
    }
}
```

## 각 정렬 알고리즘의 장단점
| 정렬 | 장점 | 단점 |
|------|------|------|
| 버블 정렬 | 구현이 매우 간단 | 매우 느림 |
| 선택 정렬 | 교환 횟수 적음 | 비교 횟수 많아 비효율 |
| 삽입 정렬 | 거의 정렬된 경우 빠름 | 최악의 경우 느림 |
| 셸 정렬 | 삽입 정렬보다 빠름 | gap에 따라 성능 차이 큼 |
| 퀵 정렬 | 평균적으로 매우 빠름 | 안정성 떨어짐 |
| 병합 정렬 | 안정적임 | 추가 메모리 필요 |
| 힙 정렬 | 추가 메모리 거의 없음 | 구현 복잡, 캐시 비효율 |
| 도수 정렬 | 매우 빠름 | 범위 제한, 메모리 많이 사용 |
