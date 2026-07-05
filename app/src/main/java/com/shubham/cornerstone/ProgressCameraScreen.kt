package com.shubham.cornerstone

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun ProgressCameraScreen(
    repository: ProgressPhotoRepository,
    onExit: () -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val scope = rememberCoroutineScope()

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission = granted
    }

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    val latestPhotos by repository
        .observeLatestPhotos()
        .collectAsStateWithLifecycle(initialValue = emptyList())

    var imageCapture by remember {
        mutableStateOf<ImageCapture?>(null)
    }

    var useFrontCamera by remember {
        mutableStateOf(true)
    }

    var countdown by remember {
        mutableIntStateOf(0)
    }

    var isCapturing by remember {
        mutableStateOf(false)
    }

    var statusMessage by remember {
        mutableStateOf("Set your phone, step back, then capture.")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF161518),
                        InkBlack
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(16.dp))

            Text(
                text = "← Back",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clickable {
                        onExit()
                    }
                    .padding(vertical = 8.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Progress\ncamera",
                fontSize = 40.sp,
                lineHeight = 44.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Take private progress photos for your weight cut. Saved only on this device.",
                fontSize = 15.sp,
                lineHeight = 23.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(22.dp))

            if (hasCameraPermission) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(430.dp),
                    shape = RoundedCornerShape(24.dp),
                    color = Charcoal
                ) {
                    Box {
                        CameraPreview(
                            modifier = Modifier.fillMaxSize(),
                            useFrontCamera = useFrontCamera,
                            onImageCaptureReady = { capture ->
                                imageCapture = capture
                            },
                            onCameraError = { message ->
                                statusMessage = message
                            }
                        )

                        if (countdown > 0) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.35f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = countdown.toString(),
                                    fontSize = 96.sp,
                                    fontWeight = FontWeight.Black,
                                    color = FightRed
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            useFrontCamera = !useFrontCamera
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Charcoal
                        )
                    ) {
                        Text(
                            text = if (useFrontCamera) {
                                "Use back camera"
                            } else {
                                "Use front camera"
                            },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = {
                            if (!isCapturing) {
                                scope.launch {
                                    isCapturing = true
                                    statusMessage = "Get ready..."

                                    for (number in 3 downTo 1) {
                                        countdown = number
                                        delay(1_000L)
                                    }

                                    countdown = 0
                                    statusMessage = "Capturing..."

                                    captureProgressPhoto(
                                        context = context,
                                        repository = repository,
                                        imageCapture = imageCapture,
                                        scope = scope,
                                        onSaved = { message ->
                                            statusMessage = message
                                            isCapturing = false
                                        },
                                        onError = { message ->
                                            statusMessage = message
                                            isCapturing = false
                                        }
                                    )
                                }
                            }
                        },
                        enabled = !isCapturing && imageCapture != null,
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = FightRed
                        )
                    ) {
                        Text(
                            text = if (isCapturing) {
                                "Wait..."
                            } else {
                                "Capture"
                            },
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(Modifier.height(14.dp))

                Text(
                    text = statusMessage,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(24.dp))

                if (latestPhotos.size >= 2) {
                    ProgressComparisonCard(
                        latest = latestPhotos[0],
                        previous = latestPhotos[1]
                    )

                    Spacer(Modifier.height(16.dp))
                }

                LatestProgressPhotosCard(
                    photos = latestPhotos,
                    onDeletePhoto = { photo ->
                        scope.launch {
                            repository.deletePhoto(photo)
                            statusMessage = "Photo deleted."
                        }
                    }
                )
            } else {
                CameraPermissionCard(
                    onRequestPermission = {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                )
            }

            Spacer(Modifier.height(36.dp))
        }
    }
}

@Composable
private fun CameraPreview(
    modifier: Modifier,
    useFrontCamera: Boolean,
    onImageCaptureReady: (ImageCapture) -> Unit,
    onCameraError: (String) -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val previewView = remember {
        PreviewView(context).apply {
            scaleType = PreviewView.ScaleType.FILL_CENTER
        }
    }

    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }

    LaunchedEffect(
        useFrontCamera,
        lifecycleOwner
    ) {
        cameraProviderFuture.addListener(
            {
                try {
                    val cameraProvider = cameraProviderFuture.get()

                    val preview = Preview.Builder()
                        .build()
                        .also { cameraPreview ->
                            cameraPreview.setSurfaceProvider(
                                previewView.surfaceProvider
                            )
                        }

                    val imageCapture = ImageCapture.Builder()
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                        .build()

                    val cameraSelector = if (useFrontCamera) {
                        CameraSelector.DEFAULT_FRONT_CAMERA
                    } else {
                        CameraSelector.DEFAULT_BACK_CAMERA
                    }

                    cameraProvider.unbindAll()

                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )

                    onImageCaptureReady(imageCapture)
                } catch (e: Exception) {
                    onCameraError(
                        e.message ?: "Camera could not start."
                    )
                }
            },
            ContextCompat.getMainExecutor(context)
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            try {
                if (cameraProviderFuture.isDone) {
                    cameraProviderFuture.get().unbindAll()
                }
            } catch (_: Exception) {
                // Ignore camera cleanup errors.
            }
        }
    }

    AndroidView(
        modifier = modifier,
        factory = {
            previewView
        }
    )
}

private fun captureProgressPhoto(
    context: Context,
    repository: ProgressPhotoRepository,
    imageCapture: ImageCapture?,
    scope: CoroutineScope,
    onSaved: (String) -> Unit,
    onError: (String) -> Unit
) {
    if (imageCapture == null) {
        onError("Camera is not ready yet.")
        return
    }

    val file = repository.createNewProgressPhotoFile()

    val outputOptions = ImageCapture.OutputFileOptions
        .Builder(file)
        .build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(
                outputFileResults: ImageCapture.OutputFileResults
            ) {
                scope.launch {
                    repository.saveCapturedPhotoMetadata(file)

                    onSaved(
                        "Photo saved privately on this device."
                    )
                }
            }

            override fun onError(exception: ImageCaptureException) {
                if (file.exists()) {
                    file.delete()
                }

                onError(
                    exception.message ?: "Photo capture failed."
                )
            }
        }
    )
}

@Composable
private fun CameraPermissionCard(
    onRequestPermission: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Charcoal
    ) {
        Column(
            modifier = Modifier.padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Camera permission needed",
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Cornerstone needs camera access to take progress photos. Photos stay on your device.",
                fontSize = 14.sp,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(18.dp))

            Button(
                onClick = {
                    onRequestPermission()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FightRed
                )
            ) {
                Text(
                    text = "Allow camera",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun ProgressComparisonCard(
    latest: ProgressPhotoEntity,
    previous: ProgressPhotoEntity
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = Charcoal
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "Compare progress",
                fontSize = 17.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Previous vs latest photo",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ComparisonImageBlock(
                    title = "Previous",
                    photo = previous,
                    modifier = Modifier.weight(1f)
                )

                ComparisonImageBlock(
                    title = "Latest",
                    photo = latest,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ComparisonImageBlock(
    title: String,
    photo: ProgressPhotoEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        ProgressPhotoImage(
            filePath = photo.filePath,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.72f)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = photo.localDate,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun LatestProgressPhotosCard(
    photos: List<ProgressPhotoEntity>,
    onDeletePhoto: (ProgressPhotoEntity) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = Charcoal
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "Latest progress photos",
                fontSize = 17.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(10.dp))

            if (photos.isEmpty()) {
                Text(
                    text = "No photos saved yet.",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                photos.take(5).forEach { photo ->
                    ProgressPhotoRow(
                        photo = photo,
                        onDelete = {
                            onDeletePhoto(photo)
                        }
                    )

                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
private fun ProgressPhotoRow(
    photo: ProgressPhotoEntity,
    onDelete: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Color(0xFF222226)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProgressPhotoImage(
                filePath = photo.filePath,
                modifier = Modifier
                    .size(76.dp)
                    .clip(RoundedCornerShape(14.dp))
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = photo.localDate,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(Modifier.height(2.dp))

                Text(
                    text = photo.fileName,
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(Modifier.width(8.dp))

            Text(
                text = "Delete",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = FightRed,
                modifier = Modifier
                    .clickable {
                        onDelete()
                    }
                    .padding(8.dp)
            )
        }
    }
}

@Composable
private fun ProgressPhotoImage(
    filePath: String,
    modifier: Modifier = Modifier
) {
    val imageBitmap = remember(filePath) {
        loadSampledImageBitmap(
            filePath = filePath,
            targetSize = 720
        )
    }

    if (imageBitmap != null) {
        Image(
            bitmap = imageBitmap,
            contentDescription = "Progress photo",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    } else {
        Box(
            modifier = modifier.background(Color(0xFF2A2A2E)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Missing",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun loadSampledImageBitmap(
    filePath: String,
    targetSize: Int
): ImageBitmap? {
    val file = File(filePath)

    if (!file.exists()) {
        return null
    }

    val boundsOptions = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }

    BitmapFactory.decodeFile(filePath, boundsOptions)

    var sampleSize = 1

    while (
        boundsOptions.outWidth / sampleSize > targetSize ||
        boundsOptions.outHeight / sampleSize > targetSize
    ) {
        sampleSize *= 2
    }

    val decodeOptions = BitmapFactory.Options().apply {
        inSampleSize = sampleSize
    }

    return BitmapFactory
        .decodeFile(filePath, decodeOptions)
        ?.asImageBitmap()
}