# 📗 [Inflearn] 알기 쉬운 Modern Android Development 입문 From. 냉동코더

## Section 0. 들어가기
#### Architecture Pattern과 Android App Architecture
* 아키텍쳐 패턴, 앱 아키텍쳐는 디자인 패턴(MVC, MVP, MVVM) 용어와 유사하다.
* 프로젝트의 규모가 커짐에 따라 약속된 효율적인 개발 방식이 필요했고, 앱의 구성요소를 UI(View), Controller(ViewModel), Data(Model)로 분리하게 되었다.
* 해당 프로젝트에서는 MVVM 패턴과 SAA(Single Activity Architecture)를 준수한다.

#### Support Library와 AndroidX와 Jetpack
* Android Support Library의 여러 문제(최소 API, 버전 통일 이슈 등)를 해결하기 위해 등장한 AndroidX
* Support Library는 AndroidX 패키지로 완전히 대체, 기존에 사용 중인 <u>com.android.support</u> 라이브러리는 <u>androidx.*</u> 로 마이그레이션 필요
* Jetpack은 <u>androidx.*</u> 패키지에 구성되며 개발자가 일관된 코드를 작성할 수 있도록 돕는 라이브러리

## Section 1. Android Architecture Components 이론
#### ViewBinding 기초
* 모듈에서 설정된 각 XML 레이아웃 파일의 Binding 클래스를 생성해 클래스를 통해 대응되는 레이아웃의 뷰 ID에 직접참조를 가능하게 해줌
* ~~findViewById -> Kotlin Synthetic~~ -> ViewBinding (Android Studio 4.1 버전부터 권장됨)
* 뷰의 ID를 부여하는 과정에서 레이아웃에서 Id와 Type을 혼동할 수 있는 우려가 있지만 ViewBinding을 사용함으로써 Null-Safe, Type-Safe를 지킬 수 있다.

#### ViewModel과 Lifecycle 기초
* MVVM에서 ViewModel은 View에서 Model의 데이터에 접근할 수 있도록 View와 Model 사이를 매개하는 역할을 한다.
* ViewModel의 Lifecycle(생명주기)은 ViewModel이 속해있는 Activity나 Fragment의 생명주기를 따릅니다.

#### LiveData와 Observer Pattern 기초
* LiveData는 수명주기를 인식할 수 있는 관찰 가능한 홀더 클래스이다.
* LiveData 값의 변화를 Observer 객체를 통해 관찰 및 수정
* LiveData를 사용함으로써 UI와 데이터 상태의 일치 보장, 메모리 누수 및 비정상 종료 없음, 최신 데이터 유지 등과 같은 장점

#### DataBinding 기초

#### Repository Pattern 기초

## Section 2. 책 검색 앱 만들기
#### Practice2A-앱 초기구조 작성하기 (View Binding, BottomNavigationVIew)

#### Practice2B-Retrofit으로 카카오 책 검색 API 다루기 (Retrofit, Moshi, OkHttp, Kapt)

#### Practice2C-Android App Architecture 기반 구축하기 (Repository, ViewModel, Coroutine, Livedata)

#### Practice2D-검색결과를 UI에 표시하기 (Coil, ListAdapter, SaveStateHandle)

## Section 3. 보강 이론
#### 안드로이드의 HTTP 통신

#### Data Class 기초

#### Singleton Pattern 기초

#### Coroutine 기초

#### ListAdapter 기초

## Section 4. Jetpack Navigation
#### Navigation 기초
#### Practice4A-Navigation으로 UI의 화면전환 구현하기 (BottomNavigationView, AppBar)
#### Practice4B-Safe args로 프래그먼트간 데이터 전달하기 (SafeArgs, Parcelable, WebView)

## Section 5. Jetpack Room
#### SQLite와 Room 기초
#### Practice5A-검색결과 저장을 위한 Room DB 구현하기 (Entity, Dao, Database, TypeConverter)
#### Practice5B-Room DB를 UI와 연동하기 (RecyclerView, ClickListener, SimpleCallback)

## Section 6. Kotlin Flow
#### Flow 기초
#### Practice6-Room 응답을 flow로 변환하기 (Flow, StateFlow, Extension functions)

## Section 7. Jetpack DataStore


## Section 8. Jetpack Paging



## Section 9. Jetpack WorkManager



## Section 10. Dagger-Hilt



## Section 11. Gradle with Kotlin script



