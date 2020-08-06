# UnityPlatformPlugin

#### Unity 와 통신이 가능한 플러그인입니다

#### AndroidManifest 설정

UnityPlayerActivity 를 확장하여 만든 시작 Activity 를 변경합니다. com.andro.unityplatformplugin.AndroMainActivity

```
<application
        android:theme="@style/UnityThemeSelector"
        android:icon="@drawable/app_icon"
        android:label="@string/app_name">
        <activity android:name="com.andro.unityplatformplugin.AndroMainActivity"
                  android:label="@string/app_name">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
```

#### MainTemplate.gradle 설정

해당 라이브러리는 jcenter 에 보관되어있습니다.

```
buildscript {
    repositories {**ARTIFACTORYREPOSITORY**
        google()
        jcenter()
    }
```

사용할 라이브러리를 추가합니다. https://bintray.com/beta/#/numicn/AndroAndroidPlugin?tab=packages ( 추가하기전 라이브러리 버전 확인 )
```
dependencies {
	  implementation 'com.andro.unityplatformplugin.common:app:0.0.16'
    ....
```
