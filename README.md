# CameraView

*This is a preview release. The API is subject to change.*

This is not an official Google product.

CameraView aims to help Android developers easily integrate Camera features.

Requires API Level 9. The library uses Camera 1 API on API Level 9-20 and Camera2 on 21 and above.

| API Level | Camera API | Preview View |
|:---------:|------------|--------------|
| 9-13      | Camera1    | SurfaceView  |
| 14-20     | Camera1    | TextureView  |
| 21-23     | Camera2    | TextureView  |
| 24        | Camera2    | SurfaceView  |

## Features

- Camera preview by placing it in a layout XML (and calling the start method)
- Configuration by attributes
  - Aspect ratio (app:aspectRatio)
  - Auto-focus (app:autoFocus)
  - Flash (app:flash)

## Usage

```xml
<com.google.android.cameraview.CameraView
    android:id="@+id/camera"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:keepScreenOn="true"
    android:adjustViewBounds="true"
    app:autoFocus="true"
    app:aspectRatio="4:3"
    app:facing="back"
    app:flash="auto"/>
```

```java
    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.start();
    }

    @Override
    protected void onPause() {
        mCameraView.stop();
        super.onPause();
    }
```

You can see a complete usage in the demo app.

## Deployment

Before you can deploy to our Maven repository, you first need to add a `private.properties` file at the root of the project. This file should contain values for `myget.username` and `myget.token` where you enter your MyGet username and generated token (not your password). For example:

```
myget.username = username
myget.token = aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee
```

To deploy the package, assemble and upload the generated archives to our Maven repository.

```
./gradlew :library:assemble :library:uploadArchives
```

If you have made changes and are deploying a new version, be sure to update the version number in the library's `build.gradle` file first.
