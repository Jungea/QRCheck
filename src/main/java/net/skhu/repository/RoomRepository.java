package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

}
