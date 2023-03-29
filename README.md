# [Inflearn] 알기 쉬운 Modern Android Development 입문 From. 냉동코더

## Section 0. 들어가기
#### Architecture Pattern과 Android App Architecture

#### Support Library와 AndroidX와 Jetpack
* Android Support Library의 여러 문제(최소 API, 버전 통일 이슈 등)를 해결하기 위해 등장한 AndroidX
* Support Library는 AndroidX 패키지로 완전히 대체, 기존에 사용 중인 <u>com.android.support</u> 라이브러리는 <u>androidx.*</u> 로 마이그레이션 필요
* Jetpack은 <u>androidx.*</u> 패키지에 구성되며 개발자가 일관된 코드를 작성할 수 있도록 돕는 라이브러리

## Section 1. Android Architecture Components 이론
#### ViewBinding 기초
* 모듈에서 설정된 각 XML 레이아웃 파일의 Binding 클래스를 생성해 클래스를 통해 대응되는 레이아웃의 뷰 ID에 직접참조를 가능하게 해줌
* ~~findViewById -> Kotlin Synthetic~~ -> ViewBinding 
* 뷰의 ID를 부여하는 과정에서 레이아웃에서 Id와 Type을 혼동할 수 있는 우려가 있지만 ViewBinding을 사용함으로써 Null-Safe, Type-Safe를 지킬 수 있다.
#### ViewModel과 Lifecycle 기초

#### LiveData와 Observer Pattern 기초

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



## Section 5. Jetpack Room


## Section 6. Kotlin Flow



## Section 7. Jetpack DataStore


## Section 8. Jetpack Paging



## Section 9. Jetpack WorkManager



## Section 10. Dagger-Hilt



## Section 11. Gradle with Kotlin script



