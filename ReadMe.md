# Schedule

## 필수 과제

### API

<div style="overflow-x: auto;">

| **Method** | **Endpoint**         | **Description**                     | **Parameters**                                                                            | **Request Body**                                                                                                 | **Response**                                                                                                                                     | **Status Code** |
|------------|----------------------|-------------------------------------|-------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|-----------------|
| `POST`     | `/schedules`         | 일정 생성                           | 없음                                                                                        | `{ "task": string, "password": string, "memberName": string }`                                                   | `{ "id": long, "task": string, "password": string, "memberName": string, "createdAt": string, "updatedAt": string }`                              | `200 OK`        |
| `GET`      | `/schedules`         | 일정 목록 조회                      | Query: <br> - `updatedDate` (예: "2025-02-02", `optional`)<br> - `memberName` (`optional`) | 없음                                                                                                             | `[ { "id": long, "task": string, "password": string, "memberName": string, "createdAt": string, "updatedAt": string }, ... ]`                      | `200 OK`        |
| `GET`      | `/schedules/{id}`    | 일정 단건 조회                       | Path: <br> - `id`                                                                         | 없음                                                                                                             | `{ "id": long, "task": string, "password": string, "memberName": string, "createdAt": string, "updatedAt": string }`                               | `200 OK`        |
| `PUT`      | `/schedules/{id}`    | 일정 수정                            | Path: <br> - `id`                                                                         | `{ "task": string, "password": string, "memberName": string }`                                                   | `{ "id": long, "task": string, "password": string, "memberName": string, "createdAt": string, "updatedAt": string }`                               | `200 OK`        |
| `DELETE`   | `/schedules/{id}`    | 일정 삭제                           | Path: <br> - `id` <br> Query: <br> - `password` (`mandatory`)                              | 없음                                                                                                             | 없음                                                                                                                                             | `200 OK`        |

</div>

<br>

### SQL

```sql
CREATE TABLE schedule (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      task VARCHAR(255) NOT NULL,
      password VARCHAR(255) NOT NULL,
      member_name VARCHAR(255) NOT NULL,
      created_at DATETIME NOT NULL,
      updated_at DATETIME NOT NULL
)
```
### ERD!
![제목 없음.jpg](%EC%A0%9C%EB%AA%A9%20%EC%97%86%EC%9D%8C.jpg)