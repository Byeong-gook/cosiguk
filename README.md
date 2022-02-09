# 코로나19 도우미 어플 (가칭)
이 어플의 목적은 코로나 19 에 대한 정보 (지역별 확진자 , 뉴스 기사 , 근처 진료소 , 접종 현황 ) 
사용자 간 소통이 가능한 게시판을 만들어 정보 공유를 목표로 만들었습니다.
   
<video src="https://user-images.githubusercontent.com/46862400/151135430-6dfd9f55-9c1e-4a03-8fe0-c49d48c4dd47.mp4"></video>
   
## 제작된 플랫폼
> iOS : 김진우 / [iOS](https://github.com/castle6116/COVID-19_HELPER)

> Android : 구태호 / [Android](https://github.com/GuTaeHo/CovidSituation)

> Web : 권수형 /  [FrontEnd](https://github.com/Byeong-gook/cosiguk/tree/master)

> BackEnd : 기병국 / [BackEnd](https://github.com/Byeong-gook/cosiguk/tree/master)

- 목차
1. [어플 소개](#1-어플-소개)
2. [Controller 설명](#2-Controller-설명)
   1. [function 설명](#21-function-설명)
3. [기술 및 라이센스](#3-기술-및-라이센스)
   1. [iOS 기술 및 라이센스](#ios에서-사용된-기술-및-라이센스)
   2. [Android 기술 및 라이센스](#android에서-사용된-기술-및-라이센스)
   3. [Web  기술 및 라이센스](#front-end-web-에서-사용된-기술-및-라이센스)
   4. [BackEnd  기술 및 라이센스](#backend에서-사용된-기술-및-라이센스)

----

## 1. 어플 소개
> 어플의 기능은 크게 코로나19 상황판 , 뉴스 상황판 , 백신 현황 (지도), 이용자들의 게시판 , 공지사항 로 분류 되어 있습니다.    
> 어플 제작 목표는 코로나 19 그리고 다른 질병 등이 창궐 했을 때 사용자에게 정확하고 빠른 정보를 전달 하고자 제작 하게 되었습니다.    
> 코로나19 상황판은 현재 확진자의 현황 , 시/도별 감염 현황을 알려 주고 있습니다.    
> 뉴스 상황판은 현재 뜨거운 이슈인 코로나19 관련 뉴스만 출력되게 하여 사용자들이 빠르게 정보를 접할 수 있게 제작 하였습니다.    
> 백신 현황은 전 국민들의 예방 접종 현황을 표출 하고 뿐만 아니라 자기 중심으로 15km 안의 예방접종센터의 거리 , 전화번호 , 위치 등을 표출 하여 사용자가 접근성이 높아지도록 하였습니다.    
> 게시판은 이용자들이 코로나 나 혹은 다른 질병에 대해서 소통할 수 있는 장을 만들었습니다.    
> 공지사항은 어플의 공지 사항을 출력하며 탭 했을 때 밑으로 펴지는 형식으로 제작 하였습니다.    

<img width="20%" src="https://user-images.githubusercontent.com/46862400/143527515-b579004c-f7c0-434e-a9bb-527deac8c35e.PNG"/><img width="20%" src="https://user-images.githubusercontent.com/46862400/143527534-b31bcf82-cb53-4cd9-85d6-6f6fe21de7c3.PNG"/><img width="20%" src="https://user-images.githubusercontent.com/46862400/143527537-e9576438-a5a9-40bf-b1f3-b18316974387.PNG"/><img width="20%" src="https://user-images.githubusercontent.com/46862400/143527540-c986beef-fcc5-439b-8ab0-69241308775d.PNG"/><img width="20%" src="https://user-images.githubusercontent.com/46862400/143527543-6992eafd-2e7b-41b4-8b6d-d0b89e90fbca.PNG"/>

----

## 2. Controller 설명
- 메인화면(1번 페이지) / MainViewController 
    + 공공데이터 포털데이터를 받아와 사용자들에게 정보를 뿌려줌   
     코로나19 예방접종 통계 데이터 조회 API , 코로나19 시/도별 발생 현황을 받아옵니다.
     CollectionView를 사용하여 제작 하였으며 인원 수 1000명 단위로 , 를 찍어주는 기능,
     XML파싱 기능 , 위로 당겼을 때 새로고침 되는 기능 제작 하였음.
     CollectionView 안에 ScrollView를 넣어 드래그가 가능하게 만듦 
     (ScrollView를 굳이 안넣어도 되었지만 초반에 만들어서 후에 수정할 예정)
     시/도별 API에서 받아온 객체를 기준으로 지역 갯수를 체크 한 뒤 cell 갯수 반환 하여 각 지역별로 뿌려주게 제작함  
- SideMenuController
    + 어플의 왼쪽 상단에 메뉴바를 만들어 누르면 나오게 만든 사이드바 메뉴
    크게 사용방법 , 공지사항 , 라이센스 , 어플 버전을 표출 하게 하였음.
    사용방법을 누를 시 HowToUseViewController로 연결 됨.
    공지사항을 누를 시 NoticeViewController로 연결 됨.
    라인센스를 누를 시 LicenseViewController로 연결 됨.
    - HowToUseViewController
        + 이용자들이 간단하게 볼 수 있게 드래그 형식으로 옆으로 넘기면 이미지와 셀이 변경 되는 형식으로 제작
        CollectionView와 Pager를 사용 layout은 flowLayout으로 배치 하였음.  
        
    - NoticeViewController
        + 어플의 공지사항을 표출 하기 위해서 제작하였으며 TableView를 사용하여 클릭시 펴지는 형식으로 사용
        Alamofire 사용됨   API통신으로 팀원이 제작한 BackEnd에 통신 
        통신에 성공하면 tableView를 reload하여 이용자에게 표출 될 수 있도록 만듦
        API에서 받아온 객체 기준으로 count 하여 tableCell 갯수 지정
        
    - LicenseViewController
        + 어플에 사용된 오픈소스 라이브러리들을 표출 하기 위해서 제작할 예정 TableView로 제작하여
        클릭 시 화면이 넘어가는 형식으로 만들려고 예정 중
    
- NewsViewController
    + 네이버 검색 API를 사용하여 검색 없이 뉴스를 표출 하기 위해 제작
    간단하게 해당 뉴스 내용을 보여주고 클릭 시 사이트로 넘어가는 형식
    Alamofire 사용 하였음 (네이버 검색 api 시간 반환 형식을 잘 몰라서 엄청 헤맸던 기억이 남)
    API에서 받아온 객체 기준으로 count 하여 tableCell 갯수 지정 
    
- VaccineViewController
    + 공공데이터 API를 사용하여 예방접종 통계 데이터 조회 서비스 , 예방접종센터 조회서비스를 가져와
    API에서 받아온 객체 기준으로 자료 입력 예방접종센터 정보를받아와 X,Y 좌표를 기준으로 반경 15km 내의
    접종센터를 거리 계산 하여 출력, 지도로 보면 가독성이 떨어질 수 있음으로 tableView 형식으로
    리스트로 출력 하였습니다. 사용자가 정보를 새로고침 할 수 있도록 새로고침 버튼도 넣었습니다.

- GeneralForumViewController
    + TableView를 사용하여 제작 하였습니다. BackEnd에 API요청을 하여 값을 가져와 글이 만들어진 시간을
    토대로 정렬 하였습니다. 게시글의 제일 아래로 내리면 자동으로 10개의 게시글을 요청합니다.
    게시글을 누르면 DetailForumViewController로 이동하여 세부 페이지를 출력 합니다.
    
- DetailForumViewController
    + CollectionView를 사용하여 만들었고 글 내용 (Header) 댓글 (Content) 글쓰기 (View) 형식으로
    제작 하였습니다. Header은 글 내용에 따라 가변적으로 사이즈가 변하도록 만들었습니다. 추천 , 비추천 버튼을
    추가하여 사용자들이 한눈에 알아볼 수 있도록 제작 하였습니다. 게시글 그리고 댓글에 대해서 더보기 버튼을 누르면
    StackView를 사용 하여 펴지며 그 안에 신고, 수정 기능을 넣었습니다. 그리고 신고와 추천, 비추천은 IP당 한번만 
    가능하게 제작하였습니다. 하단에는 글쓰기가 있으며 텍스트 필드를 누르면 키보드가 같이 올라가 이용자가 사용할 때
    불편함이 없도록 제작 하였습니다.
    
### 2.1 function 설명
- MainViewController
    <details markdown="1">
    <summary>접기/펼치기</summary>
    
       + initRefresh ()   
        -   refreshControl 추가 , refresh 이벤트 추가 (updateUI로 추가)
        
       + updateUI (refresh: UIRefreshControl)   
        -   코로나19 예방접종 api에서 받아온 값을 저장한 Covidresult , 코로나19 지역별 감염현황 api에서 받아온 값을 저장하는 Coviddesult
            배열을 초기화 진행 후 예방접종 api를 불러오는 함수인 COVID_CONNECT에 며칠전 기준으로 요청할지 값을 포함해서 보낸다
            Covid_day를 초기화 한 뒤 COVID_Desult_CONNECT를 요청 위와 같이 값을 포함해서 요청, CollectionView 리로드 해준다
            refreshControl 종료    
        
       + DecimalWon (value:Int,cot:Int) , (value:String) , (value:String,cot:Int)   
        -   세자리 수 마다 ,를 찍어 String으로 반환하는 함수이다.
            numberFormatter을 정의 numberStyle을 .decimal로 하여 세자리 수 마다 콤마를 넣어준다. String으로 변환하여 반환 
        
       + textinput()    
        -   스토리보드에 정의되어있는 코로나19 상황판에 API 반환값을 기준으로 기준일 , 일일 확진 , 사망 , 누적검사 , 누적확진 ,
        확진환자 금일 증가수 , 사망자 금일 증가수 , 누적 검사 수 금일 증가수의 값을 업데이트 해준다
        
       + COVID_CONNECT (day : Int)   
        -   fomat_today 라는 DateFormatter를 선언 dateFormat을 yyyyMMdd 로 변경 test변수에 day만큼 날짜를 빼서 저장
        ex) 20211202 - day(1) = 20211201 yesterday 변수에 test를 문자열로 변경해서 저장
        yesterday와 today를 기반으로 감염현황 api 요청 만약 2일치가 들어오지않으면 전날로 요청, api 요청 값을 기반으로 textinput 함수 실행
        
       + COVID_Desult_CONNECT (day : Int)   
        -   fomat_today 라는 DateFormatter를 선언 dateFormat을 yyyyMMdd 로 변경 test변수에 day만큼 날짜를 빼서 저장
        ex) 20211202 - day(1) = 20211201 yesterday 변수에 test를 문자열로 변경해서 저장
        yesterday와 today를 기반으로 시/도별 감염 현황 api 요청 만약 2일치가 들어오지않으면 전날로 요청, api 요청 값을 기반으로 textinput 함수 실행
        로딩 화면 숨기는 hideLoading() 실행
        
       + parser(_ parser: XMLParser, didStartElement elementName: String, namespaceURI: String?, qualifiedName qName: String?, attributes attributeDict: [String : String] = [:])   
        -   HTTP통신으로 돌아오는 XML값 파싱, 각 값으로 파싱 gubun이 비었으면 감염현황 api , gubun이 존재 하면 시/도별 감염 현황 api로 판별하여 파싱 진행
        
       + parser(_ parser: XMLParser, foundCharacters string: String)   
        -   XML값 각 태그에 담겨있는 문자열을 전달한다
        
       + parser(_ parser: XMLParser, didEndElement elementName: String, namespaceURI: String?, qualifiedName qName: String?)   
        -   태그가 nil인지 확인 후 nil이 아니라면 XML파서가 각 태그 값을 기준으로 배열에 값을 넣는다. elementValue를 nil로 변경
        
       + collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize
        -   셀의 크기를 지정 해주는 함수 
       
       + collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int
        -   Covidedesult 배열의 개수를 반환
        
       + collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell
        -   Covidedesult 배열을 기반 시/도별 상환판의 내용을 지정해준다. CollectionView 안에 View에 border값을 넣어 회색으로 지정하고 굵기 1로 지정 코너값을 넣어 동그랗게 만들어줌
            Cell도 코너 값을 넣어 동그랗게 만들어줌.

    </details>
    
- SideMenuController
    <details markdown="1">
    <summary>접기/펼치기</summary>
    
       + startUI()
        -   tableView의 dataSource, delegate를 self로 지정 separatorStyle을 지정하여 선을 표시하지 않음.
        
       + tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int 
        -   items 배열을 개수를 반환
        
       + tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell   
        -   item 배열에 indexPath.row를 참조하여 값이 어플버전이 아니라면 versionLabel의 text를 nil로 설정 cell을 반환한다.
        
       + tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath)   
        -   item 배열에 indexPath.row를 참조하여 HowToUse , NoticeView , LicenseView으로 Segue로 연결하여 셀을 클릭한 경우 화면을 이동 시켜줌
    
    </details>
- NewsViewController
    <details markdown="1">
    <summary>접기/펼치기</summary>
    
       + startUI()
        -   tableview_Custom의 delegate , dataSource를 self로 지정 , rowHeight를 120으로 지정 naverNewsRequest 함수를 호출하여 
            값이 정상적으로 반환되면 news 배열에 각 값을 넣고 TableView를 reload하고 hideLoading 함수를 호출
        
       + scrollViewDidEndDragging(_ scrollView: UIScrollView, willDecelerate decelerate: Bool)
        -   size = scrollView의 콘텐츠 전체의 높이 값 , offset = 현재 콘텐츠의 y좌표 값 , bounds = scrollView에 크기 값 , 
        inset = 스크롤뷰 모서리나 safe area에 사용자가 추가한 컨텐츠 크기 만약 ScrollView의 콘텐츠 y좌표 값이 바닥에 닿았을 때
        start의 값이 100 이하라면 naverNewsRequest 함수를 호출하고 news배열에 추가하고 TableView를 reload 하고 refresh를 멈춰준다.  
        
       + naverNewsRequest(Query : String, Display : Int , Start : Int, complation : ((News?) -> ())?)
        -   Display는 20개씩 받아오기 위해 지정 , Start는 1부터 시작하여 naverNewsRequest를 호출할 때 마다 20씩 증가
            헤더에 X-Naver-Client-Id 와 X-Naver-Client-Secret 의 값을 KeyChainSwift 라이브러리를 사용하여 암호화 하여 저장
            header에 추가 하여 Naver 뉴스 검색 api에 코로나로 검색하여 값을 받아오게 하여줌.
            
       + tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int 
        -   news 배열 개수를 반환
        
       + tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell    
        -   cell 세부 내용 구현 Content의 라인을 3개로 지정 , 한글 줄바꿈 적용 네이버 뉴스 검색 api로 들어오는 date를 yyyy년 MM월 dd일 HH시 mm분으로 변경
            api에서 들어오는 타이틀 , 콘텐츠 부분 html 태그를 적용 시켜 글자 굵기 등 적용 후 cell을 리턴
        
       + tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath)
        -   cell을 클릭한 경우 news 배열에 있는 link로 이동하게 만듬
        
       + htmlEscaped(font: UIFont, colorHex: String, lineSpacing: CGFloat) -> NSAttributedString   
        -   swift에 구현되어 있는 NSAttributedString으로 변환하여 반환하여줌 
    
    </details>
- VaccineViewController
    <details markdown="1">
    <summary>접기/펼치기</summary>
    
       + startUI()
        -   네이버 지도 객체의 delegate, touchDelegate 를 self로 지정 해주고 gps 버튼 활성화 , 줌 컨트롤러 활성화 를 해준 뒤
            VaccineCollection(CollectionView_의 delegate , datasource도 self로 지정 해준다.
            SwitchButton의 delegate , isOn 을 각 self 와 true로 지정 하여 준다.
            현재 위치를 받아오기 위해 locationManger의 delegate를 self로 변경 하여 위치 권한 확인 및 현재 위치를 받아온다.
            그 후 textSet() 함수를 실행 하고 만약 위치서비스가 허용되지 않음 , 한번만 허용함 , 다음번에 묻기로 설정 되어 있으면
            locationManagerDidChangeAuthorization() 함수를 실행 하여 위치 권한이 허용되지 않음인 경우 
            기능이 제대로 작동하지 않음을 고지하기 위해 getLocationUsagePermission() 함수를 불러온다.
            만약 위치권한이 제대로 받아왔을 경우 locationManger.startUpdatingLocation() 하여 위치를 받아온다.
        
       + MapRefresh(_ sender: Any)   
        -   ViewController에 있는 RefreshButton을 클릭 한 경우 발생하는 함수로 naverMapSetting() 함수를 호출 하고
            VaccineCollection을 reload 한다.
        
       + naverMapSetting()   
        -   네이버 지도의 infoWindow(클릭 시 나오는 네모 객체)의 dataSource를 infoWindow의 defaultSource로 지정 후
            현재 만들어져 있는 마커를 모두 nil로 초기화를 진행 후 vaccinCenter 배열을 기준으로 for문 실행 marker를 생성하여 배열에 저장 되어 있는
            lat , lng을 기반으로 마커의 위치를 지정하여 준 뒤 클릭 시 데이타 소스는 marker에 tag를 달아 infoWindow를 열게끔 이벤트를 생성
            marker의 touchHandler를 위에서 만든 이벤트로 설정하여 준다. marker의 텍스트, 컬러 , 텍스트 사이즈 설정 후 현재 위치를 기반으로
            진료소의 위치와 거리를 계산 만약 현재 위치 값이 없는 경우 서울 시청으로 기본값을 지정 하였음. 마커 이미지 , 크기를 설정한 뒤 makerAll 배열에 추가
            marker의 mapView를 연동 시켜준 뒤 함수를 종료 한다
            
       + mapView(_ mapView: NMFMapView, didTapMap latlng: NMGLatLng, point: CGPoint)   
        -   지도를 클릭 시 infoWindow를 닫아준다.
        
       + naverMapCamera()    
        -   지도의 현재 화면을 자신의 위치 기준으로 옮겨준다. 만약 값이 없다면 서울 시청으로 이동한다. 그리고 로딩 화면을 없애주는 hideLoading() 함수를 실행한다.
        
       + locationManagerDidChangeAuthorization(_ manager: CLLocationManager) 
        -   LocationManger로 현재 권한을 확인하여 허용되지 않음 인 경우 getLocationUsagePermission() 함수를 실행한다.
        
       + textSet()
        -   백신 현황 페이지에서 상단에 있는 예방 접종 현황 , 내 주변 진료소 현황 리스트 화면의 텍스트를 설정하는 함수이다.
            예방 접종 현황 view에 Radius , View안에 있는 Label에 각 Radius를 준다.
            VaccineHttp() 함수를 불러와 예방 접종 현황 api를 호출 vaccine의 값이 nil이 아니고 currentCount(api에서 반환한 객체 개수)가 0개인 경우
            전날로 요청하여 예방 접종 현황의 라벨에 문자를 입력해 준다.
            VaccineCenterHttp 함수를 불러와 진료소 현황 api를 호출 center의 값이 nil이 아닌 경우 center에 lat , lng을 기준으로 거리를 계산 하여
            15km 이내의 진료소만 vaccineCenter 배열에 삽입 하고 난 후 vaccineCenter의 lat , lng으로 현재의 거리와 계산하여 가장 가까운 순으로
            정렬 합니다. vaccineCollection을 reload하고 naverMapSetting(), naverMapCamera 함수를 호출 한 뒤 종료 한다. 
        
       + DecimalWon(value: Int) -> String
        -   세자리 수 마다 ,를 찍어 String으로 반환하는 함수이다.
            numberFormatter을 정의 numberStyle을 .decimal로 하여 세자리 수 마다 콤마를 넣어준다. String으로 변환하여 반환
        
       + VaccineHttp(day: Int ,complation : ((Vaccine?) -> ())?)   
        -   예방 접종 현황 api를 호출하는 함수이다.
            DateFormatter로 오늘 날짜를 yyyy-MM-dd 형식으로 변환 한 뒤 day 만큼 날짜를 뺀 뒤 today로 저장 parameter로 serviceKey와 perPage baseDate를 설정하여
            요청한다. get에 성공하면 obj로 반환된 객체를 JSON으로 변경하여 JSONDecoder를 사용하여 Vaccine 객체 형태로 변환하여 디코딩을 진행한 뒤 클로저로 전송한다.
            통신에 실패하거나 문제가 발생한 경우 콘솔에 출력해준다.
        
       + VaccineCenterHttp(complation : ((Center?) -> ())?)
        -   진료소 현황을 요청하는 api
            parameter로 key와 perPage를 포함해서 전송하며 api로 받은 obj 값을 JSON으로 변경하여 JSONDecoder를 사용해 Center 객체로
            변환 한 다음 Center 객체를 클로저로 전송한다.
        
       + collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int 
        -   vaccineCenter 객체의 개수를 반환한다.
       
       + collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell
        -   cell 세부 내용 구현 함수
            centerLocation 변수에 진료소의 lat, lng를 가져오고 Location 변수에 사용자의 위치를 받아와 거리를 계산하여 label을 변경 하여준다.
        
       + getLocationUsagePermission()
        -   사용자가 위치권한이 허용안함 으로 되어있는 경우 호출되는 함수.
            UIAlertController를 활용하여 제작 하였으며 만약 네 를 누른 경우 설정화면으로 옮겨져 위치권한을 수정할 수 있게 합니다.
        
       + locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation])
        -   위치가 업데이트가 일어나면 호출되는 함수
            currentLocation에 locationManger의 location을 삽입하여줍니다.
        
       + distance(from: CLLocationCoordinate2D) -> CLLocationDistance
        -   from의 위치 값과 현재 자신의 위치값을 계산하여 반환하는 함수이다.
        
       + isOnValueChange(isOn: Bool)
        -   지도와 리스트를 변경하는 버튼을 눌렀을 때 호출되는 함수 이다.
    
    </details>
- NoticeViewController
    <details markdown="1">
    <summary>접기/펼치기</summary>
    
       + startUI()
        -   tableView의 delegate, dataSource를 self로 설정 NoticeAllRequest() 함수를 호출한다.
            반환값이 nil이 아니라면 NoticeList_All에 삽입하여 주고 tableView reload를 진행한다. 
        
       + NoticeAllRequest(complation : ((Notice?) -> ())?)
        -   자체 서버에 저장된 noticeList를 받아오는 api 호출 함수
            api로 받은 obj 값을 JSON으로 변경하여 JSONDecoder를 사용해 Notice 객체로 변환 한 다음 Notice 객체를 클로저로 전송한다.
        
       + numberOfSections(in tableView: UITableView) -> Int   
        -   NoticeList_All 객체의 개수를 반환한다.
        
       + tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int   
        -   tableView를 클릭하면 section의 수를 늘려 펼쳐지는 형식으로 보여줍니다.
        
       + tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat    
        -   indexPath를 기준 제목 부분이 높이를 60 , 세부 내용 부분을 250으로 설정하여줌.
        
       + tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell   
        -   tableView의 cell 세부 내용을 구현하는 함수 indexPath의 row로 제목부분과 컨텐츠 부분으로 분류하여 구현하였음.
            제목 부분 : NoticeList_All에 저장 되어 있는 배열 값을 토대로 제목부분을 줄바꿈 규칙을 한글형식으로 지정.
            컨텐츠 부분 : NoticeList_All에 저장 되어 있는 배열 값을 토대로 title, content을 삽입 하여 cell을 반환. 
        
       + tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath)   
        -   cell을 클릭 시 발생하는 이벤트이며 선택된 셀의 구조는 NoticeTableTitleCell로 하였다.
            선택된 tableView의 Row와 이벤트의 indexPath.row 가 같다면 실행하며 index.row가 0인 경우 NoticeList_All의 open이 true 인 경우(열려있는 경우)
            open을 false로 변경 선택한 section(펼쳐진)을 indexPath.section을 추가 시켜주고 tableView의 Section을 reload 해준다.
            false 인 경우(닫혀 있는 경우) open을 true로 변경 하여 주고 indexPath.section을 추가 시켜주고 tableView의 Section을 reload 해준다. 
            
    </details>
- HowToUseViewController
    <details markdown="1">
    <summary>접기/펼치기</summary>
    
       + pageValueDidChanged()
        -   Pager 이벤트를 구현한 함수
            ScrollBar가 이동된 경우 collectionView의 ViewItem을 옮겨주게끔 만들었음.
        
       + setupCollectionView()
        -   pager Action을 pageValueDidChange() 함수로 추가 해주고, numberOfPages를 imgName의 개수 만큼 반환해준다.
            currentPage를 0으로 설정하여 첫번째 페이지 부터 나오게 설정 한다. CollectionView의 dataSource , delegate를 self로 변경해준다.
            collectionViewLayout을 FlowLayout으로 설정 layout의 아이템 사이즈는 view의 bound size 로 width를 설정한다.
            height의 높이는 height - 220로 설정한다. collectionView의 Layout를 collectionViewLayout로 설정해준다.
        
       + collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int   
        -   imgName 배열 개수로 반환하여준다.
        
       + collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell
        -   cell을 구현하는 함수
            indexPath.row를 기준으로 이미지와 문구를 넣어준다 
        
       + scrollViewWillEndDragging(_ scrollView: UIScrollView, withVelocity velocity: CGPoint, targetContentOffset: UnsafeMutablePointer<CGPoint>)
        -   스크롤이 멈췄을때 사용자가 보고 있는 화면의 collectionView 좌표를 토대로 index를 확인하는 함수
            layout에 collectionView의 collectionViewLayout을 설정해주고 cell의 사이즈를 확인하기 위해 itemSize.width + minimumLineSpacing을
            더하여 측정. estimatedIndex에 contentOffset.x / cellSize 를 하여 현재 사용자가 보고 있는 화면의 인덱스를 계산한다.
            인덱스를 Int형으로 형변환 하여 소수점을 잘라내어 인덱스 확인하여 현재 화면의 인덱스를 확인하여 targetContentOffset에 indexNumber * cell 크기를 
            반환하여 준다.
        
    </details>
- LicenseViewController
    <details markdown="1">
    <summary>접기/펼치기</summary>

    <!--summary 아래 빈칸 공백 두고 내용을 적는공간-->
    
    </details>
- GeneralForumViewController
    <details markdown="1">
    <summary>접기/펼치기</summary>
    
       + StartSetting()
        -   ForumTable의 delegate, dataSource를 self로 설정 ForumTableGet() 함수를 요청하여 반환값이 nil이 아닌 경우
            self.totalPage에 반환값의 totalPage를 넣고 tableContent를 빈값으로 초기화 하여준다.
            그 다음 data를 tableContent에 삽입하고 ForumTable을 reload 한 뒤 pageNum을 +1 하여 저장한다
            WriteButton의 디자인을 설정한다.
        
       + ForumTableGet(complation : ((GeneralForum?) -> ())?)
        -   
        
       + tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
        -   
        
       + tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell   
        -   
        
       + tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat
        -   
        
       + tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath)
        -   
        
       + prepare(for segue: UIStoryboardSegue, sender: Any?)
        -   
        
       + scrollViewDidEndDragging(_ scrollView: UIScrollView, willDecelerate decelerate: Bool)
        -   
        
    </details>
- DetailForumViewController
    <details markdown="1">
    <summary>접기/펼치기</summary>
    
       + initRefresh ()   
        -   
        
       + updateUI (refresh: UIRefreshControl)   
        -   
        
       + DecimalWon (value:Int,cot:Int) , (value:String) , (value:String,cot:Int)   
        -   
        
       + textinput()    
        -   
        
       + COVID_CONNECT (day : Int)   
        -   
        
       + COVID_Desult_CONNECT (day : Int)   
        -   
        
       + parser(_ parser: XMLParser, didStartElement elementName: String, namespaceURI: String?, qualifiedName qName: String?, attributes attributeDict: [String : String] = [:])   
        -   
        
       + parser(_ parser: XMLParser, foundCharacters string: String)   
        -   
        
       + parser(_ parser: XMLParser, didEndElement elementName: String, namespaceURI: String?, qualifiedName qName: String?)   
        -   
        
       + collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize
        - 
       
       + collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int
        -
        
       + collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell
        -
    <!--summary 아래 빈칸 공백 두고 내용을 적는공간-->
    
    </details>
    
----

## 3. 기술 및 라이센스

### iOS에서 사용된 기술 및 라이센스

- Alamofire (라이브러리 HTTP 통신 / JSON 파라미터 인코딩 / URL 인코딩)
- AutoLayout (뷰간 제약조건 설정)
- CoreLocation (GPS 모듈)
- CocoaPods (라이브러리 관리 매니저)
- Codable (프로토콜 변환 / JSON 파싱)
- CollectionView / FlowLayout / TableView / ScrollView (레이아웃 표출 객체)
- RefreshControl (드래그시 새로고침)
- Naver Search API (뉴스 검색 API)
- XML Parser (XML 파싱)
- NMapsMap (Naver 지도 라이브러리)
- SideMenu (사이드 메뉴 라이브러리)
- KeychainSwift (헤더 암호화 라이브러리)

### 라이센스
- MIT License (KeyChainSwift)
> Copyright (c) 2015 - 2019 Evgenii Neumerzhitckii

- MIT License (SideMenu)
> Copyright (c) 2015 Jonathan Kent  [contact@jonkent.me](mailto:contact@jonkent.me) 

- Alamofire
> Copyright (c) 2014-2021 Alamofire Software Foundation (http://alamofire.org/)

-----

### Android에서 사용된 기술 및 라이센스

- 위치정보 라이브러리
> gms:play-services-location 

- HTTP 통신 라이브러리
> okhttp3:okhttp
> okhttp3:logging-interceptor
> retrofit2:retrofit

- GSON 파서
> retrofit2:converter-gson

- XML 파서
> tikxml:annotation
> tikxml:core
> tikxml:retrofit-converter
> tikxml:processor

- 위험 권한
> ted:tedpermission

- 롬복
> projectlombok:lombok

- 인디케이터
> relex:circleindicator

- 위치 주소 변환
> Geocoder

- 뷰 페이저 (화면 전환)
> viewpager2:viewpager2

### 라이센스

- Copyright 2013 Square, Inc. (HTTP 통신)
- Copyright 2013 Square, Inc. (GSON 파서)
- Copyright 2015 Tickaroo (XML 파서)
- Copyright 2021 Ted Park (위험 권한)
- Copyright (C) 2009-2021 The Project Lombok Authors. (lombok)
- Copyright 2018 ongakuer (뷰 페이저 인디케이터)

---

### Front End (Web) 에서 사용된 기술 및 라이센스
- React (UI용 javascript 라이브러리)
- react-router-dom (react routing 라이브러리)
- axios (promise base의 브라우저 및 node.js용 http 클라이언트)
- material-ui (react용 UI 라이브러리)
- styled-components (react용 CSS in JS 라이브러리)

### 라이센스
- Copyright (c) Facebook, Inc. and its affiliates.(React)
- Copyright (c) 2021 Ernesto Garcia (react-router-dom)
- Copyright (c) 2014-present Matt Zabriskie (axios)
- Copyright (c) 2014 Call-Em-All (material-ui)
- Copyright (c) 2016-present Glen Maddern and Maximilian Stoiber (styled-components)

----

### BackEnd에서 사용된 기술 및 라이센스
- Thymeleaf (HTML 템플릿 엔진)
- SpringBoot (Spring 프레임워크 [ JPA(ORM 기술 표준)
- HTTPEntity (Http 프로토콜 활용한 기술) ]
- 서버 :  라즈베리파이 (WAS 스프링부트 내장톰캣) 
- DB: MariaDB

### 라이센스
Apache License Version 2.0 (TomCat)

Copyright (C) 2009-2021 The Project Lombok Authors. (Lombok)

----

### iOS License

#### Alamofire

```
Copyright (c) 2014-2021 Alamofire Software Foundation (http://alamofire.org/)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```


#### KeychainSwift

```
The MIT License

Copyright (c) 2015 - 2019 Evgenii Neumerzhitckii

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```


#### SideMenu

```
Copyright (c) 2015 Jonathan Kent contact@jonkent.me

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Generated by CocoaPods - https://cocoapods.org
```


----

### Android License

#### Retrofit2 

```
Copyright 2013 Square, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

#### Converter-Gson

```
Copyright 2013 Square, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

#### Tickaroo

```
Copyright 2015 Tickaroo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

#### TedPermission

```
Copyright 2021 Ted Park

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

#### Lombok

```
Copyright (C) 2009-2021 The Project Lombok Authors.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```

#### CircleIndicator

```
ongakuer/CircleIndicator is licensed under the

Apache License 2.0
A permissive license whose main conditions require preservation of copyright and license notices. Contributors provide an express grant of patent rights. Licensed works, modifications, and larger works may be distributed under different terms and without source code.

 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/

   TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION

   1. Definitions.

      "License" shall mean the terms and conditions for use, reproduction,
      and distribution as defined by Sections 1 through 9 of this document.

      "Licensor" shall mean the copyright owner or entity authorized by
      the copyright owner that is granting the License.

      "Legal Entity" shall mean the union of the acting entity and all
      other entities that control, are controlled by, or are under common
      control with that entity. For the purposes of this definition,
      "control" means (i) the power, direct or indirect, to cause the
      direction or management of such entity, whether by contract or
      otherwise, or (ii) ownership of fifty percent (50%) or more of the
      outstanding shares, or (iii) beneficial ownership of such entity.

      "You" (or "Your") shall mean an individual or Legal Entity
      exercising permissions granted by this License.

      "Source" form shall mean the preferred form for making modifications,
      including but not limited to software source code, documentation
      source, and configuration files.

      "Object" form shall mean any form resulting from mechanical
      transformation or translation of a Source form, including but
      not limited to compiled object code, generated documentation,
      and conversions to other media types.

      "Work" shall mean the work of authorship, whether in Source or
      Object form, made available under the License, as indicated by a
      copyright notice that is included in or attached to the work
      (an example is provided in the Appendix below).

      "Derivative Works" shall mean any work, whether in Source or Object
      form, that is based on (or derived from) the Work and for which the
      editorial revisions, annotations, elaborations, or other modifications
      represent, as a whole, an original work of authorship. For the purposes
      of this License, Derivative Works shall not include works that remain
      separable from, or merely link (or bind by name) to the interfaces of,
      the Work and Derivative Works thereof.

      "Contribution" shall mean any work of authorship, including
      the original version of the Work and any modifications or additions
      to that Work or Derivative Works thereof, that is intentionally
      submitted to Licensor for inclusion in the Work by the copyright owner
      or by an individual or Legal Entity authorized to submit on behalf of
      the copyright owner. For the purposes of this definition, "submitted"
      means any form of electronic, verbal, or written communication sent
      to the Licensor or its representatives, including but not limited to
      communication on electronic mailing lists, source code control systems,
      and issue tracking systems that are managed by, or on behalf of, the
      Licensor for the purpose of discussing and improving the Work, but
      excluding communication that is conspicuously marked or otherwise
      designated in writing by the copyright owner as "Not a Contribution."

      "Contributor" shall mean Licensor and any individual or Legal Entity
      on behalf of whom a Contribution has been received by Licensor and
      subsequently incorporated within the Work.

   2. Grant of Copyright License. Subject to the terms and conditions of
      this License, each Contributor hereby grants to You a perpetual,
      worldwide, non-exclusive, no-charge, royalty-free, irrevocable
      copyright license to reproduce, prepare Derivative Works of,
      publicly display, publicly perform, sublicense, and distribute the
      Work and such Derivative Works in Source or Object form.

   3. Grant of Patent License. Subject to the terms and conditions of
      this License, each Contributor hereby grants to You a perpetual,
      worldwide, non-exclusive, no-charge, royalty-free, irrevocable
      (except as stated in this section) patent license to make, have made,
      use, offer to sell, sell, import, and otherwise transfer the Work,
      where such license applies only to those patent claims licensable
      by such Contributor that are necessarily infringed by their
      Contribution(s) alone or by combination of their Contribution(s)
      with the Work to which such Contribution(s) was submitted. If You
      institute patent litigation against any entity (including a
      cross-claim or counterclaim in a lawsuit) alleging that the Work
      or a Contribution incorporated within the Work constitutes direct
      or contributory patent infringement, then any patent licenses
      granted to You under this License for that Work shall terminate
      as of the date such litigation is filed.

   4. Redistribution. You may reproduce and distribute copies of the
      Work or Derivative Works thereof in any medium, with or without
      modifications, and in Source or Object form, provided that You
      meet the following conditions:

      (a) You must give any other recipients of the Work or
          Derivative Works a copy of this License; and

      (b) You must cause any modified files to carry prominent notices
          stating that You changed the files; and

      (c) You must retain, in the Source form of any Derivative Works
          that You distribute, all copyright, patent, trademark, and
          attribution notices from the Source form of the Work,
          excluding those notices that do not pertain to any part of
          the Derivative Works; and

      (d) If the Work includes a "NOTICE" text file as part of its
          distribution, then any Derivative Works that You distribute must
          include a readable copy of the attribution notices contained
          within such NOTICE file, excluding those notices that do not
          pertain to any part of the Derivative Works, in at least one
          of the following places: within a NOTICE text file distributed
          as part of the Derivative Works; within the Source form or
          documentation, if provided along with the Derivative Works; or,
          within a display generated by the Derivative Works, if and
          wherever such third-party notices normally appear. The contents
          of the NOTICE file are for informational purposes only and
          do not modify the License. You may add Your own attribution
          notices within Derivative Works that You distribute, alongside
          or as an addendum to the NOTICE text from the Work, provided
          that such additional attribution notices cannot be construed
          as modifying the License.

      You may add Your own copyright statement to Your modifications and
      may provide additional or different license terms and conditions
      for use, reproduction, or distribution of Your modifications, or
      for any such Derivative Works as a whole, provided Your use,
      reproduction, and distribution of the Work otherwise complies with
      the conditions stated in this License.

   5. Submission of Contributions. Unless You explicitly state otherwise,
      any Contribution intentionally submitted for inclusion in the Work
      by You to the Licensor shall be under the terms and conditions of
      this License, without any additional terms or conditions.
      Notwithstanding the above, nothing herein shall supersede or modify
      the terms of any separate license agreement you may have executed
      with Licensor regarding such Contributions.

   6. Trademarks. This License does not grant permission to use the trade
      names, trademarks, service marks, or product names of the Licensor,
      except as required for reasonable and customary use in describing the
      origin of the Work and reproducing the content of the NOTICE file.

   7. Disclaimer of Warranty. Unless required by applicable law or
      agreed to in writing, Licensor provides the Work (and each
      Contributor provides its Contributions) on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
      implied, including, without limitation, any warranties or conditions
      of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A
      PARTICULAR PURPOSE. You are solely responsible for determining the
      appropriateness of using or redistributing the Work and assume any
      risks associated with Your exercise of permissions under this License.

   8. Limitation of Liability. In no event and under no legal theory,
      whether in tort (including negligence), contract, or otherwise,
      unless required by applicable law (such as deliberate and grossly
      negligent acts) or agreed to in writing, shall any Contributor be
      liable to You for damages, including any direct, indirect, special,
      incidental, or consequential damages of any character arising as a
      result of this License or out of the use or inability to use the
      Work (including but not limited to damages for loss of goodwill,
      work stoppage, computer failure or malfunction, or any and all
      other commercial damages or losses), even if such Contributor
      has been advised of the possibility of such damages.

   9. Accepting Warranty or Additional Liability. While redistributing
      the Work or Derivative Works thereof, You may choose to offer,
      and charge a fee for, acceptance of support, warranty, indemnity,
      or other liability obligations and/or rights consistent with this
      License. However, in accepting such obligations, You may act only
      on Your own behalf and on Your sole responsibility, not on behalf
      of any other Contributor, and only if You agree to indemnify,
      defend, and hold each Contributor harmless for any liability
      incurred by, or claims asserted against, such Contributor by reason
      of your accepting any such warranty or additional liability.

   END OF TERMS AND CONDITIONS

   APPENDIX: How to apply the Apache License to your work.

      To apply the Apache License to your work, attach the following
      boilerplate notice, with the fields enclosed by brackets "[]"
      replaced with your own identifying information. (Don't include
      the brackets!)  The text should be enclosed in the appropriate
      comment syntax for the file format. We also recommend that a
      file or class name and description of purpose be included on the
      same "printed page" as the copyright notice for easier
      identification within third-party archives.

   Copyright [yyyy] [name of copyright owner]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

---

### Web License

---

#### React
````
MIT License 

Copyright (c) Facebook, Inc. and its affiliates.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
````

#### react-router-dom

````
MIT License

Copyright (c) 2021 Ernesto Garcia

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
````

#### axios
````
Copyright (c) 2014-present Matt Zabriskie

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
````

#### material-UI
````
The MIT License (MIT)

Copyright (c) 2014 Call-Em-All

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
````

#### styled-components
````
MIT License

Copyright (c) 2016-present Glen Maddern and Maximilian Stoiber

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
````


----

### BackEnd License

---

#### Lombok

````
    Copyright (C) 2009-2021 The Project Lombok Authors.
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
    
    
````


#### JSON

````
    Copyright (c) 2002 JSON.org
     Permission is hereby granted, free of charge, to any person obtaining a copy
     of this software and associated documentation files (the "Software"), to deal
     in the Software without restriction, including without limitation the rights
     to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
     copies of the Software, and to permit persons to whom the Software is
     furnished to do so, subject to the following conditions:
     The above copyright notice and this permission notice shall be included in all
     copies or substantial portions of the Software.
     The Software shall be used for Good, not Evil.
     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
     SOFTWARE.

````


#### JSON-SIMPLE



````
                                     Apache License
                               Version 2.0, January 2004
                            http://www.apache.org/licenses/
    
       TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
    
       1. Definitions.
    
          "License" shall mean the terms and conditions for use, reproduction,
          and distribution as defined by Sections 1 through 9 of this document.
    
          "Licensor" shall mean the copyright owner or entity authorized by
          the copyright owner that is granting the License.
    
          "Legal Entity" shall mean the union of the acting entity and all
          other entities that control, are controlled by, or are under common
          control with that entity. For the purposes of this definition,
          "control" means (i) the power, direct or indirect, to cause the
          direction or management of such entity, whether by contract or
          otherwise, or (ii) ownership of fifty percent (50%) or more of the
          outstanding shares, or (iii) beneficial ownership of such entity.
    
          "You" (or "Your") shall mean an individual or Legal Entity
          exercising permissions granted by this License.
    
          "Source" form shall mean the preferred form for making modifications,
          including but not limited to software source code, documentation
          source, and configuration files.
    
          "Object" form shall mean any form resulting from mechanical
          transformation or translation of a Source form, including but
          not limited to compiled object code, generated documentation,
          and conversions to other media types.
    
          "Work" shall mean the work of authorship, whether in Source or
          Object form, made available under the License, as indicated by a
          copyright notice that is included in or attached to the work
          (an example is provided in the Appendix below).
    
          "Derivative Works" shall mean any work, whether in Source or Object
          form, that is based on (or derived from) the Work and for which the
          editorial revisions, annotations, elaborations, or other modifications
          represent, as a whole, an original work of authorship. For the purposes
          of this License, Derivative Works shall not include works that remain
          separable from, or merely link (or bind by name) to the interfaces of,
          the Work and Derivative Works thereof.
    
          "Contribution" shall mean any work of authorship, including
          the original version of the Work and any modifications or additions
          to that Work or Derivative Works thereof, that is intentionally
          submitted to Licensor for inclusion in the Work by the copyright owner
          or by an individual or Legal Entity authorized to submit on behalf of
          the copyright owner. For the purposes of this definition, "submitted"
          means any form of electronic, verbal, or written communication sent
          to the Licensor or its representatives, including but not limited to
          communication on electronic mailing lists, source code control systems,
          and issue tracking systems that are managed by, or on behalf of, the
          Licensor for the purpose of discussing and improving the Work, but
          excluding communication that is conspicuously marked or otherwise
          designated in writing by the copyright owner as "Not a Contribution."
    
          "Contributor" shall mean Licensor and any individual or Legal Entity
          on behalf of whom a Contribution has been received by Licensor and
          subsequently incorporated within the Work.
    
       2. Grant of Copyright License. Subject to the terms and conditions of
          this License, each Contributor hereby grants to You a perpetual,
          worldwide, non-exclusive, no-charge, royalty-free, irrevocable
          copyright license to reproduce, prepare Derivative Works of,
          publicly display, publicly perform, sublicense, and distribute the
          Work and such Derivative Works in Source or Object form.
    
       3. Grant of Patent License. Subject to the terms and conditions of
          this License, each Contributor hereby grants to You a perpetual,
          worldwide, non-exclusive, no-charge, royalty-free, irrevocable
          (except as stated in this section) patent license to make, have made,
          use, offer to sell, sell, import, and otherwise transfer the Work,
          where such license applies only to those patent claims licensable
          by such Contributor that are necessarily infringed by their
          Contribution(s) alone or by combination of their Contribution(s)
          with the Work to which such Contribution(s) was submitted. If You
          institute patent litigation against any entity (including a
          cross-claim or counterclaim in a lawsuit) alleging that the Work
          or a Contribution incorporated within the Work constitutes direct
          or contributory patent infringement, then any patent licenses
          granted to You under this License for that Work shall terminate
          as of the date such litigation is filed.
    
       4. Redistribution. You may reproduce and distribute copies of the
          Work or Derivative Works thereof in any medium, with or without
          modifications, and in Source or Object form, provided that You
          meet the following conditions:
    
          (a) You must give any other recipients of the Work or
              Derivative Works a copy of this License; and
    
          (b) You must cause any modified files to carry prominent notices
              stating that You changed the files; and
    
          (c) You must retain, in the Source form of any Derivative Works
              that You distribute, all copyright, patent, trademark, and
              attribution notices from the Source form of the Work,
              excluding those notices that do not pertain to any part of
              the Derivative Works; and
    
          (d) If the Work includes a "NOTICE" text file as part of its
              distribution, then any Derivative Works that You distribute must
              include a readable copy of the attribution notices contained
              within such NOTICE file, excluding those notices that do not
              pertain to any part of the Derivative Works, in at least one
              of the following places: within a NOTICE text file distributed
              as part of the Derivative Works; within the Source form or
              documentation, if provided along with the Derivative Works; or,
              within a display generated by the Derivative Works, if and
              wherever such third-party notices normally appear. The contents
              of the NOTICE file are for informational purposes only and
              do not modify the License. You may add Your own attribution
              notices within Derivative Works that You distribute, alongside
              or as an addendum to the NOTICE text from the Work, provided
              that such additional attribution notices cannot be construed
              as modifying the License.
    
          You may add Your own copyright statement to Your modifications and
          may provide additional or different license terms and conditions
          for use, reproduction, or distribution of Your modifications, or
          for any such Derivative Works as a whole, provided Your use,
          reproduction, and distribution of the Work otherwise complies with
          the conditions stated in this License.
    
       5. Submission of Contributions. Unless You explicitly state otherwise,
          any Contribution intentionally submitted for inclusion in the Work
          by You to the Licensor shall be under the terms and conditions of
          this License, without any additional terms or conditions.
          Notwithstanding the above, nothing herein shall supersede or modify
          the terms of any separate license agreement you may have executed
          with Licensor regarding such Contributions.
    
       6. Trademarks. This License does not grant permission to use the trade
          names, trademarks, service marks, or product names of the Licensor,
          except as required for reasonable and customary use in describing the
          origin of the Work and reproducing the content of the NOTICE file.
    
       7. Disclaimer of Warranty. Unless required by applicable law or
          agreed to in writing, Licensor provides the Work (and each
          Contributor provides its Contributions) on an "AS IS" BASIS,
          WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
          implied, including, without limitation, any warranties or conditions
          of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A
          PARTICULAR PURPOSE. You are solely responsible for determining the
          appropriateness of using or redistributing the Work and assume any
          risks associated with Your exercise of permissions under this License.
    
       8. Limitation of Liability. In no event and under no legal theory,
          whether in tort (including negligence), contract, or otherwise,
          unless required by applicable law (such as deliberate and grossly
          negligent acts) or agreed to in writing, shall any Contributor be
          liable to You for damages, including any direct, indirect, special,
          incidental, or consequential damages of any character arising as a
          result of this License or out of the use or inability to use the
          Work (including but not limited to damages for loss of goodwill,
          work stoppage, computer failure or malfunction, or any and all
          other commercial damages or losses), even if such Contributor
          has been advised of the possibility of such damages.
    
       9. Accepting Warranty or Additional Liability. While redistributing
          the Work or Derivative Works thereof, You may choose to offer,
          and charge a fee for, acceptance of support, warranty, indemnity,
          or other liability obligations and/or rights consistent with this
          License. However, in accepting such obligations, You may act only
          on Your own behalf and on Your sole responsibility, not on behalf
          of any other Contributor, and only if You agree to indemnify,
          defend, and hold each Contributor harmless for any liability
          incurred by, or claims asserted against, such Contributor by reason
          of your accepting any such warranty or additional liability.
    
       END OF TERMS AND CONDITIONS
    
       APPENDIX: How to apply the Apache License to your work.
    
          To apply the Apache License to your work, attach the following
          boilerplate notice, with the fields enclosed by brackets "[]"
          replaced with your own identifying information. (Don't include
          the brackets!)  The text should be enclosed in the appropriate
          comment syntax for the file format. We also recommend that a
          file or class name and description of purpose be included on the
          same "printed page" as the copyright notice for easier
          identification within third-party archives.
    
       Copyright [yyyy] [name of copyright owner]
    
       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at
    
           http://www.apache.org/licenses/LICENSE-2.0
    
       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.
       
       ````



