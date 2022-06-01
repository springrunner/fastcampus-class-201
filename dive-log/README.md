DiveLog 프로젝트
====

# 프로젝트 소개
'다이빙 기록을 남기자!' 라는 취지로 개발하기 시작

## 설치방법
```
$ git https://github.com/springrunner/fastcampus-class-201
$ cd fastcampus-class-201/dive-log
$ chmod +x gradlew
$ ./gradlew bootRun
```

브라우저를 열고 http://localhost:8080 접속하여 확인가능


# 프로젝트 구성
* dive-log 프로젝

## 도메인
### **다이브리조트(DiveResort)**
* 이름(name)
* 대표자명(OwnerName)
* 연락처(ContactNumber)
* 위치(Address)
* 설명(Description)
* 다이브포인트 목록(DivePoints)

```java
public class DiveResort {
	private String name;
	private String ownerName;
	private String contactNumber;
	private String address;
	private String description;
}
```

### **다이브포인트(DivePoint)**
* 다이브리조트(DiveResort)
* 이름(Name)
* 바닥수심(Depth)
* 지형정보(Description)

```java
public class DivePoint {
	private DiveResort diveResort;
	private String name;
	private String depth;
	private String description;
}
```

### **다이브기록(DiveLog)**
* 다이빙일(DiveDate)
* 날씨(Weather)
* 다이빙포인트(DivePoint)
* 입수시간(EntryTime)
* 출시시간(ExitTime)
* 버디(Buddy): 함께다이빙한사람 기록
* 노트(Note): 다이빙 장비의 이상여부, 다이빙포인트에서 느낀 점, 새롭게 발견한 생물 등 기록

```java
public class DiveLog {
	private DivePoint divePoint;
	private LocalDate diveDate;
	private LocalTime entryTime;
	private LocalTime exitTime;
	private String buddy;
	private String note;
}
```