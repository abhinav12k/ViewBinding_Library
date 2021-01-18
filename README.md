# ViewBinding Library
![JitPack](https://img.shields.io/jitpack/v/github/abhinav78910/ViewBinding_Library?style=for-the-badge)
![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/abhinav78910/ViewBinding_Library?style=for-the-badge)

This library helps to reduce the boilerplate code in initializing activities and fragments using ViewBinding. With this library you can initialize fragments, activities with a single line of code and can use the reference anywhere in the file.

## Installation

- Add the Jitpack.io dependency in project level gradle file

```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
  
- Add `ViewBinding_Library` dependency in app level gradle file (Replace `Tag` with latest release)

```gradle
dependencies {
	implementation 'com.github.abhinav78910:ViewBinding_Library:Tag'
}
```

### Using ViewBinding_Library into your Android Project

- Using ViewBinding_Library in Activity

```kotlin
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
```
- Using ViewBinding_Library in Fragments

```kotlin
class TestFragment : Fragment(R.layout.fragment_test) {

    private val binding: FragmentTestBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
```
Incase of fragments Use onViewCreated instead of onCreateView. UI for the fragment is taken care by the library you need not to use onCreateView for initializing the UI for fragment.

#### Quick Fixes
- If you are facing this error

```error
Cannot inline bytecode built with JVM target 1.8 into bytecode that is being built with JVM target 1.6. Please specify proper '-jvm-target' option
```
Then update your JVM target to 1.8 in the app level build.gradle file

```gradle
android{
	...
    	compileOptions {
        	sourceCompatibility JavaVersion.VERSION_1_8
        	targetCompatibility JavaVersion.VERSION_1_8
    	}
    	kotlinOptions {
        	jvmTarget = '1.8'
    	}
}
```

## License

    Copyright (c) 2021 Abhinav

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
