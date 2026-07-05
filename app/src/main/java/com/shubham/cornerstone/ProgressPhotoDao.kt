package com.shubham.cornerstone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressPhotoDao {

    @Insert
    suspend fun insertPhoto(photo: ProgressPhotoEntity): Long

    @Query(
        """
        SELECT *
        FROM progress_photo
        ORDER BY capturedAtEpochMs DESC
        """
    )
    fun observeAllPhotos(): Flow<List<ProgressPhotoEntity>>

    @Query(
        """
        SELECT *
        FROM progress_photo
        ORDER BY capturedAtEpochMs DESC
        LIMIT :limit
        """
    )
    fun observeLatestPhotos(limit: Int = 5): Flow<List<ProgressPhotoEntity>>

    @Query(
        """
        DELETE FROM progress_photo
        WHERE id = :photoId
        """
    )
    suspend fun deletePhotoById(photoId: Long)
}