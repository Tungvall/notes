package se.twowall.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.twowall.notes.entity.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findNoteBy(Long user_id);

    Optional<Note> getNoteBy(Long id);

    Optional<Note> findByIdAndUserUsername(Long id, String username);

    List<Note> findByUserId(Long user_id);

    Note save(Note note);
    Long id(long id);

}
