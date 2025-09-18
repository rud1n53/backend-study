package com.example.demo.test; 
// package는 이 클래스가 속한 폴더 경로를 표시하는 것. (소속(네임스페이스)만을 알려줌)

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// 저 annotation에서 가져온게 @GetMapping, @PostMapping 등등임
// @붙은 것들이 다 어노테이션임. 역할 표시..라네요?

@RestController // 해당 클래스는 REST API 요청을 처리하는 컨트롤러임을 밝힘.
@RequestMapping("/api") // 이 컨트롤러의 모든 주소 앞에 /api를 붙인다는 뜻.
// 따라서 GetMapping("/posts")이면 실제 호출 주소는 /api/posts이다.

public class TestController { 
// java파일 제목은 그 파일이 정한 객체의 이름을 따라가야 한다.

    @GetMapping("/posts") // GET 방식으로 /api/posts 가 들어오면 이 메서드 실행
    public ResponseEntity<TestDto> test(@PathVariable("name") String name) {
        TestDto dto = new TestDto();
        dto.setName(name); 
        dto.setAge(30);
        //이 메서드들은 Lombok이 객체 프로퍼티 기준으로 자동생성해준것.
        return ResponseEntity.ok(dto); 
        // 이 ok라는 메서드, HTTP 응답을 만드는 것. (ok()가 상태 코드 200을 의미)
        // dto는 JSON으로 변환돼서 응답 body에 담긴다.
    }

    @PostMapping("/test") // POST 방식으로 /api/tests 가 들어오면 이 메서드 실행
    public ResponseEntity<TestDto> testPost(@RequestBody TestDto dto) { 
        //ResponseEntity<TestDto>는 HTTP 응답을 감싸는 객체이다. 
        //<TestDto> -> 응답 본문에 TestDto 타입 데이터를 넣는다는 의미.
        //(@RequestBody TestDto dto)는 클라이언트가 body에 JSON을 보내면, 
        //그걸 TestDto 객체로 변환해서 넣어준다고 하네요.
        dto.setAge(dto.getAge() + 1);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/test/{id}")
    public ResponseEntity<Integer> testDelete(@PathVariable Integer id) {
		    // @PathVariable이 URL의 {id}부분을 매개변수로 넣어줌
        return ResponseEntity.ok(id);
    }
}
// compileOnly("org.projectlombok:lombok")
// Lombok이 컴파일할때만 필요하다는 소리 (실행 시 불필요)
// runtimeOnly("com.mysql:mysql-connector-j")
// 실행시 MySQL DB랑 연결할 때 필요한 드라이버.
// annotationProcessor("org.projectlombok:lombok)
// Lombok이 @Getter, @Setter 등의 어노테이션을 해석해서 자동 코드 생성