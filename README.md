# 이용 안내
성공회대학교 QR코드 출석체크 웹 이용 안내입니다.

앱 쪽으로 이동 [[QRCheck_Android](https://github.com/Jungea/QRCheck_Android)]


## 데이터베이스  
[[fin]db0416.sql](https://github.com/Jungea/QRCheck/blob/master/db/%5Bfin%5Ddb0416.sql "[fin]db0416.sql")

## 강의실 정보

**6202** 강의실의 시간표와 강의실 QR코드

|시간표| QR코드 |
|--|--|
| ![6202](https://user-images.githubusercontent.com/33142199/79813202-f3279c00-83b5-11ea-81cf-beae8d3274de.jpg =200x300) | ![6202](https://user-images.githubusercontent.com/33142199/79812723-b4ddad00-83b4-11ea-90da-a38cd5b825a2.png =300x300) |
> 소프트웨어 캡스톤디자인은 6202 강의다.

## 
**6405** 강의실의 시간표와 강의실 QR코드
|시간표| QR코드 |
|--|--|
| ![6405](https://user-images.githubusercontent.com/33142199/79813205-f589f600-83b5-11ea-8e67-ddac52842f1b.jpg =200x300) | ![6405](https://user-images.githubusercontent.com/33142199/79812777-d8a0f300-83b4-11ea-852a-9ccccf2f2bd8.png =300x300) |

## 테스트

> **공통 비밀번호: 1111**
###
  
 - 교수1 아이디: 100101
[프론트엔드 개발(월, 수), 웹프로그래밍, 소프트웨어 캡스톤디자인]

- 교수2 아이디: 100102
[대학생활 세미나 1]

- 교수3 아이디: 100103
[시스템분석 및 설계, Python 프로젝트]

##
> 현재 시간의 임의로 설정하려면
APIController.java now()를 수정하면 된다.

    return LocalDateTime.of(year, month, dayOfMonth, hour, minute);  
  
##

다른 테스트는   [QRCheck_Android](https://github.com/Jungea/QRCheck_Android)로 이동



