package com.smart.construction.painting.repo;

import com.smart.construction.painting.entity.ProjectEntity;
import com.smart.construction.painting.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<RoomEntity, Long> {

	/**
	 * Fetch list of rooms detail for a single project
	 * @param projectId
	 * @return list of rooms detail
	 */
	List<RoomEntity> findByProject_Id(long projectId);

}