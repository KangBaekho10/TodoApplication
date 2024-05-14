# 📆투두앱 백엔드 서버 만들기

Spring 과제로 투두앱 백엔드 서버를 만들었습니다. <br/>

제목과 내용을 적으면 할 일 카드가 생성되도록 만들었습니다. <br/>

할 일 카드에 대해서는 조회, 수정, 삭제가 가능하도록 만들었습니다.

### 목차
- [요구 사항](#요구-사항)
- [WHY?](#why)
- [기획 및 설계](#기획-및-설계)

## 요구 사항
  > **할 일 카드 작성 기능**
  > - `할 일 제목`, `할 일 내용`, `작성일`, `작성자 이름`을 저장할 수 있습니다.
  > - 저장된 할 일의 정보를 반환 받아 확인할 수 있습니다.

  > **선택한 할 일 조회 기능**
  > - 선택한 할 일의 정보를 조회할 수 있습니다.
  > - 반환 받은 할 일 정보에는 `할 일 제목`, `할 일 내용`, `작성일`, `작성자 이름`을 정보가 들어있습니다.

  > **할 일 카드 목록 조회 기능**
  > - 등록된 할 일 전체를 조회할 수 있습니다.
  > - 조회된 할 일 목록은 작성일 기준 내림차순으로 정렬 되어있습니다.

  > **선택할 할 일 수정 기능**
  > - 선택한 할 일의 `할 일 제목`, `할 일 내용`, `작성자 이름`을 수정할 수 있습니다.
  > - 수정된 할 이르이 정보를 반환 받아 확인할 수 있습니다.

  > **선택할 할 일 삭제 기능**
  > - 선택한 게시글을 삭제할 수 있습니다.

## WHY?
<details>
  <summary></summary> 
  
### Q1. API의 request를 어떤 방식으로 사용 하셨나요?
> A. Request Body 방식을 사용하였습니다. 이유는 다음과 같습니다.<br/>
>
> 1. Request Body 방식이 URL에 JSON 등의 데이터를 노출하지 않기 때문에 안전기 때문입니다.<br/>
> 2. 주소를 이용하지 않아서 데이터 양이 많은 경우에 적합하기 때문입니다.

### Q2. RESTful한 API를 설계 하셨나요?
> A. 네, 그렇습니다. 이유는 다음과 같습니다.<br/>
>
> 1. Resource의 이름을 명사, 소문자, 복수형으로 지향하였으며, '/'를 통해 계층 관계를 표현하였습니다. (예> /todocards)<br/>
> 2. id Resource를 가져오기 위해 Identifier를 포함하였습니다. (예> /todocards/{userId})<br/>
> 3. 적절한 Status Code를 응답하였습니다. (예> TodoCard가 정상적으로 생성되었을 때, 성공 Status Code가 201)<br/>
> 4. Path Variable을 이용하여 변수를 받아오도록 하였습니다. (예> fun getTodoCard(@PathVariable userid: Long))


### Q3. 적절한 관심사 분리를 적용하셨나요?<br/>
> A. Spring의 Layer 구조와 DB에 맞추어 패키지를 나누었습니다. (Controller , Dto , model , repository , Service)

### Q4. API 명세서 작성 가이드라인과 비교했을때 자신의 API 명세서<br/>
> A. 규모가 큰 프로젝트가 아니라서 제외시킨 항목이 많았던 것 같습니다.

</details>

## 기획 및 설계

<details>
  <summary></summary> 
  
#### 1. Event Storming
     
![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/e6a10243-5c12-4dbd-931d-1ede39275e36)

#### 2. Use Case Diagram

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/d6156bca-3de1-4126-8b25-5cfbd2a39c0c)

#### 3. API Specification

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/dd4159ce-835f-4eeb-bd0e-7d0739b8d652)

#### 4. ERD

![image](https://github.com/KangBaekho10/TodoApplication/assets/166815465/f06de950-2c98-4c3d-a748-27a5995d4af8)

</details>

## 코드 구조












