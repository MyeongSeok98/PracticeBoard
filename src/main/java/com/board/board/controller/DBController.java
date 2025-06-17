package com.board.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/DB")
    public ResponseEntity<String> checkDatabase() {
        try {
            // 간단히 1을 SELECT 해 봅니다
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);

            // 또는 버전 정보를 확인하고 싶으면 아래처럼 사용하세요
            // String version = jdbcTemplate.queryForObject("SELECT VERSION()", String.class);

            return ResponseEntity.ok("DB 연결 OK! SELECT 1 결과: " + result);
            // ResponseEntity.ok("DB 연결 OK! MySQL 버전: " + version);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("DB 연결 실패: " + e.getMessage());
        }
    }
}