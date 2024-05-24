# 📆투두앱 백엔드 서버 만들기

Spring 과제로 투두앱 백엔드 서버를 만들었습니다. <br/>

제목과 내용을 적으면 할 일 카드가 생성되도록 만들었습니다. <br/>

할 일 카드에 대해서는 조회, 수정, 삭제가 가능하도록 만들었습니다.

## 목차
- [요구 사항](#요구-사항)
- [WHY?](#why)
- [기획 및 설계](#기획-및-설계)
- [코드 구조](#코드-구조)

## 요구 사항

<details>
<summary>STEP 1</summary><div>
  
**할 일 카드 작성 기능**
  > - `할 일 제목`, `할 일 내용`, `작성일`, `작성자 이름`을 입력받아 저장할 수 있습니다.
  > - 저장된 할 일의 정보를 반환받아 확인할 수 있습니다.

**선택한 할 일 조회 기능**
  > - 선택한 할 일의 정보를 조회할 수 있습니다.
  > - 반환받은 할 일 정보에는 `할 일 제목`, `할 일 내용`, `작성일`, `작성자 이름`을 정보가 들어있습니다.

**할 일 카드 목록 조회 기능**
  > - 등록된 할 일 전체를 조회할 수 있습니다.
  > - 조회된 할 일 목록은 작성일 기준 내림차순으로 정렬되어 있습니다.

**선택한 할 일 수정 기능**
  > - 선택한 할 일의 `할 일 제목`, `할 일 내용`, `작성자 이름`을 수정할 수 있습니다.
  > - 수정된 할 일의 정보를 반환받아 확인할 수 있습니다.

**선택한 할 일 삭제 기능**
  > - 선택한 게시글을 삭제할 수 있습니다.

</div></details>

<details>
<summary>STEP 2</summary><div>

**할 일 카드 완료 기능**
  > - 완료 처리할 할 일 카드는 목록 조회 시 `완료 여부` 필드가 `TRUE`로 내려갑니다.
  > - `완료 여부` 기본 값은 `FALSE`입니다.

**댓글 작성 기능**
  > - `작성자 이름`, `비밀번호`, `댓글`을 입력받아 저장할 수 있습니다.
  > - 응답에서 `비밀번호`는 제외하고 등록된 댓글을 반환합니다.

**댓글 수정 기능**
  > - `작성자 이름`, `비밀번호`를 입력받아 저장된 값과 일치하면 수정할 수 있습니다.
  > - 응답에서 `비밀번호`는 제외하고 수정된 댓글을 반환합니다.

**댓글 삭제 기능**
  > - `작성자 이름`, `비밀번호`를 입력받아 저장된 값과 일치하면 삭제할 수 있습니다.
  > - 응답에서 삭제 메시지와 상태 코드를 반환합니다.

**댓글 조회 기능**
  > - STEP 1에서 만든 할 일 조회 API의 응답에서 댓글을 조회할 수 있습니다.
  > - 연관되지 않은 댓글은 포함되지 않아야 합니다. 

</div></details>

<details>
<summary>STEP 3</summary><div>
  
**할 일 카드 정렬 기능**
  > - API를 요청할 때 정렬(`오름차순`, `내림차순`)을 포함합니다.
  > - 정렬 기준을 통해 정렬한 할 일 목록을 반환합니다.

**할 일 카드 필터 기능**
  > - API를 요청할 때 작성자 이름 기준으로 목록을 반환합니다.

**할 일 카드 검증 기능**
  > - 할 일 카드를 작성하거나 수정할 때 검사합니다.
  > - 할 일 카드의 제목이 1자 이상, 200자 이하인지 검사합니다.
  > - 할 일 카드의 본문이 1자 이상, 1000자 이하인지 검사합니다.
  > - 조건을 충족하지 않는다면 기능 실패로 응답합니다.

**할 일 카드 응답 코드 반환**
  > - ReponseEntity를 사용하여 API의 응답을 Status 코드로 반환합니다.
  > - 조회 기능 성공: status 200 OK
  > - 작성 기능 성공: status 201 Created
  > - 수정 기능 성공: status 200 OK
  > - 삭제 기능 성공: status 204 No Content
  > - 작성, 수정 기능 실패: status 400 Bad Request

</div></details>

<details>
<summary>STEP 4</summary><div>
  
**개발 완료**
  > - 사용자가 많아졌을 때 페이지 혹은 카드의 개수를 지정해서 조회합니다.
  > - 회원가입, 로그인이 가능합니다.

**추후 개발 예정**
> - 인증, 인가에 대해서 요청한 사용자를 구분하여 수정 삭제가 가능합니다.

</div></details>

## WHY?

<details>
<summary>STEP 1</summary><div>
  
Q1. API의 request를 어떤 방식으로 사용하셨나요?
> A. Request Body 방식을 사용하였습니다. 이유는 다음과 같습니다.<br/>
>
> 1. Request Body 방식이 URL에 JSON 등의 데이터를 노출하지 않기 때문에 안전하기 때문입니다.<br/>
> 2. 주소를 이용하지 않아서 데이터양이 많은 경우에 적합하기 때문입니다.

Q2. RESTful 한 API를 설계하셨나요?
> A. 네, 그렇습니다. 이유는 다음과 같습니다. <br/>
>
> 1. Resource의 이름을 명사, 소문자, 복수형으로 지향하였으며, '/'를 통해 계층 관계를 표현하였습니다. <br/>
  (예> /todocards) <br/>
> 2. id Resource를 가져오기 위해 Identifier를 포함하였습니다. <br/>
  (예> /todocards/{userId}) <br/>
> 3. 적절한 Status Code를 응답하였습니다. <br/>
  (예> TodoCard가 정상적으로 생성되었을 때, 성공 Status Code가 201) <br/>
> 4. Path Variable을 이용하여 변수를 받아오도록 하였습니다. <br/>
  (예> fun getTodoCard(@PathVariable userid: Long))

Q3. 적절한 관심사 분리를 적용하셨나요?<br/>
> A. Spring의 Layer 구조와 DB에 맞추어 패키지를 나누었습니다. (Controller , Dto , model , repository , Service)

Q4. API 명세서 작성 가이드라인과 비교했을 때 자신의 API 명세서<br/>
> A. 규모가 큰 프로젝트가 아니라서 제외한 항목이 많았던 것 같습니다.

</div></details>

<details>
<summary>STEP 2</summary><div>

Q1. 처음 설계한 API 명세서에 변경사항이 있었나요? 변경되었다면 어떤 점 때문일까요?
> A. 댓글 기능에 대한 추가 사항이 존재하였습니다. 기능을 늘리면 자연스럽게 API 명세서도 늘어났다고 생각합니다.<br/>

Q2. 첫 설계의 중요성에 대해 작성해주세요!
> A. 첫 설계를 디테일하게 할수록 추가 요구사항을 적용할 때 변경에 대한 수고가 줄어든다는 것을 알게 되었습니다.<br/>

Q3. ERD를 먼저 설계한 후 Entity를 개발했을 때 어떤 점이 도움이 되셨나요?
> A. 테이블명과 칼럼 이름을 미리 정해두고, 연관 관계를 설정해두어서 코드 동작 설계를 편하게 할 수 있었습니다.<br/>

Q4. 댓글 여러 개 달려있는 할 일을 삭제하려고 한다면 무슨 문제가 발생할까요? DB 테이블 관점에서 해결 방법이 무엇일까요?
> A. 서버 오류가 발생하며 삭제 동작이 진행되지 않습니다.<br/>
> 하지만 할 일을 삭제할 때 댓글까지 삭제할 수 있게 CASCADE라는 영속성 전이를 이용하면 해결 가능합니다.<br/>

Q4. IoC / DI에 대해 간략하게 설명해 주세요.
> A. 간략하게 설명하도록 하겠습니다.<br/>
> * IoC : 객체의 생성과 생명 주기를 외부에서 제어하는 디자인 패턴<br/>
> * DI : 객체가 필요한 의존성을 자체적으로 생성하는 것이 아니라, 외부에서 주입받는 디자인 패턴<br/>
>
> ∴ DI ⊂ IoC

</div></details>

## 기획 및 설계

<details>
<summary>STEP 1</summary><div>
  
#### 1. Event Storming

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/e6a10243-5c12-4dbd-931d-1ede39275e36)

#### 2. Use Case Diagram

![20240516_172800](https://github.com/KangBaekho10/TodoApplication/assets/166815465/a9db859b-d5ee-4b4c-aaac-08907ec0e492)

#### 3. API Specification

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/dd4159ce-835f-4eeb-bd0e-7d0739b8d652)

#### 4. ERD

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/f06de950-2c98-4c3d-a748-27a5995d4af8)

</div></details>

<details>
<summary>STEP 2, 3</summary><div>

#### 1. Event Storming

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/5c7dbfdb-3cc4-498a-aab4-4cde2d31902c)

#### 2. Use Case Diagram

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/2aed2a28-08bd-403a-a052-859e5cfc5086)

#### 3. API Specification

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/acacbbb5-4fe8-4158-8ae5-9583fde05b4a)

#### 4. ERD

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/660fa781-6850-45bb-a446-e4252776c1c8)

</div></details>

<details>
<summary>STEP 4</summary><div>

#### 1. Event Storming

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/a90fb0c5-b9fc-43c1-8f36-8c2338f96912)

#### 2. Use Case Diagram

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/2ae4bff6-4df0-4ae2-be77-4597aa227e6b)

#### 3. API Specification

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/bab663e3-3dcb-4916-b8d4-f73f089f1336)

#### 4. ERD
  
![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/bdd9230e-fd34-4268-9c22-8ec94a6cbde2)

</div></details>

## 코드 구조

할 일 카드에 대한 `TodoCard`와 댓글 대한 `Comment`로 API를 나누었습니다.

Spring의 Layer 구조와 DB에 맞추어 패키지를 `Controller` , `Dto` , `Service` , `Repository`, `Model`로 나누었습니다.

- 동작 과정

```Kotlin
1) Web Layer에 해당하는 'Controller'에서 Client로부터 Request 받는다.

2) Request에 맞는 함수를 'Dto'에서 찾아 Service Layer에 해당하는 'Service'로 넘겨준다.

3) 'Service'에서는 Request에 대한 실제 동작이 이루어진다. (삽입, 수정, 삭제, 조회)

4) 'Service'는 Entity를 통해 동작한 Data를 Repository Layer에 해당하는 'Repository'로 넘겨준다.

5) 'Repository'는 'Model'과 직접 연결되어 있고, 'Model'은 Repository에 의해 넘겨받은 Data를 DB에서 동작한다.

6) 동작한 내용은 다시 역순으로 진행하고, Web Layer를 통해 Client에게 Response 해준다.
```

<details>
<summary> TodoCard </summary><div>

- Controller

```Kotlin

// 단일 카드 조회
fun getTodoCard(@PathVariable userId: Long) : ResponseEntity<TodoCardResponse> {
...
}

// 전체 카드 조회
fun getTodoCardList(): ResponseEntity<List<TodoCardResponse>> {
...
}

// 할 일 카드 생성
fun createTodoCard(@RequestBody createTodoCardRequest: CreateTodoCardRequest): ResponseEntity<TodoCardResponse> {
...
}

// 할 일 카드 수정
fun updateTodoCard(
    @PathVariable userId: Long,
    @RequestBody updateTodoCardRequest: UpdateTodoCardRequest
) : ResponseEntity<TodoCardResponse> {
...
}

// 할 일 카드 삭제
fun deleteTodoCard(@PathVariable userId: Long) : ResponseEntity<Unit> {
...
}

```

- Service

```Kotlin

// 단일 카드 조회
fun getTodoCardById(userId: Long): TodoCardResponse

// 전체 카드 조회
fun getAllTodoCardList(): List<TodoCardResponse>

// 할 일 카드 생성
fun createTodoCard(request: CreateTodoCardRequest): TodoCardResponse

// 할 일 카드 수정
fun updateTodoCard(userId: Long, request: UpdateTodoCardRequest): TodoCardResponse

// 할 일 카드 삭제
fun deleteTodoCard(userId: Long)

```

- Repository

```Kotlin

interface TodoCardRepository: JpaRepository<TodoCard, Long> {}

```

- Model

```Kotlin
...
// 1:N
// DATA에 맞는 DB Column을 지정
class TodoCard (
...
    @OneToMany(mappedBy = "todoCard", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comment: MutableList<Comment> = mutableListOf()
...
)

// Column 일치하는 곳에 DATA 삽입
fun TodoCard.toResponse(): TodoCardResponse { 
...
}

```

</div></details>

<details>
<summary> Comment </summary><div>

- Controller

```Kotlin

// 댓글 생성
fun createComment(
  @PathVariable userId: Long,
  @RequestBody commentRequest: CommentRequest
): ResponseEntity<CommentResponse> {
...
}

// 댓글 수정
fun updateComment(
  @PathVariable userId: Long,
  @PathVariable commentId: Long,
  @RequestBody commentRequest: CommentRequest
): ResponseEntity<CommentResponse> {
...
}

// 댓글 삭제
fun updateComment(
  @PathVariable userId: Long,
  @PathVariable commentId: Long,
  @RequestBody commentRequest: CommentRequest
): ResponseEntity<CommentResponse> {
...
}

```

- Service

```Kotlin

// 댓글 조회 (할 일 카드에서 조회 가능)
fun getComment(commentId : Long) : CommentResponse

// userId로 할 일 카드를 지정하여 댓글 생성
fun createComment(userId: Long, request: CommentRequest) : CommentResponse

// userId로 할 일 카드를 지정하여 댓글 수정
fun updateComment(userId: Long, commentId: Long, request: CommentRequest) : CommentResponse

// userId로 할 일 카드를 지정하여 댓글 삭제
fun deleteComment(userId: Long, commentId: Long, request: DeleteCommentRequest)

```

- Repository

```Kotlin

interface CommentRepository: JpaRepository<Comment, Long> {
    fun findByTodoCardUseridAndCommentid(userId: Long, commentId: Long): Comment?
}

```

- Model

```Kotlin

class Comment (
...
// N:1
// DATA에 맞는 DB Column 지정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    val todoCard: TodoCard,
...
)
// Column 일치하는 곳에 DATA 삽입
fun Comment.toResponse(): CommentResponse{
...
}

```

</div></details>

<details>
<summary> User </summary><div>

- Controller

```Kotlin

@PostMapping("/signup")
fun createUser(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
...
}
// 사용자 생성
...
fun login(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse>{
...
}
// 사용자 로그인

```

- Service

```Kotlin

fun createUser(request: UserRequest): UserResponse
//사용자 생성

fun login(request: UserRequest): UserResponse
// 사용자 로그인

```

- Repository

```Kotlin

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmailAndPassword(email: String, password: String): User
}

```

- Model

```Kotlin

class User(
)
...
// Column 일치하는 곳에 DATA 삽입
fun User.toResponse(): UserResponse {
...
}

```

</div></details>

## 환경 설정<br>
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) 
![Jdk17](https://img.shields.io/badge/jdk17-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"/)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
