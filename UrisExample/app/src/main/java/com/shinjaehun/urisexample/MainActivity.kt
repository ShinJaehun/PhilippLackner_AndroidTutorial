package com.shinjaehun.urisexample

import android.content.ContentUris
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.shinjaehun.urisexample.ui.theme.UrisExampleTheme
import java.io.File
import java.io.FileOutputStream

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES),
            0
        )

        // Uri는 이런 식으로 접근할 수 있음
        val uri = Uri.parse("android.resource://$packageName/drawable/audio")
        val imageBytes = contentResolver.openInputStream(uri)?.use {
            it.readBytes()
        }
        Log.i(TAG, "image size of drawable : ${imageBytes?.size}") // image size of drawable : 46094

        val file = File(filesDir, "audio.png")
        FileOutputStream(file).use {
            it.write(imageBytes)
        }
        Log.i(TAG, "internal copy of the image's uri: ${file.toURI()}")
        // internal copy of the image's uri: file:/data/user/0/com.shinjaehun.readexternalmediafiles/files/audio.png

        val dataUri = Uri.parse("data:text/plain;charset=UTF-8,Hello%20World")
        Log.i(TAG, "data uri: $dataUri")

        setContent {
            UrisExampleTheme {
                Scaffold(
                    modifier = Modifier
                ) { innerPadding ->
                    val projection = arrayOf(
                        MediaStore.Files.FileColumns._ID,
                        MediaStore.Files.FileColumns.DISPLAY_NAME,
                    )

                    val pickImage = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent(),
                        onResult = { contentUri ->
                            Log.i(TAG, "contentUri of your picks: $contentUri")
                            // contentUri of your picks: content://media/picker_get_content/0/com.android.providers.media.photopicker/media/1000000022

                            // 이렇게 사용하면 안됩니다: contentUri는 이미 공유된 정보이기 때문에 암호화(?)되어 있는 상태...
                            // contentUri를 이용해서 file 정보를 얻어올 수 없다고 함...
//                            if (contentUri != null) {
//                                contentResolver.query(
//                                    contentUri,
//                                    projection,
//                                    null,
//                                    null,
//                                    null
//                                )?.use { cursor ->
//                                    val idColumn = cursor.getColumnIndexOrThrow(
//                                        MediaStore.Files.FileColumns._ID
//                                    )
//                                    val nameColumn = cursor.getColumnIndexOrThrow(
//                                        MediaStore.Files.FileColumns.DISPLAY_NAME
//                                    )
//                                    val mimeTypeColumn = cursor.getColumnIndexOrThrow(
//                                        MediaStore.Files.FileColumns.MIME_TYPE
//                                    )
//
//                                    while (cursor.moveToNext()) {
//                                        val id = cursor.getLong(idColumn)
//                                        val name = cursor.getString(nameColumn)
//                                        val mimeType = cursor.getString(mimeTypeColumn)
//
//                                        Log.i(TAG, "id of the file: $id")
//                                        Log.i(TAG, "name of the file: $name")
//                                        Log.i(TAG, "mimeType of the file: $mimeType")
//                                    }
//                                }
//                            }

                            // I don't know if this is the solution you wanted.
                            // It is clear is that the file name cannot be found directly using the contentUri from photoPicker.
                            // Workaround I used: I can't say it's an efficient way.
                            // Anyway, compare the contentUri obtained with photoPicker
                            // with the contentUri of image files in the external gallery.
                            // You are able to get the file name using the contentUri.
                            contentResolver.query(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                projection,
                                null,
                                null,
                                null
                            )?.use { cursor ->
                                val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
                                val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)

                                while (cursor.moveToNext()) {
                                    val id = cursor.getLong(idColumn)
                                    val contentUriOfExternalContent = ContentUris.withAppendedId(
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                        id
                                    )

                                    if (contentUriOfExternalContent.toString().substringAfterLast("/")
                                        == contentUri.toString().substringAfterLast("/")) {
                                        val name = cursor.getString(nameColumn)
                                        Log.i(TAG, "the file name is : $name")
                                    }
                                }
                            }
                        }
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                pickImage.launch("image/*")
                            },
                            modifier = Modifier
                                .padding(innerPadding),

                            ) {
                            Text(
                                text = "Pick image"
                            )
                        }
                    }
                }
            }
        }
    }
}

