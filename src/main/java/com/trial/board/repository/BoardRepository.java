package com.trial.board.repository;

import com.trial.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DB에 저장하기 위해서 Repository가 필요.

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> { //<Entity명,PK타입> Board클래스의 Id타입을 Integer로 설정했으므로

    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
