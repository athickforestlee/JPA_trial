package com.trial.board.service;

import com.trial.board.entity.Board;
import com.trial.board.repository.BoardRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository; //@Autowired로 인해 SpringBean이 알아서 읽어오기 때문에 private BoardRepository boardRepository = new BoardRepository로 안 써도 된다. *의존관계 주입(Dependency Injection)
    
    //이하 글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile); //throws Exception 예외처리로 transferTo() 오류 해결가능.

        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);

        boardRepository.save(board);
    }
    
    //이하 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable){

        return boardRepository.findAll(pageable);
    }

    //검색한 게시글 리스트 처리
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    //이하 특정 게시글 불러오기
    public Board boardView(Integer id){

        return boardRepository.findById(id).get();
    }

    //특정 게시글 삭제

    public void boardDelete(Integer id){

        boardRepository.deleteById(id);
    }
}
