# 📗 [Inflearn] 알기 쉬운 Modern Android Development 입문 From. 냉동코더

<img src="https://user-images.githubusercontent.com/98886487/231916048-4e45406a-d7ce-420f-b5c1-0a7434ea4b43.png" width="300" height="300" /> 

<img src="https://user-images.githubusercontent.com/98886487/236624051-022b0f79-f114-4199-82bb-dc11aa2d86ac.gif" width="200" height="400" />


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
* ~~findViewById -> Kotlin Synthetic~~ -> ViewBinding (Android Studio 4.1 버전부터 강제됨)
* 뷰의 ID를 부여하는 과정에서 레이아웃에서 Id와 Type을 혼동할 수 있는 우려가 있지만 ViewBinding을 사용함으로써 Null-Safe, Type-Safe를 지킬 수 있다.

#### ViewModel과 Lifecycle 기초
* MVVM에서 ViewModel은 View에서 Model의 데이터에 접근할 수 있도록 View와 Model 사이를 매개하는 역할을 한다.
* ViewModel의 Lifecycle(생명주기)은 ViewModel이 속해있는 Activity나 Fragment의 생명주기를 따릅니다.

#### LiveData와 Observer Pattern 기초
* LiveData는 수명주기를 인식할 수 있는 관찰 가능한 홀더 클래스이다.
* LiveData 값의 변화를 Observer 객체를 통해 관찰 및 수정
* LiveData를 사용함으로써 UI와 데이터 상태의 일치 보장, 메모리 누수 및 비정상 종료 없음, 최신 데이터 유지 등과 같은 장점

#### DataBinding 기초
* 클래스 파일에서 UI와 데이터를 연결하던 방식에서 뷰가 있는 xml에서 직접 데이터에 접근하여 UI를 구성하는 방법

#### Repository Pattern 기초
* 데이터의 출처에 관계 없이 동일한 인터페이스로 데이터와 데이터 소스에 접근하기 위한 패턴
* ViewModel에서는 Repository에 접근하고 Repository에서 DataLayer에 접근하며 DataLayer를 캡슐화시키는 것이 주목적으로 이해할 수 있다.

## Section 2. 책 검색 앱 만들기
#### [완료] Practice2A-앱 초기구조 작성하기 (ViewBinding, BottomNavigationVIew)
#### [완료] Practice2B-Retrofit으로 카카오 책 검색 API 다루기 (Retrofit, Moshi, OkHttp, Kapt)
#### [완료] Practice2C-Android App Architecture 기반 구축하기 (Repository, ViewModel, Coroutine, Livedata)
#### [완료] Practice2D-검색결과를 UI에 표시하기 (Coil, ListAdapter, SaveStateHandle)

## Section 3. 보강 이론
#### 안드로이드의 HTTP 통신
#### Data Class 기초
#### Singleton Pattern 기초
#### Coroutine 기초
#### ListAdapter 기초

## Section 4. Jetpack Navigation
#### Navigation 기초
* graph와 NavController 객체를 통해 프래그먼트 간의 이동을 관리하는 구성요소
* Safe args를 사용해 유형 안전성을 갖춘 화면 간에 데이터 전달
#### [완료] Practice4A-Navigation으로 UI의 화면전환 구현하기 (BottomNavigationView, AppBar)
#### [완료] Practice4B-Safe args로 프래그먼트간 데이터 전달하기 (SafeArgs, Parcelable, WebView)

## Section 5. Jetpack Room
#### SQLite와 Room 기초
* SQLite를 활용해서 기기 자체의 내부 저장소(로컬 데이터베이스)에 접근하는 라이브러리
* Room은 기기가 네트워크에 엑세스할 수 없는 오프라인 상태에서도 콘텐츠를 탐색할 수 있으며 다시 기기가 온라인 상태가 되면 변경사항이 DB에 동기화된다는 특징이 있다.
* AAC(Android Architecture Component)의 다른 컴포넌트들(ViewModel, LiveData, Databinding...)과 함께 많이 사용된다.
#### [완료] Practice5A-검색결과 저장을 위한 Room DB 구현하기 (Entity, Dao, Database, TypeConverter)
#### [완료] Practice5B-Room DB를 UI와 연동하기 (RecyclerView, ClickListener, SimpleCallback)

## Section 6. Kotlin Flow
#### Flow 기초
* 데이터 생산자에서 데이터 소비자로 데이터가 이동하는 과정을 데이터 스트림이라고 한다. 이 과정을 코틀린에서 Flow라고 한다.
* 비동기적으로 작동하기 때문에 Coroutine과 함께 사용되며 순차적으로 값을 배출해 완료/에러를 반환한다.
#### [완료] Practice6-Room 응답을 flow로 변환하기 (Flow, StateFlow, Extension functions)

## Section 7. Jetpack DataStore
#### DataStore 기초
* 간단한 데이터나 앱의 단순 환경설정 등을 Key-Value 쌍으로 저장하는 데이터 저장소 솔루션 (SharedPreference를 대체하기 위해 등장)
* Coroutine과 Flow를 사용해 비동기 처리가 가능하며 일관된 트랜잭션 방식으로 접근한다는 장점
* 복잡한 대규모의 데이터를 관리할 때에는 DataStore 대신 Room 사용 권장
#### [완료] Practice7-세팅화면 구현하기 (Preferences DataStore, Enum)

## Section 8. Jetpack Paging
#### Paging 기초
* 방대한 양의 데이터를 일정한 사이즈로 나누어 가져오는 라이브러리
* 페이징 방식을 사용하여 리소스, 성능, 메모리, 비용 측면에서 매우 효율적이라는 장점이 있다.
#### [완료] Practice8A-Room 응답에 Paging 적용하기 (Pager, PagingDataAdapter)
#### [완료] Practice8B-Retrofit 응답에 Paging 적용하기 (PagingSource)
#### [완료] Practice8C-네트워크 로딩 상태 핸들링하기 (LoadState, LoadStateAdapter)

## Section 9. Jetpack WorkManager
#### WorkManager 기초
* 정기적인 백그라운드 작업시에 사용되는 API (SDK 26 이상부터 강제됨)
#### [완료] Practice9-자동 캐시 정리 구현하기 (Worker, WorkRequest, WorkInfo)

## Section 10. Dagger-Hilt
#### Dependency Injection 및 Hilt 기초
* Dagger 기반의 Android 환경에서 최적화된 의존성 주입(Dipendency Injection) 라이브러리
* 객체들간의 의존성을 줄이고, 유연한 코드 작성을 위한 설계 패턴
* 
#### [완료] Practice10A-Hilt 의존성 작성하기 (Module, Provides, Binds)
#### [완료] Practice10B-Hilt 의존성 주입하기 (Inject constructor, HiltWorker)
#### [완료] Practice10C-관심사에 따라 ViewModel 분리하기 (by viewModels)

## Section 11. Gradle with Kotlin script
#### Build Tool과 Android Gradle Plugin
* Gradle에 KTS(Kotlin Script)를 적용해 향상된 편집 환경
* 빌드시 Groovy DSL보다는 속도는 느리지만 KTS를 사용할 때 개선점이 더 많기 때문에 앞으로 더 유망할 것.
#### Practice11-build.gradle을 KTS로 마이그레이션하기 (KTS, buildSrc)

## Section 12. 마치며
#### MAD Score
* ~~Jetpack 라이브러리 사용 정도에 따른 점수 부여(지원중단)~~

## Section 13. Appendix. Jetpack Test
#### 안드로이드 테스트 자동화 기초
#### 

