@echo off
"C:\\Users\\suzukikaho\\AppData\\Local\\Android\\Sdk\\cmake\\3.18.1\\bin\\cmake.exe" ^
  "-HC:\\Users\\suzukikaho\\AndroidStudioProjects\\C_2207\\C_2207\\testprog\\app\\src\\main\\cpp" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=23" ^
  "-DANDROID_PLATFORM=android-23" ^
  "-DANDROID_ABI=arm64-v8a" ^
  "-DCMAKE_ANDROID_ARCH_ABI=arm64-v8a" ^
  "-DANDROID_NDK=C:\\Users\\suzukikaho\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\suzukikaho\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\suzukikaho\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\suzukikaho\\AppData\\Local\\Android\\Sdk\\cmake\\3.18.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=C:\\Users\\suzukikaho\\AndroidStudioProjects\\C_2207\\C_2207\\testprog\\app\\build\\intermediates\\cxx\\Debug\\863c2t5j\\obj\\arm64-v8a" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=C:\\Users\\suzukikaho\\AndroidStudioProjects\\C_2207\\C_2207\\testprog\\app\\build\\intermediates\\cxx\\Debug\\863c2t5j\\obj\\arm64-v8a" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-BC:\\Users\\suzukikaho\\AndroidStudioProjects\\C_2207\\C_2207\\testprog\\app\\.cxx\\Debug\\863c2t5j\\arm64-v8a" ^
  -GNinja
