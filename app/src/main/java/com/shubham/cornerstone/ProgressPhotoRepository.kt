package com.shubham.cornerstone

import android.content.Context
import kotlinx.coroutines.flow.Flow
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ProgressPhotoRepository(
    private val dao: ProgressPhotoDao,
    private val appContext: Context
) {

    fun observeAllPhotos(): Flow<List<ProgressPhotoEntity>> {
        return dao.observeAllPhotos()
    }

    fun observeLatestPhotos(): Flow<List<ProgressPhotoEntity>> {
        return dao.observeLatestPhotos()
    }

    suspend fun saveCapturedPhotoMetadata(
        file: File,
        weightKg: Double? = null
    ): Long {
        return dao.insertPhoto(
            ProgressPhotoEntity(
                filePath = file.absolutePath,
                fileName = file.name,
                localDate = LocalDate.now().toString(),
                weightKg = weightKg
            )
        )
    }

    suspend fun deletePhoto(photo: ProgressPhotoEntity) {
        val file = File(photo.filePath)
        if (file.exists()) {
            file.delete()
        }

        dao.deletePhotoById(photo.id)
    }

    fun createNewProgressPhotoFile(): File {
        val dateFolder = LocalDate.now().toString()

        val folder = File(
            appContext.filesDir,
            "cornerstone/progress/photos/$dateFolder"
        )

        if (!folder.exists()) {
            folder.mkdirs()
        }

        val timestamp = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
        )

        return File(
            folder,
            "progress_photo_$timestamp.jpg"
        )
    }
}