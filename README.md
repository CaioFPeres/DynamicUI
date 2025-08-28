# DynamicUI

This is an Android project made with the goal of being able to change the UI elements through LLM prompts. It was made in the most generic way possible, so that the LLM can accept a range of different inputs that can achieve the same goal.

Example: 
- "Make the first text box bigger";
- "Make the first text box wider";
- "Make the first text box wider and the second one taller";
- "Make the first text box wider and the second one taller, and the background red!";

In order to execute the project, open it on Android Studio and press the Run button. Or, generate an APK through `Build -> Generate App Bundles or APKs -> Generate APK` and install on your device.

\
Here is the folder structure of this project:
``` bash
├─📁 dynamicui
│  ├─📄 MainActivity.kt
│  ├─📁 data
│  │  ├─📁 remote
│  │  │  ├─📄 AssistantResponseAPI.kt
│  │  │  └─📄 RetrofitClient.kt
│  │  └─📁 repository
│  │     └─📄 AssistantResponseRepositoryImpl.kt
│  ├─📁 di
│  │   ├─📄 App.kt
│  │   └─📄 modules.kt
│  ├─📁 domain
│  │  ├─📁 model
│  │  │  └─📄 AssistantModel.kt
│  │  └─📁 repository
│  │     └─📄 AssistantResponseRepository.kt
│  └─📁 ui
│    ├─📁 listScreen
│    │  ├─📄 ListScreen.kt
│    │  ├─📄 ListScreenState.kt
│    │  └─📄 ListScreenViewModel.kt
│    ├─📁 loginScreen
│    │  ├─📄 LoginScreen.kt
│    │  ├─📄 LoginScreenViewModel.kt
│    │  └─📄 LoginUIState.kt
│    └─📁 theme
│      ├─📄 Color.kt
│      ├─📄 Theme.kt
│      └─📄 Type.kt
```