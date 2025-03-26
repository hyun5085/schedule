# Schedule

## 필수 과제

### API

<div style="overflow-x: auto;">

| **Method** | **Endpoint**         | **Description**                     | **Parameters**                                                                            | **Request Body**                                                                                                 | **Response**                                                                                                                                     | **Status Code** |
|------------|----------------------|-------------------------------------|-------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|-----------------|
| `POST`     | `/schedules`         | 일정 생성                           | 없음                                                                                        | `{ "scheduleTodo": string, "schedulePassword": string, "scheduleWriter": string }`                                                   | `{ "scheduleId": long, "scheduleTodo": string, "scheduleWriter": string, "schedulePassword": string, "scheduleCreated": string, "scheduleUpdated": string }`                              | `201 Created`        |
| `GET`      | `/schedules`         | 일정 목록 조회                      | Query: <br> - `scheduleUpdated` (예: "2025-02-02", `optional`)<br> - `scheduleWriter` (`optional`) | 없음                                                                                                             | `[ { "scheduleId": long, "scheduleTodo": string, "scheduleWriter": string, "scheduleCreated": string, "scheduleUpdated": string }, ... ]`                      | `200 OK`        |
| `GET`      | `/schedules/{id}`    | 일정 단건 조회                       | Path: <br> - `id`                                                                         | 없음                                                                                                             | `{ "scheduleId": long, "scheduleTodo": string, "scheduleWriter": string, "scheduleCreated": string, "scheduleUpdated": string }`                               | `200 OK`        |
| `PUT`      | `/schedules/{id}`    | 일정 수정                            | Path: <br> - `id`                                                                         | `{ "scheduleTodo": string, "scheduleWriter": string, "schedulePassword": string }`                                                   | `{ "scheduleId": long, "scheduleTodo": string, "scheduleWriter": string, "schedulePassword": string, "scheduleCreated": string, "scheduleUpdated": string }`                               | `200 OK`        |
| `DELETE`   | `/schedules/{id}`    | 일정 삭제                           | Path: <br> - `id` <br> Query: <br> - `schedulePassword` (`mandatory`)                              | 없음                                                                                                             | 없음                                                                                                                                             | `204 No Content`        |

</div>

<br>

### SQL

```sql
CREATE TABLE schedules (
      schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
      schedule_todo VARCHAR(255) NOT NULL,
      schedule_password VARCHAR(255) NOT NULL,
      schedule_writer VARCHAR(255) NOT NULL,
      schedule_created DATETIME NOT NULL,
      schedule_updated DATETIME NOT NULL
)

```
### ERD
![제목 없음.jpg](%EC%A0%9C%EB%AA%A9%20%EC%97%86%EC%9D%8C.jpg)