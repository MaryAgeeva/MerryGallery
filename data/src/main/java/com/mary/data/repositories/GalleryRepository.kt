package com.mary.data.repositories

import android.content.Context
import com.mary.domain.entities.Directory
import com.mary.domain.entities.GalleryImage
import com.mary.domain.repositories.IGalleryRepository
import io.reactivex.Single
import javax.inject.Inject
import android.provider.MediaStore
import android.util.Log
import com.mary.domain.APP_TAG
import java.io.File
import java.lang.Exception

class GalleryRepository @Inject constructor(private val context: Context): IGalleryRepository {

    override fun getAllDirectories(): Single<List<Directory>> =
        Single.create { emitter ->
            val dirs = mutableListOf<Directory>()
            val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATA,
                "COUNT(*)"
            )
            val selection = "1 = 1) GROUP BY (${MediaStore.Images.Media.BUCKET_ID}"
            val order = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

            context.contentResolver.query(uri, projection, selection, null, order)?.use {
                while(it.moveToNext()) {
                    if(File(it.getString(it.getColumnIndex(projection[2]))).exists()) {
                        try {
                            dirs.add(
                                Directory(
                                    it.getInt(it.getColumnIndex(projection[0])),
                                    it.getString(it.getColumnIndex(projection[1])),
                                    it.getString(it.getColumnIndex(projection[2])),
                                    it.getInt(it.getColumnIndex(projection[3])))
                                )
                        }catch (e: Exception) {
                            Log.e("$APP_TAG Repository", "exception: $e")
                        }
                    }
                }
            }
            emitter.onSuccess(dirs.asSequence().distinctBy { it.title }.toList())
        }

    override fun getImagesForDirectory(id: Int): Single<List<GalleryImage>> =
        Single.create { emitter ->
            val dirs = mutableListOf<GalleryImage>()
            val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DATE_TAKEN
            )
            val selection = "${MediaStore.Images.Media.BUCKET_ID} = ?"
            val order = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

            context.contentResolver.query(uri, projection, selection, arrayOf(id.toString()), order)?.use {
                while(it.moveToNext()) {
                    if(File(it.getString(it.getColumnIndex(projection[1]))).exists()) {
                        try {
                            dirs.add(
                                GalleryImage(
                                    it.getString(it.getColumnIndex(projection[0])),
                                    it.getString(it.getColumnIndex(projection[1])),
                                    it.getString(it.getColumnIndex(projection[2])))
                            )
                        }catch (e: Exception) {
                            Log.e("$APP_TAG Repository", "exception: $e")
                        }
                    }
                }
            }
            emitter.onSuccess(dirs.asSequence().distinctBy { it.title }.toList())
        }
}