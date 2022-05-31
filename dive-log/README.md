DiveLog 프로젝트
====

# 프로젝트 소개
'다이빙 기록을 남기자!' 라는 취지로 개발하기 시작

## 설치방법
```
$ git clone .../dive-log
$ cd dive-log
$ ./gradlew bootRun
```

브라우저를 열고 http://localhost:8080 접속하여 확인가능


# 프로젝트 구성
* dive-log 프로젝

## 도메인
### **다이브리조트(DiveResort)**
* 이름(name)
* 대표자명(OwnerName)
* 연락처(contactNumber)
* 위치(Address)
* 설명(Description)
* 다이브포인트 목록(DivePoints)

### **다이브포인트(DivePoint)**
* 다이브리조트(DiveResort)
* 이름(Name)
* 바닥수심(BottomDepth)
* 지형정보(Description)

### **다이브기록(DiveLog)**
* 다이빙일(DiveDate)
* 날씨(Weather)
* 다이빙포인트(DivePoint)
* 입수시간(EntryDateTime)
* 출시시간(ExitDateTime)
* 버디명(BuddyName)
* 코멘트(Comment)
  * 다이빙 장비의 이상여부, 다이빙포인트에서 느낀 점, 새롭게 발견한 생물 등 기록
 