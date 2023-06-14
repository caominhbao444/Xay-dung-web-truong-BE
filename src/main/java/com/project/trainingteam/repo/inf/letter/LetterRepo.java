package com.project.trainingteam.repo.inf.letter;

import com.project.trainingteam.entities.letter.Letter;
import com.project.trainingteam.entities.letter.LetterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepo extends JpaRepository<Letter,Long> {
    //USER
    @Query("SELECT COUNT(l.id) as check " +
            "FROM Letter l " +
            "WHERE l.username = :username AND l.letterTypeName = :letterTypeName AND l.status != 2")
    Integer countLettersByUserNameAndLetterTypeNameAndStatus(String username, String letterTypeName);

    @Query("SELECT l FROM Letter l WHERE l.username = :username")
    Page<Letter> findLetterByUserName(String username, Pageable pageable);

    @Query("SELECT l FROM Letter l WHERE l.username = :username AND l.status = 0")
    Page<Letter>findUserLetterByUserNameAndStatus0(String username,Pageable pageable);

    @Query("SELECT l FROM Letter l WHERE l.username = :username AND l.status = 1")
    Page<Letter>findUserLetterByUserNameAndStatus1(String username, Pageable pageable);

    @Query("SELECT l FROM Letter l WHERE l.username = :username AND l.status = 2")
    Page<Letter>findUserLetterByUserNameAndStatus2(String username , Pageable pageable);


    ///////////////////////////////////////////////////////////////////////////////
    @Query("SELECT l FROM Letter l WHERE l.facultyName = :facultyName")
    Page<Letter>findLetterByFacultyName(String facultyName, Pageable pageable);


    @Query("SELECT l FROM Letter l WHERE l.facultyName = :facultyName AND l.status = 0")
    Page<Letter>findFacultyLetterByFacultyNameAndStatus0(String facultyName , Pageable pageable);

    @Query("SELECT l FROM Letter l WHERE l.facultyName = :facultyName AND l.status = 1")
    Page<Letter>findFacultyLetterByFacultyNameAndStatus1(String facultyName , Pageable pageable);

    @Query("SELECT l FROM Letter l WHERE l.facultyName = :facultyName AND l.status = 2")
    Page<Letter>findFacultyLetterByFacultyNameAndStatus2(String facultyName , Pageable pageable);

    @Query("SELECT l FROM Letter l " +
            "WHERE l.facultyName = :facultyName AND l.status != 2 " +
            "GROUP BY l.id " +
            "ORDER BY l.status")
    Page<Letter> findLetterByFacultyNameAndStatus0And1(String facultyName, Pageable pageable);


    /////////////////////////////////////////////////////////////////////////////////////////////

}
